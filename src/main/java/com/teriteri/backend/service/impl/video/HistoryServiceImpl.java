package com.teriteri.backend.service.impl.video;


import com.teriteri.backend.constant.EntityConstant;
import com.teriteri.backend.mapper.HistoryMapper;
import com.teriteri.backend.mapper.UserMapper;
import com.teriteri.backend.mapper.VideoMapper;
import com.teriteri.backend.pojo.History;
import com.teriteri.backend.pojo.User;
import com.teriteri.backend.pojo.Video;
import com.teriteri.backend.pojo.VideoStats;
import com.teriteri.backend.pojo.Category;
import com.teriteri.backend.pojo.dto.HistoryDTO;
import com.teriteri.backend.pojo.vo.VideoVO;
import com.teriteri.backend.service.video.HistoryService;
import com.teriteri.backend.service.video.VideoService;
import com.teriteri.backend.pojo.dto.UserDTO;
import com.teriteri.backend.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.teriteri.backend.utils.SnowFlake;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private final SnowFlake snowFlake;
    @Autowired
    private final VideoMapper videoMapper;
    @Autowired
    private final HistoryMapper historyMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private VideoService videoService;
    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    @Qualifier("taskExecutor")
    private Executor taskExecutor;

    /**
     * 添加用户观看历史记录的方法
     *
     * @param userId  用户ID
     * @param videoId 视频ID
     */
    @Override
    public void addHistory(Long userId, Long videoId) {
        // 根据用户ID获取用户信息
        User user = userMapper.selectById(userId);
        // 检查用户是否开启记录历史的设置
        // 根据视频ID获取视频信息
        Video video = videoMapper.selectById(videoId);
        // 创建新的历史记录对象
        History history = new History();
        // 使用雪花算法生成唯一ID
        history.setId(snowFlake.getID());
        // 设置实体类型为视频
        history.setEntityType(EntityConstant.VIDEO_TYPE);
        // 设置实体ID为视频ID
        history.setEntityId(video.getVid().longValue());
        // 设置用户ID
        history.setUserId(userId);
        // 设置创建时间为当前时间
        history.setCreatedTime(LocalDateTime.now());
        // 通过RabbitMQ发送历史记录消息
//        rabbitTemplate.convertAndSend(MqConstant.CRUD_EXCHANGE, MqConstant.HISTORY_BINDING_KEY, history);
        historyMapper.addHistory(history);
        // 清除用户历史记录的缓存
        String cacheKey = "history:user:" + userId;
        redisUtil.removeCache(cacheKey); // 删除缓存

    }


    /**
     * 获取用户的历史记录
     *
     * @param userId 用户ID
     * @return 返回按日期降序排列的历史记录Map，键为日期字符串，值为包含视频信息的历史DTO
     */
    @Override
    public Map<String, HistoryDTO> getHistory(Long userId) {
        // 从数据库获取用户的历史记录
        Map<String, HistoryDTO> history = historyMapper.getHistory(userId);

        // 遍历历史记录中的每一天
        for (Map.Entry<String, HistoryDTO> entry : history.entrySet()) {
            HistoryDTO historyDTO = entry.getValue();
            // 获取该天所有视频的ID列表
            List<Long> entityIds = historyDTO.getEntityIds();

            // 将Long列表转换为Integer列表
            List<Integer> intIds = entityIds.stream()
                    .map(Long::intValue)
                    .collect(Collectors.toList());

            // 使用正确的方法获取视频数据
            List<Map<String, Object>> videoMaps = videoService.getVideosWithDataByIdList(intIds);

            // 将Map转换为VideoVO列表
            List<VideoVO> videoVOS = videoMaps.stream()
                    .map(map -> {
                        Video video = (Video) map.get("video");
                        UserDTO userDTO = (UserDTO) map.get("user");  // 修改为 UserDTO
                        VideoStats stats = (VideoStats) map.get("stats");
                        Category category = (Category) map.get("category");

                        VideoVO videoVO = new VideoVO();
                        // 设置视频基本信息
                        videoVO.setVid(video.getVid());
                        videoVO.setUid(video.getUid());
                        videoVO.setTitle(video.getTitle());
                        videoVO.setType(video.getType().byteValue());
                        videoVO.setAuth(video.getAuth().byteValue());
                        videoVO.setDuration(video.getDuration());
                        videoVO.setDescr(video.getDescr());
                        videoVO.setCoverUrl(video.getCoverUrl());
                        videoVO.setVideoUrl(video.getVideoUrl());
                        videoVO.setMcId(video.getMcId());
                        videoVO.setScId(video.getScId());
                        videoVO.setTags(video.getTags());
                        videoVO.setStatus(video.getStatus().byteValue());
                        videoVO.setUploadDate(video.getUploadDate());
                        videoVO.setDeleteDate(video.getDeleteDate());

                        // 设置用户信息
                        videoVO.setNickname(userDTO.getNickname());  // 使用 UserDTO 的方法

                        // 设置统计数据
                        videoVO.setPlay(stats.getPlay());
                        videoVO.setGood(stats.getGood());
                        videoVO.setBad(stats.getBad());
                        videoVO.setCoin(stats.getCoin());
                        videoVO.setCollect(stats.getCollect());
                        videoVO.setShare(stats.getShare());
                        videoVO.setComment(stats.getComment());

                        return videoVO;
                    })
                    .collect(Collectors.toList());
            //在这里添加去重逻辑
            videoVOS = videoVOS.stream()
                    .collect(Collectors.toMap(
                            VideoVO::getVid, // 以 vid 作为键
                            Function.identity(), // 保留原始对象
                            (existing, replacement) -> existing // 若重复则保留第一个
                    ))
                    .values()
                    .stream()
                    .collect(Collectors.toList());

            // 进行重新排序，保持原始顺序
            // 将视频列表转换为以视频ID为键的Map
            // 将视频列表转换为以视频ID为键的Map，并将键类型从Integer转换为Long
            Map<Long, VideoVO> videoMap = videoVOS.stream()
                    .collect(Collectors.toMap(
                            videoVO -> videoVO.getVid().longValue(), // 显式将Integer转换为Long
                            Function.identity()
                    ));

            // 按照原始entityIds的顺序重新排序视频列表
            List<VideoVO> sortedVideos = entityIds.stream()
                    .map(videoMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // 将排序后的视频列表设置回DTO
            historyDTO.setVideoVOS(sortedVideos);
        }

        // 创建日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 对历史记录按日期进行降序排序
        history = history.entrySet().stream()
                .sorted((e1, e2) -> {
                    // 将日期字符串解析为LocalDate对象
                    LocalDate d1 = LocalDate.parse(e1.getKey(), formatter);
                    LocalDate d2 = LocalDate.parse(e2.getKey(), formatter);
                    return d2.compareTo(d1); // 降序排序
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,           // 保留原始键（日期字符串）
                        Map.Entry::getValue,        // 保留原始值（HistoryDTO）
                        (oldValue, newValue) -> oldValue, // 碰撞策略（可选）
                        LinkedHashMap::new // 有序的 Map
                ));
        return history;
    }



    @Override
    public void deleteAllHistory(Long userId) {
        historyMapper.deleteAllHistory(userId);
        // 清除用户历史记录的缓存
        String cacheKey = "history:user:" + userId;
        redisUtil.removeCache(cacheKey); // 删除缓存
    }

    @Override
    public void deleteHistory(Long id) {
        // 删除数据库中的历史记录
        historyMapper.deleteHistory(id);
        // 查询该记录所属的用户ID
        History history = historyMapper.selectById(id);
        if (history != null) {
            // 清除该用户的历史记录缓存
            String recordCacheKey = "history:record:" + id;
            redisUtil.removeCache(recordCacheKey);
        }
    }
}

