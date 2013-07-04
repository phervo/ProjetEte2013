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

	public PraatScriptException(int sequenceLength,int nbVar){
		super();
		if(sequenceLength==0){
			System.out.println("you are trying to send a empty sequence");
		}else{
			System.out.println("error, you try to use "+sequenceLength+" vars where "+nbVar+" are needed");
		}
	}
	

}
