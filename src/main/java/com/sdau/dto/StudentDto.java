package com.sdau.dto;

import com.sdau.entity.Student;
import lombok.Data;

@Data
public class StudentDto extends Student {

    private String dtoName;

    private String system;

}
