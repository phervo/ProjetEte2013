package geneticAlogrithm;

import messages.MessageToPraat;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

import praatGestion.OrderToPraat;
import elements.Sequence;

/** <p>Overwrite of the EvolutionObserver<Sequence>. I need to use it with a ga in parameter to change the value of the attribute 
 * finalSequence and store the best candidate at each generation. So i overwrite it</p>
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */

public class MySequenceEvolutionObserver implements EvolutionObserver<Sequence>{
	/**
	 * the genetic algorithm where we want to store the value. This function is suppose to be used in a Ga, so it is suppose to be "this"
	 * during the call.
	 */
	private GeneticAlgorithmCall myGa;
	
	/**
	 * Constructor to create the object specifying the GA
	 * 
	 *@param  myGa
	 *	the genetic algorithm where we want to store the value. This function is suppose to be used in a Ga, so it is suppose to be "this"
	 * during the call.
	 */
	public MySequenceEvolutionObserver(GeneticAlgorithmCall myGa) {
		super();
		this.myGa = myGa;
	}

	/**
	 * Overwrited method from the interface EvolutionObserver<Sequence>.
	 * It display the number of the current generation, the best candidat of this generation and to store it the field 
	 * finalSequence of myGa.
	 * 
	 * @since 0.1
	 * 
	 * */
	@Override
	public void populationUpdate(PopulationData<? extends Sequence> data) {
		// TODO Auto-generated method stub
		System.out.printf("Generation %d: %s\n",
                data.getGenerationNumber(),
                data.getBestCandidate().getValuesInString());
		this.myGa.setSequence(data.getBestCandidate());
		/*IMPORTANT : praat got a internal memory and can afford only a certain number of object (2000 in my case)
		 * When reached, praat stop working (error message)
		 * So some time we need to remove those objects to avoid a calculation crash
		 * We cant use the praat's remove function cause it only remove the objects from the list, the only way is to close and relaunch praat
		 * Thats why i didi it here at the generation eval point. I do the operation all the 1000 objects to be sure to be large*/
		if(data.getGenerationNumber()!=0 && data.getGenerationNumber()%1 == 0.0){
			OrderToPraat.reLaunchPraat();
		}
	}

}
