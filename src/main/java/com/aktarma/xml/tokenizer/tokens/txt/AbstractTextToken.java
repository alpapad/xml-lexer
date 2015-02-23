package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.AbstractTokenPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class AbstractTextToken extends AbstractTokenPart {

    private StringBuffer content;

    public AbstractTextToken(int line, int charpos, TokenType type) {
        super(line, charpos, type);
    }

    public String getContent() {
    	if(content != null){
    		return content.toString();
    	}
        return null;
    }

    public void setContent(String content) {
    	if(content != null) {
    		this.content = new StringBuffer(content);
    	} else {
    		this.content = null;
    	}
    }
    
    protected void append(String content) {
    	if(content == null) {
    		return;
    	}
    	if(this.content == null) {
    		setContent(content);
    	} else {
    		this.content.append(content);
    	}
    }
}
