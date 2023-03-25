public class NodeTerm extends Node {

	private NodeFact fact;
	private NodeMulop mulop;
	private NodeTerm term;

	/** 
	 * 
	 * Constructor of Node term
	 * 
	 * @param fact
	 * @param mulop
	 * @param term
	 */
	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}

	/**
	 * Method appending the term 
	 * @param term
	 */
	public void append(NodeTerm term) {
		if (this.term==null) {
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} else
			this.term.append(term);
	}

	/**
	 * 
	 * Run this node in the environment 
	 * @param env
	 * @return double value
	 * @throws EvalException
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		return term==null
			? fact.eval(env)
			: mulop.op(term.eval(env),fact.eval(env));
	}

	/**
	 * 
	 * Put in the code with the above term, mulop, and fact variables 
	 * 
	 */
	@Override
	public String code() {
		return (term==null ? "" : term.code()+mulop.code())+fact.code();
	}

}
