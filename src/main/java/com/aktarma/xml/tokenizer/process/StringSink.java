package com.aktarma.xml.tokenizer.process;

import com.aktarma.xml.tokenizer.tokens.NsElementPart;
import com.aktarma.xml.tokenizer.tokens.TokenPart;
import com.aktarma.xml.tokenizer.tokens.elements.AbstractElementToken;
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

public class StringSink extends AbstractTokenVisitor {
	private StringBuilder sb = new StringBuilder();

	public static boolean debug = false;

	public String text() {
		return sb.toString();
	}

	@Override
	public boolean visit(CommentToken token) {
		if (debug) {
			sb.append("<!-- CommentToken -->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(ConditionalToken token) {
		if (debug) {
			sb.append("<!-- ConditionalToken -->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(InlineTextToken token) {
		if (debug) {
			sb.append("<!-- InlineTextToken -->");
		}
		sb.append(escapeTextValue(token.getContent()));
		return true;

	}

	@Override
	public boolean visit(InvalidToken token) {
		if (debug) {
			sb.append("<!-- InvalidToken -->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(TagStartToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(TagEndToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(NsTagStartToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(NsTagEndToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(CssStartToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(CssEndToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(ScriptStartToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(ScriptEndToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(TaglibToken taglib) {
		return visitElementToken(taglib);
	}

	@Override
	public boolean visit(JSPIncludeToken token) {
		return visitElementToken(token);
	}

	@Override
	public boolean visit(InlineCodeToken token) {
		if (debug) {
			sb.append("<!-- InlineCodeToken -->");
		}
		sb.append(escapeTextValue(token.getContent()));
		return true;
	}

	@Override
	public boolean visit(CDATAToken token) {
		if (debug) {
			sb.append("<!--CDATA-->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(JSPCodeToken token) {
		if (debug) {
			sb.append("<!--JSPCode-->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(JSPCommentToken token) {
		if (debug) {
			sb.append("<!--JSPComment-->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(XMLToken token) {
		if (debug) {
			sb.append("<!--XML-->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(SEAWSToken token) {
		if (debug) {
			sb.append("<!-- SEAWSTokenPart -->");
		}
		sb.append(token.getContent());
		return true;
	}

	@Override
	public boolean visit(ElAttritbutePart token) {
		if (debug) {
			sb.append("<!-- AttrValPart -->");
		}
		if (token.getName() != null) {
			renderAttribute(sb, token.getName(), token.getOperator(), token.getValue());
		}
		return true;// visit((TokenPart) token);
	}

	@Override
	public boolean visit(ElWhiteSpacePart token) {
		if (debug) {
			sb.append("<!-- WhiteSpaceTokenPart -->");
		}
		sb.append(token.getContent());
		return false;
	}

	@Override
	public boolean visit(ElInvalidPart token) {
		if (debug) {
			sb.append("<!-- InvalidTokenPart -->");
		}
		sb.append(token.getContent());
		return false;
	}

	private static void renderAttribute(StringBuilder buffer, String qname, String eq, String value) {
		if (value == null || value.trim().length() == 0) {
			buffer.append(qname);// .append(' ');
		} else {
			buffer.append(qname).append(eq).append(escapeAttributeValue(value));// .append("\"");
		}
	}

	private static String escapeAttributeValue(String value) {
		if (value == null) {
			return "";
		}
		return value;// .replace("&", "&amp;").replace("\"",
						// "&quot;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private String escapeTextValue(String value) {
		if (value != null) {
			return value;// .replace("&", "&amp;").replace("<",
							// "&lt;").replace(">", "&gt;");
		}
		return "";
	}

	@Override
	public void start() {
		sb = new StringBuilder();

	}
	
	@Override
	public void finish() {
	}

	@Override
	public void abort() {
	}

	private boolean visitElementToken(AbstractElementToken token) {
		if (debug) {
			sb.append("<!--" + token.type() + "-->");
		}
		if (token instanceof NsElementPart) {
			NsElementPart nsel = (NsElementPart) token;
			
			sb.append(nsel.getOpen())//
					.append(nsel.getNs()) //
					.append(':') //
					.append(nsel.getTagName());
		} else {
			sb.append(token.getOpen()).append(token.getTagName());
		}
		appendAttributes(token);
		sb.append(token.getClose());
		return true;
	}

	private void appendAttributes(AbstractElementToken token) {
		if (token.getParts() != null) {
			for (TokenPart p : token.getParts()) {
				switch (p.type()) {
				case ATTRVAL:
				case TAG_WS:
				case INVALID_TAG_PART:
					this.visitToken(p);
					break;
				default:
					this.visitToken(p);
					System.err.println("Uknown token part:" + p.type());
					break;
				}
			}
		}
	}
}
