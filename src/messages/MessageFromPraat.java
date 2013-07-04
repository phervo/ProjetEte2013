package messages;

import java.util.ArrayList;

import communication.ServerSide;
import elements.Formant;
import elements.FormantSequence;
import exceptions.FormantNumberexception;
import geneticAlogrithm.SequenceEvaluator;

/** <p>Class which deals with the message received from praat to transform them into the object we wanted<br/>
 *  All the method from this class are static so no need to create an instance<br/>
 *  </p>
 * 
 * @see MessageToPraat
 * @see ServerSide
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class MessageFromPraat {
	
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
	* @return String containing the script for creating praat voice synthesis environment.
	*  If the string is too short or undefined, return a empty FormantSequence (0.0 values)
	*
	* @see FormantSequence
	* @see ServerSide
	* @see SequenceEvaluator
	* 
	* @since 0.1
	*
	*/
	public static FormantSequence splitChaineToFormantSequence(String chaine) throws FormantNumberexception{ // a corrige
		String[] tab= chaine.split(" ");
		FormantSequence fms= new FormantSequence("candidat", 3, new ArrayList<Formant>());
		Formant f1= new Formant();
		Formant f2= new Formant();
		if(tab.length>1 && tab[0].compareTo("--undefined--")==0 && tab[2].compareTo("--undefined--")==0  && tab[3].compareTo("--undefined--")==0){
			f1.setFrequency(Double.parseDouble(tab[0]));
			f2.setFrequency(Double.parseDouble(tab[1]));
			f1.setBandwith(Double.parseDouble(tab[2]));
			f2.setBandwith(Double.parseDouble(tab[3]));
			try {
				fms.setFormantAt(0, f1);
				fms.setFormantAt(1, f2);
			} catch (FormantNumberexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{//else its "FIN" and it will end but we had to create a var to return else it can freeze sometimes
			f1.setFrequency(0.0);
			f2.setFrequency(0.0);
			f1.setBandwith(0.0);
			f2.setBandwith(0.0);
			try {
			fms.setFormantAt(0, f1);
			fms.setFormantAt(1, f2);
			} catch (FormantNumberexception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fms;
	}
}
