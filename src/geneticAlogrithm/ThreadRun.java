package geneticAlogrithm;

import vue.VueLorsRunGa;
import communication.CloseServer;
import communication.ServerThread;
import controler.ActionInfoTextArea;
import controler.ActionQuitButton;
import elements.FormantSequence;
import elements.ModeleString;
import exceptions.FormantNumberexception;

public class ThreadRun implements Runnable{

	private FormantSequence modele;
	private VueLorsRunGa vrga;
	public ThreadRun(FormantSequence modele,VueLorsRunGa vrga){
		this.modele=modele;
		this.vrga=vrga;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		GeneticAlgorithmCall ga;
		try {
			ModeleString modeleNewFrame=new ModeleString();
			ActionInfoTextArea aia = new ActionInfoTextArea(vrga, modeleNewFrame);
			modeleNewFrame.addObserver(aia);
			ActionQuitButton acq = new ActionQuitButton(vrga);
			vrga.getQuitButton().setAction(acq);
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
