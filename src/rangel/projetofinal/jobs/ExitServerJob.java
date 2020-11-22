package rangel.projetofinal.jobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Rangel Cardoso Dias
 * @matricula UC18200693
 * 
 * @implNote Class responsable for finish the server;
 */
public class ExitServerJob extends Thread{

	
	/** PROPERTIES */

	private ServerSocket ss;
	private ArrayList<Socket> sockets;
	
	
	/** INIT */

	public ExitServerJob(ServerSocket ss) {
		this.ss = ss;
		this.sockets = new ArrayList<Socket>();
	}
	
	@Override
	public void run() {
		super.run();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			
			int option = 1;
			
			try {
				option = Integer.parseInt(in.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (option == 0){
				try {
					System.out.println("SERVER TURNED OFF");
					ss.close();
					for(Socket s : sockets) {
						s.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
			
		}
		
		
		
	}
	
}