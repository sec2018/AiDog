package com.sec.aidog.api;

import com.sec.aidog.service.RedisService;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("api")
@Controller
public class ManagerApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "通行证", required = true, dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名标识", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value="getuserinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JsonResult> GetManagerInfo(@RequestParam(value = "username")String username,HttpServletRequest request){
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            //取出存在缓存中的已登录用户的信息
            String managerstr = redisService.get("token:"+token);
            r.setCode(200);
            r.setMsg("获取用户信息成功！");
            r.setData(managerstr);
            r.setSuccess(true);
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("获取用户信息失败");
            r.setSuccess(false);
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @RequestMapping(value="/register/{id}", produces="text/html;charset=UTF-8",method=RequestMethod.GET)
    @ResponseBody
    public String register(@PathVariable(value="id")Integer id) {
        Map<String,Object> data = new HashMap<String,Object>();
        String json = "";
        switch(id) {
            case 1:
                json = redisService.get("_registerAllcities");
                if(json == null) {
                    try {
                        List<Map<String, String>> map = null;
                        map = userService.GetAllCities();
                        data.put("data1",map);
                        json = JSONObject.fromObject(data).toString();
                        redisService.set("_registerAllcities", json);
                        break;
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                json = redisService.get("_registerAllcounties");
                if(json == null) {
                    try {
                        List<Map<String, String>> map1 = userService.GetAllCities();
                        data.put("data1",map1);
                        List<Map<String, String>> map2 = userService.GetAllCounties();
                        data.put("data2",map2);
                        json = JSONObject.fromObject(data).toString();
                        redisService.set("_registerAllcounties", json);
                        break;
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                json = redisService.get("_registerAllvillages");
                if(json == null) {
                    try {
                        List<Map<String, String>> map1 = userService.GetAllCities();
                        data.put("data1",map1);
                        List<Map<String, String>> map2 = userService.GetAllCounties();
                        data.put("data2",map2);
                        List<Map<String, String>> map3 = userService.GetAllVillages();
                        data.put("data3",map3);
                        json = JSONObject.fromObject(data).toString();
                        redisService.set("_registerAllvillages", json);
                        break;
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 4:
                json = redisService.get("_registerAllhamlets");
                if(json == null) {
                    try {
                        List<Map<String, String>> map1 = userService.GetAllCities();
                        data.put("data1",map1);
                        List<Map<String, String>> map2 = userService.GetAllCounties();
                        data.put("data2",map2);
                        List<Map<String, String>> map3 = userService.GetAllVillages();
                        data.put("data3",map3);
                        List<Map<String, String>> map4 = userService.GetAllHamlets();
                        data.put("data4",map4);
                        json = JSONObject.fromObject(data).toString();
                        redisService.set("_registerAllhamlets", json);
                        break;
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
        }
        return json;
    }


}
