package com.sec.aidog.mongoservice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.mgpojo.MgSysLaytime;
import com.sec.aidog.mongoservice.MgLaytimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MgLaytimeServiceImpl implements MgLaytimeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Map<String, Object> getMidLaytimeList(String mid, int startitem,int pagesize) {
        Page page = PageHelper.startPage(startitem, pagesize);
        Query query = new Query(Criteria.where("mid").is(mid)).limit(pagesize).skip((startitem-1)*pagesize);
        List<MgSysLaytime> mgSysLaytimeList = mongoTemplate.find(query,MgSysLaytime.class);
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", mgSysLaytimeList);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }
}
