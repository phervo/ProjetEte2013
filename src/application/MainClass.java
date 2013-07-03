package application;

import java.util.ArrayList;

import communication.ClientSide;
import communication.ServerSide;
import elements.Formant;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;
import messages.MessageGestion;
import geneticAlogrithm.GeneticAlgorithmCall;



public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ServerSide.launchPraat(); //pas un thread
		ServerSide.initPraat(MessageGestion.writePraatScriptHeader());
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13); //init
		ga.startAlgorithm();
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();
		ServerSide.closePraat();//non plus*/
		
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
		
		
		///////////////test de la nouvelle impl de Formant et FSequence/////
		Formant f1= new Formant();
		System.out.println(f1.toString());
		f1.setAmplitude(0.1);
		f1.setBandwith(0.1);
		f1.setFrequency(0.1);
		System.out.println(f1.toString());
		Formant f2 = new Formant(0.0, 0.2, 0.3);
		System.out.println(f2.toString());
		Formant f3 = new Formant(0.0, 0.2, 0.3);
		
		FormantSequence fs1 = new FormantSequence(1);
		fs1.displayFormantSequence();
		try {
			fs1.setFormantAt(0, new Formant(0.1,0.1,0.1));
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fs1.displayFormantSequence();
		FormantSequence fs2 = new FormantSequence("blop");
		fs2.displayFormantSequence();
		ArrayList l= new ArrayList<Formant>();
		l.add(f1);
				l.add(f2);
						l.add(f3);
		try {
			FormantSequence fs3 = new FormantSequence("nomBidon",3,l);
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fs2.displayFormantSequence();
		try {
			FormantSequence fs3 = new FormantSequence("nomBidon",2,l); //wrong
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
		}
		try {
			fs1.setFormantAt(1, new Formant());
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
		}
		
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

