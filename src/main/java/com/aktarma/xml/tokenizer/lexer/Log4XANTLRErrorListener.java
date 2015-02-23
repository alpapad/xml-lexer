package com.aktarma.xml.tokenizer.lexer;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class Log4XANTLRErrorListener extends BaseErrorListener {
    public static final ConsoleErrorListener INSTANCE = new ConsoleErrorListener();

    private final String fname;
    
    private final int startLine ;
    
    public Log4XANTLRErrorListener(String fname) {
		super();
		this.fname = fname;
		startLine = 0;
	}
    public Log4XANTLRErrorListener(String fname,final int startLine ) {
 		super();
 		this.fname = fname;
 		this.startLine = startLine;
 	}

	@Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		System.err.println(fname + " Offending symbol:" +  offendingSymbol  + ", Error at line " + (line + startLine)+  ":" + charPositionInLine + " " + msg);
    }
}
