package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;









import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;

import communication.CloseServer;
import communication.ServerSide;
import elements.Formant;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.SequenceCrossOver;
import geneticAlogrithm.SequenceFactory;
import geneticAlogrithm.SequenceMutation;

/** <p>Class where i put my unitary test to be sure that if i made modifications on the source code of a class, it will continue
 * to work properly. I will make small functions for each. It is only unitary test, another class is available for integration test</p>
 * 
 * @see IntegrationTest
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class UnitaryTestsGA {
	
	/**
	 * private constructor to forbid to instanciate
	 */
	private UnitaryTestsGA(){
		
	}
	
	/**
	* Test if the server launch and close in a new thread as it should.
	*
	
	* @since 0.1
	*
	*/
	public static void servertest(){
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(13);
		ServerSide serverJavaGa= ServerSide.getInstance(ga);
		Thread t = new Thread(serverJavaGa,"ThreadServer");
		t.start();
		CloseServer.envoyerMessageFermeture();
	}
	
	/**
	* Test of fromants and FormantsSequence, if the method  works and if the exception are raised when needed
	*
	* @since 0.1
	*
	*/
	public static void testFormantAndFormantSequence(){
		//1st the formants
		Formant f1= new Formant();
		System.out.println("1 : must return all values at 0.0  =>    "+f1.toString());
		f1.setAmplitude(0.1);
		f1.setBandwith(0.1);
		f1.setFrequency(0.1);
		System.out.println("2 : must return all values at 0.1  =>    "+f1.toString());
		Formant f2 = new Formant(0.0, 0.2, 0.3);
		System.out.println("3 : must return the values 0.0  0.2  0.3  =>    "+f2.toString());
		Formant f3 = new Formant(0.0, 0.2, 0.3);
		System.out.println();//space in the display
		
		//2nd the FormantSequence
		FormantSequence fs1;
		try {
			fs1 = new FormantSequence(1);
			System.out.println("4 : must return a sentence with 1 formant and 0.0 values");
			fs1.displayFormantSequence();
			System.out.println();//space in the display
			
			System.out.println("4.5 : must launch a exception for 0 size");
			fs1 = new FormantSequence(0);
			fs1.displayFormantSequence();
			
		} catch (FormantNumberexception e1) {
			// TODO Auto-generated catch block
			//error message in the exception
		}
		System.out.println();//space in the display
		
		try {
			System.out.println("5 : must return a sentence with 1 formant and 0.1 values");
			fs1 = new FormantSequence(1);
			fs1.setFormantAt(0, new Formant(0.1,0.1,0.1));
			fs1.displayFormantSequence();
			System.out.println();//space in the display
			
			System.out.println("5.5 : must launch a exception for wrong index");
			fs1.setFormantAt(1, new Formant(0.1,0.1,0.1));
			fs1.displayFormantSequence();
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			//error message in the exception
		}
		
		System.out.println();//space in the display
		
		System.out.println("6 : must return a sentence with 2 formant, 0.0 values and test as a name");
		FormantSequence fs2 = new FormantSequence("test");
		fs2.displayFormantSequence();
		System.out.println("6.5 : must return a sentence with 2 formant, 0.0 values and default name");
		fs2= new FormantSequence("");
		fs2.displayFormantSequence();
		System.out.println();//space in the display
		
		ArrayList<Formant> l= new ArrayList<Formant>();
		l.add(f1);
		l.add(f2);
		l.add(f3);
		try {
			System.out.println("7 : must return a sentence with 3 formant, values from f1 f2 f3 and nomBidon name");
			FormantSequence fs3 = new FormantSequence("nomBidon",3,l);
			fs3.displayFormantSequence();
			System.out.println();//space in the display
			System.out.println("7.5 : must launch a exception for too much object in the list in parameter");
			fs3 = new FormantSequence("nomBidon",2,l);
			System.out.println();//space in the display
			
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			//error message in the exception
		}
		System.out.println();//space in the display
		try {
			System.out.println("7.5.2 : must launch a exception for not enough box in the list");
			FormantSequence fs3 = new FormantSequence("nomBidon",4,l);
			System.out.println();//space in the display
			
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			//error message in the exception
		}
		System.out.println();//space in the display
		
		try {
			System.out.println("8 : must display a value");
			FormantSequence fs3 = new FormantSequence("nomBidon",3,l);
			System.out.println(fs3.getFormantAt(2).getAmplitude());
			System.out.println();//space in the display
			System.out.println("8.5 : must raise en exception for wrong index");
			System.out.println(fs3.getFormantAt(3).getAmplitude());
	
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
		}
		System.out.println();//space in the display
		
		try {
			System.out.println("9 : must print a value");
			fs1 = new FormantSequence(1);
			fs1.setFormantAt(0, new Formant());
			fs1.displayFormantSequence();
			System.out.println();//space in the display
			System.out.println("9.5 : must raise en exception for wrong index");
			fs1.setFormantAt(1, new Formant());
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public static void test(){
		/*note j'ai tout balancer la pour le moment mais de vrais fonctions seront a faire */
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(10);
		//////////////
		ga.buildTarget();
		//Sequence target= ga.getTarget();
		//target.displaySeq();
		/////////////
		////////////////////test1///////////////////
		ga.generateAlphabet();
		System.out.println("////////////////////test1///////////////////");
		/*for(int i=0;i<ga.getCandidatListLength();i++){
			System.out.println(ga.getCandidatList()[i]);
		}*/
		System.out.println("///////////////////////////////////////");
		////////////////////Test1.5//////////////////
		//Sequence seq = new Sequence(10,ga.getCandidatList());
		System.out.println("////////////////////Test1.5//////////////////");
		/*for(int i=0;i<ga.getCandidatListLength();i++){
			System.out.println(seq.getValues()[i]);
		}*/
		System.out.println("///////////////////////////////////////");
		////////////////////////////////////////////
		ga.createCandidateFactory();
		///////////////////test2////////////////////
		System.out.println("////////////////////test2///////////////////");
		//SequenceFactory c = ga.getMySequenceFactory();
		//System.out.println(c.getLength());
		//c.displayAlphabet();
		Random rng=new MersenneTwisterRNG();
		//Sequence newSec1 = c.generateRandomCandidate(rng);
		//Sequence newSec2 = c.generateRandomCandidate(rng);
		
		//MessageGestion.writeInTheLogs(newSec1.getValuesInString());
		//MessageGestion.writeInTheLogs(newSec2.getValuesInString());
		System.out.println("///////////////////////////////////////");
		System.out.println("///////////////////                 ////////////////////");
		//tester la mutation et le cross over
		SequenceCrossOver sqco= new SequenceCrossOver(); // 1 point
		/*List<Sequence> l1= sqco.mate(newSec1, newSec2, 1, rng);
		for(int i=0;i<l1.size();i++){
			System.out.println("pour cross over sequence n "+i);
			l1.get(i).displaySeq();
		}
		SequenceMutation sqm= new SequenceMutation(ga.getCandidatList(),new Probability(0.02));
		List<Sequence> l2=sqm.apply(l1, rng);
		for(int i=0;i<l2.size();i++){
			System.out.println("pour mutation sequence n "+i);
			l2.get(i).displaySeq();
		}
		System.out.println("///////////////////////////////////////");
		//SequenceEvaluator seqeval=new SequenceEvaluator();
		//double res=seqeval.getFitness(null, null);
		//System.out.println(res);
		System.out.println("///////////////////////////////////////");
		
		double[] tab = {1.1,1.5};
		//Sequence candidate= new Sequence(2,tab);
		//double res=ga.getMySeqEval().getFitness(candidate, null);
		//System.out.println(res);
		
		

		////tests pour le nouveau script d'extraction//////
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4); //init
		ServerSide.launchPraat();
		ServerSide.initPraat(FileGestion.writePraatScriptHeader());
		ServerSide.sendMessageToPrat(FileGestion.writePraatScriptAsCandidates(null));
	*/
		
		//FormantSequence f= new FormantSequence();
		//f.displayFormantSequence();
		
		/*String s=FileGestion.writePraatScriptAsCandidatesSansFichier(null);
		System.out.println(s);
		ServerSide.sendMessageToPrat(s);*/
		
	}
	
	 private static class ClasseInterne{ // internal class
		 public static void main(String[] args){
			 UnitaryTestsGA.testFormantAndFormantSequence();
		 }
		 
	 }
	
}
