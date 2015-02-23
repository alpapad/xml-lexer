package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class CDATAToken extends AbstractTextToken {
    public CDATAToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.CDATA);
        setContent(content);
    }
}
