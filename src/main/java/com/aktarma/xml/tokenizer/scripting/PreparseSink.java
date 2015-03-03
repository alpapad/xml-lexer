package com.aktarma.xml.tokenizer.scripting;

import java.util.ArrayList;
import java.util.List;

import com.aktarma.xml.tokenizer.process.AbstractTokenVisitor;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.INsElement;
import com.aktarma.xml.tokenizer.tokens.IToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.RootTag;

public class PreparseSink extends AbstractTokenVisitor {

	private final List<IToken> collected = new ArrayList<>();

	private final List<IElement> elementStack = new ArrayList<>();
	private final RootTag root = new RootTag();
	public PreparseSink() {
		elementStack.add(root);
	}

	@Override
	public boolean visit(NsTagStartToken token) {
		updateRelationships(token);
		return super.visit(token);
	}

	@Override
	public boolean visit(NsTagEndToken token) {
		updateRelationships(token);
		return super.visit(token);
	}
	
	private void push(IElement element) {
		elementStack.add(0, element);
	}

	private IElement peek() {
		if (elementStack.size() != 0) {
			return elementStack.get(0);
		} else {
			return null;
		}
	}

	private IElement pop() {
		if (elementStack.size() != 0) {
			return elementStack.remove(0);
		} else {
			return null;
		}
	}

	public List<IElement> getElementStack() {
		return elementStack;
	}
	
	@Override
	public void start() {
		collected.clear();
	}

	@Override
	public void finish() {
		assert elementStack.size() ==1;
		assert elementStack.get(0) == root;
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
	

	private void updateRelationships(INsElement token) {
		if(token.isStart()) {
			IElement tkn = peek();
			if(tkn != null) {
				token.setParent(tkn);
			}
			if(!token.isSelfClose()) {
				push(token);
			}
		} else {
			IElement tkn = peek();
			assert(tkn != null);
			assert(tkn.isStart());
			assert tkn.getTagName().equals(token.getTagName()) : tkn.getTagName() + ":" + token.getTagName();
			tkn = pop();
			token.setSibling(tkn);
			tkn.setSibling(token);
			token.setParent(tkn.getParent());
		}
	}
}
