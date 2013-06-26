package application;

import communication.ClientSide;
import communication.ServerSide;
import messages.MessageGestion;
import geneticAlogrithm.FormantSequence;
import geneticAlogrithm.GeneticAlgorithmCall;

public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSide.launchPraat(); //pas un thread
		ServerSide.initPraat(MessageGestion.writePraatScriptHeader());
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(6); //init
		ga.startAlgorithm();
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();
		ServerSide.closePraat();//non plus
		
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