package geneticAlogrithm;

import java.util.List;

import messages.MessageGestion;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import communication.ServerSide;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;

public class SequenceEvaluator implements FitnessEvaluator<Sequence>{
	private FormantSequence targetSequence = null;
	private GeneticAlgorithmCall ga=null; 
	
	public SequenceEvaluator(FormantSequence target,GeneticAlgorithmCall ga){
		/*create the instance with the target and a reference to the ga to have access to the stored message from praat*/
		this.targetSequence=target;
		this.ga=ga;
	}
	
	@Override
	public double getFitness(Sequence candidate, List<? extends Sequence> population) {
		// TODO Auto-generated method stub
		//max 9 in fitness
		int matches=0;
		//0) mettre un mutex
		try {
			ga.getMutexFitnessFunction().acquire();
		
		//1) j'envoie le candidat courant (var candidate au script)
		/*write value in the script send to praat and send it*/
		//System.out.println(candidate.getValuesInString());
    	ServerSide.sendMessageToPrat(MessageGestion.writePraatScriptAsCandidates(candidate));
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

	@Override
	public boolean isNatural() {
		// TODO Auto-generated method stub
		return true;
	}
}
