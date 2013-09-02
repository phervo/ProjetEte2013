package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import elements.FormantSequence;
import exceptions.FormantNumberexception;
import vue.LaunchFrame;

/**
 * this class define the actions for the combobox selection int he Lacunchframe. 
 */
public class ActionSelectionCombobox extends AbstractAction implements Observer{
	private FormantSequence modele;
	private LaunchFrame vue;
	
	public ActionSelectionCombobox(LaunchFrame  v){
		modele=null;
		vue=v;
		vue.getTextF1().setEnabled(false);
		vue.getTextF2().setEnabled(false);
		vue.getTextF3().setEnabled(false);
		vue.getRunButton().setEnabled(false);
	}
	
	/**
	 * for observer, allow to continue the program when the target is selected
	 */
	@Override
	public void update(Observable arg0, Object arg1) { 
		// TODO Auto-generated method stub
		try {
			vue.getTextF1().setText(""+modele.getFormantAt(0).getFrequency());
			vue.getTextF2().setText(""+modele.getFormantAt(1).getFrequency());
			vue.getTextF3().setText(""+modele.getFormantAt(2).getFrequency());
			ActionRunButton arb = new ActionRunButton(vue,modele);
			vue.getRunButton().setAction(arb);
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * for AbstractAction.
	 * select the modele and set an observer on it
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JComboBox<FormantSequence> jc = (JComboBox)arg0.getSource();
		modele=(FormantSequence) jc.getSelectedItem();
		modele.addObserver(this);
		modele.synchronise();
	}

	

}
