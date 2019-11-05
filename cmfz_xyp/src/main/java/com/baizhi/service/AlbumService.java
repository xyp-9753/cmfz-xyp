package com.baizhi.service;

import com.baizhi.entiry.Album;

import java.util.Map;

public interface AlbumService {
    Map<String,Object> selectAll(Integer page, Integer rows);

    String add(Album album);

    void edit(Album album);
    Album selectOne(String id);
}
