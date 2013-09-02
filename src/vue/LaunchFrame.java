package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import elements.FormantSequence;

public class LaunchFrame extends JFrame{
	private JComboBox liste;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JLabel explanationLabel;
	private JButton newTarget;
	private JButton runButton;
	private JTextField textF1;
	private JTextField textF2;
	private JTextField textF3;
	private JLabel labelF1;
	private JLabel labelF2;
	private JLabel labelF3;
	
	public LaunchFrame(){
		this.setTitle("Choose your target");
		this.setSize(300, 300);
		this.setLayout(new GridLayout(4,1));
		//1 define the explanation texte
		explanationLabel = new JLabel("<html>Choose your target Sound in the combobox.<br>"
				+ "If you want to use another sound than those specified in it ,"
				+ " click on the \"new target...\" button,<br> else click on the \"run\" button.</html>");
		this.add(explanationLabel);
		//2 set the combobox
		FormantSequence[] elements = new FormantSequence[]{new FormantSequence("a"),new FormantSequence("e"),new FormantSequence("u")};
		liste = new JComboBox(elements);
		liste.setSelectedItem(null);
		this.add(liste);
		//creation of the center Panel
		labelF1 = new JLabel("F1: ");
		labelF2 = new JLabel("F2: ");
		labelF3 = new JLabel("F3: ");
		textF1 = new JTextField(4);
		//textF1.setEnabled(false);
		textF2 = new JTextField(4);
		//textF2.setEnabled(false);
		textF3 = new JTextField(4);
		//textF3.setEnabled(false);
		centerPanel= new JPanel();
		centerPanel.add(labelF1);
		centerPanel.add(textF1);
		centerPanel.add(labelF2);
		centerPanel.add(textF2);
		centerPanel.add(labelF3);
		centerPanel.add(textF3);
		this.add(centerPanel);
		//creation of the button's panel
		runButton= new JButton("run");
		newTarget= new JButton("new target...");
		bottomPanel = new JPanel();
		bottomPanel.add(runButton);
		bottomPanel.add(newTarget);
		this.add(bottomPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// a changer
		this.setResizable(false);
	}
}
