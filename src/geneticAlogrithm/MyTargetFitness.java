package geneticAlogrithm;

import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;
import elements.Sequence;

/**
 * I rewrite the TargetFitness class from the watchmaker APi.
 * The original one stop the algorithm when a specific fitness score is reach.
 * With my fitness function, I need to verify that that in addition, the three formats are in the correct margin.
 * 
 * @author phervo
 *
 */


public class MyTargetFitness implements TerminationCondition{
	private final double targetFitness;
    private final boolean natural;

    /**
     * @param targetFitness The fitness score that must be achieved by at least
     * one individual in the population in order for this condition to be satisfied.
     * @param natural Whether fitness scores are natural or non-natural.  If fitness
     * is natural, the condition will be satisfied if any individual has a fitness
     * that is greater than or equal to the target fitness.  If fitness is non-natural,
     * the condition will be satisfied in any individual has a fitness that is less
     * than or equal to the target fitness.
     * @see org.uncommons.watchmaker.framework.FitnessEvaluator
     */
    public MyTargetFitness(double targetFitness, boolean natural)
    {
        this.targetFitness = targetFitness;
        this.natural = natural;
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldTerminate(PopulationData<?> populationData)
    {
    	boolean result=false;
    	Sequence seq =  (Sequence) populationData.getBestCandidate();
        if (natural)
        {
            // If we're using "natural" fitness scores, higher values are better.
        	if(populationData.getBestCandidateFitness() >= targetFitness && seq.getFormantFound().equals("F1 F2 F3")){
        		result=true;
        	}
        }
        else
        {
        	 // If we're using "non-natural" fitness scores, lower values are better.
        	if(populationData.getBestCandidateFitness() <= targetFitness && seq.getFormantFound().equals("F1 F2 F3")){
        		result=true;
        	}
        }
        return result;
    }
}
