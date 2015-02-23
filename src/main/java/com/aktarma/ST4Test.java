package com.aktarma;

import java.io.IOException;
import java.util.HashMap;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;



public class ST4Test {

	public static void main(String[] args) throws IOException {
		STGroup g = new STGroupFile("templates/xhtml.stg");
		ST st = g.getInstanceOf("html");
		//st.a
		HashMap<String,String> a =	new HashMap<String,String>();
		a.put("f","http://xmlns.jcp.org/jsf/core");
		a.put("h","http://xmlns.jcp.org/jsf/html");
		a.put("pt","http://xmlns.jcp.org/jsf/passthrough");
		a.put("jsf","http://xmlns.jcp.org/jsf");
		//a.put("XX","AAA");
		//a.put("XX","AAA");
		//a.put("XX","AAA");
		st.add("namespaces", a);
		//st.add(arg0, arg1)
		System.err.println(st.render());
		
	}
}
/*
xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://xmlns.jcp.org/jsf/core"
        xmlns:h="http://xmlns.jcp.org/jsf/html"
        xmlns:pt="http://xmlns.jcp.org/jsf/passthrough"
        xmlns:jsf="http://xmlns.jcp.org/jsf"
        xmlns:p="http://primefaces.org/ui" 
 */
