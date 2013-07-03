package geneticAlogrithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import elements.Sequence;
import exceptions.SequenceArrayException;


public class SequenceCrossOver extends AbstractCrossover<Sequence>{

	public SequenceCrossOver(){ // 1 point cross over
		this(1);
	}
	
	protected SequenceCrossOver(int crossoverPoints) {
		super(crossoverPoints);
		// TODO Auto-generated constructor stub
	}
	
	public SequenceCrossOver(int crossoverPoints, Probability crossoverProbability)
    {
        super(crossoverPoints, crossoverProbability);
    }

	@Override
	public List<Sequence> mate(Sequence parent1, Sequence parent2, int numberOfCrossoverPoints,
			Random rng) {
		// TODO Auto-generated method stub
		if (parent1.getLength() != parent2.getLength())
        {
            throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
        }
		Sequence sq1=parent1;
		Sequence sq2=parent2;
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
