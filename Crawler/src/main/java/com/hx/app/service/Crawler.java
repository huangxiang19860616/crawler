package com.hx.app.service;


import com.hx.app.model.CrawlerData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
@Service
@Scope("prototype")
public interface Crawler {

     void crawl(CrawlerData data);
}
