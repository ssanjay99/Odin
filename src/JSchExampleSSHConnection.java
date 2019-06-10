
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class JSchExampleSSHConnection {

	/**
	 * JSch Example Tutorial Java SSH Connection Program
	 * 
	 * @return
	 */
	public void Restart(String host, String Username, String Password, String Command1) {
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(Username, host, 22);
			session.setPassword(Password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(Command1);
			System.out.println(Command1);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
					channel.disconnect();
				}
				if (channel.isClosed()) {
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			session.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Stop(String host, String Username, String Password, String Command1) {
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(Username, host, 22);
			session.setPassword(Password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(Command1);
			System.out.println(Command1);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			// while (true) {
			// while (in.available() > 0) {
			// int i = in.read(tmp, 0, 1024);
			// if (i < 0)
			// break;
			// System.out.print(new String(tmp, 0, i));
			// }
			// if (channel.isClosed()) {
			// System.out.println("exit-status: " + channel.getExitStatus());
			// break;
			// }
			// try {
			// Thread.sleep(1000);
			// } catch (Exception ee) {
			// }
			// }
			channel.disconnect();
			session.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	}
