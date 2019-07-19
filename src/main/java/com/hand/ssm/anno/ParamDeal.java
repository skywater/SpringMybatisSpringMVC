/**
 * Project Name: dbass-web
 * File Name: ParamDeal.java
 * @date 2018年12月24日上午10:58:41
 * Copyright (c) 2018 .com All Rights Reserved.
 */

package com.hand.ssm.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @date 2018年12月24日 上午10:58:41
 * @author jiangpeiquan
 * @version
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamDeal {
	String value() default "";
}

 