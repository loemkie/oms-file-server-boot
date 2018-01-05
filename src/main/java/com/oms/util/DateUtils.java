package com.oms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String formatDate(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);
		return ctime;
	}
}
