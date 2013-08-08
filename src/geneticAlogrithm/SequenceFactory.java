package geneticAlogrithm;

import java.util.Random;
import messages.MessageToPraat;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import elements.GlobalAlphabet;
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
	 * The Meta-alphabet of all the possible values that a member of a Sequence could take.
	 * 
	 */
	private GlobalAlphabet globalAlphabet;
	/**
	 * The length of a Sequence. It will be use to create a Sequence of the given size.
	 * 
	 */
	private int length;
	
	/**
	* Constructor with given parameters 
	*
	*
	* @param globalAlphabet
	* 	see description above
	* @param length
	* see description above
	* 
	* @see SequenceFactory#globalAlphabet
	* @see SequenceFactory#length
	* 
	* @since 0.1
	*
	*/
	public SequenceFactory(GlobalAlphabet globalAlphabet,int length){
		this.length=length;
		this.globalAlphabet=globalAlphabet;
	}
	
	/**
	* Method overWrited from watchMaker's API AbstractCrossover class.
	* It allow the GA to generate random candidates.
	* For more information, see watchmaker's API.
	* 
	* This method affect the values of the different alpahbets to the different box of the sequence values.
	* It is necessaary to define it manually because it depend of the lenght of the sequence, he number of alphabets and the 
	* position of each vocal element in the script.
	* 
	* @param rng
	*	 a random generator
	* 
	* @see AbstractCandidateFactory
	* @see MessageToPraat#writePraatScriptWithCandidates(Sequence)
	* 
	* @return A new Sequence
	* 
	* @since 0.1
	*
	*/
	@Override
	public Sequence generateRandomCandidate(Random rng){
		// TODO Auto-generated method stub
		Sequence mySeq=null;
		try {
			mySeq = new Sequence(this.length);
		
			for(int i=0;i<mySeq.getLength();i++){
				//separation selon les valeurs de var
				try {
					//I have deleted the lungAlphabet but for example if we use it, we should have said if position =0 ou 1 get value in the globalAlphabet.getLungsAlphabet()
					if(i==6){ //masseter
						mySeq.setValues(i, globalAlphabet.getMasseterAlphabet().getValueAt(rng.nextInt(globalAlphabet.getMasseterAlphabet().getLength())));
					}else{
						mySeq.setValues(i, globalAlphabet.getOtherAlphabet().getValueAt(rng.nextInt(globalAlphabet.getOtherAlphabet().getLength())));
					}
				
				} catch (SequenceArrayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.display();
				}
			}	
		} catch (SequenceArrayException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return mySeq;
	}
	
}
