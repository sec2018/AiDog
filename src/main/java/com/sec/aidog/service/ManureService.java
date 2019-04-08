package com.sec.aidog.service;

import java.util.Map;

public interface ManureService {

    Map<String, Object> getManureList(String districtcode, int startPage, int pageSize);
}
