package praatGestion;

import geneticAlogrithm.GeneticAlgorithmCall;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

import messages.MessageFromPraat;
import messages.MessageToPraat;


/** <p>Class which contains the different order you can give to praat<br/>
 *  The ordertoPraat object is created in the Praat object. You are not suppose to instantiate one elsewhere.
 *  This classe define the calls to the praat programm using the runtime environment an the command line.
 *  All those method are private to forbid them beiing use outer the classe.
 *  They are used in the update function to do the proper task (corresponding to the new state) and launch automaticly the next
 *  state. 
 *  
 *  Important note : I could have define the operation directly in the different states but i preferred to deal with all the runtime tacks in a single
 *  class to avoid code repetition. So if you had to change anything in this op, you wont have to change it directly in the states</p>
 * 
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 * 
 */
public class OrderToPraat implements Observer {
	
	/**
	 * The function launch praat doesn't use the waitFor() method of runtime.
	 * If we do it, it will block the execution of the rest of the program (deadlock).
	 * so it is not a thread nor a prioritary process. It means that the rest of the code which use specific process or thread can be execute before.
	 * It is the case of the function sendMessageToPrat(String string) for example.
	 * But this function use sendpraat and as I said, it needs a RUNNING instance of praat.
	 * So we need to use a semaphore to forbid the other functions to launch before this one have finished.
	 * 
	 */
	//private static final Semaphore praatLaunch = new Semaphore(1, true);
	
	/**
	 * A mutex to protect the separate execution of each instruction
	 * 
	 * @see launchPraat()
	 * @see endMessageToPrat(String string)
	 * 
	 */
	private static final Semaphore token = new Semaphore(1, true);
	
	/**
	 * A boleean which indicate if the praat software is launch or not.
	 * Be carefull, it doesnt indicate that we have launch the process to launch praat and that it is maybe running,
	 * it indicate if the software is completely launch and running.
	 */
	public static boolean PraatLaunch=false;
	
	/**
	    * Constructor
	    * just overwrite the default constructor
	    *
	    *
	    * @since 0.1
	    *
	    */
	public OrderToPraat(){
		
	}
	
	/**
	* lanch praat, It is necessary before using any other function dealing with praat cause sendpraat needs a RUNNING version of praat.
	*
	*
	* @since 0.1
	*
	*/
	private static void launchPraat(){
		try {
			System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
			token.acquire();
			System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
			/*surtout pas de waitFor sinon on bloque tout*/
			Runtime run = Runtime.getRuntime();
			String[] sendpraatLaunch ={"praat"};
			try {
				run.exec(sendpraatLaunch);
				//Thread.currentThread().sleep(250); //it is a trick but i havent found a better solution
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
				token.release();
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
				//runProcess.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//note : the semaphore is release at the end of the function, it doesnt wait the end of praat without the waitFor()
			
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
	public static void sendMessageToPrat(String string){
			try {
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
				token.acquire();
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
				String[] sendpraatCom ={"sendpraat", "praat",string};
				Runtime run = Runtime.getRuntime();
				try {
					Process runProcess=run.exec(sendpraatCom);
					runProcess.waitFor(); //le thread qui l'a lance attend la fin de l'execution
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
				token.release();
				System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
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
	private static void closePraat(){
		System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
		try {
			token.acquire();
			System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
			String[] sendpraatCom ={"sendpraat", "praat","Quit"};
			Runtime run = Runtime.getRuntime();
			try {
				Process p=run.exec(sendpraatCom);
				p.waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
			token.release();
			System.out.println("etat du semaphore "+token.toString()+"possibilite prendre un jeton "+token.availablePermits()+" nombre de threads en attente "+token.getQueueLength());
		
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	/**
	* Function use each 50 generations. Each generation have 10 candidates and produce two sounds (the sound and the formant sound).
	* So it make 50*10*2 = 1000 sound objects in praat. As there is a praat sound objects limit (something like 2000), this function will allow to
	* relaunch praat with a clean memory. 
	* 
	* IMPORTANT NOTE : the praat's remove function only remove the object from the list and not from the memory. That why i use this way.
	*
	* @deprecated
	* 	look at the update function instead
	* @since 0.1
	*
	*/
	private static void reLaunchPraat(){
		String[] sendpraatQuit1 ={"sendpraat", "praat","Quit"};
		String[] sendpraatLaunch ={"praat"};
		String[] sendpraatCom ={"sendpraat", "praat",MessageToPraat.writePraatScriptHeader()};
		Runtime run = Runtime.getRuntime();
		try {
			Process runProcess1=run.exec(sendpraatQuit1);
			runProcess1.waitFor();
			
			run.exec(sendpraatLaunch);
			
			 //sem here cause it is called in lanchPraat and it guaranti that praat is relaunch before executing runProcess2
			Process runProcess2=run.exec(sendpraatCom);
			runProcess2.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * principle : automatisation of the state pattern transitions
	 * avoid to duplicate the code
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//System.out.println("action detected");
		Praat p = (Praat)arg0; //cast
		if(p.getState().getClass()==Launch.class){
			launchPraat();
			p.headerSet();
		}else if(p.getState().getClass()==HeaderSet.class){
			while(OrderToPraat.PraatLaunch!=true){
				sendMessageToPrat(MessageToPraat.writePraatLaunchTest());
			}
			sendMessageToPrat(MessageToPraat.writePraatScriptHeader());
			OrderToPraat.PraatLaunch=false;
			p.running();
		}else if(p.getState().getClass()==Running.class){
			//nothing
		}else if(p.getState().getClass()==ReLaunch.class){
			closePraat();
			p.launch();
		}else if(p.getState().getClass()==Close.class){
			closePraat();
		}
	}
	
}
