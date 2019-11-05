package com.baizhi.service;

import com.baizhi.entiry.Admin;
import com.baizhi.entiry.User;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    public void login(Admin admin, String inputCode, HttpServletRequest request);
    void add(Admin admin, String code, HttpServletRequest request);
}
