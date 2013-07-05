package geneticAlogrithm;

import java.util.Random;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;
import elements.Sequence;
import exceptions.SequenceArrayException;


/** <p>Class which define a Factory for the Sequence Object.<br/>
 * As it is say in the Sequence documentation, you are not suppose to instantiate it yourself, the Ga will do it,using this factory.<br/>
 * you had to implement it to use the watchmer's GA algorithm. The Ga will generate random candidates using this factory<br/>
 * For more details, look at the watchmaker API</p>
 * 
 * @see GeneticAlgorithmCall
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class SequenceFactory extends AbstractCandidateFactory<Sequence>{
	/**
	 * The alphabet of all the possible values that a member of a Sequence could take.
	 * 
	 */
	private double[] alphabet;
	/**
	 * The length of a Sequence. It will be use to create a Sequence of the given size.
	 * 
	 */
	private int length;
	
	/**
	* Constructor with given parameters 
	*
	*
	* @param alphabet
	* 	see description above
	* @param length
	* see description above
	* 
	* @see SequenceFactory#alphabet
	* @see SequenceFactory#length
	* 
	* @since 0.1
	*
	*/
	public SequenceFactory(double[] alphabet,int length){
		this.length=length;
		this.alphabet=alphabet;
	}
	
	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It allow the GA to generate random candidates.
	* For more information, see watchmaker's API.
	* 
	* @param rng
	*	 a random generator
	* 
	* @see AbstractCandidateFactory
	* 
	* @return A new Sequence
	* 
	* @since 0.1
	*
	*/
	@Override
	public Sequence generateRandomCandidate(Random rng) {
		// TODO Auto-generated method stub
		Sequence mySeq = new Sequence(this.length);
		for(int i=0;i<mySeq.getLength();i++){
			try {
				mySeq.setValues(i, alphabet[rng.nextInt(length)]);
			} catch (SequenceArrayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mySeq;
	}
	
	/**
	 * Display the alphabet on the screen. Use to checkout.
	 */
	public void displayAlphabet(){
		for(int i=0;i<length;i++){
			System.out.println(this.alphabet[i]);
		}
	}
}
