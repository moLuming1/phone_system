package com.sdau.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 授课关系实体
 */
@Data
public class Teach implements Serializable {

    public  static final long serialVersionUID=1L;

    //主键
    private Long id;

    //课程主键
    private Long courseId;

    //教师主键
    private Long teacherId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;             //插入时填充字段

    @TableField(fill = FieldFill.INSERT_UPDATE)  //插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)          //插入时填充字段
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)   //插入和更新时填充字段
    private Long updateUser;



}
