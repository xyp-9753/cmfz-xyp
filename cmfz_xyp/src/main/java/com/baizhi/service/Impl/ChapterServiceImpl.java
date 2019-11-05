package com.baizhi.service.Impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entiry.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleUnresolved;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;


    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        int i = chapterDao.insertSelective(chapter);
        if (i==0){
            throw  new RuntimeException("添加章节失败");

        }
        return chapter.getId();
    }

    @Override
    public void edit(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if (i==0){
            throw new RuntimeException("修改章节失败");

        }
    }

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows,String albumId) {
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);
        List<Chapter> chapters = chapterDao.selectByRowBounds(chapter, rowBounds);
        int count = chapterDao.selectCount(chapter);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);

        return map;
    }
}
