/**
 * Project Name:springboot  
 * File Name:BaseRequest.java  
 * Package Name:com.jpq.springboot.vo  
 * Date:2018年7月17日下午5:31:22  
 * Copyright (c) 2018, jiangpeiquan@jpq.com jpq All Rights Reserved. 
 */

package com.hand.ssm.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ClassName:BaseRequest <br/>
 * Date: 2018年7月17日 下午5:31:22 <br/>
 * 
 * @author jiangpeiquan
 * @version
 * @since
 * @see
 */
public class BaseRequest<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String token;
	private String id;
	private T data;
	public BaseRequest (){
	}
	public BaseRequest (T data){
		this.data = data;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }
}
