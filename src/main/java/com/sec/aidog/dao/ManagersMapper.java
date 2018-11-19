package com.sec.aidog.dao;

import com.sec.aidog.model.ManagersExample;
import com.sec.aidog.model.ManagersKey;
import com.sec.aidog.pojo.Managers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManagersMapper {
    int countByExample(ManagersExample example);

    int deleteByExample(ManagersExample example);

    int deleteByPrimaryKey(ManagersKey key);

    int insert(Managers record);

    int insertSelective(Managers record);

    List<Managers> selectByExample(ManagersExample example);

    Managers selectByPrimaryKey(ManagersKey key);

    int updateByExampleSelective(@Param("record") Managers record, @Param("example") ManagersExample example);

    int updateByExample(@Param("record") Managers record, @Param("example") ManagersExample example);

    int updateByPrimaryKeySelective(Managers record);

    int updateByPrimaryKey(Managers record);
}