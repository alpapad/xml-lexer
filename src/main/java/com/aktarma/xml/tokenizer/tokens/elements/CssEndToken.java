package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class CssEndToken extends AbstractElementToken {

    public CssEndToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.CSS_END);
        this.open = ETAG_OPEN;
        this.tagName = tagName.substring(ETAG_OPEN.length());
    }
}
