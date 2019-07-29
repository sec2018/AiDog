package com.sec.aidog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.dao.*;
import com.sec.aidog.model.*;
import com.sec.aidog.pojo.*;
import com.sec.aidog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service("dogService")
public class DogServiceImpl implements DogService{

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private LastnecdosingMapper lastnecdosingMapper;

    @Autowired
    private LastappdosingMapper lastappdosingMapper;

    @Autowired
    private DogownerMapper dogownerMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private AnalyzeillMapper analyzeillMapper;

    @Autowired
    private AnaallillMapper anaallillMapper;

    @Autowired
    private ChildcheckMapper childcheckMapper;

    @Autowired
    private ChildillMapper childillMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Dog addDog(String username, String dogname, String dogsex, String dogbelonghamlet, String ownerhamletcode, int dogownerid,
                         String dogweight, String dogcolor, int dogage,String govcode) throws Exception {
        String result = "添加失败";
        Dog sheepdog = null;
        DogExample example = new DogExample();
        example.createCriteria().andDogownerIdEqualTo(dogownerid).andDogGovcodeEqualTo(govcode);
        List<Dog> dogList = dogMapper.selectByExample(example);
        if (dogList!=null && dogList.size()==1) {
            return dogList.get(0);
        }else if(dogList.size()>1){
            return null;
        }
        sheepdog = new Dog();
        sheepdog.setDogName(dogname);
        sheepdog.setNecId("-1");
        sheepdog.setAppId("-1");
        sheepdog.setUsername(username);
        sheepdog.setManagerName(managerMapper.findUserByName(username).getManagerName());
        sheepdog.setDogownerId(dogownerid);
        sheepdog.setDogWeight(dogweight);
        sheepdog.setDogColor(dogcolor);
        sheepdog.setDogAge(dogage);
        sheepdog.setDogInfo("健康");
        sheepdog.setDogStatus("1");
        sheepdog.setDogRegistertime(new Date());
        sheepdog.setDogSex(dogsex);
        sheepdog.setDistrictcode(ownerhamletcode);
        sheepdog.setDogGovcode(govcode);
        boolean flag = dogMapper.insert(sheepdog)==0?false:true;
        if(flag){
            return sheepdog;
        }
        return sheepdog;
    }

    @Override
    public Map<String, Object> getDogList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<DogView> doglist = dogMapper.getDogListByDistrictcode(districtcode);
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", doglist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getDogInfo(Integer dogid) {
        Dog dog  = dogMapper.selectByPrimaryKey(dogid);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("dog",dog);
        String admintel = managerMapper.findUserByName(dog.getUsername()).getManagerTel();
        map.put("admintel",admintel);
        String hamlet = districtMapper.selectByDistrictCode(dog.getDistrictcode()).getDistrictName();
        map.put("hamlet",hamlet);
        String nec_id = dog.getNecId();
        if(!nec_id.equals("-1")){
            Lastnecdosing lastnecdosing = lastnecdosingMapper.getLastnecdosing(nec_id);
            map.put("nec",lastnecdosing);
        }
        String app_id = dog.getAppId();
        if(!app_id.equals("-1")){
            Lastappdosing lastappdosing = lastappdosingMapper.getLastappdosing(app_id);
            map.put("app",lastappdosing);
        }
        Integer ownerid = dog.getDogownerId();
        Dogowner dogowner = dogownerMapper.selectByPrimaryKey(ownerid);
        map.put("owner",dogowner);
        return map;
    }

    @Override
    public String modifyDog(Integer dogid, String dogname, String dogsex, String dogweight, String dogcolor, Integer dogage) {
        Dog dog = dogMapper.selectByPrimaryKey(dogid);
        dog.setDogName(dogname);
        dog.setDogSex(dogsex);
        dog.setDogWeight(dogweight);
        dog.setDogColor(dogcolor);
        dog.setDogAge(dogage);
        String res = dogMapper.updateByPrimaryKey(dog)>0?"修改成功！":"修改失败";
        return res;
    }

    @Override
    public Map<String, Object> getDogStaList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
