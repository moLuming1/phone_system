package com.sdau.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdau.common.R;
import com.sdau.entity.Admin;

import com.sdau.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    /**
     * 管理员登录处理
     * @param request
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {
        //1.将页面提交的密码进行MD5加密
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据用户名查数据库
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<Admin>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin one = adminService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if (one == null) {
            return R.error("登录失败");
        }

        //4.密码比对，不一致返回登录失败结果
        if (!one.getPassword().equals(password)) {
            return R.error("登录失败");
        }

        //6.登录成功，将员工id存入session并返回登录成功结果
        request.getSession().setAttribute("admin", one.getId());
        return R.success(one);
    }

    /**
     * 管理员退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理session中保存的当前登录员工id
        request.getSession().removeAttribute("admin");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Admin admin) {
        log.info("新增员工,管理员信息为{}", admin.toString());

        //设置初始密码为123456，需要进行MD5加密
        admin.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());

        //获取当前登录用户的id
        Long adminId = (Long) request.getSession().getAttribute("admin");

        admin.setCreateUser(adminId);
        admin.setUpdateUser(adminId);

        adminService.save(admin);

        return R.success("新增管理员成功");

    }

    /**
     * 管理员信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={},pageSize={},name={}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<Admin>();
        //添加过滤条件
        lqw.like(!StringUtils.isEmpty(name), Admin::getName, name);
        //添加排序条件
        lqw.orderByDesc(Admin::getUpdateTime);
        //执行查询
        adminService.page(pageInfo, lqw);
        //System.out.println("1111111111111");
        return R.success(pageInfo);
    }


    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Admin> getById(@PathVariable int id) {
        log.info("根据id查询员工信息,id为: {}", id);
        Admin admin = adminService.getById(id);
        return R.success(admin);

    }




}


