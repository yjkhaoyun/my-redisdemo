package com.redis.myredisdemo.service.impl;

import com.redis.myredisdemo.service.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ICode implements Code {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void setCode(String uuid,String imgCode) {
        //将穿过来的参数uuid和imgCode存在redis中   30秒后过期
        stringRedisTemplate.boundValueOps(uuid).set(imgCode,30,TimeUnit.SECONDS);
    }

    @Override
    public String getCode(String uuid) {
        String imgCode = stringRedisTemplate.boundValueOps(uuid).get();
        return imgCode;
    }
}