//        List<DogView> doglist = dogMapper.getDogListByDistrictcode(districtcode);
        List<DogSta> dogstalist = new ArrayList<>();
        DogSta dogSta = null;
        int count = 1;
        switch (districtcode.length()){
            case 1:
                //国家级管理员
                List<District> districtList = districtMapper.getProvinces();
                for(int i=0;i<districtList.size();i++){
                    dogSta = new DogSta();
                    dogSta.setCountnum(count);
                    count++;
                    dogSta.setDistrictname(districtList.get(i).getDistrictName());
                    String provinceCode0to2 = districtList.get(i).getDistrictcode().substring(0,2);
                    //获得该省所有的狗
                    List<Dog> sdlist = dogMapper.getIndexInforByDistrictcode(provinceCode0to2);
                    dogSta.setDognum(sdlist.size());
                    //佩戴项圈牧犬数量和喂食器数量
                    int neckdognumtotal = 0;
                    int feedernumtotal = 0;
                    for(Dog each:sdlist){
                        //"-1"表示未佩戴项圈
                        if(!each.getNecId().equals("-1")) {
                            neckdognumtotal++;
                        }
                        //"-1"表示无喂食器
                        if(!each.getAppId().equals("-1")) {
                            feedernumtotal++;
                        }
                    }
                    dogSta.setNecdognum(neckdognumtotal);
                    dogSta.setAppdognum(feedernumtotal);
                    dogSta.setManagedognum(sdlist.size());
                    if(sdlist.size() == 0){
                        dogSta.setNecdognumper("0");
                        dogSta.setAppdognumper("0");
                    }else{
                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
//                        String s = df.format(num);
                        dogSta.setNecdognumper(df.format((neckdognumtotal/(sdlist.size()*1.0000))*100)+"%");
                        dogSta.setAppdognumper(df.format((feedernumtotal/(sdlist.size()*1.0000))*100)+"%");
                    }
                    dogstalist.add(dogSta);
                }
                break;
             case 2:
                 //省级管理员
                 List<District> districtList2 = districtMapper.getCities(districtcode);
                 for(int i=0;i<districtList2.size();i++){
                     dogSta = new DogSta();
                     dogSta.setCountnum(count);
                     count++;
                     dogSta.setDistrictname(districtList2.get(i).getDistrictName());
                     String cityCode0to4 = districtList2.get(i).getDistrictcode().substring(0,4);
                     //获得该省所有的狗
                     List<Dog> sdlist = dogMapper.getIndexInforByDistrictcode(cityCode0to4);
                     dogSta.setDognum(sdlist.size());
                     //佩戴项圈牧犬数量和喂食器数量
                     int neckdognumtotal = 0;
                     int feedernumtotal = 0;
                     for(Dog each:sdlist){
                         //"-1"表示未佩戴项圈
                         if(!each.getNecId().equals("-1")) {
                             neckdognumtotal++;
                         }
                         //"-1"表示无喂食器
                         if(!each.getAppId().equals("-1")) {
                             feedernumtotal++;
                         }
                     }
                     dogSta.setNecdognum(neckdognumtotal);
                     dogSta.setAppdognum(feedernumtotal);
                     dogSta.setManagedognum(sdlist.size());
                     if(sdlist.size() == 0){
                         dogSta.setNecdognumper("0");
                         dogSta.setAppdognumper("0");
                     }else{
//                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
//                        String s = df.format(num);
                         dogSta.setNecdognumper((neckdognumtotal/(sdlist.size()*1.0000))*100+"%");
                         dogSta.setAppdognumper((feedernumtotal/(sdlist.size()*1.0000))*100+"%");
                     }
                     dogstalist.add(dogSta);
                 }
                 break;
            case 4:
                //市级管理员
                List<District> districtList3 = districtMapper.getCounties(districtcode);
                for(int i=0;i<districtList3.size();i++){
                    dogSta = new DogSta();
                    dogSta.setCountnum(count);
                    count++;
                    dogSta.setDistrictname(districtList3.get(i).getDistrictName());
                    String countyCode0to6 = districtList3.get(i).getDistrictcode().substring(0,6);
                    //获得该市所有的狗
                    List<Dog> sdlist = dogMapper.getIndexInforByDistrictcode(countyCode0to6);
                    dogSta.setDognum(sdlist.size());
                    //佩戴项圈牧犬数量和喂食器数量
                    int neckdognumtotal = 0;
                    int feedernumtotal = 0;
                    for(Dog each:sdlist){
                        //"-1"表示未佩戴项圈
                        if(!each.getNecId().equals("-1")) {
                            neckdognumtotal++;
                        }
                        //"-1"表示无喂食器
                        if(!each.getAppId().equals("-1")) {
                            feedernumtotal++;
                        }
                    }
                    dogSta.setNecdognum(neckdognumtotal);
                    dogSta.setAppdognum(feedernumtotal);
                    dogSta.setManagedognum(sdlist.size());
                    if(sdlist.size() == 0){
                        dogSta.setNecdognumper("0");
                        dogSta.setAppdognumper("0");
                    }else{
//                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
//                        String s = df.format(num);
                        dogSta.setNecdognumper((neckdognumtotal/(sdlist.size()*1.0000))*100+"%");
                        dogSta.setAppdognumper((feedernumtotal/(sdlist.size()*1.0000))*100+"%");
                    }
                    dogstalist.add(dogSta);
                }
                break;
            case 6:
                //县级管理员
                List<District> districtList4 = districtMapper.getVillages(districtcode);
                for(int i=0;i<districtList4.size();i++){
                    dogSta = new DogSta();
                    dogSta.setCountnum(count);
                    count++;
                    dogSta.setDistrictname(districtList4.get(i).getDistrictName());
                    String villageCode0to9 = districtList4.get(i).getDistrictcode().substring(0,9);
                    //获得该县所有的狗
                    List<Dog> sdlist = dogMapper.getIndexInforByDistrictcode(villageCode0to9);
                    dogSta.setDognum(sdlist.size());
                    //佩戴项圈牧犬数量和喂食器数量
                    int neckdognumtotal = 0;
                    int feedernumtotal = 0;
                    for(Dog each:sdlist){
                        //"-1"表示未佩戴项圈
                        if(!each.getNecId().equals("-1")) {
                            neckdognumtotal++;
                        }
                        //"-1"表示无喂食器
                        if(!each.getAppId().equals("-1")) {
                            feedernumtotal++;
                        }
                    }
                    dogSta.setNecdognum(neckdognumtotal);
                    dogSta.setAppdognum(feedernumtotal);
                    dogSta.setManagedognum(sdlist.size());
                    if(sdlist.size() == 0){
                        dogSta.setNecdognumper("0");
                        dogSta.setAppdognumper("0");
                    }else{
//                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
//                        String s = df.format(num);
                        dogSta.setNecdognumper((neckdognumtotal/(sdlist.size()*1.0000))*100+"%");
                        dogSta.setAppdognumper((feedernumtotal/(sdlist.size()*1.0000))*100+"%");
                    }
                    dogstalist.add(dogSta);
                }
                break;
            case 9:
                //乡级管理员
                List<District> districtList5 = districtMapper.getHamlets(districtcode);
                for(int i=0;i<districtList5.size();i++){
                    dogSta = new DogSta();
                    dogSta.setCountnum(count);
                    count++;
                    dogSta.setDistrictname(districtList5.get(i).getDistrictName());
                    String hamletCode = districtList5.get(i).getDistrictcode();
                    //获得该乡所有的狗
                    List<Dog> sdlist = dogMapper.getIndexInforByDistrictcode(hamletCode);
                    dogSta.setDognum(sdlist.size());
                    //佩戴项圈牧犬数量和喂食器数量
                    int neckdognumtotal = 0;
                    int feedernumtotal = 0;
                    for(Dog each:sdlist){
                        //"-1"表示未佩戴项圈
                        if(!each.getNecId().equals("-1")) {
                            neckdognumtotal++;
                        }
                        //"-1"表示无喂食器
                        if(!each.getAppId().equals("-1")) {
                            feedernumtotal++;
                        }
                    }
                    dogSta.setNecdognum(neckdognumtotal);
                    dogSta.setAppdognum(feedernumtotal);
                    dogSta.setManagedognum(sdlist.size());
                    if(sdlist.size() == 0){
                        dogSta.setNecdognumper("0");
                        dogSta.setAppdognumper("0");
                    }else{
//                        DecimalFormat df = new DecimalFormat("0.0000");//保留4位小数
//                        String s = df.format(num);
                        dogSta.setNecdognumper((neckdognumtotal/(sdlist.size()*1.0000))*100+"%");
                        dogSta.setAppdognumper((feedernumtotal/(sdlist.size()*1.0000))*100+"%");
                    }
                    dogstalist.add(dogSta);
                }
                break;
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", dogstalist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }


    @Override
    public Map<String, Object> getIllStaList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<Analyzeill> illstalist = new ArrayList<>();
        Analyzeill illsta = null;
        int count = 1;
        switch (districtcode.length()){
            case 1:
                //国家级管理员
                AnalyzeillExample example1 = new AnalyzeillExample();
                example1.createCriteria().andDistrictLevelEqualTo(0);
                illstalist = analyzeillMapper.selectByExample(example1);
                break;
            case 2:
                //省级管理员
                AnalyzeillExample example2 = new AnalyzeillExample();
                example2.createCriteria().andDistrictLevelEqualTo(1).andDistrictcodeLike(districtcode.substring(0,2)+"%");
                illstalist = analyzeillMapper.selectByExample(example2);
                break;
            case 4:
                //市级管理员
                AnalyzeillExample example3 = new AnalyzeillExample();
                example3.createCriteria().andDistrictLevelEqualTo(2).andDistrictcodeLike(districtcode.substring(0,4)+"%");
                illstalist = analyzeillMapper.selectByExample(example3);
                break;
            case 6:
                //县级管理员
                AnalyzeillExample example4 = new AnalyzeillExample();
                example4.createCriteria().andDistrictLevelEqualTo(3).andDistrictcodeLike(districtcode.substring(0,6)+"%");
                illstalist = analyzeillMapper.selectByExample(example4);
                break;
            case 9:
                //乡级管理员
                AnalyzeillExample example5 = new AnalyzeillExample();
                example5.createCriteria().andDistrictLevelEqualTo(4).andDistrictcodeLike(districtcode.substring(0,9)+"%");
                illstalist = analyzeillMapper.selectByExample(example5);
                break;
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", illstalist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getBillStaList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<Anaallill> illstalist = new ArrayList<>();
        Analyzeill illsta = null;
        int count = 1;
        switch (districtcode.length()){
            case 1:
                //国家级管理员
                AnaallillExample example1 = new AnaallillExample();
                example1.createCriteria().andDistrictLevelEqualTo(0);
                illstalist = anaallillMapper.selectByExample(example1);
                break;
            case 2:
                //省级管理员
                AnaallillExample example2 = new AnaallillExample();
                example2.createCriteria().andDistrictLevelEqualTo(1).andDistrictcodeLike(districtcode.substring(0,2)+"%");
                illstalist = anaallillMapper.selectByExample(example2);
                break;
            case 4:
                //市级管理员
                AnaallillExample example3 = new AnaallillExample();
                example3.createCriteria().andDistrictLevelEqualTo(2).andDistrictcodeLike(districtcode.substring(0,4)+"%");
                illstalist = anaallillMapper.selectByExample(example3);
                break;
            case 6:
                //县级管理员
                AnaallillExample example4 = new AnaallillExample();
                example4.createCriteria().andDistrictLevelEqualTo(3).andDistrictcodeLike(districtcode.substring(0,6)+"%");
                illstalist = anaallillMapper.selectByExample(example4);
                break;
            case 9:
                //乡级管理员
                AnaallillExample example5 = new AnaallillExample();
                example5.createCriteria().andDistrictLevelEqualTo(4).andDistrictcodeLike(districtcode.substring(0,9)+"%");
                illstalist = anaallillMapper.selectByExample(example5);
                break;
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", illstalist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> getChildCheckList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<Childcheck> childchecklist = new ArrayList<>();
        Childcheck childcheck = null;
        int count = 1;
        switch (districtcode.length()){
            case 1:
                //国家级管理员
                ChildcheckExample example1 = new ChildcheckExample();
                childchecklist = childcheckMapper.selectByExample(example1);
                break;
            case 2:
                //省级管理员
                ChildcheckExample example2 = new ChildcheckExample();
                example2.createCriteria().andNumLike(districtcode.substring(0,2)+"%");
                childchecklist = childcheckMapper.selectByExample(example2);
                break;
            case 4:
                //市级管理员
                ChildcheckExample example3 = new ChildcheckExample();
                example3.createCriteria().andNumLike(districtcode.substring(0,4)+"%");
                childchecklist = childcheckMapper.selectByExample(example3);
                break;
            case 6:
                //县级管理员
                ChildcheckExample example4 = new ChildcheckExample();
                example4.createCriteria().andNumLike(districtcode.substring(0,6)+"%");
                childchecklist = childcheckMapper.selectByExample(example4);
                break;
            case 9:
                //乡级管理员
                ChildcheckExample example5 = new ChildcheckExample();
                example5.createCriteria().andNumLike(districtcode.substring(0,9)+"%");
                childchecklist = childcheckMapper.selectByExample(example5);
                break;
            case 12:
                //乡级管理员
                ChildcheckExample example6 = new ChildcheckExample();
                example6.createCriteria().andNumEqualTo(districtcode);
                childchecklist = childcheckMapper.selectByExample(example6);
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", childchecklist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }


    @Override
    public Map<String, Object> getChildIllList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<Childill> childilllist = new ArrayList<>();
        Childill childill = null;
        int count = 1;
        switch (districtcode.length()){
            case 1:
                //国家级管理员
                ChildillExample example1 = new ChildillExample();
                childilllist = childillMapper.selectByExample(example1);
                break;
            case 2:
                //省级管理员
                ChildillExample example2 = new ChildillExample();
                example2.createCriteria().andNumLike(districtcode.substring(0,2)+"%");
                childilllist = childillMapper.selectByExample(example2);
                break;
            case 4:
                //市级管理员
                ChildillExample example3 = new ChildillExample();
                example3.createCriteria().andNumLike(districtcode.substring(0,4)+"%");
                childilllist = childillMapper.selectByExample(example3);
                break;
            case 6:
                //县级管理员
                ChildillExample example4 = new ChildillExample();
                example4.createCriteria().andNumLike(districtcode.substring(0,6)+"%");
                childilllist = childillMapper.selectByExample(example4);
                break;
            case 9:
                //乡级管理员
                ChildillExample example5 = new ChildillExample();
                example5.createCriteria().andNumLike(districtcode.substring(0,9)+"%");
                childilllist = childillMapper.selectByExample(example5);
                break;
            case 12:
                //乡级管理员
                ChildillExample example6 = new ChildillExample();
                example6.createCriteria().andNumEqualTo(districtcode);
                childilllist = childillMapper.selectByExample(example6);
                break;
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", childilllist);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }
}
