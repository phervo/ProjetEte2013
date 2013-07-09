package elements;

import messages.MessageFromPraat;

public class LungsAlphabet {
	/*idee second const + erreures, pas forcement excep*/
	private double [] values;
	private int length;
	
	public LungsAlphabet(double minBorn,double maxborn,int nbDigits){ //erreures a gerer plus tard exceptions
		this.length = (int) MessageFromPraat.arrondir((((maxborn-minBorn)/0.1)+1),0);//case 0
		this.values =  new double[this.length];
		
		for(int i=0;i<this.length;i++){
			this.values[i]=minBorn;
			minBorn=MessageFromPraat.arrondir(minBorn+0.1,nbDigits);
		}
	}
	public double getValueAt(int i){
		return values[i];
	}
	
	public int getLength(){
		return this.length;
	}
	/**
	 * Display the alphabet on the screen. Use to checkout.
	 */
	public void displayAlphabet(){
		for(int i=0;i<length;i++){
			System.out.println(this.values[i]);
		}
	}
	
}
