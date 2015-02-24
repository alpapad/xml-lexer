package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.aktarma.xml.tokenizer.process.JsfTagsCollector;
import com.aktarma.xml.tokenizer.process.JsfTagsCollector.JsfTags;

public class ScanDir {

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
		for(JsfTags t:JsfTagsCollector.getTags()){
			URI u = new URI(t.getUri());
			if(!("java.sun.com".equalsIgnoreCase(u.getHost())  || "www.echa.eu".equalsIgnoreCase(u.getHost()) || null == u.getHost() )) {
				System.err.println(u.getHost() + " " + u.getPath()  + " --> " + t.getTag() + " (" + t.getCount() + " times):" + t.getAttrs());
			}
		}
		
		System.err.println(Utils.jsft.toString());
	}
}
