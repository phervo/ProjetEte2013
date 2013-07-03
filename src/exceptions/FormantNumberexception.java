package exceptions;

import elements.FormantSequence;

/** <p>Class which is designed to prevent a user to try to overwrite the definition of the SequenceFormantList</p>
 * 
 * @see FormantSequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class FormantNumberexception extends Exception{
	/**
	* Constructor Indicate where the problem is located
	*
	* @param nbFormant
	* 	the number of formants of the FormantSequence
	* @since 0.1
	*
	*/
	public FormantNumberexception(int nbFormants,int listLenght){
		if(nbFormants==0){
			System.out.println("nbFormant might be != from 0");
		}else{
			System.out.println("wrong number of formants in list. You try either to insert or to get a elem at index "+listLenght+"  where the max index is "+(nbFormants-1));// list start at 0 in java
		}
	}

}
