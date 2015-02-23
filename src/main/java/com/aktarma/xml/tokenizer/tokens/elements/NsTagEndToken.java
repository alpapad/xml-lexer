package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.NsElementPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class NsTagEndToken extends AbstractElementToken implements NsElementPart{
	private String ns;

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
}
