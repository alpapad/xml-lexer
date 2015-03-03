package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class JSPIncludeToken extends AbstractElement {
    public JSPIncludeToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.JSP_INCLUDE);
        this.open = TAG_OPEN;
        this.tagName = tagName.substring(TAG_OPEN.length());
    }

	@Override
	public boolean isStart() {
		return true;
	}
}
