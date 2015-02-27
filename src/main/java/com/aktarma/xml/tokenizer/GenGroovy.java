package com.aktarma.xml.tokenizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import com.aktarma.xml.tokenizer.scripting.JsfTag;
import com.aktarma.xml.tokenizer.scripting.JsfTagsCollector;

public class GenGroovy {

	public static void main(String[] args) throws IOException,
			URISyntaxException {

		File dir = new File(CFG.getContentDir());
		Collection<File> files = FileUtils.listFiles(dir, new RegexFileFilter(
				".*\\.jsp$"), DirectoryFileFilter.DIRECTORY);
		for (File f : files) {
			String parsed = Utils.parseFile(f);

			Utils.diff(f.getAbsolutePath(), parsed);
		}
		for (JsfTag t : JsfTagsCollector.getTags()) {
			URI u = new URI(t.getUri());
			if (u.getHost() == null || !CFG.getSkipNs().contains(u.getHost())) {
				System.err.println(u.getHost() + " " + u.getPath() + " --> "
						+ t.getTag() + " (" + t.getCount() + " times):"
						+ t.getAttrs());
			}
		}
		createFiles();

		System.err.println(Utils.jsft.toString());
	}

	private static void createFiles() throws URISyntaxException, IOException {
		for (JsfTag t : JsfTagsCollector.getTags()) {
			URI u = new URI(t.getUri());
			System.err.println(u.getHost() + " " + u.getPath() + " --> "
					+ t.getTag() + " (" + t.getCount() + " times):"
					+ t.getAttrs());

			createGroovyFile(t.getUri(), u.getHost(), u.getPath(), t.getTag(),
					t.getAttrs().toString());
		}
	}

	private static void createGroovyFile(String u, String host, String path,
			String tag, String attrs) throws IOException {
		File baseDir = new File("other");

		File dir = new File(baseDir, Utils.toPath(u));
		dir.mkdirs();

		String pkg = Utils.toPackage(u);

		String cls = Utils.toClassName(tag);

		File f = new File(dir, cls + ".groovy");

		// name = name.replace(".", "");

		try (FileOutputStream fos = new FileOutputStream(f)) {
			String code = "package "
					+ pkg
					+ "\n\n"
					+ "import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;\n"
					+ "import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;\n"
					+ "import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;\n"
					+ "\n"
					+ "\n"
					+ "/** "
					+ attrs
					+ " **/\n"
					+ "\n"
					+ "public class "
					+ cls
					+ " extends NsTagVisitor {\n"
					+ "\n"
					+ "\n\tpublic String start(NsTagStartToken token) {\n\t\treturn super.start(token);\n\t}\n"
					+ "\n"
					+ "\n\tpublic String end(NsTagEndToken token) {\n\t\treturn super.end(token);\n\t}\n"
					+ "}";

			fos.write(code.getBytes());
		}

	}
}
