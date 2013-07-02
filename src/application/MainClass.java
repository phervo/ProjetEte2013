package application;

import communication.ClientSide;
import communication.ServerSide;
import elements.Sequence;
import messages.MessageGestion;
import geneticAlogrithm.GeneticAlgorithmCall;



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

