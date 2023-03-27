// public class NodeStmt extends Node {

// 	private NodeAssn assn;

// 	/**
// 	 * Constructor for Node Statement
// 	 *
// 	 * @param assn
// 	 */
// 	public NodeStmt(NodeAssn assn) {
// 		this.assn = assn;
// 	}

// 	/**
// 	 * Run in the environment
// 	 *
// 	 * @param env
// 	 * @return
// 	 */
// 	@Override
// 	public double eval(Environment env) throws EvalException {
// 		return assn.eval(env);
// 	}

// 	@Override
// 	public String code() {
// 		return assn.code();
// 	}

// }

public abstract class NodeStmt extends Node {
}
