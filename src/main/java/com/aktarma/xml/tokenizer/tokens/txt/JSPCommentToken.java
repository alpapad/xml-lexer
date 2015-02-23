package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class JSPCommentToken extends AbstractTextToken {
   
    public JSPCommentToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.JSP_COMMENT);
        setContent(content);

    }
}
