package com.sec.aidog.service;

import com.sec.aidog.pojo.Necklet;

import java.util.List;

public interface NeckletService {

    boolean batchNecRegister(List<Necklet> neclist);
}
