package com.aktarma.xml.tokenizer.scripting;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.aktarma.xml.tokenizer.tokens.INsElement;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;

public class TagBuilder {

	private final INsElement token;

	private final Map<String,String> attrs = new LinkedHashMap<>();
	private String tagName;
	
	public TagBuilder(INsElement token) {
		super();
		this.token = token;
		this.tagName = token.getNs() + ':' + token.getTagName();
	}

	public static TagBuilder start(INsElement token) {
		return new TagBuilder(token);
	}

	public TagBuilder tag() {
		this.tagName = token.getNs() + ':' + token.getTagName();
		return this;
	}

	public TagBuilder tag(String tag) {
		this.tagName =  tag.trim();
		return this;
	}

	public TagBuilder at(String name) {
		Collections.singletonMap(name, name);
		return at(Collections.singletonMap(name, name), null, null);
	}

	public TagBuilder at(String name, Map<String, ?> valueMap) {
		return at(Collections.singletonMap(name, name), valueMap, null);
	}

	public TagBuilder at(String name, String newName) {
		return at(Collections.singletonMap(name, newName), (Map<String, ?>) null, null);
	}

	public TagBuilder a(String name, String val) {
		attrs.put(name.trim(), "\"" + val + "\"");
		return this;
	}

	public TagBuilder at(Map<String, String> nameMap, Map<String, ?> valueMap, String def) {
		String name = nameMap.keySet().iterator().next();
		String newName = nameMap.values().iterator().next();
		if(newName == null) {
			newName = name;
		}
		
		ElAttritbutePart att = token.getAttribute(name);

		if (valueMap != null) {
			System.err.println("MAP");
		}
		if (att != null) {
			if (att.getValue() != null) {
				if (valueMap != null) {
					String v = val(name);
					if (valueMap.containsKey(v)) {
						return this.a(name, (String) valueMap.get(v));
					} else if (def != null) {
						return this.a(name, def);
					}
				}
			} 
			attrs.put(newName, att.getValue());
		}
		return this;
	}

	public String val(String name) {
		if(name == null) {
			System.err.println("Null??");
		}
		return val(name, null);
	}

	public String val(String name, String def) {
		ElAttritbutePart att = token.getAttribute(name);

		if (att != null && !StringUtils.isEmpty(att.getValue())) {
			String v = att.getValue().trim();

			v = v.replaceAll("^['\"]", "");
			v = v.replaceAll("(['\"])$", "");
			return v;
		}
		return def;
	}

	public String tagName() {
		return token.getTagName();
	}

	public String ns() {
		return token.getNs();
	}

	public boolean exists(String name) {
		return token.getAttribute(name) != null;
	}

	public String get() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(token.getOpen()).append(tagName);
		for(Entry<String,String> e: attrs.entrySet()) {
			sb.append(' ').append(e.getKey());
			if(e.getValue() != null) {
				sb.append("=").append(e.getValue());
			} 
		}
		sb.append(token.getClose());
		return sb.toString();
	}
}
