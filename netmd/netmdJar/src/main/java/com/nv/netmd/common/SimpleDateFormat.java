package com.nv.netmd.common;



import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 *
 * @author Sreeram T G
 */
public class SimpleDateFormat implements DateFormat{
	private Pattern patterns;
	private String format;
	Map<String, String> map=fillMap();

	/**
	 * @param pattern
	 * @return 
	 */
	Map<String, String> fillMap(){
		Map<String, String>  map=new HashMap<String, String>();		
		map.put("yyyy-MM-dd HH:mm:ss", "%1$tY-%1$tm-%1$td %tT");
		map.put("yyyy-MM-dd HH:mm","%1$tY-%1$tm-%1$td %1$tI:%1$tM");
		map.put("yyyy-MM-dd", "%1$tY-%1$tm-%1$td");
		map.put("hh:mm:ss a","%1$tI:%1$tM:%1$tS %1$Tp");
		map.put("hh:mm", "%1$tI:%1$tM");
		map.put("hh:mm a", "%1$tI:%1$tM  %1$Tp");

		return map;
	}
	/**
	 * @param text
	 */
	public SimpleDateFormat(String text) {
		super();
		this.format=text.trim();
		if(map.get(text)==null){
			//error
			throw new NullPointerException();
		}
		patterns = Pattern.compile("((\\d{4})([-/]?)(\\d{2})([-/]?)(\\d{2})([ ]?))?([0-9]{2})?([:])?([0-9]{2})?([:])?([0-9]{2})?([ ])?([APap][mM])?$");
	}


	/**
	 * Format the date in to string 
	 * @param date
	 * @return string
	 */
	@Override
	public String format(Date date) {
		// TODO Auto-generated method stub
		if (date == null) {
			throw new NullPointerException();
		}
		String dateString=String.format(map.get(format), date);
		return dateString;
	}
	/**
	 * Here a string can be converted to date using regular expression 
	 * we detect the pattern 
	 * sample format which except this method is; 2012-02-08,2013-03-08 10:12, 2013-04-04 10:12:12 am,10:23
	 * @param format
	 * @return Date
	 */
	@Override
	public Date parse(String format) {
		if (format == null) {
			throw new NullPointerException();
		}
		Date date=null;
		int year=0;
		int month=0;
		int day=0;
		int hourOfDay=0;
		int minute=0;
		int second=0;
		Matcher matcher = patterns.matcher(format.trim());
		System.out.println("Group count  "+matcher.groupCount());

		if (matcher.find()) {

			if(matcher.group(2)!=null)
				year=Integer.parseInt(matcher.group(2));
			if(matcher.group(4)!=null)
				month=Integer.parseInt(matcher.group(4));
			if(matcher.group(6)!=null)
				day=Integer.parseInt(matcher.group(6));
			if(matcher.group(8)!=null)
				hourOfDay=Integer.parseInt(matcher.group(8));
			if(matcher.group(10)!=null)
				minute=Integer.parseInt(matcher.group(10));
			if(matcher.group(12)!=null)
				second=Integer.parseInt(matcher.group(12));
			matcher.group(14);

			Calendar cal=Calendar.getInstance();			
			cal.set(year, month, day, hourOfDay, minute, second);			

			if(matcher.group(14)!=null && (matcher.group(14).equals("PM")||matcher.group(14).equals("pm")))
			{
				cal.add(Calendar.HOUR, 12);
			}
			date=cal.getTime();
			System.out.println("string date="+date.toString());
			System.out.println("Date=="+date);
			return date;

		} 

		else {
			System.out.println("Match not found");
		}
		return date;
	}
}