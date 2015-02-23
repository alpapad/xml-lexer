package com.aktarma.xml.tokenizer.process;

import com.aktarma.xml.tokenizer.tokens.TokenPart;
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

public interface TokenVisitor {
    void start();
    void finish();
    void abort();
    
    
    boolean visit(TagStartToken token);
    
    boolean visit(TagEndToken token);

	boolean visit(NsTagStartToken token);
	
	boolean visit(NsTagEndToken token);
	
    boolean visit(CDATAToken token);

    boolean visit(CommentToken token);

    boolean visit(ConditionalToken token);

    boolean visit(InlineTextToken token);

    boolean visit(InvalidToken token);

    boolean visit(SEAWSToken token);
    
    

    boolean visit(XMLToken token);
    
    boolean visit(TaglibToken taglib);
    
	boolean visit(JSPCodeToken jspCodeToken);
	
	boolean visit(JSPCommentToken jspCommentToken);
	



	
	/**
	 * <style>
	 * @param token
	 * @return
	 */
	boolean visit(CssStartToken token);
	
	/**
	 * </style>
	 * @param token
	 * @return
	 */
	boolean visit(CssEndToken token);
	
	/**
	 * <script> type
	 * @param token
	 * @return
	 */
	boolean visit(ScriptStartToken token);
	
	/**
	 * </script> type
	 * @param token
	 * @return
	 */
	boolean visit(ScriptEndToken token);
	
	boolean visit(InlineCodeToken token);
	
	boolean visit(JSPIncludeToken token);
	
    
	boolean visit(ElWhiteSpacePart token);
	
	boolean visit(ElAttritbutePart token);
    
    boolean visit(ElInvalidPart token);
    
	
	/**
	 * Special case..
	 * @param current
	 * @return
	 */
	boolean visitToken(TokenPart current);
}
