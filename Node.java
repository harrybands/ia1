// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated,
// and/or code()-generated.

public abstract class Node {

	protected int pos=0;

	/**
	 * Abstract method
	 * @param env
	 * @return double value
	 * @throws EvalException - if function eval() cannot be created 
	 */
	public double eval(Environment env) throws EvalException {
		throw new EvalException(pos,"cannot eval() node!");
	}

	/*
	 * Functional method to keep the String going and the code runs well. 
	 */
	public String code() { return ""; }

}
