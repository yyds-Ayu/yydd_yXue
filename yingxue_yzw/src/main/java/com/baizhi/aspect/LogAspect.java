package com.baizhi.aspect;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


@Configuration  //配置交给工厂
@Aspect  //切面类
public class LogAspect {

    private static final Logger log1 = LoggerFactory.getLogger(LogAspect.class);
    @Resource
    HttpServletRequest request;

    @Resource
    LogService logService;
    //@Around("execution(* com.baizhi.serviceimpl.*.*(..)) && !execution(* com.baizhi.serviceimpl.*.query*(..))")
    @Around("@annotation(com.baizhi.annotation.AddLog)")
    public Object AddLog(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("--------------进入环绕通知-------------");
        Admin admin = (Admin)request.getServletContext().getAttribute("admin");

        //Date date = new Date();

        String name = proceedingJoinPoint.getSignature().getName();

        //获取方法的Signature对象
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();

        //获取方法
        Method method = signature.getMethod();

        //获取方法上的注解
        AddLog annotation = method.getAnnotation(AddLog.class);

        //获取注解上的属性值
        String value = annotation.value();
        String status = null;
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            status = "Success(成功)";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            status = "Error(失败)";
            throw new RuntimeException(throwable.getMessage());
        }finally {
            //Log log = new Log(UUIDUtil.getUUID(),admin.getUsername(),new Date(),name,status);
            Log log = new Log(null,admin.getUsername(), new Date(), name+"("+value+")", status);

            log1.info("管理员操作日志：",log);

            //日志入库
            logService.add(log);

        }
        return proceed;
    }
}
