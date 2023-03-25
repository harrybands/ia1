// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

public class Token {

	private String token;
	private String lexeme;

	/**
	 * 
	 * Constructor of Token class, creating new token and lexeme
	 * 
	 * @param token
	 * @param lexeme
	 */
	public Token(String token, String lexeme) {
		this.token=token;
		this.lexeme=lexeme;
	}

	/**
	 * 
	 * Second Constructor 
	 * 
	 * @param token
	 */
	public Token(String token) {
		this(token,token);
	}

	/**
	 * Return the token part of the token 
	 * @return token 
	 */
	public String tok() { return token; }

	/**
	 * 
	 * Return the lexeme part of the token 
	 * @return lexeme 
	 */
	public String lex() { return lexeme; }

	/**
	 * 
	 * Method comparing tokens   
	 * 
	 * @param t
	 * @return
	 */
	public boolean equals(Token t) {
		return token.equals(t.token);
	}

	/**
	 * 
	 * ToString method 
	 * @return strings of the token with token and lexeme information
	 */
	public String toString() {
		return "<"+tok()+","+lex()+">";
	}

}
