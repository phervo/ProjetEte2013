package geneticAlogrithm;

public class Sequence {
	/* General classe containing the float array we will use to store the values of the different parameters
	 * 
	 * */
	
	private int length;
	private double[] values;
	
	public Sequence(int length) {
		super();
		this.length = length;
		this.values = new double[length];
		for(int i =0;i<length;i++){
			this.values[i]=0;
		}
	}
	
	public Sequence(int length,double[] values) {
		super();
		this.length = length;
		if(values.length==this.length){
			this.values = values;
		}else{
			System.out.println("attention le talbeau que vous passez n'est pas de la bonne taille");
			System.out.println("il ne doit pas exeder "+this.length+"cases");
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double[] getValues() {
		return values;
	}
	
	public double getValuesAt(int i) {
		return values[i];
	}

	public void setValues(int indice,double value) {
		this.values[indice]=value;
	}
	
	public void displaySeq(){
		for(int i=0;i<length;i++){
			System.out.println(this.values[i]);
		}
	}
	
	public String getValuesInString(){
		StringBuffer st=new StringBuffer("");
		for(int i=0;i<length;i++){
			st.append(this.getValuesAt(i)+" ");
		}
		return st.toString();
	}
}
