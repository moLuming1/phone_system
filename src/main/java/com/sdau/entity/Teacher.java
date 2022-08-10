package com.sdau.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 教师实体类
 */
@Data
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @TableField(select = false)
    private String password;

    private String name;

    private String phone;

    //状态 1 启用 0禁用
    private Integer status;

    //所属学院主键代码
    private Long academyId;



    //private Integer isDeleted;




}
