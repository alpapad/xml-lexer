package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class TagStartToken extends AbstractElementToken {
    public TagStartToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.TAG_OPEN);
        this.open = TAG_OPEN;
        this.tagName = tagName.substring(TAG_OPEN.length());
    }
}
