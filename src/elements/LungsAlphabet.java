package elements;

public class LungsAlphabet {
	/*idee second const + erreures, pas forcement excep*/
	private double [] values;
	private int length;
	
	public LungsAlphabet(){
		this.length=21;
		this.values =  new double[this.length];
		this.values[0]=-0.5;
		this.values[1]=-0.4;
		this.values[2]=-0.3;
		this.values[3]=-0.2;
		this.values[4]=-0.1;
		this.values[5]=0.0;
		this.values[6]=0.1;
		this.values[7]=0.2;
		this.values[8]=0.3;
		this.values[9]=0.4;
		this.values[10]=0.5;
		this.values[11]=0.6;
		this.values[12]=0.7;
		this.values[13]=0.8;
		this.values[14]=0.9;
		this.values[15]=1.0;
		this.values[16]=1.1;
		this.values[17]=1.2;
		this.values[18]=1.3;
		this.values[19]=1.4;
		this.values[20]=1.5;
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
