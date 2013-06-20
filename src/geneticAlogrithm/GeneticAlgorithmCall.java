package geneticAlogrithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.ElapsedTime;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import communication.ServerSide;

import files.FileGestion;

public class GeneticAlgorithmCall {
	/*in order to get a clean code, i have divided the big operation in small fonctions
	 * All this methods are called in the function startAlgorithm at the end
	 * need to use the constructor
	 * */
	int length;
	private double[] candidatList;
	private Sequence target;
	//private CandidateFactory<Sequence> mySequenceFactory;
	private SequenceFactory mySequenceFactory;
	private EvolutionaryOperator<Sequence> pipeline;
	private SequenceEvaluator mySeqEval;
	private SelectionStrategy<Object> selection;
	private Random rng;
	private EvolutionEngine<Sequence> engine;
	
	public GeneticAlgorithmCall(int length) { //fonctionne
		/*
		 * intialse length to the value in parameter and the others to null*/
		super();
		this.length=length;
		this.candidatList=null;
		this.target=null;
		this.mySequenceFactory= null;
		this.pipeline=null;
		this.mySeqEval=null;
		this.selection=null;
		this.rng=null;
		this.engine=null;	
	}
	
	public void buildTarget(){
		/*its here that we define the target*/
		double[] doubleSequence={0.1,0.5,0.7,0.4};
		int longueur=doubleSequence.length;
		this.target=new Sequence(longueur,doubleSequence);
	}

	public void generateAlphabet(){ //fonctionne
		/*It is here that we define all the possible values that can be use by the algorithm to generate a candidate
		 * it is the list of the unitary values. They will be combined into a sequence to create a candidate by another function*/
		//int nbArray=(int) ((upperBound-lowerBound)/0.1)+1; //+1 pr 0.0
		this.candidatList= new double[21];
		//definition manuelle pour le moment
		this.candidatList[0]=-1.0;
		this.candidatList[1]=-0.9;
		this.candidatList[2]=-0.8;
		this.candidatList[3]=-0.7;
		this.candidatList[4]=-0.6;
		this.candidatList[5]=-0.5;
		this.candidatList[6]=-0.4;
		this.candidatList[7]=-0.3;
		this.candidatList[8]=-0.2;
		this.candidatList[9]=-0.1;
		this.candidatList[10]=-0.0;
		this.candidatList[11]=0.1;
		this.candidatList[12]=0.2;
		this.candidatList[13]=0.3;
		this.candidatList[14]=0.4;
		this.candidatList[15]=0.5;
		this.candidatList[16]=0.6;
		this.candidatList[17]=0.7;
		this.candidatList[18]=0.8;
		this.candidatList[19]=0.9;
		this.candidatList[20]=1.0;
	}
	
	// CandidateFactory<Sequence>
	public void createCandidateFactory(){
		/*
		 * use the alphabet define previously to create a factorycandidate of type SequenceFactory*/
		mySequenceFactory = new SequenceFactory(this.candidatList, this.length);
	}
	
	public void createEvolutionaryOperator(){
		List<EvolutionaryOperator<Sequence>> operators
	    = new LinkedList<EvolutionaryOperator<Sequence>>();
		operators.add(new SequenceMutation(this.candidatList,new Probability(0.02)));
		operators.add(new SequenceCrossOver());
		pipeline = new EvolutionPipeline<Sequence>(operators);
	}
	
	public void createFitnessEvalutator(){
		mySeqEval=new SequenceEvaluator(this.target);
	}
	
	public void createSelection(){
		selection=new RouletteWheelSelection();
	}
	
	public void createRandomGenerator(){
		rng=new MersenneTwisterRNG();
	}
	
	public void startAlgorithm(){
		//load the different elements
		this.generateAlphabet();
		this.buildTarget();
		this.createCandidateFactory(); //creation
		this.createEvolutionaryOperator();
		this.createFitnessEvalutator();
		this.createSelection();
		this.createRandomGenerator();
		
		//start the engine
		engine = new GenerationalEvolutionEngine<Sequence>(mySequenceFactory, pipeline, mySeqEval, selection, rng);
		engine.addEvolutionObserver(new EvolutionObserver<Sequence>()
				{
				    public void populationUpdate(PopulationData<? extends Sequence> data)
				    {
				    	System.out.printf("Generation %d: %s\n",
				                          data.getGenerationNumber(),
				                          data.getBestCandidate().getValuesInString());
				    	ServerSide.sendMessageToPratV2(FileGestion.writePraatScriptAsCandidatesSansFichier(data.getBestCandidate()));
				    	System.out.println("y a eu un update");
				    	System.out.println("ligne qui ne sert a rien");
				    }
				});
		engine.evolve(10, 0, new GenerationCount(2));
	}

	
	
	///////////////getters and setters///////////////////
	
	public Sequence getTarget() {
		return target;
	}

	public void setTarget(Sequence target) {
		this.target = target;
	}
	
	public int getLength() {
		return length;
	}

	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getCandidatListLength(){
		return getCandidatList().length;
	}
	
	public double[] getCandidatList() {
		return candidatList;
	}
	
	public double getCandidatListAt(int i) {
		return candidatList[i];
	}
	
	public void setCandidatList(double[] candidatList) {
		this.candidatList = candidatList;
	}
	
	public void setCandidatListAt(int i,double value) {
		this.candidatList[i]=value;
	}

	public SequenceFactory getMySequenceFactory() {
		return mySequenceFactory;
	}

	public SequenceEvaluator getMySeqEval() {
		return mySeqEval;
	}

	public void setMySeqEval(SequenceEvaluator mySeqEval) {
		this.mySeqEval = mySeqEval;
	}
	
	
	
}
