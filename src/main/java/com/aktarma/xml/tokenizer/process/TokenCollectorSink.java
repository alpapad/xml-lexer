package com.aktarma.xml.tokenizer.process;

import java.util.ArrayList;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.IToken;

public class TokenCollectorSink extends AbstractTokenVisitor {

	private List<IToken> collected;

	@Override
	public void start() {
		collected = new ArrayList<>();
	}

	@Override
	public void finish() {
	}

	@Override
	public void abort() {

	}
	
	
	public List<IToken> getCollected() {
		return collected;
	}
	
	@Override
	protected boolean dispatch(IToken token){
		collected.add(token);
		return true;
	}
}
