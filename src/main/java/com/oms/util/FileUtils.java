package com.oms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
	/**
	 * 获取文件扩展名(返回小写)
	 * @param fileName 文件名
	 * @return 例如：test.jpg  返回：  jpg
	 */
	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1) || (fileName.lastIndexOf(".") == fileName.length() - 1)) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
	
	public static String getName(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	public static String getDir(){
		String year = new SimpleDateFormat("yyyy").format(new Date()); 
        String month = new SimpleDateFormat("MM").format(new Date()); 
        String day = new SimpleDateFormat("dd").format(new Date()); 
        return "/"+year+"/"+month+"/"+day+"/";
	}
}
