package application;

import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;

import communication.ClientSide;
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
		/*ServerSide.launchPraat(); //pas un thread
		ServerSide.initPraat(FileGestion.writePraatScriptHeader());
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4); //init
		ga.startAlgorithm();
		ClientSide cs= new ClientSide();
		cs.envoyerMessageFermeture();
		ServerSide.closePraat();//non plus*/
		
		////tests pour le nouveau script d'extraction//////
		GeneticAlgorithmCall ga= new GeneticAlgorithmCall(4); //init
		ServerSide.launchPraat();
		ServerSide.initPraat(FileGestion.writePraatScriptHeader());
		ServerSide.sendMessageToPrat(FileGestion.writePraatScriptAsCandidates(null));
		
	}
}