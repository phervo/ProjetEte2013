package exceptions;

import messages.MessageFromPraat;

/** <p>Class which is designed to prevent a user to try to use the MessageFromPraatScripts in a wrong way (see details below)</p>
 * 
 * @see MessageFromPraat
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class PraatScriptException extends Exception{

	/**
	* Constructor Indicate where the problem is located
	*
	* @param nbVar
	* 	the index you try to access
	* 
	* @param sequenceLength
	* 	the length of the sequence in parameters
	* @since 0.1
	*
	*/
	public PraatScriptException(int nbVar,int sequenceLength){
		super();
		if(nbVar==0){
			System.out.println("length of the sequence must be >0");
		}else if(sequenceLength==0){
			System.out.println("you try to send a empty sequence in parameter");
		}else{
			System.out.println("error, you try to use the "+nbVar+"box  where "+sequenceLength+" are declared");
		}
	}
	

}
