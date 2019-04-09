package com.sec.aidog.service.impl;

import com.sec.aidog.dao.DogMapper;
import com.sec.aidog.dao.LastnecdosingMapper;
import com.sec.aidog.dao.NecconfigMapper;
import com.sec.aidog.dao.NeckletMapper;
import com.sec.aidog.pojo.Dog;
import com.sec.aidog.pojo.Lastnecdosing;
import com.sec.aidog.pojo.Necconfig;
import com.sec.aidog.pojo.Necklet;
import com.sec.aidog.service.NeckletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
