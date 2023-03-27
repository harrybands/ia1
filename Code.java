import java.io.*;

public class Code {

	/*
	 * 
	 * Syntax from C language at the very first
	 * 
	 */
	private final String[] prologue = {
			"#include <stdio.h>",
			"int main() {",
	};

	/*
	 * 
	 * Sytax from C langauge at the very end
	 * 
	 */
	private final String[] epilogue = {
			"return 0;",
			"}",
	};

	/*
	 * Process of reading java code and convert it into C code
	 */
	public Code(String code, Environment env) {
		String fn = System.getenv("Code");
		if (fn == null) // check if there is any environment
			return;
		try {
			BufferedWriter f = new BufferedWriter(new FileWriter(fn + ".c")); // read the file name and add .c
			for (String s : prologue) // add string for prologue
				f.write(s + "\n");
			f.write(env.toC()); // write code from conversion part in environment
			f.write(code);
			for (String s : epilogue) // add string for epilogue
				f.write(s + "\n");
			f.close(); // close the buffer
		} catch (Exception e) { // throw exception if encountered
			System.err.println(e);
		}
	}

}