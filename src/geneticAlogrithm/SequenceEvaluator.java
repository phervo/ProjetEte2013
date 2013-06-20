package geneticAlogrithm;

import java.util.List;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import files.FileGestion;


public class SequenceEvaluator implements FitnessEvaluator<Sequence>{
	private Sequence targetSequence = null;

	
	public SequenceEvaluator(Sequence targetSequence){
		this.targetSequence=targetSequence;
	}
	
	@Override
	public double getFitness(Sequence candidate, List<? extends Sequence> population) {
		// TODO Auto-generated method stub
		int matches=0;
		for (int i = 0; i < candidate.getLength(); i++){
			if (candidate.getValuesAt(i) == targetSequence.getValuesAt(i))
            {
                ++matches;
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
