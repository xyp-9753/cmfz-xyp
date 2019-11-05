package com.baizhi.service;

import com.baizhi.entiry.Banner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BannerService {
    public Map<String,Object> queryByPage(Integer page,Integer rows);
    public String  add(Banner banner);
    public void edit(Banner banner);
    public void del(String id, HttpServletRequest request);
    void BannerPoiExport();
}
