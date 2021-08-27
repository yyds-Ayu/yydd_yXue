package com.baizhi.aspect;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import com.baizhi.util.UUIDUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Configuration  //配置交给工厂
@Aspect  //切面类
public class LogAspect {

    @Resource
    HttpServletRequest request;
    //@Around("execution(* com.baizhi.serviceimpl.*.*(..)) && !execution(* com.baizhi.serviceimpl.*.query*(..))")
    @Around("@annotation(com.baizhi.annotation.AddLog)")
    public Object AddLog(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("--------------进入环绕通知-------------");
        Admin admin = (Admin)request.getServletContext().getAttribute("admin");

        //Date date = new Date();

        String name = proceedingJoinPoint.getSignature().getName();

        String status = null;
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            status = "Success(成功)";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            status = "Error(失败)";
        }

       Log log = new Log(UUIDUtil.getUUID(),admin.getUsername(),new Date(),name,status);
        return proceed;

    }
}
