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
	 * the first formant. We just initialise the value here. we will put the good value in the SequenceEvaluator.
	 */
	private Formant f1;
	
	/**
	 * the second formant. We just initialise the value here. we will put the good value in the SequenceEvaluator.
	 */
	private Formant f2;
	
	/**
	 * field which indicate which formant the algo has found
	 * 4 possible values "none","F1","F2","both".
	 * here is just the init, the value will be set during the evaluation
	 */
	private String formantFound;
	
	/**
	 * field wich indicqte the fitness score found when compqring the formqnt of the sequence to the tqrget formants.
	 * We init it to 0 here, the final score will be set in the fitness function in fitnessEvqluator
	 */
	private double fitnessScore;
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
			f1=new Formant();
			f2=new Formant();
			formantFound="none";
			fitnessScore=0;
		}else{
			throw new SequenceArrayException(length,length,length);
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
			f1=new Formant();
			f2=new Formant();
			formantFound="none";
			fitnessScore=0;
		}else{
			throw new SequenceArrayException(length,0,values.length);
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
			throw new SequenceArrayException(this.getLength(),index,this.values.length);
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
			throw new SequenceArrayException(this.getLength(),index,this.values.length);
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

	public Formant getF1() {
		return f1;
	}

	public void setF1(Formant f1) {
		this.f1 = f1;
	}

	public Formant getF2() {
		return f2;
	}

	public void setF2(Formant f2) {
		this.f2 = f2;
	}

	public String getFormantFound() {
		return formantFound;
	}

	public void setFormantFound(String formantFound) {
		this.formantFound = formantFound;
	}

	public double getFitnessScore() {
		return fitnessScore;
	}

	public void setFitnessScore(double fitnessScore) {
		this.fitnessScore = fitnessScore;
	}
	
	
	
}
