package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DashboardModel;
import operation.BankIssue;
import operation.GoToOperation;
import tab.TabAccess;
import tableAndgraph.ExpenseChart;
import tableAndgraph.GetMoneyChart;

public class DashboardController extends DashboardModel {
//	JavaFX Node object 
	@FXML
	private AnchorPane container;
	
	@FXML
	private MenuItem mnuDashboard;
	@FXML
	private MenuItem mnuGetMoney;
	@FXML
	private MenuItem mnuExpense;
	@FXML
	private MenuItem mnuLend;
	@FXML
	private MenuItem mnuBank;
	@FXML
	private MenuItem mnuSettings;
	@FXML
	private MenuItem mnuTransactionHistory;
	@FXML
	private MenuItem mnuCashCalculate;
	@FXML
	private MenuItem mnuExit;
	@FXML
	private MenuItem mnuCreateSource;
	@FXML
	private MenuItem mnuCreateSector;
	@FXML
	private MenuItem mnuUndo;
	@FXML
	private MenuItem mnuBankSettings;
	@FXML
	private MenuItem mnuSourceSettings;
	@FXML
	private MenuItem mnuSectorSettings;
	@FXML
	private MenuItem mnuSystemSettings;
	@FXML
	private MenuItem mnuHowTo;
	@FXML
	private MenuItem mnuAbout;
	
	@FXML
	private Button btnSignOut;
	@FXML
	private Button btnMakeATransaction;
	@FXML
	private Button btnCashCalculate;
	@FXML
	private Button btnAdjustBalance;
	@FXML
	private Button btnTransactionHistory;
	@FXML
	private Button btnSettings;
	@FXML
	private Button btnRefreshCharts;
	
	@FXML
	private Label lblLongClock;
	@FXML
	private Label lblWalletBalance;
	@FXML
	private Label lblUserFullName;
	@FXML
	private Label lblTotalGetMoney;
	@FXML
	private Label lblTotalExpense;
	@FXML
	private Label lblbKashName;
	@FXML
	private Label lblbKashBalance;
	@FXML
	private Label lblRocketName;
	@FXML
	private Label lblRocketBalance;
	@FXML
	private Label lblPersonalName;
	@FXML
	private Label lblPersonalBalance;
	@FXML
	private Label lblBorrowName;
	@FXML
	private Label lblTotalBorrow;
	@FXML
	private Label lblLendName;
	@FXML
	private Label lblTotalLend;
	
	@FXML
	private ComboBox<String> cmboGetMoneyMonthList;
	@FXML
	private ComboBox<String> cmboExpenseMonthList;
	@FXML
	private ComboBox<String> cmboSourceList;
	@FXML
	private ComboBox<String> cmboSectorList;
	
	@FXML
	private BarChart<String, Number> chartGetMoney;
	@FXML
	private CategoryAxis gmXaxis;
	@FXML
	private NumberAxis gmYaxis;
	@FXML
	private BarChart<String, Number> chartExpense;
	@FXML
	private CategoryAxis exXaxis;
	@FXML
	private NumberAxis exYaxis;
	
