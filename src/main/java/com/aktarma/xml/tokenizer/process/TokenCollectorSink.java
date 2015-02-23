package com.aktarma.xml.tokenizer.process;

import java.util.ArrayList;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.TokenPart;

public class TokenCollectorSink extends AbstractTokenVisitor {

	private List<TokenPart> collected;

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
	
	
	public List<TokenPart> getCollected() {
		return collected;
	}
	
	@Override
	protected boolean dispatch(TokenPart token){
		collected.add(token);
		return true;
	}
}
