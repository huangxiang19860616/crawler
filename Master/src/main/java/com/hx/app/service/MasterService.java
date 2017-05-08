package com.hx.app.service;

import com.hx.app.config.AppConfig;
import com.hx.app.dao.TaskDao;
import com.hx.app.listener.TaskCacheListener;
import com.hx.app.util.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@Service
public class MasterService extends LeaderSelectorListenerAdapter implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterService.class);

    private static final String PATH_ROOT_MASTER = "/crawler/leader";

    private static final String PATH_ROOT_TASKS = "/crawler/tasks";

    private LeaderSelector leaderSelector;

    private CuratorFramework client;

    private final AtomicInteger leaderCount = new AtomicInteger();

    @Autowired
    private AppConfig config;

    private TreeCache taskCache;

    //关闭锁
    private CountDownLatch closeLatch = new CountDownLatch(1);

    @Autowired
    private TaskDao taskDao;

    /**
     * 任务定时器，用于定时的从数据库中扫描任务。
     */
    private Timer tasksTimer = new Timer();

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {

        LOGGER.info("takeLeadership count " +  leaderCount.getAndIncrement());

        //1. 取出数据库中未完成的任务
       // List<TaskEntity> taskList =  taskDao.findAllValidByStatus(0);

        //2. 把任务挂载到任务节点上

        //3. 监听端点
        taskCache.getListenable().addListener(new TaskCacheListener());
        taskCache.start();

        startScanTask();

        closeLatch.await();
    }

    private void startScanTask() {
        tasksTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    LOGGER.info("+++-> method takeLeadership, tasks scanning ......  period: " + config.getTaskScanPeriod() + " minutes");
                    tasksScanner();

                } catch (Exception e) {

                    LOGGER.warn("+++-> method takeLeadership, tasks scanning Exception!!!!");
                }
            }

        }, 120 * 1000, config.getTaskScanPeriod() * 60 * 1000);
    }

    private void tasksScanner() {
    }

    @PreDestroy
    @Override
    public void close() throws IOException {
        closeLatch.countDown();
        CloseableUtils.closeQuietly(taskCache);
        CloseableUtils.closeQuietly(leaderSelector);
        CloseableUtils.closeQuietly(client);
    }

    @PostConstruct
    public void start() {
        initialize();

        client.start();

        nodeCreate();

        leaderSelector.start();
    }

    private void nodeCreate() {
        try {
            if (!CuratorUtils.isNodeExist(client, PATH_ROOT_MASTER)) {
                CuratorUtils.createNode(client, PATH_ROOT_MASTER, new byte[0]);
            }

            if (!CuratorUtils.isNodeExist(client, PATH_ROOT_TASKS)) {
                CuratorUtils.createNode(client, PATH_ROOT_TASKS, new byte[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            client.close();
            System.exit(-1);
        }
    }

    public void initialize() {
        client = CuratorUtils.createSimple("localhost:" + config.getServerPort());
        leaderSelector = CuratorUtils.createLeaderSelector(client, PATH_ROOT_MASTER, this);
        leaderSelector.autoRequeue();

        taskCache = CuratorUtils.createTreeCache(client, PATH_ROOT_TASKS);
    }
}