	@FXML
	private Circle face;
	@FXML
	private Line hourHand;
	@FXML
	private Line minuteHand;
	@FXML
	private Line secondHand;
	@FXML
	private Circle spindlespindle;
	@FXML
	private Group analogueClock;
	@FXML
	private Label digitalClock;
	@FXML
	private Label brand;
	
		
////////////////////////////////// General Function //////////////////////////////
	@FXML
	public void initialize() {
//		show user name and current balance status
		lblUserFullName.setText(userFullName()+" ");
		lblWalletBalance.setText(addThousandSeparator(getWalletBalance()));
		lblTotalBorrow.setText(addThousandSeparator(getTotalBorrowTk()));
		lblTotalLend.setText(addThousandSeparator(getTotalLendTk()));
		
//		show balance if service is enabled
		if (BankIssue.isbKashActivated()) {
			lblbKashBalance.setText(addThousandSeparator(getbkAccountBalance()));
		}
		if (BankIssue.isRocketActivated()) {
			lblRocketBalance.setText(addThousandSeparator(getRocAccountBalance()));
		}
		lblPersonalBalance.setText(getPerAccountBalance());
		
		showAllGetMoneyMonths(); //load all transacted Get Money month
		showSource(); // load all active sources
		showAllExpenseMonths(); //load all transacted Expense month
		showSector(); //load all active sectors
		showSourceAmount(); //show total amount by selected source
		showSectorAmount();//show total amount by selected sector
		getMoneyChart(); //generate chart value
		expenseChart();
		clock(); //run the analog clock
		showAllCurrentStatus();
		
//		add tooltip
		btnSignOut.setTooltip(new Tooltip("Sign Out from Application"));
		btnMakeATransaction.setTooltip(new Tooltip("Take you to your transaction window"));
		btnCashCalculate.setTooltip(new Tooltip("Calculate how much money at your hand now"));
		btnSettings.setTooltip(new Tooltip("Take you to Bank, Source, Sector and System Settings"));
		btnTransactionHistory.setTooltip(new Tooltip("Show your all transaction history"));
		btnRefreshCharts.setTooltip(new Tooltip("Refresh Get Money and Expense Charts"));
		Tooltip.install(face, new Tooltip("Analog Clock"));
		Tooltip.install(lblWalletBalance, new Tooltip("Your wallet balance now"));
		Tooltip.install(lblUserFullName, new Tooltip("User's Full Name"));
		Tooltip.install(lblTotalBorrow, new Tooltip("Your Total Borrowed Tk"));
		Tooltip.install(lblTotalLend, new Tooltip("Your Total Lended Tk."));
		Tooltip.install(lblPersonalBalance, new Tooltip("Total Tk. at your personal bank"));
		Tooltip.install(lblbKashBalance, new Tooltip("Total Tk. at your bKash account"));
		Tooltip.install(lblRocketBalance, new Tooltip("Total Tk. at your Rocket account"));
		Tooltip.install(chartGetMoney, new Tooltip("Summary of how much Tk. you get in this month"));
		Tooltip.install(cmboGetMoneyMonthList, new Tooltip("All transacted month name"));
		Tooltip.install(cmboSourceList, new Tooltip("Name of Income Sources, where from you get Tk."));
		Tooltip.install(lblTotalGetMoney, new Tooltip("Total Tk. from this Income Source"));
		Tooltip.install(chartExpense, new Tooltip("Summary of how much Tk. you expensed in this month"));
		Tooltip.install(cmboExpenseMonthList, new Tooltip("All transacted month name"));
		Tooltip.install(cmboSectorList, new Tooltip("Name of Expenditure Sector, where you expense your Tk."));
		Tooltip.install(lblTotalExpense, new Tooltip("Total Tk. from this Expenditure Sector"));
		
//		show welcome note if the user is new
		if (userIsNew()) {		
			Alert confirmationMsg = new Alert(AlertType.INFORMATION);
			confirmationMsg.setTitle("Welcome");
			confirmationMsg.setHeaderText("Welcome to Money Manager, Recorder of Your Personal Finance");
			confirmationMsg.setContentText("Before starting to use, please setup your account from Settings.");
			confirmationMsg.showAndWait();
			updateLastAccessDate();
		}
	}
	
	
	@FXML //open expected stage on click
	private void btnRefreshCharts(ActionEvent ev) {
		Stage DashboardStage = (Stage) btnRefreshCharts.getScene().getWindow();//make object for this stage
		(new GoToOperation()).goToDashboard(DashboardStage); //open expected stage
	}
	
	
/////////////////// Menu Function ///////////////////////////
	@FXML
	private void mnuDashboard(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToDashboard(DashboardStage);
	}
	
	@FXML
	private void mnuGetMoney(ActionEvent event) {
		(new TabAccess()).setTabName("tabGetMoney"); //define which tab should open
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToMakeATransaction(DashboardStage);
	}

	@FXML
	private void mnuExpense(ActionEvent event) {
		(new TabAccess()).setTabName("tabExpense");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToMakeATransaction(DashboardStage);
	}

	@FXML
	private void mnuLend(ActionEvent event) {
		(new TabAccess()).setTabName("tabLend");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToMakeATransaction(DashboardStage);
	}

