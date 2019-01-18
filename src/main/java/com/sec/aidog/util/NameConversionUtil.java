package com.sec.aidog.util;

import com.sec.aidog.dao.DistrictsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameConversionUtil {
    @Autowired
    private DistrictsDao districtsDao;
    public String GovToEchartsAreaName(String districtName) {
        String echartsareaname =  districtsDao.getEchartsareanametempByDistrictName(districtName);
        return echartsareaname;
    }

    public String EchartsAreaNameToGov(String districtName) {
        String govareaname = districtsDao.getGovareanametempByDistrictName(districtName) ;
        return govareaname;
    }

}
