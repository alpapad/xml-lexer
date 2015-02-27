package com.aktarma.xml.tokenizer.tokens.elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.AbstractTokenPart;
import com.aktarma.xml.tokenizer.tokens.ElementPart;
import com.aktarma.xml.tokenizer.tokens.ElementTagPart;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public abstract class AbstractElementToken extends AbstractTokenPart implements
		ElementPart, IElement {

	protected final static String TAG_OPEN = "<".intern();
	protected final static String ETAG_OPEN = "</".intern();
	protected final static String TAG_CLOSE = ">".intern();
	protected final static String TAG_SLEF_CLOSE = "/>".intern();

	protected String tagName = "";

	protected String open = "";

	protected String close = "";

	protected boolean selfClose = false;

	private List<ElementTagPart> parts = null;

	private LinkedHashMap<String, ElementTagPart> indexedParts = null;

	public AbstractElementToken(int line, int charpos, TokenType type) {
		super(line, charpos, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#isSelfClose()
	 */
	@Override
	public boolean isSelfClose() {
		return selfClose;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.elements.IElement#setSelfClose(boolean)
	 */
	@Override
	public void setSelfClose(boolean selfClose) {
		this.selfClose = selfClose;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#getTagName()
	 */
	@Override
	public String getTagName() {
		return tagName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.elements.IElement#setOpen(java.lang.
	 * String)
	 */
	@Override
	public void setOpen(String open) {
		this.open = open;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.elements.IElement#setClose(java.lang
	 * .String)
	 */
	@Override
	public void setClose(String close) {
		this.close = close;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.elements.IElement#addPart(com.aktarma
	 * .xml.tokenizer.tokens.ElementTagPart)
	 */
	@Override
	public void addPart(ElementTagPart element) {
		if (parts == null) {
			parts = new ArrayList<ElementTagPart>();
		}
		element.setParent(this);
		// is indexed?
		if (element.getKey() != null) {
			if (indexedParts == null) {
				indexedParts = new LinkedHashMap<String, ElementTagPart>();
			}
			// existed?
			if (indexedParts.containsKey(element.getKey())) {
				// remove!
				ElementTagPart other = indexedParts.get(element.getKey());
				if (other != null) {
					parts.remove(other);
				}
			}
			indexedParts.put(element.getKey(), element);
		}
		parts.add(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#getParts()
	 */
	@Override
	public List<ElementTagPart> getParts() {
		return parts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#getAttributes()
	 */
	@Override
	public List<ElAttritbutePart> getAttributes() {
		List<ElAttritbutePart> attrs = new ArrayList<>();
		if (parts != null) {
			for (ElementTagPart p : parts) {
				if (p instanceof ElAttritbutePart) {
					attrs.add((ElAttritbutePart) p);
				}
			}
		}
		return attrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aktarma.xml.tokenizer.tokens.elements.IElement#getAttribute(java.
	 * lang.String)
	 */
	@Override
	public ElAttritbutePart getAttribute(String attr) {
		if(attr == null || indexedParts == null) {
			return null;
		}
		
		ElementTagPart p = indexedParts.get(attr.trim().toLowerCase());
		if (p instanceof ElAttritbutePart) {
			return (ElAttritbutePart) p;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#getOpen()
	 */
	@Override
	public String getOpen() {
		return open;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aktarma.xml.tokenizer.tokens.elements.IElement#getClose()
	 */
	@Override
	public String getClose() {
		return close;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [tagName=" + tagName
				+ ", open=" + open + ", close=" + close + ", selfClose="
				+ selfClose + ", parts=" + parts + ", indexedParts="
				+ indexedParts + "]";
	}

}
