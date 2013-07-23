package praatGestion;

public class ReLaunch extends PraatState{

	public ReLaunch(){
		System.out.println("etat relance");
	}	
	
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
		//no action so leave it empty
	}

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		System.out.println("etat relance, on relance");
		praatObject.setState(new ReLaunch());
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		System.out.println("etat relance, on ferme");
		praatObject.setState(new Close());
	}

	@Override
	public void headerSet(Praat praatObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void running(Praat praatObject) {
		// TODO Auto-generated method stub
		
	}

	

}
