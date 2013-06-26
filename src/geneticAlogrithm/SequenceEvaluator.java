package geneticAlogrithm;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

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
		return matches;
	}

	@Override
	public boolean isNatural() {
		// TODO Auto-generated method stub
		return true;
	}
}
