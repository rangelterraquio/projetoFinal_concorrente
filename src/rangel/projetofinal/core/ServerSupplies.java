package rangel.projetofinal.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import rangel.projetofinal.models.User;

public class ServerSupplies {

	/** Properties */
	public static int PORT = 9806;
	public static String NAMEREQUIRED = "NAMEREQUiRED";
	public static String INVALIDNAME = "INVALIDNAME";
	public static String NAMEACCEPTED = "NAMEACCEPTED";
	
	public static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<PrintWriter> printWrites = new ArrayList<PrintWriter>();
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("[SERVER ONLINE: WAITING FOR A CLIENT CONNECTION ... ]");
		
		ServerSocket ss = new ServerSocket(PORT);
		
		
		//Fazer um Server handler 
		
		
		while(true) {
			
			Socket socket = ss.accept();
			
			//Fazer um usuario handler
			
			
			
			
		}
		
		
	}
	
	
}
