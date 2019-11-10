package com.baizhi.service;

import com.baizhi.entiry.Star;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

public interface StarService {
    public Map<String,Object> selectAll(Integer page,Integer rows);
    public String add(Star star);
    public void edit(Star star);
    List<Star> selectAllStar();
}
