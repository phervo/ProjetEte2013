package communication;

import messages.MessageGestion;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public final class ServerSide implements Runnable{
	//def instance
	private static volatile ServerSide instance=null;
	//autres attributs
	private ServerSocket socketserver  ;
    private Socket socketduserveur ;
    private GeneticAlgorithmCall ga;
    private final String finChaine= new String("FIN");
    
    //constructeur
    private ServerSide(GeneticAlgorithmCall ga){
    	super();
    	this.ga=ga;
    	try {
			this.socketserver=new ServerSocket(2009);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //methode principqle getInstance
    public final static ServerSide getInstance(GeneticAlgorithmCall ga){
    	if(ServerSide.instance==null){
    		synchronized(ServerSide.class){
    			if(ServerSide.instance ==null){
    				ServerSide.instance = new ServerSide(ga);
    			}
    		}
    	}
    	return ServerSide.instance;
    }
    
    public void closeServer(){
    	try {
			this.socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("server ferme");
    }
    
	public ServerSide(ServerSocket socketserver, Socket socketduserveur) {
		super();
		this.socketserver = socketserver;
		this.socketduserveur = socketduserveur;
	}
	
	public static void launchPraat(){
		/*launch praat */
		Runtime run = Runtime.getRuntime();
		String[] sendpraatCom ={"praat"};
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initPraat(String fileName){
		/*execute the script to generate the robovox and the speaker*/
		String[] sendpraatCom ={"sendpraat", "praat",fileName};
		Runtime run = Runtime.getRuntime();
		try {
			Process runProcess=run.exec(sendpraatCom);
			runProcess.waitFor(); //le thread qui l'a lance attend la fin de l'execution
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMessageToPrat(String fileName){
		String[] sendpraatCom ={"sendpraat", "praat",fileName};
		Runtime run = Runtime.getRuntime();
		try {
			Process runProcess=run.exec(sendpraatCom);
			runProcess.waitFor(); //le thread qui l'a lance attend la fin de l'execution
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closePraat(){
		String[] sendpraatCom ={"sendpraat", "praat","Quit"};
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void storeMessageReceivedFromPraat(String chaine){ // try a dl
		try {
			ga.setMessageFromPraat(MessageGestion.splitChaineToFormantSequence(chaine));
		} catch (FormantNumberexception e) {
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

	@Override
	public void run() {
	// TODO Auto-generated method stub
    	try {
			String message_distant="";
    		while(message_distant.compareTo(finChaine)!=0){
				socketduserveur = socketserver.accept();
				BufferedReader in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
				message_distant = in.readLine().trim();
				storeMessageReceivedFromPraat(message_distant);
				System.out.println("J'ai recu le message suivant : "+message_distant);
				socketduserveur.close();
			}
    		this.closeServer(); //important
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
