package com.sdau.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdau.common.R;
import com.sdau.dto.TeacherDto;
import com.sdau.entity.Academy;
import com.sdau.entity.Teacher;
import com.sdau.service.AcademyService;
import com.sdau.service.CourseService;
import com.sdau.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //@Controller+@ResponseBody
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AcademyService academyService;

    /**
     * 新增教师
     * @param teacherDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody TeacherDto teacherDto){
        log.info("教师信息为：{}",teacherDto);
        teacherService.saveTeacher(teacherDto);
        return R.success("新增教师成功");
    }

    /**
     * 根据id删除教师
     */
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id){
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id!=null,Teacher::getId,id);
        boolean flag = teacherService.remove(lqw);
        if(flag){
            return R.success("删除成功");
        }
        return R.success("删除失败");
    }

    /**
     * 根据id查询教师信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R<TeacherDto> getById(@PathVariable Long id){
        //查询教师
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id!=null,Teacher::getId,id);
        Teacher teacher = teacherService.getOne(lqw);
        TeacherDto teacherDto = new TeacherDto();
        //属性拷贝
        BeanUtils.copyProperties(teacher,teacherDto);
        //查询院系信息
        LambdaQueryWrapper<Academy> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(teacher.getAcademyId()!=null,Academy::getId,teacher.getAcademyId());
        Academy academy = academyService.getOne(lqw2);
        //信息完善
        teacherDto.setDtoName(academy.getName());
        teacherDto.setSystem(academy.getSystem());
        return  R.success(teacherDto);
    }

    /**
     * 修改教师信息
     * @param teacherDto
     * @return
     */
    @PutMapping("update")
    public R<String> update(@RequestBody TeacherDto teacherDto){
        //先删除
        teacherService.removeById(teacherDto.getId());
        //添加
        teacherService.saveTeacher(teacherDto);

        return R.success("修改教师信息成功");
    }


    /**
     * 教师信息分页查询+模糊查询(学院+系)
     * @param page
     * @param pageSize
     * @param teacherDto
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, TeacherDto teacherDto) {
        log.info("page={},pageSize={},name={}", page, pageSize, teacherDto.toString());

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();


        //执行查询
        teacherService.page(pageInfo, lqw);

        return R.success(pageInfo);
    }








}
