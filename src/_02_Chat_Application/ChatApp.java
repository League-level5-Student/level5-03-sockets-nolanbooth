package _02_Chat_Application;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ChatApp {
	private int port;
	private JTextArea area;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatApp(int port, JTextArea area) {
		this.port = port;
		this.area = area;
		this.area.setEditable(false);
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				try {
					area.setText(area.getText() + "Client: " + is.readObject()+"\n");
				//	System.out.println(is.readObject());
					
					
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(String message) {
	
		try {
			if (os != null) {
				os.writeObject(message);
				
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
