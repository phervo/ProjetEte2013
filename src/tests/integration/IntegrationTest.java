package tests.integration;

import tests.UnitaryTestsGA;
import vue.DefinitionFrame;
import vue.LaunchFrame;

/** <p>Class where i put my integration test to be sure that if i made modifications on the source code of a class, it will continue
 * to work properly. I will make small functions for each.</p>
 * 
 * @see UnitaryTestsGA
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class IntegrationTest {
	
	public static void testInte(){
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13); //init
		double[] d= {-0.6,0.2,-0.9,-0.2,0.2,-0.6,-0.1,-0.5,-0.2,-0.3,-0.7,0.1,-0.5};
		Sequence sq= new Sequence(13,d);
		ServerThread.initPraat(MessageToPraat.writePraatScriptHeader());
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
	}
	
	public static void testFitness(){
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
	
	
	private static class ClasseInterne{ // internal class for a main
		 public static void main(String[] args){
			// DefinitionFrame f= new DefinitionFrame();
			 LaunchFrame f2= new LaunchFrame();
		 }
	}
}
