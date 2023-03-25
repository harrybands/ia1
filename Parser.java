// This class is a recursive-descent parser,
// modeled after the programming language's grammar.
// It constructs and has-a Scanner for the program
// being parsed.

/**
 * 
 * Constructor of the Parser function 
 * 
 */
public class Parser {

	private Scanner scanner; // Create scanner to scan value 

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

	private NodeStmt parseStmt() throws SyntaxException {
		NodeAssn assn = parseAssn();
		match(";");
		NodeStmt stmt = new NodeStmt(assn);
		return stmt;
	}

	private NodeBoolExpr parseBoolExpr() throws SyntaxException {
		NodeExpr expr1 = parseExpr();
		NodeRelop relop = parseRelop();
		NodeExpr expr2 = parseExpr();
		return new NodeBoolExpr(expr1, relop, expr2);
	}	

	private NodeWr parseWr() throws SyntaxException {
		match("wr");
		NodeExpr expr = parseExpr();
		return new NodeWr(expr);
	}

	private NodeRd parseRd() throws SyntaxException {
		match("rd");
		String id = curr().lex();
		match("id");
		return new NodeRd(id);
	}

	private NodeIfElse parseIfElse() throws SyntaxException {
		match("if");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("then");
		NodeStmt ifstmt = parseStmt();
		NodeStmt elsestmt = null;
		if (curr().equals(new Token("else"))) {
			match("else");
			elsestmt = parseStmt();
		}
		return new NodeIfElse(boolexpr, ifstmt, elsestmt);
	}

	private NodeWhileDo parseWhileDo() throws SyntaxException {
		match("while");
		NodeBoolExpr boolexpr = parseBoolExpr();
		match("do");
		NodeStmt stmt = parseStmt();
		return new NodeWhileDo(boolexpr, stmt);
	}





	/**	
	* This method parse a new node called relop for boolean expression	
    * @return new node relop	
    * @throws SyntaxException	
    */	

	private NodeRelop parseRelop() throws SyntaxException {
		if (curr().equals(new Token("<"))) {
			match("<");
			return new NodeRelop(pos(),"<");
		}
		if (curr().equals(new Token("<="))) {
			match("<=");
			return new NodeRelop(pos(),"<=");
		}
		if (curr().equals(new Token(">"))) {
			match(">");
			return new NodeRelop(pos(),">");
		}
		if (curr().equals(new Token(">="))) {
			match(">=");
			return new NodeRelop(pos(),">=");
		}
		if (curr().equals(new Token("<>"))) {
			match("<>");
			return new NodeRelop(pos(),"<>");
		}
		if (curr().equals(new Token("=="))) {
			match("==");
			return new NodeRelop(pos(),"==");
		}
		return null;
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
		NodeStmt stmt = parseStmt();
		match("EOF");
		return stmt;
	}

}
