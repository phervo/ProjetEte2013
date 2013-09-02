package controler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import vue.DefinitionFrame;
import vue.LaunchFrame;
import elements.Formant;
import elements.FormantSequence;
import exceptions.FormantNumberexception;

public class ActionOk extends AbstractAction implements Observer{
	private FormantSequence modele;
	private LaunchFrame vue1;
	private DefinitionFrame vue2;
	
	public ActionOk(LaunchFrame v,DefinitionFrame v2){
		modele=null;
		vue1=v;
		vue2=v2;
		this.putValue(NAME, "Ok");
	}
	@Override
	public void actionPerformed(ActionEvent e) { // recuperer la page ici et pas suelemtn le boutton...
		// TODO Auto-generated method stub
		//JComboBox<String> jc = (JComboBox)e.getSource();
		if(vue2.getFieldName().getText().equals("")!=true && vue2.getFieldF1().getText().equals("")!=true && vue2.getFieldF2().getText().equals("")!=true && vue2.getFieldF3().getText().equals("")!=true && vue2.getFieldMargin().getText().equals("")!=true){
			if(Double.valueOf(vue2.getFieldF1().getText())!=0.0 && Double.valueOf(vue2.getFieldF2().getText())!=0.0 && Double.valueOf(vue2.getFieldF3().getText())!=0.0 && Double.valueOf(vue2.getFieldMargin().getText())!=0.0){
				ArrayList<Formant> list= new ArrayList<Formant>();
				Formant f1= new Formant(Double.valueOf(vue2.getFieldF1().getText()), 0.0, 0.0);
				list.add(f1);
				Formant f2= new Formant(Double.valueOf(vue2.getFieldF2().getText()), 0.0, 0.0);
				list.add(f2);
				Formant f3= new Formant(Double.valueOf(vue2.getFieldF3().getText()), 0.0, 0.0);
				list.add(f3);
				try {
					modele = new FormantSequence(vue2.getFieldName().getText(), list.size(), list, Double.valueOf(vue2.getFieldMargin().getText()));
				} catch (FormantNumberexception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modele.addObserver(this);
				modele.synchronise();
			}else{
				System.out.println("values must be != fom 0.0");
			}
		}else{
			System.out.println("you must enter a value (!=0)for each field");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		vue1.getSelectionCombobox().addItem(modele);
		vue1.getSelectionCombobox().setSelectedItem(modele);
		vue2.dispose();
	}

}
