package com.baizhi.service.Impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entiry.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AdminSreviceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public void add(Admin admin, String code, HttpServletRequest request) {
        String  aliCode = (String) request.getSession().getAttribute("aliCode");
        if (aliCode.equals(code)){
            admin.setId(UUID.randomUUID().toString());
            int i = adminDao.insertSelective(admin);
            if (i==0){
                throw new RuntimeException("注册失败");
            }
        }else {
            throw new RuntimeException("验证码错误");
        }

    }

    @Override
    public void login(Admin admin, String inputCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String securityCode = (String) session.getAttribute("securityCode");
        if (securityCode.equals(inputCode)){
            Admin admin1 = adminDao.selectOne(admin);
            if (admin1!=null){
                session.setAttribute("loginAdmin",admin1);
            }else {
                throw new RuntimeException("用户名或密码错误");
            }
        }else {
            throw new RuntimeException("验证码错误");
        }
    }
}
