package monitoring;

import javax.swing.JFrame;

import au.com.bytecode.opencsv.CSVReader;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Curve extends JFrame{
	
	public Curve(){
		setSize(500, 500);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}
	
	/**
	 * function which read the csv file and draw the curve
	 */
	public void paint(Graphics g) {
		int numberOfIteration=0;
		int storedValue1=0;
		int storedValue2=0;
		
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("C:/Users/phervo/Documents/dossierProjet/algoritmProgression.csv"),',',' ' , 1); //cf opencsv api
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		        // nextLine[] is an array of values from the line
		    	if(numberOfIteration==0){
		    		g.drawLine(0,0,(int)Double.parseDouble(nextLine[0].trim())*100,(int)Double.parseDouble(nextLine[1].trim())*100);	
		    	}else{
		    		g.drawLine(storedValue1,storedValue2,(int)Double.parseDouble(nextLine[0].trim())*100,(int)Double.parseDouble(nextLine[1].trim())*100);
		    	}
		    	numberOfIteration++;
		    	storedValue1=(int)Double.parseDouble(nextLine[0].trim())*100;
		    	System.out.println(storedValue1);
	    		storedValue2=(int)Double.parseDouble(nextLine[1].trim())*100;
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
