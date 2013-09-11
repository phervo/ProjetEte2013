package communication;

import messages.MessageFromPraat;
import exceptions.CastFormantException;
import geneticAlogrithm.GeneticAlgorithmCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import praatGestion.OrderToPraat;

/** <p>Class wich define a thread containing a Java server that will listen for praat answer in a particular port.<br/>
 * As praat communicate with sockets, it is an obligation to have a server that is listening all the time so the server is design to be a thread.<br/>
 * A waiting mechanism is defined to be sure that the action lanch in this class are finished before doing anything else.<br/>
 * To be sure that there is only one instance of the server at every moment, this class is design on the singleton design pattern principle<br/>
 * As it will be launch in a new thread and wont do anything until catching a message,we create a specific class which will send it the terminaison String
 * It is the class CloseServer<br/>
 * IMPORTANT : the implementation need to implements Thread and not extends Thread server to be sure that the thread is created in getInstance and not elsewhere.</p>
 * 
 * @see CloseServer
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public final class ServerThread extends Thread{
	/**
	 * The instance of ServerSide.
	 * 
	 */
	private static volatile ServerThread instance=null;
	
	/**
	 * a ServerSocket to listen on a particular port.
	 * 
	 */
	private ServerSocket socketserver  ;
	
	/**
	 * a socket to get the messages in input
	 * 
	 */
    private Socket socketduserveur ;
    
    /**
	 * a reference to the ga to store the message it get.
	 * 
	 */
    private GeneticAlgorithmCall ga;
   
    /**
   	 * a String to end the while loop qnd stop listening
   	 * 
   	 */
    private final String finChaine= new String("FIN");
    
    /**
    * Constructor, Private according to the singleton pattern 
    * 
    *
    * @param ga
    * 	see explanation above
    *
    * @since 0.1
    *
    */
    private ServerThread(GeneticAlgorithmCall ga){
    	super();
    	this.ga=ga;
    	try {
			this.socketserver=new ServerSocket(2009);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Method getInstance, it either create or give the reference to the instance.
     * For more information, see the singleton design pattern documentation
     * 
     *
     * @param ga
     * 	see explanation above
     *
     * @since 0.1
     *
     */
    public final static ServerThread getInstance(GeneticAlgorithmCall ga){
    	if(ServerThread.instance==null){
    		synchronized(ServerThread.class){
    			if(ServerThread.instance ==null){
    				ServerThread.instance = new ServerThread(ga);
    				Thread t = new Thread(ServerThread.instance,"ThreadServer");
    				t.start();
    			}
    		}
    	}
    	return ServerThread.instance;
    }
    
    /**
     * Method getInstance, it either create or give the reference to the instance.
     * For more information, see the singleton design pattern documentation
     * 
     *
     *
     * @since 0.1
     *
     */
    public void closeServer(){
    	try {
			this.socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ga.getModele().setMyString("server closed");
    	//System.out.println("server ferme");
    }
	
    /**
     * function which store the message the server get from Praat in the ga field messageFromPraat.
     * It will cast it with splitChaineToFormantSequence() from MessageFromPraat.
     * 
     *
     * @param chaine
     * 	the string from praat.
     * 
     * @see MessageFromPraat#splitChaineToFormantSequence(String)
     *
     * @since 0.1
     *
     */
	public void storeMessageReceivedFromPraat(String chaine){ //seauentiel donc on peu mettre lq
		try {
			ga.setMessageFromPraat(MessageFromPraat.splitChaineToFormantSequence(chaine));
		} catch (CastFormantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.display();
		}
		//ga.getMutexFitnessFunction().release();
	}

	/**
     * the run function. This class is design for the instance to be run in a thread and be run apart from the Ga part.
     *
     * @since 0.1
     *
     */
	@Override
	public void run() {
	// TODO Auto-generated method stub
    	try {
    		ga.getModele().setMyString("server launched");
    		//System.out.println("lancement server");
			String message_distant="";
    		while(message_distant.compareTo(finChaine)!=0){
				socketduserveur = socketserver.accept();
				BufferedReader in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
				message_distant = in.readLine().trim();
				//it is "FIN" OR "LAUNCH"
				//it is message i use to communicate so no need to make a specific tretment to cast in Formant behind
				if(message_distant.compareTo("LAUNCH")==0){
					OrderToPraat.PraatLaunch=true;//it means the praat software is lauch so we put the boolean to true
				}else{
					storeMessageReceivedFromPraat(message_distant);
					ga.getMySeqEval().getAnswerFromPraat().release(); // we realease the sem here
				}
				ga.getModele().setMyString("I received the message :"+message_distant);
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
