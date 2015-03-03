package com.aktarma.xml.tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.aktarma.xml.tokenizer.parser.MixedHtmlParser;
import com.aktarma.xml.tokenizer.process.StringSink;
import com.aktarma.xml.tokenizer.process.TokenChainSink;
import com.aktarma.xml.tokenizer.process.TokenCollectorSink;
import com.aktarma.xml.tokenizer.scripting.JsfTagsCollector;
import com.aktarma.xml.tokenizer.scripting.PreparseSink;
import com.aktarma.xml.tokenizer.scripting.ScriptedSink;
import com.aktarma.xml.tokenizer.tokens.IToken;
import com.aktarma.xml.tokenizer.tokens.TokenType;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class Utils {

	public final String basePath = "";

	public static String toPackage(String uri) {
		if (StringUtils.isEmpty(uri)) {
			return "rootp";
		}

		try {
			URI u = new URI(uri);
			String h = u.getHost();
			if (StringUtils.isEmpty(h)) {
				h = "rootp";
			} else {
				if ("java.sun.com".equalsIgnoreCase(h)) {
					h = "core.jsf";
				} else {
					String[] v = h.split("\\.");
					ArrayUtils.reverse(v);
					h = StringUtils.join(v, ".");
				}
			}
			String path = u.getPath();
			path = path.replace("/", ".").replace("\\",".").replace('-', '.');

			return h + path;
		} catch (URISyntaxException e) {
			return uri;
		}
	}

	public static String toPath(String uri) {
		if (StringUtils.isEmpty(uri)) {
			return "rootp";
		}

		try {
			URI u = new URI(uri);
			String h = u.getHost();

			if (StringUtils.isEmpty(h)) {
				h = "rootp";
			} else {
				if ("java.sun.com".equalsIgnoreCase(h)) {
					h = "core/jsf";
				} else {
					String[] v = h.split("\\.");
					ArrayUtils.reverse(v);
					h = StringUtils.join(v, "/");
				}
			}
			String path = u.getPath().replace('-','/');
			return h + path;
		} catch (URISyntaxException e) {
			return uri;
		}
	}
	
	public static String toClassName(String tagName){
		return "$" + StringUtils.trim(tagName);
	}

	// Helper method for get the file content
	public static List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	// Helper method for get the file content
	public static List<String> stringToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try (BufferedReader in = new BufferedReader(new StringReader(filename))) {
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static JsfTagsCollector jsft = new JsfTagsCollector();

	public static String parseFile(File f) throws IOException {
		StringSink s = new StringSink();
		TokenCollectorSink collector = new TokenCollectorSink();


		TokenChainSink sink = new TokenChainSink();
		sink.addVisitor(collector);
		sink.addVisitor(jsft);
		//sink.addVisitor(new ScriptedSink(true, "other"));

		MixedHtmlParser.processFile(f.getAbsolutePath(), sink);

		for (IToken token : collector.getCollected()) {
			TokenType.visitType(s, token);
		}

		return s.text();
	}

	public static String parseReplaceFile(File f, String basePath) throws IOException {
		ScriptedSink s = new ScriptedSink(false, basePath, f.getAbsolutePath());
		PreparseSink preparse = new PreparseSink();
		
		MixedHtmlParser.processFile(f.getAbsolutePath(), preparse);
		for(IToken tkn : preparse.getCollected()){
			s.visitToken(tkn);
		}
		return s.text();
	}
	
	public static String validate(File f, String basePath) throws IOException {
		ScriptedSink s = new ScriptedSink(true,basePath, f.getAbsolutePath());
		PreparseSink preparse = new PreparseSink();
		MixedHtmlParser.processFile(f.getAbsolutePath(), preparse);
		for(IToken tkn : preparse.getCollected()){
			s.visitToken(tkn);
		}
		return s.text();
	}

	public static void diff(String file, String parsed) {
		List<String> original = fileToLines(file);
		List<String> revised = stringToLines(parsed);

		// Compute diff. Get the Patch object. Patch is the container for
		// computed deltas.
		Patch patch = DiffUtils.diff(original, revised);
		List<Delta> deltas = patch.getDeltas();
		if (deltas != null && deltas.size() > 0) {
			System.out.println("Diff for file:" + file);
		}
		for (Delta delta : deltas) {
			printDelta(delta);
		}
	}

	public static void printDelta(Delta delta) {

		System.out.println(delta.getClass().getSimpleName() + ", position: " + delta.getOriginal().getPosition() + ", lines: \n" + delta.getOriginal().getLines() + "\n to \n" + delta.getRevised().getLines());
	}
}
