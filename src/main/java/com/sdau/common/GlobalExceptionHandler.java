package com.sdau.common;


import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 全局异常处理器
 */
//拦截类上加了restcontroller注解的controller
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")) {
            String[] spilt = ex.getMessage().split(" ");
            String msg = spilt[2] + "已存在";
            return R.error(msg);
        }

        return R.error("失败了");
    }


    /**
     * 异常处理方法
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class) //设置捕获异常类别
    public R<String> exceptionHandler(CustomException ex) {
        log.error(ex.getMessage());
        System.out.println("1111111111111");
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public R<String> errorHandler(InvalidDefinitionException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }


}