	@FXML
	private void mnuBank(ActionEvent event) {
		(new TabAccess()).setTabName("tabBank");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToMakeATransaction(DashboardStage);
	}
	
	@FXML
	private void mnuSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabBank");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuTransactionHistory(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToTransactionHistory(DashboardStage);
	}
	
	@FXML
	private void mnuCashCalculate(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToCashCalculate(DashboardStage);
	}
	
	@FXML
	private void mnuExit(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		DashboardStage.close();
	}
	
	@FXML
	private void mnuCreateSource(ActionEvent event) {
		(new TabAccess()).setTabName("tabSource");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}

	@FXML
	private void mnuCreateSector(ActionEvent event) {
		(new TabAccess()).setTabName("tabSector");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuUndo(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Action Failed");
		alert.setHeaderText("Undo Function Works Only From \"Make A Transaction\" and \"History\" Window");
		alert.setContentText("Press \"OK\" to go to \"Make A Transaction\" window");
		alert.setX(DashboardStage.getX() + 60);
		alert.setY(DashboardStage.getY() + 170);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			(new TabAccess()).setTabName("tabGetMoney"); //name of which Tab should open
			(new GoToOperation()).goToMakeATransaction(DashboardStage);
		}
	}

	@FXML
	private void mnuBankSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabBank");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuSourceSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabSource");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}

	@FXML
	private void mnuSectorSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabSector");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuSystemSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabSystem");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuAdvancedSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabAdvanced");
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	@FXML
	private void mnuHowTo(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToHelp(DashboardStage);
	}
	
	@FXML
	private void mnuAbout(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToAbout(DashboardStage);
	}

	
///////////////////// Button Function ///////////////////////////
	@FXML
	private void btnSignOut(ActionEvent event) {
		Stage DashboardStage = (Stage) btnSignOut.getScene().getWindow();
		(new GoToOperation()).goToSignIn(DashboardStage);
	}
	
	@FXML
	private void goToMakeAtransaction(ActionEvent event) {
		(new TabAccess()).setTabName("tabGetMoney");
		Stage DashboardStage = (Stage) btnMakeATransaction.getScene().getWindow();
		goToMakeATransaction(DashboardStage);
	}
	
	@FXML
	private void btnCashCalculate(ActionEvent event) {
		Stage DashboardStage = (Stage) btnCashCalculate.getScene().getWindow();
		(new GoToOperation()).goToCashCalculate(DashboardStage);
	}
	
	@FXML
	private void btnTransactionHistory(ActionEvent event) {
		Stage DashboardStage = (Stage) btnTransactionHistory.getScene().getWindow();
		(new GoToOperation()).goToTransactionHistory(DashboardStage);
	}
	
	@FXML
	private void btnSettings(ActionEvent event) {
		(new TabAccess()).setTabName("tabBank");
		Stage DashboardStage = (Stage) btnSettings.getScene().getWindow();
		(new GoToOperation()).goToSettings(DashboardStage);
	}
	
	
///////////////////////  Others Function ///////////////////////////
	//load all transacted Get Money month
	private void showAllGetMoneyMonths() {
		cmboGetMoneyMonthList.setItems(loadAllGetMoneyMonths());
//		cmboGetMoneyMonthList.getSelectionModel().selectFirst();
		cmboGetMoneyMonthList.getSelectionModel().select(1);
	}
	
//	load all active sources
	private void showSource() {
		cmboSourceList.setItems(loadSource());
		cmboSourceList.getSelectionModel().selectFirst();
	}
	
//	show total amount by selected source
	private void showSourceAmount() {
		lblTotalGetMoney.setText(getAmountBySource(cmboGetMoneyMonthList.getValue(), cmboSourceList.getValue()));
	}
	
//	load all transacted expense months
	private void showAllExpenseMonths() {
		cmboExpenseMonthList.setItems(loadAllExpenseMonths());
//		cmboExpenseMonthList.getSelectionModel().selectFirst();
		cmboExpenseMonthList.getSelectionModel().select(1);
	}
	
//	load all active sector
	private void showSector() {
		cmboSectorList.setItems(loadSector());
		cmboSectorList.getSelectionModel().selectFirst();
	}

