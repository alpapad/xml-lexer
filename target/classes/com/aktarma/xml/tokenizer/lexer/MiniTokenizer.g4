
/** XML lexer derived from ANTLR v4 ref guide book example */
lexer grammar MiniTokenizer;


SCRIPT_OPEN         :   '<script' 				               -> pushMode(INSIDE) ;
SCRIPT_CLOSE		:   '</script' 							   -> pushMode(INSIDE) ;
STYLE_OPEN          :   '<style' 				               -> pushMode(INSIDE) ;
STYLE_CLOSE			:   '</style' 							   -> pushMode(INSIDE) ;
TEXT        :   (.+?) ;        // match any 16 bit char


// ----------------- Everything INSIDE of a tag ---------------------
mode INSIDE;
TAG_CLOSE       :   '>'                                    -> popMode ;
TAG_SELF_CLOSE  :   '/>'                                   -> popMode ;
EQUALS          :   WS* '=' WS*                            -> pushMode(ATTRVAL_MODE);

ATTR            :   NameStartChar NameChar* ;
SPACE           :   WS+;

mode ATTRVAL_MODE;
ATTRVAL         :   
				WS*
				(
					('"' (ATT_DOUBLE|ATT_IGNORE)* '"') 
					| 
					('\'' (ATT_SINGLE|ATT_IGNORE)* '\'')
					|
					(~[<"' ]+ WS+)
				) -> popMode;




fragment
ATT_SINGLE:	~[<'];

fragment
ATT_DOUBLE:	~[<"];

fragment
ATT_IGNORE: ATT_JSP |ATT_EL|ATT_ESCAPED_HASH|ATT_HTML;

fragment
ATT_JSP: '<%=' (.*?) '%>' ;

fragment
ATT_EL: ('#{' (.*?) '}')| '${' (.*?) '}';

fragment
ATT_ESCAPED_HASH: '<%="#"%>';

fragment
ATT_HTML: '<' (.*?) '/>';


fragment
WS              : [ \t\r\n];

fragment
DIGIT           :   [0-9] ;

fragment
NameChar        :   NameStartChar
                |   '-' | '_' | '.' | DIGIT 
                |   '\u00B7'
                |   '\u0300'..'\u036F'
                |   '\u203F'..'\u2040'
            ;
            
fragment
NameStartChar
            :   [:a-zA-Z]
            |   '\u2070'..'\u218F' 
            |   '\u2C00'..'\u2FEF' 
            |   '\u3001'..'\uD7FF' 
            |   '\uF900'..'\uFDCF' 
            |   '\uFDF0'..'\uFFFD'
            ;