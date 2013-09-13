package geneticAlogrithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import elements.Sequence;

/**
 * In this class I define the principle of selection of the individuals who will evolved via the evoltuoin operators.
 * I make a special Selection operator to increase the speed of the number of formants found.
 * My goal is to select sequence with different formantFound to make them cross and maybe get a better result.
 * 
 * @author phervo
 *
 */
public class MySelectionOperator implements SelectionStrategy<Object>{

	/**
	 * constructor
	 */
	public MySelectionOperator(){
		
	}
	
	@Override
	public <S> List<S> select(List<EvaluatedCandidate<S>> population,
            boolean naturalFitnessScores,
            int selectionSize,
            Random rng){
		
		/*1st : i get the result sort by their fitness score in the var population, from the smaller(the best) to the bigger.
		 * the first step is to know how many of each formants I got
		 */
		Sequence s;
		ScoreMap smNone= new ScoreMap("none");
		ScoreMap smF1= new ScoreMap("F1");
		ScoreMap smF2= new ScoreMap("F2");
		ScoreMap smF3= new ScoreMap("F3");
		ScoreMap smF1F2= new ScoreMap("F1F2");
		ScoreMap smF2F3= new ScoreMap("F2F3");
		ScoreMap smF1F3= new ScoreMap("F1F3");
		
		for(int i=0;i<population.size();i++){
			s = (Sequence)population.get(i).getCandidate();
			if(s.getFormantFound().equals("none")){
				smNone.setNumberOfElement(smNone.getNumberOfElement()+1);
				smNone.getListe().add(s);
			}else if(s.getFormantFound().equals("F1")){
				smF1.setNumberOfElement(smF1.getNumberOfElement()+1);
				smF1.getListe().add(s);
			}else if(s.getFormantFound().equals("F2")){
				smF2.setNumberOfElement(smF2.getNumberOfElement()+1);
				smF2.getListe().add(s);
			}else if(s.getFormantFound().equals("F3")){
				smF3.setNumberOfElement(smF3.getNumberOfElement()+1);
				smF3.getListe().add(s);
			}else if(s.getFormantFound().equals("F1 F2")){
				smF1F2.setNumberOfElement(smF1F2.getNumberOfElement()+1);
				smF1F2.getListe().add(s);
			}else if(s.getFormantFound().equals("F2 F3")){
				smF2F3.setNumberOfElement(smF2F3.getNumberOfElement()+1);
				smF2F3.getListe().add(s);
			}else if(s.getFormantFound().equals("F1 F3")){
				smF1F3.setNumberOfElement(smF1F3.getNumberOfElement()+1);
				smF1F3.getListe().add(s);
			}//no more case
			
		}
		System.out.println(smNone.toString());
		System.out.println(smF1.toString());
		System.out.println(smF2.toString());
		System.out.println(smF3.toString());
		System.out.println(smF1F2.toString());
		System.out.println(smF2F3.toString());
		System.out.println(smF1F3.toString());
		
		/*
		 * construction of the list by levels
		 * important note: the sequences are still ordered in each list
		 */
		ArrayList<ScoreMap> lvl2 = new ArrayList<ScoreMap>();
		if(smF1F2.getNumberOfElement()!=0){
			lvl2.add(smF1F2);
		}
		if(smF2F3.getNumberOfElement()!=0){
			lvl2.add(smF2F3);
		}
		if(smF1F3.getNumberOfElement()!=0){
			lvl2.add(smF1F3);
		}
		ArrayList<ScoreMap> lvl1 = new ArrayList<ScoreMap>();
		if(smF1.getNumberOfElement()!=0){
			lvl1.add(smF1);
		}
		if(smF2.getNumberOfElement()!=0){
			lvl1.add(smF2);
		}
		if(smF3.getNumberOfElement()!=0){
			lvl1.add(smF3);
		}
		ArrayList<ScoreMap> lvl0 = new ArrayList<ScoreMap>();
		if(smNone.getNumberOfElement()!=0){
			lvl0.add(smNone);
		}
		
		System.out.println("listes second niveau");
		System.out.println("liste 0");
		for(int i=0;i<lvl0.size();i++){
			System.out.println(lvl0.get(i).toString());
		}
		System.out.println("liste 1");
		for(int i=0;i<lvl1.size();i++){
			System.out.println(lvl1.get(i).toString());
		}
		System.out.println("liste 2");
		for(int i=0;i<lvl2.size();i++){
			System.out.println(lvl2.get(i).toString());
		}
		/*
		 * the second step is to build the return the good individuals.
		 * For this, i return selectionSize individuals.
		 * I retrun individuals that have better chance to match together with a cross over knowing their formants.
		 */
		List<Sequence> Myselection = new ArrayList<Sequence>(selectionSize);
		for(int i=0;i<selectionSize;i++){
			//1st the worst case, all the formants are none
			if(lvl1.size()==0 && lvl2.size()==0){
				
			}
		}
			
			
			//we start with the biggers ones
			/*if(smF1F2.getNumberOfElement()!=0){ 
				if(smF3.getNumberOfElement()!=0){
					Myselection.add(smF1F2.getAt(rng.nextInt(smF1F2.getNumberOfElement()-1)));
					Myselection.add(smF3.getAt(rng.nextInt(smF3.getNumberOfElement()-1)));
				}
				
			}else if(smF2F3.getNumberOfElement()!=0){
				if(smF1.getNumberOfElement()!=0){
					Myselection.add(smF2F3.getAt(rng.nextInt(smF2F3.getNumberOfElement()-1)));
					Myselection.add(smF1.getAt(rng.nextInt(smF1.getNumberOfElement()-1)));
				}
				
			}else if(smF1F3.getNumberOfElement()!=0){
				if(smF2.getNumberOfElement()!=0){
					Myselection.add(smF1F3.getAt(rng.nextInt(smF1F3.getNumberOfElement()-1)));
					Myselection.add(smF2.getAt(rng.nextInt(smF2.getNumberOfElement()-1)));
				}
			
			//then if there is no bigger one, we use the little one
			}else if(smF1.getNumberOfElement()!=0){
				if(smF2.getNumberOfElement()!=0){
					Myselection.add(smF1.getAt(rng.nextInt(smF1.getNumberOfElement()-1)));
					Myselection.add(smF2.getAt(rng.nextInt(smF2.getNumberOfElement()-1)));
				}else if(smF3.getNumberOfElement()!=0){
					Myselection.add(smF1.getAt(rng.nextInt(smF1.getNumberOfElement()-1)));
					Myselection.add(smF3.getAt(rng.nextInt(smF3.getNumberOfElement()-1)));
				}//no need to verify the SMFXFX , it is reversible so they would have already been used
				
			}else if(smF2.getNumberOfElement()!=0){
				//no f1 or i wouldnt have come that down in the if
				if(smF3.getNumberOfElement()!=0){
					Myselection.add(smF2.getAt(rng.nextInt(smF2.getNumberOfElement()-1)));
					Myselection.add(smF3.getAt(rng.nextInt(smF3.getNumberOfElement()-1)));
				}//no need to verify the SMFXFX , it is reversible so they would have already been used
				
				//no f1 nor f2 or i wouldnt have come that down in the if
			}else{ //there is only "none" cases so i used the roulette wheel algorithme
			}
		}
		
		for(int i=0;i<Myselection.size();i++){
			System.out.println("sequence"+Myselection.get(i).getValuesInString());
		}*/
		// TODO Auto-generated method stub
		return null;
	}
	
	private void appendToList(List<Sequence> mySelection,Random rng,ScoreMap biggerOne,ScoreMap smallerOne){
		mySelection.add(biggerOne.getAt(rng.nextInt(biggerOne.getNumberOfElement()-1)));
		mySelection.add(smallerOne.getAt(rng.nextInt(smallerOne.getNumberOfElement()-1)));
	}
	
	private double getAdjustedFitness(double rawFitness,boolean naturalFitness)	{
		if (naturalFitness){
			return rawFitness;
		}else{
		// If standardised fitness is zero we have found the best possible
		// solution.  The evolutionary algorithm should not be continuing
		// after finding it.
			return rawFitness == 0 ? Double.POSITIVE_INFINITY : 1 / rawFitness;
		}
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "My personnal selection";
    }
}
