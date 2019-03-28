package com.sec.aidog.service;

import com.sec.aidog.pojo.Necklet;

import java.util.List;

public interface NeckletService {

    boolean batchNecRegister(List<Necklet> neclist);

    boolean singleNecRegister(Necklet necklet);

    boolean bindNecklet(String necid, Integer dogid);
}