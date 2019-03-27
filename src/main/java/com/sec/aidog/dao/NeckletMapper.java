package com.sec.aidog.dao;

import com.sec.aidog.model.NeckletExample;
import com.sec.aidog.pojo.Necklet;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NeckletMapper {
    int countByExample(NeckletExample example);

    int deleteByExample(NeckletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Necklet record);

    int insertSelective(Necklet record);

    List<Necklet> selectByExample(NeckletExample example);

    Necklet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Necklet record, @Param("example") NeckletExample example);

    int updateByExample(@Param("record") Necklet record, @Param("example") NeckletExample example);

    int updateByPrimaryKeySelective(Necklet record);

    int updateByPrimaryKey(Necklet record);

    int insertBatchNecRegister(List<Necklet> neckletList);

    @Select("select nec_id FROM necklet where bind_time is null")
    List<String> getUnuseNecList();
}