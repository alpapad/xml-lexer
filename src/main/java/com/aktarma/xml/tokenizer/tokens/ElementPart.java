package com.aktarma.xml.tokenizer.tokens;

import java.util.List;

public interface ElementPart {

	boolean isSelfClose();

	void setSelfClose(boolean selfClose);

	String getTagName();

	void setOpen(String open);

	void setClose(String close);

	void addPart(ElementTagPart element);

	List<ElementTagPart> getParts();

	String getOpen();

	String getClose();

}