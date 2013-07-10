package elements;

import messages.MessageFromPraat;

/** <p>Class which define all the possible values for all the praat parameters that didn't get their own alphabet.<br/>
 * It will be use in the class manipulating sequences to give a value in a specific interval to the others parameter.
 * It wont be use directly, it will be use thought a meta alphabet, a GlobalAlphabet</p>
 * 
 * @see GlobalAlphabet
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class OtherAlphabet {
	private double [] values;
	private int length;
	
	public OtherAlphabet(double minBorn,double maxborn,int nbDigits){
		this.length = (int) MessageFromPraat.arrondir((((maxborn-minBorn)/0.01)+1),0);//case 0
		this.values =  new double[this.length];
		
		for(int i=0;i<this.length;i++){
			this.values[i]=minBorn;
			minBorn=MessageFromPraat.arrondir(minBorn+0.01,nbDigits);
		}
	}
	public double getValueAt(int i){
		return values[i];
	}
	public int getLength(){
		return this.length;
	}
	/**
	 * Display the alphabet on the screen. 
	 * @since 0.1
	 *
	 */
	public void displayAlphabet(){
		for(int i=0;i<length;i++){
			System.out.println(this.values[i]);
		}
	}
}
