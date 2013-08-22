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
	 * the list of the 
	 */
	private List<Sequence> previousPopulation;
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
	public SequenceEvaluator(FormantSequence target,GeneticAlgorithmCall ga,List<Sequence> previousPopulation){
		/*create the instance with the target and a reference to the ga to have access to the stored message from praat*/
		this.targetSequence=target;
		this.ga=ga;
		this.previousPopulation=previousPopulation;
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
		int formantFound=0;
		//0) put a mutex
		try {
			ga.getMutexFitnessFunction().acquire();
		
		//1) j'envoie le candidat courant (var candidate au script)
		/*write value in the script send to praat and send it*/
			System.out.println("candidat :"+candidate.getValuesInString());
			//a ce moment precis j ai une nouvelle sequence, c est maintenant que je peux regarder s il correpsond a l un des precendents
			if(previousPopulation!=null){
				for(int i=0;i<previousPopulation.size();i++){
					if(candidate.equals(previousPopulation.get(i))){
						candidate.setFitnessScore(previousPopulation.get(i).getFitnessScore());
					}
				}
			}
			if(candidate.getFitnessScore()!=0.0){
				System.out.println("EEEHHH JE SUIS DIFFERENT !!!");
				matches = (int) candidate.getFitnessScore();
			}else{
				MessageToPraat.writePraatScriptWithCandidates(candidate);
				OrderToPraat.sendCandidiateScriptToPrat(System.getProperty("user.dir") + "/results/fichierEncours.praat");
				//2) recuperer le resultat dans messageFromPraat (attente serveur et socket si besoin) et le comparer a la cible
				
				
				answerFromPraat.acquire();	
				//store the formants from praat in the sequence :
				candidate.setF1(ga.getMessageFromPraat().getFormantAt(0));
				candidate.setF2(ga.getMessageFromPraat().getFormantAt(1));
				candidate.setFormantFound("none");
		    	for(int i=0;i<this.targetSequence.getNbFormant();i++){
		    		//there is an interval of +/-10% around the value so we caluclate this value
		    		double lowerBornfreq = this.targetSequence.getFormantAt(i).getFrequency()+(this.targetSequence.getAutorisedMargin()*this.targetSequence.getFormantAt(i).getFrequency());
		    		double upperBornfreq = this.targetSequence.getFormantAt(i).getFrequency()+(this.targetSequence.getAutorisedMargin()*this.targetSequence.getFormantAt(i).getFrequency());
		    		//double lowerBornBW = this.targetSequence.getFormantAt(i).getBandwith()*0.9;
		    		//double upperBornBW = this.targetSequence.getFormantAt(i).getBandwith()*1.1;
		    		//double lowerBornA = this.targetSequence.getFormantAt(i).getAmplitude()*0.9;
		    		//double upperBornA = this.targetSequence.getFormantAt(i).getAmplitude()*1.1;
		    		
		    		//just for print in the cvs, not use for the fitness itself
		    		if((ga.getMessageFromPraat().getFormantAt(i).getFrequency()>=lowerBornfreq && ga.getMessageFromPraat().getFormantAt(i).getFrequency()<=upperBornfreq)){
						formantFound++;
						candidate.setFormantFound("F"+(i+1)); //difference beetween the index and the real formant number
					}
		    		
		    		diffF1=Math.abs((int) (candidate.getF1().getFrequency()-ga.getTarget().getFormantAt(0).getFrequency()));
		    		diffF2=Math.abs((int) (candidate.getF2().getFrequency()-ga.getTarget().getFormantAt(1).getFrequency()));
		    		matches=diffF1+diffF2;
					//if(ga.getMessageFromPraat().getFormantAt(i).getBandwith()>=lowerBornBW && ga.getMessageFromPraat().getFormantAt(i).getBandwith()<=upperBornBW ){
					//	matches++;
					//}
					//if(ga.getMessageFromPraat().getFormantAt(i).getAmplitude()>=lowerBornA && ga.getMessageFromPraat().getFormantAt(i).getAmplitude()<=upperBornA){
					//	matches++;
					//}
				}
		    	if(formantFound==2){
		    		candidate.setFormantFound("both");
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
