package rangel.projetofinal.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import rangel.projetofinal.jobs.ChatUserHandler;
import rangel.projetofinal.jobs.ExitServerJob;
/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Class responsable for Server chat;
 * 
 *  @see Como executar o projeto
	- 1 . Executar ServerSupplies
	- 2 . Executar ChatServer
	- 3 . Executar SuppliesClient
 *
 */
public class ChatServer {

	
	/** PROPERTIES */
	public static int PORT = 9807;

	public static ArrayList<ObjectOutputStream> objectsOutput = new ArrayList<ObjectOutputStream>();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		System.out.println("[CHAT SERVER ONLINE: WAITING FOR A CLIENT CONNECTION ... ]");
		
		ServerSocket ss = new ServerSocket(PORT);
		
		ExitServerJob exitJob = new ExitServerJob(ss);
		exitJob.start();
		
		while(true){
			
			Socket socket = ss.accept();
			System.out.println("[CHAT SERVER] NEW USER ON CHAT!");
			ChatUserHandler handler = new ChatUserHandler(socket);	
			handler.start();
			
			
		}
		
	}
}
