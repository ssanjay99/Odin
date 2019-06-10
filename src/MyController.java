import java.io.IOException;

import org.jdom2.JDOMException;
import org.json.JSONException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class MyController {

	ObservableList<String> Environment = FXCollections.observableArrayList("omsqa2", "omsqa3", "omsstage2",
			"omsstage3");
	ObservableList<String> Agentname = FXCollections.observableArrayList("HBCScheduleAgent", "HBCReleaseAgent",
			"HBCCloseShipment", "HBCSendInvoice", "HBCPaymentCollectionAgent", "HBCMiscellaneousPurgeAgent",
			"HBCShipShipmentServer", "HBCCreateReturnInvoiceServer", "HBCFraudCheckAgent", "HBCCreateChainedOrderAgent",
			"HBCPOProcessAgent", "HBCRTAMActivitySyncAgentServer", "HBCRTAMFullSyncAgentServer", "HBCBuyerRemorseAgent",
			"HBCShipmentMonitor", "HBCCreateShmntInvoice", "HBCCustomerPickReminderMailAgent",
			"HBCPaymentExecutionAgent", "HBCEscalationMailServer", "HBCEnhancedOrderMonitorServer",
			"HBCCloseReturnOrderAgent", "HBCCloseOrderAgent", "HBCCloseShipment", "HBCJMSPOSReturnsIntegServer",
			"HBCEnhancedOrderMonitor", "HBCJMSFraudReviewIntegServer", "HBCBorderFreeAgentServer","HBCConfirmOrderAgent","HBCEmailIntegServer");

	ObservableList<String> Environment1 = FXCollections.observableArrayList("omsqa2", "omsqa3", "omsstage2",
			"omsstage3");

	ObservableList<String> CriteriaID = FXCollections.observableArrayList("ScheduleSalesOrder", "ReleaseSalesOrder",
			"CreateSalesOrderInvoice", "SendSalesOrderInvoice", "RetrunFullfilment", "CreateChainedOrder",
			"ResolveBuyersRemorse", "EnhancedMonitor", "ShipmentMonitor", "RealTimeAvailability", "FraudCheck",
			"BorderFreeAgent", "ProcessPurchaseOrder", "IBAtrigger", "HBCProcessHoldTypeAgentServer","HBCConfirmOrderAgent");

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



	// @FXML
	// private Label AgentStatus;

	@FXML
	private void initialize() {
		am_environment.setItems(Environment);
		am_agentname.setItems(Agentname);
		Agent_env.setItems(Environment1);
		Criteria_id.setItems(CriteriaID);
		

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
		Result.setText(flag);
		Submit.setDisable(false);
		StopAgent.setDisable(true);
		
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
		Stopid.setText(flag3);
		Submit.setDisable(false);
		StopAgent.setDisable(true);

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
		Check_id.setText(Flag1);
		Submit.setDisable(false);
		StopAgent.setDisable(false);
		if (Flag1.contains("AGENT ACTIVE")) {
			Submit.setDisable(true);
			
		}else if(Flag1.contains("AGENT DOWN")) {
			StopAgent.setDisable(true);
		}

	}

	@FXML
	void OntriggerClick(ActionEvent event) throws IOException, InterruptedException {

		OMScalls ts = new OMScalls();
		String agnt1 = Agent_env.getValue();
		String agnt2 = Criteria_id.getValue();
		String Flag2 = ts.triggerAgent(agnt1, agnt2);
		Result.setText("");
		Status.setText("");
		Check_id.setText("");
		Stopid.setText("");
		Status.setText(Flag2);
		Submit.setDisable(false);
		StopAgent.setDisable(true);

	}

}
