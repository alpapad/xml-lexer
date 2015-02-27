package org.apache.myfaces.tomahawk

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [id, forceId] **/

public class $div extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		return TagBuilder.start(token).tag("div").at("id","jsf:id").get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		return TagBuilder.start(token).tag("div");//super.end(token);
	}
}