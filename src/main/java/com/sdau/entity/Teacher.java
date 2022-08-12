package com.sdau.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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

    @ExcelIgnore
    private Long id;

    @ExcelProperty("用户名")
    private String username;

    @TableField(select = false)
    @ExcelProperty("密码")
    private String password;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("手机号")
    private String phone;

    //状态 1 启用 0禁用
    @ExcelIgnore
    private Integer status;

    //所属学院主键代码
    @ExcelIgnore
    private Long academyId;



}
