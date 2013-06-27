package geneticAlogrithm;

public class Formant {
	/*Class with define a formant and its attributes
	 * It is the inner structure, you can add attributes but nothing else
	 * If you want to add more formant, modify in FormantSequence*/
	private double frequency;
	private double bandwith;
	private double amplitude;
	
	public Formant() {
		super();
		this.frequency = 0.0;
		this.bandwith = 0.0;
		this.amplitude = 0.0;
	}
	
	public Formant(double frequency, double bandwith, double amplitude) {
		super();
		this.frequency = frequency;
		this.bandwith = bandwith;
		this.amplitude = amplitude;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getBandwith() {
		return bandwith;
	}

	public void setBandwith(double bandwith) {
		this.bandwith = bandwith;
	}

	public double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}
	
	public String toString(){
		return ("frequency: "+this.getFrequency()+" bandWith:"+this.getBandwith()+" amplitude: "+this.getAmplitude());
	}
}
