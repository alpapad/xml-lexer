package com.aktarma.xml.tokenizer.tokens.parts;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import com.aktarma.xml.tokenizer.tokens.IElement;
import com.aktarma.xml.tokenizer.tokens.TokenType;

public class ElAttritbutePart extends AbstractElementPart {

    //public static final Pattern UNESCAPE_UNICODE_PATTERN = Pattern.compile("\\&\\#(x?)(.+);");

    private String name = null;
    private String key = null;
    
    private String operator = null;
    private String value = null;
    
    public ElAttritbutePart(int line, int charpos, IElement parent, String name, String operator, String value) {
        super(line, charpos, parent, TokenType.ATTRVAL);
        this.name = name;
        this.operator = operator;
        this.value = value;//unescape(value);
        
        this.key = name.trim().toLowerCase();
    }

    public void setValue(String value) {
        this.value =  value;//unescape(value);
    }

    public String getName() {
        return name;
    }

    public String getSimpleName(){
    	if(name != null) {
    		return name.trim();
    	}
    	return "";
    } 
    
    public String getValue() {
        return value;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getKey() {
        return key;
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String eq) {
		this.operator = eq;
	}

	@Override
	public String toString() {
		return "AttrValPart [name=" + name + ", key=" + key + ", op=" + operator + ", value=" + value + ", line=" + line + ", charpos=" + charpos + ", type=" + type + "]";
	}

//    private static String unescape(String orig) {
//        if(orig == null) {
//            return "";
//        }
//        String s = orig.trim();
//        if(s.startsWith("\"")){
//            s = s.replace("\"","");
//
//        } else  if(s.startsWith("'")){
//            s = s.replace("'","");
//        }
//        s = s.replace("&amp;", "&").replace("&gt;", ">").replace("&lt;", "<").replace("&apos;", "'").replace("&quot;", "\"");
//
//        StringBuffer sb = new StringBuffer();
//
//        Matcher matcher = UNESCAPE_UNICODE_PATTERN.matcher(s);
//        int end = 0;
//        while (matcher.find()) {
//            boolean isHex = matcher.group(1).equals("x");
//            String unicodeCode = matcher.group(2);
//
//            int base = isHex ? 16 : 10;
//            int i = Integer.valueOf(unicodeCode, base).intValue();
//            char[] c = Character.toChars(i);
//            sb.append(s.substring(end, matcher.start()));
//            end = matcher.end();
//            sb.append(c);
//        }
//        sb.append(s.substring(end, s.length()));
//
//        return sb.toString();
//    }


}
