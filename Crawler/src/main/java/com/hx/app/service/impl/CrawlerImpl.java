package com.hx.app.service.impl;

import com.hx.app.entity.TaskConfigEntity;
import com.hx.app.entity.TaskEntity;
import com.hx.app.model.CrawlerData;
import com.hx.app.service.Crawler;
import com.hx.app.utils.ClassInstance;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5 0005.
 */

public class CrawlerImpl implements Crawler{

    public CrawlerImpl() {

    }

    @Override
    public void crawl(CrawlerData crawlerData) {

        String taskId = crawlerData.getTaskData().getTaskId();

        //获取任务配置
        List<TaskConfigEntity> taskConfigList = getTaskConfigEntities(taskId);

        String[] urlArray = getUrls(taskConfigList);

        TaskEntity taskEntity = getTaskEntity(taskId);
        PageProcessor processor;
        Downloader downloader;
        int threadNum;
        Scheduler scheduler;
        List<Pipeline> pipelineList = new ArrayList<>();
        try {
            processor = new ClassInstance<PageProcessor>().getInstance(taskEntity.getProcessor());
            downloader = new ClassInstance<Downloader>().getInstance(taskEntity.getDownloader());
            threadNum = taskEntity.getThreadNum();
            scheduler = new ClassInstance<Scheduler>().getInstance(taskEntity.getScheduler());
            for(String pipeline : taskEntity.getPipline())
            {
                pipelineList.add(new ClassInstance<Pipeline>().getInstance(pipeline));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        }

        Spider spider = Spider.create(processor).setDownloader(downloader).setUUID(taskId).thread(threadNum)
                .setScheduler(scheduler).addUrl(urlArray);

        for (Pipeline pipeline : pipelineList)
        {
            spider.addPipeline(pipeline);
        }
        spider.run();
    }

    private TaskEntity getTaskEntity(String taskId) {
        return null;
    }

    private String[] getUrls(List<TaskConfigEntity> taskConfigList)
    {
        return null;
    }

    public List<TaskConfigEntity> getTaskConfigEntities(String taskId) {
        return null;
    }
}
