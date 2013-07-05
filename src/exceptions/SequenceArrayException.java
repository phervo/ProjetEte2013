package exceptions;

import elements.FormantSequence;
import elements.Sequence;

/** <p>Class which is designed to prevent a user to try to overwrite the definition of the Sequence</p>
 * 
 * @see Sequence
 * @see FormantSequence 
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class SequenceArrayException extends Exception{
	/**
	 * The lenght of the instance's sequence
	 * 
	 */
	private int length;
	
	/**
	 * The index of the sequence you try to get or set when you raise the exception
	 * 
	 */
	private int index;
	/**
	 * The length of the array you try to pass in parameter to the sequence when you raise the exception
	 * 
	 */
	private int ArrayLenght;
	
	/**
	* Constructor for incorrect number of array length Indicate where the problem is located
	*
	* @param index
	* 	the index you try to access
	* 
	* @param arrayLenght
	* 	the length of the array
	* 
	* @since 0.1
	*
	*/
	public SequenceArrayException(int length,int index,int arrayLenght){
		super();
		this.length=length;
		this.index=index;
		this.ArrayLenght=arrayLenght;
	}
	
	public void display(){
		if(length<=0){
			System.out.println("length might be > 0 ");
		}else if (ArrayLenght==0){
			System.out.println("you are trying to give an empty list to the function, the list must contain the same number of Formant as you specified in the field nbFormant, in this case "+length+" Formants");
		}else if (index>(ArrayLenght-1)){ // list in java start at 0
			System.out.println("you try to access to the "+index+" item whereas "+(ArrayLenght-1)+"items are declared");
		}else if(length!=ArrayLenght){
			System.out.println("the value you put for length and the length of the list in argument doesnt correspond, verifie the size of your list");
		}
	}
}
