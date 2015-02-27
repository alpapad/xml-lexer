package com.aktarma.xml.tokenizer.scripting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aktarma.xml.tokenizer.tokens.INsElement;

public class JsfTag {
	private final String uri;
	private final String tag;

	private int count = 1;

	private final Set<String> attrs = new HashSet<>();

	// stack of elements seen so far
	private final List<INsElement> elementStack = new ArrayList<>();

	private final NsTagVisitor visitor;

	public JsfTag(String uri, String tag, NsTagVisitor visitor) {
		this.uri = uri;
		this.tag = tag;
		this.visitor = visitor;
	}

	public String getUri() {
		return uri;
	}

	public String getTag() {
		return tag;
	}

	public boolean add(String e) {
		return attrs.add(e);
	}

	public Set<String> getAttrs() {
		return attrs;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void incCount() {
		this.count++;
	}

	public NsTagVisitor getVisitor() {
		return visitor;
	}

	public void push(INsElement element) {
		elementStack.add(0, element);
	}

	public INsElement peek() {
		if (elementStack.size() != 0) {
			return elementStack.get(0);
		} else {
			return null;
		}
	}

	public INsElement pop() {
		if (elementStack.size() != 0) {
			return elementStack.remove(0);
		} else {
			return null;
		}
	}

	public int size() {
		return elementStack.size();
	}

	public List<INsElement> getElementStack() {
		return elementStack;
	}

	@Override
	public String toString() {
		return "JsfTag [uri=" + uri + ", tag=" + tag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsfTag other = (JsfTag) obj;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
}
