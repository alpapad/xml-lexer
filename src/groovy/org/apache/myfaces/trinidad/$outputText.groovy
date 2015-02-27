package org.apache.myfaces.trinidad

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [rendered, id, styleClass, value, escape, inlineStyle] **/

public class $outputText extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		return TagBuilder.start(token).tag("h:outputText").at("id").at("rendered").at("styleClass").at("value").at("escape").at("inlineStyle","style").get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		return TagBuilder.start(token).tag("h:outputText").get();
		//return super.end(token);
	}
}