package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.demo.constants.DemoConstants;

public class CommonUtility {

	/**
	 * Setting date as 00:00:00
	 * @param inputDate
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDate(String inputDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DemoConstants.MM_DD_YYYY);
		Date date = formatter.parse(inputDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

	/**
	 * Setting date as 23:59:59
	 * @param inputDate
	 * @return
	 * @throws Exception
	 */
	public static Date getEndDate(String inputDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(DemoConstants.MM_DD_YYYY);
		Date date = formatter.parse(inputDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

}
