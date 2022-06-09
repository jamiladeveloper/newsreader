package com.jamila.rssserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		Date d = ConvertToDate("Thu, 09 Jun 2022 13:00:00 +0000");
		System.out.println(d);
	}
	
	public static Date ConvertToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss XXX", Locale.ENGLISH);
		Date d = null;
		try {
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			try {
				d = sdf.parse(dateStr);
			} catch (ParseException e1) {
				sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss XXXX", Locale.ENGLISH);
				try {
					d = sdf.parse(dateStr);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}
		}
		return d;
	}

}
