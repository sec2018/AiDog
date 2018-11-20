package com.sec.aidog.api;

import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("api")
@Controller
public class ManagerApi {


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
            String managerstr = redisService.get("_token:"+token);
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
}
