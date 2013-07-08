package elements;

public class OtherAlphabet {
	private double [] values;
	private int length;
	
	public OtherAlphabet(){
		this.length=11;
		this.values =  new double[this.length];
		this.values[0]=0.0;
		this.values[1]=0.1;
		this.values[2]=0.2;
		this.values[3]=0.3;
		this.values[4]=0.4;
		this.values[5]=0.5;
		this.values[6]=0.6;
		this.values[7]=0.7;
		this.values[8]=0.8;
		this.values[9]=0.9;
		this.values[10]=1.0;
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
