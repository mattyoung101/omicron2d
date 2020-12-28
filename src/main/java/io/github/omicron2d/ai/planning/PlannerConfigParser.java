// Generated from /home/matt/workspace/omicron2d/src/main/antlr/PlannerConfig.g4 by ANTLR 4.9
package io.github.omicron2d.ai.planning;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PlannerConfigParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, COLON=2, LBRACKET=3, RBRACKET=4, SEMICOLON=5, ARROW=6, NOT=7, 
		LITERAL=8, WS=9, COMMENT=10;
	public static final int
		RULE_condition = 0, RULE_conditionList = 1, RULE_preConditions = 2, RULE_postConditions = 3, 
		RULE_actionName = 4, RULE_action = 5, RULE_document = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"condition", "conditionList", "preConditions", "postConditions", "actionName", 
			"action", "document"
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

	@Override
	public String getGrammarFileName() { return "PlannerConfig.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PlannerConfigParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(PlannerConfigParser.LITERAL, 0); }
		public TerminalNode NOT() { return getToken(PlannerConfigParser.NOT, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(14);
				match(NOT);
				}
			}

			setState(17);
			match(LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionListContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(PlannerConfigParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PlannerConfigParser.RBRACKET, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterConditionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitConditionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitConditionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionListContext conditionList() throws RecognitionException {
		ConditionListContext _localctx = new ConditionListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_conditionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			match(LBRACKET);
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NOT || _la==LITERAL) {
				{
				{
				setState(20);
				condition();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(26);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreConditionsContext extends ParserRuleContext {
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public PreConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preConditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterPreConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitPreConditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitPreConditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreConditionsContext preConditions() throws RecognitionException {
		PreConditionsContext _localctx = new PreConditionsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_preConditions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			conditionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostConditionsContext extends ParserRuleContext {
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public PostConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postConditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterPostConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitPostConditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitPostConditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostConditionsContext postConditions() throws RecognitionException {
		PostConditionsContext _localctx = new PostConditionsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_postConditions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			conditionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionNameContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(PlannerConfigParser.LITERAL, 0); }
		public ActionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterActionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitActionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitActionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionNameContext actionName() throws RecognitionException {
		ActionNameContext _localctx = new ActionNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_actionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public ActionNameContext actionName() {
			return getRuleContext(ActionNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PlannerConfigParser.COLON, 0); }
		public PreConditionsContext preConditions() {
			return getRuleContext(PreConditionsContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(PlannerConfigParser.ARROW, 0); }
		public PostConditionsContext postConditions() {
			return getRuleContext(PostConditionsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(PlannerConfigParser.SEMICOLON, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			actionName();
			setState(36);
			match(COLON);
			setState(37);
			preConditions();
			setState(38);
			match(ARROW);
			setState(39);
			postConditions();
			setState(40);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocumentContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PlannerConfigParser.EOF, 0); }
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).enterDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlannerConfigListener ) ((PlannerConfigListener)listener).exitDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlannerConfigVisitor ) return ((PlannerConfigVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				action();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(47);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\64\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\5\2\22\n\2\3\2\3\2\3"+
		"\3\3\3\7\3\30\n\3\f\3\16\3\33\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\6\b.\n\b\r\b\16\b/\3\b\3\b\3\b\2\2\t"+
		"\2\4\6\b\n\f\16\2\2\2/\2\21\3\2\2\2\4\25\3\2\2\2\6\36\3\2\2\2\b \3\2\2"+
		"\2\n\"\3\2\2\2\f$\3\2\2\2\16-\3\2\2\2\20\22\7\t\2\2\21\20\3\2\2\2\21\22"+
		"\3\2\2\2\22\23\3\2\2\2\23\24\7\n\2\2\24\3\3\2\2\2\25\31\7\5\2\2\26\30"+
		"\5\2\2\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\34"+
		"\3\2\2\2\33\31\3\2\2\2\34\35\7\6\2\2\35\5\3\2\2\2\36\37\5\4\3\2\37\7\3"+
		"\2\2\2 !\5\4\3\2!\t\3\2\2\2\"#\7\n\2\2#\13\3\2\2\2$%\7\3\2\2%&\5\n\6\2"+
		"&\'\7\4\2\2\'(\5\6\4\2()\7\b\2\2)*\5\b\5\2*+\7\7\2\2+\r\3\2\2\2,.\5\f"+
		"\7\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\2"+
		"\2\3\62\17\3\2\2\2\5\21\31/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}