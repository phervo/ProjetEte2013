package controler;

import elements.FormantSequence;
import elements.ModeleString;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

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
		ModeleString modeleNewFrame=new ModeleString();
		ActionInfoTextArea aia = new ActionInfoTextArea(vrga, modeleNewFrame);
		modeleNewFrame.addObserver(aia);
		vue.dispose();
		//modeleNewFrame.setMyString("blabla");
		//modeleNewFrame.setMyString("reblabla");
		
		GeneticAlgorithmCall ga;
		try {
			ga = new GeneticAlgorithmCall(8,modeleNewFrame);
			ServerThread.getInstance(ga);
			ga.buildTarget(modele);
			ga.startAlgorithm();
			CloseServer.envoyerMessageFermeture(modeleNewFrame);
		} catch (FormantNumberexception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
