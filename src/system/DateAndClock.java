package system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndClock extends UserBasic {
	
/*	check whether today's date is before or after the last opening date
	return true if today's date is before or equal from last opening date
	and return false if it's not  */
	public static boolean dateChecker() {
		String DbDateString = null;
		String todayDateString = null;
		Date DbDate = null;
    	Date todayDate = null;
		
//    	get today's date as string
		DateFormat dateFormat = new SimpleDateFormat((new DateFormatManager()).getDateFormat());
		todayDateString = dateFormat.format(Calendar.getInstance().getTime());
		
//		get last opening date from database as string
		String sql = "SELECT lastAccessDate \n"
					+ "FROM  Current_Status \n"
					+ "WHERE ID=1";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)){
			
			DbDateString = result.getString("lastAccessDate");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
//		convert both sting date to Date format
		try {
			DbDate = new SimpleDateFormat((new DateFormatManager()).getDateFormat()).parse(DbDateString);
			todayDate = new SimpleDateFormat((new DateFormatManager()).getDateFormat()).parse(todayDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
//		check date is before or after or equal
    	try {
			if (DbDate.compareTo(todayDate) > 0) {
			    return false;
			} else if (DbDate.compareTo(todayDate) < 0) {
				return true;
			} else if (DbDate.compareTo(todayDate) == 0) {
				return true;
			}
		} catch (Exception e) {}
    	
    	return true;
	}
	
	
	public static String timeNow() {
		DateFormat timeFormat = new SimpleDateFormat(new DateFormatManager().getTimeFormate());
		Calendar now = Calendar.getInstance();
    	String timeNow = timeFormat.format(now.getTime());
		return timeNow;
	}
	
	
	public static String getMonth() {
		DateFormat monthFormat = new SimpleDateFormat("MMM-yyyy");
		Calendar today = Calendar.getInstance();
    	String thisMonth = monthFormat.format(today.getTime());
		return thisMonth;
	}
	
	
	public void updateLastAccessDate() {
		String sql = "UPDATE Current_Status SET lastAccessDate = ? ";
		
//    	get today's date as string
		DateFormat dateFormat = new SimpleDateFormat((new DateFormatManager()).getDateFormat());
		String todayDate = dateFormat.format(Calendar.getInstance().getTime());
		
		try (Connection conn = connector();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, todayDate);
			
			pstmt.executeUpdate();
		} catch (Exception e) {}
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(dateChecker());
//		System.out.println(shortClock());
//		System.out.println(getMonth());
//	}
}
