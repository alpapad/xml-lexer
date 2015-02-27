package org.apache.myfaces.trinidad.html

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [rendered, width, id, styleClass, halign, inlineStyle] **/

public class $rowLayout extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		return TagBuilder.start(token).tag("tr")
			.at("id","jsf:id")
			.at("rendered","jsf:rendered")
			.at("width","width")
			.at("styleClass","class")
			.at("halign","halign")
			.at("inlineStyle", "style")
			.get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		return "<tr>";//super.end(token);
	}
}