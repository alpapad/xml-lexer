package com.aktarma.xml.tokenizer.tokens.elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.aktarma.xml.tokenizer.tokens.AbstractToken;
import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.ITagPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public abstract class AbstractElement extends AbstractToken implements IElement {

	protected final static String TAG_OPEN = "<".intern();
	protected final static String ETAG_OPEN = "</".intern();
	protected final static String TAG_CLOSE = ">".intern();
	protected final static String TAG_SLEF_CLOSE = "/>".intern();

	protected String tagName = "";

	protected String open = "";

	protected String close = "";

	protected boolean selfClose = false;

	private List<ITagPart> parts = null;

	private LinkedHashMap<String, ITagPart> indexedParts = null;

	private IElement parent;
	
	private IElement sibling;
	
	private List<IElement> children = new ArrayList<>();
	
	public AbstractElement(int line, int charpos, TokenType type) {
		super(line, charpos, type);
	}

	@Override
	public boolean isSelfClose() {
		return selfClose;
	}

	@Override
	public void setSelfClose(boolean selfClose) {
		this.selfClose = selfClose;
	}

	@Override
	public String getTagName() {
		return tagName;
	}

	@Override
	public void setOpen(String open) {
		this.open = open;
	}

	@Override
	public void setClose(String close) {
		this.close = close;
	}


	@Override
	public void addPart(ITagPart element) {
		if (parts == null) {
			parts = new ArrayList<ITagPart>();
		}
		element.setParent(this);
		// is indexed?
		if (element.getKey() != null) {
			if (indexedParts == null) {
				indexedParts = new LinkedHashMap<String, ITagPart>();
			}
			// existed?
			if (indexedParts.containsKey(element.getKey())) {
				// remove!
				ITagPart other = indexedParts.get(element.getKey());
				if (other != null) {
					parts.remove(other);
				}
			}
			indexedParts.put(element.getKey(), element);
		}
		parts.add(element);
	}


	@Override
	public List<ITagPart> getParts() {
		return parts;
	}

	@Override
	public List<ElAttritbutePart> getAttributes() {
		List<ElAttritbutePart> attrs = new ArrayList<>();
		if (parts != null) {
			for (ITagPart p : parts) {
				if (p instanceof ElAttritbutePart) {
					attrs.add((ElAttritbutePart) p);
				}
			}
		}
		return attrs;
	}


	@Override
	public ElAttritbutePart getAttribute(String attr) {
		if (attr == null || indexedParts == null) {
			return null;
		}

		ITagPart p = indexedParts.get(attr.trim().toLowerCase());
		if (p instanceof ElAttritbutePart) {
			return (ElAttritbutePart) p;
		}
		return null;
	}

	@Override
	public String getOpen() {
		return open;
	}


	@Override
	public String getClose() {
		return close;
	}

	@Override
	public IElement getParent() {
		return parent;
	}
	
	@Override
	public void setParent(IElement parent) {
		this.parent = parent;
		if(parent != null){
			parent.addChild(this);
		}
	}

	@Override
	public void addChild(IElement el) {
		children.add(el);
	}

	@Override
	public List<IElement> getChildren() {
		return children;
	}
	
	@Override
	public void setSibling(IElement el) {
		this.sibling = el;
	}

	public IElement getSibling() {
		return sibling;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [tagName=" + tagName + ", open=" + open + ", close=" + close + ", selfClose=" + selfClose + ", parts=" + parts + ", indexedParts=" + indexedParts + "]";
	}

}
