package com.aktarma.xml.tokenizer.tokens.parts;

import java.util.UUID;

import com.aktarma.xml.tokenizer.tokens.AbstractToken;
import com.aktarma.xml.tokenizer.tokens.ITagPart;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class AbstractElementPart extends AbstractToken implements ITagPart {

	protected IElement parent;
	
	public AbstractElementPart(int line, int charpos, IElement parent, TokenType type) {
		super(line, charpos, type);
		this.parent = parent;
	}

	@Override
	public String getKey() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void setParent(IElement parent) {
		this.parent = parent;
	}

	@Override
	public IElement getElement() {
		return this.parent;
	}

}
