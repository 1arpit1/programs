package ssh.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.ObjectInputStream.GetField;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellTest {
	private JSch sch = null;
	private String username;
	private String password;
	private int port;
	private String serverName;
	private Session session;
	private ChannelShell channel;

	java.util.Properties config = new java.util.Properties();

	public ShellTest(String user, String pass, String host, int port)
			throws Exception {

		this.port = port;
		this.username = user;
		this.serverName = host;
		this.password = pass;
		sch = new JSch();
		config.put("StrictHostKeyChecking", "no");

	}

	private ChannelShell getChannel() throws Exception {
		if (channel != null && !channel.isConnected()) {
			try {
				channel = (ChannelShell) session.openChannel("shell");
			} catch (Throwable t) {
				session = sch.getSession(this.username, this.serverName,
						this.port);
				session.setPassword(this.password);
				session.setConfig(config);
				session.setServerAliveInterval(10000000);
				session.connect();
				channel = (ChannelShell) session.openChannel("shell");
			}
		} else if (session == null) {
			session = sch.getSession(this.username, this.serverName, this.port);
			session.setPassword(this.password);
			session.setConfig(config);
			session.setServerAliveInterval(10000000);
			session.connect();
			channel = (ChannelShell) session.openChannel("shell");
		} else if (channel == null) {
			channel = (ChannelShell) session.openChannel("shell");
		}

		channel.setOutputStream(System.out, true);
		return channel;

	}

	private void setInputStream(InputStream stream, Channel channel)
			throws Exception {
		channel.setInputStream(stream, true);
		channel.connect();
	}

	public static void main(String[] args) throws Exception {
		int port = 0;
		ShellTest test = new ShellTest("user", "pass", "server", port);
		InputStream stream = ClassLoader
				.getSystemResourceAsStream("command.txt");
		test.setInputStream(stream, test.getChannel());
	   

	}

}
