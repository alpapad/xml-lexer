package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class XMLToken extends AbstractTextToken {
    public XMLToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.OTHER_XML);
        setContent(content);
    }
}
