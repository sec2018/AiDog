package com.sec.aidog.api;

import com.sec.aidog.pojo.Managers;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
            String managerstr = redisService.get("token:"+token);
            Managers resultUser = ((Managers) JSONUtil.JSONToObj(managerstr, Managers.class));
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
//                    redirectAttributes.addAttribute("province", resultUser.getProvince());
//                    return "redirect:/province/province.do";

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
            r.setMsg("获取用户信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}
