package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import system.DateAndClock;

public class SettingsModel extends DateAndClock {
	
	public static String getUserFullName() {
		if (checkUserPresence()) {
			return userFullName();
		} else {
			return "No User";
		}
	}
	
	public static boolean isbKashActive() {
		String sql = "SELECT bKashActive FROM System_Settings WHERE ID = 1";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			if ((result.getString("bKashActive")).equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isRocketActive() {
		String sql = "SELECT RocketActive FROM System_Settings WHERE ID = 1";
		try (Connection conn = connector();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(sql)) {
			if ((result.getString("RocketActive")).equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
