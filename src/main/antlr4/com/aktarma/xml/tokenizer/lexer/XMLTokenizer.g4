/*
 [The "BSD licence"]
 Copyright (c) 2013 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/** XML lexer derived from ANTLR v4 ref guide book example */
lexer grammar XMLTokenizer;


CDATA               : '<![CDATA[' (.*?) ']]>' ;
SCRIPT               : '<script' (.*?) '</script>' ;
CSS               : '<style' (.*?) '</style>' ;

JSP_COMMENT			: (('<%--' (.*?) '--%>')			  				  
			  		  |
			  		  ('<%' WS* '/' (.*?) '/' WS* '%>'));
 	
JSP_CODE_BLOCK		: '<%=' (.*?) '%>' ;

COMMENT_EMPTY       : '<!-->';
COMMENT_COND_START  : (('<![') | ('<!--' WS* [\[]) |('<!--' WS* '<!['))   -> more, pushMode(COND_COMMENTS);
COMMENT_START       : '<!--' WS* ~[\[]                					  -> more, pushMode(COMMENTS);
	


DTD         :   '<!'~('-'|'[') .*? '>' ; 
ENTITYREF   :   '&' IDENT ';' ;
CHARREF     :   '&#' DIGIT+ ';'
            |   '&#x' HEXDIGIT+ ';'
            ;
SEA_WS      :   (' '|'\t'|'\r'? '\n')+ ;


TAG_START   :   '<' IDENT				               -> pushMode(INSIDE) ;
JSPTAGLIB	:   '<%@' SPACE							   -> pushMode(INSIDE) ;
JSPPAGE		:   '<%@page' SPACE						   -> pushMode(INSIDE) ;
JSPIMPORT	:   '<%@import' SPACE					   -> pushMode(INSIDE) ;
JSPINCLUDE	:   '<%@include' SPACE					   -> pushMode(INSIDE) ;


TAG_END		:   '</' IDENT 							   -> pushMode(INSIDE) ;
XML_TAG     :   '<?xml' SPACE                          -> pushMode(INSIDE) ;
SPECIAL_TAG :   '<?' IDENT                             -> more, pushMode(PROC_INSTR) ;

TEXT        :   ~[<&]+ ;        // match any 16 bit char other than < and &


mode COMMENTS;
COMMENT     : '-->'                                         -> popMode ;
CTEXT       : .                                             -> more ; 

mode COND_COMMENTS;
COND_COMMENT    : (('-->')|(']>'))                          -> popMode ;
COND_CTEXT      : .                                         -> more ;

// ----------------- Everything INSIDE of a tag ---------------------
mode INSIDE;

TAG_CLOSE       :   '>'                                    -> popMode ;
JSPTAGLIB_CLOSE :	'%>'                        		   -> popMode ;
XML_TAG_CLOSE   :  '?>'                                    -> popMode ; // close <?xml...?>
TAG_SELF_CLOSE  :   '/>'                                   -> popMode ;
EQUALS          :   WS* '=' WS*		                       -> pushMode(ATTRVAL_MODE);

ATTR            :   NameStartChar NameChar*;
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
IDENT           :   NameStartChar NameChar* ;

fragment
SPACES          :   (' '|'\t'|'\r'? '\n')* ;

fragment
WS              : [ \t\r\n];

fragment
HEXDIGIT        :   [a-fA-F0-9] ;

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

// ----------------- Handle <? ... ?> ---------------------
mode PROC_INSTR;
PROCESSING_INSTR    :   '?>'                    -> popMode ; // close <?...?>
IGNORE              :   .                       -> more ;


