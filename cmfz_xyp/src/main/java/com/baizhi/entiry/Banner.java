package com.baizhi.entiry;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Banner {
    @Id
    @Excel(name="编号")
    private String id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "图片",type = 2)
    private String cover;
    @Excel(name="描述")
    private String description;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "创建日期",format = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
}
