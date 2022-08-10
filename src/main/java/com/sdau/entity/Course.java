package com.sdau.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程实体类
 */
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    //课程主键如BK1111
    private String id;

    //课程名称如高等数学
    private String name;

    //教学班
    private String teachClassID;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;             //插入时填充字段

    @TableField(fill = FieldFill.INSERT_UPDATE)  //插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)          //插入时填充字段
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)   //插入和更新时填充字段
    private Long updateUser;

}
