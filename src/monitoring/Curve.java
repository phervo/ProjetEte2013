package monitoring;

import exceptions.FormantNumberexception;
import geneticAlogrithm.GeneticAlgorithmCall;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import au.com.bytecode.opencsv.CSVReader;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Curve{

	/**
	 * function which read the csv file and draw the curve.
	 * Use the read functions of the opencsv api and the mathPlot 2.0 api to draw the curve
	 * 
	 * @param ga
	 * 		the ga used. We need it to get the target and do the comparison
	 * @throws FormantNumberexception 
	 */
	public Curve(GeneticAlgorithmCall ga) throws FormantNumberexception{
		CSVReader reader;
		String[] row = null;
		double[] absis=null; // the same for the two
		double[] ordoneeF1=null;
		double[] ordoneeF2=null;
		int compteur=0;
		try {
			/*
			 * 1st we read the csv file to extract the information and get tzo array representing the absis and the ordonee
			 * For that purpose we use the opencsv api 
			 */
			reader = new CSVReader(new FileReader("C:/Users/phervo/Documents/dossierProjet/algoritmProgression.csv"),',',' ' , 1); //cf opencsv api
			List content = reader.readAll();
			//init of the lists
			absis= new double[content.size()];			
			ordoneeF1 =new double[content.size()];	
			ordoneeF2 =new double[content.size()];	
			//decomposition in two array, one for the values of the absis and one for the values of the ordonee
			for (Object object : content) {
			    row = (String[]) object;
			    absis[compteur]=Double.parseDouble(row[0].trim());
			    ordoneeF1[compteur]=405.0-Double.parseDouble(row[2].trim());
			    ordoneeF2[compteur]=2080.0-Double.parseDouble(row[3].trim());
			    //ordoneeF1[compteur]=ga.getTarget().getFormantAt(0).getFrequency()-Double.parseDouble(row[2].trim());
			    //ordoneeF2[compteur]=ga.getTarget().getFormantAt(1).getFrequency()-Double.parseDouble(row[3].trim());
			    compteur++;
			}
			reader.close();
			for(int i=0;i<content.size();i++){
				System.out.println(absis[i]);
			}
			System.out.println();
			for(int i=0;i<content.size();i++){
				System.out.println(ordoneeF1[i]);
			}
			
			System.out.println();
			for(int i=0;i<content.size();i++){
				System.out.println(ordoneeF2[i]);
			}
			
			/*
			 *then the drawing part 
			 */
			
			// create your PlotPanel (you can use it as a JPanel)
			Plot2DPanel plot = new Plot2DPanel();
			
			// define the legend position
			plot.addLegend("SOUTH");
			
			// add a line plot to the PlotPanel
			plot.addLinePlot("F1", absis, ordoneeF1);
			plot.addLinePlot("F2",absis,ordoneeF2);
			// put the PlotPanel in a JFrame like a JPanel
			JFrame frame = new JFrame("a plot panel");
			frame.setSize(600, 600);
			frame.setContentPane(plot);
			frame.setVisible(true);
			
			 
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