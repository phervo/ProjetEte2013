package messages;

import geneticAlogrithm.Formant;
import geneticAlogrithm.FormantSequence;
import geneticAlogrithm.GeneticAlgorithmCall;
import geneticAlogrithm.Sequence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MessageGestion {
	private static FileWriter f;
	private static BufferedWriter output;
	
	public static String writePraatScriptHeader(){
		StringBuilder stb= new StringBuilder(); 
		stb.append("#-----------------------------------------------\n");
		stb.append("# Project : Software synthesis using GA\n");
		stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Erase all\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
		stb.append("Create Artword... phon 0.5\n");//creation of actions list(init)
		stb.append("#-----------------------------------------------\n");
		return stb.toString();
	}

	public static String writePraatScriptAsCandidates(Sequence candidat){
			StringBuilder stb= new StringBuilder(); //utilisation de stringBuilder car je suppose que pas synchroniser
			stb.append("#-----------------------------------------------\n");
			stb.append("# Project : Software synthesis using GA\n");
			stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
			stb.append("#-----------------------------------------------\n");
			//we complete artword with the set tqrget methods below
			stb.append("select Artword phon\n");
			stb.append("# Supply lung energy\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Set target... 0.00    "+candidat.getValuesAt(0)+" Lungs\n"); //"+candidat.getValuesAt(0)+"
			//stb.append("Set target... 0.03    0.0 Lungs\n");
			//stb.append("Set target... 0.5     0.0 Lungs\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("# Control glottis\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("#Glottal closure\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(1)+" Interarytenoid\n");
			//stb.append("Set target... 0.5   0.5 Interarytenoid\n");
			stb.append("#\n");
			stb.append("# Adduct vocal folds\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(2)+" Cricothyroid\n");
			//stb.append("Set target... 0.5   1.0 Cricothyroid\n");
			stb.append("# Close velopharyngeal port\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(3)+" LevatorPalatini\n");
			//stb.append("Set target... 0.5   1.0 LevatorPalatini\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("# Shape mouth to open vowel\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("# Lower the jaw\n");
			stb.append("# -----------------------------------------\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(4)+" Masseter\n");
			//stb.append("Set target... 0.5   -0.4 Masseter\n");
			stb.append("# Pull tongue backwards\n");
			stb.append("Set target... 0.0   "+candidat.getValuesAt(5)+" Hyoglossus\n");
			//stb.append("Set target... 0.5   0.4 Hyoglossus\n");
			stb.append("# Synthesise the sound\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("select Artword phon\n");
			stb.append("plus Speaker Robovox\n");
			stb.append("To Sound... 22050 25   0 0 0    0 0 0    0 0 0\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("# Automatic data extraction part\n");
			stb.append("# 1) get the values\n");
			stb.append("To Formant (burg)... 0 5 5500 0.025 50\n");
			stb.append("numberOfFormant = Get number of formants... 1\n");
			stb.append("if numberOfFormant>3\n");
			stb.append("time = Get total duration\n");
			stb.append("midTime = time/2\n");
			stb.append("for intervalNumber from 1 to 3 \n");//number 3 fix
			stb.append("varTabFreq[intervalNumber] = Get mean... intervalNumber 0 0 \"Hertz\"\n");
			stb.append("varTabBandWith[intervalNumber] =  Get bandwidth at time... intervalNumber midTime \"Hertz\" \"Linear\"\n");
			stb.append("endfor\n");
			stb.append("# convert it into string for sendsocket\n");
			//i havent find a way with this version of praat to do otherwise
			//that to write like that but  it could maybe be change in the future if the praat api change
			stb.append("temp1$=string$(varTabFreq[1])\n");
			stb.append("temp2$=string$(varTabFreq[2])\n");
			stb.append("temp3$=string$(varTabFreq[3])\n");
			stb.append("temp4$=string$(varTabBandWith[1])\n");
			stb.append("temp5$=string$(varTabBandWith[2])\n");
			stb.append("temp6$=string$(varTabBandWith[3])\n");
			stb.append("sendsocket localhost:2009 'temp1$' 'temp2$' 'temp3$' 'temp4$' 'temp5$' 'temp6$'\n");
			//sinon on passe un message d erreur
			stb.append("else\n");
			stb.append("sendsocket localhost:2009 INF \n");
			stb.append("endif\n");
		return stb.toString();
	}
	
	public static String writePraatScriptAsCandidatesWithoutCalculs(Sequence candidat){
		StringBuilder stb= new StringBuilder(); //utilisation de stringBuilder car je suppose que pas synchroniser
		stb.append("#-----------------------------------------------\n");
		stb.append("# Project : Software synthesis using GA\n");
		stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
		stb.append("#-----------------------------------------------\n");
		//we complete artword with the set tqrget methods below
		stb.append("select Artword phon\n");
		stb.append("# Supply lung energy\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Set target... 0.00    "+candidat.getValuesAt(0)+" Lungs\n"); //"+candidat.getValuesAt(0)+"
		//stb.append("Set target... 0.03    0.0 Lungs\n");
		//stb.append("Set target... 0.5     0.0 Lungs\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("# Control glottis\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("#Glottal closure\n");
		stb.append("Set target... 0.0   "+candidat.getValuesAt(1)+" Interarytenoid\n");
		//stb.append("Set target... 0.5   0.5 Interarytenoid\n");
		stb.append("#\n");
		stb.append("# Adduct vocal folds\n");
		stb.append("Set target... 0.0   "+candidat.getValuesAt(2)+" Cricothyroid\n");
		//stb.append("Set target... 0.5   1.0 Cricothyroid\n");
		stb.append("# Close velopharyngeal port\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Set target... 0.0   "+candidat.getValuesAt(3)+" LevatorPalatini\n");
		//stb.append("Set target... 0.5   1.0 LevatorPalatini\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("# Shape mouth to open vowel\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("# Lower the jaw\n");
		stb.append("# -----------------------------------------\n");
		stb.append("Set target... 0.0   "+candidat.getValuesAt(4)+" Masseter\n");
		//stb.append("Set target... 0.5   -0.4 Masseter\n");
		stb.append("# Pull tongue backwards\n");
		stb.append("Set target... 0.0   "+candidat.getValuesAt(5)+" Hyoglossus\n");
		//stb.append("Set target... 0.5   0.4 Hyoglossus\n");
		stb.append("# Synthesise the sound\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("select Artword phon\n");
		stb.append("plus Speaker Robovox\n");
		stb.append("To Sound... 22050 25   0 0 0    0 0 0    0 0 0\n");
		stb.append("#-----------------------------------------------\n");
	return stb.toString();
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
	
	public static FormantSequence splitChaineToFormantSequence(String chaine){
		/*function to parse the string i get with praat to a formantSequence*/
		/*y a peu etre des souccis ici a voir lors des tests*/
		String[] tab= chaine.split(" ");
		FormantSequence fms= new FormantSequence("candidat", 3, new ArrayList<Formant>());
		Formant f1= new Formant();
		Formant f2= new Formant();
		Formant f3= new Formant();
		if(tab.length>1){
			f1.setFrequency(Double.parseDouble(tab[0]));
			f2.setFrequency(Double.parseDouble(tab[1]));
			f3.setFrequency(Double.parseDouble(tab[2]));
			f1.setBandwith(Double.parseDouble(tab[3]));
			f2.setBandwith(Double.parseDouble(tab[4]));
			f3.setBandwith(Double.parseDouble(tab[5]));
			fms.getList().add(f1);
			fms.getList().add(f2);
			fms.getList().add(f3);
		}else{//else its "FIN" and it will end but we had to create a var to return else it can freeze sometimes
			f1.setFrequency(0.0);
			f2.setFrequency(0.0);
			f3.setFrequency(0.0);
			f1.setBandwith(0.0);
			f2.setBandwith(0.0);
			f3.setBandwith(0.0);
			fms.getList().add(f1);
			fms.getList().add(f2);
			fms.getList().add(f3);
		}
		return fms;
	}
}
