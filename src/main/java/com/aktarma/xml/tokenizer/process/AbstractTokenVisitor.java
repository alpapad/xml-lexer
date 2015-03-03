package com.aktarma.xml.tokenizer.process;

import com.aktarma.xml.tokenizer.tokens.IToken;
import com.aktarma.xml.tokenizer.tokens.TokenType;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.CssEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.CssStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.JSPIncludeToken;
import com.aktarma.xml.tokenizer.tokens.elements.TagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.ScriptEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.ScriptStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TaglibToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;
import com.aktarma.xml.tokenizer.tokens.parts.ElInvalidPart;
import com.aktarma.xml.tokenizer.tokens.parts.ElWhiteSpacePart;
import com.aktarma.xml.tokenizer.tokens.txt.CDATAToken;
import com.aktarma.xml.tokenizer.tokens.txt.CommentToken;
import com.aktarma.xml.tokenizer.tokens.txt.ConditionalToken;
import com.aktarma.xml.tokenizer.tokens.txt.InlineCodeToken;
import com.aktarma.xml.tokenizer.tokens.txt.InlineTextToken;
import com.aktarma.xml.tokenizer.tokens.txt.InvalidToken;
import com.aktarma.xml.tokenizer.tokens.txt.JSPCodeToken;
import com.aktarma.xml.tokenizer.tokens.txt.JSPCommentToken;
import com.aktarma.xml.tokenizer.tokens.txt.SEAWSToken;
import com.aktarma.xml.tokenizer.tokens.txt.XMLToken;

public abstract class AbstractTokenVisitor implements TokenVisitor {
	
	
	@Override
	public void start() {
	}

	@Override
	public void finish() {
	}

	@Override
	public void abort() {
	}
	

	@Override
	public boolean visit(JSPCodeToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(JSPCommentToken token) {
		return dispatch(token);
	}
	
	@Override
	public boolean visit(JSPIncludeToken token) {
		return dispatch(token);
	}
	
	@Override
	public boolean visit(CDATAToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(TagEndToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(CommentToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(ConditionalToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(InlineTextToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(InvalidToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(TagStartToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(SEAWSToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(XMLToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(TaglibToken token) {
		return dispatch(token);
	}


	@Override
	public boolean visit(CssStartToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(ScriptStartToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(InlineCodeToken token) {
		return dispatch(token);
	}
	
	@Override
	public boolean visit(ElWhiteSpacePart token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(ElAttritbutePart token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(ElInvalidPart token) {
		return dispatch(token);
	}
	@Override
	public boolean visit(CssEndToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(ScriptEndToken token) {
		return dispatch(token);
	}


	@Override
	public boolean visit(NsTagStartToken token) {
		return dispatch(token);
	}

	@Override
	public boolean visit(NsTagEndToken token) {
		return dispatch(token);
	}
	
	protected boolean dispatch(IToken token){
		return true;
	}
	
	@Override
	public boolean visitToken(IToken token) {
		if(token == null) {
			return true;
		}
    	return TokenType.visitType(this, token);
    }
	
}
