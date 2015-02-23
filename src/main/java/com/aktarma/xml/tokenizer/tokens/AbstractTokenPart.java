package com.aktarma.xml.tokenizer.tokens;

public abstract class AbstractTokenPart implements TokenPart {
    
    protected int line;
    protected int charpos;
    protected final TokenType type;
    
    public AbstractTokenPart(int line, int charpos,TokenType type) {
        this.line = line;
        this.charpos = charpos;
        this.type = type;
    }

    @Override
    public int line() {
        return line;
    }

    @Override
    public int charPos() {
        return charpos;
    }
    
    @Override
    public TokenType type() {
        return type;
    }
}
