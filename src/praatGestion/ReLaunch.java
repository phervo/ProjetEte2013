package praatGestion;

public class ReLaunch extends PraatState{

	public ReLaunch(){
		//System.out.println("etat relance");
	}	
	
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("on relance et pour ca on ferme praat et on retourne a l ete launch");
		praatObject.setState(new Launch());
	}

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		//nothing
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat relance, on ferme");
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
		//nothing
	}

	

}
