package com.aktarma.xml.tokenizer.scripting;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.groovy.control.CompilationFailedException;

import com.aktarma.xml.tokenizer.Utils;
import com.aktarma.xml.tokenizer.tokens.INsElement;

public class JsfTagLibg {

	final Map<String, JsfTag> tags = new HashMap<>();

	private final String uri;

	private final String prefix;

	private final String basePath;

	private final Set<String> attrs = new LinkedHashSet<>();


	private boolean validateOnly = false;

	private final static NsTagVisitor tgv = new NsTagVisitor();

	public JsfTagLibg(String basePath, String prefix, String uri) {
		super();
		this.uri = uri;
		this.prefix = prefix;
		this.basePath = basePath;
	}

	public boolean isValidateOnly() {
		return validateOnly;
	}

	public void setValidateOnly(boolean validateOnly) {
		this.validateOnly = validateOnly;
	}


	public String getUri() {
		return uri;
	}

	public String getPrefix() {
		return prefix;
	}

	public NsTagVisitor tag(INsElement token, String tag) {
		JsfTag tg;

		if (tags.containsKey(tag)) {
			tg = tags.get(tag);
			tg.incCount();
		} else {
			tg = new JsfTag(this, tag, visitor(uri, tag));
			tags.put(tag, tg);
		}
		return tg.getVisitor();
	}

	public boolean addAttribute(String e) {
		return attrs.add(e);
	}

	public Set<String> getAttributes() {
		return attrs;
	}

	private NsTagVisitor visitor(String uri, String tag) {
		if (!validateOnly) {
			String path = Utils.toPath(uri);
			try {
				File dir = new File(new File(basePath), path);

				final String className = Utils.toClassName(tag);
				File f = new File(dir, className + ".groovy");
				if (f.exists()) {

					@SuppressWarnings("resource")
					GroovyClassLoader gcl = new GroovyClassLoader();
					Class<?> clazz = gcl.parseClass(f);
					Object aScript = clazz.getConstructor(new Class<?>[] {})
							.newInstance();
					return (NsTagVisitor) aScript;
				}
			} catch (IllegalAccessException | InstantiationException
					| CompilationFailedException | IOException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tgv;
	}

	public boolean finish() {
		tags.clear();
		return true;
	}

	public boolean abort() {
		tags.clear();
		return true;
	}

	@Override
	public String toString() {
		return "JsfTagLibg [tags=" + tags + ", uri=" + uri + ", prefix="
				+ prefix + "]";
	}

}
