package monitoring;

import java.io.FileWriter;
import java.io.IOException;
import elements.FormantSequence;
import elements.Sequence;
import exceptions.FormantNumberexception;
import au.com.bytecode.opencsv.CSVWriter;

public class MonitoringCSV {
	/**
	 * function for monitoring.
	 * Write the values in a csv file. So we can open it under excel and do graph with the data.
	 * 
	 * @param fileName
	 * 		the path of the file
	 * @param notErasePreviousFile
	 * 		indicate if you want to erase the previous file or simply write at the end. True if you want the file to stay or false if
	 * 		you want a new file
	 * @param exectutionTime
	 * 		the execution time since the program was launch
	 * @param score
	 * 		the score of the current sequence
	 * @param sequence 
	 * 		the best sequence of the generation
	 * @throws IOException 
	 * 	if the file doesn't exist
	 */
	public static void writeCSVFile(String fileName,boolean notErasePreviousFile,double exectutionTime,int generationCount, double score, Sequence sequence,FormantSequence target) throws IOException{
		// need to use that trick to use the append at the end of the file
		FileWriter mFileWriter = new FileWriter(fileName, notErasePreviousFile); 
		CSVWriter mCsvWriter = new CSVWriter(mFileWriter,',',' ');// i put a white space so i can use the trim function during the readind and avoid a regular expression or
		//a split with an array as a result
		String[] entries;
		//if new file, we put headers
		if(!notErasePreviousFile){
			entries= ("TARGET,"+target.getSoundName()+","+"NBFORMANTUSEDINSIMULATION,"+target.getNbFormant()).split(",");
			mCsvWriter.writeNext(entries);
			entries= "TIME,GENERATION,SCORE,F1,F2,F3,FORMANT_FOUND,Sequence".split(",");
			mCsvWriter.writeNext(entries);
		}
	    entries = (exectutionTime+","+generationCount+","+score+","+sequence.getF1().getFrequency()+","+sequence.getF2().getFrequency()+","+sequence.getF3().getFrequency()+","+sequence.getFormantFound()+","+sequence.getValuesInString()).split(",");
	    mCsvWriter.writeNext(entries);
	    mCsvWriter.close();
	}

	/**
	 * function which display the curve of values stored in the csv
	 * @param target
	 * 		the FormantSequence used as target by the GA.
	 */
	public static void displayCSV(FormantSequence target){
		try {
			new Curve(target);
		} catch (FormantNumberexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
