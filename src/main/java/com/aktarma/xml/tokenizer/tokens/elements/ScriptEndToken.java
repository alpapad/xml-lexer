package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ScriptEndToken extends AbstractElementToken {
    public ScriptEndToken(int line, int charpos, String tagName) {
        super(line, charpos, TokenType.SCRIPT_END);
        this.open = TAG_OPEN;
        this.tagName = tagName.substring(TAG_OPEN.length());
    }
}
