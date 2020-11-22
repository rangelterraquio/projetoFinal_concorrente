package rangel.projetofinal.core;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rangel.projetofinal.listeners.ChatListener;
import rangel.projetofinal.listeners.ChatListenerDelegate;
import rangel.projetofinal.models.ChatMessage;
import rangel.projetofinal.models.User;
import rangel.projetofinal.views.View;
/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote  Class responsable for Client's chat;
 */

public class ChatClient extends Thread implements ChatListenerDelegate{

	/** VIEW */
	JFrame chatWindow;
	
	JTextArea chatArea = new JTextArea(22,45);
	
	public JTextField textField = new JTextField(40);
	
	JLabel blackLabel = new JLabel("       ");
	
	JButton sendButton = new JButton("SEND");
	
	
	/** PROPERTIES CLIENT */
	
	private ObjectOutputStream out;

	private ObjectInputStream in;
	
	private User user;

	private Socket socket;
	

	/** GETTERS AND SETTERS */
	

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public  ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	public ChatClient(User user){
		setUser(user);

		chatWindow = new JFrame(getUser().getName().toUpperCase() + "'s " + "CHAT" );
		chatWindow.setLayout(new FlowLayout());
		
		
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(blackLabel);
		chatWindow.add(textField);
		chatWindow.add(sendButton);
		
		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatWindow.setSize(550, 500);
		chatWindow.setVisible(true);
		
		
		textField.setEditable(true);
		chatArea.setEditable(false);
		
		
		
		ChatListener listener = new ChatListener(this);
		

		sendButton.addActionListener(listener);
		textField.addActionListener(listener);
	
		

			try {
				socket = new Socket("localhost",ChatServer.PORT);
				
				setOut(new ObjectOutputStream(socket.getOutputStream()));
				setIn(new ObjectInputStream(socket.getInputStream()));

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	
	

	@Override
	public void run() {
		super.run();
		try {
			startChat();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Method to stat the chat;
	 * @throws IOException 
	 * @throws UnknownHostException */
	private void startChat() throws UnknownHostException, IOException {
		

		while(true) {
			
			ChatMessage serverMsg;
			try {
				serverMsg = (ChatMessage) in.readObject();
				chatArea.append(serverMsg.getAuthor().getName() + ": " + serverMsg.getMsg() + " \n");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
				socket.close();
			}
			
		}
		
	}
	

	
	
	/**
	 * Method triggered when send button it's clicked;
	 */
	@Override
	public void sendButtonClicked() {
		String text = textField.getText();
		
		if (!text.isEmpty()) {
			
			ChatMessage msg = new ChatMessage(getUser(), text);
			try {
				getOut().writeObject(msg);
				getOut().reset();
				textField.setText("");
			} catch (IOException e) {
				View.showErrorMSG("ERROR","IT WAS NOT POSSIBLE TO SEND A MESSAGE, TRY IT AGAIN;");
				e.printStackTrace();
			}

		}
	}



	
	
	
	
	
	
	
	
}
