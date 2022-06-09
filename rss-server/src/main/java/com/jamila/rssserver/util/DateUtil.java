package com.jamila.rssserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static Date ConvertToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			try {
				d = sdf.parse(dateStr);
			} catch (ParseException e1) {
				sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ssXXX ", Locale.ENGLISH);
				try {
					d = sdf.parse(dateStr);
				} catch (ParseException e2) {
					sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss XXX", Locale.ENGLISH);
					try {
						d = sdf.parse(dateStr);
					} catch (ParseException e3) {
						sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
						try {
							d = sdf.parse(dateStr);
						} catch (ParseException e4) {
							e4.printStackTrace();
						}
					}
				}
			}
		}
		return d;
	}
	
	public static String dateToStr(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return sdf.format(d);
	}
	
	public static String getDate(String dateStr) {
		return dateToStr(ConvertToDate(dateStr));
	}
}
