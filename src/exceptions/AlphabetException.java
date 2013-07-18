package exceptions;

import messages.MessageFromPraat;

/** <p>Class which is designed to prevent a user to use an Alphabet Object in the wrong way.<br/>
 *  This class indicate the error in a message via the display method.</p>
 * 
 * @see MessageFromPraat
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class AlphabetException extends Exception {
	/**
	 * The value you try to insert as the minborn in the constructor
	 * 
	 */
	private double minborn;
	/**
	 * The value you try to insert as the maxborn in the constructor
	 * 
	 */
	private double maxborn;
	
	/**
	* Constructor for incorrect interval use .
	*
	* 
	* @param minborn
	* 	The value you try to insert as the minborn in the constructor
	* 
	* @param maxborn
	*  	The value you try to insert as the maxborn in the constructor
	* @since 0.1
	*
	*/
	public AlphabetException(double minborn, double maxborn) {
		super();
		this.minborn = minborn;
		this.maxborn = maxborn;
	}
	
	/**
	 * display the type of the error and explain how to change it.
	 * 
	 */
	public void display(){
		if(minborn==maxborn){
			System.out.println("you have enter the same value twice : "+this.minborn+" you should enter two different values");
		}else if (minborn>maxborn){
			System.out.println("The value you enter for minborn is higher than the value for maxborn, change the order of your parameters");
		}
	}
}
