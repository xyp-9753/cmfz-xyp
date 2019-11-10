package com.baizhi.service;

import com.baizhi.entiry.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String ,Object> selectAll(Integer page,Integer rows,String albumId);
    String add(Chapter chapter);

    void edit(Chapter chapter);
}
