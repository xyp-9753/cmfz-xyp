package com.baizhi.controller;


import com.baizhi.entiry.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;


    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String,Object> selectAll(Integer page,Integer rows){
        Map<String, Object> map = bannerService.queryByPage(page, rows);

        return map;

    }
    @ResponseBody
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Banner banner, HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)){
                String id = bannerService.add(banner);
                map.put("message",id);
            }
            if ("edit".equals(oper)){
                bannerService.edit(banner);
            }
            if ("del".equals(oper)){
                bannerService.del(banner.getId(),request);

            }
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;

    }
    @RequestMapping("upload")
    @ResponseBody
    public Map<String ,Object> upload(MultipartFile cover,String id,HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();
        try {
            cover.transferTo(new File(request.getServletContext().getRealPath("/banner/img"),cover.getOriginalFilename()));
            Banner banner = new Banner();
            banner.setId(id);
            banner.setCover(cover.getOriginalFilename());
            bannerService.edit(banner);
            map.put("status",true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status",false);
        }
        return map;
    }
}
