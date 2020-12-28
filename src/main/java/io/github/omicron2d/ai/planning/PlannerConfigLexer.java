// Generated from /home/matt/workspace/omicron2d/src/main/antlr/PlannerConfig.g4 by ANTLR 4.9
package io.github.omicron2d.ai.planning;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PlannerConfigLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, COLON=2, LBRACKET=3, RBRACKET=4, SEMICOLON=5, ARROW=6, NOT=7, 
		LITERAL=8, WS=9, COMMENT=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "COLON", "LBRACKET", "RBRACKET", "SEMICOLON", "ARROW", "NOT", 
			"LITERAL", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'action'", "':'", "'['", "']'", "';'", "'->'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "COLON", "LBRACKET", "RBRACKET", "SEMICOLON", "ARROW", "NOT", 
			"LITERAL", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public PlannerConfigLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PlannerConfig.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fB\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\b\3\b\3\t\6\t-\n\t\r\t\16\t.\3\n\6\n\62\n\n\r\n\16\n\63\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\7\13<\n\13\f\13\16\13?\13\13\3\13\3\13\2\2\f\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\3\2\5\5\2\62;C\\c|\5\2\13"+
		"\f\17\17\"\"\4\2\f\f\17\17\2D\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\3\27\3\2\2\2\5\36\3\2\2\2\7 \3\2\2\2\t\"\3\2\2\2\13$\3"+
		"\2\2\2\r&\3\2\2\2\17)\3\2\2\2\21,\3\2\2\2\23\61\3\2\2\2\25\67\3\2\2\2"+
		"\27\30\7c\2\2\30\31\7e\2\2\31\32\7v\2\2\32\33\7k\2\2\33\34\7q\2\2\34\35"+
		"\7p\2\2\35\4\3\2\2\2\36\37\7<\2\2\37\6\3\2\2\2 !\7]\2\2!\b\3\2\2\2\"#"+
		"\7_\2\2#\n\3\2\2\2$%\7=\2\2%\f\3\2\2\2&\'\7/\2\2\'(\7@\2\2(\16\3\2\2\2"+
		")*\7#\2\2*\20\3\2\2\2+-\t\2\2\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2"+
		"\2/\22\3\2\2\2\60\62\t\3\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2"+
		"\63\64\3\2\2\2\64\65\3\2\2\2\65\66\b\n\2\2\66\24\3\2\2\2\678\7\61\2\2"+
		"89\7\61\2\29=\3\2\2\2:<\n\4\2\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2"+
		"\2>@\3\2\2\2?=\3\2\2\2@A\b\13\2\2A\26\3\2\2\2\6\2.\63=\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}