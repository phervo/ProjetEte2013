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
	 * 
	 */
	private PraatState current;
	
	/**
	 * public constructor
	 * define the state to close by default and set the observers
	 */
	public Praat(){
		current = new Close();
		this.addObserver(new OrderToPraat());
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
	 * it is here we had the setchange et notifyObservers
	 */
	
	/**
	 * launch praat
	 *
	 */
	public void launch(){
		current.launch(this);
		setChanged();
	    notifyObservers();
	}
	
	/**
	 * relaunch praat
	 */
	public void reLaunch(){
		current.reLaunch(this);
		setChanged();
	    notifyObservers();
	}
	
	/**
	 * set the headers
	 */
	public void headerSet(){
		current.headerSet(this);
		setChanged();
	    notifyObservers();
	}
	
	/**
	 * set the headers
	 */
	public void running(){
		current.running(this);
		setChanged();
	    notifyObservers();
	}
	
	/**
	 * close praat 
	 */
	public void close(){
		current.close(this);
		setChanged();
	    notifyObservers();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public PraatState getState(){ 
		return current;
	}
}
