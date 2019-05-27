package com.sec.aidog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.LastnecdosingMapper;
import com.sec.aidog.dao.NecconfigMapper;
import com.sec.aidog.dao.NeckletMapper;
import com.sec.aidog.pojo.*;
import com.sec.aidog.service.NeckletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service
public class NeckletServiceImpl implements NeckletService{

    @Autowired
    private NeckletMapper neckletMapper;

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private NecconfigMapper necconfigMapper;

    @Autowired
    private LastnecdosingMapper lastnecdosingMapper;

    @Override
    public boolean batchNecRegister(List<Necklet> neclist) {
        boolean isSuccess = neckletMapper.insertBatchNecRegister(neclist)>0?true:false;
        return isSuccess;
    }

    @Override
    public boolean singleNecRegister(Necklet necklet) {
        boolean isSuccess = neckletMapper.insert(necklet)>0?true:false;
        return isSuccess;
    }


    @Override
    @Transactional
    public boolean bindNecklet(String necid, Integer dogid) {
        try{
            Dog dog = dogMapper.selectByPrimaryKey(dogid);
            Necklet necklet = neckletMapper.selectByNecId(necid);
            if(dog != null && necklet!=null){
                dog.setNecId(necid);
                boolean flag1 = dogMapper.updateByPrimaryKey(dog)!=0?true:false;
                necklet.setBindTime(new Date());
                necklet.setDistrictcode(dog.getDistrictcode());
                boolean flag2 = neckletMapper.updateByPrimaryKey(necklet)!=0?true:false;
                Necconfig necconfig = new Necconfig();
                necconfig.setNecId(necid);
                necconfig.setUpdatetime(new Date());
                boolean flag3 = necconfigMapper.insert(necconfig)!=0?true:false;
                Lastnecdosing lastnecdosing = new Lastnecdosing();
                lastnecdosing.setNecId(necid);
                lastnecdosing.setRealtime(new Date());
                lastnecdosing.setDistrictcode(dog.getDistrictcode());
                boolean flag4 = lastnecdosingMapper.insert(lastnecdosing)!=0?true:false;
                if(flag1 && flag2 && flag3 && flag4){
                    return true;
                }else{
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return false;
                }
            }else{
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }


    @Override
    public Map<String, Object> getHamletOwnerNecList(String hamletcode,Integer ownerid) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Dog> neckletList = dogMapper.getUseNecListByHamletcode(hamletcode,ownerid);
        int i=0;
        for (Dog item:neckletList)
        {
            Map<String, Object> maptemp = new HashMap<String, Object>();
            maptemp.put("nec", item.getNecId());
            maptemp.put("id", item.getDogId());
            map.put(""+i, maptemp);
            i++;
        }
        return map;
    }

    @Override
    public Map<String, Object> getNeckletList(String districtcode, int startPage, int pageSize) {
        Page page = PageHelper.startPage(startPage, pageSize);
        List<NeckletView> neckletViewList = new ArrayList<>();
        List<SysLaytime> SysLaytimelist = neckletMapper.selectViewLayTime(districtcode);
        List<SysDeviceconf> sysDeviceconflist = neckletMapper.selectViewDeviceconf(districtcode);
        List<SysLayconfig> sysLayconfiglist = neckletMapper.selectViewLayconfig(districtcode);

        NeckletView neckletView = null;
        for(int i=0;i<SysLaytimelist.size();i++){
            neckletView = new NeckletView();
            neckletView.setNecId(SysLaytimelist.get(i).getMid());
            neckletView.setPower(SysLaytimelist.get(i).getVoltage()==null?"未反馈":SysLaytimelist.get(i).getVoltage()+"");
            neckletView.setTemperature(SysLaytimelist.get(i).getTemperature()==null?"未反馈":SysLaytimelist.get(i).getTemperature()+"");
            neckletView.setPillcode("PL2306");
            String devicestatus = changestatus(sysDeviceconflist.get(i).getStatus());   //投药状态加轮询状态
            String dosingstatus = devicestatus.substring(0,12);
            neckletView.setDosingstatus(dosingstatus.substring(0,4)+"-"+dosingstatus.substring(4,8)+"-"+dosingstatus.substring(8,12));
            neckletView.setConfstatus("正常");
            if(sysDeviceconflist.get(i).getUimodifyflag().equals(Byte.valueOf("1")) && sysDeviceconflist.get(i).getHardmodifyflag().equals(Byte.valueOf("0"))){
                neckletView.setConfstatus("硬件接收信息中");
            }else if(sysDeviceconflist.get(i).getUimodifyflag().equals(Byte.valueOf("0")) && sysDeviceconflist.get(i).getHardmodifyflag().equals(Byte.valueOf("0"))){
                neckletView.setConfstatus("硬件已完成配置");
            }
            int countnum = 12;
            for(int j = 11;j >=0;j--){
                if(dosingstatus.charAt(j) == '0'){
                    countnum--;
                }else{
                    break;
                }
            }
            switch(countnum){
                case 12:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getTwelve());
                    neckletView.setNextDosingTime(null);
                    neckletView.setLeftnum(0);
                    break;
                case 11:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getEleven());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getTwelve());
                    neckletView.setLeftnum(1);
                    break;
                case 10:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getTen());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getEleven());
                    neckletView.setLeftnum(2);
                    break;
                case 9:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getNine());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getTen());
                    neckletView.setLeftnum(3);
                    break;
                case 8:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getEight());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getNine());
                    neckletView.setLeftnum(4);
                    break;
                case 7:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getSeven());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getEight());
                    neckletView.setLeftnum(5);
                    break;
                case 6:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getSix());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getSeven());
                    neckletView.setLeftnum(6);
                    break;
                case 5:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getFive());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getSix());
                    neckletView.setLeftnum(7);
                    break;
                case 4:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getFour());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getFive());
                    neckletView.setLeftnum(8);
                    break;
                case 3:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getThree());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getFour());
                    neckletView.setLeftnum(9);
                    break;
                case 2:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getTwo());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getThree());
                    neckletView.setLeftnum(10);
                    break;
                case 1:
                    neckletView.setFirstDosingTime(sysLayconfiglist.get(i).getOne());
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getTwo());
                    neckletView.setLeftnum(11);
                    break;
                case 0:
                    neckletView.setFirstDosingTime(null);
                    neckletView.setNextDosingTime(sysLayconfiglist.get(i).getOne());
                    neckletView.setLeftnum(12);
                    break;
            }
            neckletViewList.add(neckletView);
        }
        Map<String, Object> map = new HashMap<String,Object>();
        //每页信息
        map.put("data", neckletViewList);
        //管理员总数
        map.put("totalNum", page.getTotal());
        return map;
    }


    public String changestatus(Integer int_status){
        String status = Integer.toBinaryString(int_status);
        byte[] bytes = status.getBytes();
        //倒置
        for (int l = 0, h = status.length() - 1; l < h; l++, h--) {
            // Swap values at l and h
            byte temp = bytes[l];
            bytes[l] = bytes[h];
            bytes[h] = temp;
        }
        status = new String(bytes);
        while(status.length()<16){
            status = status+"0";
        }
//        status = status.substring(0,4)+"-"+status.substring(4,8)+"-"+status.substring(8,12)+"-"+status.substring(12,16);
//        System.out.println(status);
        return status;
    }

}
