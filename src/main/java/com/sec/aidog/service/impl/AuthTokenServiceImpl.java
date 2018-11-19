package com.sec.aidog.service.impl;

import com.sec.aidog.service.AuthTokenService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthTokenServiceImpl implements AuthTokenService {

    @Autowired
    private RedisService redisService;
    /*
     * 验证请求头token是否合法
     * */
    @Override
    public JsonResult powerCheck(String token) {

        JsonResult res = new JsonResult();
        res.setCode(200);
        res.setMsg("token验证通过");
        res.setSuccess(true);

        System.out.println("token的值为:" + token);

        if (StringUtils.isEmpty(token) == true) {
            res.setMsg("authtoken为空");
            return res;
        }
        //处理黑名单
        res = checkForbidList(token);
        if (res.isSuccess() == false) {
            return res;
        }

        //处理白名单
        res = checkAllowList(token);
        if (res.isSuccess() == false) {
            return res;
        }

        String key = String.format("aidog.api.%s", token);

        //从缓存中取
//        String existToken = redisService.get(key);

        String existToken = "wang";
        if (StringUtils.isEmpty(existToken) == true) {
            res.setMsg(String.format("不存在此authtoken：%s", token));
            return res;
        }
        //比较token是否相同
        Boolean isEqual = token.equals(existToken);
        if (isEqual == false) {
            res.setMsg(String.format("不合法的authtoken:%s", token));
            return res;
        }
        //do something

        return res;
    }

    public JsonResult checkForbidList(String token){
        JsonResult res = new JsonResult();
        res.setCode(200);
        res.setMsg("token验证通过");
        res.setSuccess(true);
        return res;
    }

    public JsonResult checkAllowList(String token){
        JsonResult res = new JsonResult();
        res.setCode(200);
        res.setMsg("token验证通过");
        res.setSuccess(true);
        return res;
    }
}
