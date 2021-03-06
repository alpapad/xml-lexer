package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.aktarma.xml.tokenizer.scripting.JsfTag;
import com.aktarma.xml.tokenizer.scripting.JsfTagsCollector;

public class ScanDir {

	public static void main(String[] args) throws IOException, URISyntaxException {

		File dir = new File(CFG.getContentDir());
		Collection<File> files = FileUtils.listFiles(
				  dir, 
				  new RegexFileFilter(".*\\.jsp$"), 
				  DirectoryFileFilter.DIRECTORY
				);
		for(File f: files) {
			String parsed = Utils.parseFile(f);
			
			Utils.diff(f.getAbsolutePath(), parsed);
		}
		for(JsfTag t:JsfTagsCollector.getTags()){
			URI u = new URI(t.getUri());
			if (u.getHost() == null || !CFG.getSkipNs().contains(u.getHost())) {
				System.err.println(u.getHost() + " " + u.getPath()  + " --> " + t.getTag() + " (" + t.getCount() + " times):" + t.getAttrs());
			}
		}
		
		System.err.println(Utils.jsft.toString());
	}
}
