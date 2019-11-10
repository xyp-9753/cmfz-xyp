package com.baizhi.service.Impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entiry.User;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user, String code, HttpServletRequest request) {
        String  aliCode = (String) request.getSession().getAttribute("aliCode");
        if (aliCode.equals(code)){
            user.setId(UUID.randomUUID().toString());
            user.setCreate_date(new Date());
            int i = userDao.insertSelective(user);
            if (i==0){
                throw new RuntimeException("注册失败");
            }
        }else {
            throw new RuntimeException("验证码错误");
        }

    }

    @Override
    public Map<String, Object> selectUsersByStarId(Integer page, Integer rows, String StarId) {
        User user = new User();
        user.setStarId(StarId);
        int count = userDao.selectCount(user);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByRowBounds(user, rowBounds);
        Map<String , Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",users);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }
}
