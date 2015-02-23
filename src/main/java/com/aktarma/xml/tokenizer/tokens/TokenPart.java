package com.aktarma.xml.tokenizer.tokens;

public interface TokenPart {

    int line();
    int charPos();
    
    TokenType type();
}
