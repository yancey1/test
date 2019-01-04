/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.joseph.provider.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.joseph.api.enums.PublicCode;
import com.joseph.api.enums.TargetEnum;
import com.joseph.api.exceptions.ConsumerException;
import com.joseph.api.util.result.ResultUtil;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * ServiceMonitorAspect
 *
 * @author zhutou
 * @date 2017/8/1
 */
@Component
@Aspect
public class ServiceMonitorAspect {

    Logger logger = LoggerFactory.getLogger(ServiceMonitorAspect.class);

    @Value("${method.timeout}")
    private int longTime;

    /**
     * 对超时的service的记录日志
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(public * com.joseph.provider.service..*.*(..))"
            + "&& !execution(* com.joseph.provider.tis..*(..)) "
            + "|| execution(public * com.joseph.provider..*.*Facade.*(..))")
    public Object aroundAll(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp, longTime);
    }

    /**
     * 记录方法的时间，捕获异常
     *
     * @param pjp      ProceedingJoinPoint
     * @param longTime 超时需记录日志的时间
     * @return 执行结果
     * @throws Throwable 异常
     */
    private Object around(ProceedingJoinPoint pjp, int longTime) throws Throwable {
        StopWatch clock = new StopWatch();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String title = className + "." + methodName;
        Object obj = null;
        try {
            clock.start(); //计时开始
            obj = pjp.proceed();
            clock.stop();  //计时结束
        } catch (ConsumerException e) {
            logger.error(TargetEnum.METHOD.getName() + ":" + title, e);
            return ResultUtil.failResult(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(TargetEnum.METHOD.getName() + ":" + title, e);
            return ResultUtil.failResult(PublicCode.MULTI_101550.getCode(), PublicCode.MULTI_101550.getMessage());
        }
        long time = clock.getTime();
        if (time >= longTime) {
            StringBuilder sb = fillParam(pjp);
            sb.append(time);
            logger.error(TargetEnum.METHOD.getName() + ":" + title + ",params:" + sb.toString(), "cost_time:", time);
        }
        return obj;
    }

    private StringBuilder fillParam(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().getName();
        StringBuilder sb = new StringBuilder(200);
        Object[] params = pjp.getArgs();
        sb.append(pjp.getSignature().getDeclaringTypeName());
        sb.append(" ");
        sb.append(methodName);
        sb.append("(");
        if (params != null && params.length > 0) {
            StringBuilder sbParam = new StringBuilder();
            for (Object param : params) {
                sbParam.append(",");
                if (param instanceof Serializable) {
                    /**
                     * 此处不能直接写JSON.toJSONString(param)，mq消息体序列化会出错
                     */
                    sbParam.append(JSON.toJSONString(param, SerializerFeature.IgnoreNonFieldGetter));
                } else {
                    sbParam.append(param);
                }
            }
            sb.append(sbParam.substring(1));
        }
        sb.append(")");
        return sb;
    }

}
