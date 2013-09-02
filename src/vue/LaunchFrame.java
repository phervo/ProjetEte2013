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
	private JComboBox selectionCombobox;
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
		String[] elements = new String[]{"a","e","u"};
		selectionCombobox = new JComboBox(elements);
		selectionCombobox.setSelectedItem(null);
		this.add(selectionCombobox);
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

	public JComboBox getSelectionCombobox() {
		return selectionCombobox;
	}

	public void setSelectionCombobox(JComboBox liste) {
		this.selectionCombobox = liste;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

	public JLabel getExplanationLabel() {
		return explanationLabel;
	}

	public void setExplanationLabel(JLabel explanationLabel) {
		this.explanationLabel = explanationLabel;
	}

	public JButton getNewTarget() {
		return newTarget;
	}

	public void setNewTarget(JButton newTarget) {
		this.newTarget = newTarget;
	}

	public JButton getRunButton() {
		return runButton;
	}

	public void setRunButton(JButton runButton) {
		this.runButton = runButton;
	}

	public JTextField getTextF1() {
		return textF1;
	}

	public void setTextF1(JTextField textF1) {
		this.textF1 = textF1;
	}

	public JTextField getTextF2() {
		return textF2;
	}

	public void setTextF2(JTextField textF2) {
		this.textF2 = textF2;
	}

	public JTextField getTextF3() {
		return textF3;
	}

	public void setTextF3(JTextField textF3) {
		this.textF3 = textF3;
	}

	public JLabel getLabelF1() {
		return labelF1;
	}

	public void setLabelF1(JLabel labelF1) {
		this.labelF1 = labelF1;
	}

	public JLabel getLabelF2() {
		return labelF2;
	}

	public void setLabelF2(JLabel labelF2) {
		this.labelF2 = labelF2;
	}

	public JLabel getLabelF3() {
		return labelF3;
	}

	public void setLabelF3(JLabel labelF3) {
		this.labelF3 = labelF3;
	}
	
	
}
