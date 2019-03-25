package com.sec.aidog.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sec.aidog.pojo.Manager;
import com.sec.aidog.service.DogService;
import com.sec.aidog.service.OwnerService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JSONUtil;
import com.sec.aidog.util.JsonResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("api")
@Controller
public class NecApi {

    @Autowired
    private DogService dogService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private OwnerService ownerService;



    @RequestMapping(value = "batchnecregister",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    @ResponseBody
    public String BatchNecRegister(@RequestBody JSONObject json, HttpServletRequest request) {
        String result = "";
        String data = json.getString("data");
        String token = request.getHeader("token");
        JsonResult r = new JsonResult();
        try {
            String managerstr = redisService.get("token:" + token);
            Manager manager = ((Manager) JSONUtil.JSONToObj(managerstr, Manager.class));
            //权限控制

            try {
                JSONArray arr = JSON.parseArray(data);

                for(Object obj:arr){

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                result = "添加主人失败!";
            }
        }catch (Exception e) {

        }
        return result.toString();
    }
}

