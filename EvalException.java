public class EvalException extends Exception {

	private int pos; // position that got to the exception errors
	private String msg; // messages to notify the users/coders

	/*
	 * Constructor to throwing specific exception
	 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}

	/*
	 * 
	 * toString method to concatenate 
	 * 
	 */
	public String toString() {
		return "eval error"
			+", pos="+pos
			+", "+msg;
	}

}
