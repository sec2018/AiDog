package com.sec.aidog.config;

import com.sec.aidog.service.AuthTokenService;
import com.sec.aidog.util.JsonResult;
import com.sec.aidog.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
//public class AuthTokenFilter implements Filter {
//
//    @Autowired
//    private AuthTokenService authTokenService;
//
//    @Override
//    public void init(FilterConfig var1) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        String token = req.getHeader("token");
//
//        JsonResult result = authTokenService.powerCheck(token);
//
//        System.out.println(SerializeUtil.serialize(result));
//        if (result.isSuccess() == true) {
//            chain.doFilter(request, response);
//        } else {
//            throw new ServletException(result.getMsg());
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}