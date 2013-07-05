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
	* Constructor for incorrect number of array length Indicate where the problem is located
	*
	* @param length
	* 	the length of the Sequence
	* 
	* @since 0.1
	*
	*/
	public SequenceArrayException(int length){
		super();
		if(length==0){
			System.out.println("length might be > 0 ");
		}else{
			System.out.println("you are trying to set a array that contain either more or less formants than declared in this instance");
		}
	}
	
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
	public SequenceArrayException(int index,int arrayLenght){
		super();
		if(arrayLenght==0){
			System.out.println("the lenght or the array you give in argument musn't be empty");
		}else{
			System.out.println("wrong number of box in array. You try either to insert or to get a elem at index "+index+"  where the max index is "+(arrayLenght-1));//array start at 0 in java
		}
	}
}
