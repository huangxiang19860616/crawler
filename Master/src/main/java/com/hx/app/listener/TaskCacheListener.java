package com.hx.app.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class TaskCacheListener implements TreeCacheListener {
    @Override
    public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
        System.out.println("事件类型：" + event.getType() + " | 路径：" + event.getData().getPath());

        switch(event.getType())
        {
            case NODE_ADDED:
                break;
            case NODE_UPDATED:
                break;
            case NODE_REMOVED:
                break;
            default:
        }
    }
}
