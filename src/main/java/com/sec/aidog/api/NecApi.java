package com.sec.aidog.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sec.aidog.pojo.Manager;
import com.sec.aidog.pojo.Necklet;
import com.sec.aidog.service.NeckletService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JSONUtil;
import com.sec.aidog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("api")
@Controller
public class NecApi {

    @Autowired
    private NeckletService neckletService;

    @Autowired
    private RedisService redisService;




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

            JSONArray arr = JSON.parseArray(data);
            List<Necklet> neclist = new ArrayList<>();
            Necklet necklet = null;
            for(Object obj:arr){
                necklet = new Necklet();
                necklet.setNecId(((JSONObject)obj).get("nec_id")+"");
                necklet.setProduceTime(new Date(Long.valueOf(((JSONObject)obj).get("producetime")+"")));
                necklet.setRegisterTime(new Date());
                neclist.add(necklet);
            }
            if(neclist.size()>0){
                boolean isSuccess = neckletService.batchNecRegister(neclist);
                if(isSuccess){
                    result = "批量注册项圈成功!";
                }else{
                    result = "批量注册项圈失败!";
                }
            }
        }catch (Exception e) {
            result = "批量注册项圈失败!";
        }
        return result.toString();
    }
}

