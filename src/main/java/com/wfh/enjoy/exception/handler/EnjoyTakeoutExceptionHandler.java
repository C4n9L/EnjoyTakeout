package com.wfh.enjoy.exception.handler;

import com.wfh.enjoy.exception.EnjoyTakeoutException;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author wfh
 * @create 2023/3/24 - 9:22
 */
@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class EnjoyTakeoutExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> addEmployeeException(SQLIntegrityConstraintViolationException ex){

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        log.info("可能发生了SQLIntegrityConstraintViolationException:{}",ex.getMessage());
        return Result.error("未知错误");

    }

    @ExceptionHandler(EnjoyTakeoutException.class)
    public Result<String> deleteCategoryException(EnjoyTakeoutException ex){
        return Result.error(ex.getMessage());
    }
}
