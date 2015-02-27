package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class Translate {

	public static void main(String[] args) throws IOException, URISyntaxException {

		
		File dir = new File(CFG.getContentDir());
		Collection<File> files = FileUtils.listFiles(
				  dir, 
				  new RegexFileFilter(".*\\.jsp$"), 
				  DirectoryFileFilter.DIRECTORY
				);
		for(File f: files) {
			String parsed = Utils.parseReplaceFile(f,"src/groovy");
			String finalPath = f.getAbsolutePath().replaceAll("jsp$", "xhtml");
			try(FileOutputStream fos = new FileOutputStream(new File(finalPath))){
				IOUtils.write(parsed, fos);
			}
			Utils.diff(f.getAbsolutePath(), parsed);
		}	
	}
}
