package org.apache.myfaces.trinidad

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.tokens.parts.ElAttritbutePart;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;

/** [var, items, varStatus] **/

public class $forEach extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		//return super.start(token);
		return TagBuilder.start(token).tag("ui:repeat").at("var").at("items","value").at("varStatus").get();
	}


	public String end(NsTagEndToken token) {
		return TagBuilder.start(token).tag("ui:repeat").get();
		//return super.end(token);//TagBuilder.start(token).tag("ui:repeat").get();//"</ui:repeat>";
	}
}