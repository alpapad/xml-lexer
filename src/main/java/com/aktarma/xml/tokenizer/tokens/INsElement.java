package com.aktarma.xml.tokenizer.tokens;


public interface INsElement extends ElementPart, IElement{

	String getNs();
	
	void setNs(String ns);
	
	
	boolean isStart();

	void setSibling(INsElement sibling);

	void setParent(INsElement parent);

	INsElement getParent();
}
