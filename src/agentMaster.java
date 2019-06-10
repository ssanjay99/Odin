public class agentMaster {
	public String Startagent(String res1, String res2) {

		JSchExampleSSHConnection Start = new JSchExampleSSHConnection();
		System.out.println(res2);
		if (res1 == "omsqa2") {
			try {
				Start.Restart("hd3qoms04lx.digital.hbc.com", "omsqa2", "omsqa2",
						"/opt/qa2/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				Start.Restart("hd3qoms09lx.digital.hbc.com", "omsqa2", "omsqa2",
						"/opt/qa2/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);

				return "AGENT STARTED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";
			}

		} else if (res1 == "omsqa3") {
			try {
				Start.Restart("hd3qoms06lx.digital.hbc.com", "omsqa3", "omsqa3",
						"/opt/qa3/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				Start.Restart("hd3qoms12lx.digital.hbc.com", "omsqa3", "omsqa3",
						"/opt/qa3/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				return "AGENT STARTED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		} else if (res1 == "omsstage2")
			try {
				Start.Restart("hd3sagt03lx.digital.hbc.com", "omsstage2", "omsstage2",
						"/opt/stage2/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				Start.Restart("hd3sagt04lx.digital.hbc.com", "omsstage2", "omsstage2",
						"/opt/stage2/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				return "AGENT STARTED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		else if (res1 == "omsstage3")
			try {
				Start.Restart("hd3sagt05lx.digital.hbc.com", "omsstage3", "omsstage3",
						"/opt/stage3/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				Start.Restart("hd3sagt06lx.digital.hbc.com", "omsstage3", "omsstage3",
						"/opt/stage3/oms/9.4/Foundation/bin/startHbcAgentServer.sh " + res2);
				return "AGENT STARTED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		return "server down";

	}

	public String stopagent(String res1, String res2) {
		JSchExampleSSHConnection Start = new JSchExampleSSHConnection();
		System.out.println(res2);
		if (res1 == "omsqa2") {
			try {
				Start.Stop("hd3qoms04lx.digital.hbc.com", "omsqa2", "omsqa2", "pkill -f " + res2);
				Start.Stop("hd3qoms09lx.digital.hbc.com", "omsqa2", "omsqa2", "pkill -f " + res2);

				return "AGENT STOPPED";
			} catch (Exception e) {
				e.printStackTrace();
				return "Failed";
			}
		} else if (res1 == "omsqa3") {
			try {
				Start.Stop("hd3qoms06lx.digital.hbc.com", "omsqa3", "omsqa3", "pkill -f " + res2);
				Start.Stop("hd3qoms12lx.digital.hbc.com", "omsqa3", "omsqa3", "pkill -f " + res2);
				return "AGENT STOPPED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		} else if (res1 == "omsstage2") {
			try {
				Start.Stop("hd3sagt03lx.digital.hbc.com", "omsstage2", "omsstage2", "pkill -f " + res2);
				Start.Stop("hd3sagt04lx.digital.hbc.com", "omsstage2", "omsstage2", "pkill -f " + res2);
				return "AGENT STOPPED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		} else if (res1 == "omsstage3") {
			try {
				Start.Stop("hd3sagt05lx.digital.hbc.com", "omsstage3", "omsstage3", "pkill -f " + res2);
				Start.Stop("hd3sagt06lx.digital.hbc.com", "omsstage3", "omsstage3", "pkill -f " + res2);
				return "AGENT STOPPED";
			} catch (Exception e) {
				e.printStackTrace();
				return "FAILED";

			}
		}
		return "Server Down";
	}
	
	
}
	


