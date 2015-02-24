package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.aktarma.xml.tokenizer.process.JsfTagsCollector.JsfTags;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;

public class GenGroovy {

	public static void main(String[] args) throws IOException, URISyntaxException {

		File dir = new File("C:/WORK/projects/reach/reach");
		Collection<File> files = FileUtils.listFiles(
				  dir, 
				  new RegexFileFilter(".*\\.jsp$"), 
				  DirectoryFileFilter.DIRECTORY
				);
		for(File f: files) {
			String parsed = Utils.parseFile(f);
			
			Utils.diff(f.getAbsolutePath(), parsed);
		}
		for(JsfTags t:Utils.jsft.getTags()){
			URI u = new URI(t.getUri());
			if(!("java.sun.com".equalsIgnoreCase(u.getHost())  || "www.echa.eu".equalsIgnoreCase(u.getHost()) || null == u.getHost() )) {
				System.err.println(u.getHost() + " " + u.getPath()  + " --> " + t.getTag() + " (" + t.getCount() + " times):" + t.getAttrs());
			}
		}
		createFiles();
		
		System.err.println(Utils.jsft.toString());
	}
	
	
	private static void createFiles() throws URISyntaxException, IOException{
		for(JsfTags t:Utils.jsft.getTags()){
			URI u = new URI(t.getUri());
			//if(!("java.sun.com".equalsIgnoreCase(u.getHost())  || "www.echa.eu".equalsIgnoreCase(u.getHost()) || null == u.getHost() )) {
				System.err.println(u.getHost() + " " + u.getPath()  + " --> " + t.getTag() + " (" + t.getCount() + " times):" + t.getAttrs());
				
				createGroovyFile(u.getHost(),  u.getPath(), t.getTag(), t.getAttrs().toString());
				
		//	}
		}
	}
	
	private static void createGroovyFile(String host, String path, String tag, String attrs ) throws IOException{
		File baseDir = new File("C:/WORK/projects/aktarma.xml-lexer/other");
		
		final String names = "pp" + tag;
		File dir= new File(baseDir,host + path);
		dir.mkdirs();
		
		String name = host + path.replace('/', '.');// + "." + tag ;
		
		
		File f = new File(dir,/* name + "." +*/ names+ ".groovy");
		
		
		//name = name.replace(".", "");
		
		try(FileOutputStream fos = new FileOutputStream(f)){
			String code =
					//"package " + name + "\n\n" + 
					"import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;\n" +
					"import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;\n" +
					"import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;\n" +
					"\n" +
					"\n" +
					//"/ ** " + attrs + " **/\n" +
					"\n" +
					"public class " + names + " extends NsTagVisitor {\n"+
					"\n"+
					"\n\tpublic String start(NsTagStartToken token) {\n\t\treturn super.start(token);\n\t}\n" + 
					"\n"+
					"\n\tpublic String end(NsTagEndToken token) {\n\t\treturn super.end(token);\n\t}\n" + 
					"}";
			
			
			fos.write(code.getBytes());
		}
		
		
	}
}
