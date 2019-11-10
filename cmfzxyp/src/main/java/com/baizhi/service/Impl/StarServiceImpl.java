package com.baizhi.service.Impl;

import com.baizhi.dao.StarDao;
import com.baizhi.entiry.Star;
import com.baizhi.service.StarService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class StarServiceImpl implements StarService {
    @Autowired
    private StarDao starDao;

    @Override
    public List<Star> selectAllStar() {
        Star star = new Star();
        List<Star> select = starDao.select(star);
        return select;
    }

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Star star = new Star();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);

        int count = starDao.selectCount(star);
        List<Star> stars = starDao.selectByRowBounds(star,rowBounds);
        Map<String , Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",stars);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);

        return map;
    }

    @Override
    public void edit(Star star) {

        int i = starDao.updateByPrimaryKeySelective(star);
        if(i==0){
            throw  new RuntimeException("修改明星失败");

        }

    }

    public String add(Star star){

        star.setId(UUID.randomUUID().toString());
        int insert = starDao.insert(star);
        if (insert==0){
            throw new RuntimeException("添加明星失败");
        }
        return star.getId();
    }
}
