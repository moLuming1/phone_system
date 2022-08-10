package com.sdau.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdau.common.R;
import com.sdau.dto.StudentDto;
import com.sdau.entity.Academy;
import com.sdau.entity.Student;
import com.sdau.entity.Teacher;
import com.sdau.service.AcademyService;
import com.sdau.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ResponseBody
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AcademyService academyService;

    /**
     * 新增学生信息
     * @param studentDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody StudentDto studentDto){
        log.info("学生信息为:{}",studentDto);
        Student student = new Student();
        //拷贝属性
        BeanUtils.copyProperties(studentDto,student);
        System.out.println(student);
        student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes()));
        LambdaQueryWrapper<Academy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(studentDto.getDtoName()!=null,Academy::getName,studentDto.getDtoName())
                .eq(studentDto.getSystem()!=null,Academy::getSystem,studentDto.getSystem());
        Academy academy = academyService.getOne(lqw);

        student.setAcademyId(academy.getId());

        studentService.save(student);
        return  R.success("新增学生信息成功");
    }

    @DeleteMapping("{id}")
    public  R<String> delete(@PathVariable Long id){
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id!=null,Student::getId,id);
        boolean flag = studentService.remove(lqw);
        if(flag){
            return R.success("删除成功");
        }
        return R.success("删除失败");
    }

}
