package tmtvo.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.logging.Logger;

public class Connection extends Observable {
	private final String host;
	private final int port;

	private Socket socket;

	private BufferedWriter output;
	private BufferedReader input;

	private Listener listener;

	public Connection(String host, int port) throws UnknownHostException,
			IOException {
		this.host = host;
		this.port = port;

		connect();
	}

	public Socket getSocket() {
		return socket;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public static Connection connect(String host, int port)
			throws UnknownHostException, IOException {
		return new Connection(host, port);
	}

	public void connect() throws UnknownHostException, IOException {
		if (socket != null)
			return;

		socket = new Socket(host, port);
		output = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream(), "UTF-8"));
		input = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
		
		listener = new Listener();
	}
	
	public void sendObject(Object o) throws IOException {
		if (o == null)
			return;
		
		output.write(o.toString());
		output.newLine();
		output.flush();
	}
	
	public String receiveObject() throws IOException {
		return input.readLine();
	}
	
	public void reconnect() throws IOException {
		close();
		connect();
	}
	
	public boolean isConnected() {
		return socket.isConnected() && !socket.isClosed();
	}
	
	public void close() throws IOException {
		if (listener != null)
			listener.interrupt();
		listener = null;
		
		if (input != null)
			input.close();
		input = null;
		
		if (output != null)
			output.close();
		output = null;
		
		if (socket != null)
			socket.close();
		socket = null;
	}

	private class Listener extends Thread {
		private Listener() {
			setName("Listener for " + host + ":" + port);
			start();
		}

		@Override
		public void run() {
			while (!isInterrupted()) {
				try {
					String text = receiveObject();
					setChanged();
					notifyObservers(text);
				} catch (IOException e) {
					Logger.getLogger(Connection.class.getName()).severe(
							"Could not receive Text.");
				}
			}

			System.out.println(this + " stopped!");
		}
	}
}