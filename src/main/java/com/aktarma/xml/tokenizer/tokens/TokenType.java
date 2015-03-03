package com.aktarma.xml.tokenizer.tokens;

import com.aktarma.xml.tokenizer.process.TokenVisitor;
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

public enum TokenType {
    TAG_OPEN,
    TAG_CLOSE,
    COMMENT,
    CONDITION,
    INLINETEXT,
    INLINECODE,
    INVALID,
    INVALID_TAG_PART,
    ATTRVAL,
    TAG_WS,
    SEA_WS,
    CDATA,
    TAGLIB,
    JSP_CODE_BLOCK,
    JSP_COMMENT,
    CSS_START,
    CSS_END,
    SCRIPT_START,
    SCRIPT_END,
    OTHER_XML, 
    JSP_INCLUDE, 
    NS_TAG_START, 
    NS_TAG_END;
    
    public static boolean visitType(TokenVisitor s, IToken token) {
        if (token == null) {
            return false;
        }
        switch (token.type()) {

        case TAG_OPEN:
            return s.visit((TagStartToken) token);
        case TAG_CLOSE:
            return s.visit((TagEndToken) token);

        case NS_TAG_START:
            return s.visit((NsTagStartToken) token);
        case NS_TAG_END:
            return s.visit((NsTagEndToken) token);
            
        case ATTRVAL:
            return s.visit((ElAttritbutePart) token);
        case INVALID_TAG_PART:
            return s.visit((ElInvalidPart) token);
        case TAG_WS:
            return s.visit((ElWhiteSpacePart) token);
        case SEA_WS:
            return s.visit((SEAWSToken) token);

        case COMMENT:
            return s.visit((CommentToken) token);
        case CONDITION:
            return s.visit((ConditionalToken) token);
            
        case INLINETEXT:
            return s.visit((InlineTextToken) token);
        case CDATA:
            return s.visit((CDATAToken) token);
        case OTHER_XML:
            return s.visit((XMLToken) token);
        case TAGLIB:
        	return s.visit((TaglibToken) token);
        case JSP_CODE_BLOCK:
        	return s.visit((JSPCodeToken)token);
        case JSP_COMMENT:
        	return s.visit((JSPCommentToken)token);
        case CSS_START:
        	return s.visit((CssStartToken)token);
        case CSS_END:
        	return s.visit((CssEndToken)token);
        case SCRIPT_START:
        	return s.visit((ScriptStartToken)token);
        case SCRIPT_END:
        	return s.visit((ScriptEndToken)token);
        case INLINECODE:
        	return s.visit((InlineCodeToken)token);
        case JSP_INCLUDE:
        	return s.visit((JSPIncludeToken)token);
        	
        case INVALID:
            return s.visit((InvalidToken) token);
        default:
        	throw new RuntimeException("Token with type:" + token.type() + " is not handled!!");
        }
    }
}
