package com.aktarma.xml.tokenizer.tokens;

import java.util.List;

import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public interface IElement {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#isSelfClose()
	 */
	boolean isSelfClose();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#setSelfClose(boolean)
	 */
	void setSelfClose(boolean selfClose);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#getTagName()
	 */
	String getTagName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.ElementPart#setOpen(java.lang.String)
	 */
	void setOpen(String open);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.ElementPart#setClose(java.lang.String)
	 */
	void setClose(String close);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.ElementPart#addPart(com.aktarma.xml.
	 * tokenizer.tokens.ElementTagPart)
	 */
	public abstract void addPart(ElementTagPart element);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#getParts()
	 */
	public abstract List<ElementTagPart> getParts();

	/*
	 * 
	 */
	public abstract List<ElAttritbutePart> getAttributes();

	/*
	 * 
	 */
	public abstract ElAttritbutePart getAttribute(String attr);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#getOpen()
	 */
	public abstract String getOpen();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.ElementPart#getClose()
	 */
	public abstract String getClose();

}