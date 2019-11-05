package com.baizhi.service.Impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.StarDao;
import com.baizhi.entiry.Album;
import com.baizhi.entiry.Star;
import com.baizhi.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private StarDao starDao;
    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Album album = new Album();
        int count = albumDao.selectCount(album);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> albums = albumDao.selectByRowBounds(album, rowBounds);
        for (Album aa : albums) {
            Star star = starDao.selectByPrimaryKey(aa.getStarId());
            aa.setStar(star);
        }
        Map<String , Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
            return map;
    }

    @Override
    public String add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCreateDate(new Date());
        int insert = albumDao.insert(album);
        if (insert==0){
            throw new RuntimeException("添加专辑失败");
        }
        return album.getId();
    }

    @Override
    public Album selectOne(String id) {
        Album album = albumDao.selectByPrimaryKey(id);
        return album;

    }

    @Override
    public void edit(Album album) {
        int i = albumDao.updateByPrimaryKeySelective(album);
        if (i==0){
            throw new RuntimeException("修改专辑失败");
        }
    }
}
