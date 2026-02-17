package com.teriteri.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teriteri.backend.pojo.History;
import com.teriteri.backend.pojo.dto.HistoryDTO;
import com.teriteri.backend.pojo.vo.VideoVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface HistoryMapper extends BaseMapper<History>{
    void addHistory(History history);

    History getHistoryByUserIdAndEntityId(Integer entityType, Long entityId, Long userId);

    void deleteHistory(Long id);

    @MapKey("dateStr")
    Map<String, HistoryDTO> getHistory(Long userId);

    void deleteAllHistory(Long userId);

    void deleteHistoryFifteenDaysAgo();

    History selectById(Long id);

}
