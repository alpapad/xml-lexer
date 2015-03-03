package com.aktarma.xml.tokenizer.tokens;

import java.util.List;

import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public interface IElement extends IToken{

	boolean isStart();
	

	boolean isSelfClose();


	void setSelfClose(boolean selfClose);


	String getTagName();


	void setOpen(String open);


	void setClose(String close);


	void addPart(ITagPart element);


	List<ITagPart> getParts();


	List<ElAttritbutePart> getAttributes();


	ElAttritbutePart getAttribute(String attr);

	String getOpen();

	String getClose();
	
	void setParent(IElement parent);

	IElement getParent();
	
	void addChild(IElement child);
	
	List<IElement> getChildren();
	
	void setSibling(IElement sibling);
	
	IElement getSibling();
}