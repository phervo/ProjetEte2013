package elements;

import exceptions.FormantNumberexception;
import exceptions.SequenceArrayException;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.SequenceCrossOver;
import geneticAlogrithm.SequenceEvaluator;
import geneticAlogrithm.SequenceFactory;
import geneticAlogrithm.SequenceMutation;

/** <p>Class that define the structure of the basic element use in the run of the Ga.<br/>
 *  We call it Sequence in reference at the DNA sequence that is the general evoluting structure in a GA.<br/>
 *  This structure is used in all the classes related to the GeneticAlgorithmCall class (linked in the see also part).<br/>
 *  You aren't suppose to instantiate one yourself, the GA will do it for you with the SequenceFactory, <br/>
 *  This class only define the structure and the operations on it </p>
 *  We design this class in order that only the values can be change during the run, the structure itself and the size of the attributes is fixed while instantiated<br/>
 * 
 * @see GeneticAlgorithmCall
 * @see SequenceMutation
 * @see SequenceCrossOver
 * @see SequenceFactory
 * @see SequenceEvaluator
 *
 *
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class Sequence {
	
	/**
	 * The length of the array
	 * 
	 * @see Sequence#getLength()
	 * 
	 */
	private int length;
	
	/**
	 * The array containing the values. It size is defined in length
	 * 
	 * @see Sequence#getValuesAt(int i)
	 * @see Sequence#setValues(int indice,double value)
	 * 
	 */
	private double[] values; //primitif type so not a list to avoid the casts
	
	/**
	* Constructor without parameters. Create a array of 0 of the length put in param. 
	* 
	*
	* @param length
	* 	the length of the sequence
	 * @throws SequenceArrayException if you give an incorrect length (<=0)
	*
	* @since 0.1
	*
	*/
	public Sequence(int length) throws SequenceArrayException {
		if(length>0){
			this.length = length;
			this.values = new double[length];
			for(int i =0;i<length;i++){
				this.values[i]=0;
			}
		}else{
			throw new SequenceArrayException(length);
		}
	}
	
	/**
	* Default Constructor Create a array of 0 of the length put in param. 
	* 
	*
	* @param length
	* 	the length of the sequence
	* 
	* @param values
	* 	the array of double that constitute the sequence
	* 
	* @throws SequenceArrayException If the array in param isn't correct.
	*
	* @since 0.1
	*
	*/
	public Sequence(int length,double[] values) throws SequenceArrayException{
		
		if(values.length==length){
			this.length = length;
			this.values = values;
		}else{
			throw new SequenceArrayException(length);
		}
	}

	/**
	* Return the value of the length attribute.
	* 
	* @return length value of the instance
	*
	* @since 0.1
	*
	*/
	public int getLength() {
		return length;
	}
	
	/**
	* Return the value of the array at the specified index.
	* 
	* @param index
	*	 the index of the value to get
	* 
	* @return value of the array at the index
	 * @throws SequenceArrayException 
	*
	* @since 0.1
	*
	*/
	public double getValuesAt(int index) throws SequenceArrayException {
		if(index<this.values.length){
			return values[index];
		}else{
			throw new SequenceArrayException(index,this.values.length);
		}
	}

	/**
	* Set the value of the array at index.
	* 
	* @param index 
	* 	the index of the array where to put the value
	* 
	* @param value
	* 	the value to insert
	* 
	* * @throws FormantNumberexception If trying to insert a elem out of the array.
	 * @throws SequenceArrayException 
	*
	* @since 0.1
	*
	*/
	public void setValues(int index,double value) throws SequenceArrayException {
		if(index<this.values.length){
			this.values[index]=value;
		}else{
			throw new SequenceArrayException(index,this.values.length);
		}
	}
	
	/**
	* Display the values of the array on the screen
	*
	*
	* @since 0.1
	*
	*/
	public void displaySeq(){
		for(int i=0;i<length;i++){
			System.out.println(this.values[i]);
		}
	}
	
	/**
	* Return all the values of the array concatenate in a String
	*
	* @return A string containing all the values of the array
	*
	* @since 0.1
	*
	*/
	public String getValuesInString(){
		StringBuffer st=new StringBuffer("");
		for(int i=0;i<length;i++){
			try {
				st.append(this.getValuesAt(i)+" ");
			} catch (SequenceArrayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return st.toString();
	}
}
