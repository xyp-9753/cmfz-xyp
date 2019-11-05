package com.baizhi.service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.entiry.Banner;
import com.baizhi.service.BannerService;
import com.sun.rowset.internal.Row;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void BannerPoiExport() {
        Banner banner = new Banner();
        List<Banner> select = bannerDao.select(banner);
        for (Banner banner1 : select) {
            String cover = banner1.getCover();
            banner1.setCover("D:\\downloads\\"+cover);
        }
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("轮播图", "轮播图"), Banner.class, select);
        try {
            sheets.write(new FileOutputStream(new File("F:\\166班\\后期项目\\banner.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String add(Banner banner) {

        banner.setId(UUID.randomUUID().toString());
        banner.setCreateDate(new Date());
        int i = bannerDao.insertSelective(banner);
        if(i==0){
            throw new RuntimeException("添加失败");
        }
        return banner.getId();
    }

    @Override
    public void edit(Banner banner) {
        if ("".equals(banner.getCover())){
            banner.setCover(null);
        }
        try {
            bannerDao.updateByPrimaryKeySelective(banner);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void del(String id, HttpServletRequest request) {
        Banner banner = bannerDao.selectByPrimaryKey(id);
        int i = bannerDao.deleteByPrimaryKey(id);
        if (i==0){
            throw new RuntimeException("删除失败");
        }else {
            String cover = banner.getCover();
            File file1 = new File("");


            File file = new File(request.getServletContext().getRealPath("/banner/img"),cover);
            boolean b = file.delete();
            if (b==false){
                throw new RuntimeException("删除轮播图失败");
            }
        }

    }

    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        int i = bannerDao.selectCount(new Banner());
        map.put("records",i);
        Integer total=i%rows==0?i/rows:i/rows+1;
        map.put("total",total);
        map.put("page",page);
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        Banner banner = new Banner();
        List<Banner> banners = bannerDao.selectByRowBounds(banner, rowBounds);
        map.put("rows",banners);
        return map;
    }
}
