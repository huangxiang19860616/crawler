package com.hx.app.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
public class TaskerData {

    private String taskId;

    private Date createTime;

    private Date beginTime;

    private Date endTime;

    private Date crawlStartTime;

    private Date crawFinishTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCrawlStartTime() {
        return crawlStartTime;
    }

    public void setCrawlStartTime(Date crawlStartTime) {
        this.crawlStartTime = crawlStartTime;
    }

    public Date getCrawFinishTime() {
        return crawFinishTime;
    }

    public void setCrawFinishTime(Date crawFinishTime) {
        this.crawFinishTime = crawFinishTime;
    }

}
