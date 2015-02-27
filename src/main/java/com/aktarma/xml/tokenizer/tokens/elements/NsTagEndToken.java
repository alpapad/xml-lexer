package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.INsElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class NsTagEndToken extends AbstractElementToken implements INsElement{
	private String ns;

	private INsElement sibling;
	private INsElement parent;
	
	
    public NsTagEndToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.NS_TAG_END);
        this.open = ETAG_OPEN;
        this.tagName = tagName.substring(ETAG_OPEN.length());
        this.ns  = this.tagName.substring(0, this.tagName.indexOf(':'));
        this.tagName= this.tagName.substring(this.tagName.indexOf(':')+1);
    }
    
    @Override
	public String getNs() {
		return ns;
	}
    
    @Override
	public void setNs(String ns) {
		this.ns = ns;
	}

	@Override
	public boolean isStart() {
		return false;
	}
	
	@Override
	public void setSibling(INsElement el) {
		this.sibling = el;
	}

	public INsElement getSibling() {
		return sibling;
	}

	@Override
	public void setParent(INsElement parent) {
		this.parent = parent;
	}

	@Override
	public INsElement getParent() {
		return parent;
	}
}
