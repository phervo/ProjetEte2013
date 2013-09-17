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
			 * I assume the rest isnt good enought and go directly ot the trash.
			 */
			if(lvl1.size()==0 && lvl2.size()==0){
				 mySelection.add(smNone.getAt(adjustResult(rng.nextInt(4))));
				 mySelection.add(smNone.getAt(adjustResult(rng.nextInt(4))));
			}else if(lvl2.size()==0){
				/*
				 * then there is at least an individual in lvl1 or it would have enter above.
				 * I pick one
				 */
					smLvlTempSequence1 = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));
					tempSequence1= smLvlTempSequence1.getAt(adjustResult(rng.nextInt(smLvlTempSequence1.getNumberOfElement())));
					mySelection.add(tempSequence1);
					
				if(lvl1.size()==1){
					/*
					 * 	then there is only one type of formant.
					 * 	Two cases. If there is more than one exemplary of this formant, i picked one above, i just picked another.
					 * 	The expectation is to get a fitter formant of this type.
					 * 	If there is only one individual, i picked it above, then i picked one of the 4 best "none" individuals 
					 */
					
					if(lvl1.get(0).getNumberOfElement()==1){
						//then in addition i have only 1 formant of this type
						mySelection.add(smNone.getAt(adjustResult(rng.nextInt(4))));//i choose amoung the two bests lvl0
					}else{
						//be carreful not to pick twice the same individuals, it doesnt matter to pick twice in the same formant list but nit twice the same sequence.
						smLvlTempSequence2 = lvl1.get(0);
						tempSequence2= smLvlTempSequence2.getAt(adjustResult(rng.nextInt(smLvlTempSequence2.getNumberOfElement())));
						while(tempSequence2.equals(tempSequence1)){
							/*
							 * we cant predict the number of elements of the list here, it is form 2 to n. So we need to use that way.
							 * We dont change the  smLvlTempSequence2 var cause it is the only one we can pick in.
							 */
							tempSequence2= smLvlTempSequence2.getAt(adjustResult(rng.nextInt(smLvlTempSequence2.getNumberOfElement())));
						}
						mySelection.add(tempSequence2);
					}
				}else{
					//cross over beetween two different formants
					smLvlTempSequence2 = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));//choose the FX
					tempSequence2= smLvlTempSequence2.getAt(adjustResult(rng.nextInt(smLvlTempSequence2.getNumberOfElement())));//choose the sequence
					while(tempSequence2.equals(tempSequence1)){
						/*
						 * we cant predict the number of elements of the list here, it is form 2 to n. So we need to use that way.
						 * We change the  smLvlTempSequence2 var here or we could get an error and make an infinite loop
						 */
						smLvlTempSequence2 = lvl1.get(adjustResult(rng.nextInt(lvl1.size())));//choose the FX
						tempSequence2= smLvlTempSequence2.getAt(adjustResult(rng.nextInt(smLvlTempSequence2.getNumberOfElement())));
					}
					mySelection.add(tempSequence2);
				}
			}else{
				//lvl2!=0
				if(lvl2.size()==2 || lvl2.size()==3){
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
				}
			}
		}
		System.out.println("taille de myselection"+mySelection.size());
			
		// TODO Auto-generated method stub
		return (List<S>) mySelection;
	}
	
	/**
	 * function wich adjust the result returned by the random generator to correspond to the list index.
	 */
	public int adjustResult(int intBeforeAdjust){
		int res;
		if(intBeforeAdjust!=0){
			res=intBeforeAdjust-1;
		}else{
			res=0;
		}
		return res;
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
