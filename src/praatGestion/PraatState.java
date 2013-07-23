package praatGestion;

public abstract class PraatState {
	public abstract void launch(Praat praatObject);
	public abstract void headerSet(Praat praatObject);
	public abstract void reLaunch(Praat praatObject);
	public abstract void close(Praat praatObject);
}
