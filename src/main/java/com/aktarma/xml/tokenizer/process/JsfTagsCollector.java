package com.aktarma.xml.tokenizer.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aktarma.xml.tokenizer.tokens.ElementTagPart;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TaglibToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public class JsfTagsCollector extends AbstractTokenVisitor {
	public static class JsfTags {
		private final String uri;
		private final String tag;

		private int count = 1;
		
		private final Set<String> attrs = new HashSet<>();

		public JsfTags(String uri, String tag) {
			super();
			this.uri = uri;
			this.tag = tag;
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
		@Override
		public String toString() {
			return "JsfTags [uri=" + uri + ", tag=" + tag + "]";
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
			JsfTags other = (JsfTags) obj;
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

	private final static Map<String, JsfTags> tags = new HashMap<>();

	private final List<TaglibToken> tagLibs = new ArrayList<>();

	private final Map<String, String> nameSpaces = new HashMap<>();

	@Override
	public void start() {
		tagLibs.clear();
		nameSpaces.clear();
	}

	@Override
	public void abort() {
		tagLibs.clear();
	}

	@Override
	public boolean visit(NsTagStartToken token) {

		JsfTags tag;
		String ns = token.getNs().trim().toLowerCase();

		String name = token.getTagName();

		if (nameSpaces.containsKey(ns)) {
			ns = nameSpaces.get(ns);
		}

		if (tags.containsKey(ns + "/" + name)) {
			tag = tags.get(ns + "/" + name);
			tag.incCount();
		} else {
			tag = new JsfTags(ns, name);
			tags.put(ns + "/" + name, tag);
		}
		if (token.getParts() != null) {
			for (ElementTagPart p : token.getParts()) {
				if (p instanceof ElAttritbutePart) {
					ElAttritbutePart at = (ElAttritbutePart) p;
					tag.add(at.getName().trim());
				}
			}
		}
		return super.visit(token);
	}

	@Override
	public boolean visit(TaglibToken taglib) {
		String ns = taglib.getPrefix();
		if (ns == null) {
			ns = "";
		} else {
			ns = ns.trim().toLowerCase().replace("\"", "").replace("'", "");
		}
		String uri = "";
		if (taglib.getUri() == null) {
			uri = "";
		} else {
			uri = taglib.getUri().replace("\"", "").replace("'", "");
		}
		nameSpaces.put(ns, uri);

		tagLibs.add(taglib);
		return true;
	}

	public List<TaglibToken> getTagLibs() {
		return tagLibs;
	}

	@Override
	public String toString() {

		return "JsfTagsCollector [tags=" + tags + "]";
	}

	public static Collection<JsfTags> getTags() {
		return tags.values();
	}
}
