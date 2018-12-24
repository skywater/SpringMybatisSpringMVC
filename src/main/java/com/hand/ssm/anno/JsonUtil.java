/**
 * Project Name:springboot
 * File Name:JSONUtil.java
 * Package Name:com.jpq.springboot.util
 * Date:2018年7月20日下午5:40:01
 * Copyright (c) 2018, jiangpeiquan@linklogis.com 联易融 All Rights Reserved.
 */

package com.hand.ssm.anno;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * ClassName:JSONUtil <br/>
 * Date:2018年7月20日 下午5:40:01 <br/>
 * @author jiangpeiquan
 * @version
 * @since
 * @see
 */
public final class JsonUtil {
	private JsonUtil() {}
	
	/**
	 * 可以转换所有对象，包括list <br/>
	 * @author jiangpeiquan
	 * @param obj
	 * @return
	 */
	public static JsonObject toJsonObj(Object obj) {
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(toJson(obj));
		return jsonEl.getAsJsonObject();
	}
	
	/**
	 * 将JSON字符串反序列化成对象 <br/>
	 * @author jiangpeiquan
	 * @param json
	 * @param type new TypeToken<List<Cs>>() {}.getType()或者class
	 * @return
	 */
	public static <T> T toObject(String json, Type type) {
		return toObject(json, type, null);
	}
	
	/**
	 * 将JSON字符串反序列化成对象 <br/>
	 * @author jiangpeiquan
	 * @param json
	 * @param type new TypeToken<List<Cs>>() {}.getType()或者class
	 * @param dateFormat 好像只支持新增一种，默认已经支持多种时间格式
	 * @return
	 */
	public static <T> T toObject(String json, Type type, String dateFormat) {
		if (StringUtils.isBlank(json)) {
			return null;
		}

		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
		if(StringUtils.isNotBlank(dateFormat)) {
			builder.setDateFormat(dateFormat);
		}
		Gson gson = builder.create();
		// 注意，这里的Type实际是gson的TypeToken类，所以不能转换成type.getClass()写成通用
		// 但是Type可以兼容class，源码最终还是一致的！
		return gson.fromJson(json, type);
	}

	/**
	 * 对象转换成json格式字符串，默认时间格式yyyy-MM-dd h:mm:ss SSS <br/>
	 * @author jiangpeiquan
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return toJson(obj, "yyyy-MM-dd h:mm:ss SSS");
	}
	
	/**
	 * 对象转换成json格式字符串 <br/>
	 * @author jiangpeiquan
	 * @param obj
	 * @param dateFormat
	 * @return
	 */
	public static String toJson(Object obj, String dateFormat) {
		return toJson(obj, dateFormat, true);
	}

	/**
	 * 对象转换成json格式字符串 <br/>
	 * @author jiangpeiquan
	 * @param obj
	 * @param dateFormat
	 * @param isSerializeNulls
	 * @return
	 */
	public static String toJson(Object obj, String dateFormat, boolean isSerializeNulls) {
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
		if (StringUtils.isNotBlank(dateFormat)) {
			builder = builder.setDateFormat(dateFormat);
		}
		if (isSerializeNulls) { // true，字符串为null，返回null；false则不返回该字段
			builder = builder.serializeNulls();
		}
		Gson gson = builder.create();
		return gson.toJson(obj);
	}

	/**
	 * 将对象转换为包装好的的json格式字符串<p>
	 * {"code":200,"msg":"","data":"{"name":"name1"}"}
	 * 
	 * @param obj
	 * @return
	 */
	public static String encodeData(Object obj) {
		JsonObject json = new JsonObject();
		json.addProperty("code", 200);
		json.addProperty("msg", "");
		json.add("data", new JsonParser().parse(toJson(obj)));
		return json.toString();
	}

	/**
	 * 返回web端需要的页面模式<p>
	 * draw: 表示请求次数，data: 具体的数据对象数组； recordsTotal: 总记录数，recordsFiltered:
	 * 过滤后的总记录数，一般是相等的；
	 * 
	 * @param lst
	 * @return
	 */
	public static <T> String encodePage(List<T> lst, int totalSize, int filteredSize) {
		JsonObject json = new JsonObject();
		json.addProperty("recordsTotal", totalSize);
		json.addProperty("recordsFiltered", filteredSize);
		json.add("data", new JsonParser().parse(toJson(lst)));
		return json.toString();
	}

	/**
	 * 返回web端需要的页面模式
	 * 
	 * @param lst
	 * @return
	 */
	public static <T> String encodePage(List<T> lst) {
		return encodePage(lst, lst.size(), lst.size());
	}

	
	/**
	 * 将数组JSON字符串转为json对象数组，其实这方法一般没什么用，权当使用范例 <br/>
	 * @author jiangpeiquan
	 * @param json
	 * @return
	 */
	private static List<JsonObject> toJsons(String json) {
		JsonParser parser = new JsonParser();// 创建一个JsonParser
		JsonElement jsonEl = parser.parse(json);
		if(jsonEl.isJsonNull()) {
			return null;
		}
		List<JsonObject> rets = new ArrayList<JsonObject>();
		if(jsonEl.isJsonArray()) {
			// JsonArray.get(0).getAsJsonObject()
			JsonArray jsArray = jsonEl.getAsJsonArray();
			jsArray.forEach(elem -> {
				rets.add(elem.getAsJsonObject());
			});
//			rets = (List) jsonEl;// 虽然本质是ArrayList<JsonObject>，但是强转失败！
//			Iterator it = jsonEl.getAsJsonArray().iterator();
			// 强转仍然失败！！！java.util.ArrayList$Itr cannot be cast to java.util.List
			// 如果是java.util.ArrayList，应该可以成功！
//			rets =  (List)it;
		}
		else {
			// json对象，本质是个Map，可以get获取
			rets.add(jsonEl.getAsJsonObject());
		}
		return rets;
	}
}

 