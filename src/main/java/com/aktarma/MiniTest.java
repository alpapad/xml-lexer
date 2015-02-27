package com.aktarma;

import java.io.IOException;

import com.aktarma.xml.tokenizer.parser.ScriptAndStylesParser;
import com.aktarma.xml.tokenizer.process.StringSink;
//import com.aktarma.xml.tokenizer.lexer.FlexiXMLTokenizer;

public class MiniTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringSink s = new StringSink();
		StringSink.debug = true;
		ScriptAndStylesParser.processFile("aa.html", s);
		System.err.println(s.text());
	}
}
