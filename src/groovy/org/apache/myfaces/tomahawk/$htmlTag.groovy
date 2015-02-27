package org.apache.myfaces.tomahawk

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [rendered, style, id, styleClass, forceId, value] **/

public class $htmlTag extends NsTagVisitor {

	
	
	//static List<String>tags = []
	
	public String start(NsTagStartToken token) {
		TagBuilder builder = TagBuilder.start(token);
		//tags.add(0,builder.val("value"));
		
		return builder.tag(builder.val("value")).at("id","jsf:id").at("rendered","jsf:rendered").at("style").at("styleClass","class").get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		TagBuilder p = TagBuilder.start(token.getSibling());
		
		return TagBuilder.start(token).tag(p.val("value")).get();
		//return builder.tag(builder.val("value"));
		//return super.end(token);
	}
}