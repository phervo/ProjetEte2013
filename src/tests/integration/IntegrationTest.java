package tests.integration;

import praatGestion.OrderToPraat;
import praatGestion.Praat;
import messages.MessageFromPraat;
import elements.FormantSequence;
import tests.UnitaryTestsGA;

/** <p>Class where i put my integration test to be sure that if i made modifications on the source code of a class, it will continue
 * to work properly. I will make small functions for each.</p>
 * 
 * @see UnitaryTestsGA
 *  
 * @author Pierre-Yves Hervo
 * @version 0.1
 */
public class IntegrationTest {
	
	public static void testPraat(){
		Praat p = new Praat();
		p.addObserver(new OrderToPraat());
		p.blabla();
	}
	
	
	private static class ClasseInterne{ // internal class for a main
		 public static void main(String[] args){
			 testPraat();
		 }
	}
}
