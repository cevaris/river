package util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class Utils {
	
	private static final GregorianCalendar CALENDAR = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");

	public static String getJulianDate(){
        return String.valueOf(CALENDAR.get(GregorianCalendar.DAY_OF_YEAR));
	}
	
	public static String getDate(){
		return DATE_FORMAT.format(new Date());
	}
	
	public static long getTimestamp(){
		return System.currentTimeMillis()/1000L;
	}
}
