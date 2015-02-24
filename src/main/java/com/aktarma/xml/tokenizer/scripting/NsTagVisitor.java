package com.aktarma.xml.tokenizer.scripting;

import com.aktarma.xml.tokenizer.process.StringSink;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;

public class NsTagVisitor {
	
	public String start(NsTagStartToken token) {
		StringSink sink = new StringSink();
		sink.visit(token);
		return sink.text();
	}

	public String end(NsTagEndToken token) {
		StringSink sink = new StringSink();
		sink.visit(token);
		return sink.text();
	}
}
