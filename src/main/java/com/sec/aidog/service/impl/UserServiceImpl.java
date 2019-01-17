package com.sec.aidog.service.impl;

import com.sec.aidog.dao.*;
import com.sec.aidog.pojo.Districts;
import com.sec.aidog.pojo.Managers;
import com.sec.aidog.pojo.Sheepdogs;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private ManagersMapper managersMapper;

    @Autowired
    private SheepdogsDao sheepdogsDao;

    @Autowired
    private ExhibitrealtimeDao exhibitrealtimeDao;

    @Autowired
    private AppexhibitrealtimeDao appexhibitrealtimeDao;

    @Autowired
    private DistrictsDao districtsDao;

    @Autowired
    private ManagersDao managersDao;

    @Override
    public Managers userLogin(String username, String pwd) {
        AESUtil util = new AESUtil();
        Managers manager = null;
        manager = managersMapper.findUserByName(username);
        try {
            if(util.encryptData(pwd).equals(manager.getPassword())){
                return manager;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Integer> GetIndexLogoInfo(Managers manager) {
        Map<String, Integer> map = new HashMap<String,Integer>();
        if(manager.getPrivilegelevel() == 1) {
            List<Sheepdogs> sdlist = sheepdogsDao.getIndexInfor();
            int dognumtotal = 0;
            int feedernumtotal = 0;
            for(Sheepdogs each:sdlist){
                if(!each.getNeckletid().equals("-1")) {
                    dognumtotal++;
                }
                if(!each.getApparatusid().equals("-1")) {
                    feedernumtotal++;
                }
            }

            int med1 = exhibitrealtimeDao.getExhiCount();

            int med2 = appexhibitrealtimeDao.getAppExhiCount();
            int mednumtotal = med1 + med2;

            List<Districts> alllist = new ArrayList<Districts>();
            List<Districts> dislist = new ArrayList<Districts>();
            List<Districts> armylist = new ArrayList<Districts>();

            alllist = districtsDao.getDistricts();
            for(Districts al : alllist)
            {
                if (al.getDistrictcode().startsWith("66"))
                {
                    armylist.add(al);
                }
                else
                {
                    dislist.add(al);
                }
            }

            int armyepidemictotal = 1;

            int provinceepidemictotal = 0;
            int cityepidemictotal = 0;

            int countyepidemictotal = 0;
            int villageepidemictotal = 0;
            int hamletepidemictotal = 0;

            for(Districts each : dislist) {
                if(each.getEpidemic() == 1) {
                    hamletepidemictotal++;
                    if(each.getDistrictcode().substring(2, 12).equals("0000000000")) {
                        provinceepidemictotal++;
                    }
                    if(each.getDistrictcode().substring(4, 12).equals("00000000")) {
                        cityepidemictotal ++;
                    }
                    if(each.getDistrictcode().substring(6,12).equals("000000")) {
                        countyepidemictotal ++;
                    }
                    if(each.getDistrictcode().substring(9,12).equals("000")) {
                        villageepidemictotal ++;
                    }
                }
            }

            int divisionepidemictotal=0;
            int regimentalepidemictotal =0;
            int companyepidemictotal = 0;
            for(Districts each : armylist) {
                if(each.getEpidemic() == 1) {
                    companyepidemictotal++;
                    if(each.getDistrictcode().substring(4,8).equals("0000")) {
                        divisionepidemictotal++;
                    }
                    if(each.getDistrictcode().substring(6,8).equals("00")) {
                        regimentalepidemictotal++;
                    }
                }
            }

            List<Integer> levellist = new ArrayList<Integer>();
            int countryadmintotal = 0;
            int provinceadmintotal = 0;
            int cityadmintotal = 0;
            int countyadmintotal = 0;
            int villageadmintotal = 0;
            int hamletadmintotal = 0;
            levellist = managersDao.getManagerLevel();
            for(Integer each:levellist) {
                switch(each) {
                    case 1:
                        countryadmintotal++;
                        break;
                    case 2:
                        provinceadmintotal++;
                        break;
                    case 3:
                        cityadmintotal++;
                        break;
                    case 4:
                        countyadmintotal++;
                        break;
                    case 5:
                        villageadmintotal++;
                        break;
                    case 6:
                        hamletadmintotal++;
                        break;
                }
            }
            map.put("provinceepidemictotal", provinceepidemictotal + armyepidemictotal);
            map.put("cityepidemictotal", cityepidemictotal - provinceepidemictotal + (divisionepidemictotal - 1));
            map.put("countyepidemictotal", countyepidemictotal - cityepidemictotal + (regimentalepidemictotal - divisionepidemictotal));
            map.put("villageepidemictotal", villageepidemictotal - countyepidemictotal + (companyepidemictotal - regimentalepidemictotal));
            map.put("hamletepidemictotal",  hamletepidemictotal - villageepidemictotal - companyepidemictotal);
            map.put("countryadmintotal", countryadmintotal);
            map.put("provinceadmintotal", provinceadmintotal);
            map.put("cityadmintotal", cityadmintotal);
            map.put("countyadmintotal", countyadmintotal);
            map.put("villageadmintotal", villageadmintotal);
            map.put("hamletadmintotal", hamletadmintotal);
            map.put("countrydognumtotal", dognumtotal);
            map.put("alldognumtotal", 3280564);
            map.put("countrymednumtotal", mednumtotal);
            map.put("feedernumtotal", feedernumtotal);
        }
        return map;
    }

    @Override
    public Map<String, Object> GetCountryMap(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<Districts> provincesandcities = new ArrayList<Districts>();
        provincesandcities = districtsDao.getProvicesAndCities();
        int i = 0;
        for(Districts each:provincesandcities) {
            if(each.getDistrictcode().endsWith("0000000000")) {
                Map<String, Object> maptemp = new HashMap<String,Object>();
                maptemp.put("provincename", each.getShortname());
                String province0to2 = each.getDistrictcode().substring(0, 2);
                int citynum = 0;
                for(Districts each2:provincesandcities) {
                    if(each2.getDistrictcode().startsWith(province0to2) && each2.getDistrictcode().endsWith("00000000")
                            && !each2.getDistrictcode().equals(province0to2+"0000000000")) {
                        citynum++;
                    }
                }
                maptemp.put("citynum", citynum);

                int managernum = managersDao.getProvinceManagerLevelAndBelowByDistrictName(each.getDistrictname()).size();
                maptemp.put("managernum", managernum);
                List<String> dognumlist = sheepdogsDao.getAllNeckletIdByDistrictcode(province0to2);
                maptemp.put("dognum", dognumlist.size());
                int neckletnum = 0;
                for(String each3:dognumlist) {
                    if(!each3.equals("-1")) {
                        neckletnum++;
                    }
                }
                maptemp.put("neckletnum", neckletnum);
                int mednum = exhibitrealtimeDao.getExhiCountByDistrictcode(province0to2);
                maptemp.put("mednum", mednum);
                int feednum = appexhibitrealtimeDao.getAppExhiCountByDistrictcode(province0to2);
                maptemp.put("feedernum", feednum);

                map.put(""+i, maptemp);
                i++;
            }
        }
        return map;
    }
}
