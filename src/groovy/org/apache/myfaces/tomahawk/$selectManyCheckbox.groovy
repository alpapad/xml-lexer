package org.apache.myfaces.tomahawk

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;
import com.aktarma.xml.tokenizer.scripting.TagBuilder;


/** [layout, id, styleClass, value] **/

public class $selectManyCheckbox extends NsTagVisitor {


	def layouts = [spread:"grid" ,lineDirection :"lineDirection", pageDirection :"pageDirection"]
	
	
	public String start(NsTagStartToken token) {
		return TagBuilder.start(token).tag("p:selectManyCheckbox").at("id").at("value").at("styleClass").at("layout",layouts).a("columns", "3").get();
		//return super.start(token);
	}


	public String end(NsTagEndToken token) {
		return TagBuilder.start(token).tag("p:selectManyCheckbox");//super.end(token);
	}
}