public class NodeFactNum extends NodeFact {

	private String num;

	/**
 	* Constructor of Node Fact Number 
 	* @param num
 	*/
	public NodeFactNum(String num) {
		this.num=num;
	}

	/*
	 * Run in the environment 
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		return Double.parseDouble(num);
	}

	/*
	 * Keep the code running with the given number in fact
	 */
	@Override
	public String code() { return num; }

}
