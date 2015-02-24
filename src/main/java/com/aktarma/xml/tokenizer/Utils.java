package com.aktarma.xml.tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import com.aktarma.xml.tokenizer.parser.MixedHtmlParser;
import com.aktarma.xml.tokenizer.process.JsfTagsCollector;
import com.aktarma.xml.tokenizer.process.JspTaglibsCollector;
import com.aktarma.xml.tokenizer.process.StringSink;
import com.aktarma.xml.tokenizer.process.TokenChainSink;
import com.aktarma.xml.tokenizer.process.TokenCollectorSink;
import com.aktarma.xml.tokenizer.scripting.ScriptedSink;
import com.aktarma.xml.tokenizer.tokens.TokenPart;
import com.aktarma.xml.tokenizer.tokens.TokenType;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

public class Utils {
	   // Helper method for get the file content
	public static List<String> fileToLines(String filename) {
            List<String> lines = new LinkedList<String>();
            String line = "";
            try(BufferedReader in = new BufferedReader(new FileReader(filename))) {
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
            try(BufferedReader in = new BufferedReader(new StringReader(filename))) {
                    while ((line = in.readLine()) != null) {
                            lines.add(line);
                    }
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return lines;
    }
    public static JsfTagsCollector  jsft = new JsfTagsCollector();
    
    public static String parseFile(File f) throws IOException{
		//System.err.println(f.getAbsolutePath());
    	StringSink s = new StringSink();
		TokenCollectorSink collector = new TokenCollectorSink();
		
		JspTaglibsCollector taglibs = new JspTaglibsCollector();
		
		TokenChainSink sink = new TokenChainSink();
		sink.addVisitor(collector);
		sink.addVisitor(taglibs);
		sink.addVisitor(jsft);

		MixedHtmlParser.processFile(f.getAbsolutePath(), sink);

		
		for (TokenPart token : collector.getCollected()) {
			TokenType.visitType(s, token);
		}
		
		return s.text();
	}
	
    public static String parseReplaceFile(File f) throws IOException{
    	ScriptedSink s = new ScriptedSink();
		MixedHtmlParser.processFile(f.getAbsolutePath(), s);
		return s.text();
	}
    
	public static void diff(String file, String parsed){
		  List<String> original = fileToLines(file);
	        List<String> revised  = stringToLines(parsed);
	        
	        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
	        Patch patch = DiffUtils.diff(original, revised);
	        List<Delta> deltas = patch.getDeltas();
	        if(deltas != null && deltas.size() >0) {
	        	System.out.println("Diff for file:"+ file);
	        }
	        for (Delta delta: deltas) {
	        	printDelta(delta);
	        }
	}
	
	public static void printDelta(Delta delta){

		System.out.println(delta.getClass().getSimpleName() + ", position: " + delta.getOriginal().getPosition() + ", lines: \n"
	                + delta.getOriginal().getLines() + "\n to \n" + delta.getRevised().getLines());
	}
}
