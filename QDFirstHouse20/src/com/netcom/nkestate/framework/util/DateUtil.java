package com.netcom.nkestate.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.netcom.nkestate.system.bo.SystemBO;

/**
 * 日期工具类
 * @author 刀斌
 */
public class DateUtil {
	
	public static Date afterDays(Date date,int count) {
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date) ;
		calendar.add(Calendar.DAY_OF_MONTH, count);
		return calendar.getTime();		
	}
	
	public static Date afterMonths(Date date,int count) {
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date) ;
		calendar.add(Calendar.MONTH, count);
		return calendar.getTime();		
	}
	
	public static Date afterYears(Date date,int count) {
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date) ;
		calendar.add(Calendar.YEAR, count);
		return calendar.getTime();		
	}
	
	
	static SimpleDateFormat df = null;
	static SimpleDateFormat df2 = null;
	
	public static String formatDateTime(Date date) {
		if(date==null) return "";
		if(df == null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		}		
		return df.format(date);
	}
	
	public static String formatDate(Date date) {
		if(date==null) return "";
		if(df2 == null) {
			df2 = new SimpleDateFormat("yyyy-MM-dd");
			df2.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		}		
		return df2.format(date);
	}
	
	public static String format(Date date,String patten) {
		if(date==null) return "";
		SimpleDateFormat sdf = new SimpleDateFormat(patten);	
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(date);
	}

	/**
	 * 功能描述：日期差异小时
	 * @param endDate
	 * @param currentDate
	 * @return 差异小时
	 */
	public static long differentHour(Date endDate, Date currentDate) {
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);
		
		long minutes = (end.getTimeInMillis() - now.getTimeInMillis())/(60*1000);
		long hours = minutes/60;
		
		return hours;		
	}

	/**
	 * 功能描述：日期差异天数
	 * @param endDate
	 * @param currentDate
	 * @return 差异天数
	 */
	public static long differentDay(Date endDate, Date currentDate) {
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);

		long minutes = (end.getTimeInMillis() - now.getTimeInMillis()) / (60 * 1000);
		long days = minutes / (60 * 24);

		return days;
	}

	/**
	 * 功能描述：日期差异月数
	 * @param endDate
	 * @param currentDate
	 * @return 差异月数
	 */
	public static int differentMonth(Date date1, Date date2){     
	       int iMonth = 0;     
	       int flag = 0;     
	       try{     
	           Calendar objCalendarDate1 = Calendar.getInstance();     
	           objCalendarDate1.setTime(date1);     
	    
	           Calendar objCalendarDate2 = Calendar.getInstance();     
	           objCalendarDate2.setTime(date2);     
	    
	           if (objCalendarDate2.equals(objCalendarDate1))     
	               return 0;     
	           if (objCalendarDate1.after(objCalendarDate2)){     
	               Calendar temp = objCalendarDate1;     
	               objCalendarDate1 = objCalendarDate2;     
	               objCalendarDate2 = temp;     
	           }     
	           if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))     
	               flag = 1;     
	    
	           if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))     
	               iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))     
	                       * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)     
	                       - objCalendarDate1.get(Calendar.MONTH);     
	           else    
	               iMonth = objCalendarDate2.get(Calendar.MONTH)     
	                       - objCalendarDate1.get(Calendar.MONTH) - flag; 
	           objCalendarDate1.add(Calendar.MONTH, iMonth);
	           objCalendarDate1.get(Calendar.DATE);
	           long date1Day=objCalendarDate1.getTimeInMillis()/(24*60*60*1000);
	           long date2Day=objCalendarDate2.getTimeInMillis()/(24*60*60*1000);
	           if(date1Day<date2Day){
	        	   iMonth+=1;
	           }
	    
	       } catch (Exception e){     
	        e.printStackTrace();     
	       }     
	       return iMonth;     
	   }

	/**
	 * 功能描述：字符转换为日期
	 * @param strDate
	 * 格式：yyyy-mm-dd
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String strDate) throws Exception {
		if(strDate == null || strDate.trim().equals(""))
			return null;
		String split = "-";
		if(strDate.indexOf("/")>0) split = "/";
		int year = Integer.parseInt(strDate.substring(0,strDate.indexOf(split)));
		int month = Integer.parseInt(strDate.substring(strDate.indexOf(split)+1,strDate.lastIndexOf(split)));
		int day = Integer.parseInt(strDate.substring(strDate.lastIndexOf(split)+1));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0, 0);
		
		//DateFormat df = DateFormat.getDateInstance();
		//return df.parse(strDate);
		
		return calendar.getTime();
	}


	/**
	 * 功能描述：字符转换为日期
	 * @param strDateTime
	 * 格式：yyyy-mm-dd hh24:mi:ss
	 * @return
	 * @throws Exception
	 */
	public static Date parseDateTime(String strDateTime) throws Exception {
		if(strDateTime == null || strDateTime.trim().equals(""))
			return null;
		String split = "-";
		if(strDateTime.indexOf("/") > 0)
			split = "/";
		String strDate = strDateTime.substring(0, strDateTime.indexOf(" "));
		String strTime = strDateTime.substring(strDateTime.indexOf(" ")).trim();
		
		int year = Integer.parseInt(strDate.substring(0, strDate.indexOf(split)));
		int month = Integer.parseInt(strDate.substring(strDate.indexOf(split) + 1, strDate.lastIndexOf(split)));
		int day = Integer.parseInt(strDate.substring(strDate.lastIndexOf(split) + 1));
		
		split = ":";
		int hours = Integer.parseInt(strTime.substring(0, strTime.indexOf(split)));
		int minutes = Integer.parseInt(strTime.substring(strTime.indexOf(split) + 1, strTime.lastIndexOf(split)));
		int seconds = Integer.parseInt(strTime.substring(strTime.lastIndexOf(split) + 1));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hours, minutes, seconds);

		return calendar.getTime();
	}

	/**
	 * 功能描述：字符转换为日期
	 * @param strDateTime
	 * 格式：yyyymmddhh24:mi:ss
	 * @return
	 * @throws Exception
	 */
	public static Date parseDateTime2(String strDateTime) throws Exception {
		if(strDateTime == null || strDateTime.trim().equals(""))
			return null;
		SimpleDateFormat datef = new SimpleDateFormat("yyyyMMddHHmmss");


		return datef.parse(strDateTime);
	}

	/**
	 * 功能描述：日期字符转换
	 * @param strDateTime
	 * 格式：yyyymmdd
	 * @return yyyy-mm-dd
	 * @throws Exception
	 */
	public static String parseDateTime3(String strDateTime) {
		if(strDateTime == null || strDateTime.trim().equals(""))
			return null;
		try{
			if(strDateTime.length() != 8){
				return strDateTime;
			}
			SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");
			Date date = datef.parse(strDateTime);

			SimpleDateFormat datef2 = new SimpleDateFormat("yyyy-MM-dd");


			return datef2.format(date);
		}catch (Exception e){
			return null;
		}
	}

	
	//取系统时间
	public static Date getSysDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //取北京时间
		Date date = calendar.getTime();		
		return date;
	}
	//取数据库的时间  仅预售许可使用
	public static Date getDBSysDate() {
		SystemBO  bo = new SystemBO();
		try {
			return bo.getDBSysDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：获得当前月的第一天和最后一天
	 * @return
	 */
	public static Map<String,String> getMonth(){
		Calendar curCal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		//获取当月最后一天
		curCal.set(Calendar.DATE, 1);
		curCal.roll(Calendar.DATE, -1);
		Date endTime = curCal.getTime();
		String eTime = datef.format(endTime);

		//获取当月第一天
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = curCal.getTime();
		String sTime = datef.format(beginTime);
		Map<String,String> map = new HashMap<String,String>();
		map.put("sTime", sTime);
		map.put("eTime", eTime);
		return map;
	}
	
	
	public static String getSysDateToString(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //取北京时间
		Date date = calendar.getTime();		
		return datef.format(calendar.getTime());
	}
	
	public static int getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); //取北京时间
	   return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：日期转化成年月日
	 * @param strDate
	 * 格式：yyyy-mm-dd
	 * @return
	 * @throws Exception
	 */
	public static String fromatDate (String strDate) throws Exception {
		if(strDate == null || "".equals(strDate))
			return null;
		String split = "-";
		if(strDate.indexOf("/")>0) split = "/";
		int year = Integer.parseInt(strDate.substring(0,strDate.indexOf(split)));
		int month = Integer.parseInt(strDate.substring(strDate.indexOf(split)+1,strDate.lastIndexOf(split)));
		int day = Integer.parseInt(strDate.substring(strDate.lastIndexOf(split)+1));
		return year + "年" + month + "月" + day + "日";
	}

	public static String getSysDateYYYYMMDD() {
		SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");
		Date date = DateUtil.getSysDate();
		return datef.format(date);
	}

	public static String getSysDateHHMMSS() {
		SimpleDateFormat datef = new SimpleDateFormat("HHmmss");
		Date date = DateUtil.getSysDate();
		return datef.format(date);
	}

	public static String parseDateTime4(String strDateTime) {
		if(strDateTime == null || strDateTime.trim().equals(""))
			return null;
		try{
			if(strDateTime.length() != 14){
				return strDateTime;
			}
			SimpleDateFormat datef = new SimpleDateFormat("yyyyMMddhhmmss");
			Date date = datef.parse(strDateTime);

			SimpleDateFormat datef2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


			return datef2.format(date);
		}catch (Exception e){
			return null;
		}
	}

	/**
	 * 功能描述：日期差异分钟数
	 * @param endDate
	 * @param currentDate
	 * @return 差异分钟
	 */
	public static long differentMinute(Date endDate,Date currentDate) {
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);

		long minutes = (end.getTimeInMillis() - now.getTimeInMillis()) / (60 * 1000);

		return minutes;
	}
} 
