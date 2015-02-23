// Generated from MiniTokenizer.g4 by ANTLR 4.5
package com.aktarma.xml.tokenizer.lexer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniTokenizer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SCRIPT_OPEN=1, SCRIPT_CLOSE=2, STYLE_OPEN=3, STYLE_CLOSE=4, TEXT=5, TAG_CLOSE=6, 
		TAG_SELF_CLOSE=7, EQUALS=8, ATTR=9, SPACE=10, ATTRVAL=11;
	public static final int INSIDE = 1;
	public static final int ATTRVAL_MODE = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "INSIDE", "ATTRVAL_MODE"
	};

	public static final String[] ruleNames = {
		"SCRIPT_OPEN", "SCRIPT_CLOSE", "STYLE_OPEN", "STYLE_CLOSE", "TEXT", "TAG_CLOSE", 
		"TAG_SELF_CLOSE", "EQUALS", "ATTR", "SPACE", "ATTRVAL", "ATT_SINGLE", 
		"ATT_DOUBLE", "ATT_IGNORE", "ATT_JSP", "ATT_EL", "ATT_ESCAPED_HASH", "ATT_HTML", 
		"WS", "DIGIT", "NameChar", "NameStartChar"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'<script'", "'</script'", "'<style'", "'</style'", null, "'>'", 
		"'/>'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SCRIPT_OPEN", "SCRIPT_CLOSE", "STYLE_OPEN", "STYLE_CLOSE", "TEXT", 
		"TAG_CLOSE", "TAG_SELF_CLOSE", "EQUALS", "ATTR", "SPACE", "ATTRVAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MiniTokenizer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniTokenizer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\r\u00f5\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\6\6[\n\6\r\6\16\6\\\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\7\ti\n\t\f\t\16\tl\13\t\3\t\3\t\7\tp\n\t\f\t\16\ts\13\t\3\t\3\t\3"+
		"\n\3\n\7\ny\n\n\f\n\16\n|\13\n\3\13\6\13\177\n\13\r\13\16\13\u0080\3\f"+
		"\7\f\u0084\n\f\f\f\16\f\u0087\13\f\3\f\3\f\3\f\7\f\u008c\n\f\f\f\16\f"+
		"\u008f\13\f\3\f\3\f\3\f\3\f\7\f\u0095\n\f\f\f\16\f\u0098\13\f\3\f\3\f"+
		"\6\f\u009c\n\f\r\f\16\f\u009d\3\f\6\f\u00a1\n\f\r\f\16\f\u00a2\5\f\u00a5"+
		"\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00b1\n\17\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u00b8\n\20\f\20\16\20\u00bb\13\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\7\21\u00c4\n\21\f\21\16\21\u00c7\13\21\3\21"+
		"\3\21\3\21\3\21\3\21\7\21\u00ce\n\21\f\21\16\21\u00d1\13\21\3\21\5\21"+
		"\u00d4\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\7\23"+
		"\u00e1\n\23\f\23\16\23\u00e4\13\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\5\26\u00f1\n\26\3\27\5\27\u00f4\n\27\7\\\u00b9\u00c5"+
		"\u00cf\u00e2\2\30\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\13\27\f\31\r"+
		"\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\2-\2/\2\5\2\3\4\n\6\2\"\"$$))>>\4\2)"+
		")>>\4\2$$>>\5\2\13\f\17\17\"\"\3\2\62;\4\2/\60aa\5\2\u00b9\u00b9\u0302"+
		"\u0371\u2041\u2042\n\2<<C\\c|\u2072\u2191\u2c02\u2ff1\u3003\ud801\uf902"+
		"\ufdd1\ufdf2\uffff\u0100\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\3\17\3\2\2\2\3\21\3\2\2\2\3\23\3\2\2\2\3\25\3\2\2\2\3"+
		"\27\3\2\2\2\4\31\3\2\2\2\5\61\3\2\2\2\7;\3\2\2\2\tF\3\2\2\2\13O\3\2\2"+
		"\2\rZ\3\2\2\2\17^\3\2\2\2\21b\3\2\2\2\23j\3\2\2\2\25v\3\2\2\2\27~\3\2"+
		"\2\2\31\u0085\3\2\2\2\33\u00a8\3\2\2\2\35\u00aa\3\2\2\2\37\u00b0\3\2\2"+
		"\2!\u00b2\3\2\2\2#\u00d3\3\2\2\2%\u00d5\3\2\2\2\'\u00de\3\2\2\2)\u00e8"+
		"\3\2\2\2+\u00ea\3\2\2\2-\u00f0\3\2\2\2/\u00f3\3\2\2\2\61\62\7>\2\2\62"+
		"\63\7u\2\2\63\64\7e\2\2\64\65\7t\2\2\65\66\7k\2\2\66\67\7r\2\2\678\7v"+
		"\2\289\3\2\2\29:\b\2\2\2:\6\3\2\2\2;<\7>\2\2<=\7\61\2\2=>\7u\2\2>?\7e"+
		"\2\2?@\7t\2\2@A\7k\2\2AB\7r\2\2BC\7v\2\2CD\3\2\2\2DE\b\3\2\2E\b\3\2\2"+
		"\2FG\7>\2\2GH\7u\2\2HI\7v\2\2IJ\7{\2\2JK\7n\2\2KL\7g\2\2LM\3\2\2\2MN\b"+
		"\4\2\2N\n\3\2\2\2OP\7>\2\2PQ\7\61\2\2QR\7u\2\2RS\7v\2\2ST\7{\2\2TU\7n"+
		"\2\2UV\7g\2\2VW\3\2\2\2WX\b\5\2\2X\f\3\2\2\2Y[\13\2\2\2ZY\3\2\2\2[\\\3"+
		"\2\2\2\\]\3\2\2\2\\Z\3\2\2\2]\16\3\2\2\2^_\7@\2\2_`\3\2\2\2`a\b\7\3\2"+
		"a\20\3\2\2\2bc\7\61\2\2cd\7@\2\2de\3\2\2\2ef\b\b\3\2f\22\3\2\2\2gi\5)"+
		"\24\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mq\7"+
		"?\2\2np\5)\24\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3"+
		"\2\2\2tu\b\t\4\2u\24\3\2\2\2vz\5/\27\2wy\5-\26\2xw\3\2\2\2y|\3\2\2\2z"+
		"x\3\2\2\2z{\3\2\2\2{\26\3\2\2\2|z\3\2\2\2}\177\5)\24\2~}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\30\3\2\2\2\u0082\u0084"+
		"\5)\24\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085"+
		"\u0086\3\2\2\2\u0086\u00a4\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008d\7$"+
		"\2\2\u0089\u008c\5\35\16\2\u008a\u008c\5\37\17\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u00a5\7$\2\2\u0091"+
		"\u0096\7)\2\2\u0092\u0095\5\33\r\2\u0093\u0095\5\37\17\2\u0094\u0092\3"+
		"\2\2\2\u0094\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u00a5\7)"+
		"\2\2\u009a\u009c\n\2\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u00a1\5)"+
		"\24\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u0088\3\2\2\2\u00a4\u0091\3\2"+
		"\2\2\u00a4\u009b\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\f\3\2\u00a7"+
		"\32\3\2\2\2\u00a8\u00a9\n\3\2\2\u00a9\34\3\2\2\2\u00aa\u00ab\n\4\2\2\u00ab"+
		"\36\3\2\2\2\u00ac\u00b1\5!\20\2\u00ad\u00b1\5#\21\2\u00ae\u00b1\5%\22"+
		"\2\u00af\u00b1\5\'\23\2\u00b0\u00ac\3\2\2\2\u00b0\u00ad\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b0\u00af\3\2\2\2\u00b1 \3\2\2\2\u00b2\u00b3\7>\2\2\u00b3"+
		"\u00b4\7\'\2\2\u00b4\u00b5\7?\2\2\u00b5\u00b9\3\2\2\2\u00b6\u00b8\13\2"+
		"\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00ba\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bd\7\'"+
		"\2\2\u00bd\u00be\7@\2\2\u00be\"\3\2\2\2\u00bf\u00c0\7%\2\2\u00c0\u00c1"+
		"\7}\2\2\u00c1\u00c5\3\2\2\2\u00c2\u00c4\13\2\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c8\3\2"+
		"\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00d4\7\177\2\2\u00c9\u00ca\7&\2\2\u00ca"+
		"\u00cb\7}\2\2\u00cb\u00cf\3\2\2\2\u00cc\u00ce\13\2\2\2\u00cd\u00cc\3\2"+
		"\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0"+
		"\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00d4\7\177\2\2\u00d3\u00bf\3"+
		"\2\2\2\u00d3\u00c9\3\2\2\2\u00d4$\3\2\2\2\u00d5\u00d6\7>\2\2\u00d6\u00d7"+
		"\7\'\2\2\u00d7\u00d8\7?\2\2\u00d8\u00d9\7$\2\2\u00d9\u00da\7%\2\2\u00da"+
		"\u00db\7$\2\2\u00db\u00dc\7\'\2\2\u00dc\u00dd\7@\2\2\u00dd&\3\2\2\2\u00de"+
		"\u00e2\7>\2\2\u00df\u00e1\13\2\2\2\u00e0\u00df\3\2\2\2\u00e1\u00e4\3\2"+
		"\2\2\u00e2\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e5\u00e6\7\61\2\2\u00e6\u00e7\7@\2\2\u00e7(\3\2\2\2"+
		"\u00e8\u00e9\t\5\2\2\u00e9*\3\2\2\2\u00ea\u00eb\t\6\2\2\u00eb,\3\2\2\2"+
		"\u00ec\u00f1\5/\27\2\u00ed\u00f1\t\7\2\2\u00ee\u00f1\5+\25\2\u00ef\u00f1"+
		"\t\b\2\2\u00f0\u00ec\3\2\2\2\u00f0\u00ed\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00ef\3\2\2\2\u00f1.\3\2\2\2\u00f2\u00f4\t\t\2\2\u00f3\u00f2\3\2\2\2"+
		"\u00f4\60\3\2\2\2\32\2\3\4\\jqz\u0080\u0085\u008b\u008d\u0094\u0096\u009d"+
		"\u00a2\u00a4\u00b0\u00b9\u00c5\u00cf\u00d3\u00e2\u00f0\u00f3\5\7\3\2\6"+
		"\2\2\7\4\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}