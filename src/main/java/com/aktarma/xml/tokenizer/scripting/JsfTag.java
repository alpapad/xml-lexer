package com.aktarma.xml.tokenizer.scripting;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.groovy.control.CompilationFailedException;


public class JsfTag {
	private final String uri;
	private final String tag;

	private int count = 1;

	private final Set<String> attrs = new HashSet<>();

	private final NsTagVisitor visitor;
	
	public JsfTag(String uri, String tag) {
		this.uri = uri;
		this.tag = tag;
		this.visitor = visitor(uri, tag);
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

	@Override
	public String toString() {
		return "JsfTag [uri=" + uri + ", tag=" + tag + "]";
	}

	private final static NsTagVisitor visitor(String uri, String tag) {
		URI u;
		try {
			u = new URI(uri);

//			if (!("java.sun.com".equalsIgnoreCase(u.getHost())
//					|| "www.echa.eu".equalsIgnoreCase(u.getHost()) || null == u
//						.getHost())) {
				File baseDir = new File( "C:/WORK/projects/aktarma.xml-lexer/other");
				String host =  u.getHost();
				String path = u.getPath();
				if(host == null) {
					host = "null";
				}
				if(path == null) {
					path = null;
				}
				File dir = new File(baseDir, host + path);
				
				
				//String name = host + path.replace('/', '.') + "." + tag ;
				
				final String names = "pp" + tag;
				File f = new File(dir, names + ".groovy");
				

				@SuppressWarnings("resource")
				GroovyClassLoader gcl = new GroovyClassLoader();
				Class<?> clazz = gcl.parseClass(f);
				Object aScript = clazz.newInstance();
				System.err.println(aScript.getClass().getSuperclass());
				return  (NsTagVisitor) aScript;
//			} 
		} catch (IllegalAccessException | InstantiationException | URISyntaxException | CompilationFailedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new NsTagVisitor();
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
