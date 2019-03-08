package com.sec.aidog.api;

import com.sec.aidog.common.RedisUtil;
import com.sec.aidog.pojo.Manager;
import com.sec.aidog.service.ProvinceService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.CommonThreadPool;
import com.sec.aidog.util.JSONUtil;
import com.sec.aidog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import com.google.gson.Gson;

@RequestMapping("api")
@Controller
public class MapApi {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Resource
    private ProvinceService provinceService;

    @Autowired
    private Gson gson;

    @ApiOperation(value = "获取默认地图数据", notes = "获取默认地图数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="getindexmap",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> GetIndexMap(HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
//            String managerstr = redisService.get("token:"+token);
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            Manager resultUser = ((Manager) JSONUtil.JSONToObj(managerstr, Manager.class));
            JSONObject jsStr = null;
            switch(resultUser.getPrivilegelevel()) {
                case 1:
                    Map<String,Object> data = new HashMap<String,Object>();
                    data.put("data1",resultUser);
                    Map<String,Integer> LogoInfo = new HashMap<String,Integer>();
                    Map<String,Object> countrymap = new HashMap<String,Object>();
                    Future<Object> LogoInfotemp = CommonThreadPool.submit(()->{
                        //System.out.println("1开始。。。");
                        Map<String,Integer> temp = new HashMap<String, Integer>();
                        temp = gson.fromJson(redisService.get(resultUser.getUsername() + "_UserController_IndexLogoInfo"),temp.getClass());
                        if(temp == null) {
                            temp = userService.GetIndexLogoInfo(resultUser);
                            redisService.set(resultUser.getUsername() + "_UserController_IndexLogoInfo", gson.toJson(temp));
                        }
                        return temp;
                    });
                    Future<Object> countrymaptemp = CommonThreadPool.submit(()->{
                        Map<String,Object> temp = new HashMap<String, Object>();
                        temp = gson.fromJson(redisService.get(resultUser.getUsername() + "_UserController_CountryMap"),temp.getClass());
                        if(temp == null) {
                            temp = userService.GetCountryMap();
                            redisService.set(resultUser.getUsername() + "_UserController_CountryMap", gson.toJson(temp));
                        }
                        return temp;
                    });
                    try {
                        LogoInfo = (Map<String, Integer>) LogoInfotemp.get();
                        countrymap = (Map<String, Object>) countrymaptemp.get();
                    }catch(InterruptedException | ExecutionException ex) {
                        ex.printStackTrace();
                    }
                    data.put("data2",LogoInfo);
                    data.put("data3",countrymap);
                    jsStr = JSONObject.fromObject(data);
                    r.setCode(200);
                    r.setMsg("获取用户信息成功！");
                    r.setData(jsStr);
                    r.setSuccess(true);
                    break;
                case 2:
                    return GetProvinceMap(resultUser.getProvince(),request);
                case 3:
//                    redirectAttributes.addAttribute("city", resultUser.getCity());
//                    redirectAttributes.addAttribute("province", resultUser.getProvince());
//                    return "redirect:/city/city.do";

                case 4:
//                    redirectAttributes.addAttribute("county", resultUser.getCounty());
//                    redirectAttributes.addAttribute("city", resultUser.getCity());
//                    redirectAttributes.addAttribute("province", resultUser.getProvince());
//                    return "redirect:/county/county.do";

                case 5:
//                    redirectAttributes.addAttribute("village", resultUser.getVillage());
//                    redirectAttributes.addAttribute("county", resultUser.getCounty());
//                    redirectAttributes.addAttribute("city", resultUser.getCity());
//                    redirectAttributes.addAttribute("province", resultUser.getProvince());
//                    return "redirect:/village/village.do";
                case 6:
//                    url.append("page_hamlet");
//                    break;
            }
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取默认地图信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value="/getprovincemap",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "province", value = "省份", required = false, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header")
    })
    @ResponseBody
    public ResponseEntity<JsonResult> GetProvinceMap(@RequestParam(value="province",required = false) String province, HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
//            String managerstr = redisService.get("token:"+token);
            String managerstr = RedisUtil.RedisGetValue("token:"+token);
            Manager resultUser = ((Manager) JSONUtil.JSONToObj(managerstr, Manager.class));
            JSONObject jsStr = null;
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("data1",resultUser);//data1保存用户信息
            String provinceres = province;
            if(provinceres!=""){
                //上级页面跳转而来，非首页，解码
//                provinceres = URLDecoder.decode(provinceres, "UTF-8");
            }else{
                //当前管理员首页
                provinceres = resultUser.getProvince();
            }
            if(province.equals("建设兵团")) {//查看建设兵团的详情
                Map<String,Integer> armyIndexInfo = provinceService.GetArmyIndexLogo(province);//获得建设兵团的总体数据信息
                data.put("data2",armyIndexInfo);
                Map<String,Object> armyProvinceMap = provinceService.GetArmyProvinceMap(province);//获得该建设兵团下各个流行师的详细数据
                data.put("data3", armyProvinceMap);
                Map<String,Object> data4 = provinceService.GetDistrictcode(province);//获得该建设兵团的区域编码
                data.put("data4", data4);
            }else {//如果非建设兵团，即省
                Map<String,Integer> provinceIndexInfo = provinceService.GetIndexLogoInfo(province);//获得该省的总体数据信息
                data.put("data2",provinceIndexInfo);
                Map<String,Object> ProvinceMap = provinceService.GetProvinceMap(province);//获得该省下各个流行市的详细数据
                data.put("data3", ProvinceMap);
                Map<String,Object> data4 = provinceService.GetDistrictcode(province);//获得该省的区域编码
                data.put("data4", data4);
            }
            jsStr = JSONObject.fromObject(data);//数据转为json格式
            r.setCode(200);
            r.setMsg("获取用户信息成功！");
            r.setData(jsStr);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取省级地图信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
