package com.sec.aidog.dao;

import com.sec.aidog.model.DistrictExample;
import com.sec.aidog.pojo.District;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DistrictMapper {
    int countByExample(DistrictExample example);

    int deleteByExample(DistrictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByExample(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    @Select("select * from district where districtcode REGEXP '.{4}00000000' and epidemic = 1")
    List<District> getProvincesAndCities();

    @Select("select district_level,districtcode,district_name,epidemic from district where districtcode REGEXP concat(#{provinceCode0to2}, '.{2}00000000') and districtcode !=concat(#{provinceCode0to2}, '0000000000')")
    public List<District> getCities(String provinceCode0to2);

    @Select("select district_level,districtcode,district_name,epidemic from district where districtcode REGEXP concat(#{cityCode0to4}, '.{2}000000') and districtcode !=concat(#{cityCode0to4}, '00000000')")
    public List<District> getCounties(String cityCode0to4);

    @Select("select district_level,districtcode,district_name,epidemic from district where districtcode REGEXP concat(#{countyCode0to6}, '.{3}000') and districtcode !=concat(#{countyCode0to6}, '000000') ")
    public List<District> getVillages(String countyCode0to6);

    @Select("select district_level,districtcode,district_name ,epidemic from district where districtcode REGEXP concat(#{villageCode0to9}, '.{3}') and districtcode !=concat(#{villageCode0to9}, '000') ")
    public List<District> getHamlets(String villageCode0to9);

    @Select("select districtcode,district_name from district where district_level = 1")
    List<District> getAllCities();

    @Select("select districtcode,district_name from district where district_level = 2")
    public List<District> getAllCounties();

    @Select("select districtcode,district_name from district where district_level = 3")
    public List<District> getAllVillages();

    @Select("select districtcode,district_name from district where district_level = 4")
    public List<District> getAllHamlets();

    @Select("select * from district where district_name=#{provinceName} or echartname=#{provinceName}")
    District getDistrictsByDistrictName(String provinceName);

    @Select("select * from district where (district_name=#{districtName} or echartname=#{districtName}) and districtcode like concat(#{higherLevelDistrictCode},'%')")
    District getCityAndBelowDistrictsByDistrictName(@Param("districtName")String districtName,@Param("higherLevelDistrictCode")String higherLevelDistrictCode);

    @Select("select echartname from district where district_name =#{districtName} or echartname =#{districtName} limit 1")
    String getEchartsareanametempByDistrictName(String districtName);

    @Select("select district_name from district where district_name =#{districtName} or echartname =#{districtName} limit 1")
    String getGovareanametempByDistrictName(String districtName);

    @Select("select * from district where districtcode like concat(#{districtCode},'%')")
    List<District> getDistrictByDistrictcode(String districtCode);

    @Select("select * from district where districtcode REGEXP concat(#{provinceCode0to2}, '.{4}000000') and districtcode !=concat(#{provinceCode0to2}, '0000000000')and epidemic = 1")
    List<District> getCitiesAndCountiesByDistrictcode(String provinceCode0to2);

    @Select("select * from district where districtcode REGEXP concat(#{armyCode0to2}, '.{4}00')  and districtcode !=concat(#{armyCode0to2}, '000000') and epidemic = 1")
    List<District> getDivisionsAndRegimentalByDistrictcode(String armyCode0to2);

}