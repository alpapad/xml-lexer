package com.aktarma.xml.tokenizer.scripting;

import java.util.HashMap;
import java.util.Map;

public class JsfTagLibg {

	final Map<String,JsfTag> tags = new HashMap<>();
		
	private final String uri;
	
	private final String prefix;
	
	public JsfTagLibg(String prefix, String uri) {
		super();
		this.uri = uri;
		this.prefix = prefix;
	}
	
	public Map<String, JsfTag> getTags() {
		return tags;
	}

	public String getUri() {
		return uri;
	}

	public String getPrefix() {
		return prefix;
	}

	public NsTagVisitor tag(String tag) {
		if(tags.containsKey(tag)) {
			return tags.get(tag).getVisitor();
		} else {
			JsfTag tg = new JsfTag(uri, tag);
			tags.put(tag, tg);
			return tg.getVisitor();
		}
	}

	@Override
	public String toString() {
		return "JsfTagLibg [tags=" + tags + ", uri=" + uri + ", prefix="
				+ prefix + "]";
	}
	
	
}
