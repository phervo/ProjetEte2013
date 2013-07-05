package geneticAlogrithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import messages.MessageFromPraat;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;


import org.uncommons.watchmaker.framework.termination.TargetFitness;

import communication.ServerSide;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;

/** <p>This is the main Class for the GA.<br/>
 * It use the Genetic algorithm device provide by the watchMaker's API and the class I redefined for it. See the corresponding doc for more information<br/>
 * In order to get a clean code, i have divided the big operation in small functions.<br/>
 * All this methods are called in the function startAlgorithm.</p>
 * 
 * @see Sequence
 * @see SequenceEvaluator
 * @see SequenceCrossOver
 * @see SequenceFactory
 * @see SequenceMutation
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class GeneticAlgorithmCall{
	
	/**
	 * The length of the sequence we will use. It musn't be modified ones it is set.
	 * 
	 */
	int length;
	
	/**
	 * The alphabet with all the possible values for the box of a sequence.
	 * 
	 */
	private double[] candidatList;
	
	/**
	 * The values of the formant in the target
	 * 
	 */
	private FormantSequence target;
	
	/**
	 * The sequenceFactory used by the GA
	 * 
	 */
	private SequenceFactory mySequenceFactory;
	
	/**
	 * The pipeline that allow to use both mutation qnd cross over in the GA, See the watchMaker doc for more information
	 * 
	 */
	private EvolutionaryOperator<Sequence> pipeline;
	
	/**
	 * The SequenceEvaluation used by the GA
	 * 
	 */
	private SequenceEvaluator mySeqEval;
	
	/**
	 * The SelectionStrategy used by the GA
	 * 
	 */
	private SelectionStrategy<Object> selection;
	
	/**
	 * The Random generator used by the GA
	 * 
	 */
	private Random rng;
	
	/**
	 * The EvolutionEngine used by the GA
	 * 
	 */
	private EvolutionEngine<Sequence> engine;
	
	/**
	 * The message received From Praat by the server. It is a String so it must be cast by the splitChaineToFormantSequence function 
	 * 
	 * @see MessageFromPraat#splitChaineToFormantSequence(String)
	 */
	private FormantSequence messageFromPraat;
	
	/**
	 * A mutex to protect the value of messageFromPraat and be sure it is not read and modify in the same time
	 * 
	 * @see  GeneticAlgorithmCall#messageFromPraat
	 */
	private final Semaphore availablevalue = new Semaphore(1, true);
	/**
	 * A mutex to protect the execution of the fitness function. The goal is to forbid it to be run while the previous execution isnt finished.
	 * this is necessary cause of the delay of praat and the GA. So we need to synchronized them this way. 
	 * 
	 * @see  GeneticAlgorithmCall#messageFromPraat
	 */
	private final Semaphore availableFitnessfunction = new Semaphore(1, true); 
	
	/**
	* Constructor where we specified the length of the sequence we will use. Initialize the other attribute to null.
	* 
	*
	* @param length
	* 	the length of the Sequence use in the GA
	 * @throws FormantNumberexception 
	* 
	* @see GeneticAlgorithmCall#length
	*
	* @since 0.1
	*
	*/
	public GeneticAlgorithmCall(int length) throws FormantNumberexception {
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
		this.messageFromPraat=new FormantSequence(2); //it is just an init
	}
	
	/**
	* Initialize the target
	*
	*
	* @since 0.1
	*
	*/
	public void buildTarget(){
		/*its here that we define the target*/
		this.target=new FormantSequence("i"); //i by default
		//this.target.displayFormantSequence();
	}

	/**
	* Initialize the alphabet
	*
	*
	* @since 0.1
	*
	*/
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
		this.candidatList[10]=0.0;
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
	
	/**
	* Initialize the CandidateFactory
	*
	*
	* @since 0.1
	*
	*/
	public void createCandidateFactory(){
		/*
		 * use the alphabet define previously to create a factorycandidate of type SequenceFactory*/
		mySequenceFactory = new SequenceFactory(this.candidatList, this.length);
	}
	
	/**
	* Initialize the EvolutionaryOperator
	*
	*
	* @since 0.1
	*
	*/
	public void createEvolutionaryOperator(){
		List<EvolutionaryOperator<Sequence>> operators
	    = new LinkedList<EvolutionaryOperator<Sequence>>();
		operators.add(new SequenceMutation(this.candidatList,new Probability(0.02)));
		operators.add(new SequenceCrossOver());
		pipeline = new EvolutionPipeline<Sequence>(operators);
	}
	
	/**
	* Initialize the FitnessEvalutator
	*
	*
	* @since 0.1
	*
	*/
	public void createFitnessEvalutator(){
		mySeqEval=new SequenceEvaluator(this.target,this);
	}
	
	/**
	* Initialize the Selection
	*
	*
	* @since 0.1
	*
	*/
	public void createSelection(){
		selection=new RouletteWheelSelection();
	}
	
	/**
	* Initialize the RandomGenerator
	*
	*
	* @since 0.1
	*
	*/
	public void createRandomGenerator(){
		rng=new MersenneTwisterRNG();
	}
	
	/**
	* Main method, use all the creating methodes above, create a evolutionObserver and launch the GA
	*
	*
	* @since 0.1
	*
	*/
	public void startAlgorithm(){
		//load the different elements
		this.generateAlphabet();
		this.buildTarget();
		this.createCandidateFactory();
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
				    }
				});
		engine.evolve(10, 0, new TargetFitness(3,true));
	}

	/**
	* Method which return the message from praat store in the instance.
	* It use a mutex to be sure the resource isn't read and write in the same time.
	* It lock the resource at the beginning and release it at the end.
	*
	* @return the formant sequence where the message from praat is store
	*
	* @since 0.1
	*
	*/
	public synchronized FormantSequence getMessageFromPraat() { 
		FormantSequence temp=null;
		try {
			availablevalue.acquire();
			temp=this.messageFromPraat;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availablevalue.release();
		return temp;
	}

	/**
	* Method which store the message from praat  in the instance.
	* It use a mutex to be sure the resource isn't read and write in the same time.
	* It lock the resource at the beginning and release it at the end.
	* It also release the lock put by the fitness function and allow the next iteration.
	*
	*
	* @since 0.1
	*
	*/
	public synchronized void setMessageFromPraat(FormantSequence messageFromPraat) {
		try
		{
			availablevalue.acquire();
			this.messageFromPraat = messageFromPraat;
			this.availableFitnessfunction.release();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availablevalue.release();
	}

	public Semaphore getMutexFitnessFunction() {
		return availableFitnessfunction;
	}
}
