package com.sec.aidog.dao;

import java.util.List;

import com.sec.aidog.model.DogExample;
import com.sec.aidog.pojo.Dog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DogMapper {
    int countByExample(DogExample example);

    int deleteByExample(DogExample example);

    int deleteByPrimaryKey(Integer dogId);

    int insert(Dog record);

    int insertSelective(Dog record);

    List<Dog> selectByExample(DogExample example);

    Dog selectByPrimaryKey(Integer dogId);

    int updateByExampleSelective(@Param("record") Dog record, @Param("example") DogExample example);

    int updateByExample(@Param("record") Dog record, @Param("example") DogExample example);

    int updateByPrimaryKeySelective(Dog record);

    int updateByPrimaryKey(Dog record);

    @Select("select nec_id from dog where districtcode like concat(#{districtCode},'%')")
    List<String> getAllNeckletIdByDistrictcode(String districtCode);
}