package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ChatAppClient {
	private String ip;
	private int port;
	private String message = "";
	private JTextArea area;
	

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatAppClient(String ip, int port, JTextArea area) {
		this.ip = ip;
		this.port = port;
		this.area = area;
		this.area.setEditable(false);
	
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				area.setText(area.getText() + "Server: " + is.readObject() + "\n");
				//System.out.println(is.readObject());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	}public String getMessage() {
		
		
		
		return message;
	}
}
