package application;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;

import communication.ServerSide;

import files.FileGestion;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.Sequence;
import geneticAlogrithm.SequenceCrossOver;
import geneticAlogrithm.SequenceEvaluator;
import geneticAlogrithm.SequenceFactory;
import geneticAlogrithm.SequenceMutation;

public class MainClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4);
		ga.startAlgorithm();
		/*String s=FileGestion.writePraatScriptAsCandidatesSansFichier(null);
		System.out.println(s);
		ServerSide.sendMessageToPratV2(s);*/
	}
}