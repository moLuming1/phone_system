package com.sdau.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdau.dto.TeacherDto;
import com.sdau.entity.Academy;
import com.sdau.entity.Teacher;
import com.sdau.mapper.TeacherMapper;
import com.sdau.service.AcademyService;
import com.sdau.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {


    @Autowired
    private AcademyService academyService;


    /**
     * 新增教师
     * @param teacherDto
     */
    @Override
    public void saveTeacher(TeacherDto teacherDto) {
        LambdaQueryWrapper<Academy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(teacherDto.getDtoName() != null, Academy::getName, teacherDto.getDtoName())
                .eq(teacherDto.getSystem() != null, Academy::getSystem, teacherDto.getSystem());
        Academy academy = academyService.getOne(lqw);
        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(teacherDto, teacher);
        teacher.setAcademyId(academy.getId());
        this.save(teacher);
    }

    /**
     * 批量新增教师
     * @param list
     */
    @Override
    public void saveBatchTeacher(List list) {
        for (int i = 0; i<list.size(); i++) {
            saveTeacher((TeacherDto) list.get(i));
        }
    }

}
