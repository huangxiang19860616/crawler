package com.hx.app.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class TaskEntity {
    private String processor;
    private String downloader;
    private String scheduler;
    private Integer threadNum;
    private List<String> pipline;

    public String getProcessor() {
        return processor;
    }

    public String getDownloader() {
        return downloader;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public String getScheduler() {
        return scheduler;
    }

    public List<String> getPipline() {
        return pipline;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setDownloader(String downloader) {
        this.downloader = downloader;
    }

    public void setScheduler(String scheduler) {
        this.scheduler = scheduler;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public void setPipline(List<String> pipline) {
        this.pipline = pipline;
    }
}
