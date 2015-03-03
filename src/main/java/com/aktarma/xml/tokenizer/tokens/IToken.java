package com.aktarma.xml.tokenizer.tokens;

public interface IToken {

    int line();
    
    int charPos();
    
    TokenType type();
}
