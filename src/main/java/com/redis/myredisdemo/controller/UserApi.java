package com.redis.myredisdemo.controller;

import com.redis.myredisdemo.service.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserApi {
    @Autowired
    private Code code;

    /**
     * 测试访问http://localhost:8080/codeIf?imgCode=aaaaaaaaammmmmaa&uuid=bb 设置值
     * @param imgCode
     * @param uuid
     * @return  将验证码存进redis缓存当中
     */
    @RequestMapping(value = "/codeIf",method = RequestMethod.GET)
    @ResponseBody
    public String codeIf(@RequestParam("imgCode") String imgCode,@RequestParam(value = "uuid",defaultValue = "null") String uuid){
        System.out.println(imgCode);
        System.out.println(uuid);
        code.setCode(uuid,imgCode);
        return "设置验证码成功";
    }

    /**
     * http://localhost:8080/getCode?uuid=bb  读取值
     * @param request
     * @return  从redis缓存中取出验证码
     */
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    @ResponseBody
    public String getCode(HttpServletRequest request){
        try {
            String uuid = request.getParameter("uuid");
            System.out.println("获取对象的"+uuid);
            String imgCode = code.getCode(uuid);

            return imgCode;
        }catch (Exception e){
            System.out.println(e);
        }
        return "获取失败";
    }
}