//	show total amount by selected sector
	private void showSectorAmount() {
		try {
			lblTotalExpense.setText(getAmountBySector(cmboExpenseMonthList.getValue(), cmboSectorList.getValue()));
		} catch (Exception e) {}
	}
	
//	Action events
	@FXML
	private void cmboGetMoneyMonthList(ActionEvent event) {
		try {
			showSourceAmount();
			getMoneyChart();
		} catch (Exception e) {}
	}
	
	@FXML
	private void cmboSourceList(ActionEvent event) {
		try {
			showSourceAmount();
		} catch (Exception e) {}
	}
	
	@FXML
	private void cmboExpenseMonthList(ActionEvent event) {
		try {
			showSectorAmount();
			expenseChart();
		} catch (Exception e) {}
	}
	
	@FXML
	private void cmboSectorList(ActionEvent event) {
		try {
			showSectorAmount();
		} catch (Exception e) {}
	}
	
//	show value in charts
	private void getMoneyChart() {
		gmXaxis.setLabel("Sources Name");
		gmYaxis.setLabel("Amount");
		chartGetMoney.setTitle("Get Money( " + cmboGetMoneyMonthList.getValue()+" )");
		Series<String, Number> gmChartData = GetMoneyChart.getSourceData(cmboGetMoneyMonthList.getValue());
		chartGetMoney.getData().add(gmChartData);
	}
	
	private void expenseChart() {
		exXaxis.setLabel("Sectors Name");
		exYaxis.setLabel("Amount");
		chartExpense.setTitle("Expense( " + cmboExpenseMonthList.getValue()+" )");
		Series<String, Number> exChartData = ExpenseChart.getExpenseData(cmboExpenseMonthList.getValue());
		chartExpense.getData().add(exChartData);
	}
	
///////////////////// Analog Clock Function /////////////////////////
	private void clock() {
		brand.setText("krHasan");
		// determine the starting time.
		Calendar calendar = GregorianCalendar.getInstance();
		final double seedSecondDegrees  = calendar.get(Calendar.SECOND) * (360 / 60);
		final double seedMinuteDegrees  = (calendar.get(Calendar.MINUTE) + seedSecondDegrees / 360.0) * (360 / 60);
		final double seedHourDegrees    = (calendar.get(Calendar.HOUR)   + seedMinuteDegrees / 360.0) * (360 / 12) ;

		// define rotations to map the analogueClock to the current time.
		final Rotate hourRotate      = new Rotate(seedHourDegrees);
		final Rotate minuteRotate    = new Rotate(seedMinuteDegrees);
		final Rotate secondRotate    = new Rotate(seedSecondDegrees);
		hourHand.getTransforms().add(hourRotate);
		minuteHand.getTransforms().add(minuteRotate);
		secondHand.getTransforms().add(secondRotate);

		// the hour hand rotates twice a day.
		final Timeline hourTime = new Timeline(
				new KeyFrame(
						Duration.hours(12),
						new KeyValue(
								hourRotate.angleProperty(),
								360 + seedHourDegrees,
								Interpolator.LINEAR
								)
						)
				);

		// the minute hand rotates once an hour.
		final Timeline minuteTime = new Timeline(
				new KeyFrame(
						Duration.minutes(60),
						new KeyValue(
								minuteRotate.angleProperty(),
								360 + seedMinuteDegrees,
								Interpolator.LINEAR
								)
						)
				);

		// move second hand rotates once a minute.
		final Timeline secondTime = new Timeline(
				new KeyFrame(
						Duration.seconds(60),
						new KeyValue(
								secondRotate.angleProperty(),
								360 + seedSecondDegrees,
								Interpolator.LINEAR
								)
						)
				);

		// the digital clock updates once a second.
//		final Timeline digitalTime = new Timeline(
//				new KeyFrame(Duration.seconds(0),
//						new EventHandler<ActionEvent>() {
//					@Override public void handle(ActionEvent actionEvent) {
//						Calendar calendar   = GregorianCalendar.getInstance();
//						String hourString   = pad(2, '0', calendar.get(Calendar.HOUR)   == 0 ? "12" : calendar.get(Calendar.HOUR) + "");
//						String minuteString = pad(2, '0', calendar.get(Calendar.MINUTE) + "");
//						String secondString = pad(2, '0', calendar.get(Calendar.SECOND) + "");
//						String ampmString   = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
//						digitalClock.setText(hourString + ":" + minuteString + ":" + secondString + " " + ampmString);
//					}
//				}
//						),
//				new KeyFrame(Duration.seconds(1))
//				);

		// time never ends.
		hourTime.setCycleCount(Animation.INDEFINITE);
		minuteTime.setCycleCount(Animation.INDEFINITE);
		secondTime.setCycleCount(Animation.INDEFINITE);
//		digitalTime.setCycleCount(Animation.INDEFINITE);

		// start the analogueClock.
//		digitalTime.play();
		secondTime.play();
		minuteTime.play();
		hourTime.play();

		// add a glow effect whenever the mouse is positioned over the clock.
		final Glow glow = new Glow();
		analogueClock.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				analogueClock.setEffect(glow);
			}
		});
		analogueClock.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				analogueClock.setEffect(null);
			}
		});

	}
	
	
