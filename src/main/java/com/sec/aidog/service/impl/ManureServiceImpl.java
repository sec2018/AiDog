package com.sec.aidog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.DogownerMapper;
import com.sec.aidog.dao.ManureMapper;
import com.sec.aidog.model.ManureExample;
import com.sec.aidog.pojo.Dog;
import com.sec.aidog.pojo.Manure;
import com.sec.aidog.pojo.ManureView;
import com.sec.aidog.service.ManureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("manureService")
public class ManureServiceImpl implements ManureService{

    @Autowired
    private ManureMapper manureMapper;

    @Autowired
    private DogownerMapper dogownerMapper;

    @Autowired
    private DogMapper dogMapper;

    @Override
    public Map<String, Object> getManureList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        ManureExample example = new ManureExample();
        example.createCriteria().andDistrictcodeLike(districtcode);
        List<Manure> manurelist = manureMapper.selectByExample(example);
        List<ManureView> manureviewlist = new ArrayList<>();
        ManureView manureView = null;
        for(Manure manure: manurelist){
            manureView = new ManureView();
            manureView.setId(manure.getId());
            manureView.setCollectionDate(manure.getCollectionDate());
            manureView.setCollectionPerson(manure.getCollectionPerson());
            manureView.setDistrictcode(manure.getDistrictcode());
            manureView.setDogId(manure.getDogId());
            manureView.setDogmanureCode(manure.getDogmanureCode());
            manureView.setDogownerId(manure.getDogownerId());
            manureView.setTestingDate(manure.getTestingDate());
            manureView.setTestingMethod(manure.getTestingMethod());
            manureView.setTestingPerson(manure.getTestingPerson());
            manureView.setTestingResult(manure.getTestingResult());
            String ownername = dogownerMapper.selectByPrimaryKey(manure.getDogownerId()).getOwnerName();
            String dogname = dogMapper.selectByPrimaryKey(manure.getDogId()).getDogName();
            String govcode = dogMapper.selectByPrimaryKey(manure.getDogId()).getDogGovcode();
            manureView.setOwnerName(ownername);
            manureView.setDogName(dogname);
            manureView.setDogGovcode(govcode);
            manureviewlist.add(manureView);
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", manureviewlist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }

    @Override
    public boolean addManure(String dogmanure_code, Integer dogowner_id, Integer dog_id, String collection_person, String districtcode,Date date) {
        Manure manure = new Manure();
        manure.setDogmanureCode(dogmanure_code);
        manure.setDogownerId(dogowner_id);
        manure.setDogId(dog_id);
        manure.setCollectionDate(date);
        manure.setCollectionPerson(collection_person);
        manure.setDistrictcode(districtcode);
        boolean flag = manureMapper.insert(manure)==1?true:false;
        return flag;
    }
}
