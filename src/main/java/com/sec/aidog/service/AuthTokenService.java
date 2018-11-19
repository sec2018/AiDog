package com.sec.aidog.service;

import com.sec.aidog.util.JsonResult;

public interface AuthTokenService {

    JsonResult powerCheck(String token);
}
