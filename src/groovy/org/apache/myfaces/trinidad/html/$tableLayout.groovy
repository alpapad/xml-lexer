package org.apache.myfaces.trinidad.html

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [cellSpacing, rendered, borderWidth, width, id, styleClass, inlineStyle, cellPadding] **/

public class $tableLayout extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		return TagBuilder.start(token).tag("table")
			.at("id","jsf:id")
			.at("rendered","jsf:rendered")
			.at("styleClass","class")
			.at("inlineStyle","style")
			.at("cellSpacing","cellspacing")
			.at("cellPadding","cellpadding")
			.at("borderWidth","border")
			.at("width","width")
			.get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		return "</table>";//super.end(token);
	}
}