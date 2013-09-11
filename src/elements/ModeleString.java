package elements;

import java.util.Observable;

/**
 * A StringBuffer that will contain all the informations from the Ga 's run.
 * It is use in the MVC modele to actualise the textarea field of the frame during the run.
 * @author phervo
 *
 */
public class ModeleString extends Observable{
	private String myString;
	private boolean finRun;
	
	public ModeleString(){
		this.myString = new String();
		this.finRun = false;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
		this.setChanged();
		this.notifyObservers();
	}

	public boolean isFinRun() {
		return finRun;
	}

	public void setFinRun(boolean finRun) {
		this.finRun = finRun;
	}
	
	
}
