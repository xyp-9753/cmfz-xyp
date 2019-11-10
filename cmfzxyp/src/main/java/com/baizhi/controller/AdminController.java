package com.baizhi.controller;

import com.baizhi.entiry.Admin;
import com.baizhi.entiry.User;
import com.baizhi.service.AdminService;
import com.baizhi.util.AliyunGetCode;
import com.baizhi.aliyun.sendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public Map<String,Object> login(Admin admin, String inputCode, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        try{
            adminService.login(admin,inputCode,request);
            map.put("status",true);

        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("register")
    public Map<String,Object> register(Admin admin, String inputCode, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        System.out.println(inputCode);

        try {
            adminService.add(admin,inputCode,request);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("getDuanXinCode")
    public Map<String,Object> getDuanXinCode(String phone,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            String s = AliyunGetCode.SuiJiCode();
            String s1 = sendMessage.sendMessage(s, phone, request);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
