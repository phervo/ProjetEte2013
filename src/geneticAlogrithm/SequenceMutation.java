package geneticAlogrithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import elements.Sequence;

public class SequenceMutation implements EvolutionaryOperator<Sequence>{
	private final double[] alphabet;
    private final NumberGenerator<Probability> mutationProbability;
    
    public SequenceMutation(double[] alphabet, Probability mutationProbability)
    {
    	this.alphabet = alphabet.clone();
    	this.mutationProbability = new ConstantGenerator<Probability>(mutationProbability);
    }
    
    public SequenceMutation(double[] alphabet, NumberGenerator<Probability> mutationProbability){
    	this.alphabet = alphabet.clone();
    	this.mutationProbability = mutationProbability;
    }
    
	@Override
	public List<Sequence> apply(List<Sequence> selectedCandidates, Random rng) {
		// TODO Auto-generated method stub
		List<Sequence> mutatedPopulation = new ArrayList<Sequence>(selectedCandidates.size());
        for (Sequence s : selectedCandidates)
        {
            mutatedPopulation.add(mutateString(s, rng));
        }
        return mutatedPopulation;
	}
	
	private Sequence mutateString(Sequence s, Random rng)
    {
		Sequence retour= s;
        for (int i = 0; i < s.getLength(); i++)
        {
            if (mutationProbability.nextValue().nextEvent(rng))
            {
            	retour.setValues(i, alphabet[rng.nextInt(alphabet.length)]);
            }
        }
        return retour;
    }

}
