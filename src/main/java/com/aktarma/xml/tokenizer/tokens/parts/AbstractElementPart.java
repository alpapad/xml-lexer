package com.aktarma.xml.tokenizer.tokens.parts;

import java.util.UUID;

import com.aktarma.xml.tokenizer.tokens.AbstractTokenPart;
import com.aktarma.xml.tokenizer.tokens.ElementPart;
import com.aktarma.xml.tokenizer.tokens.ElementTagPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class AbstractElementPart extends AbstractTokenPart implements ElementTagPart {

	protected ElementPart parent;
	
	public AbstractElementPart(int line, int charpos, ElementPart parent, TokenType type) {
		super(line, charpos, type);
		this.parent = parent;
	}

	@Override
	public String getKey() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void setParent(ElementPart parent) {
		this.parent = parent;
	}

	@Override
	public ElementPart getParent() {
		return this.parent;
	}

}
