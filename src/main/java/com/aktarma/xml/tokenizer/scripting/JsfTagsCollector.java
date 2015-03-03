package com.aktarma.xml.tokenizer.scripting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aktarma.xml.tokenizer.process.AbstractTokenVisitor;
import com.aktarma.xml.tokenizer.tokens.ITagPart;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.elements.TaglibToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public class JsfTagsCollector extends AbstractTokenVisitor {

	private final static Map<String, JsfTag> tags = new HashMap<>();

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

		JsfTag tag;
		String ns = token.getNs().trim().toLowerCase();

		String name = token.getTagName();

		if (nameSpaces.containsKey(ns)) {
			ns = nameSpaces.get(ns);
		}

		if (tags.containsKey(ns + "/" + name)) {
			tag = tags.get(ns + "/" + name);
			tag.incCount();
		} else {
			tag = new JsfTag(ns, name, null);
			tags.put(ns + "/" + name, tag);
		}
		if (token.getParts() != null) {
			for (ITagPart p : token.getParts()) {
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

	public static Collection<JsfTag> getTags() {
		return tags.values();
	}
}
