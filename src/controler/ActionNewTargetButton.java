package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import elements.FormantSequence;
import vue.DefinitionFrame;
import vue.LaunchFrame;

public class ActionNewTargetButton extends AbstractAction {
	private FormantSequence modele;
	private LaunchFrame vue;
	
	public ActionNewTargetButton(LaunchFrame v){
		modele=null;
		vue=v;
		this.putValue(NAME, "new target...");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DefinitionFrame f= new DefinitionFrame();
		ActionOk aok= new ActionOk(vue,f);
		f.getOkButton().setAction(aok);
		ActionCancel acan = new ActionCancel(f);
		f.getCancelButton().setAction(acan);
	}

}
