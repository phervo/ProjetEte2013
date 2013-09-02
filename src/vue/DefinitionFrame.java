package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
	private JTextField fieldMargin;
	private JLabel labelName;
	private JLabel labelF1;
	private JLabel labelF2;
	private JLabel labelF3;
	private JLabel labelMargin;
	private JPanel panelName;
	private JPanel panelF1;
	private JPanel panelF2;
	private JPanel panelF3;
	private JPanel panelMargin;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	
	public DefinitionFrame(){
		this.setTitle("Create new target");
		this.setSize(300, 300);
		this.setLayout(new GridLayout(6, 1));
		//creation of the panel for the name
		labelName=new JLabel("Name for the new Target: ");
		fieldName=new JTextField(4);
		panelName=new JPanel();
		panelName.add(labelName);
		panelName.add(fieldName);
		this.add(panelName);
		//creation of the panel for F1
		labelF1=new JLabel("Value For F1: ");
		fieldF1=new JTextField(4);
		panelF1=new JPanel();
		panelF1.add(labelF1);
		panelF1.add(fieldF1);
		this.add(panelF1);
		//creation of the panel for F2
		labelF2=new JLabel("Value For F2: ");
		fieldF2=new JTextField(4);
		panelF2=new JPanel();
		panelF2.add(labelF2);
		panelF2.add(fieldF2);
		this.add(panelF2);
		//creation of the panel for F3
		labelF3=new JLabel("Value For F3: ");
		fieldF3=new JTextField(4);
		panelF3=new JPanel();
		panelF3.add(labelF3);
		panelF3.add(fieldF3);
		this.add(panelF3);
		//creation of the panel for margin
		labelMargin=new JLabel("Autorised margin(): ");
		fieldMargin=new JTextField(4);
		panelMargin=new JPanel();
		panelMargin.add(labelMargin);
		panelMargin.add(fieldMargin);
		this.add(panelMargin);
		//then the buttons
		okButton= new JButton("Ok");
		cancelButton= new JButton("Cancel");
		buttonPanel= new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// a changer
		this.setResizable(false);
	}

	public JTextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(JTextField fieldName) {
		this.fieldName = fieldName;
	}

	public JTextField getFieldF1() {
		return fieldF1;
	}

	public void setFieldF1(JTextField fieldF1) {
		this.fieldF1 = fieldF1;
	}

	public JTextField getFieldF2() {
		return fieldF2;
	}

	public void setFieldF2(JTextField fieldF2) {
		this.fieldF2 = fieldF2;
	}

	public JTextField getFieldF3() {
		return fieldF3;
	}

	public void setFieldF3(JTextField fieldF3) {
		this.fieldF3 = fieldF3;
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
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

	public JPanel getPanelName() {
		return panelName;
	}

	public void setPanelName(JPanel panelName) {
		this.panelName = panelName;
	}

	public JPanel getPanelF1() {
		return panelF1;
	}

	public void setPanelF1(JPanel panelF1) {
		this.panelF1 = panelF1;
	}

	public JPanel getPanelF2() {
		return panelF2;
	}

	public void setPanelF2(JPanel panelF2) {
		this.panelF2 = panelF2;
	}

	public JPanel getPanelF3() {
		return panelF3;
	}

	public void setPanelF3(JPanel panelF3) {
		this.panelF3 = panelF3;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JTextField getFieldMargin() {
		return fieldMargin;
	}

	public void setFieldMargin(JTextField fieldMargin) {
		this.fieldMargin = fieldMargin;
	}

	public JLabel getLabelMargin() {
		return labelMargin;
	}

	public void setLabelMargin(JLabel labelMargin) {
		this.labelMargin = labelMargin;
	}

	public JPanel getPanelMargin() {
		return panelMargin;
	}

	public void setPanelMargin(JPanel panelMargin) {
		this.panelMargin = panelMargin;
	}
		
}
