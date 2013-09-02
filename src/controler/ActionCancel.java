package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;

import vue.DefinitionFrame;

public class ActionCancel extends AbstractAction{
	private DefinitionFrame vue;
	
	public ActionCancel(DefinitionFrame v){
		vue=v;
		this.putValue(NAME, "Cancel");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.vue.dispose();	
	}
	
}
