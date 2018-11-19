package com.sec.aidog.controller;

import com.sec.aidog.common.Constant;
import com.sec.aidog.service.LoginService;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.service.UserService;
import com.sec.aidog.util.JsonResult;
import com.sec.aidog.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;
    /**
     * 登录
     * @param request
     * @param response
     * @param name
     * @param pass
     * @return
     */
    @PostMapping(value = "/login")
    public JsonResult login(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam String name, @RequestParam String pass){
        //判断登录
        try{
            //userService为自己定义的Service类
            if(userService.userLogin(name, pass)){
//                CookiesHandle cookiesHandle = new CookiesHandle(request, response);
//                //将用户名存到cookie中
//                cookiesHandle.addCookie("last_login_username", name, 60*60*24);

                //PassHandle为自己定义的一个生成Token的类，可以根据自己喜好来改
                String token = TokenGenerator.generateValue();

                //将token存到redis缓存中
                redisService.set("_token:" + name, token);

//                //将token存到cookie中
//                cookiesHandle.addCookie("uuid", token, 60*60*24);

                return new JsonResult(Constant.SUCCESS.getCode(), "登录"+Constant.SUCCESS.getMsg(),false, null);
            } else
                return new JsonResult(Constant.LOGIN_EXCEPTION.getCode(), Constant.LOGIN_EXCEPTION.getMsg(),false, null);
        }catch (Exception e){
            return new JsonResult(Constant.LOGIN_EXCEPTION.getCode(), Constant.LOGIN_EXCEPTION.getMsg(),false, null);
        }
    }


    /**
     * 判断用户是否已登
     * @Login： 为上面我们自己配置的注解类，
     * 括号中可以选择对应的注解，
     * 这里因为我们只配置了一个，因此可不写
     * 如果通过了AOP的身份认证，该方法就会被调用
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/isLogin")
    @LoginService
    public JsonResult isLogin(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
//        CookiesHandle cookiesHandle = new CookiesHandle(request, response);
//        String name = cookiesHandle.getCookieByName("last_login_username").getValue();
        try {
            //取出存在缓存中的已登录用户的信息
            String user = redisService.get(token);
            if (user == null)
                return new JsonResult(Constant.LOGIN_USERNOTEXIST.getCode(), Constant.LOGIN_USERNOTEXIST.getMsg(),false, null);
            else
                return new JsonResult(Constant.LOGIN_ALREADY.getCode(), Constant.LOGIN_ALREADY.getMsg(),false, null);
        }catch (Exception e){
            return new JsonResult(Constant.LOGIN_USERNOTEXIST.getCode(), Constant.LOGIN_USERNOTEXIST.getMsg(),false, null);
        }
    }
}
