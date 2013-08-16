package messages;

import elements.Sequence;
import exceptions.PraatScriptException;
import exceptions.SequenceArrayException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import praatGestion.OrderToPraat;


/** <p>Class which define the different messages that are send to Praat<br/>
 *  All the method from this class are static so no need to create an instance<br/>
 *  The scripts generated aren't executed, they are designed to be used with the sendMessageToPrat function of the OrderToPraat</p>
 * 
 * @see MessageFromPraat
 * @see OrderToPraat#sendMessageToPrat(String)
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class MessageToPraat {
	/**
	 * The number of formants to get from praat. This number is used in the scripts to get the correct number of formant.
	 * If you modify it, be careful, this number must correspond to those used in the GA or you will get errors.
	 * 
	 */
	private static final int nbFormantMax=2; //barre superieure de recherche
	
	/**
	 * The number of var to send to praat in the scripts.
	 * It allow to verify that the sequence given to the function is long enough. 
	 * 
	 */
	private static final int nbVarToSendToPraat=8;
	
	/**
	 * The duration of the speach generated by praat
	 * 
	 */
	private static final double Speachtime=1.0; //0.5
	
	/**
	* constructor without parameters, just create to override the default java constructor.
	* No need to use it so we define it as private
	* 
	* @since 0.1
	*
	*/
	private MessageToPraat(){
		
	}
	
	/**
	* Return a String containing the header of a praat script to generate speech synthesis.
	* The script create the Speaker and the Artword elements 
	* It is separated from the rest of the script to avoid to recreate both elements at each iteration of the GA.
	*
	* @return String containing the script for creating praat voice synthesis environment
	*
	* @since 0.1
	*
	*/
	public static String writePraatScriptHeader(){
		StringBuilder stb= new StringBuilder(); 
		stb.append("#-----------------------------------------------\n");
		stb.append("# Project : Software synthesis using GA\n");
		stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
		stb.append("Create Artword... phon "+Speachtime+"\n");//creation of actions list(init)
		stb.append("#-----------------------------------------------\n");
		return stb.toString();
	}
	
	/**
	 * function which will send a message to praat and ask it to return the string LAUNCH in socket.
	 * It is use when we launch praat to see that praat have finish to launch before sending the other scripts.
	 * For more explanation, see the technical documentation.
	 * @return a string containing a script that ask for a socket
	 */
	public static String writePraatLaunchTest(){
		StringBuilder stb= new StringBuilder(); 
		stb.append("#-----------------------------------------------\n");
		stb.append("# Project : Software synthesis using GA\n");
		stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("sendsocket localhost:2009 LAUNCH \n");
		stb.append("#-----------------------------------------------\n");
		return stb.toString();
	}

	/**
	* Return a String containing a praat script fill in with the values of the sequence in parameters.
	* This script when useds will query the formants informations of the generated sound and send them back to the program.
	* It is designed to be use at each step of the GA to evaluate the current sequence. For more details,
	* see the architecture documentation.
	* You must have initialize the praat environment with writePraatScriptHeader() before.
	*
	* @return String containing the script for creating and querying a sound
	* @throws PraatScriptException If you gave an incorrect sequence
	*
	* @since 0.1
	*
	*/
	public static String writePraatScriptWithCandidates(Sequence candidat) throws PraatScriptException{ // catch a corrige pareil en dessous
			if(candidat.getLength()==nbVarToSendToPraat){
				StringBuilder stb= new StringBuilder(); //utilisation de stringBuilder car je suppose que pas synchroniser
				stb.append("#-----------------------------------------------\n");
				stb.append("# Project : Software synthesis using GA\n");
				stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
				stb.append("#-----------------------------------------------\n");
				//we complete artword with the set target methods below
				stb.append("select Artword phon\n");
				stb.append("# Supply lung energy\n");
				stb.append("#-----------------------------------------------\n");
				try {
					stb.append("Set target... 0.0    0.1 Lungs\n");
					stb.append("Set target... 0.03     0.0 Lungs\n");
					stb.append("Set target... "+Speachtime+"     0.0 Lungs\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("# Control glottis\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("#Glottal closure\n");
					stb.append("Set target... 0.0  0.5  Interarytenoid\n"); //this one is fixed
					stb.append("Set target... "+Speachtime+"  0.5 Interarytenoid\n");
					stb.append("#\n");
					stb.append("# Adduct vocal folds\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(0)+" Cricothyroid\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(0)+" Cricothyroid\n");
					stb.append("# Close velopharyngeal port\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(1)+" LevatorPalatini\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(1)+" LevatorPalatini\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(2)+" Genioglossus\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(2)+" Genioglossus\n");
					stb.append("#\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(3)+" Styloglossus\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(3)+" Styloglossus\n");
					stb.append("#\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(4)+" Mylohyoid\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(4)+" Mylohyoid\n");
					stb.append("#\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(5)+" OrbicularisOris\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(5)+" OrbicularisOris\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("# Shape mouth to open vowel\n");
					stb.append("#-----------------------------------------------\n");
					stb.append("# Lower the jaw\n");
					stb.append("# -----------------------------------------\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(6)+" Masseter\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(6)+" Masseter\n");
					stb.append("# Pull tongue backwards\n");
					stb.append("Set target... 0.0   "+candidat.getValuesAt(7)+" Hyoglossus\n");
					stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(7)+" Hyoglossus\n");
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
					//stb.append("writeInfoLine(numberOfFormant)\n");
					stb.append("if numberOfFormant>="+nbFormantMax+"\n"); //if good number of formant
					stb.append("time = Get total duration\n");
					stb.append("midTime = time/2\n");
					stb.append("for intervalNumber from 1 to "+nbFormantMax+" \n");
					stb.append("varTabFreq[intervalNumber] = Get mean... intervalNumber 0 0 \"Hertz\"\n");
					//stb.append("varTabBandWith[intervalNumber] =  Get bandwidth at time... intervalNumber midTime \"Hertz\" \"Linear\"\n");
					stb.append("endfor\n");
					stb.append("# convert it into string for sendsocket\n");
					//i havent find a way with this version of praat to do otherwise
					//that to write like that but  it could maybe be change in the future if the praat api change
					stb.append("temp1$=string$(varTabFreq[1])\n");
					stb.append("temp2$=string$(varTabFreq[2])\n");
					//stb.append("temp4$=string$(varTabBandWith[1])\n");
					//stb.append("temp5$=string$(varTabBandWith[2])\n");
					//stb.append("sendsocket localhost:2009 'temp1$' 'temp2$' 'temp4$' 'temp5$' \n");
					stb.append("sendsocket localhost:2009 'temp1$' 'temp2$' \n");
					//sinon on passe un message d erreur
					stb.append("else\n"); //else send error caracter
					stb.append("sendsocket localhost:2009 INF \n");
					stb.append("endif\n");
					
				} catch (SequenceArrayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.display();
				}
				return stb.toString();
			}else{
				throw new PraatScriptException(candidat.getLength(),nbVarToSendToPraat);
			}
	}
	
	
	
	
	/**
	* Write a file with the script for generate a voice synthesis with parameters from a sequence.
	* It is designed to be used at the end of the Ga and return the best candidate in a script that can be directly executed under praat.
	*
	* @throws PraatScriptException If you gave an incorrect sequence
	*
	* @since 0.1
	*
	*/
	public static void writePraatScriptInFile(Sequence candidat, String filename) throws PraatScriptException{
		if(candidat.getLength()==nbVarToSendToPraat){
			String adressedufichier = System.getProperty("user.dir") + "/results/"+filename;
			StringBuilder stb= new StringBuilder(); 
			stb.append("#-----------------------------------------------\n");
			stb.append("# Project : Software synthesis using GA\n");
			stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("#-----------------------------------------------\n");
			stb.append("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
			stb.append("Create Artword... phon "+Speachtime+"\n");//creation of actions list(init)
			stb.append("#-----------------------------------------------\n");
			stb.append("# Supply lung energy\n");
			stb.append("#-----------------------------------------------\n");
			try {
				stb.append("Set target... 0.0    0.1 Lungs\n");
				stb.append("Set target... 0.03     0.0 Lungs\n");
				stb.append("Set target... "+Speachtime+"     0.0 Lungs\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("# Control glottis\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("#Glottal closure\n");
				stb.append("Set target... 0.0  0.5  Interarytenoid\n"); //this one is fixed
				stb.append("Set target... "+Speachtime+"  0.5 Interarytenoid\n");
				stb.append("#\n");
				stb.append("# Adduct vocal folds\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(0)+" Cricothyroid\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(0)+" Cricothyroid\n");
				stb.append("# Close velopharyngeal port\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(1)+" LevatorPalatini\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(1)+" LevatorPalatini\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(2)+" Genioglossus\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(2)+" Genioglossus\n");
				stb.append("#\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(3)+" Styloglossus\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(3)+" Styloglossus\n");
				stb.append("#\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(4)+" Mylohyoid\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(4)+" Mylohyoid\n");
				stb.append("#\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(5)+" OrbicularisOris\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(5)+" OrbicularisOris\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("# Shape mouth to open vowel\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("# Lower the jaw\n");
				stb.append("# -----------------------------------------\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(6)+" Masseter\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(6)+" Masseter\n");
				stb.append("# Pull tongue backwards\n");
				stb.append("Set target... 0.0   "+candidat.getValuesAt(7)+" Hyoglossus\n");
				stb.append("Set target... "+Speachtime+"   "+candidat.getValuesAt(7)+" Hyoglossus\n");
				stb.append("# Synthesise the sound\n");
				stb.append("#-----------------------------------------------\n");
				stb.append("select Artword phon\n");
				stb.append("plus Speaker Robovox\n");
				stb.append("To Sound... 22050 25   0 0 0    0 0 0    0 0 0\n");
				stb.append("#-----------------------------------------------\n");
				//part were i wrote a command to rename the sound as the parameter given to the execute praat command (see launchAll)
				stb.append("#-----------------------------------------------\n");
				stb.append("form Param\n"); // i use a form for the param, see praat api
				stb.append("sentence value \"default\"\n");
				stb.append("endform\n");
				stb.append("Rename... 'value$'\n"); // dont forget the quote or the result wont print
				stb.append("#-----------------------------------------------\n");
				
				stb.append("# Automatic data extraction part\n");
				stb.append("To Formant (burg)... 0 5 5500 0.025 50\n");
				stb.append("Speckle... 0 0 5500 30 yes\n");
				stb.append("numberOfFormant = Get number of formants... 1\n");
				stb.append("writeInfoLine(\"number of formants :\", numberOfFormant)\n");
				stb.append("for intervalNumber from 1 to numberOfFormant \n");
				stb.append("temp1 = Get value at time... intervalNumber 0.1 Hertz Linear\n");
				stb.append("appendInfoLine(\"Valeur de temp1 \",temp1)\n");
				stb.append("temp2 = Get value at time... intervalNumber 0.25 Hertz Linear\n");
				stb.append("appendInfoLine(\"Valeur de temp2 \",temp2)\n");
				stb.append("temp3 = Get value at time... intervalNumber 0.5 Hertz Linear\n");
				stb.append("appendInfoLine(\"Valeur de temp3 \",temp3)\n");
				stb.append("temp4 = Get value at time... intervalNumber 0.9 Hertz Linear\n");
				stb.append("appendInfoLine(\"Valeur de temp4 \",temp4)\n");
				stb.append("mymean = (temp1+temp2+temp3+temp4)/4 \n");
				stb.append("appendInfoLine(\"Valeur moyenne du formant \",intervalNumber,\":\",mymean)\n");
				stb.append("endfor\n");
				
				String texte = stb.toString();
				FileWriter fw = new FileWriter(adressedufichier, false);
				BufferedWriter output = new BufferedWriter(fw);
				output.write(texte);
				output.flush();
				output.close();
				fw.close();
			} catch (SequenceArrayException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			throw new PraatScriptException(candidat.getLength(),nbVarToSendToPraat);
		}
	}

	/**
	* script use to clear the memory of praat by removing all the objects in he object list.
	* We use it because praat had a maximal capacity of object in memory and so using a GA could reach this capacity.
	* So we clean the object window from time to time at each end of generation.
	* this function recreate the two speakers. 
	*
	* @return String containing the script to execute
	*
	* @since 0.1
	* @deprecated Use {@link OrderToPraat#reLaunchPraat()} instead
	*/
	public static String clearPraatObjectWindow(){
		StringBuilder stb= new StringBuilder();
		stb.append("#-----------------------------------------------\n");
		stb.append("# Project : Software synthesis using GA\n");
		stb.append("# Hervo Pierre-Yves, automatic Script generated in java\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("select all\n");
		stb.append("Remove\n");
		stb.append("#-----------------------------------------------\n");
		stb.append("Create Speaker... Robovox Female 2\n"); //nom genre typeDeGlottis
		stb.append("Create Artword... phon "+Speachtime+"\n");//creation of actions list(init)
		stb.append("#-----------------------------------------------\n");
		return stb.toString();
	}
}
