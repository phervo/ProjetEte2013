package geneticAlogrithm;

import java.util.Random;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

public class SequenceFactory extends AbstractCandidateFactory<Sequence>{
	private double[] alphabet;
	private int length;
	
	public SequenceFactory(double[] alphabet,int length){//taille de la sequence a cree et alphabet avec nuplets possibles
		this.length=length;
		this.alphabet=alphabet;
	}
	
	@Override
	public Sequence generateRandomCandidate(Random rng) {
		// TODO Auto-generated method stub
		Sequence mySeq = new Sequence(this.length);
		for(int i=0;i<mySeq.getLength();i++){
			mySeq.setValues(i, alphabet[rng.nextInt(length)]);
		}
		return mySeq;
	}

	public double[] getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(double[] alphabet) {
		this.alphabet = alphabet;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void displayAlphabet(){
		for(int i=0;i<length;i++){
			System.out.println(this.alphabet[i]);
		}
	}
}
