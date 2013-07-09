package elements;

public class GlobalAlphabet {
	private LungsAlphabet lungsAlphabet;
	private MasseterAlphabet masseterAlphabet;
	private OtherAlphabet otherAlphabet;
	
	public GlobalAlphabet() {
		super();
		this.lungsAlphabet = new LungsAlphabet(-0.5,1.5, 1);
		this.masseterAlphabet = new MasseterAlphabet(-0.5,0.5,1);
		this.otherAlphabet = new OtherAlphabet(0.0,1.0,1);
	}

	public LungsAlphabet getLungsAlphabet() {
		return lungsAlphabet;
	}

	public MasseterAlphabet getMasseterAlphabet() {
		return masseterAlphabet;
	}

	public OtherAlphabet getOtherAlphabe() {
		return otherAlphabet;
	}
	
	/**
	 * Display the alphabet on the screen. Use to checkout.
	 */
	public void displayAllAlphabets(){
		System.out.println("alphabet of lungs");
		for(int i=0;i<lungsAlphabet.getLength();i++){
			System.out.println(this.lungsAlphabet.getValueAt(i));
		}
		System.out.println("alphabet of masseter");
		for(int i=0;i<masseterAlphabet.getLength();i++){
			System.out.println(this.masseterAlphabet.getValueAt(i));
		}
		System.out.println("alphabet of others");
		for(int i=0;i<otherAlphabet.getLength();i++){
			System.out.println(this.otherAlphabet.getValueAt(i));
		}
	}
}
