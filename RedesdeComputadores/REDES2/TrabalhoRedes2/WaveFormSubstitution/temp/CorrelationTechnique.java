package temp;

public abstract class CorrelationTechnique {

	/*** N identifies the intitial position in the window***/
	private int n;
	/*** M is the number of samples in the template***/
	private int m;
	/*** define the space of search***/
	private byte[] window;
	
	private int windowLenght;
	
	private int starWindowPosition;
	
	
	public CorrelationTechnique(int n, int m, byte[] window) {
		super();
		this.n = n;
		this.m = m;
		this.window = window;
	}

	public abstract float c(int n);
	
	
}
