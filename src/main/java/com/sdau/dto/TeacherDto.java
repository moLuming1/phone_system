package com.sdau.dto;

import com.sdau.entity.Teacher;
import lombok.Data;

@Data
public class TeacherDto extends Teacher {

    private String dtoName;

    private String system;
}
