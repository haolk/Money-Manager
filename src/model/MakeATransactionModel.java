package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import operation.BankIssue;
import operation.ComboboxList;
import operation.GlobalId;
import system.DateAndClock;
import system.UnitConverter;
import tab.Borrow;
import tab.Lend;

public class MakeATransactionModel extends DateAndClock {
	
	private final static int maxTextSize = 100;

	public int letterCount(String text) {
		return maxTextSize - text.length();
	}
	
	
	public boolean amountIsZero(String amount) {
		try {
			if (UnitConverter.stringToDouble(amount)<=0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}

	}

	
	public boolean validAmount(String amount) {
		if (amount.matches("[0-9]{1,7}(\\.[0-9]{1,2}){0,7}")) {
			return true;
		} else if (amount.matches("[0-9]{1,7}\\.")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String getWalletBalance() {
		return UnitConverter.longToString(currentWalletBalance());
	}
	
	
	public String updatedbKashBalance(String amount, String nature) {
		long amountlong = UnitConverter.stringToLong(amount);
		long charge = new BankIssue().bKashChargeCalculate(amountlong, nature);
		long dbBkashBalance = currentbKashBalance();
		
		if (nature.equals("Cash In")) {
			String newBalance = UnitConverter.longToString(dbBkashBalance+(amountlong-charge));
			return newBalance;
		} else {
			String newBalance = UnitConverter.longToString(dbBkashBalance-(amountlong+charge));
			return newBalance;
		}
	}
	

	public String updatedRocketBalance(String amount, String method, String nature) {
		long amountlong = UnitConverter.stringToLong(amount);
		long charge = new BankIssue().rocketChargeCalculate(amountlong, method, nature);
		long dbRocketBalance = currentRocketBalance();
		
		if (method.equals("Cash In")) {
			String newBalance = UnitConverter.longToString(dbRocketBalance+(amountlong-charge));
			return newBalance;
		} else {
			String newBalance = UnitConverter.longToString(dbRocketBalance-(amountlong+charge));
			return newBalance;
		}
		
	}
	
	
	public String bkashBnkCharge(String amount, String nature) {
		long amountlong = UnitConverter.stringToLong(amount);
		long charge = (new BankIssue()).bKashChargeCalculate(amountlong, nature);
		return UnitConverter.longToString(charge);
	}
	
	
	public String rocketBnkCharge(String amount, String method, String nature) {
		long amountlong = UnitConverter.stringToLong(amount);
		long charge = (new BankIssue()).rocketChargeCalculate(amountlong, method, nature);
		return UnitConverter.longToString(charge);
	}
	
	
	public ObservableList<String> getMethod() {
		ObservableList<String> list = FXCollections.observableArrayList((new ComboboxList()).getMethodList());
		return list;
	}
	
	
	public int globalIdToSave() {
		return GlobalId.getGlobalid();
	}
	
	
	public String timeToSave() {
		return timeNow();
	}
	
	
	public String monthToSave(LocalDate localdate) {
		java.util.Date date = java.sql.Date.valueOf(localdate);
		DateFormat monthFormat = new SimpleDateFormat("MMM-yy");
    	String Month = monthFormat.format(date);
		return Month;
	}
	
	
	public String yearToSave(LocalDate localdate) {
		java.util.Date date = java.sql.Date.valueOf(localdate);
		DateFormat monthFormat = new SimpleDateFormat("yyyy");
    	String year = monthFormat.format(date);
		return year;
	}
	
	
		
//////////////////////////////////////////// Get Money Function  ////////////////////////////////////////////
//---------------------------------------------------------------------------------------------------------//
	
	public ObservableList<String> gmGetSource() {
		ObservableList<String> list = FXCollections.observableArrayList((new ComboboxList()).getSourceList());
		return list;
	}
	
	
	public String gmWalletBalanceAfter(String amount) {
		long dbBalance = currentWalletBalance();
		long typedBalance = UnitConverter.stringToLong(amount);
		String updateBalance = UnitConverter.longToString(dbBalance + typedBalance);
		return updateBalance;
	}
	
	
////////////////////////////////////////////   Expense Function  ////////////////////////////////////////////
//---------------------------------------------------------------------------------------------------------//
	
	public ObservableList<String> exGetSector() {
		ObservableList<String> list = FXCollections.observableArrayList((new ComboboxList()).getSectorList());
		return list;
	}
	
	
	public String exWalletBalanceAfter(String amount) {
		long dbBalance = currentWalletBalance();
		long typedBalance = UnitConverter.stringToLong(amount);
		String updateBalance = UnitConverter.longToString(dbBalance - typedBalance);
		return updateBalance;
	}
	
	
////////////////////////////////////////////      Lend Function  ////////////////////////////////////////////
//---------------------------------------------------------------------------------------------------------//
	
	public ObservableList<String> boGetBorrowType() {
		ObservableList<String> list = FXCollections.observableArrayList("Money Take","Return Borrowed Money");
		return list;
	}
	
	
	public ObservableList<String> boGetRepayPersonName() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getBoRepayPersonNameList());
		return list;
	}
	
	
	public String getTotalBorrowTk() {
		return UnitConverter.longToString(totalBorrowTk());
	}
	
	
//	public String boBkBalanceAfter(String amountWithCharge, String bnkCharge, String borrowType) {
//		if (borrowType.equals("Money Take")) {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL - bnkChargeL);
//			long dbBkBalance = currentbKashBalance();
//			return UnitConverter.longToString(dbBkBalance + amountWithoutCharge);
//		} else {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL + bnkChargeL);
//			long dbBkBalance = currentbKashBalance();
//			return UnitConverter.longToString(dbBkBalance - amountWithoutCharge);
//		}
//	}
//	
//	
//	public String boRocBalanceAfter(String amountWithCharge, String bnkCharge, String borrowType) {
//		if (borrowType.equals("Money Take")) {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL - bnkChargeL);
//			long dbRocBalance = currentRocketBalance();
//			return UnitConverter.longToString(dbRocBalance + amountWithoutCharge);
//		} else {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL + bnkChargeL);
//			long dbRocBalance = currentRocketBalance();
//			return UnitConverter.longToString(dbRocBalance - amountWithoutCharge);
//		}
//	}
	
	
	public String updatedTotalBorrowTk(String amountToAdd, String borrowType) {
		if (borrowType.equals("Money Take")) {
			long dbBalance = UnitConverter.stringToLong(getTotalBorrowTk());
			long amountL = UnitConverter.stringToLong(amountToAdd);
			return UnitConverter.longToString(dbBalance + amountL);
		} else {
			long dbBalance = UnitConverter.stringToLong(getTotalBorrowTk());
			long amountL = UnitConverter.stringToLong(amountToAdd);
			return UnitConverter.longToString(dbBalance - amountL);
		}
	}
	
	
	public boolean boRepayValidation(String amount, String personName) {
		long typedAmount = UnitConverter.stringToLong(amount);
		long personBorrowedAmount = UnitConverter.stringToLong(new Borrow().boRepayPersonBorrowedAmount(personName));
		if(typedAmount>personBorrowedAmount) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean boisTypedAmountLessThanBorrowed(String typedAmount, String personName) {
		long typed = UnitConverter.stringToLong(typedAmount);
		long personBorrowedAmount = UnitConverter.stringToLong(new Borrow().boRepayPersonBorrowedAmount(personName));
		if(typed<personBorrowedAmount) {
			return true;
		} else {
			return false;
		}
	}
	
//////////////////////////// lend //////////////////////////////
	
	public ObservableList<String> leGetLendType() {
		ObservableList<String> list = FXCollections.observableArrayList("Give Money","Take Back Lended Money");
		return list;
	}
	
	
	public ObservableList<String> leGetRepayPersonName() {
		ObservableList<String> list = FXCollections.observableArrayList(new ComboboxList().getLeRepayPersonNameList());
		return list;
	}
	
	
	public String getTotalLendTk() {
		return UnitConverter.longToString(totalLendTk());
	}
	
	
//	public String leBkBalanceAfter(String amountWithCharge, String bnkCharge, String LendType) {
//		if (LendType.equals("Give Money")) {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL + bnkChargeL);
//			long dbBkBalance = currentbKashBalance();
//			return UnitConverter.longToString(dbBkBalance - amountWithoutCharge);
//		} else {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL - bnkChargeL);
//			long dbBkBalance = currentbKashBalance();
//			return UnitConverter.longToString(dbBkBalance + amountWithoutCharge);
//		}
//	}
	
	
//	public String leRocBalanceAfter(String amountWithCharge, String bnkCharge, String LendType) {
//		if (LendType.equals("Give Money")) {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL + bnkChargeL);
//			long dbRocBalance = currentRocketBalance();
//			return UnitConverter.longToString(dbRocBalance - amountWithoutCharge);
//		} else {
//			long amountWithChargeL = UnitConverter.stringToLong(amountWithCharge);
//			long bnkChargeL = UnitConverter.stringToLong(bnkCharge);
//			long amountWithoutCharge = (amountWithChargeL - bnkChargeL);
//			long dbRocBalance = currentRocketBalance();
//			return UnitConverter.longToString(dbRocBalance + amountWithoutCharge);
//		}
//	}
	
	
	public String updatedTotalLendTk(String amountToAdd, String LendType) {
		if (LendType.equals("Give Money")) {
			long dbBalance = UnitConverter.stringToLong(getTotalLendTk());
			long amountL = UnitConverter.stringToLong(amountToAdd);
			return UnitConverter.longToString(dbBalance + amountL);
		} else {
			long dbBalance = UnitConverter.stringToLong(getTotalLendTk());
			long amountL = UnitConverter.stringToLong(amountToAdd);
			return UnitConverter.longToString(dbBalance - amountL);
		}
	}
	
	
	public boolean leRepayValidation(String amount, String personName) {
		long typedAmount = UnitConverter.stringToLong(amount);
		long personLendedAmount = UnitConverter.stringToLong(new Lend().leRepayPersonLendedAmount(personName));
		if(typedAmount>personLendedAmount) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean leisTypedAmountLessThanLended(String typedAmount, String personName) {
		long typed = UnitConverter.stringToLong(typedAmount);
		long personLendedAmount = UnitConverter.stringToLong(new Lend().leRepayPersonLendedAmount(personName));
		if(typed<personLendedAmount) {
			return true;
		} else {
			return false;
		}
	}
	
////////////////////////////////////////////      Bank Function  ////////////////////////////////////////////
//---------------------------------------------------------------------------------------------------------//

	public ObservableList<String> bnkTransactionType() {
		ObservableList<String> list = FXCollections.observableArrayList("Personal", "Outside", "Get Money");
		return list;
	}
	
	
	public ObservableList<String> bnkAmountNature() {
		ObservableList<String> list = FXCollections.observableArrayList("Credit","Debit");
		return list;
	}

	
	public String getbkAccountBalance() {
		return UnitConverter.longToString(currentbKashBalance());
	}
	
	
	public String getRocAccountBalance() {
		return UnitConverter.longToString(currentRocketBalance());
	}
	
	
	public String getPerAccountBalance() {
		return UnitConverter.longToString(currentPersonalBalance());
	}
	
	
	public String updatedPersonalBalance(String amount, String nature) {
		long amountlong = UnitConverter.stringToLong(amount);
		long dbPersonalBal = currentPersonalBalance();
		
		if (nature.equals("Credit")) {
			String newBalance = UnitConverter.longToString(dbPersonalBal+amountlong);
			return newBalance;
		} else {
			String newBalance = UnitConverter.longToString(dbPersonalBal-amountlong);
			return newBalance;
		}
		
	}
	
//	public static void main(String[] args) {
//		MakeATransactionModel access = new MakeATransactionModel();
//		System.out.println(access.updatedbKashBalance("5000", "Cash In"));
//	}
	
}





