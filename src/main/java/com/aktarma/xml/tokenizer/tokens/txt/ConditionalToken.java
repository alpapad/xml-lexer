package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ConditionalToken extends AbstractTextToken {
    public ConditionalToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.CONDITION);
        setContent(content);
    }
}
