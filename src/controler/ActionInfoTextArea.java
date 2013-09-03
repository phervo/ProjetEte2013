package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import elements.ModeleString;
import vue.VueLorsRunGa;

public class ActionInfoTextArea implements Observer{
	private VueLorsRunGa vue;
	private ModeleString modele;
	
	
	public ActionInfoTextArea(VueLorsRunGa vue, ModeleString modele) {
		super();
		this.vue = vue;
		this.modele = modele;
		vue.getTextArea().setEditable(false);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(!modele.isFinRun()){
			vue.getTextArea().append(modele.getMyString().toString()+"\n");
		}else{
			vue.getBottomPanel().setVisible(true);
		}
	}

}
