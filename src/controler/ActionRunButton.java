package controler;

import elements.FormantSequence;
import elements.ModeleString;
import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.ThreadRun;

import java.awt.EventQueue;
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
		vue.dispose();
		VueLorsRunGa vrga = new VueLorsRunGa();
		EventQueue.invokeLater(new ThreadRun(modele,vrga));
	}
}
