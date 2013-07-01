package application;

import communication.ClientSide;
import communication.ServerSide;
import messages.MessageGestion;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.Sequence;



public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSide.launchPraat(); //pas un thread
		ServerSide.initPraat(MessageGestion.writePraatScriptHeader());
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13); //init
		ga.startAlgorithm();
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();
		ServerSide.closePraat();//non plus
		
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13); //init
		double[] d= {0.0,0.1,0.0,-0.1,-0.1,-0.7,-0.3,0.2,-0.9,0.1,-0.8,0.0,-0.1};
		Sequence sq= new Sequence(13,d);
		ServerSide.initPraat(MessageGestion.writePraatScriptHeader());
		//ServerSide.sendMessageToPrat(MessageGestion.writePraatScriptAsCandidatesWithoutCalculs(sq));
		ServerSide.sendMessageToPrat(MessageGestion.writePraatScriptAsCandidates(sq));*/
		
		
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

