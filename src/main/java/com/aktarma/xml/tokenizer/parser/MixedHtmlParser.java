package com.aktarma.xml.tokenizer.parser;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import com.aktarma.xml.tokenizer.lexer.FlexiXMLTokenizer;
import com.aktarma.xml.tokenizer.lexer.Log4XANTLRErrorListener;
import com.aktarma.xml.tokenizer.process.TokenVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.AbstractElement;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.TagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TaglibToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;
import com.aktarma.xml.tokenizer.tokens.parts.ElInvalidPart;
import com.aktarma.xml.tokenizer.tokens.parts.ElWhiteSpacePart;
import com.aktarma.xml.tokenizer.tokens.txt.CDATAToken;
import com.aktarma.xml.tokenizer.tokens.txt.CommentToken;
import com.aktarma.xml.tokenizer.tokens.txt.ConditionalToken;
import com.aktarma.xml.tokenizer.tokens.txt.InlineTextToken;
import com.aktarma.xml.tokenizer.tokens.txt.InvalidToken;
import com.aktarma.xml.tokenizer.tokens.txt.JSPCodeToken;
import com.aktarma.xml.tokenizer.tokens.txt.JSPCommentToken;
import com.aktarma.xml.tokenizer.tokens.txt.SEAWSToken;
import com.aktarma.xml.tokenizer.tokens.txt.XMLToken;

public class MixedHtmlParser {

	public static void processString(String input, TokenVisitor sink) {
		process("", new ANTLRInputStream(input), sink);
	}

	public static void processFile(String input, TokenVisitor sink) throws IOException {
		process(input, new ANTLRFileStream(input), sink);
	}

	public static void processFile(File input, TokenVisitor sink) throws IOException {
		process(input.getAbsolutePath(), new ANTLRFileStream(input.getAbsolutePath()), sink);
	}
	
	private static void process(final String fileName, final CharStream input, final TokenVisitor sink) {
		
		final FlexiXMLTokenizer lexer = new FlexiXMLTokenizer(input);
		final Log4XANTLRErrorListener errorListener = new Log4XANTLRErrorListener(fileName);
		final Vocabulary v = lexer.getVocabulary();
		
		lexer.addErrorListener(errorListener);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		AbstractElement current = null;

		tokens.fill();

		final int numTokens = tokens.size();
		
		sink.start();
		
		for (int i = 0; i < numTokens - 1; i++) {
			final Token token = tokens.get(i);
			try {
				switch (token.getType()) {
				case FlexiXMLTokenizer.COND_COMMENT:
					if (!sink.visit(new ConditionalToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.SCRIPT:
					ScriptAndStylesParser.processString(fileName, token.getLine(), token.getText(), sink);
					current = null;
					break;
				case FlexiXMLTokenizer.CSS:
					ScriptAndStylesParser.processString(fileName, token.getLine(), token.getText(), sink);
					current = null;
					break;
				case FlexiXMLTokenizer.COMMENT:
				case FlexiXMLTokenizer.COMMENT_EMPTY:
					if (!sink.visit(new CommentToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.CDATA:
					if (!sink.visit(new CDATAToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.JSP_COMMENT:
					if (!sink.visit(new JSPCommentToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.JSP_CODE_BLOCK:
					if (!sink.visit(new JSPCodeToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.DTD:
					if (!sink.visit(new XMLToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.PROCESSING_INSTR:
				case FlexiXMLTokenizer.ENTITYREF:
					if (!sink.visit(new XMLToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.CHARREF:
				case FlexiXMLTokenizer.TEXT:
					if (!sink.visit(new InlineTextToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.SEA_WS:
					if (!sink.visit(new SEAWSToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.JSPIMPORT:
				case FlexiXMLTokenizer.JSPINCLUDE:
				case FlexiXMLTokenizer.JSPPAGE:
				case FlexiXMLTokenizer.JSPTAGLIB:
					current = new TaglibToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					break;
				case FlexiXMLTokenizer.JSPTAGLIB_CLOSE:
					current.setClose(token.getText());
					if (!sink.visitToken(current)) {
						return;
					}
					current = null;
					break;
				case FlexiXMLTokenizer.XML_TAG:
				case FlexiXMLTokenizer.TAG_START: {
					final String tagName = token.getText();
					if (tagName.contains(":")) {
						current = new NsTagStartToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					} else {
						current = new TagStartToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					}
				}
					break;
				case FlexiXMLTokenizer.TAG_END: {
					final String tagName = token.getText();
					if (tagName.contains(":")) {
						current = new NsTagEndToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					} else {
						current = new TagEndToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					}
				}
					break;
				// Self closing tags
				case FlexiXMLTokenizer.TAG_SELF_CLOSE:
				case FlexiXMLTokenizer.XML_TAG_CLOSE:
					current.setSelfClose(true);
				case FlexiXMLTokenizer.TAG_CLOSE:
					current.setClose(token.getText());
					switch (current.type()) {
					case NS_TAG_START:
					case NS_TAG_END:
					case TAG_OPEN:
					case TAG_CLOSE:
						if (!sink.visitToken(current)) {
							return;
						}
						current = null;
						break;
					default:
						// TODO: raise error!
						System.err.println(v.getSymbolicName(token.getType()) + " <--- Error: Tag cose:" + current);
						break;
					}
				case FlexiXMLTokenizer.ATTRVAL:
				case FlexiXMLTokenizer.EQUALS:
					// consumed via ATTR
					break;
				case FlexiXMLTokenizer.ATTR:
					if ((i + 2) < numTokens) {
						Token val = tokens.get(i + 2);
						Token eq = tokens.get(i + 1);
						if (val.getType() == FlexiXMLTokenizer.ATTRVAL) {
							assert (eq.getType() == FlexiXMLTokenizer.EQUALS);
							current.addPart(new ElAttritbutePart(token.getLine(), token.getCharPositionInLine(), current, token.getText(), eq.getText(), val.getText()));
							break;
						}
					}
					current.addPart(new ElAttritbutePart(token.getLine(), token.getCharPositionInLine(), current, token.getText(), null, null));
					break;

				case FlexiXMLTokenizer.SPACE:
					current.addPart(new ElWhiteSpacePart(token.getLine(), token.getCharPositionInLine(), current, token.getText()));
					break;

				case Token.INVALID_TYPE:
					if (current == null) {
						if (!sink.visit(new InvalidToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
							return;
						}
						current = null;
					} else {
						current.addPart(new ElInvalidPart(token.getLine(), token.getCharPositionInLine(), current, token.getText()));
					}
					break;
				default:
					// TODO: Raise error!
					System.err.println("Unhandled token type:" + v.getSymbolicName(token.getType()) + ",:[" + token.getText() + "]");
				}
			} catch (Throwable t) {
				// TODO: raise error!
				System.out.println(fileName + ", line:" + token.getLine() + ", Exception: " + t);
				t.printStackTrace();
				sink.abort();
				throw t;
			}
		}
		sink.finish();
	}
}
