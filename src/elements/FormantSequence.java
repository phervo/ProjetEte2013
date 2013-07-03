package elements;

import java.util.ArrayList;
import java.util.List;

/** <p>Class which define the structure of the sequence that will be analyze by the genetic algorithm<br/>
 * 	The class is designed in order that once the number of formant is fixed, you cannot change it.
 *  It is in order to avoid problems during the run of the GA</p>
 * 
 * @see FormantSequence
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class FormantSequence {
	
	/**
	 * The name of the sound>
	 * 
	 * @see FormantSequence#getSoundName()
	 * 
	 */
	private String soundName;
	
	/**
	 * The number of formants in the sequence. Once it is fixed you cannot change it. It is use in setFormantAt()
	 * 
	 * @see FormantSequence#getNbFormant()
	 * @see FormantSequence#setFormantAt(int i,Formant element)
	 */
	private int nbFormant;
	
	/**
	 * <p>The list of all the formants used in the object FormantSequence. Is size is nbFormant.<br/>
	 * You can change a formant after the creation but you cannot add new formants.
	 * 
	 * @see FormantSequence#getList()
	 * @see FormantSequence#getFormantAt(int i)
	 * @see FormantSequence#setFormantAt(int i,Formant element)
	 */
	private List<Formant> list;
	
	/**
	* Default Constructor. It create a Sequence with the number of formants specified in parameters and with each attribute at 0.0
	*
	* @param nbFormant
	* 	the number of formants
	* @since 0.1
	*
	*/
	public FormantSequence(int nbFormant) {
		//no test neither exception here cause its me who defined the constructor.
		super();
		this.soundName="default empty formantSequence";
		this.nbFormant = nbFormant;
		this.list= new ArrayList<>();
		for(int i=0;i<nbFormant;i++){
			list.add(new Formant());
		}
	}
	
	/**
	* Constructor with given parameters.
	* 
	*
	* @param soundName
	* 	the name of the sound
	* @param nbFormant
	* 	the number of formants
	* @param list
	* 	the list of Formants
	*
	* @since 0.1
	*
	*/
	public FormantSequence(String soundName, int nbFormant,ArrayList<Formant> list) {
		super();
		if(nbFormant==0){
			System.out.println("nbFormant might be != from 0");
		}else if(list.size()==nbFormant){
			this.soundName = soundName;
			this.nbFormant = nbFormant;
			this.list=list;
		}else{
			System.out.println("wrong number of formants in list. There are "+list.size()+" where "+nbFormant+" needed");
			//dois generer une exception ici
		}
	}
	
	/**
	* Constructor which create the formant of the sound given in parameter.
	* If the sound doesn't exist, return a sequenceFormant with 0.0 attributes
	*
	* @param soundName
	* 	the name of the sound. Work for "i"
	*
	* @since 0.1
	*
	*/
	public FormantSequence(String soundName) { //a modifier +exception
		super();
		//this.nbFormant = 3;
		this.nbFormant = 2;
		Formant formant1;
		Formant formant2;
		//Formant formant3;
		this.list= new ArrayList<>();
		if(soundName.compareTo("i")==0){
			formant1 = new Formant(280.0, 28.0, 0.0);
			formant2 = new Formant(2250.0, 450.0, 0.0);
			//formant3 = new Formant(2890.0, 867.0, 0.0);
			list.add(formant1);
			list.add(formant2);
			//list.add(formant3);
		}else{
			//rajouter d'autres par la suite
			formant1 = new Formant(0.0,0.0, 0.0);
			formant2 = new Formant(0.0, 0.0, 0.0);
			//formant3 = new Formant(0.0, 0.0, 0.0);
			list.add(formant1);
			list.add(formant2);
			//list.add(formant3);
		}
	}

	/**
	* Return the value of the SoundName.
	* 
	* @return SoundName value of the instance
	*
	* @since 0.1
	*
	*/
	public String getSoundName() {
		return soundName;
	}

	/**
	* Return the value of the NbFormant attribute.
	* 
	* @return NbFormant value of the instance
	*
	* @since 0.1
	*
	*/
	public int getNbFormant() {
		return nbFormant;
	}
	
	/**
	* Return the Formant at the specified index.
	* 
	* @param index
	* 	The index of the list where the formant is
	* 
	* @return NbFormant value of the instance
	*
	* @since 0.1
	*
	*/
	public Formant getFormantAt(int index) {// gerer le debordement
		return list.get(index);
	}

	public void setFormantAt(int i,Formant element) { //idem
		this.list.set(i, element);
	}

	/**
	* Display the values of all the attributes and of the formants on screen
	*
	*
	* @since 0.1
	*
	*/
	public void displayFormantSequence(){
		System.out.println("Sound: "+this.soundName);
		System.out.println("compared with "+this.getNbFormant()+"formants");
		for(int i=0;i<this.getNbFormant();i++){
			System.out.println("Formant"+i+": "+this.getFormantAt(i).toString());
		}
	}
	
}
