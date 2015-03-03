package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;


public class SEAWSToken extends AbstractTextToken {
    public SEAWSToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.SEA_WS);
        this.setContent(content);
    }
}
