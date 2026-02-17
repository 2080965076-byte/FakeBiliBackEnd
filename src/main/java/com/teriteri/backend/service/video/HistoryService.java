package com.teriteri.backend.service.video;

import com.teriteri.backend.pojo.dto.HistoryDTO;

import java.util.Map;

public interface HistoryService {
    void addHistory(Long userId, Long videoId);

    Map<String, HistoryDTO> getHistory(Long userId);


    void deleteAllHistory(Long userId);

    void deleteHistory(Long id);
}
