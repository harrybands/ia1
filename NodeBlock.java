public class NodeBlock extends Node {

	private NodeStmt stmt;
	private NodeBlock block;

	/**
	 * Constructor of this class
	 *
	 * @param stmt
	 * @param block
	 */
	public NodeBlock(NodeStmt stmt, NodeBlock block) {
		this.stmt = stmt;
		this.block = block;
	}

	/**
	 * This method appends what in side the block
	 *
	 * @param block
	 */
	public void append(NodeBlock block) {
		if (this.block == null) {
			this.block = block;
		} else
			this.block.append(block);
	}

	/**
	 * This method evaluates the block
	 *
	 * @return the evaluation of the block
	 * @param environment
	 */
	@Override
	public double eval(Environment env) throws EvalException {
		stmt.eval(env);
		if (block != null) {
			block.eval(env);
		}
		return 0;
	}

	@Override
	public String code() {

		return block == null
				? stmt.code() // smt
				: stmt.code() + block.code(); // smt ; block
	}

}
