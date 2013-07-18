package elements;

import java.util.ArrayList;
import java.util.List;

import exceptions.FormantNumberexception;

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
	 * @see FormantSequence#getFormantAt(int i)
	 * @see FormantSequence#setFormantAt(int i,Formant element)
	 */
	private List<Formant> list;
	
	/**
	* Constructor without parameters. It create a Sequence with the number of formants specified in parameters and with each attribute at 0.0
	*
	* @param nbFormant
	* 	the number of formants
	* 
	* @throws FormantNumberexception If you use the wrong parameters.
	* 
	* @since 0.1
	*
	*/
	public FormantSequence(int nbFormant) throws FormantNumberexception {
		if(nbFormant>0){
			this.soundName="default empty formantSequence";
			this.nbFormant = nbFormant;
			this.list= new ArrayList<>();
			for(int i=0;i<nbFormant;i++){
				list.add(new Formant());
			}
		}else{
			throw new FormantNumberexception(nbFormant,nbFormant,nbFormant); //only the 1st parameter matter,just don't put 0
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
	* @throws FormantNumberexception If you use the wrong parameters.
	*
	* @since 0.1
	*
	*/
	public FormantSequence(String soundName, int nbFormant,ArrayList<Formant> list) throws FormantNumberexception{
		if(list.size()==nbFormant && nbFormant!=0){
			this.soundName = soundName;
			this.nbFormant = nbFormant;
			this.list=list;
		}else{
			throw new FormantNumberexception(nbFormant,0,list.size());
		}
	}
	
	/**
	* Constructor which create the formant of the sound given in parameter.
	* If the sound doesn't exist, return a sequenceFormant with 0.0 attributes.
	* If you put an empty String, it wont use it and call the FormantSequence "default empty formantSequence"
	* @param soundName
	* 	the name of the sound. Work for "i", "a" and "e"
	*
	* @since 0.1
	*
	*/
	public FormantSequence(String soundName) {
		//bandwith = 0.1*value of frequency for F1 et 0.2 for F2 
		if(soundName.compareTo("")==0){//if empty
			this.soundName = "default empty formantSequence"; // we put a default name, wont raise a exception for that
		}else{
			this.soundName = soundName;
		}
		this.nbFormant = 2;
		this.list= new ArrayList<>();
		if(soundName.compareTo("i")==0){
			list.add(new Formant(280.0, 28.0, 0.0));
			list.add(new Formant(2250.0, 450.0, 0.0));
		}else if(soundName.compareTo("a")==0){
			list.add(new Formant(710.0,71,0));
			list.add(new Formant(1100.0,220,0));
		}else if(soundName.compareTo("e")==0){
			list.add(new Formant(405.0,40.5,0));
			list.add(new Formant(2080.0,416.0,0));
		}else{
			list.add(new Formant());
			list.add(new Formant());
		}
	}

	/**
	* Return the value of the SoundName attribute.
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
	* @throws FormantNumberexception If trying to get a elem after nbFormant.
	*
	* @since 0.1
	*
	*/
	public Formant getFormantAt(int index) throws FormantNumberexception{
		if(index<this.list.size()){
			return list.get(index);
		}else{
			throw new FormantNumberexception(this.getNbFormant(),index,this.list.size());
		}
	}

	/**
	* Set the value of the formant at index.
	* 
	* @param index 
	* 	The index of the list you want to modify
	* 
	* @param element
	* 	the formant you want to add
	* 
	* @throws FormantNumberexception If trying to insert a elem after nbFormant.
	*
	* @since 0.1
	*
	*/
	public void setFormantAt(int index,Formant element) throws FormantNumberexception {
		if(index<this.list.size()){
			this.list.set(index, element);
		}else{
			throw new FormantNumberexception(this.getNbFormant(),index,this.list.size());
		}
	}

	/**
	* Display the values of all the attributes and of the formants of the instance on screen
	*
	*
	* @since 0.1
	*
	*/
	public void displayFormantSequence(){
		System.out.println("Sound: "+this.getSoundName());
		System.out.println("compared with "+this.getNbFormant()+"formants");
		for(int i=0;i<this.getNbFormant();i++){
			try {
				System.out.println("Formant"+i+": "+this.getFormantAt(i).toString());
			} catch (FormantNumberexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
