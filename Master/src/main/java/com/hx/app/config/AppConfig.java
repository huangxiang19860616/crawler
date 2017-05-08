package com.hx.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@Component
public class AppConfig {

    @Value("${zookeeper.ip}")
    private String zookeeperIp;

    @Value("${zookeeper.port}")
    private int zookeeperPort;

    @Value("${redis.ip}")
    private String redisIp;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${server.port}")
    private int serverPort;

    @Value("${taskscan.period}")
    private int taskScanPeriod;

    public void setTaskScanPeriod(int taskScanPeriod) {
        this.taskScanPeriod = taskScanPeriod;
    }

    public  int getTaskScanPeriod() {
        return taskScanPeriod;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getZookeeperIp() {
        return zookeeperIp;
    }

    public void setZookeeperIp(String zookeeperIp) {
        this.zookeeperIp = zookeeperIp;
    }

    public int getZookeeperPort() {
        return zookeeperPort;
    }

    public void setZookeeperPort(int zookeeperPort) {
        this.zookeeperPort = zookeeperPort;
    }

    public String getRedisIp() {
        return redisIp;
    }

    public void setRedisIp(String redisIp) {
        this.redisIp = redisIp;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

}
