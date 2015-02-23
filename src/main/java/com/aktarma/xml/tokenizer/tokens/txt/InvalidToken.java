package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class InvalidToken extends AbstractTextToken {

    public InvalidToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.INVALID);
        this.setContent(content);
    }
}
