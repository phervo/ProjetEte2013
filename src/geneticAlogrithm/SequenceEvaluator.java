package geneticAlogrithm;

import java.util.List;
import java.util.concurrent.Semaphore;

import messages.MessageToPraat;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import praatGestion.OrderToPraat;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;
import exceptions.PraatScriptException;

/** <p>Class which define the Fitness function for your GA.<br/>
 * you had to implement it to use the watchmer's GA API.<br/>
 * Here we defined it for a Sequence object.<br/>
 * For more details, look at the watchmaker API<br/>
 * The principle is the following one:<br/>
 * The Ga got an instance of type SequenceEvaluator and call to the fitness function.<br/>
 * The fitness function lanch a praat script with the candidate and compare the formant's answer with the target's ones.<br/>
 * To be sure that the fitness function wait for the answer of praat, the launch of the function put a mutex lock.<br/>
 * This lock is releasw in the function getMessageFromPraat of the class GeneticAlgorithmCall when we are sure we get the answer</p>
 * 
 *
 * 
 * @see Sequence
 * @see GeneticAlgorithmCall
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class SequenceEvaluator implements FitnessEvaluator<Sequence>{
	/**
	 * The FormantSequence that is our target. We will use the values of this sequence as a comparison point
	 * with the values we get from praat to say if the candidate sound is good or not.
	 * 
	 * 
	 */
	private FormantSequence targetSequence = null;
	
	/**
	 * The genetic algorithm. It is just a reference we need to get the messageFromPraat value.
	 * 
	 * 
	 */
	private GeneticAlgorithmCall ga=null; 
	
	/**
	 *  a semaphore wich allows to execute the rest of the fitness function while we get a answer from praat.
	 *  It is lock by default and released when we are sure the java server have store a value into the ga.
	 * 
	 * 
	 */
	private final Semaphore answerFromPraat = new Semaphore(1, true);
	
	/**
	* Constructor with given parameters.
	* 
	*
	* @param target
	* 	the target (see description above)
	*
	* @param ga
	* 	the genetic Algorithm (see description above)
	* 
	* @see SequenceEvaluator#ga
	* @see SequenceEvaluator#targetSequence
	*
	* @since 0.1
	*
	*/
	public SequenceEvaluator(FormantSequence target,GeneticAlgorithmCall ga){
		/*create the instance with the target and a reference to the ga to have access to the stored message from praat*/
		this.targetSequence=target;
		this.ga=ga;
		try {
			answerFromPraat.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //locked
	}
	
	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It define how the fitness score is calculated comparing the candidate and the target.
	* It is used in the GACall.
	* This function put a mutex lock on herself to be sure that the GA wait for the answer of praat before evaluating the next candidate.
	* 
	* 
	* @param candidate
	*	 the candidate to the evaluation
	* 
	* @param population
	* 	?
	* 
	* @see FitnessEvaluator
	*
	* @since 0.1
	*
	*/
	@Override
	public double getFitness(Sequence candidate, List<? extends Sequence> population) {
		// TODO Auto-generated method stub
		//max 9 in fitness
		int matches=0;
		int diffF1=0;
		int diffF2=0;
		int diffF3=0;
		int formantFound=0;
		//0) put a mutex
		try {
			ga.getMutexFitnessFunction().acquire();
			
			/*
			 * 1) we try to see if the sequence is equal to one of the previous one. If is is the case, we reaffect the previous fitness value.
			 */
			System.out.println("candidat :"+candidate.getValuesInString());
			//a ce moment precis j ai une nouvelle sequence, c est maintenant que je peux regarder s il correpsond a l un des precendents
			if(ga.getPreviousGeneration()!=null && candidate.getFitnessScore()==0.0){ //avoid to reaffect those which already got a score(elites)
				System.out.println("je passe dans la reaffectation de score");
				for(int i=0;i<ga.getPreviousGeneration().size();i++){
					if(candidate.equals(ga.getPreviousGeneration().get(i))){
						//then copy all the informations
						candidate.setF1(ga.getPreviousGeneration().get(i).getF1());
						candidate.setF2(ga.getPreviousGeneration().get(i).getF2());
						candidate.setF3(ga.getPreviousGeneration().get(i).getF3());
						candidate.setFormantFound(ga.getPreviousGeneration().get(i).getFormantFound());
						candidate.setFitnessScore(ga.getPreviousGeneration().get(i).getFitnessScore());
					}
				}
			}
			/*2) analyse of the candidate. If it corresponds to a previous sentence (already calculated) then avoid the recalculation and
			*    directly affect the values.
			*    Else send the script to praat.
			*/
			if(candidate.getFitnessScore()!=0.0){
				//case previously existing in a former run
				System.out.println("EEEHHH JE SUIS DIFFERENT !!!");
				matches = (int) candidate.getFitnessScore();
			}else{
				/*write value in the script send to praat and send it*/
				MessageToPraat.writePraatScriptWithCandidates(candidate);
				OrderToPraat.sendCandidiateScriptToPrat(System.getProperty("user.dir") + "/results/fichierEncours.praat");
				
				answerFromPraat.acquire();	
				//store the formants from praat in the sequence :
				candidate.setF1(ga.getMessageFromPraat().getFormantAt(0));
				candidate.setF2(ga.getMessageFromPraat().getFormantAt(1));
				candidate.setF3(ga.getMessageFromPraat().getFormantAt(2));
				candidate.setFormantFound("none");
				
	    		/* this part is for fitness score.
	    		 * I calculate the difference beetween the candidate formants and the target formants.
	    		 * then i add all this values to get a value of the difference to the target.
	    		 * This is what i return as "matches"
	    		 * 
	    		 */
	    		diffF1=Math.abs((int) (candidate.getF1().getFrequency()-ga.getTarget().getFormantAt(0).getFrequency()));
	    		diffF2=Math.abs((int) (candidate.getF2().getFrequency()-ga.getTarget().getFormantAt(1).getFrequency()));
	    		diffF3=Math.abs((int) (candidate.getF3().getFrequency()-ga.getTarget().getFormantAt(2).getFrequency()));
	    		matches=diffF1+diffF2+diffF3;
			
	    		/*
	    		 * this part is just for logs in the cvs, not used for the fitness score itself
	    		 */
		    	for(int i=0;i<this.targetSequence.getNbFormant();i++){
		    		double lowerBornfreq = this.targetSequence.getFormantAt(i).getFrequency()-(this.targetSequence.getAutorisedMargin()*this.targetSequence.getFormantAt(i).getFrequency());
		    		double upperBornfreq = this.targetSequence.getFormantAt(i).getFrequency()+(this.targetSequence.getAutorisedMargin()*this.targetSequence.getFormantAt(i).getFrequency());
		    		System.out.println("lowerBornfreq :"+lowerBornfreq);
		    		System.out.println("upperBornfreq :"+upperBornfreq);
		    		System.out.println("valure formant :"+ga.getMessageFromPraat().getFormantAt(i).getFrequency());
		    		
		    		if((ga.getMessageFromPraat().getFormantAt(i).getFrequency()>=lowerBornfreq && ga.getMessageFromPraat().getFormantAt(i).getFrequency()<=upperBornfreq)){
						formantFound++;
						candidate.setFormantFound("F"+(i+1)); //difference beetween the index and the real formant number
					}
		    		System.out.println("valure formantfound :"+candidate.getFormantFound());
		    	}	
		    		
		    	if(formantFound==2){
		    		candidate.setFormantFound("two");
		    	}else if(formantFound==3){
		    		candidate.setFormantFound("all");
		    	}
		    	candidate.setFitnessScore(matches);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PraatScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("matchScore : "+matches);
		ga.getMutexFitnessFunction().release();
		return matches;
	}

	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It define if the fitness is natural or not. It means if we are looking for a bigger value than the previous one or a lower value.
	* Here in our implementation, it is a bigger value.
	* It is used in the GACall.
	*
	* @see FitnessEvaluator
	*
	* @since 0.1
	*
	*/
	@Override
	public boolean isNatural() {
		// TODO Auto-generated method stub
		return false;
	}

	public Semaphore getAnswerFromPraat() {
		return answerFromPraat;
	}
	
	
}
