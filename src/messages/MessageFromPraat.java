package messages;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import praatGestion.OrderToPraat;
import communication.ServerThread;
import elements.Formant;
import elements.FormantSequence;
import exceptions.CastFormantException;
import exceptions.FormantNumberexception;
import geneticAlogrithm.SequenceEvaluator;

/** <p>Class which deals with the message received from praat to transform them into the object we wanted<br/>
 *  All the method from this class are static so no need to create an instance<br/>
 *  </p>
 * 
 * @see MessageToPraat
 * @see ServerThread
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class MessageFromPraat {
	/**
	 * define the number of values that we are waiting from praat
	 */
	private static final int nbCharFromPraat=2;
	/**
	* constructor without parameters, just create to override the default java constructor.
	* No need to use it so we define it as private
	* 
	* @since 0.1
	*
	*/
	private MessageFromPraat(){
		
	}
	
	/**
	* Return the String we get From praat as a FormantSequence.
	* It is use to set the values for the fitness function.
	*
	* @return FormantSequence with the values of the praatScript
	*
	* @throws CastFormantException if the String isnt of the good size or if it doesnt contain only numbers
	*
	* @see FormantSequence
	* @see ServerThread
	* @see SequenceEvaluator
	* 
	* 
	* 
	* @since 0.1
	*
	*/
	public static FormantSequence splitChaineToFormantSequence(String chaine) throws CastFormantException{
		boolean IsOk=true;
		String[] tab= chaine.split(" ");
		ArrayList<Formant> list= new ArrayList<Formant>();
		FormantSequence fms=null; 
		
			if(tab.length<1 || tab.length>nbCharFromPraat || (tab.length>1 && tab.length<nbCharFromPraat)){ // if !=1 ou !=nbCharFromPraat
				IsOk=false;
				throw new CastFormantException(String.valueOf(tab.length));
			}else if(tab.length==1){// 1 or nbCharFromPraat
				IsOk=false;
			}else{ //tab.length equal nbCharFromPraat
				/* three cases :  not a double => raise exception
				 * an undefined in the sequence =>return a 0.0 seq, it lighter to treat it here than in the praat script wich is already long in time and not really design for if then else
				 * niether of the two previous one=> the good answer
				 */
				for(int i=0;i<nbCharFromPraat;i++){
					if(tab[i].compareTo("--undefined--")==0){
						IsOk=false;
					}else if(!isDouble(tab[i])){
						IsOk=false;
						throw new CastFormantException(tab[i]);
					}//else it is ok
				}
			}
			
			//now we create the result with the value of IsOk
			if(IsOk){
				//list.add(new Formant(arrondir(Double.parseDouble(tab[0]),1),arrondir(Double.parseDouble(tab[2]),1),0.0));
				//list.add(new Formant(arrondir(Double.parseDouble(tab[1]),1),arrondir(Double.parseDouble(tab[3]),1),0.0));
				list.add(new Formant(arrondir(Double.parseDouble(tab[0]),1),0.0,0.0));
				list.add(new Formant(arrondir(Double.parseDouble(tab[1]),1),0.0,0.0));
				
				try {
					fms=new FormantSequence("candidat", 2,list);
				} catch (FormantNumberexception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.display();
				}
			}else{
				try { /*its "FIN" or one of the formant values couldnt have been calculated in praat(see corresponding error in the praat script)
					so we create a empty formant and switch to the next*/
					list.add(new Formant());
					list.add(new Formant());
					fms = new FormantSequence("candidat", 2,list);
				} catch (FormantNumberexception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.display();
				}
			}
			return fms;
	}
	
	/**
	* method use to query if the string in param is or not a double
	*
	* @return corresponding bolean 
	*
	* @since 0.1
	*
	*/
	public static boolean isDouble(String s) {
	       boolean isValid = true;
	       try{ Double.parseDouble(s); }
	       catch(NumberFormatException nfe){ isValid = false; }
	       return isValid;
	 }
	
	/**
	* method use get a double with n digit after the comma
	*
	* @param value
	*  the original value
	*  
	* @param n
	* 	the number of digits after the comma
	*
	* @return corresponding double 
	*
	* @since 0.1
	*
	*/
	static public double arrondir(double value, int n) { 
		double r = (Math.round(value * Math.pow(10, n))) / (Math.pow(10, n)); 
		return r; 
	}
}
