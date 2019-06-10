import java.io.BufferedWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.JSONException;

public class OMScalls {

	public String getServerList(String res1, String res2)
			throws IOException, JSONException, JDOMException, InterruptedException {

		String requestTemplate = "<hi />";
		String url = null;

		switch (res1) {
		case "omsqa2":
			url = getProperty("WSGODQA2");
			break;

		case "omsqa3":
			url = getProperty("WSGODQA3");
			break;

		case "omsstage2":
			url = getProperty("WSGODSTG2");
			break;

		case "omsstage3":
			url = getProperty("WSGODSTG3");
			break;

		}

		String name = getProperty("UserCredentials");
		String password = getProperty("PWDCredentials");
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(requestTemplate);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/xml");
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.setHeader("Authorization", "Basic  " + authStringEnc);

		ResponseHandler<String> handler = new BasicResponseHandler();

		// String body = client.execute(httpPost, handler);
		HttpResponse httpResponse = null;
		httpResponse = client.execute(httpPost);
		httpResponse.getEntity().getContent().close();
		int ResponseCode = httpResponse.getStatusLine().getStatusCode();
		String body = client.execute(httpPost, handler);
		System.out.println(body);

		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream stream = new ByteArrayInputStream(body.getBytes("UTF-8"));
		System.out.println(body);
		Document document = saxBuilder.build(stream);
		Element classElement = document.getRootElement();
		List<Element> Values = classElement.getChildren();
		List<Attribute> ValueNodes = classElement.getAttributes();
		for (int temp = 0; temp < Values.size(); temp++) {
			Element Value = Values.get(temp);
			System.out.println("\nCurrent Element :" + Value.getName());
			List<Attribute> Attributes = Value.getAttributes();
			List<Element> Child = Value.getChildren();
			for (Attribute model : Attributes) {
				System.out.println(model.getValue());
				if (model.getValue().contains(res2)) {
					return "AGENT ACTIVE";
				}
			}
		}
		return "AGENT DOWN";
	}

