package com.aktarma.xml.tokenizer.tokens;



public interface INsElement extends IElement{

	String getNs();
	
	void setNs(String ns);

	void setSibling(INsElement sibling);
}
