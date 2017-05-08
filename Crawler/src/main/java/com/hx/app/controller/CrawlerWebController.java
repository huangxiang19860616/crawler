package com.hx.app.controller;

import com.hx.app.model.CrawlerData;
import com.hx.app.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * Created by Administrator on 2017/5/4 0004.
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerWebController {

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping(value = "/test")
    public String test()
    {
        return "test";
    }

    @RequestMapping("/startCrawler")
    public String startCrawler(@RequestBody CrawlerData data)
    {
        crawlerService.startCrawler(data);
        return "ok";
    }

    @RequestMapping("/stopCrawler")
    public String stopCrawler(@RequestParam("tid") String tid)
    {
        crawlerService.stopCrawler(tid);
        return "ok";
    }
}
