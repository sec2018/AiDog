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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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


    @ApiOperation(value = "单个项圈注册", notes = "单个项圈注册")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "nec_id", value = "项圈标识", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "producetime", value = "生产日期", required = true, dataType = "String",paramType = "query"),
        @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String",paramType = "header")
    })
    @RequestMapping(value="singlenecregister",method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public ResponseEntity<JsonResult> singleNecRegister(@RequestParam(value = "nec_id")String nec_id, @RequestParam(value = "producetime")String producetime, HttpServletRequest request){
        JsonResult r = new JsonResult();
        try {
            Necklet necklet = new Necklet();
            necklet.setNecId(nec_id);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
            necklet.setProduceTime(format.parse(producetime));
            necklet.setRegisterTime(new Date());
            boolean isSuccess = neckletService.singleNecRegister(necklet);
            if(isSuccess){
                r.setCode(200);
                r.setMsg("注册项圈成功!");
                r.setData(necklet);
                r.setSuccess(true);
            }else{
                r.setCode(500);
                r.setData(null);
                r.setMsg("注册项圈失败！");
                r.setSuccess(false);
            }
        } catch (Exception e) {
            r.setCode(500);
            r.setData(e.getClass().getName() + ":" + e.getMessage());
            r.setMsg("注册项圈失败！");
            r.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }
}

