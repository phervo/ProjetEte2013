package geneticAlogrithm;

import java.util.ArrayList;

import elements.Sequence;

/**
 * this class is a structure that allow me to map the informations I need to select the individual in the population
 * @author phervo
 *
 */

public class ScoreMap {

	private String index;
	private int numberOfElement;
	private ArrayList<Sequence> liste;
	
	public ScoreMap(String index) {
		super();
		this.index = index;
		this.numberOfElement = 0;
		this.liste = new ArrayList<Sequence>();
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public int getNumberOfElement() {
		return numberOfElement;
	}
	public void setNumberOfElement(int numberOfElement) {
		this.numberOfElement = numberOfElement;
	}
	public ArrayList<Sequence> getListe() {
		return liste;
	}
	
	public Sequence getAt(int i) {
		return liste.get(i);
	}
	
	public void setListe(ArrayList<Sequence> liste) {
		this.liste = liste;
	}
	
	public String toString(){
		String res="name "+index+" number of element "+numberOfElement;
		for(int i=0;i<liste.size();i++){
			res+=" sequence "+getAt(i).getFitnessScore();
		}
		return res;
		
	}
	
}
