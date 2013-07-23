package praatGestion;

public class Close extends PraatState{

	public Close(){
		System.out.println("etat ferme");
	}
	
	@Override
	public void launch(Praat praatObject) {
		// TODO Auto-generated method stub
		praatObject.setState(new Launch());
	}

	@Override
	public void reLaunch(Praat praatObject) {
		// TODO Auto-generated method stub
		// non action so i leave it empty
	}

	@Override
	public void close(Praat praatObject) {
		// TODO Auto-generated method stub
		//no action so i leave it empty
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
