package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
	private ServerSocket socketserver  ;
    private Socket socketduserveur ;

    
    public ServerSide(){
    	super();
    	this.socketserver=null;
    	this.socketduserveur=null;
    }
    
	public ServerSide(ServerSocket socketserver, Socket socketduserveur) {
		super();
		this.socketserver = socketserver;
		this.socketduserveur = socketduserveur;
	}
	
	public static void sendMessageToPrat(String fileName){
		String[] sendpraatCom ={"praatcon", fileName}; //chemin locaux
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMessageToPratV2(String fileName){
		//String[] sendpraatCom ={"sendpraat", "praat","Read from file... C:/Users/Py/workspace/ProjetSpeechSynthesis/testsonpoumpoum","Play"}; //chemin locaux
		String[] sendpraatCom ={"sendpraat", "praat",fileName};
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ServerSocket getSocketserver() {
		return socketserver;
	}

	public void setSocketserver(ServerSocket socketserver) {
		this.socketserver = socketserver;
	}

	public Socket getSocketduserveur() {
		return socketduserveur;
	}

	public void setSocketduserveur(Socket socketduserveur) {
		this.socketduserveur = socketduserveur;
	}
	
}
