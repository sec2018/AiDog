package com.sec.aidog.mongoservice.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.DistinctIterable;
import com.sec.aidog.mgpojo.MgSysLaytime;
import com.sec.aidog.mongoservice.MgLaytimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MgLaytimeServiceImpl implements MgLaytimeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MgSysLaytime> getMidLaytimeList(String mid, int startitem,int pagesize) {
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        query.addCriteria(Criteria.where("mid").is(mid)).limit(pagesize).skip((startitem-1)*pagesize);
        List<MgSysLaytime> mgSysLaytimeList = mongoTemplate.find(query,MgSysLaytime.class);
        return mgSysLaytimeList;
    }

    //select * from sys_laytime as a where not exists (select 1 from sys_laytime where mid = a.mid and updatetime>a.updatetime)
    @Override
    public List<MgSysLaytime> getAllDevicePosition() {
        List<String> mids = new ArrayList<>();
        DBObject qu = new BasicDBObject()
        DistinctIterable<MgSysLaytime> mgmid =  mongoTemplate.getCollection("sys_laytime").distinct("mid",qu);

        Query query = new Query();


        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));

        return null;
    }
}
