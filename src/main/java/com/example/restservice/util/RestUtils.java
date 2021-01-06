package com.example.restservice.util;

import java.util.UUID;

import org.springframework.context.ApplicationContext;

public class RestUtils {
	
	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext ctx){
		applicationContext = ctx;
	}
	
	public static Object getBean(String name){
		if (applicationContext.containsBean(name)){
			return applicationContext.getBean(name);
		}
		return null;
	}
	
	public static <T> T getBean(Class<T> clz){
		return applicationContext.getBean(clz);
		
	}

	public static String generateUUID(){
		return UUID.randomUUID().toString();
		//return UUID.randomUUID().toString().replaceAll("-", "");
	}

}

