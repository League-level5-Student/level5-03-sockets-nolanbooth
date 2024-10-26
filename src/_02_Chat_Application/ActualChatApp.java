package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ActualChatApp extends JFrame {
	JButton button = new JButton("SEND");
	JTextField field = new JTextField(10);
	JPanel panel = new JPanel();
	JTextArea area = new JTextArea();
	
	
	ChatApp chatApp;
	ChatAppClient chatAppClient;
	
	
	public static void main(String[] args) {
		new ActualChatApp();
	}
	
	public ActualChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			
			
			chatApp = new ChatApp(8080, area);
			setTitle("CHAT APP SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + chatApp.getIPAddress() + "\nPort: " + chatApp.getPort());
			button.addActionListener((e)->{
				String serverMessage = field.getText();
				field.setText("");
				chatApp.sendMessage(serverMessage);
				area.setText(area.getText() + "Server: "+serverMessage+"\n");
				
				
			});
			add(panel);
			panel.add(button);
			
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panel.add(field);
			panel.add(area);
			chatApp.start();
			
		}else{
			setTitle("CHAT APP CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			
			chatAppClient = new ChatAppClient(ipStr, port, area);
			
			button.addActionListener((e)->{
				String clientMessage = field.getText();
				field.setText("");
				chatAppClient.sendMessage(clientMessage);
				area.setText(area.getText() + "Client: "+clientMessage+"\n");
				
				
			});
			add(panel);
			panel.add(button);
			
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panel.add(field);
			panel.add(area);
			chatAppClient.start();
		}
	}
}

