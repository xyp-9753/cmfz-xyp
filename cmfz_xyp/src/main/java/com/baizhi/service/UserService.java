package com.baizhi.service;

import com.baizhi.entiry.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    public Map<String ,Object> selectUsersByStarId(Integer page,Integer rows,String StarId);
    void add(User user, String code, HttpServletRequest request);

}
