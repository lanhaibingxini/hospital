package com.ujiuye.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateUtil {
	//字符串转时间日期
	public static Date stringToDate(String str) {
		Date date = null;
		if(str != null && !"".equals(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	//时间日期转字符串
	public static String dateToString(Date date) {
		String str = null;
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = sdf.format(date);
		}
		return str;
	}
}
