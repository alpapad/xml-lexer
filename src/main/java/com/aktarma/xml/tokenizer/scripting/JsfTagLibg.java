package com.aktarma.xml.tokenizer.scripting;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;

import com.aktarma.xml.tokenizer.Utils;

public class JsfTagLibg {

	final Map<String,JsfTag> tags = new HashMap<>();
		
	private final String uri;
	
	private final String prefix;
	
	private final String basePath;
	
	
	public JsfTagLibg(String basePath, String prefix, String uri) {
		super();
		this.uri = uri;
		this.prefix = prefix;
		this.basePath = basePath;
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
			JsfTag tg = new JsfTag(uri, tag, visitor(uri, tag));
			tags.put(tag, tg);
			return tg.getVisitor();
		}
	}
	private   NsTagVisitor visitor(String uri, String tag) {
		String path = Utils.toPath(uri);
		try {

		
			File dir = new File(new File(basePath), path);


			final String className = Utils.toClassName(tag);
			File f = new File(dir, className + ".groovy");

			@SuppressWarnings("resource")
			GroovyClassLoader gcl = new GroovyClassLoader();
			Class<?> clazz = gcl.parseClass(f);
			Object aScript = clazz.getConstructor(new Class<?>[]{}).newInstance();
			return (NsTagVisitor) aScript;
		} catch (IllegalAccessException | InstantiationException | CompilationFailedException | IOException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new NsTagVisitor();
	}
	
	@Override
	public String toString() {
		return "JsfTagLibg [tags=" + tags + ", uri=" + uri + ", prefix="
				+ prefix + "]";
	}
	
	
}
