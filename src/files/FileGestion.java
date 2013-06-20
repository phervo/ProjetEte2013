package files;

import geneticAlogrithm.Sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileGestion {
	private static FileWriter f;
	private static BufferedWriter output;
	private static FileReader fr1;
	private static FileReader fr2;
	private static BufferedReader input1;
	private static BufferedReader input2;
	
	public static void writePraatScriptAsCandidates(Sequence candidat){
		try {
			String adresseDuFichier = "./scriptSpeechSynthethiseWithCandidate.praat";
			f= new FileWriter(adresseDuFichier, false); //modifier ici pr le append
			output= new BufferedWriter(f);
			output.write("#-----------------------------------------------\n");
			output.write("# Project : Software synthesis using GA\n");
			output.write("# Hervo Pierre-Yves, automatic Script generated in java\n");
			output.write("#-----------------------------------------------\n");
			output.write("do(\"Erase all\")\n");
			output.write("#-----------------------------------------------\n");
			output.write("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
			output.write("Create Artword... phon 0.5\n"); //creation of actions list(init)
			//we complete artword with the set tqrget methods below
			output.write("#-----------------------------------------------\n");
			output.write("# Supply lung energy\n");
			output.write("#-----------------------------------------------\n");
			output.write("Set target... 0.00       "+candidat.getValuesAt(0)+" Lungs\n");
			//output.write("Set target... 0.03     0.0 Lungs\n");
			//output.write("Set target... 0.5     0.0 Lungs\n");
			output.write("#-----------------------------------------------\n");
			output.write("# Control glottis\n");
			output.write("#-----------------------------------------------\n");
			output.write("#Glottal closure\n");
			output.write("Set target... 0.0   "+candidat.getValuesAt(1)+"   Interarytenoid\n");
			//output.write("Set target... 0.5   0.5 Interarytenoid\n");
			output.write("# Adduct vocal folds\n");
			output.write("Set target... 0.0   "+candidat.getValuesAt(2)+" Cricothyroid\n");
			//output.write("Set target... 0.5   1.0 Cricothyroid\n");
			output.write("#-----------------------------------------------\n");
			output.write("# Close velopharyngeal port\n");
			output.write("#-----------------------------------------------\n");
			output.write("Set target... 0.0   "+candidat.getValuesAt(3)+" LevatorPalatini\n");
			/*output.write("Set target... 0.5   1.0 LevatorPalatini\n");
			output.write("# -----------------------------------------\n");
			output.write("# Shape mouth to open vowel\n");
			output.write("#-----------------------------------------------\n");
			output.write("# Lower the jaw\n");
			output.write("Set target... 0.0   -0.4 Masseter\n");
			//output.write("Set target... 0.5   -0.4 Masseter\n");
			output.write("# Pull tongue backwards\n");
			output.write("Set target... 0.0   0.4 Hyoglossus\n");
			//output.write("Set target... 0.5   0.4 Hyoglossus\n");
			output.write("#-----------------------------------------------\n");*/
			output.write("# Synthesise the sound\n");
			output.write("#-----------------------------------------------\n");
			output.write("select Artword phon\n");
			output.write("plus Speaker Robovox\n");
			output.write("To Sound... 22050 25   0 0 0    0 0 0    0 0 0\n");
			output.write("#-----------------------------------------------\n");
			//partie de generation de fichier a voir pa la suite si je la met a part ou pas
			output.write("do(\"Play\")\n");
			output.write("deleteFile (\"reductedCandidateValues.txt\")\n");
			output.write("stop=0\n");
			output.write("while stop < 0.1\n");
			output.write("val = do (\"Get value at time...\", 0,stop, \"Sinc70\")\n");
			//output.write("appendFileLine(\"reductedCandidateValues.txt\",\"a temp: \",stop,\" valeur: \",val)\n");
			output.write("appendFileLine(\"reductedCandidateValues.txt\",val)\n");
			//output.write("appendFileLine(\"reductedTargetValues.txt\",\"a temp: \",stop,\" valeur: \",val)\n");
			output.write("stop=stop+0.01\n");
			output.write("endwhile\n");
			output.flush();
			output.close();
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String writePraatScriptAsCandidatesSansFichier(Sequence candidat){
			StringBuilder stb= new StringBuilder(); //utilisation de stringBuilder car je suppose que pas synchroniser
			stb.append("#-----------------------------------------------\n");
			stb.append("# Project : Software synthesis using GA\n");
			stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Erase all\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
			stb.append("Create Artword... phon 0.5\n");//creation of actions list(init)
			stb.append("#-----------------------------------------------\n");
			//we complete artword with the set tqrget methods below
			stb.append("# Supply lung energy\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Set target... 0.00       "+candidat.getValuesAt(0)+" Lungs\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("# Control glottis\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("#Glottal closure\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(1)+"   Interarytenoid\n");
			stb.append("Set target... 0.03     0.0 Lungs\n");
			stb.append("Set target... 0.5     0.0 Lungs\n");
			stb.append("# Synthesise the sound\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("select Artword phon\n");
			stb.append("plus Speaker Robovox\n");
			stb.append("To Sound... 22050 25   0 0 0    0 0 0    0 0 0\n");
			stb.append("#-----------------------------------------------\n");
			
			/*stb.append("Read from file... C:/Users/Py/workspace/ProjetSpeechSynthesis/testsonpoumpoum \n");
			stb.append("Play\n");*/
			
			
			//partie de generation de fichier a voir pa la suite si je la met a part ou pas
			/*output.write("do(\"Play\")\n");
			output.write("deleteFile (\"reductedCandidateValues.txt\")\n");
			output.write("stop=0\n");
			output.write("while stop < 0.1\n");
			output.write("val = do (\"Get value at time...\", 0,stop, \"Sinc70\")\n");
			//output.write("appendFileLine(\"reductedCandidateValues.txt\",\"a temp: \",stop,\" valeur: \",val)\n");
			output.write("appendFileLine(\"reductedCandidateValues.txt\",val)\n");
			//output.write("appendFileLine(\"reductedTargetValues.txt\",\"a temp: \",stop,\" valeur: \",val)\n");
			output.write("stop=stop+0.01\n");
			output.write("endwhile\n");
			output.flush();
			output.close();
			f.close();*/
		return stb.toString();
	}
	
	//pour l instant j utilise pas la fonction la etant donne que je veux recuperer deux fois le meme fichier mai par la
	//suite ce sera celle la
	public static void writePraatScriptAsTarget(){
		/*script to extract a part of the target sound in order to get a small spectrum ready for comparation
		 * Use only one time at the begining of the programme*/
		String adresseDuFichier = "./scriptPraatForTargetSpectrum.praat";
		try {
			f= new FileWriter(adresseDuFichier, false);
			output= new BufferedWriter(f);
			output.write("#-----------------------------------------------\n");
			output.write("# Project : Software synthesis using GA\n");
			output.write("# Hervo Pierre-Yves, automatic Script generated in java\n");
			output.write("# Reduced part of the target speectrum in order to compare with the candidate spectrum\n");
			output.write("#-----------------------------------------------\n");
			output.write("do(\"Erase all\")\n");
			output.write("execute scriptPraat.praat\n");
			output.write("do (\"View & Edit\")\n");
			output.write("editor Sound phon_Robovox\n");
			output.write("do (\"Select...\", 0.25, 0.26)\n");
			output.write("do (\"Extract selected sound (preserve times)\")\n");
			output.write("endeditor\n");
			output.write("do (\"To Spectrum...\", \"yes\")\n");
			output.write("do (\"Save as text file...\", \"./reductedTargetSpectrum.Spectrum\")\n");
			output.write("#-----------------------------------------------\n");
			output.flush();
			output.close();
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //modifier ici pr le append
	}
	
	/*depreciated use too much memory*/
	public static int CompareTargetAndCandidates(String TargetFile,String CandidateFile){
		/*fonction fitness pr le moment a mettre ailleur dans package par suite*/
		String ligneTarget=null;
		String ligneCandidate=null;
		int NumberOfMatch=0;
		try {
			fr1 = new FileReader(TargetFile);
			fr2 = new FileReader(CandidateFile);
			input1 = new BufferedReader(fr1);
			input2 = new BufferedReader(fr2);
			
			while ((ligneTarget=input1.readLine())!=null && (ligneCandidate=input2.readLine())!=null){
				float temp1=Float.parseFloat(ligneTarget);
				float temp2=Float.parseFloat(ligneCandidate);
				if(temp1==temp2){
					NumberOfMatch++;
				}
			}
			input1.close();
			input2.close();
			fr1.close();
			fr2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NumberOfMatch;
		
	}
	
	public static void writeInTheLogs(String stringBuffer){ 
		String adresseDuFichier = "/Users/py/workspace/ProjetSpeechSynthesis/log.txt";
		try {
			f= new FileWriter(adresseDuFichier, true); //modifier ici pr le append
			output= new BufferedWriter(f);
			output.write(stringBuffer);
			output.write("\n");
			output.flush();
			output.close();
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
