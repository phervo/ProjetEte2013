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
	    private boolean relaunch;
	    
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
        this.relaunch=false;
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
		        this.relaunch=false;
				}
		
		protected List<EvaluatedCandidate<T>> nextEvolutionStep(List<EvaluatedCandidate<T>> evaluatedPopulation,
                int eliteCount,
                Random rng)
         {
			//I save the previous generation to keep a record and use it in the Evlautor to find the sequence trhat have already been calculated
			List<Sequence> maListe=new ArrayList<Sequence>(evaluatedPopulation.size());
			/*
			 * boolean to know if praat have been relaunch. It avoid to look for a sound that dont exist anymore.
			 * If it is a nullPointeur, the values will be recalculated in the evaluator
			 */
			if(this.relaunch){
				maListe=null;
			}else{
				for(int i=0;i<evaluatedPopulation.size();i++){
					maListe.add((Sequence)evaluatedPopulation.get(i).getCandidate());//an evaluated candidate
				}
			}
	        this.myGa.setPreviouGeneration(maListe);
	        
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
	        
	        return evaluatePopulation(population);
			
         }

		public boolean isRelaunch() {
			return relaunch;
		}

		public void setRelaunch(boolean relaunch) {
			this.relaunch = relaunch;
		}

}
