package communication;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import messages.MessageFromPraat;
import messages.MessageToPraat;


/** <p>Class which contains the different order you can give to praat<br/>
 *  all the method are static so no need to instantiate an object</p>
 * 
 *  
 * @author Pierre-Yves Hervo
 * @version 0.2
 * 
 */
public class OrderToPraat {
	
	/**
	 * The function launch praat doesnt use the waitFor() method of runtime.
	 * If we do it, it will block the execution of the rest of the program (deadlock).
	 * so it is not a thread nor a prioritary process. It means that the rest of the code wich use specific process or thread can be execute before.
	 * It is the case of the function sendMessageToPrat(String string) for exemple.
	 * But this function use sendpraat and as I said, it needs a RUNNING instance of praat.
	 * So we need to use a semaphore to forbid the other functions to launch before this one have finished.
	 * 
	 */
	private static final Semaphore praatLaunch = new Semaphore(1, true);
	
	/**
	    * Constructor, Private to forbid people to instantiate 
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
		/*surtout pas de waitFor sinon on bloque tout*/
		try {
			praatLaunch.acquire();
			Runtime run = Runtime.getRuntime();
			String[] sendpraatCom ={"praat"};
			try {
				run.exec(sendpraatCom);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			praatLaunch.release();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	* send a script to praat. The script must be in a String.
	* Use the function in the messages package.
	*
	*@param string
	*	A String containing the scipt
	* @see MessageFromPraat
	* @since 0.1
	*
	*/
	public static synchronized void sendMessageToPrat(String string){
		try {
			praatLaunch.acquire();
			String[] sendpraatCom ={"sendpraat", "praat",string};
			Runtime run = Runtime.getRuntime();
			try {
				Process runProcess=run.exec(sendpraatCom);
				runProcess.waitFor(); //le thread qui l'a lance attend la fin de l'execution
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			praatLaunch.release();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	
	/**
	* Function use each 50 generations. Each generation have 10 candidates and produce two sounds (the sound and the formant sound).
	* So it make 50*10*2 = 1000 sound objects in praat. As there is a praat sound objects limit (something like 2000), this function will allow to
	* relaunch praat with a clean memory. 
	* 
	* IMPORTANT NOTE : the praat's remove function only remove the object from the list and not from the memory. That why i use this way.
	*
	*
	* @since 0.1
	*
	*/
	public static void reLaunchPraat(){
		String[] sendpraatQuit1 ={"sendpraat", "praat","Quit"};
		String[] sendpraatLaunch ={"praat"};
		String[] sendpraatCom ={"sendpraat", "praat",MessageToPraat.writePraatScriptHeader()};
		Runtime run = Runtime.getRuntime();
		try {
			Process runProcess1=run.exec(sendpraatQuit1);
			runProcess1.waitFor();
			praatLaunch.acquire();
			run.exec(sendpraatLaunch);
			praatLaunch.release();
			praatLaunch.acquire(); //sem here cause it is called in lanchPraat and it guaranti that praat is relaunch before executing runProcess2
			Process runProcess2=run.exec(sendpraatCom);
			runProcess2.waitFor();
			praatLaunch.release();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
