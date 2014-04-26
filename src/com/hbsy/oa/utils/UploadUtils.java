package com.hbsy.oa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

public class UploadUtils {
	
	public static String saveUploadFile(File upload){
		//鎶婃棩鏈熸牸寮忓寲鎴愬瓧绗︿覆鐨勪竴涓府鍔╃被 
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		/*
		 * 寰楀埌upload鏂囦欢澶圭殑缁濆璺緞
		 * ServletActionContext.getServletContext()
		 * =
		 * C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\itcastoa823\WEB-INF/upload/2012\02\16\aaaaadfasdf
		 */
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		//鎶婃棩鏈熺被鍨嬫牸寮忓寲涓�/yyyy/MM/dd/"杩欑褰㈠紡鐨勫瓧绗︿覆
		String subPath = sdf.format(new Date());
		//濡傛灉鏂囦欢澶逛笉瀛樺湪锛屽氨鍒涘缓鏂囦欢澶�
		File dir = new File(basePath+subPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		//String path = basePath+"/"+this.uploadFileName;
		//UUID.randomUUID().toString()鑳藉淇濊瘉鍚嶅瓧鐨勫敮涓��
		String path = basePath+subPath+UUID.randomUUID().toString();
		File dest = new File(path);
		//鎶婃枃浠剁Щ鍔ㄥ埌dest澶�
		upload.renameTo(dest);
		return path;
	}
}
