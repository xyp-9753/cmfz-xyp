package com.baizhi;

import com.baizhi.controller.ArticleController;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entiry.Admin;
import com.baizhi.entiry.Article;
import com.baizhi.entiry.Banner;
import com.baizhi.entiry.Star;
import com.baizhi.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@SpringBootTest
class CmfzXypApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private StarService starService;
    @Autowired
    private ArticleController articleController;
    @Test
    void contextLoads() {
       /* Map<String, Object> map = articleController.selectAll(1, 3);
        System.out.println(map);*/
       /* List<Article> list = articleDao.selectAll();
        System.out.println(list);*/
        /*Map<String, Object> map = articleService.selectAll(0, 2);
        System.out.println(map);*/
        /*bannerService.BannerPoiExport();*/
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++) 	{
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        System.out.println(flag);
    }

}
