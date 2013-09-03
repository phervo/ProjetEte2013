package praatGestion;

public class HeaderSet extends PraatState{

	public HeaderSet(){
		//System.out.println("etat header");
	}
	
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
	 //nothing
	}

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat eaderSet, on relance");
		praatObject.setState(new ReLaunch());
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat headerset, on ferme");
		praatObject.setState(new Close());
	}

	@Override
	public void headerSet(Praat praatObject) {
		// TODO Auto-generated method stub
		//nothing
	}

	@Override
	public void running(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat headerSet, va a running");
		praatObject.setState(new Running());
	}

}
