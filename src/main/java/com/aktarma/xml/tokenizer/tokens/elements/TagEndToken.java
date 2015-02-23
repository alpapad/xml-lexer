package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class TagEndToken extends AbstractElementToken {

    public TagEndToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.TAG_CLOSE);
        this.open = ETAG_OPEN;
        this.tagName = tagName.substring(ETAG_OPEN.length());
    }
}
