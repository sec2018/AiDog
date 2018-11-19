package com.sec.aidog.aop;

import com.sec.aidog.common.Constant;
import com.sec.aidog.service.RedisService;
import com.sec.aidog.util.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 必须添加这两个注解
 * @Component: 泛指组件，讲该类纳入bean中
 * @Aspect: 通过该注解及表达式就可以轻松的使用POJO来定义切面
 */
@Aspect
@Component
public class LoginAop {

    /**
     * 此方法封装了对redis的一些常用操作，
     * 通过此方法，我们可以去redis里寻找用户的身份是否存在，方便判断身份，
     * 如果不使用redis的话，可以将以下所有出现过该方法的地方替换成对应的Service即可.
     */
    @Resource
    private RedisService redisService;

    /**
     * 定义拦截规则：拦截标有com.sec.aidog.service.LoginService类中注解的所有方法
     */
    @Pointcut("@annotation(com.sec.aidog.service.LoginService)")
    public void loginMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param point
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("loginMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint point){

        //正在被通知的方法相关信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();
        //返回的结果
        Object result = null;
        //返回方法参数
        Object[] args = point.getArgs();
        for(Object arg : args){
            //获取request请求
            if(arg instanceof HttpServletRequest){
                HttpServletRequest request = (HttpServletRequest) arg;
                //判断用户是否登录
                if(!isLogin(request)){
                    //JsonResult与ResultCode是我自己封装的返回类及enum类
                    result = new JsonResult(Constant.NOT_LOGIN.getCode(), Constant.NOT_LOGIN.getMsg(),false, null);
                }
            }
        }
        try{
            if(result == null)
                // 一切正常的情况下，继续执行被拦截的方法
                result = point.proceed();
        }catch (Throwable e){
            result = new JsonResult(Constant.LOGIN_EXCEPTION.getCode(), Constant.LOGIN_EXCEPTION.getMsg(),false, null);
        }

        return result;
    }

    /**
     * 判断方法是否需要登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        try {
            //将cookie中的用户信息取出
            //CooikesHandle类是我自己封装的一个处理Cookie的类
//            CookiesHandle cookiesHandle = new CookiesHandle(request, null);
//            String uuid = cookiesHandle.getCookieByName("uuid").getValue();
//            String name = cookiesHandle.getCookieByName("last_login_username").getValue();
            //将redis缓存中的用户信息取出
            String token = request.getHeader("token");
            String managerstr  = redisService.get("_token:" + token);
            //System.out.println(token);
            if(token == null){
                return false;
            }
            else{
                if(managerstr!=null){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("aop获取redis缓存中的token失败");
            return false;
        }
        return false;
    }
}
