package com.teriteri.backend.utils;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Data
@Component
@ConfigurationProperties(prefix = "sf")
public class SnowFlakeProperties {
    //起始时间戳
    private long startTimeStamp;
    //机器ID
    private long workID;
    //数据中心ID
    private long dataCenterID;
    //序列号
    private long sequence;
    //数据中心ID移动位数
    private long dataCenterIndex;
    //机器ID移动位数
    private long workIDIndex;
    //时间戳移动位数
    private long timeStampIndex;
    //记录上一次时间戳
    private long lastTimeStamp;
    //序列号掩码
    private long sequenceMask;
    //序列号长度12位
    private long sequenceLength;

    public SnowFlakeProperties() {
        //默认时间 2020-1-1 00:00:00
        this.startTimeStamp = 1577808000L;
        //设置机器编号 1
        this.workID = 1L;
        //设置数据中心ID 1
        this.dataCenterID = 1;
        //起始序列号 0开始
        this.sequence = 0L;
        //数据中心位移位数
        this.dataCenterIndex = 12L;
        //机器ID位移位数
        this.workIDIndex = 17L;
        //时间戳位移位数
        this.timeStampIndex = 22L;
        //记录上次时间戳
        this.lastTimeStamp =  -1L;
        //序列号长度
        this.sequenceLength = 12L;
        //序列号掩码 等同于 -1L ^ (-1L << 12)
        this.sequenceMask = ~ (-1L << sequenceLength);
    }

    public SnowFlakeProperties(long startTimeStamp, long workID, long dataCenterID, long sequence,
                               long dataCenterIndex, long workIDIndex, long timeStampIndex, long lastTimeStamp,
                               long sequenceMask, long sequenceLength) {
        this.startTimeStamp = startTimeStamp;
        this.workID = workID;
        this.dataCenterID = dataCenterID;
        this.sequence = sequence;
        this.dataCenterIndex = dataCenterIndex;
        this.workIDIndex = workIDIndex;
        this.timeStampIndex = timeStampIndex;
        this.lastTimeStamp = lastTimeStamp;
        this.sequenceMask = sequenceMask;
        this.sequenceLength = sequenceLength;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public void setWorkID(long workID) {
        this.workID = workID;
    }

    public void setDataCenterID(long dataCenterID) {
        this.dataCenterID = dataCenterID;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public void setDataCenterIndex(long dataCenterIndex) {
        this.dataCenterIndex = dataCenterIndex;
    }

    public void setWorkIDIndex(long workIDIndex) {
        this.workIDIndex = workIDIndex;
    }

    public void setTimeStampIndex(long timeStampIndex) {
        this.timeStampIndex = timeStampIndex;
    }

    public void setLastTimeStamp(long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }

    public void setSequenceMask(long sequenceMask) {
        this.sequenceMask = sequenceMask;
    }

    public void setSequenceLength(long sequenceLength) {
        this.sequenceLength = sequenceLength;
    }
}
