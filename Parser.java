// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

public class Parser {

	private Scanner scanner;

	/**
	 *
	 * check if syntax are matched
	 *
	 * @param s
	 * @throws SyntaxException
	 */
	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

	/**
	 *
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private Token curr() throws SyntaxException {
		return scanner.curr();
	}

	/**
	 *
	 * Method return the most recent postion in the scanner
	 *
	 * @return the most recent position being scanned
	 */
	private int pos() {
		return scanner.pos();
	}

	/**
	 *
	 * Method to parse in the Multiplication options
	 *
	 * @return Mul node
	 * @throws SyntaxException
	 */
	private NodeMulop parseMulop() throws SyntaxException {
		if (curr().equals(new Token("*"))) {
			match("*");
			return new NodeMulop(pos(), "*");
		}
		if (curr().equals(new Token("/"))) {
			match("/");
			return new NodeMulop(pos(), "/");
		}
		return null;
	}

	/**
	 *
	 * Method to parse in the Adding options
	 *
	 * @return Add node
	 * @throws SyntaxException
	 */
	private NodeAddop parseAddop() throws SyntaxException {
		if (curr().equals(new Token("+"))) {
			match("+");
			return new NodeAddop(pos(), "+");
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			return new NodeAddop(pos(), "-");
		}
		return null;
	}

	/**
	 *
	 * Method to parse in the fact node
	 *
	 * @return a Fact node
	 * @throws SyntaxException
	 */
	private NodeFact parseFact() throws SyntaxException {
		if (curr().equals(new Token("("))) {
			match("(");
			NodeExpr expr = parseExpr();
			match(")");
			return new NodeFactExpr(expr);
		}
		if (curr().equals(new Token("id"))) {
			Token id = curr();
			match("id");
			return new NodeFactId(pos(), id.lex());
		}
		if (curr().equals(new Token("-"))) {
			match("-");
			NodeFact fact = parseFact();
			return new NodeFactSign(fact);
		}
		Token num = curr();
		match("num");
		return new NodeFactNum(num.lex());
	}

	private NodeTerm parseTerm() throws SyntaxException {
		NodeFact fact = parseFact();
		NodeMulop mulop = parseMulop();
		if (mulop == null)
			return new NodeTerm(fact, null, null);
		NodeTerm term = parseTerm();
		term.append(new NodeTerm(fact, mulop, null));
		return term;
	}

	private NodeExpr parseExpr() throws SyntaxException {
		NodeTerm term = parseTerm();
		NodeAddop addop = parseAddop();
		if (addop == null)
			return new NodeExpr(term, null, null);
		NodeExpr expr = parseExpr();
		expr.append(new NodeExpr(term, addop, null));
		return expr;
	}

	private NodeAssn parseAssn() throws SyntaxException {
		Token id = curr();
		match("id");
		match("=");
		NodeExpr expr = parseExpr();
		NodeAssn assn = new NodeAssn(id.lex(), expr);
		return assn;
	}

	/**
	 * Parser for a boolean expression: expr relop expr (left-epxr relop right-expr)
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolExpr(expr1, relop, expr2);
	}

	/**
	 * Parse a Write statement
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private NodeWr parseWr() throws SyntaxException {
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeWr(expr);
	}

	/**
	 * Parse a Read statement
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private NodeRd parseRd() throws SyntaxException {
		match("rd");
		Token id = curr();
		match("id");
		return new NodeRd(id.lex());
	}

	/**
	 * Parse an If statement
	 *
	 * @return NodeBoolExpr
	 * @throws SyntaxException
	 */
	private NodeBoolExpr parseIf() throws SyntaxException {
		match("if");
		return parseBoolExpr();
	}

	/**
	 * Parse a Then statement
	 *
	 * @return NodeStmt
	 * @throws SyntaxException
	 */
	private NodeStmt parseThen() throws SyntaxException {
		match("then");
		return parseStmt();
	}

	/**
	 * Parse an Else statement
	 *
	 * @return NodeStmt
	 * @throws SyntaxException
	 */
	private NodeStmt parseElse() throws SyntaxException {
		match("else");
		return parseStmt();
	}

	/**
	 * Parse a While statement
	 *
	 * @return NodeBoolExpr
	 * @throws SyntaxException
	 */
	private NodeBoolExpr parseWhile() throws SyntaxException {
		match("while");
		return parseBoolExpr();
	}

	/**
	 * Parse a Do statement
	 *
	 * @return NodeStmt
	 * @throws SyntaxException
	 */
	private NodeStmt parseDo() throws SyntaxException {
		match("do");
		return parseStmt();
	}

	/**
	 * Parse a statement
	 * (Inspired by `fact` -> we create project inherits from NodeStmt)
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private NodeStmt parseStmt() throws SyntaxException {

		if (curr().equals(new Token("id"))) {
			// deal with assn
			NodeAssn assn = parseAssn();
			return new NodeStmtAssn(assn);
		}

		if (curr().equals(new Token("rd"))) {
			// deal with rd
			NodeRd rd = parseRd();
			return new NodeStmtRd(rd);
		}

		if (curr().equals(new Token("wr"))) {
			NodeWr wr = parseWr();
			return new NodeStmtWr(wr);
		}

		if (curr().equals(new Token("if"))) {
			NodeBoolExpr BoolExp = parseIf();
			NodeStmt IfThen = parseThen();

			if (curr().lex().equals("else")) {
				NodeStmt Else = parseElse();
				return new NodeIfThenElse(BoolExp, IfThen, Else);
			}

			return new NodeIfThen(BoolExp, IfThen);

		}

		if (curr().equals(new Token("while"))) {
			NodeBoolExpr BoolExp = parseWhile();
			NodeStmt While = parseDo();
			return new NodeWhile(BoolExp, While);
		}

		// 'begin' block 'end'
		match("begin");
		NodeBlock block = parseBlock();
		match("end");

		return new NodeStmtBeginEnd(block);
	}

	/**
	 * This method parse a new node called relop for boolean expression
	 *
	 * @return new node relop
	 * @throws SyntaxException
	 */
	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(), "<");
		}
		if (curr().equals(new Token("<="))) {
			match("<=");
			return new NodeRelop(pos(), "<=");
		}
		if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(), ">");
		}
		if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(), ">=");
		}
		if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(), "<>");
		}
		if (curr().equals(new Token("=="))) {
			match("==");
			return new NodeRelop(pos(), "==");
		}
		return null;
	}

	/**
	 * Parse block
	 *
	 * @return
	 * @throws SyntaxException
	 */
	private NodeBlock parseBlock() throws SyntaxException {
		NodeStmt stmt = parseStmt();

		// stmt ; block
		if (curr().equals(new Token(";"))) {
			match(";");
			NodeBlock block = parseBlock();
			return new NodeBlock(stmt, block);
		}

		// stmt
		return new NodeBlock(stmt, null);
	}

	/**
	 *
	 * Method to parse the program
	 *
	 * @param program
	 * @return new Program node
	 * @throws SyntaxException
	 */
	public Node parse(String program) throws SyntaxException {
		scanner = new Scanner(program);
		scanner.next();

		NodeBlock block = parseBlock();
		match("EOF");
		return block;
	}

}
