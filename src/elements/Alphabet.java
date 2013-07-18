package elements;

import exceptions.AlphabetException;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;
import messages.MessageFromPraat;

/** <p>Class which define all the possible values for a Praat parameter or several praat parameter (specify it in the name).<br/>
 * It will be use in the class manipulating sequences to give a value in a specific interval to the parameter.
 * It wont be use directly, it will be use thought a meta alphabet, a GlobalAlphabet</p>
 * 
 * @see GlobalAlphabet
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class Alphabet {
	/**
	 * the name of the alphabet. Use to differentiate the different alphabets
	 */
	private String name;
	/**
	 * the array containing the values of the alphabet
	 */
	private double [] values;
	/**
	 * the length of the alphabet
	 */
	private int length;
	/**
	 *  
	 *  Public constructor wich allow to automaticly generate a double alphabet of length X with X the number of element beetween the minborn and the maxBorn
	 *  You just have to precise the start value, the end value and the number of digits after the comma.
	*
	* @param name
	* 	the name of your alphabet
	* @param minBorn
	* 	the start value
	* @param maxborn
	* 	the end value
	* @param nbDigits
	* 	the number of digit for each number
	* @throws AlphabetException
	* 	if you enter twice the same value or if you put the higher value on the minBorn.
	* @since 0.1
	*/
	public Alphabet(String name,double minBorn,double maxborn,int nbDigits) throws AlphabetException{
		if(maxborn>minBorn){
			this.name=name;
			this.length = (int) MessageFromPraat.arrondir((((maxborn-minBorn)/0.01)+1),0);//case 0
			this.values =  new double[this.length];
			
			for(int i=0;i<this.length;i++){
				this.values[i]=minBorn;
				minBorn=MessageFromPraat.arrondir(minBorn+0.01,nbDigits);
			}
		}else{
			throw new AlphabetException(minBorn,maxborn);
		}
	}
	
	/**
	* Return the value of value of the array at the given index.
	* 
	* @return the value of value of the array at the given index
	*
	* @since 0.1
	*
	*/
	public double getValueAt(int i){
		return values[i];
	}
	
	/**
	* Return the value of the length attribute.
	* 
	* @return length value of the instance
	*
	* @since 0.1
	*
	*/
	public int getLength(){
		return this.length;
	}
	
	/**
	* Return the value of the name attribute.
	* 
	* @return name value of the instance
	*
	* @since 0.1
	*
	*/
	public String getName(){
		return this.name;
	}
	
	/**
	 * Display the alphabet on the screen. 
	 * @since 0.1
	 *
	 */
	public void displayAlphabet(){
		System.out.println("alphabet : "+this.name);
		for(int i=0;i<length;i++){
			System.out.println(this.values[i]);
		}
	}
}
