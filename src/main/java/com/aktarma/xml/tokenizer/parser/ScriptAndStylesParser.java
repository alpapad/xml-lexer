package com.aktarma.xml.tokenizer.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import com.aktarma.xml.tokenizer.lexer.FlexiMiniTokenizer;
import com.aktarma.xml.tokenizer.lexer.Log4XANTLRErrorListener;
import com.aktarma.xml.tokenizer.lexer.MiniTokenizer;
import com.aktarma.xml.tokenizer.process.TokenVisitor;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.IToken;
import com.aktarma.xml.tokenizer.tokens.elements.AbstractElement;
import com.aktarma.xml.tokenizer.tokens.elements.CssEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.CssStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.ScriptEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.ScriptStartToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;
import com.aktarma.xml.tokenizer.tokens.parts.ElInvalidPart;
import com.aktarma.xml.tokenizer.tokens.parts.ElWhiteSpacePart;
import com.aktarma.xml.tokenizer.tokens.txt.InlineCodeToken;
import com.aktarma.xml.tokenizer.tokens.txt.InvalidToken;

public class ScriptAndStylesParser {

	/**
	 * Main entry point. This processor is expected to be nested in another
	 * processor which will retrieve <b>&lt;script&gt;</b>s and <b>&lt;style&gt;</b>s.
	 * 
	 * @param fileName
	 * @param startLine
	 * @param input
	 * @param sink
	 */
	public static void processString(String fileName, int startLine, String input, TokenVisitor sink) {
		process(fileName, startLine, new ANTLRInputStream(input), sink);
	}

	/**
	 * For reading driectly a file
	 * 
	 * @param input
	 * @param sink
	 * @throws IOException
	 */
	public static void processFile(String input, TokenVisitor sink) throws IOException {
		sink.start();
		try {
			process(input, 0, new ANTLRFileStream(input), sink);
		} catch (Exception e) {
			sink.abort();
			throw e;
		}
		sink.finish();
	}

	private static void process(String sourceName, int startLine, CharStream input, TokenVisitor sink) {
		final FlexiMiniTokenizer lexer = new FlexiMiniTokenizer(input);

		final Vocabulary v = lexer.getVocabulary();
		lexer.addErrorListener(new Log4XANTLRErrorListener(sourceName, startLine));

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		IToken current = null;

		tokens.fill();

		final int numTokens = tokens.size();

		for (int i = 0; i < numTokens - 1; i++) {
			try {
				final Token token = tokens.get(i);

				switch (token.getType()) {

				case MiniTokenizer.ATTRVAL:
				case MiniTokenizer.EQUALS:
					// handled by ATTR
					break;
				case MiniTokenizer.ATTR: {
					IElement el = el(current);

					if ((i + 2) < numTokens) {
						Token val = tokens.get(i + 2);
						Token eq = tokens.get(i + 1);
						if (val.getType() == MiniTokenizer.ATTRVAL) {
							assert (eq.getType() == MiniTokenizer.EQUALS);
							el.addPart(new ElAttritbutePart(token.getLine(), token.getCharPositionInLine(), el, token.getText(), eq.getText(), val.getText()));
							break;
						}
					}
					el.addPart(new ElAttritbutePart(token.getLine(), token.getCharPositionInLine(), el, token.getText(), null, null));
					break;
				}
				// Space within the tag
				case MiniTokenizer.SPACE:
					el(current).addPart(new ElWhiteSpacePart(token.getLine(), token.getCharPositionInLine(), el(current), token.getText()));
					break;

				// <script... </script> tags
				case MiniTokenizer.SCRIPT_OPEN:
					if (!sink.visitToken(current)) {
						return;
					}
					current = new ScriptStartToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					break;
				case MiniTokenizer.SCRIPT_CLOSE:
					if (!sink.visitToken(current)) {
						return;
					}
					current = new ScriptEndToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					break;
				// <style... </style> tags
				case MiniTokenizer.STYLE_OPEN:
					if (!sink.visitToken(current)) {
						return;
					}
					current = new CssStartToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					break;
				case MiniTokenizer.STYLE_CLOSE:
					if (!sink.visitToken(current)) {
						return;
					}
					current = new CssEndToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					break;

				// Next two tokens are emmited when a close ">" is found.
				// They will trigger the visitin of the actual tag (found in
				// current).
				// These are emmited by the lexer ONLY when visiting a tag
				case MiniTokenizer.TAG_SELF_CLOSE:
					el(current).setSelfClose(true);
					// fall through
				case MiniTokenizer.TAG_CLOSE:
					el(current).setClose(token.getText());
					switch (current.type()) {
					case CSS_START:
					case SCRIPT_START:
					case SCRIPT_END:
					case CSS_END:
						if (!sink.visitToken(current)) {
							return;
						}
						current = null;
						break;
					default:
						// TODO: raise error!
						System.err.println(v.getSymbolicName(token.getType()) + " <--- Error: Tag cose:" + current);
					}
					break;

				// What lies between <xx> and </xx>.
				case MiniTokenizer.TEXT:
					if (current != null && current instanceof InlineCodeToken) {
						InlineCodeToken el = (InlineCodeToken) current;
						el.addSnippet(token.getText());
					} else {
						if (current != null && !sink.visitToken(current)) {
							return;
						}
						current = new InlineCodeToken(token.getLine(), token.getCharPositionInLine(), token.getText());
					}
					break;
				// Special case.. invalid token types... added either as an
				// invalid part
				// in the element OR as text in inline code..
				// WE DO NOT THROW ANYTHING HERE!
				case Token.INVALID_TYPE:
					if (current == null) {
						if (!sink.visit(new InvalidToken(token.getLine(), token.getCharPositionInLine(), token.getText()))) {
							return;
						}
					} else {
						if (current instanceof InlineCodeToken) {
							InlineCodeToken el = (InlineCodeToken) current;
							el.addSnippet(token.getText());
						} else if (current instanceof AbstractElement) {
							el(current).addPart(new ElInvalidPart(token.getLine(), token.getCharPositionInLine(), el(current), token.getText()));
						} else {
							throw new RuntimeException("Expecting either an inline code or a element token, not:" + current);
						}
					}
					break;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		if (current != null) {
			sink.visitToken(current);
		}
	}

	private static IElement el(IToken current) {
		if (current instanceof AbstractElement) {
			return (IElement) current;
		} else {
			throw new RuntimeException("Expected a current element token but was:" + current);
		}
	}

}
