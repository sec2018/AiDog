package com.sec.aidog.service.impl;

import com.sec.aidog.dao.NeckletMapper;
import com.sec.aidog.pojo.Necklet;
import com.sec.aidog.service.NeckletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeckletServiceImpl implements NeckletService{

    @Autowired
    private NeckletMapper neckletMapper;

    @Override
    public boolean batchNecRegister(List<Necklet> neclist) {
        boolean isSuccess = neckletMapper.insertBatchNecRegister(neclist)>0?true:false;
        return isSuccess;
    }
}
