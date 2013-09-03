package controler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import vue.VueLorsRunGa;

public class ActionQuitButton extends AbstractAction{
	private VueLorsRunGa vue;
	public ActionQuitButton( VueLorsRunGa vue){
		this.vue=vue;
		this.putValue(NAME, "Quit");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		vue.dispose();
	}
	
}
