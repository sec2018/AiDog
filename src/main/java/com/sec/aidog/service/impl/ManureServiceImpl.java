package com.sec.aidog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.dao.ManureMapper;
import com.sec.aidog.model.ManureExample;
import com.sec.aidog.pojo.Manure;
import com.sec.aidog.service.ManureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("manureService")
public class ManureServiceImpl implements ManureService{

    @Autowired
    private ManureMapper manureMapper;

    @Override
    public Map<String, Object> getManureList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        ManureExample example = new ManureExample();
        example.createCriteria().andDistrictcodeLike(districtcode);
        List<Manure> manurelist = manureMapper.selectByExample(example);
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", manurelist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }
}
