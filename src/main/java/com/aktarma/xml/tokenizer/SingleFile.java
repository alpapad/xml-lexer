package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.IOException;

import com.aktarma.xml.tokenizer.process.StringSink;

public class SingleFile {

	public static void main(String[] args) throws IOException {
		StringSink.debug = true;
		File f = new File("/home/alpapad/rit/xml-lexer/aa.jsp");//"/home/alpapad/rit/webapp/webapp/secure/raw/dossier/cl/update_harmonized_entry.jsp");
		//File f = new File("/home/alpapad/rit/webapp/webapp/secure/raw/dossier/cl/update_harmonized_entry.jsp");
		
		String parsed = Utils.parseFile(f);
		
		Utils.diff(f.getAbsolutePath(), parsed);
		System.err.println(parsed);
	}


}