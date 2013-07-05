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
	 * define the number of values that we are waiting from praat
	 */
	private static final int nbCharFromPraat=4;
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
	* @see FormantSequence
	* @see ServerSide
	* @see SequenceEvaluator
	* 
	* @since 0.1
	*
	*/
	public static FormantSequence splitChaineToFormantSequence(String chaine){//gerer le fait que ce soit bien des doubles
		String[] tab= chaine.split(" ");
		ArrayList<Formant> list= new ArrayList<Formant>();
		FormantSequence fms=null; 
		
			if(tab.length>1 && tab.length==nbCharFromPraat && tab[0].compareTo("--undefined--")!=0 && tab[1].compareTo("--undefined--")!=0 && tab[2].compareTo("--undefined--")!=0  && tab[3].compareTo("--undefined--")!=0){
				list.add(new Formant(Double.parseDouble(tab[0]),Double.parseDouble(tab[2]),0.0));
				list.add(new Formant(Double.parseDouble(tab[1]),Double.parseDouble(tab[3]),0.0));
				try {
					fms=new FormantSequence("candidat", 2,list);
				} catch (FormantNumberexception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.display();
				}
			}else{//else its "FIN" or one of the formant values is undefined by praat so we create a empty formant and switch to the next
				try {
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
}
