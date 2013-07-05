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
	*  Constructor for incorrect number of formant Indicate where the problem is located
	*
	* @param nbFormants
	* 	the number of formants of the FormantSequence
	* 
	* @since 0.1
	*
	*/
	public FormantNumberexception(int nbFormants){
		super();
		if(nbFormants==0){
			System.out.println("nbFormant might be > 0 ");
		}else{
			System.out.println("you are trying to set a list that contain either more or less formants than declared in this instance");
		}
	}
	
	/**
	* Constructor for incorrect setting or getting Indicate where the problem is located
	*
	* @param index
	* 	the index you try to access
	* 
	* @param listLenght
	* 	the length of the list of formant in parameters
	* @since 0.1
	*
	*/
	public FormantNumberexception(int index,int listLenght){
		super();
		if(listLenght==0){
			System.out.println("the lenght or the list you give in argument musn't be empty");
		}else{
			System.out.println("wrong number of formants in list. You try either to insert or to get a elem at index "+index+"  where the max index is "+(listLenght-1));// list start at 0 in java
		}
	}

}
