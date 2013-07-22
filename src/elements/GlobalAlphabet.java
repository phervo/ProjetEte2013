package elements;

import exceptions.AlphabetException;

/** <p>Class wich define the meta alphabet of the program. It contains one instance of each existing alphabet
 * If you want to add a alphabet to the programm, define a new classe with the alphabet and then add an instance here.<br/>
 * This structure will be use in the class building a sequence for example SequenceFactory,SequenceMutation and in SequenceEvaluator.<br/>
 * It allow to send particular values to some parameters and some others to another.<br/>
 * For example, lungs took values from 0.0 to 0.2 (lungs Alphabet) whereas Genioglossus took is values between 0.0 and 1.0(other Alphabet)</p>
 * 
 * @see Alphabet
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class GlobalAlphabet {
	
	/**
	 * The alphabet for lungs
	 * 
	 * WE DECIDED TO PUT THE VALUES OF THE LUNG FIXED but i left it here in case we use it again someday
	 * 
	 * 
	 * @see GlobalAlphabet#getLungsAlphabet()
	 * 
	 */
	private Alphabet lungsAlphabet;
	
	/**
	 * The alphabet for masseter
	 *  
	 * @see GlobalAlphabet#getMasseterAlphabet()
	 * 
	 */
	private Alphabet masseterAlphabet;
	
	/**
	 * The alphabet for all the other parameters
	 *  
	 * @see GlobalAlphabet#getOtherAlphabet()
	 * 
	 */
	private Alphabet otherAlphabet;
	
	/**
	* Constructor wich create the alphabet with the default intervalls for each alphabet.
	* 
	*
	* @see Alphabet#Alphabet(String, double, double, int)
	*
	* @since 0.1
	*
	*/
	public GlobalAlphabet() {
		super();
		try {
			this.lungsAlphabet = new Alphabet("LungsAlphabet",0.0,0.2,2);
			this.masseterAlphabet = new Alphabet("MasseterAlphabet",-0.5,0.5,2);
			this.otherAlphabet = new Alphabet("otherAlphabet",0.0,1.0,2);
		
		} catch (AlphabetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	* Return the lungsAlphabet
	* 
	* @return the lungsAlphabet
	*
	* @since 0.1
	*
	*/
	public Alphabet getLungsAlphabet() {
		return lungsAlphabet;
	}

	/**
	* Return the masseterAlphabet
	* 
	* @return the masseterAlphabet
	*
	* @since 0.1
	*
	*/
	public Alphabet getMasseterAlphabet() {
		return masseterAlphabet;
	}

	/**
	* Return the OtherAlphabet
	* 
	* @return the OtherAlphabet
	*
	* @since 0.1
	*
	*/
	public Alphabet getOtherAlphabet() {
		return otherAlphabet;
	}
	
	/**
	 * Display all the alphabets on the screen.
	 * 
	 * @see Alphabet#displayAlphabet()
	 * 
	 * @since 0.1
	 *
	 */
	public void displayAllAlphabets(){
		System.out.println("alphabet of lungs");
		for(int i=0;i<lungsAlphabet.getLength();i++){
			System.out.println(this.lungsAlphabet.getValueAt(i));
		}
		System.out.println("alphabet of masseter");
		for(int i=0;i<masseterAlphabet.getLength();i++){
			System.out.println(this.masseterAlphabet.getValueAt(i));
		}
		System.out.println("alphabet of others");
		for(int i=0;i<otherAlphabet.getLength();i++){
			System.out.println(this.otherAlphabet.getValueAt(i));
		}
	}
}
