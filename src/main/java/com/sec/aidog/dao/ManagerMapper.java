package com.sec.aidog.dao;

import com.sec.aidog.model.ManagerExample;
import com.sec.aidog.pojo.Manager;

import java.util.List;

import com.sec.aidog.pojo.Managers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper {
    int countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(Integer managerId);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(Integer managerId);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    @Select("select * from manager where username = #{name}")
    Manager findUserByName(String name);

    @Select("select privilegelevel from manager")
    public List<Integer> getManagerLevel();

    @Select("select privilegelevel from managers where province=#{provinceName}")
    public List<Integer> getProvinceManagerLevelAndBelowByDistrictName(String provinceName);
}