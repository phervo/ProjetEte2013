package communication;

import java.io.IOException;

import messages.MessageFromPraat;


/** <p>Class which contains the different order you can give to praat<br/>
 *  all the method are static so no need to instantiate an object</p>
 * 
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class OrderToPraat {
	
	/**
	    * Constructor, Private to forbid people to instanciate 
	    * just overwrite the default constructor
	    *
	    *
	    * @since 0.1
	    *
	    */
	private OrderToPraat(){
		
	}
	
	/**
	* lanch praat, It is necessary before using any other function dealing with praat cause sendpraat needs a RUNNING version of praat.
	*
	*
	* @since 0.1
	*
	*/
	public static void launchPraat(){
		/*launch praat */
		Runtime run = Runtime.getRuntime();
		String[] sendpraatCom ={"praat"};
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* send a script to praat. The script must be in a String.
	* Use the function in the messages package.
	*
	*
	* @see MessageFromPraat
	* @since 0.1
	*
	*/
	public static synchronized void sendMessageToPrat(String fileName){
		String[] sendpraatCom ={"sendpraat", "praat",fileName};
		Runtime run = Runtime.getRuntime();
		try {
			Process runProcess=run.exec(sendpraatCom);
			runProcess.waitFor(); //le thread qui l'a lance attend la fin de l'execution
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* close praat. Use it as the last command or the sendpraat routine will continue to be send but nothing will happen.
	* The Sendpraat command use in the other function need a RUNNING praat.
	*
	*
	* @since 0.1
	*
	*/
	public static void closePraat(){
		String[] sendpraatCom ={"sendpraat", "praat","Quit"};
		Runtime run = Runtime.getRuntime();
		try {
			run.exec(sendpraatCom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
