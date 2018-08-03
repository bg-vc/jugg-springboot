package com.jugg.vince.springboot.common.aop;

import com.jugg.vince.springboot.common.enums.ResultCode;
import com.jugg.vince.springboot.common.model.Result;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 服务入口日志切面
 * @author: Vince
 * @Date: 2017年11月7日 下午2:40:31
 */
@Aspect
@Component("logAspect")
@Order(0)
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.jugg.vince.springboot.web..*.*(..))")
    public void logPointCut() {
    }

    @Around(value = "logPointCut()")
    public Object logOperate(ProceedingJoinPoint joinPoint) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

        log.info("[log aop] begin;method:{}", methodName);
        try {
            result = joinPoint.proceed();
            log.info("[log aop] end;method:{},result:{},耗时:{}",
                    methodName, result, stopWatch.getTime());
        } catch (Throwable e) {
            log.error("[log aop] exception;method:{};耗时:{}",
                    methodName, stopWatch.getTime());
            result = new Result<String>(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
