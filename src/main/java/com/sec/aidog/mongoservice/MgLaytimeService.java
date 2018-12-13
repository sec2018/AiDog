package com.sec.aidog.mongoservice;

import com.sec.aidog.mgpojo.MgSysLaytime;

import java.util.List;
import java.util.Map;

public interface MgLaytimeService {

    Map<String, Object> getMidLaytimeList(String mid, int startitem,int pagesize);
}
