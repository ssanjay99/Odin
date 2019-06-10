
//import java.awt.TextField;
import java.io.IOException;

import org.jdom2.JDOMException;
import org.json.JSONException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

	ObservableList<String> Environment = FXCollections.observableArrayList("omsqa2", "omsqa3", "omsstage2",
			"omsstage3");
	ObservableList<String> Agentname = FXCollections.observableArrayList("HBCScheduleAgent", "HBCReleaseAgent",
			"HBCCloseShipment", "HBCSendInvoice", "HBCPaymentCollectionAgent", "HBCMiscellaneousPurgeAgent",
			"HBCShipShipmentServer", "HBCCreateReturnInvoiceServer", "HBCFraudCheckAgent", "HBCCreateChainedOrderAgent",
			"HBCPOProcessAgent", "HBCRTAMActivitySyncAgentServer", "HBCRTAMFullSyncAgentServer", "HBCBuyerRemorseAgent",
			"HBCShipmentMonitor", "HBCCreateShmntInvoice", "HBCCustomerPickReminderMailAgent",
			"HBCPaymentExecutionAgent", "HBCEscalationMailServer", "HBCEnhancedOrderMonitorServer",
			"HBCCloseReturnOrderAgent", "HBCCloseOrderAgent", "HBCCloseShipment", "HBCJMSPOSReturnsIntegServer",
			"HBCEnhancedOrderMonitor", "HBCJMSFraudReviewIntegServer", "HBCBorderFreeAgentServer",
			"HBCConfirmOrderAgent", "HBCEmailIntegServer", "HBCAuthAgent");

	ObservableList<String> Environment1 = FXCollections.observableArrayList("omsqa2", "omsqa3", "omsstage2",
			"omsstage3");

	ObservableList<String> CriteriaID = FXCollections.observableArrayList("ScheduleSalesOrder", "ReleaseSalesOrder",
			"CreateSalesOrderInvoice", "SendSalesOrderInvoice", "RetrunFullfilment", "CreateChainedOrder",
			"ResolveBuyersRemorse", "EnhancedMonitor", "ShipmentMonitor", "RealTimeAvailability", "FraudCheck",
			"BorderFreeAgent", "ProcessPurchaseOrder", "IBAtrigger", "HBCProcessHoldTypeAgentServer",
			"HBCConfirmOrderAgent", "AuthAgent");

	ObservableList<String> BannerName = FXCollections.observableArrayList("SAKS", "OFF5", "LT", "BAY");

	@FXML
	private ChoiceBox<String> am_environment;

	@FXML
	private ChoiceBox<String> am_agentname;

	@FXML
	private ChoiceBox<String> Agent_env;

	@FXML
	private ChoiceBox<String> Criteria_id;

	@FXML
	private Label Check_id;

	@FXML
	private Button Trigger;

	@FXML
	private Button Submit;

	@FXML
	private Label Result;

	@FXML
	private Label Stopid;

	@FXML
	private Label Status;

	@FXML
	private Button StopAgent;

	@FXML
	private Button check;

	@FXML
	private ChoiceBox<String> Ship_env;

	@FXML
	private TextField OrderNo;

	@FXML
	private ChoiceBox<String> Banner;

	@FXML
	private Button Ship;

	@FXML
	private Label ShipStatus;

	@FXML
	private RadioButton partial;

	@FXML
	private TextField Quantity;

	@FXML
	private void initialize() {
		am_environment.setItems(Environment);
		am_agentname.setItems(Agentname);
		Agent_env.setItems(Environment1);
		Criteria_id.setItems(CriteriaID);
		Ship_env.setItems(Environment);
		Banner.setItems(BannerName);
		Submit.setDisable(true);
		StopAgent.setDisable(true);
		Quantity.setDisable(true);

	}

	@FXML
	void OnPartialClick(ActionEvent event) {
		Quantity.setDisable(false);
	}

	@FXML
	void OnSubmitClick(ActionEvent event) throws InterruptedException {

		agentMaster Ms = new agentMaster();
		String res1 = am_environment.getValue();
		String res2 = am_agentname.getValue();
		String flag = Ms.Startagent(res1, res2);
		Result.setText("");
		Status.setText("");
		Check_id.setText("");
		Stopid.setText("");
		Submit.setDisable(true);
		StopAgent.setDisable(true);
		if (flag.contains("AGENT STARTED")) {
			Submit.setDisable(true);
			try {
				String Value = "AGENT STARTED";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}

	}else if(flag.contains("FAILED") || flag.contains("server down")) {
		Submit.setDisable(true);
		try {
			String Value = "FAILED TO START";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(main.primaryStage);
			stage.show();
			PopController Popup = new PopController();
			((PopController) fxmlLoader.getController()).Popuptext(Value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	}

	@FXML
	void OnstopClick(ActionEvent event) {
		agentMaster Ts = new agentMaster();
		String res1 = am_environment.getValue();
		String res2 = am_agentname.getValue();
		String flag3 = Ts.stopagent(res1, res2);
		Result.setText("");
		Status.setText("");
		Check_id.setText("");
		Stopid.setText("");
		Submit.setDisable(true);
		StopAgent.setDisable(true);
		if (flag3.contains("AGENT ACTIVE")) {
			Submit.setDisable(true);
			try {
				String Value = "AGENT ACTIVE";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
		else if(flag3.contains("AGENT STOPPED") || flag3.contains("Server Down")) {
			Submit.setDisable(true);
			try {
				String Value = "AGENT STOPPED";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
		

	@FXML
	void OnCheckStatusClick(ActionEvent event) throws IOException, JSONException, JDOMException, InterruptedException {

		OMScalls Rs = new OMScalls();
		String res1 = am_environment.getValue();
		String res2 = am_agentname.getValue();
		String Flag1 = Rs.getServerList(res1, res2);
		Result.setText("");
		Status.setText("");
		Check_id.setText("");
		Stopid.setText("");
		Submit.setDisable(false);
		StopAgent.setDisable(false);
		if (Flag1.contains("AGENT ACTIVE")) {
			Submit.setDisable(true);
			try {
				String Value = "AGENT ACTIVE";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Flag1.contains("AGENT DOWN")) {
			StopAgent.setDisable(true);
			try {
				String Value = "AGENT DOWN";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	void OntriggerClick(ActionEvent event) throws IOException, InterruptedException {

		OMScalls ts = new OMScalls();
		String agnt1 = Agent_env.getValue();
		String agnt2 = Criteria_id.getValue();
		String Flag2 = ts.triggerAgent(agnt1, agnt2);

		Result.setText("");
		Check_id.setText("");
		Stopid.setText("");
		Submit.setDisable(true);
		StopAgent.setDisable(true);
		if (Flag2.contains("AGENT TRIGGERED")) {
			Submit.setDisable(true);
			try {
				String Value = "AGENT TRIGGERED";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Flag2.contains("Server is down")) {
			StopAgent.setDisable(true);
			try {
				String Value = "Server is down";
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(main.primaryStage);
				stage.show();
				PopController Popup = new PopController();
				((PopController) fxmlLoader.getController()).Popuptext(Value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}


	}

	@FXML
	void OnshipClick(ActionEvent event) throws IOException, JSONException, JDOMException, InterruptedException {
		OMScalls ts = new OMScalls();
		String res1 = Ship_env.getValue();
		String res2 = Banner.getValue();
		String res3 = OrderNo.getText();
		String qty = Quantity.getText();
		if (partial.isSelected()) {
			String Flag4 = ts.PartialShipmentCall(res3, res2, res1, qty);
			ShipStatus.setText(Flag4);
			if (Flag4.contains("SHIPPED")) {
				Submit.setDisable(true);
				try {
					String Value = "SHIPPED";
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(main.primaryStage);
					stage.show();
					PopController Popup = new PopController();
					((PopController) fxmlLoader.getController()).Popuptext(Value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (Flag4.contains("Error in Shipment")) {
				StopAgent.setDisable(true);
				try {
					String Value = "Error in Shipment";
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(main.primaryStage);
					stage.show();
					PopController Popup = new PopController();
					((PopController) fxmlLoader.getController()).Popuptext(Value);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} else {
			String Flag4 = ts.ShipmentCall(res3, res2, res1);
			ShipStatus.setText(Flag4);
			if (Flag4.contains("SHIPPED")) {
				Submit.setDisable(true);
				try {
					String Value = "SHIPPED";
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(main.primaryStage);
					stage.show();
					PopController Popup = new PopController();
					((PopController) fxmlLoader.getController()).Popuptext(Value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (Flag4.contains("Error in Shipment")) {
				StopAgent.setDisable(true);
				try {
					String Value = "Error in Shipment";
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initModality(Modality.WINDOW_MODAL);
					stage.initOwner(main.primaryStage);
					stage.show();
					PopController Popup = new PopController();
					((PopController) fxmlLoader.getController()).Popuptext(Value);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			

		}
		OrderNo.clear();
		Quantity.clear();
		partial.setSelected(false);
		

	}

}
