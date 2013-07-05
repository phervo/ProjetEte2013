package exceptions;

import elements.FormantSequence;

/** <p>Class which is designed to prevent a user to try to overwrite the definition of the SequenceFormantList</>
 * </p>
 * 
 * @see FormantSequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class FormantNumberexception extends Exception{
	/**
	 * The number Of formant from the sequence that raise the exception
	 * 
	 */
	private int nbFormants;
	
	/**
	 * The index of the sequence you try to get or set when you raise the exception
	 * 
	 */
	private int index;
	/**
	 * The length of the array you try to pass in parameter to the sequence when you raise the exception
	 * 
	 */
	private int listLenght;
	
	/**
	*  Constructor for incorrect number of formant Indicate where the problem is located
	*
	* @param nbFormants
	* 	the number of formants of the FormantSequence
	* 
	* @since 0.1
	*
	*/
	public FormantNumberexception(int nbFormants){ // a revoir
		super();
		/*this.nbFormants=nbFormants;
		this.index = 0;
		this.listLenght = 0;*/
		
		if(nbFormants==0){
			System.out.println("nbFormant might be > 0 ");
		}else{
			System.out.println("you are trying to set a list that contain either more or less box than declared in this instance");
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