//	private String pad(int fieldWidth, char padChar, String s) {
//	    StringBuilder sb = new StringBuilder();
//	    for (int i = s.length(); i < fieldWidth; i++) {
//	      sb.append(padChar);
//	    }
//	    	sb.append(s);
//	    return sb.toString();
//	}
	
	
	private void showAllCurrentStatus() {

		final Timeline status = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent actionEvent) {
						showBalanceStatus(currentBalanceStatus());
					}
				}
						),
				new KeyFrame(Duration.seconds(3))
				);
		status.setCycleCount(Animation.INDEFINITE);
		status.play();
	}
	
	
	private void showBalanceStatus(int status) {
		if (status == 1) { // Borrow
			lblBorrowName.setVisible(true);
			lblTotalBorrow.setVisible(true);
			lblLendName.setVisible(false);
			lblTotalLend.setVisible(false);
			lblPersonalName.setVisible(false);
			lblPersonalBalance.setVisible(false);
			lblbKashName.setVisible(false);
			lblbKashBalance.setVisible(false);
			lblRocketName.setVisible(false);
			lblRocketBalance.setVisible(false);
		} else if(status == 2) { // Lend
			lblBorrowName.setVisible(false);
			lblTotalBorrow.setVisible(false);
			lblLendName.setVisible(true);
			lblTotalLend.setVisible(true);
			lblPersonalName.setVisible(false);
			lblPersonalBalance.setVisible(false);
			lblbKashName.setVisible(false);
			lblbKashBalance.setVisible(false);
			lblRocketName.setVisible(false);
			lblRocketBalance.setVisible(false);
		} else if(status == 3) { // Personal
			lblBorrowName.setVisible(false);
			lblTotalBorrow.setVisible(false);
			lblLendName.setVisible(false);
			lblTotalLend.setVisible(false);
			lblPersonalName.setVisible(true);
			lblPersonalBalance.setVisible(true);
			lblbKashName.setVisible(false);
			lblbKashBalance.setVisible(false);
			lblRocketName.setVisible(false);
			lblRocketBalance.setVisible(false);
		} else if(status == 4) { // bKash
			lblBorrowName.setVisible(false);
			lblTotalBorrow.setVisible(false);
			lblLendName.setVisible(false);
			lblTotalLend.setVisible(false);
			lblPersonalName.setVisible(false);
			lblPersonalBalance.setVisible(false);
			lblbKashName.setVisible(true);
			lblbKashBalance.setVisible(true);
			lblRocketName.setVisible(false);
			lblRocketBalance.setVisible(false);
		} else if(status == 5) { // Rocket
			lblBorrowName.setVisible(false);
			lblTotalBorrow.setVisible(false);
			lblLendName.setVisible(false);
			lblTotalLend.setVisible(false);
			lblPersonalName.setVisible(false);
			lblPersonalBalance.setVisible(false);
			lblbKashName.setVisible(false);
			lblbKashBalance.setVisible(false);
			lblRocketName.setVisible(true);
			lblRocketBalance.setVisible(true);
		}
	}
	
}







