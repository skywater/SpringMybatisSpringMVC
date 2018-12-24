/**
 * Project Name: springboot
 * File Name: ParamDealAspect.java
 * @date 2018年12月20日下午7:47:36
 * Copyright (c) 2018 linklogis.com All Rights Reserved.
 */

package com.hand.ssm.anno;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @date 2018年12月20日 下午7:47:36
 * @author jiangpeiquan
 * @version
 */
//@Component
//@Configuration
@Aspect
public class ParamDealAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     *  定义切入点为 @within(com.lls.framework.web.annotation.ParamDeal)
     */
    @Pointcut("@within(com.hand.ssm.anno.ParamDeal)")
    public void paramDealPointcut() {}
    
    @Before(value = "paramDealPointcut()")
    public void beforMethod(JoinPoint joinPoint) throws Throwable {    	
    	logger.info("beforMethod：{},{}", AspectUtil.getClassName(joinPoint), AspectUtil.getMethodName(joinPoint));
    }
    
    /**
     * ProceedingJoinPoint is only supported for around advice <br/>
     * @author jiangpeiquan
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "paramDealPointcut()")
    public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	logger.info("aroundMethod：{},{}", AspectUtil.getClassName(joinPoint), AspectUtil.getMethodName(joinPoint));
    	// joinPoint.getArgs()返回的数组：1、默认@RequestParam注解参数，则是["www","rrrr"]数组；2、@RequestBody注解参数，则是[{"token":null,"id":null,"data":null}] 
//    	logger.info("{},{}", joinPoint.getSignature().getDeclaringTypeName(), JsonUtil.toJson(joinPoint.getArgs()));
//    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        logger.info("{},{}", request.getSession().getAttributeNames(), 
//        	JsonUtil.toJson(request.getParameterNames()));
    }
    
    @After(value = "paramDealPointcut()")
    public void afterMethod(JoinPoint joinPoint) throws Throwable {
    	logger.info("afterMethod：{},{}", AspectUtil.getClassName(joinPoint), AspectUtil.getMethodName(joinPoint));
    	
    }
    
    @AfterReturning(value = "paramDealPointcut()")
    public void afterRetMethod(JoinPoint joinPoint) throws Throwable {
    	logger.info("afterRetMethod：{},{}", AspectUtil.getClassName(joinPoint), AspectUtil.getMethodName(joinPoint));
        
    }
    
    @AfterThrowing(value = "paramDealPointcut()")
    public void afterThrwMethod(JoinPoint joinPoint) throws Throwable {
    	logger.info("afterThrwMethod：{},{}", AspectUtil.getClassName(joinPoint), AspectUtil.getMethodName(joinPoint));
    	
    }
}

 