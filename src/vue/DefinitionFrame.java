package vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import elements.FormantSequence;

public class DefinitionFrame extends JFrame{
	private JTextField fieldName;
	private JTextField fieldF1;
	private JTextField fieldF2;
	private JTextField fieldF3;
	private JLabel labelName;
	private JLabel labelF1;
	private JLabel labelF2;
	private JLabel labelF3;
	private JPanel panelName;
	private JPanel panelF1;
	private JPanel panelF2;
	private JPanel panelF3;
	private JPanel centerPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	public DefinitionFrame(){
		this.setTitle("Create new target");
		this.setSize(600, 600);
		//creation of the panel for the name
		labelName=new JLabel("Name for the new Target: ");
		fieldName=new JTextField();
		panelName=new JPanel(new BorderLayout());
		panelName.add(labelName,BorderLayout.WEST);
		panelName.add(fieldName,BorderLayout.EAST);
		//creation of the panel for F1
		labelF1=new JLabel("Value For F1: ");
		fieldF1=new JTextField();
		panelF1=new JPanel(new BorderLayout());
		panelF1.add(labelF1,BorderLayout.WEST);
		panelF1.add(fieldF1,BorderLayout.EAST);
		//creation of the panel for F2
		labelF2=new JLabel("Value For F1: ");
		fieldF2=new JTextField();
		panelF2=new JPanel(new BorderLayout());
		panelF2.add(labelF2,BorderLayout.WEST);
		panelF2.add(fieldF2,BorderLayout.EAST);
		//creation of the panel for F3
		labelF3=new JLabel("Value For F1: ");
		fieldF3=new JTextField();
		panelF3=new JPanel(new BorderLayout());
		panelF3.add(labelF3,BorderLayout.WEST);
		panelF3.add(fieldF3,BorderLayout.EAST);
		//add all to a panel
		centerPanel= new JPanel();
		centerPanel.add(panelName);
		centerPanel.add(panelF1);
		centerPanel.add(panelF2);
		centerPanel.add(panelF3);
		this.add(centerPanel,BorderLayout.CENTER);
		okButton= new JButton("Ok");
		cancelButton= new JButton("cancelButton"); //a ajouter
		this.add(okButton,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// a changer
	}
}
