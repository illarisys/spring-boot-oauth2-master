package com.rd.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger {

	private static final Log log = LogFactory.getLog(Logger.class);
	
	public static void  error(Exception e){
		log.error(e.getMessage(), e);
	}
	
	public static void  info(Exception e){
		log.info(e.getMessage(), e);
	}
	
	public static void  debug(Exception e){
		log.debug(e.getMessage(), e);
	}
	
	public static void  error(String e){
		log.error(e);
	}
	
	public static void  info(String e){
		log.info(e);
	}
	
	public static void  debug(String e){
		log.debug(e);
	}
	
	public static void  debug(String e, String f){
		log.debug(e + " " +  f);
	}
}
