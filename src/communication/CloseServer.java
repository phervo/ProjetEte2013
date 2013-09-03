package communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import elements.ModeleString;

/** <p>Class which contains the method to close the server.<br/>
 *  As the server is a thread which is only listening, it can't close itself.<br/>
 *  so this class is designed to send it the termination string while the server is still listening <br/>
 * 	All the method are static so no need to instantiate an object</p>
 * 
 *  @see ServerThread
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class CloseServer {
	
	/**
	    * Constructor, Private to forbid people to instantiate 
	    * just overwrite the default constructor
	    *
	    *
	    * @since 0.1
	    *
	    */
	private CloseServer(){
		
	}

	/**
     * Send to the server the termination string to close it.
     * It send the escape character on the port where the server is listening.
     *
     * @since 0.1
     *
     */
	public static void envoyerMessageFermeture(ModeleString ms){
		ms.setMyString("send request to quit");
		//System.out.println("Envoi demande de fermeture");
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(),2009);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
	        out.println("FIN");
	        out.flush();
	        out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}	
}
