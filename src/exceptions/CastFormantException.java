package exceptions;

import elements.FormantSequence;
import messages.MessageFromPraat;

/** <p>Class which is designed to prevent a user to try to use the splitChaineToFormantSequence function with wrong parameters<br/>
 * the param are initialized in the constructor and the error message is diplay by the "display() function"</p>
 * 
 * @see MessageFromPraat
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class CastFormantException extends Exception {
	
	/**
	 * The string you try to cast
	 * 
	 */
	private String string;
	
	/**
	* Constructor for incorrect string input .
	*
	* 
	* @param string
	* 	The string you try to cast
	* 
	* @since 0.1
	*
	*/
	public CastFormantException(String string) {
		super();
		this.string = string;
	}

	/**
	 * display the type of the error and explain how to change it.
	 * 
	 */
	public void display(){
		if(MessageFromPraat.isDouble(string)){
			System.out.println("you are trying to cast a String with "+Double.parseDouble(string)+"args where 4 args are expected");
		}else{
			System.out.println("you must pass an string with a double inside in parameter, here is what we get : "+string);
		}
	}
}
