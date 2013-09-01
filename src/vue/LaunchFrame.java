package vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elements.FormantSequence;

public class LaunchFrame extends JFrame{
	private JComboBox liste;
	private JPanel centerPanel;
	private JLabel explanationLabel;
	private JButton newTarget;
	private JButton runButton;
	
	public LaunchFrame(){
		this.setTitle("Choose your target");
		this.setSize(600, 600);
		//creation fo the comboBox, it go top
		FormantSequence[] elements = new FormantSequence[]{new FormantSequence("a"),new FormantSequence("e"),new FormantSequence("u")};
		liste = new JComboBox(elements);
		this.add(liste,BorderLayout.NORTH);
		//creation of the center Panel
		explanationLabel=new JLabel("If you want to use another sound than those specified int he combobox, click on the \"new target...\" button, else click on the \"run\" button");
		newTarget= new JButton("new target...");
		centerPanel= new JPanel(new BorderLayout());
		centerPanel.add(explanationLabel,BorderLayout.WEST);
		centerPanel.add(explanationLabel,BorderLayout.EAST);
		runButton= new JButton("run");
		this.add(runButton,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// a changer
	}
}
