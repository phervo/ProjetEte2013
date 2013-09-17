package geneticAlogrithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import elements.Sequence;
import exceptions.SequenceArrayException;

/** <p>Class which define the CrossOver operation for an object of type Sequence<br/>
 * you had to implement it to use the watchmer's GA algorithm.<br/>
 * For more details, look at the watchmaker API</p>
 * 
 * @see Sequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class SequenceCrossOver extends MyAbstractCrossover<Sequence>{
	/**
	* Constructor without parameters. Define a 1 point crossOver 
	*
	* @see AbstractCrossover
	* 
	* @since 0.1
	*
	*/
	public SequenceCrossOver(){ // 1 point cross over
		this(1);
	}
	
	/**
	* Constructor which allow to define the number of crossOverPoints 
	*
	*
	* @param crossoverPoints
	* 	the number of Cross Over points
	* @see AbstractCrossover
	* 
	* @since 0.1
	*
	*/
	protected SequenceCrossOver(int crossoverPoints) {
		super(crossoverPoints);
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Constructor which allow to define the number of crossOverPoints and a probability
	*
	*
	* @param crossoverPoints
	* 	the number of Cross Over points
	* @param crossoverProbability
	* 	the probability of the Cross Over event
	* 
	* @see AbstractCrossover
	* 
	* @since 0.1
	*
	*/
	public SequenceCrossOver(int crossoverPoints, Probability crossoverProbability)
    {
        super(crossoverPoints, crossoverProbability);
    }

	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It define the operation of CrossOver for a Sequence.
	* It produce a new Sequence by using the Cross over operation on  two parents.
	* It is used in the GACall.
	* 
	* 
	* @param parent1
	*	 the first parent
	* 
	* @param parent2
	* 	the second parent
	* @param numberOfCrossoverPoints
	* 	the number Of Crossover Points
	* @param rng
	* 	a random generator
	*
	* @return A list with two cross overed Sequence
	*
	* @since 0.1
	*
	*/
	@Override
	public List<Sequence> mate(Sequence parent1, Sequence parent2, int numberOfCrossoverPoints,
			Random rng) {
		
		System.out.println("parent1"+parent1.getValuesInString());
		System.out.println("parent2"+parent2.getValuesInString());
		
		// TODO Auto-generated method stub
		if (parent1.getLength() != parent2.getLength())
        {
            throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
        }
		Sequence sq1=null;
		Sequence sq2=null;
		try {
			//1 : clone the object, important for the elitism
			sq1 = new Sequence(parent1.getLength());
			sq2 = new Sequence(parent2.getLength());
			for(int i=0;i<parent1.getLength();i++){
				sq1.setValues(i, parent1.getValuesAt(i));
				sq2.setValues(i, parent2.getValuesAt(i));
			}
		} catch (SequenceArrayException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i = 0; i < numberOfCrossoverPoints; i++)
        {
			int crossoverIndex = (1 + rng.nextInt(parent1.getLength()-1)); //cf doc watchmaker
            for (int j = 0; j < crossoverIndex; j++)
            {
                double temp;
				try {
					temp = sq1.getValuesAt(j);
					sq1.setValues(j, sq2.getValuesAt(j));
		            sq2.setValues(j, temp);
				} catch (SequenceArrayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            }
        }
		List<Sequence> result = new ArrayList<Sequence>(2);
        result.add(sq1);
        result.add(sq2);
        
        return result;
	}
}
