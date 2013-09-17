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
		ScoreMap smLvlTempSequence1=null;
		Sequence tempSequence1=null;
		ScoreMap smLvlTempSequence2=null;
		Sequence tempSequence2=null;
		
		
		System.out.println("ordered initial population");
		for(int i=0;i<population.size();i++){
			Sequence s1= (Sequence) population.get(i).getCandidate();
			System.out.println(s1.getValuesInString()+"fitness"+s1.getFitnessScore());
		}
		
		for(int i=0;i<population.size();i++){
			s = (Sequence)population.get(i).getCandidate();
			if(s.getFormantFound().equals("none")){
				smNone.addSequence(s);
			}else if(s.getFormantFound().equals("F1")){
				smF1.addSequence(s);
			}else if(s.getFormantFound().equals("F2")){
				smF2.addSequence(s);
			}else if(s.getFormantFound().equals("F3")){
				smF3.addSequence(s);
			}else if(s.getFormantFound().equals("F1 F2")){
				smF1F2.addSequence(s);
			}else if(s.getFormantFound().equals("F2 F3")){
				smF2F3.addSequence(s);
			}else if(s.getFormantFound().equals("F1 F3")){
				smF1F3.addSequence(s);
			}//no more case
			
		}
		//System.out.println(smNone.toString());
		//System.out.println(smF1.toString());
		//System.out.println(smF2.toString());
		//System.out.println(smF3.toString());
		//System.out.println(smF1F2.toString());
		//System.out.println(smF2F3.toString());
		//System.out.println(smF1F3.toString());
		
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
		List<Sequence> mySelection = new ArrayList<Sequence>(selectionSize);
		for(int i=0;i<(selectionSize/2);i++){
			/*1st the worst case, all the formants are none
			 * so I make a selection beetween the 4 best result of lvl0.
			 * I assume the rest isnt good enought and go directly to the trash.
			 */
			if(lvl1.size()==0 && lvl2.size()==0){
				 mySelection.add(smNone.getAt(rng.nextInt(5))); //5 because the parameter is exclusive
				 System.out.println("ajout de sequence ds cas none"+mySelection.get(mySelection.size()-1).getValuesInString());
				 mySelection.add(smNone.getAt(rng.nextInt(5))); //5 because the parameter is exclusive
				 System.out.println("ajout de sequence ds cas none"+mySelection.get(mySelection.size()-1).getValuesInString());
			}else if(lvl2.size()==0){
				/*
				 * then there is at least an individual in lvl1 or it would have enter above.
				 * I pick one then there is different cases.
				 */
					smLvlTempSequence1 = lvl1.get(rng.nextInt(lvl1.size()));
					tempSequence1= smLvlTempSequence1.getAt(rng.nextInt(smLvlTempSequence1.getNumberOfElement()));
					mySelection.add(tempSequence1);
					System.out.println("ajout de sequence ds cas lvl1 base"+mySelection.get(mySelection.size()-1).getValuesInString());
					
				if(lvl1.size()==1){
					/*
					 * 	then there is only one type of formant a this lvl.
					 * 	Two cases. If there is more than one exemplary of this formant, i picked one above, i just picked another.
					 * 	The expectation is to get a fitter formant of this type.
					 * 	If there is only one individual, i picked it above, then i picked one of the 4 best "none" individuals.
					 * 	The expectation this time is to get more FX. 
					 */ 
					
					if(lvl1.get(0).getNumberOfElement()==1){
						//then in addition i have only 1 formant of this type
						mySelection.add(smNone.getAt(rng.nextInt(5)));
						System.out.println("ajout de sequence ds cas lvl1 1seul elem ds 1 seul FX"+mySelection.get(mySelection.size()-1).getValuesInString());
					}else{
						//be carreful not to pick twice the same individuals, it doesnt matter to pick twice in the same formant list but not twice the same sequence.
						smLvlTempSequence2 = lvl1.get(0);
						tempSequence2= smLvlTempSequence2.getAt(rng.nextInt(smLvlTempSequence2.getNumberOfElement()));
						System.out.println("selection sequence ds cas lvl1 pls elem ds 1 seul FX"+mySelection.get(mySelection.size()-1).getValuesInString());
						while(tempSequence2.equals(tempSequence1)){
							/*
							 * we cant predict the number of elements of the list here, it is form 2 to n. So we need to use that way.
							 * We dont change the  smLvlTempSequence2 var cause it is the only one we can pick in.
							 */
							tempSequence2= smLvlTempSequence2.getAt(rng.nextInt(smLvlTempSequence2.getNumberOfElement()));
						}
						mySelection.add(tempSequence2);
						System.out.println("ajout de sequence ds cas lvl1 pls elem ds 1 seul FX"+mySelection.get(mySelection.size()-1).getValuesInString());
					}
				}else{
					//cross over beetween two different formants
					smLvlTempSequence2 = lvl1.get(rng.nextInt(lvl1.size()));//choose the FX
					tempSequence2= smLvlTempSequence2.getAt(rng.nextInt(smLvlTempSequence2.getNumberOfElement()));//choose the sequence
					System.out.println("selection sequence ds cas lvl1 pls elem ds 1 seul FX"+mySelection.get(mySelection.size()-1).getValuesInString());
					while(tempSequence2.equals(tempSequence1)){
						/*
						 * we cant predict the number of elements of the list here,So we need to use that way.
						 * We change the  smLvlTempSequence2 var here or we could get an error and make an infinite loop
						 */
						smLvlTempSequence2 = lvl1.get(rng.nextInt(lvl1.size()));//choose the FX
						tempSequence2= smLvlTempSequence2.getAt(rng.nextInt(smLvlTempSequence2.getNumberOfElement()));
					}
					mySelection.add(tempSequence2);
					System.out.println("ajout de sequence ds cas lvl1 pls elem ds 1 seul FX"+mySelection.get(mySelection.size()-1).getValuesInString());
				}
			}else{
				/*
				 * There is at least an individual of lvl2. I pick one and then there is different cases.
				 */
				smLvlTempSequence1 = lvl2.get(rng.nextInt(lvl2.size()));//choose the FX
				tempSequence1= smLvlTempSequence1.getAt(rng.nextInt(smLvlTempSequence1.getNumberOfElement()));//choose the sequence
				mySelection.add(tempSequence1);
				
				/*
				 * I want to match the individual i get with a lvl1 coresponding to the missing formant.
				 * If it is not possible, any formant of this type, then i try to match it with another FXFY.
				 * If there is no other FXFY then I match it with any lvl1.
				 * By default, worst case, i match it with the lvl0 best candidate
				 */
				
				
				/*if(lvl2.size()==2 || lvl2.size()==3){
					//cross over beetween two different formants
					smLvlTemp = lvl2.get(adjustResult(rng.nextInt(lvl2.size())));//choose the FX
					temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));//choose the sequence
					mySelection.add(temp);
					//cross over beetween two different formants
					smLvlTemp = lvl2.get(adjustResult(rng.nextInt(lvl2.size())));//choose the FX
					temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));//choose the sequence
					mySelection.add(temp);
				}else if(lvl2.size()==1){
					smLvlTemp = lvl2.get(0);//because lvl1.size()==1
					temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));
					mySelection.add(temp);
					// now we took a FX, we choose the correct one to we hope find the three formants
					if(lvl1.size()!=0){
						if(smLvlTemp==smF1F2){
							if(smF3.getNumberOfElement()!=0){
								temp= smF3.getAt(adjustResult(rng.nextInt(smF3.getNumberOfElement())));
								mySelection.add(temp);
							}else{
								smLvlTemp = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));//choose the FX
								temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));
								mySelection.add(temp);
							}
							
						}else if(smLvlTemp==smF2F3){
							
							if(smF1.getNumberOfElement()!=0){
								temp= smF1.getAt(adjustResult(rng.nextInt(smF1.getNumberOfElement())));
								mySelection.add(temp);
							}else{
								smLvlTemp = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));//choose the FX
								temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));
								mySelection.add(temp);
							}
						}else if(smLvlTemp==smF1F3){
							if(smF2.getNumberOfElement()!=0){
								temp= smF2.getAt(adjustResult(rng.nextInt(smF2.getNumberOfElement())));
								mySelection.add(temp);
							}else{
								smLvlTemp = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));//choose the FX
								temp= smLvlTemp.getAt(adjustResult(rng.nextInt(smLvlTemp.getNumberOfElement())));
								mySelection.add(temp);
							}
						}
					}else{
						smLvlTemp = lvl0.get(0);
						temp=smLvlTemp.getAt(0);
						mySelection.add(temp);//add the best of 0
					}
				}*/
			}
		}
		System.out.println("taille de myselection"+mySelection.size());
			
		// TODO Auto-generated method stub
		return (List<S>) mySelection;
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
