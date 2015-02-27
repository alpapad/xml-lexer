package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.IOException;

import com.aktarma.xml.tokenizer.process.StringSink;

public class SingleFile {

	public static void main(String[] args) throws IOException {
		StringSink.debug = true;
		File f = new File("aa.jsp");
		
		String parsed = Utils.parseFile(f);
		
		Utils.diff(f.getAbsolutePath(), parsed);
		System.err.println(parsed);
	}


}
