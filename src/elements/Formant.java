package elements;

/**<p> Class which define the structure of a formant and its attributes.<br/>
 * Our program deal with sequences of formants, this structures are defined in FormantSequence.<br/>
 * The FormantSequence class use this one. For more information see FormantSequence.</p>
 * 
 * @see FormantSequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 *
 */
public class Formant {
	/**
     * The formant frequency
     * 
     * @see Formant#getFrequency()
     * @see Formant#setFrequency(double frequency)
     */
	private double frequency;
	
	/**
     * The formant bandwith
     * 
     * @see Formant#getBandwith()
     * @see Formant#setBandwith(double bandwith)
     */
	private double bandwith;
	
	/**
     * The formant amplitude
     * 
     * @see Formant#getAmplitude()
     * @see Formant#setAmplitude(double amplitude)
     */
	private double amplitude;
	
	/**
	 * Constructor by default. Set all the values to 0.0
	 *
	 *@since 0.1
	 *
	 */
	public Formant() {
		super();
		this.frequency = 0.0;
		this.bandwith = 0.0;
		this.amplitude = 0.0;
	}
	
	/**
	 * Constructor with given parameters.
	 * 
	 * @param frequency
	 * 	the frequency of the formant
	 * @param bandwith
	 * 	the bandwith of the formant
	 * @param amplitude
	 * 	the amplitude of the formant
	 *
	 *@since 0.1
	 *
	 */
	public Formant(double frequency, double bandwith, double amplitude) {
		super();
		this.frequency = frequency;
		this.bandwith = bandwith;
		this.amplitude = amplitude;
	}

	/**
	 * Return the value of the frequency attribute.
	 * 
	 * @return frequency value of the instance
	 *
	 *@since 0.1
	 *
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * Set the value of the frequency attribute for the instance.
	 * 
	 * @param frequency 
	 * 	frequency you want to set 
	 *
	 *@since 0.1
	 *
	 */
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	/**
	 * Return the value of the bandwith attribute.
	 * 
	 * @return Bandwith value of the instance
	 *
	 *@since 0.1
	 *
	 */
	public double getBandwith() {
		return bandwith;
	}

	/**
	 * Set the value of the Bandwith attribute for the instance.
	 * 
	 * @param bandwith 
	 * 	Bandwith you want to set 
	 *
	 *@since 0.1
	 *
	 */
	public void setBandwith(double bandwith) {
		this.bandwith = bandwith;
	}

	/**
	 * Return the value of the Amplitude attribute.
	 * 
	 * @return Amplitude value of the instance.
	 *
	 *@since 0.1
	 *
	 */
	public double getAmplitude() {
		return amplitude;
	}

	/**
	 * Set the value of the Amplitude attribute for the instance.
	 * 
	 * @param amplitude
	 * 	Amplitude you want to set 
	 *
	 *@since 0.1
	 *
	 */
	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}
	
	
	/**
	 * Return the values of the different attributes concatenated in a String.
	 * 
	 * @return values of all the attributes of the instance.
	 *
	 *@since 0.1
	 *
	 */
	public String toString(){
		return ("frequency: "+this.getFrequency()+" bandWith:"+this.getBandwith()+" amplitude: "+this.getAmplitude());
	}
}
