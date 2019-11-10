package com.baizhi.controller;

import com.baizhi.entiry.Star;
import com.baizhi.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("star")
public class StarController {
    @Autowired
    private StarService starService;
    @RequestMapping("selectAll")
    public Map<String ,Object> selectAll(Integer page,Integer rows){
        Map<String, Object> map = starService.selectAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Star star){
        Map<String , Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)){
                String add = starService.add(star);
                map.put("status",true);
                map.put("message",add);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile photo, String id, HttpServletRequest request){
        Map<String , Object> map = new HashMap<>();
        try {
            photo.transferTo(new File(request.getServletContext().getRealPath("/star/img"),photo.getOriginalFilename()));            Star star = new Star();
            star.setId(id);
            star.setPhoto(photo.getOriginalFilename());
            starService.edit(star);
            map.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;

    }
    @RequestMapping("getStarName")
    public void getStarName(HttpServletResponse response) throws IOException {
        List<Star> stars = starService.selectAllStar();
        System.out.println(stars);
        String str="<select>";
        for (Star star : stars) {
            str+="<option value="+star.getId()+">"+star.getNickname()+"</option>";
        }
        str+="</select>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(str);
    }


}
