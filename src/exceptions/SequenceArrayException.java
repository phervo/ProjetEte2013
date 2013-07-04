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
	* Constructor Indicate where the problem is located
	*
	* @param length
	* 	the length of the Sequence
	* 
	* @param arrayLenght
	* 	the length of the 
	* 
	* @since 0.1
	*
	*/
	public SequenceArrayException(int length,int arrayLenght){
		super();
		if(length==0){
			System.out.println("length param might be != from 0");
		}else{
			System.out.println("wrong number of formants in list. You try either to insert or to get a elem at index "+arrayLenght+"  where the max index is "+(arrayLenght-1));// list start at 0 in java
		}
	}
}
