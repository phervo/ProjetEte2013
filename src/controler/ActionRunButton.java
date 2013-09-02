package controler;

import elements.FormantSequence;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import communication.CloseServer;
import communication.ServerThread;
import vue.LaunchFrame;
import vue.VueLorsRunGa;

public class ActionRunButton extends AbstractAction{
	private LaunchFrame vue;
	private FormantSequence modele;
	
	public ActionRunButton(LaunchFrame v,FormantSequence mod){
		vue=v;
		modele=mod;
		this.putValue(NAME,"Run");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		VueLorsRunGa vrga = new VueLorsRunGa();
		ActionInfoTextArea aia = new ActionInfoTextArea();
		//vrga.getTextArea().setAction(aia);
		vue.dispose();
		GeneticAlgorithmCall ga;
		/*try {
			ga = new GeneticAlgorithmCall(16);
			ServerThread.getInstance(ga);
			ga.buildTarget(modele);
			ga.startAlgorithm();
			CloseServer.envoyerMessageFermeture();
		} catch (FormantNumberexception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
}
