package core.jsf.jsf.core

import com.aktarma.xml.tokenizer.scripting.NsTagVisitor;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagEndToken;
import com.aktarma.xml.tokenizer.tokens.elements.NsTagStartToken;


/** [escape] **/

public class $verbatim extends NsTagVisitor {


	public String start(NsTagStartToken token) {
		return "";//super.start(token);//"";//
	}


	public String end(NsTagEndToken token) {
		return "";//super.end(token);// "";//
	}
}