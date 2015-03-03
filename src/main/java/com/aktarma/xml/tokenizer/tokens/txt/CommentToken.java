package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class CommentToken extends AbstractTextToken {
    public CommentToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.COMMENT);
        setContent(content);
    }
}
