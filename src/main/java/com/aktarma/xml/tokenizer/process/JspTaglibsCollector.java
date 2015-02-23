package com.aktarma.xml.tokenizer.process;

import java.util.ArrayList;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.elements.TaglibToken;

public class JspTaglibsCollector extends AbstractTokenVisitor {

	private final List<TaglibToken> tagLibs = new ArrayList<>();

	@Override
	public void start() {
		tagLibs.clear();
	}

	@Override
	public void abort() {
		tagLibs.clear();
	}

	@Override
	public boolean visit(TaglibToken taglib) {
		tagLibs.add(taglib);
		return true;
	}

	public List<TaglibToken> getTagLibs() {
		return tagLibs;
	}

	@Override
	public String toString() {
		return "JspTaglibsCollector [tagLibs=" + tagLibs + "]";
	}
}
