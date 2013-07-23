package praatGestion;

public class Running extends PraatState{

	public Running(){
		System.out.println("etat running");
	}
		
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
		//nothing
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

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		System.out.println("etat running, on relance");
		praatObject.setState(new ReLaunch());
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		System.out.println("etat running, on ferme");
		praatObject.setState(new Close());
	}

}
