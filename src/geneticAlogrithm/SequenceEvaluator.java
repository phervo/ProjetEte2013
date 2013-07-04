package geneticAlogrithm;

import java.util.List;

import messages.MessageToPraat;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import communication.ServerSide;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;
import exceptions.PraatScriptException;
import exceptions.SequenceArrayException;

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
	}
	
	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It define how the fitness score is calculated comparing the candidate and the target.
	* It is used in the GACall.
	* This function put a mutex lock on herself to be sure that the GA wait for the answer of praat before evaluating the next candidate.
	* 
	* 
	* @param candidate
	*	 the first parent
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
		//0) put a mutex
		try {
			ga.getMutexFitnessFunction().acquire();
		
		//1) j'envoie le candidat courant (var candidate au script)
		/*write value in the script send to praat and send it*/
		//System.out.println(candidate.getValuesInString());
    	try {
			ServerSide.sendMessageToPrat(MessageToPraat.writePraatScriptWithCandidates(candidate));
		} catch (PraatScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2) recuperer le resultat dans messageFromPraat (attente serveur et socket si besoin) et le comparer a la cible
    	for(int i=0;i<this.targetSequence.getNbFormant();i++){
    		if(ga.getMessageFromPraat().getFormantAt(i).getFrequency()==this.targetSequence.getFormantAt(i).getFrequency()){
				matches++;
			}
			if(ga.getMessageFromPraat().getFormantAt(i).getBandwith()==this.targetSequence.getFormantAt(i).getBandwith()){
				matches++;
			}
			if(ga.getMessageFromPraat().getFormantAt(i).getAmplitude()==this.targetSequence.getFormantAt(i).getAmplitude()){
				matches++;
			}
		}
    	System.out.println(candidate.getValuesInString()+" matchScore : "+matches);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		return true;
	}
}