	public String triggerAgent(String agnt1, String agnt2) throws IOException {

		String requestTemplate = "<hi />";
		String url = null;

		switch (agnt1) {
		case "omsqa2":
			url = getProperty("TSGODQA2");
			if (agnt2 == "ScheduleSalesOrder") {
				requestTemplate = getRequestProperty("ScheduleAgentSO");
			} else if (agnt2 == "ReleaseSalesOrder") {
				requestTemplate = getRequestProperty("ReleaseAgentSO");
			} else if (agnt2 == "CreateSalesOrderInvoice") {
				requestTemplate = getRequestProperty("CreateSalesIvoice");
			} else if (agnt2 == "SendSalesOrderInvoice") {
				requestTemplate = getRequestProperty("SendSalesInvoice");
			} else if (agnt2 == "RetrunFullfilment") {
				requestTemplate = getRequestProperty("RetrunRecived");
			} else if (agnt2 == "CreateChainedOrder") {
				requestTemplate = getRequestProperty("CreateChainedOrder");
			} else if (agnt2 == "ResolveBuyersRemorse") {
				requestTemplate = getRequestProperty("BuyersRemore");
			} else if (agnt2 == "RealTimeAvailability") {
				requestTemplate = getRequestProperty("RtamMonitor");
			} else if (agnt2 == "FraudCheck") {
				requestTemplate = getRequestProperty("FraudCheck");
			} else if (agnt2 == "IBAtrigger") {
				requestTemplate = getRequestProperty("IBAtrigger");
			} else if (agnt2 == "BorderFreeAgent") {
				requestTemplate = getRequestProperty("BorderFreeAgent");
			}

			else if (agnt2 == "HBCProcessHoldTypeAgentServer") {
				requestTemplate = getRequestProperty("HBCProcessHoldTypeAgentServer");
			} else if (agnt2 == "HBCConfirmOrderAgent") {
				requestTemplate = getRequestProperty("ConfrimOrder");

			} else if (agnt2 == "AuthAgent") {
				requestTemplate = getRequestProperty("AuthAgent");
			}
			break;
		case "omsqa3":
			url = getProperty("TSGODQA3");
			if (agnt2 == "ScheduleSalesOrder") {
				requestTemplate = getRequestProperty("ScheduleAgentSO");
			} else if (agnt2 == "ReleaseSalesOrder") {
				requestTemplate = getRequestProperty("ReleaseAgentSO");
			} else if (agnt2 == "CreateSalesOrderInvoice") {
				requestTemplate = getRequestProperty("CreateSalesIvoice");
			} else if (agnt2 == "SendSalesOrderInvoice") {
				requestTemplate = getRequestProperty("SendSalesInvoice");
			} else if (agnt2 == "RetrunFullfilment") {
				requestTemplate = getRequestProperty("RetrunRecived");
			} else if (agnt2 == "CreateChainedOrder") {
				requestTemplate = getRequestProperty("CreateChainedOrder");
			} else if (agnt2 == "ResolveBuyersRemorse") {
				requestTemplate = getRequestProperty("BuyersRemore");
			} else if (agnt2 == "RealTimeAvailability") {
				requestTemplate = getRequestProperty("RtamMonitor");
			} else if (agnt2 == "FraudCheck") {
				requestTemplate = getRequestProperty("FraudCheck");
			} else if (agnt2 == "IBAtrigger") {
				requestTemplate = getRequestProperty("IBAtrigger");
			} else if (agnt2 == "BorderFreeAgent") {
				requestTemplate = getRequestProperty("BorderFreeAgent");
			}

			else if (agnt2 == "HBCProcessHoldTypeAgentServer") {
				requestTemplate = getRequestProperty("HBCProcessHoldTypeAgentServer");

			} else if (agnt2 == "HBCConfirmOrderAgent") {
				requestTemplate = getRequestProperty("ConfrimOrder");

			} else if (agnt2 == "AuthAgent") {
				requestTemplate = getRequestProperty("AuthAgent");
			}
			break;
		case "omsstage2":
			url = getProperty("TSGODSTG2");
			if (agnt2 == "ScheduleSalesOrder") {
				requestTemplate = getRequestProperty("ScheduleAgentSO");
			} else if (agnt2 == "ReleaseSalesOrder") {
				requestTemplate = getRequestProperty("ReleaseAgentSO");
			} else if (agnt2 == "CreateSalesOrderInvoice") {
				requestTemplate = getRequestProperty("CreateSalesIvoice");
			} else if (agnt2 == "SendSalesOrderInvoice") {
				requestTemplate = getRequestProperty("SendSalesInvoice");
			} else if (agnt2 == "RetrunFullfilment") {
				requestTemplate = getRequestProperty("RetrunRecived");
			} else if (agnt2 == "CreateChainedOrder") {
				requestTemplate = getRequestProperty("CreateChainedOrder");
			} else if (agnt2 == "ResolveBuyersRemorse") {
				requestTemplate = getRequestProperty("BuyersRemore");
			} else if (agnt2 == "RealTimeAvailability") {
				requestTemplate = getRequestProperty("RtamMonitor");
			} else if (agnt2 == "FraudCheck") {
				requestTemplate = getRequestProperty("FraudCheck");
			} else if (agnt2 == "IBAtrigger") {
				requestTemplate = getRequestProperty("IBAtrigger");
			} else if (agnt2 == "BorderFreeAgent") {
				requestTemplate = getRequestProperty("BorderFreeAgent");
			} else if (agnt2 == "HBCProcessHoldTypeAgentServer") {
				requestTemplate = getRequestProperty("HBCProcessHoldTypeAgentServer");

			} else if (agnt2 == "HBCConfirmOrderAgent") {
				requestTemplate = getRequestProperty("ConfrimOrder");

			} else if (agnt2 == "AuthAgent") {
				requestTemplate = getRequestProperty("AuthAgent");

			}
			break;
		case "omsstage3":
			url = getProperty("TSGODSTG3");
			if (agnt2 == "ScheduleSalesOrder") {
				requestTemplate = getRequestProperty("ScheduleAgentSO");
			} else if (agnt2 == "ReleaseSalesOrder") {
				requestTemplate = getRequestProperty("ReleaseAgentSO");
			} else if (agnt2 == "CreateSalesOrderInvoice") {
				requestTemplate = getRequestProperty("CreateSalesIvoice");
			} else if (agnt2 == "SendSalesOrderInvoice") {
				requestTemplate = getRequestProperty("SendSalesInvoice");
			} else if (agnt2 == "RetrunFullfilment") {
				requestTemplate = getRequestProperty("RetrunRecived");
			} else if (agnt2 == "CreateChainedOrder") {
				requestTemplate = getRequestProperty("CreateChainedOrder");
			} else if (agnt2 == "ResolveBuyersRemorse") {
				requestTemplate = getRequestProperty("BuyersRemore");
			} else if (agnt2 == "RealTimeAvailability") {
				requestTemplate = getRequestProperty("RtamMonitor");
			} else if (agnt2 == "FraudCheck") {
				requestTemplate = getRequestProperty("FraudCheck");
			} else if (agnt2 == "IBAtrigger") {
				requestTemplate = getRequestProperty("IBAtrigger");
			} else if (agnt2 == "BorderFreeAgent") {
				requestTemplate = getRequestProperty("BorderFreeAgent");
			}

			else if (agnt2 == "HBCProcessHoldTypeAgentServer") {
				requestTemplate = getRequestProperty("HBCProcessHoldTypeAgentServer");

			} else if (agnt2 == "HBCConfirmOrderAgent") {
				requestTemplate = getRequestProperty("ConfrimOrder");

			} else if (agnt2 == "AuthAgent") {
				requestTemplate = getRequestProperty("AuthAgent");
			}
			break;

		}
		String name = getProperty("UserCredentials");
		String password = getProperty("PWDCredentials");
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(requestTemplate);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/xml");
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.setHeader("Authorization", "Basic  " + authStringEnc);

		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpResponse httpResponse = null;
		httpResponse = client.execute(httpPost);
		int ResponseCode = httpResponse.getStatusLine().getStatusCode();
		if (ResponseCode == 204) {
			return "AGENT TRIGGERED";
		}
		return "Server is down";
	}

