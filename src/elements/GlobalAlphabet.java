package elements;

public class GlobalAlphabet {
	private LungsAlphabet la;
	private MasseterAlphabet ma;
	private OtherAlphabet oa;
	
	public GlobalAlphabet() {
		super();
		this.la = new LungsAlphabet();
		this.ma = new MasseterAlphabet();
		this.oa = new OtherAlphabet();
	}

	public LungsAlphabet getLa() {
		return la;
	}

	public MasseterAlphabet getMa() {
		return ma;
	}

	public OtherAlphabet getOa() {
		return oa;
	}
	
	/**
	 * Display the alphabet on the screen. Use to checkout.
	 */
	public void displayAllAlphabets(){
		System.out.println("alphabet of lungs");
		for(int i=0;i<la.getLength();i++){
			System.out.println(this.la.getValueAt(i));
		}
		System.out.println("alphabet of masseter");
		for(int i=0;i<ma.getLength();i++){
			System.out.println(this.ma.getValueAt(i));
		}
		System.out.println("alphabet of others");
		for(int i=0;i<oa.getLength();i++){
			System.out.println(this.oa.getValueAt(i));
		}
	}
}
