package application;

import communication.CloseServer;
import communication.ServerThread;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

/** <p>Main class which call the Genetic algorithm, praat and the java Server<br/>
 * It is the executed part of the program</p>
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */

public class MainClass {
	
	public static void main(String[] args) throws FormantNumberexception {
		// TODO Auto-generated method stub
		GeneticAlgorithmCall ga = new GeneticAlgorithmCall(8); //init
		ServerThread.getInstance(ga);
		ga.startAlgorithm();
		CloseServer.envoyerMessageFermeture();
	}
}

