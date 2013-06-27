package geneticAlogrithm;

import java.util.ArrayList;
import java.util.List;

public class FormantSequence {
	/*Class wich define the structure of the sequence to analyse
	 * in our implementation, its 3 formants but you can add other if you want*/
	private String soundName;
	private int nbFormant;
	private List<Formant> list;
	
	public FormantSequence(String soundName, int nbFormant,ArrayList<Formant> list) {
		super();
		this.soundName = soundName;
		this.nbFormant = nbFormant;
		this.list=list;
	}
	
	public FormantSequence() {
		super();
		this.soundName="default empty formant";
		this.nbFormant = 3;
		Formant formant1;
		Formant formant2;
		Formant formant3;
		this.list= new ArrayList<>();
		formant1 = new Formant(0.0,0.0, 0.0);
		formant2 = new Formant(0.0, 0.0, 0.0);
		formant3 = new Formant(0.0, 0.0, 0.0);
		list.add(formant1);
		list.add(formant2);
		list.add(formant3);
	}
	
	public FormantSequence(String soundName) { //constructor wich create the formant given the name
		super();
		this.nbFormant = 3;
		Formant formant1;
		Formant formant2;
		Formant formant3;
		this.list= new ArrayList<>();
		if(soundName.compareTo("i")==0){
			formant1 = new Formant(280.0, 28.0, 0.0);
			formant2 = new Formant(2250.0, 450.0, 0.0);
			formant3 = new Formant(2890.0, 867.0, 0.0);
			list.add(formant1);
			list.add(formant2);
			list.add(formant3);
		}else{
			//rajouter d'autres par la suite
			formant1 = new Formant(0.0,0.0, 0.0);
			formant2 = new Formant(0.0, 0.0, 0.0);
			formant3 = new Formant(0.0, 0.0, 0.0);
			list.add(formant1);
			list.add(formant2);
			list.add(formant3);
		}
	}

	public String getSoundName() {
		return soundName;
	}

	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}

	public int getNbFormant() {
		return nbFormant;
	}

	public void setNbFormant(int nbFormant) {
		this.nbFormant = nbFormant;
	}
	
	public List<Formant> getList() {
		return list;
	}
	
	public Formant getFormantAt(int i) {
		return list.get(i);
	}

	public void setList(int i,Formant element) {
		this.list.set(i, element);
	}

	public void displayFormantSequence(){
		System.out.println("Sound: "+this.soundName);
		System.out.println("compared with "+this.getNbFormant()+"formants");
		for(int i=0;i<this.getNbFormant();i++){
			System.out.println("Formant"+i+": "+this.getFormantAt(i).toString());
		}
	}
	
}
