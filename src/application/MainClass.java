package application;

import vue.LaunchFrame;
import communication.CloseServer;
import communication.ServerThread;
import controler.ActionNewTargetButton;
import controler.ActionRunButton;
import controler.ActionSelectionCombobox;
import elements.FormantSequence;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

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
		FormantSequence target = null;
		ActionSelectionCombobox asc = new ActionSelectionCombobox(vue);
		vue.getSelectionCombobox().setAction(asc);
		ActionRunButton arb = new ActionRunButton();
		vue.getRunButton().setAction(arb);
		ActionNewTargetButton antb = new ActionNewTargetButton(vue);
		vue.getNewTarget().setAction(antb);
		/*GeneticAlgorithmCall ga = new GeneticAlgorithmCall(16); //init
		ServerThread.getInstance(ga);
		ga.startAlgorithm();
		CloseServer.envoyerMessageFermeture();*/
	}
}

