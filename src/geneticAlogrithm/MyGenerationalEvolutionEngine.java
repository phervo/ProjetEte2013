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

/**
 * I overwrite the existing class in the api to store the population each time and have a comparaison point with the new individuals created via evolutionary operators
 * @author phervo
 *
 * @param <T>
 * 	the type of object you make evolve
 */
public class MyGenerationalEvolutionEngine<T> extends GenerationalEvolutionEngine<T> {
	
	 	private final EvolutionaryOperator<T> evolutionScheme;
	    private final FitnessEvaluator<? super T> fitnessEvaluator;
	    private final SelectionStrategy<? super T> selectionStrategy;
	    private final GeneticAlgorithmCall myGa;
	    
		public MyGenerationalEvolutionEngine(CandidateFactory<T> candidateFactory,
			EvolutionaryOperator<T> evolutionScheme,
			FitnessEvaluator<? super T> fitnessEvaluator,
			SelectionStrategy<? super T> selectionStrategy, Random rng,
			GeneticAlgorithmCall myGa) {
		super(candidateFactory, evolutionScheme, fitnessEvaluator, selectionStrategy,
				rng);
		this.evolutionScheme = evolutionScheme;
        this.fitnessEvaluator = fitnessEvaluator;
        this.selectionStrategy = selectionStrategy;
        this.myGa=myGa;
		// TODO Auto-generated constructor stub
	}
		
		public MyGenerationalEvolutionEngine(CandidateFactory<T> candidateFactory,
                EvolutionaryOperator<T> evolutionScheme,
                InteractiveSelection<T> selectionStrategy,
                Random rng,
                GeneticAlgorithmCall myGa)
				{
				super(candidateFactory, evolutionScheme, selectionStrategy,rng);
				this.evolutionScheme = evolutionScheme;
		        this.fitnessEvaluator = null;
		        this.selectionStrategy = selectionStrategy;
		        this.myGa=myGa;
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
	        
	      	//I change the population after creating the new individuals above so I can still compare the new individual to the "old","parent" population
	        this.myGa.setPreviouGeneration((List<Sequence>) population);// no need to be sort
	        return evaluatePopulation(population);
			
         }

}
