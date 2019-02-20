package com.yunmai.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 将object转换成json
	 * 
	 * @param object
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJackson(Object object)
			throws JsonGenerationException, JsonMappingException, IOException {
		return objectMapper.writeValueAsString(object);

	}

	/**
	 * 将json 字符串转换成对象，目标对象不传如果为队列则会为LinkList或者LinkMap，详情见api文档
	 * 
	 * @param result
	 * @param className
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jacksonToBean(String result, Class<?> className)
			throws JsonGenerationException, JsonMappingException, IOException {

		return objectMapper.readValue(result, className);
	}
	
	/**
	 * 将对象转换为json串
	 * <br>author：ywt
	 * <br>date：2012-6-27 上午10:55:57
	 * <br>version：V1.0.0
	 * @param object
	 * @return：
	 * <br>ModifyRecord：
	 * <br>1、ywt - 2012-6-27 上午10:55:57 ：
	 */
	public static String object2json(Object object)
	{
		try {
			JSONObject jsonObject = JSONObject.fromObject(object);       
			String json = jsonObject.toString();
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       

		return "";
	}
}
