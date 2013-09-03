package praatGestion;

public class Launch extends PraatState{

	public Launch(){
		//System.out.println("etat lance");
	}
	
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
		//no action so i leave it empty
	}

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat lance, on relance");
		praatObject.setState(new ReLaunch());
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat lance, on ferme");
		praatObject.setState(new Close());
	}

	@Override
	public void headerSet(Praat praatObject) {
		// TODO Auto-generated method stub
		//System.out.println("etat lance, on head");
		praatObject.setState(new HeaderSet());
	}

	@Override
	public void running(Praat praatObject) {
		// TODO Auto-generated method stub
		//nothing
	}

}
