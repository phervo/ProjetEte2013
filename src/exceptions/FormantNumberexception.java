package exceptions;

import elements.FormantSequence;

/** <p>Class which is designed to prevent a user to try to overwrite the definition of the SequenceFormantList<br/>
 * the param are initialized in the constructor and the error message is diplay by the "display() function"</p>
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
	* Constructor for incorrect setting, getting or init, Indicate where the problem is located
	*
	* @param nbFormants
	*   the number of formant of the instance 
	* 
	* @param index
	* 	the index you try to access
	* 
	* @param listLenght
	* 	the length of the list of formant in parameters
	* 

	* @since 0.1
	*
	*/
	public FormantNumberexception(int nbFormants,int index,int parameterlistLenght){
		super();
		this.nbFormants=nbFormants;
		this.index = index;
		this.listLenght = parameterlistLenght;
	}
	
	public void display(){
		if(nbFormants<=0){
			System.out.println("nbFormant might be > 0 ");
		}else if (listLenght==0){
			System.out.println("you are trying to give an empty list to the function, the list must contain the same number of Formant as you specified in the field nbFormant, in this case "+nbFormants+" Formants");
		}else if (index>(listLenght-1)){ // list in java start at 0
			System.out.println("you try to access to the "+index+" item whereas "+(listLenght-1)+"items are declared");
		}else if(nbFormants!=listLenght){
			System.out.println("the value you put for length and the length of the list in argument doesnt correspond, verifie the size of your list");
		}
	}

}