	private ArrayList<String> getOrderStatus(String orderIDValue, String banner, String res1)
			throws IOException, JSONException, JDOMException, InterruptedException {

		String requestTemplate = getRequestProperty("getOrderDetails");
		String requestTemplate1 = requestTemplate.replaceAll("&orderIDValue", orderIDValue);
		String requestTemplate2 = requestTemplate1.replaceAll("&banner", banner);
		String url = null;
		switch (res1) {
		case "omsqa2":
			url = getProperty("QA2getOrder");
			break;

		case "omsqa3":
			url = getProperty("QA3getOrder");
			break;

		case "omsstage2":
			url = getProperty("STG2getOrder");
			break;

		case "omsstage3":
			url = getProperty("STG3getOrder");
			break;

		}

		String finalrequest = requestTemplate2;

		String name = getProperty("UserCredentials");
		String password = getProperty("PWDCredentials");
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(finalrequest);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/xml");
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.setHeader("Authorization", "Basic  " + authStringEnc);

		ResponseHandler<String> handler = new BasicResponseHandler();

		String body = client.execute(httpPost, handler);

		HashMap<String, String> AttributeValue = XMLConvertor(body);

		ArrayList<String> Status = new ArrayList<String>();

		Status.add(AttributeValue.get("MaxLineStatusDesc").toString());
		Status.add(AttributeValue.get("OrderedQty").toString());

		Status.add(AttributeValue.get("OrderReleaseKey").toString());
		Status.add(AttributeValue.get("ItemID").toString());

		Status.add(AttributeValue.get("ShipNode").toString());

		return Status;

	}

	private HashMap<String, String> XMLConvertor(String body) throws JSONException, IOException, JDOMException {

		SAXBuilder saxBuilder = new SAXBuilder();

		InputStream stream = new ByteArrayInputStream(body.getBytes("UTF-8"));

		System.out.println(body);
		Document document = saxBuilder.build(stream);
		Element classElement = document.getRootElement();
		List<Element> Values = classElement.getChildren();
		List<Attribute> ValueNodes = classElement.getAttributes();

		HashMap<String, String> AttributesValues = new HashMap<String, String>();

		// Multimap<String, String> AttributesValues = ArrayListMultimap.create();

		// ListMultimap<String, String> AttributesValues = Multimaps
		// .newListMultimap(new TreeMap<String, Collection<String>>(), new
		// Supplier<List<String>>() {
		// public List<String> get() {
		// return Lists.newArrayList();
		// }
		// });

		for (int temp = 0; temp < Values.size(); temp++) {
			Element Value = Values.get(temp);
			System.out.println("\nCurrent Element :" + Value.getName());

			List<Attribute> Attributes = Value.getAttributes();
			List<Element> Child = Value.getChildren();
			if (!Child.isEmpty()) {

				for (Element f : Child) {

					List<Attribute> ChildAttributes = f.getAttributes();
					List<Element> GrandChild = f.getChildren();

					if (!GrandChild.isEmpty()) {

						for (Element S : GrandChild)

						{
							List<Attribute> GrandChildAttributes = S.getAttributes();

							for (Attribute A : GrandChildAttributes) {

								if (A.getValue().isEmpty()) {
									continue;
								}
								AttributesValues.put(A.getName(), A.getValue());

							}

						}
					}

					for (Attribute g : ChildAttributes) {

						if (g.getValue().isEmpty()) {
							continue;
						}
						AttributesValues.put(g.getName(), g.getValue());

					}

				}

			}

			for (Attribute e : Attributes) {

				if (e.getValue().isEmpty()) {
					continue;
				}
				AttributesValues.put(e.getName(), e.getValue());

			}

		}

		return AttributesValues;

	}

