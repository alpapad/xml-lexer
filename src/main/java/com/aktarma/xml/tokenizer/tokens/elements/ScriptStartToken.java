package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ScriptStartToken extends AbstractElementToken {
    public ScriptStartToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.SCRIPT_START);
        this.open = TAG_OPEN;
        this.tagName = tagName.substring(TAG_OPEN.length());
    }
}
