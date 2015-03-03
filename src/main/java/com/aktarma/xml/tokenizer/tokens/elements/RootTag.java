package com.aktarma.xml.tokenizer.tokens.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.ITagPart;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.INsElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public class RootTag implements INsElement{

	private List<IElement> children = new ArrayList<>();
	
	@Override
	public boolean isSelfClose() {
		return false;
	}

	@Override
	public void setSelfClose(boolean selfClose) {
	}

	@Override
	public String getTagName() {
		return "";
	}

	@Override
	public void setOpen(String open) {
	}

	@Override
	public void setClose(String close) {
	}

	@Override
	public void addPart(ITagPart element) {
	}

	@Override
	public List<ITagPart> getParts() {
		return Collections.emptyList();
	}

	@Override
	public String getOpen() {
		return "";
	}

	@Override
	public String getClose() {
		return "";
	}

	@Override
	public List<ElAttritbutePart> getAttributes() {
		return Collections.emptyList();
	}

	@Override
	public ElAttritbutePart getAttribute(String attr) {
		return null;
	}

	@Override
	public String getNs() {
		return "";
	}

	@Override
	public void setNs(String ns) {
	}

	@Override
	public boolean isStart() {
		return true;
	}

	@Override
	public void setSibling(INsElement sibling) {
	}

	@Override
	public INsElement getParent() {
		return null;
	}

	@Override
	public void addChild(IElement child) {
		children.add(child);
	}

	@Override
	public List<IElement> getChildren() {
		return children;
	}

	@Override
	public int line() {
		return 0;
	}

	@Override
	public int charPos() {
		return 0;
	}

	@Override
	public TokenType type() {
		return null;
	}

	@Override
	public void setParent(IElement parent) {
	}
}
