package communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {
	private Socket socket;
	private PrintWriter out;
	
	public ClientSide(){
		/*Constructeur qui gerera la deconnexion serveur*/
			try {
				socket = new Socket(InetAddress.getLocalHost(),2009);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//mon identite et le port du serveur
			out=null;
	}

	public void envoyerMessageFermeture(){
		System.out.println("Envoi demande de fermeture");
		try {
		out = new PrintWriter(socket.getOutputStream());
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
