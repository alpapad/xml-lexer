package com.aktarma.xml.tokenizer.tokens.txt;

import com.aktarma.xml.tokenizer.tokens.TokenType;

public class InlineCodeToken extends AbstractTextToken {

    public InlineCodeToken(int line, int charpos, String content) {
        super(line, charpos, TokenType.INLINECODE);
        this.setContent(content);
    }

	public void addSnippet(String text) {
		super.append(text);
	}
}
