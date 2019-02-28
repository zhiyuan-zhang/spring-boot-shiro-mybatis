package com.demo.shiro.common;


import com.demo.shiro.bean.ResultBean;
import com.demo.shiro.common.exceptions.NoPermissionException;
import com.demo.shiro.common.exceptions.TokenErrorException;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


/**
 * 代替AOP Exception
 */
@Log4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResultBean handle401(ShiroException e) {
        return new ResultBean(e.getMessage());
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResultBean handle401() {
        return new ResultBean( "Unauthorized");
    }

//    // 捕捉其他所有异常
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultBean globalException(HttpServletRequest request, Throwable ex) {
//        return new ResultBean(ex.getMessage());
//    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBean processException(NativeWebRequest request, Exception e) {

        ResultBean result = new ResultBean();

        if (e instanceof MethodArgumentTypeMismatchException) {
            result.setCode(ResultBean.CHECK_FAIL);
            result.setMsg("参数异常，请检查请求或者类型是否匹配");
        } else if (e instanceof MissingPathVariableException) {
            result.setMsg("参数异常，请检查请求或者类型是否匹配");
            result.setCode(ResultBean.CHECK_FAIL);
        } else if (e instanceof MissingServletRequestParameterException) {
            result.setMsg("参数异常，无法找到相对应的参数");
            result.setCode(ResultBean.CHECK_FAIL);
        } else if (e instanceof TokenErrorException) {
            result.setMsg("Token异常，请重新登录");
            result.setCode(ResultBean.TOKEN_ERROR);
        } else if (e instanceof NoHandlerFoundException) {
            result.setMsg("404：" + request.toString() + "地址不存在");
        } else {
            result.setCode(ResultBean.CHECK_FAIL);
            result.setMsg(e.getMessage());
        }
        log.error("------------------发现异常请查看！！！--------------------");
        //未知异常 ， 打印堆栈
        log.error(" error ", e);

        return result;
    }

    @ExceptionHandler(NoPermissionException.class)
    @ResponseBody
    public ResultBean processCheckException(NativeWebRequest request, NoPermissionException e) {
        return result(e);
    }

    public ResultBean result(Exception e) {
        ResultBean result = new ResultBean();

        result.setCode(ResultBean.CHECK_FAIL);
        result.setMsg(e.getMessage());
        log.error("------------------发现异常请查看！！！--------------------");
        log.error(" error ");

        return result;
    }

}