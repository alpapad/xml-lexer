package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class CssStartToken extends AbstractElement {
    public CssStartToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.CSS_START);
        this.open = TAG_OPEN;
        this.tagName = tagName.substring(TAG_OPEN.length());
    }

	@Override
	public boolean isStart() {
		return true;
	}
}
