package tests.unitary;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;

import communication.ServerSide;

import files.FileGestion;
import geneticAlogrithm.FormantSequence;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.Sequence;
import geneticAlogrithm.SequenceCrossOver;
import geneticAlogrithm.SequenceEvaluator;
import geneticAlogrithm.SequenceFactory;
import geneticAlogrithm.SequenceMutation;

public class UnitaryTestsGA {
	public void test(){
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
		for(int i=0;i<ga.getCandidatListLength();i++){
			System.out.println(ga.getCandidatList()[i]);
		}
		System.out.println("///////////////////////////////////////");
		////////////////////Test1.5//////////////////
		Sequence seq = new Sequence(10,ga.getCandidatList());
		System.out.println("////////////////////Test1.5//////////////////");
		/*for(int i=0;i<ga.getCandidatListLength();i++){
			System.out.println(seq.getValues()[i]);
		}*/
		System.out.println("///////////////////////////////////////");
		////////////////////////////////////////////
		ga.createCandidateFactory();
		///////////////////test2////////////////////
		System.out.println("////////////////////test2///////////////////");
		SequenceFactory c = ga.getMySequenceFactory();
		System.out.println(c.getLength());
		c.displayAlphabet();
		Random rng=new MersenneTwisterRNG();
		Sequence newSec1 = c.generateRandomCandidate(rng);
		Sequence newSec2 = c.generateRandomCandidate(rng);
		
		FileGestion.writeInTheLogs(newSec1.getValuesInString());
		FileGestion.writeInTheLogs(newSec2.getValuesInString());
		System.out.println("///////////////////////////////////////");
		System.out.println("///////////////////                 ////////////////////");
		//tester la mutation et le cross over
		SequenceCrossOver sqco= new SequenceCrossOver(); // 1 point
		List<Sequence> l1= sqco.mate(newSec1, newSec2, 1, rng);
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
		Sequence candidate= new Sequence(2,tab);
		double res=ga.getMySeqEval().getFitness(candidate, null);
		System.out.println(res);
		
		

		////tests pour le nouveau script d'extraction//////
		/*GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4); //init
		ServerSide.launchPraat();
		ServerSide.initPraat(FileGestion.writePraatScriptHeader());
		ServerSide.sendMessageToPrat(FileGestion.writePraatScriptAsCandidates(null));
	*/
		
		FormantSequence f= new FormantSequence();
		f.displayFormantSequence();
	}
	
	/*String s=FileGestion.writePraatScriptAsCandidatesSansFichier(null);
	System.out.println(s);
	ServerSide.sendMessageToPrat(s);*/
	
	
}
