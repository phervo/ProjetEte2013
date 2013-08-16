package geneticAlogrithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.interactive.InteractiveSelection;

import elements.Sequence;

public class MyGenerationalBidule<T> extends GenerationalEvolutionEngine<T> {
	
	 	private final EvolutionaryOperator<T> evolutionScheme;
	    private final FitnessEvaluator<? super T> fitnessEvaluator;
	    private final SelectionStrategy<? super T> selectionStrategy;

		public MyGenerationalBidule(CandidateFactory<T> candidateFactory,
			EvolutionaryOperator<T> evolutionScheme,
			FitnessEvaluator<? super T> fitnessEvaluator,
			SelectionStrategy<? super T> selectionStrategy, Random rng) {
		super(candidateFactory, evolutionScheme, fitnessEvaluator, selectionStrategy,
				rng);
		this.evolutionScheme = evolutionScheme;
        this.fitnessEvaluator = fitnessEvaluator;
        this.selectionStrategy = selectionStrategy;
		// TODO Auto-generated constructor stub
	}
		
		public MyGenerationalBidule(CandidateFactory<T> candidateFactory,
                EvolutionaryOperator<T> evolutionScheme,
                InteractiveSelection<T> selectionStrategy,
                Random rng)
				{
				super(candidateFactory, evolutionScheme, selectionStrategy,rng);
				this.evolutionScheme = evolutionScheme;
		        this.fitnessEvaluator = null;
		        this.selectionStrategy = selectionStrategy;
				}
		
		protected List<EvaluatedCandidate<T>> nextEvolutionStep(List<EvaluatedCandidate<T>> evaluatedPopulation,
                int eliteCount,
                Random rng)
         {
			List<T> population = new ArrayList<T>(evaluatedPopulation.size());

	        // First perform any elitist selection.
	        List<T> elite = new ArrayList<T>(eliteCount);
	        Iterator<EvaluatedCandidate<T>> iterator = evaluatedPopulation.iterator();
	        while (elite.size() < eliteCount)
	        {
	            elite.add(iterator.next().getCandidate());
	        }
	        // Then select candidates that will be operated on to create the evolved
	        // portion of the next generation.
	        population.addAll(selectionStrategy.select(evaluatedPopulation,
	                                                   fitnessEvaluator.isNatural(),
	                                                   evaluatedPopulation.size() - eliteCount,
	                                                   rng));
	        // Then evolve the population.
	        population = evolutionScheme.apply(population, rng);
	        // When the evolution is finished, add the elite to the population.
	        population.addAll(elite);
	        
	      	for(int i=0;i<population.size();i++){
	      		Sequence s= (Sequence)population.get(i);
	      		System.out.println(s.getValuesInString());
	        	System.out.println();
	        }
	        	
	        return evaluatePopulation(population);
			
         }

}
