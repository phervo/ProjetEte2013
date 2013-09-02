package application;

import vue.LaunchFrame;
import controler.ActionNewTargetButton;
import controler.ActionSelectionCombobox;
import exceptions.FormantNumberexception;


/** <p>Main class which call the Genetic algorithm, praat and the java Server<br/>
 * It is the executed part of the program</p>
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */

public class MainClass {
	
	public static void main(String[] args) throws FormantNumberexception {
		// TODO Auto-generated method stub
		LaunchFrame vue= new LaunchFrame();
		ActionSelectionCombobox asc = new ActionSelectionCombobox(vue);
		vue.getSelectionCombobox().setAction(asc);
		ActionNewTargetButton antb = new ActionNewTargetButton(vue);
		vue.getNewTarget().setAction(antb);
	}
}

