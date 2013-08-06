package monitoring;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class MonitoringCSV {
	/**
	 * function for monitoring.
	 * Write the values in a csv file. So we can open it under excel and do graph with the data.
	 * 
	 * @param fileName
	 * 		the path of the file
	 * @param erasePreviousFile
	 * 		indicate if you want to erase the previous file or simply write at the end. True if you want the file to stay or false if
	 * 		you want a new file
	 * @param exectutionTime
	 * 		the execution time since the program was launch
	 * @param score
	 * 		the score of the current sequence
	 * 
	 * @throws IOException 
	 * 	if the file doesn't exist
	 */
	public static void writeCSVFile(String fileName,boolean notErasePreviousFile,double exectutionTime, double score) throws IOException{
		// need to use that trick to use the append at the end of the file
		FileWriter mFileWriter = new FileWriter(fileName, notErasePreviousFile); 
		CSVWriter mCsvWriter = new CSVWriter(mFileWriter,',',' ');// i put a white space so i can use the trim function during the readind and avoid a regular expression or
		//a split with an array as a result
		String[] entries;
		//if new file, we put headers
		if(!notErasePreviousFile){
			entries= "TIME,SCORE".split(",");
			mCsvWriter.writeNext(entries);
		}
	    entries = (exectutionTime+","+score).split(",");
	    mCsvWriter.writeNext(entries);
	    mCsvWriter.close();
	}

	/**
	 * function which display the curve of values stored in the csv
	 */
	public static void displayCSV(){
		new Curve();
	}
}
