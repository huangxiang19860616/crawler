package com.hx.app.service.impl;

import com.hx.app.model.CrawlerData;
import com.hx.app.service.Crawler;
import com.hx.app.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class CrawlerServiceImpl implements CrawlerService {

    private static Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl.class);

    @Autowired
    private Crawler crawler;

    @Override
    public void startCrawler(CrawlerData data) {
         crawler.crawl(data);
    }

    @Override
    public void stopCrawler(String tid) {

    }
}
