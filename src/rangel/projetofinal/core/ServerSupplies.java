package rangel.projetofinal.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import rangel.projetofinal.jobs.MenuUserHandler;
import rangel.projetofinal.models.User;

public class ServerSupplies {

	/** Properties */
	public static int PORT = 9806;
	public static String NAMEREQUIRED = "NAMEREQUiRED";
	public static String INVALIDNAME = "INVALIDNAME";
	public static String NAMEACCEPTED = "NAMEACCEPTED";
	public static String REMOVESUCESSEFULLY = "REMOVESUCESSEFULLY";
	public static String REMOVEFAILED = "REMOVEFAILED";
	
	
	public static ArrayList<User> users = new ArrayList<User>();
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("[SERVER ONLINE: WAITING FOR A CLIENT CONNECTION ... ]");
		
		ServerSocket ss = new ServerSocket(PORT);
		
		
		//Fazer um Server handler 
		
		
		while(true) {
			
			Socket socket = ss.accept();
			System.out.println("[SERVER] NEW CONNECTION ESTABLISHED!");

			//Fazer um usuario handler
			MenuUserHandler handler = new MenuUserHandler(socket);
			handler.start();
			
			
			
		}
		
		
	}
	
	
}
