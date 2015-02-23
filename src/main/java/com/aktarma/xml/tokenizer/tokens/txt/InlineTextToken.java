package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class InlineTextToken extends AbstractTextToken {

    public InlineTextToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.INLINETEXT);
        this.setContent(content);
    }

}
