package com.sdau.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdau.dto.TeacherDto;
import com.sdau.entity.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {

    public void saveTeacher(TeacherDto teacherDto);

    public void saveBatchTeacher(List list);

}
