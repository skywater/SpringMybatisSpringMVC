/**
 * Project Name: springboot
 * File Name: JoinPointUtil.java
 * @date 2018年12月20日下午8:49:06
 * Copyright (c) 2018 linklogis.com All Rights Reserved.
 */

package com.hand.ssm.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @date 2018年12月20日 下午8:49:06
 * @author jiangpeiquan
 * @version
 */
public final class AspectUtil {
    private final static Logger logger = LoggerFactory.getLogger(AspectUtil.class);

	/**
	 * 获取注解类 <br/>
	 * @author jiangpeiquan
	 * @param joinPoint
	 * @param clazz
	 * @return
	 */
	public static <T extends Annotation> T getAnnotation(JoinPoint joinPoint, Class<T> clazz) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(clazz);
	}

	/**
	 * 获取当前调用方法名 <br/>
	 * @author jiangpeiquan
	 * @param joinPoint
	 * @return
	 */
	public static String getMethodName(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
	}
	
	/**
	 * 获取当前类名 <br/>
	 * @author jiangpeiquan
	 * @param joinPoint
	 * @return
	 */
	public static String getClassName(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getName();
	}
	
    /**
     * 返回切面对应调用方法参数
     * @param joinPoint 切面连接点
     * @return String 接口的参数及对应值
     */
    public static String getMethodParams(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //查找相应方法
        String[] parameterNames = methodSignature.getParameterNames(); //查找相应方法参数名称
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                Object param = joinPoint.getArgs()[i];
                if (param == null || param instanceof Map) {
                    continue;
                }
                if (i > 0) {
                    sb.append(",");
                }
                boolean isSimple = BeanUtils.isSimpleValueType(param.getClass());
                if (isSimple) {
                    sb.append(parameterNames[i]).append(":").append(param);
                } else {
                    sb.append(parameterNames[i]).append(":").append(JsonUtil.toJson(param));
                }
            }
        }
        return sb.toString();
    }
}

 