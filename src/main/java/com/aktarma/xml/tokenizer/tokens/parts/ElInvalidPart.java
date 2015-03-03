package com.aktarma.xml.tokenizer.tokens.parts;

import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ElInvalidPart extends AbstractElementPart{
	private final static String EMPTY = "".intern();
	
    private final String content;

    public ElInvalidPart(int line, int charpos, IElement parent, String content) {
        super(line, charpos, parent, TokenType.INVALID_TAG_PART);
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
