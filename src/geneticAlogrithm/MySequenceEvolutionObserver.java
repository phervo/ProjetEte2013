package geneticAlogrithm;

import messages.MessageToPraat;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

import communication.OrderToPraat;

import elements.Sequence;

public class MySequenceEvolutionObserver implements EvolutionObserver<Sequence>{
	private GeneticAlgorithmCall myGa;
	
	
	public MySequenceEvolutionObserver(GeneticAlgorithmCall myGa) {
		super();
		this.myGa = myGa;
	}


	@Override
	public void populationUpdate(PopulationData<? extends Sequence> data) {
		// TODO Auto-generated method stub
		System.out.printf("Generation %d: %s\n",
                data.getGenerationNumber(),
                data.getBestCandidate().getValuesInString());
		this.myGa.setSequence(data.getBestCandidate());
		OrderToPraat.sendMessageToPrat(MessageToPraat.clearPraatObjectWindow());
	}

}
