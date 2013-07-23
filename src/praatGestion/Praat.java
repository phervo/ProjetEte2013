package praatGestion;

import java.io.IOException;
import java.util.Observable;

/**
 * <p>Class designed on the design pattern States. It allow to know the state of the praat Object and use the good action in consequence.
 * I also use the design pattern observer to know when i could send a specific message(exemple if praat is launch send the header via sendpraat)</p>
 * @author phervo
 * @version 0.1
 */
public class Praat extends Observable{
	/**
	 * the state of the Praat object, cf design pattern
	 */
	private PraatState current;
	
	/**
	 * public constructor
	 */
	public Praat(){
		current = new Close();
	}
	
	/**
	 * the setState method
	 * 
	 * @param s
	 * 	the state you want to set
	 */
	public void setState(PraatState s){ 
		current = s;
	}
	
	/////////////////////////////////////the functions for the state pattern//////////////////////////
	/*
	 * the same as those from the states without the parameters
	 */
	
	/**
	 * launch praat
	 *
	 */
	public void launch(){
		current.launch(this);
	}
	
	/**
	 * relaunch praat
	 */
	public void reLaunch(){
		current.reLaunch(this);
	}
	
	/**
	 * close praat 
	 */
	public void close(){
		current.close(this);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public void blabla(){
		try {
			Runtime run = Runtime.getRuntime();
			String[] sendpraatLaunch ={"praat"};
			run.exec(sendpraatLaunch);
			setChanged();
	        notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
