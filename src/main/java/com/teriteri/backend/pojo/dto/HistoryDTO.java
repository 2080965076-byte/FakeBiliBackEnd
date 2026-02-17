package com.teriteri.backend.pojo.dto;


import com.teriteri.backend.pojo.vo.VideoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO implements Serializable {
    // 日期字符串，表示历史记录的日期
    private String dateStr;
    // 实体ID列表，用于关联其他业务实体
    private List<Long> entityIds;
    // 视频视图对象列表，包含视频的详细信息
    private List<VideoVO> videoVOS;
    // 创建时间列表，记录历史记录的创建时间
    private List<LocalDateTime> createTimes;
    // 历史记录ID列表，用于唯一标识每条历史记录
    private List<Long> ids;
}
