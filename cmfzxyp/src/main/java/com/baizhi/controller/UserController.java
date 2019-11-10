package com.baizhi.controller;

import com.baizhi.entiry.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("selectUsersByStarId")
    public Map<String,Object> selectUsersByStarId(Integer page,Integer rows,String starId){
        Map<String, Object> map = userService.selectUsersByStarId(page, rows, starId);
        System.out.println(starId);
        System.out.println(map);
        return map;
    }
    @RequestMapping("register")
    public Map<String,Object> register(User user, String code, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            userService.add(user,code,request);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }


}
