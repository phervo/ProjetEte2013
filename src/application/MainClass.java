
package application;

import java.util.ArrayList;

import praatGestion.OrderToPraat;
import praatGestion.Praat;
import communication.CloseServer;
import communication.ServerThread;
import exceptions.FormantNumberexception;
import messages.MessageToPraat;
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
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(8); //init
		ServerThread.getInstance(ga);
		ga.startAlgorithm();
		CloseServer.envoyerMessageFermeture();
		//OrderToPraat.closePraat();
		
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13); //init
		double[] d= {-0.6,0.2,-0.9,-0.2,0.2,-0.6,-0.1,-0.5,-0.2,-0.3,-0.7,0.1,-0.5};
		Sequence sq= new Sequence(13,d);
		ServerSide.initPraat(MessageGestion.writePraatScriptHeader());
		for(int i=0;i<50;i++){
			long begin = System.currentTimeMillis();
			//ServerSide.sendMessageToPrat(MessageGestion.writePraatScriptAsCandidatesWithoutCalculs(sq));
			ServerSide.sendMessageToPrat(MessageGestion.writePraatScriptAsCandidates(sq));
			long end = System.currentTimeMillis();
			float time = ((float) (end-begin)) / 1000f;
			MessageGestion.writeInTheLogs(Float.toString(time));
		}
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();*/
		
	
		///////test de la fonction fitness///////////////
		//FileGestion.splitChaineToFormantSequence("0 1 2 3 4 5");
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4); //init
		ga.startAlgorithm();
		FormantSequence fc =new FormantSequence();
		
		SequenceEvaluator seqeval = new SequenceEvaluator(fc, ga);
		double mondouble = seqeval.getFitness(null, null);
		System.out.println(mondouble);
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();*/
	}
}

