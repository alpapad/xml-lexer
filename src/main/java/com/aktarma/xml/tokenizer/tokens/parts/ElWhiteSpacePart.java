package com.aktarma.xml.tokenizer.tokens.parts;

import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ElWhiteSpacePart extends AbstractElementPart{
	private final static String EMPTY = "".intern();
	
    private final String content;

    public ElWhiteSpacePart(int line, int charpos, IElement parent, String content) {
        super(line, charpos,  parent, TokenType.TAG_WS);
        if (content != null) {
            this.content = content;
        } else {
            this.content = EMPTY;
        }
    }

    public String getContent() {
        return content;
    }
}
