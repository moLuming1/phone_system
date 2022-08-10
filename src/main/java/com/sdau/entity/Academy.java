package com.sdau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 学院实体
 */
@Data
public class Academy implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String system;



}
