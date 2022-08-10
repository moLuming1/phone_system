package com.sdau.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 学生实体
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @TableField(select = false)
    private String password;

    private String name;

    //所属学院信息id
    private Long  academyId;


}
