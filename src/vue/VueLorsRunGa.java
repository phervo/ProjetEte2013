package vue;

import java.awt.GridLayout;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class VueLorsRunGa extends JFrame{
	private JTextArea textArea;
	private JPanel bottomPanel;
	private JLabel mylabel;
	private JButton quitButton;
	private JScrollPane scroll;
	private JPanel middlePanel;
	/**
	 * A mutex to avoid that the display of the window was cancelled by the GA 's run
	 */
	private final Semaphore semaphore = new Semaphore(1, true);
	
	public VueLorsRunGa(){
	// TODO Auto-generated method stub
		super();
		try {
			semaphore.acquire();
			this.setTitle("Run informations");
			this.setSize(500, 500);
			this.setLayout(new GridLayout(2,1));
			textArea = new JTextArea(12,40);
	        scroll = new JScrollPane(textArea);
	        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	        //Add Textarea in to middle panel
	        JPanel middlePanel = new JPanel ();
	        middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
	        middlePanel.add(scroll);
			
			this.add(middlePanel);
			mylabel = new JLabel("<html>The results are available int the repository called \"results\"<br>"
					+ "There you can find the scripts and the sounds genrated during the run<br>"
					+ "you can also find the .csv containing the details of the run<br>"
					+ "but you have to save the curves manually</html>");
			quitButton = new JButton();
			bottomPanel = new JPanel();
			bottomPanel.add(mylabel);
			bottomPanel.add(quitButton);
			bottomPanel.setVisible(false);
			this.add(bottomPanel);
			//this.pack();
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// a changer
			this.setResizable(false);
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JPanel getBottomPanel() {
		return bottomPanel;
	}
	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}
	public JLabel getMylabel() {
		return mylabel;
	}
	public void setMylabel(JLabel mylabel) {
		this.mylabel = mylabel;
	}
	public JButton getQuitButton() {
		return quitButton;
	}
	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
	
	public Semaphore getSemaphore() {
		return semaphore;
	}
	
	
}
