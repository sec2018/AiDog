package com.sec.aidog.util;

import com.sec.aidog.pojo.PageBean;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class OrderProvider {

    public String combineNeckletAndFeederDogListStr(Map<String, Object> paramsmap) {
        SQL sql = new SQL().SELECT("dogid,dogname,neckletid,apparatusid,managername").FROM("sheepdogs");
        String hamletCode = paramsmap.get("hamletCode").toString();
        if (StringUtils.hasText(hamletCode)) {
            sql.WHERE("districtcode = #{hamletCode}");
        }
        PageBean pageBean = (PageBean)paramsmap.get("pageBean1");
        if (pageBean != null) {
            return sql.toString()+" limit "+ paramsmap.get("pagestart")+","+paramsmap.get("pagesize");
        }
        return sql.toString();
    }
}
