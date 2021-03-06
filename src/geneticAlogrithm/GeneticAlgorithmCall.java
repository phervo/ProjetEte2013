package geneticAlogrithm;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import messages.MessageFromPraat;
import messages.MessageToPraat;
import monitoring.MonitoringCSV;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.StochasticUniversalSampling;
import org.uncommons.watchmaker.framework.termination.ElapsedTime;

import praatGestion.Praat;
import elements.FormantSequence;
import elements.GlobalAlphabet;
import elements.ModeleString;
import elements.Sequence;
import exceptions.FormantNumberexception;
import exceptions.PraatScriptException;

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
	 * The length of the sequence we will use. It musn't be modified onces it is set.
	 * 
	 */
	int length;
	
	/**
	 * The alphabet with all the possible values for the box of a sequence.
	 * 
	 */
	private GlobalAlphabet alphabet;
	
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
	private MyGenerationalEvolutionEngine<Sequence> engine;
	
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
	 * Var to store the best candidate a each generation. 
	 * This value will be use at he end to write the correct praat script.
	 */
	private Sequence finalsequence;
	
	/**
	 * praat object wich indicate the state in wich praat is. It will be use to made praat evolv beetween the different possible state
	 * and be sure the differnet operations are used at the good time.
	 * 
	 */
	
	/**
	 * indicate the time at which we launch the algorithm, it is utse to calculate the execution time.
	 * initiate in the constructor
	 */
	private long start=0;
	
	private Praat praatObject;
	
	private List<Sequence> previousGeneration;
	
	private ModeleString modele;
	
	/**
	 * indicate the number of generations that the complete run last
	 */
	private int nbGeneration;
	
	/**
	 * probability of the mutation to append
	 */
	private Probability mutationProb;
	
	/**
	 * var wich indicate the number of time I got the same result as the best candidate of the generation.
	 * I use it to dfetect whenever the algorithm cant evolve further, it stay in the same local optimum.
	 * I will then increase the mutation probability.
	 * 
	 * @see GeneticAlgorithmCall#increaseMutationProbability
	 */
	private int nbSameResultCount;

	private SequenceMutation seqMuta;
	
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
	public GeneticAlgorithmCall(int length, ModeleString modele) throws FormantNumberexception {
		super();
		this.length=length;
		this.modele=modele;
		this.alphabet=null;
		this.target=null;
		this.mySequenceFactory= null;
		this.pipeline=null;
		this.mySeqEval=null;
		this.selection=null;
		this.rng=null;
		this.engine=null;
		this.messageFromPraat=new FormantSequence(2); //it is just an init
		this.finalsequence=null;
		this.praatObject=null;
		this.start=System.currentTimeMillis();
		//deleting the files in the folder containing the previous results to avoid keeping result that doesnt suit the current run
		emptyDirectory(new File(System.getProperty("user.dir") + "/results/scripts"));
		emptyDirectory(new File(System.getProperty("user.dir") + "/results/sounds"));
		emptyDirectory(new File(System.getProperty("user.dir") + "/results/curve"));
		this.previousGeneration=null;
		this.setNbGeneration(0);
		this.nbSameResultCount=0;
		this.seqMuta=null;
	}
	
	
	/**
	 * build the Praat object and lanch it
	 */
	public void buildPraatState(){
		this.praatObject=new Praat();
		praatObject.launch();
	}
	
	
	/**
	* Initialize the target
	*
	*
	* @since 0.1
	*
	*/
	public void buildTarget(FormantSequence fseq){
		/*its here that we define the target*/
		this.target=fseq;
		//this.target.displayFormantSequence();
	}
	
	/**
	* Initialize the alphabet
	*
	*
	* @since 0.1
	*
	*/
	public void generateAlphabet(){
		/*It is here that we define all the possible values that can be use by the algorithm to generate a candidate
		 * it is the list of the unitary values. They will be combined into a sequence to create a candidate by another function*/
		this.alphabet = new GlobalAlphabet();
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
		mySequenceFactory = new SequenceFactory(this.alphabet, this.length);
	}
	
	/**
	* Initialize the EvolutionaryOperator
	*
	*
	* @since 0.1
	*
	*/
	public void createEvolutionaryOperator(){
		this.mutationProb= new Probability(0.05);
		this.seqMuta=new SequenceMutation(this.alphabet,this.mutationProb);
		List<EvolutionaryOperator<Sequence>> operators
	    = new LinkedList<EvolutionaryOperator<Sequence>>();
		operators.add(new SequenceCrossOver(2));
		operators.add(this.seqMuta);
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
		mySeqEval = new SequenceEvaluator(this.target,this);
	}
	
	/**
	* Initialize the Selection
	*
	*
	* @since 0.1
	*
	*/
	public void createSelection(){
		//selection= new StochasticUniversalSampling();
		selection = new MySelectionOperator();
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
		buildPraatState();
		this.generateAlphabet();
		this.createCandidateFactory();
		this.createEvolutionaryOperator();
		this.createFitnessEvalutator();
		this.createSelection();
		this.createRandomGenerator();
		
		//start the engine
		engine = new MyGenerationalEvolutionEngine<Sequence>(mySequenceFactory, pipeline,mySeqEval , selection, rng,this);
		//engine = new MyGenerationalBidule<Sequence>(mySequenceFactory, pipeline, mySeqEval, selection, rng);
		engine.addEvolutionObserver(new MySequenceEvolutionObserver(this));
		engine.evolve(11, 1, new MyTargetFitness(fitnessMargin(),mySeqEval.isNatural()),new ElapsedTime(10800000));//3 hours
		//save the result in a final file,idem for the csv
		try {
			MessageToPraat.writePraatScriptInFile(this.finalsequence,"praatScriptWithCorrectValues.praat");
			MonitoringCSV.writeCSVFile(System.getProperty("user.dir") + "/results/curve/algoritmProgression.csv",true,getFinalExecTime(),this.getNbGeneration(),this.finalsequence.getFitnessScore(),this.finalsequence,this.getTarget());
		} catch (PraatScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//relaunch praat and display the result we saved during the run
		deleteFile(System.getProperty("user.dir") + "/results/scripts/fichierEncours.praat");
		//praatObject.reLaunch();
		//OrderToPraat.launchAllScripts();
		//praatObject.close();
		//at the end, we launch the monitoring function to display the results
		MonitoringCSV.displayCSV(this.getTarget());
		this.getModele().setFinRun(true);//display the info and quit button of the frame
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
	*
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
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availablevalue.release();
	}

	/**
	* Return the semaphore for the fitness function. It is used in FitnessEvaluator to forbid the function to be launch twice.
	* 
	* @return frequency value of the instance
	*
	* @since 0.1
	*
	*/
	public Semaphore getMutexFitnessFunction() {
		return availableFitnessfunction;
	}
	
	/**
	* Method which set the value of finalSeauence. it is used at each end of generation to store the value of the best candidate.
	*
	* @since 0.1
	*
	*/
	public void setSequence(Sequence c){
		//this.increaseNbSameResultCount(c);
		this.finalsequence = c;
	}
	
	public SequenceEvaluator getMySeqEval(){
		return this.mySeqEval;
	}
	
	
	public Praat getPraatObject(){
		return this.praatObject;
	}
	
	public double getStartTime(){
		return this.start;
	}
	
	/**
	   * function to delete all the files in th edirectory before using it.
	   * It avoid to keep file which arent usefull.
	   * @param folder
	   * 	the folder to clear
	   */
	  public static void emptyDirectory(File folder){
	       for(File file : folder.listFiles()){
	          if(file.isDirectory()){
	            emptyDirectory(file);
	       }
	       file.delete();
	     }
	  }
	  
	  /**
	   * function to delete a specific file.
	   * It avoid to keep file which arent usefull. I use it to delete the temp file on wich i work during the run.
	   * this script is different from those og the save one with sendsocket wich cause a error once the run is finish and the serveur close.
	   * @param file
	   * 	the path to the file
	   */
	  public static void deleteFile(String file){
		  File MyFile = new File(file); 
		  MyFile.delete(); 
	  }
	  
	  public FormantSequence getTarget(){
		return target;
	  }
	  
	  /**
		 * function which calculate the total exec time using the start variable of the geneAlgoCall
		 * @return double containing the time in seconds of execution since the launch
		 */
		public double getFinalExecTime(){
			long end = System.currentTimeMillis();
			float time = ((float) (end-getStartTime())) / 1000f;
			return time;
		}
		
		/**
		 * function wich calculate the margin authorise in the calculation of the formants.
		 * it is this value which will be used to determine when to stop the GA.
		 * @return the min born for the Genetic algorithm
		 */
		public int fitnessMargin(){
			int res=0;
			int bornF1=0;
			int bornF2=0;
			int bornF3=0;
			try {
				bornF1 = (int) (target.getAutorisedMargin()*target.getFormantAt(0).getFrequency());
				bornF2 = (int) (target.getAutorisedMargin()*target.getFormantAt(1).getFrequency());
				bornF3 = (int) (target.getAutorisedMargin()*target.getFormantAt(2).getFrequency());
				res=bornF1+bornF2+bornF3;
			} catch (FormantNumberexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
		
		public void setPreviouGeneration(List<Sequence> previousGeneration){
			this.previousGeneration=previousGeneration;
		}
		public List<Sequence> getPreviousGeneration(){
			return previousGeneration;
		}


		public int getNbGeneration() {
			return nbGeneration;
		}


		public void setNbGeneration(int nbGeneration) {
			this.nbGeneration = nbGeneration;
		}


		public MyGenerationalEvolutionEngine<Sequence> getEngine() {
			return engine;
		}


		public void setEngine(MyGenerationalEvolutionEngine<Sequence> engine) {
			this.engine = engine;
		}


		public ModeleString getModele() {
			return modele;
		}


		public void setModele(ModeleString modele) {
			this.modele = modele;
		}
		
		/**
		 * method which increase the existing value of 0.01 if the GA is stagnant
		 * For this, i used the nbSameResultCount var to know if I reach 5 time the same value.
		 */
		public void increaseMutationProbability(){
			if(this.nbSameResultCount==5){
				double previousValue=this.mutationProb.doubleValue();
				double newValue=previousValue+0.01;
				this.mutationProb=new Probability(newValue);
				this.seqMuta= new SequenceMutation(this.alphabet,this.mutationProb);
				this.nbSameResultCount=0;
				System.out.println("AUGMENTATION DE LA PROBABILITE DE MUTATION"+previousValue+" "+newValue);
			}
		}
		
		/**
		 * method where I increase the var NbSameResultCount if the result of the current generation is the same than the previous one.
		 * 
		 */
		public void increaseNbSameResultCount(Sequence newBestCandidate){
			if(this.finalsequence!=null && this.finalsequence.equals(newBestCandidate)){
				this.nbSameResultCount+=1;
				this.increaseMutationProbability();
			}else{
				this.nbSameResultCount=0;
			}
		}
}
