package org.apache.myfaces.trinidad.html

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [columnSpan, rendered, width, header, valign, id, styleClass, halign, inlineStyle] **/

public class $cellFormat extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		
		TagBuilder b = TagBuilder.start(token);
		
		String  tag = "td"
		if(b.val("header") == "true") {
			tag = "th";
		}
		
		
		return TagBuilder.start(token).tag(tag)
			.at("id","jsf:id")
			.at("rendered","jsf:rendered")
			.at("columnSpan","colspan")
			.at("width","rowspan")
			.at("header","headers")
			.at("valign","valign")
			.at("halign","halign")
			.at("styleClass","class")
			.at("inlineStyle","style")
			.at("varStatus").get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		TagBuilder b = TagBuilder.start(token.getSibling());
		
		String  tag = "td"
		if(b.val("header") == "true") {
			tag = "th";
		}
		
		return "</" + tag +">";//super.end(token);
	}
}