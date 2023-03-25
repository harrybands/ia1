public class NodeFactId extends NodeFact {

	private String id;

	/*
	 * Constructor of Node Fact ID
	 * @param pos 
	 * @param id 
	 */
	public NodeFactId(int pos, String id) {
		this.pos=pos;
		this.id=id;
	}

	/**
	 * 
	 * Run the environment 
	 * @param env 
	 * @return double val
	 * 
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		return env.get(pos,id);
	}

	@Override
	public String code() { return id; }

}
