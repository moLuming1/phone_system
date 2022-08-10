package com.sdau.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员实体
 */
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @TableField(select = false)
    private String password;

    private String name;

    private String phone;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;             //插入时填充字段

    @TableField(fill = FieldFill.INSERT_UPDATE)   //插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)           //插入时填充字段
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)    //插入和更新时填充字段
    private Long updateUser;

}
