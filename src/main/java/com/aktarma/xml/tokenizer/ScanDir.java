package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.aktarma.xml.tokenizer.process.JsfTagsCollector.JsfTags;

public class ScanDir {

	public static void main(String[] args) throws IOException {

		File dir = new File("/home/alpapad/rit/webapp");
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
			System.err.println(t.getUri()  + " --> " + t.getTag() + " (" + t.getCount() + " times):" + t.getAttrs());
		}
		System.err.println(Utils.jsft.toString());
	}
}
