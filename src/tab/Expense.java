package tab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import database.DatabaseConnection;
import operation.GlobalId;

public class Expense extends DatabaseConnection {
	
	public void saveExpenseData(Map<String, String> expenseData) {
		String sql = "INSERT INTO Expense(globalID, exTime, exDate, exMonth, exAmount, exDescription, \n"
				+ "exSector, exWalletBalanceBefore, exWalletBalanceAfter) \n"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		
		int globalId = GlobalId.getGlobalid();
		
		try (Connection conn = connector();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setLong(1,globalId);
			pstmt.setString(2, expenseData.get("exTime"));
			pstmt.setString(3, expenseData.get("exDate"));
			pstmt.setString(4, expenseData.get("exMonth"));
			pstmt.setString(5, expenseData.get("exAmount"));
			pstmt.setString(6, expenseData.get("exDescription"));
			pstmt.setString(7, expenseData.get("exSector"));
			pstmt.setString(8, expenseData.get("exWalletBalanceBefore"));
			pstmt.setString(9, expenseData.get("exWalletBalanceAfter"));
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
