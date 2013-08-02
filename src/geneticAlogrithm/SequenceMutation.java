package geneticAlogrithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import elements.GlobalAlphabet;
import elements.Sequence;
import exceptions.SequenceArrayException;

/** <p>Class which define the Mutation operation for an object of type Sequence<br/>
 * you had to implement it to use the watchmer's GA algorithm.<br/>
 * For more details, look at the watchmaker API</p>
 * 
 * @see Sequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class SequenceMutation implements EvolutionaryOperator<Sequence>{
	/**
	 * The alphabet of all the possible values that a member of a Sequence could take.
	 * 
	 */
	private final GlobalAlphabet globalAlphabet;
	/**
	 * The mutation probability.
	 * 
	 */
    private final NumberGenerator<Probability> mutationProbability;
    
    /**
	* Constructor with given parameters 
	*
	*
	* @param  globalAlphabet
	* 	see description above
	* @param mutationProbability
	* see description above
	* 
	* @see SequenceMutation#globalAlphabet
	* @see SequenceMutation#mutationProbability
	* 
	* @since 0.1
	*
	*/
    public SequenceMutation(GlobalAlphabet globalAlphabet, Probability mutationProbability)
    {
    	this.globalAlphabet = globalAlphabet;
    	this.mutationProbability = new ConstantGenerator<Probability>(mutationProbability);
    }
    
    /**
	* Constructor with given parameters. Same thing that the previous one but wih another object type for Probability
	*
	*
	* @param  globalAlphabet
	* 	see description above
	* @param mutationProbability
	* see description above
	* 
	* @see SequenceMutation#globalAlphabet
	* @see SequenceMutation#mutationProbability
	* 
	* @since 0.1
	*
	*/
    public SequenceMutation(GlobalAlphabet globalAlphabet, NumberGenerator<Probability> mutationProbability){
    	this.globalAlphabet = globalAlphabet;
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
	
	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It define the operation of Mutation for a Sequence.
	* It produce a new Sequence by using the Cross over operation on a random box of the array (with a probability).
	* It is used in the GACall.
	* 
	* 
	* @param s
	*	 the original sequence.
	* 
	* @param rng
	* 	a random generator.
	* 
	* @return Sequence where a mutation has been applied.
	*
	* @since 0.1
	*
	*/
	private Sequence mutateString(Sequence s, Random rng)
    {
		Sequence retour= s;
        for (int i = 0; i < s.getLength(); i++)
        {
            if (mutationProbability.nextValue().nextEvent(rng))
            {
            	try {
            		if(i==12 || i==13){ //masseter
						retour.setValues(i, globalAlphabet.getMasseterAlphabet().getValueAt(rng.nextInt(globalAlphabet.getMasseterAlphabet().getLength())));
					}else{
						retour.setValues(i, globalAlphabet.getOtherAlphabet().getValueAt(rng.nextInt(globalAlphabet.getOtherAlphabet().getLength())));
					}
				} catch (SequenceArrayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        return retour;
    }

}
