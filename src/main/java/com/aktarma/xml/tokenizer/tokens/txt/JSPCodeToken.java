package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class JSPCodeToken extends AbstractTextToken {
    public JSPCodeToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.JSP_CODE_BLOCK);
        setContent(content);
    }
}