	private int ShipSLDCOrder(String orderID, String banner, String res1, String Qty, String ReleaseKey, String ItemID,
			String ShipNode) throws IOException {

		String requestTemplate = getRequestProperty("ShippingAgent");
		String requestTemplate1 = requestTemplate.replaceAll("&orderIDValue", orderID);
		String requestTemplate2 = requestTemplate1.replaceAll("&banner", banner);
		String requestTemplate3 = requestTemplate2.replaceAll("&OrderedQty", Qty);
		String requestTemplate4 = requestTemplate3.replaceAll("&ReleaseKey", ReleaseKey);
		String requestTemplate5 = requestTemplate4.replaceAll("&ItemID", ItemID);
		String requestTemplate6 = requestTemplate5.replaceAll("&ShipNode", ShipNode);

		String url = null;
		switch (res1) {
		case "omsqa2":
			url = getProperty("WSShipQA2");
			break;

		case "omsqa3":
			url = getProperty("WSShipQA3");
			break;

		case "omsstage2":
			url = getProperty("WSShipSTG2");
			break;

		case "omsstage3":
			url = getProperty("WSShipSTG3");
			break;

		}

		String finalrequest = requestTemplate6;

		String name = getProperty("UserCredentials");
		String password = getProperty("PWDCredentials");
		String authString = name + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(finalrequest);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/xml");
		httpPost.setHeader("Content-type", "application/xml");
		httpPost.setHeader("Authorization", "Basic  " + authStringEnc);

		ResponseHandler<String> handler = new BasicResponseHandler();

		// String body = client.execute(httpPost, handler);
		HttpResponse httpResponse = null;
		httpResponse = client.execute(httpPost);
		int ResponseCode = httpResponse.getStatusLine().getStatusCode();
		return ResponseCode;

		// TODO Auto-generated method stub

	}

	public String ShipmentCall(String OrderID, String Banner, String res1)
			throws IOException, JSONException, JDOMException, InterruptedException {
		ArrayList<String> Status = getOrderStatus(OrderID, Banner, res1);

		switch (Status.get(0)) {

		case "Released":
			int ResponseCodeforShip = ShipSLDCOrder(OrderID, Banner, res1, Status.get(1), Status.get(2), Status.get(3),
					Status.get(4));
			if (ResponseCodeforShip == 200) {
				System.out.println("Shipment Confirmed");

			} else {
				System.out.println("Error in Shipment");
			}
			break;
		}
		ArrayList<String> FinalStatus = getOrderStatus(OrderID, Banner, res1);

		System.out.println("Final Status of the Order is " + FinalStatus.get(0));
		if (FinalStatus.get(0).equals("Shipped")) {
			return "Shipped";
		} else {
			return "Error in Shipment";
		}

	}

	public String PartialShipmentCall(String OrderID, String Banner, String res1, String qty)
			throws IOException, JSONException, JDOMException, InterruptedException {
		ArrayList<String> Status = getOrderStatus(OrderID, Banner, res1);

		switch (Status.get(0)) {

		case "Released":
			int ResponseCodeforShip = ShipSLDCOrder(OrderID, Banner, res1, qty, Status.get(2), Status.get(3),
					Status.get(4));
			if (ResponseCodeforShip == 200) {
				System.out.println("Shipment Confirmed");

			} else {
				System.out.println("Error in Shipment");
			}
			break;
		}
		ArrayList<String> FinalStatus = getOrderStatus(OrderID, Banner, res1);

		System.out.println("Final Status of the Order is " + FinalStatus.get(0));
		if (FinalStatus.get(0).equals("Shipped")) {
			return "SHIPPED";
		} else {
			return "Error in Shipment";
		}

	}

	String getProperty(String Prop) throws IOException {

		Properties prop = new Properties();
		InputStream input = null;

		input = this.getClass().getResourceAsStream("Config.properties");
		prop.load(input);
		String Value = prop.getProperty(Prop);
		return Value;

	}

	String getRequestProperty(String Prop) throws IOException {

		Properties prop = new Properties();
		InputStream input = null;

		input = this.getClass().getResourceAsStream("Requests.properties");
		prop.load(input);
		String Value = prop.getProperty(Prop);
		return Value;

	}
}