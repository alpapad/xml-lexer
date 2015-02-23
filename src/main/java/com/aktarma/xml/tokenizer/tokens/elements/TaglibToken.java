package com.aktarma.xml.tokenizer.tokens.elements;

import com.aktarma.xml.tokenizer.tokens.ElementTagPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public class TaglibToken extends AbstractElementToken {
	public TaglibToken(int line, int charpos, String tagName) {
		super(line, charpos, TokenType.TAGLIB);
		this.open = TAG_OPEN;
		this.tagName = tagName.substring(TAG_OPEN.length());
	}
	
	public String getUri(){
		return getAttrVal("uri");
	}
	
	public String getPrefix(){
		return getAttrVal("prefix");
	}
	
	private String getAttrVal(String attr) {
		for(ElementTagPart part:this.getParts()){
			if(part instanceof ElAttritbutePart) {
				ElAttritbutePart att = (ElAttritbutePart)part;
				if(attr.equalsIgnoreCase(att.getName().trim())){
					return att.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "TaglibToken [getUri()=" + getUri() + ", getPrefix()="
				+ getPrefix() + "]";
	}
	
	
}
