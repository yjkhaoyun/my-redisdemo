package com.redis.myredisdemo.service;

public interface Code {
    public void setCode(String uuid,String imgCode);
    public String getCode(String uuid);
}
