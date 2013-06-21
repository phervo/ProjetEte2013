package communication;

import geneticAlogrithm.GeneticAlgorithmCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide implements Runnable{
	private ServerSocket socketserver  ;
    private Socket socketduserveur ;
    private GeneticAlgorithmCall ga;
    private final String finChaine= new String("FIN");
    public boolean serverAlreadyLaunch;
    
    public ServerSide(GeneticAlgorithmCall ga){
    	super();
    	this.ga=ga;
    	this.serverAlreadyLaunch=false;
    	try {
			this.socketserver=new ServerSocket(2009);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void closeServer(){
    	this.serverAlreadyLaunch=false;
    	try {
			this.socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("server ferme");
    }
    
    public boolean isServerLanch(){
    	return this.serverAlreadyLaunch;
    }
    
	public ServerSide(ServerSocket socketserver, Socket socketduserveur) {
		super();
		this.socketserver = socketserver;
		this.socketduserveur = socketduserveur;
	}
	
	public static void LaunchPraat(){
		Runtime run = Runtime.getRuntime();
		try {
			run.exec("praat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMessageToPrat(String fileName){
		String[] sendpraatCom ={"sendpraat", "praat",fileName};
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storeMessageReceivedFromPraat(String chaine){
		ga.setMessageFromPraat(chaine);
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.serverAlreadyLaunch=true;
    	try {
			String message_distant="";
    		while(message_distant.compareTo(finChaine)!=0){
				socketduserveur = socketserver.accept();
				//System.out.println("J'ai recu quelque chose !");
				BufferedReader in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
				message_distant = in.readLine().trim();
				storeMessageReceivedFromPraat(message_distant);
				socketduserveur.close();
			}
    		this.closeServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
