package vue;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VueLorsRunGa extends JFrame{
	private JTextArea textArea;
	private JPanel bottomPanel;
	private JLabel mylabel;
	private JButton quitButton;
	
	public VueLorsRunGa(){
		this.setTitle("Run informations");
		this.setSize(500, 500);
		this.setLayout(new GridLayout(2,1));
		textArea = new JTextArea();
		this.add(textArea);
		mylabel = new JLabel("<html>The results are available int the repository called \"results\"<br>"
				+ "There you can find the scripts and the sounds genrated during the run<br>"
				+ "you can also find the .csv containing the details of the run<br>"
				+ "but you have to save the curves manually</html>");
		quitButton = new JButton();
		bottomPanel = new JPanel();
		bottomPanel.add(mylabel);
		bottomPanel.add(quitButton);
		this.add(bottomPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// a changer
		this.setResizable(false);
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	
}
