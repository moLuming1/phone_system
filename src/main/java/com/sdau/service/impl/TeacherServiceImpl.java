package com.sdau.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdau.entity.Teacher;
import com.sdau.mapper.TeacherMapper;
import com.sdau.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
 implements TeacherService
   {
}
