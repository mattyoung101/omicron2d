// Generated from /home/matt/workspace/omicron2d/src/main/antlr/ServerMessage.g4 by ANTLR 4.8
package io.github.omicron2d.communication.messages;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ServerMessageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, INTEGER=6, FLOAT=7, DIGITS=8, 
		LPAREN=9, RPAREN=10, QUOTE=11, PERIOD=12, MESSAGE_SENDER=13, PLAYMODE=14, 
		PLAYER_MESSAGE=15, REF_MESSAGE=16, WHITESPACE=17;
	public static final int
		RULE_seeMessage = 0, RULE_time = 1, RULE_direction = 2, RULE_sender = 3, 
		RULE_sayMessage = 4, RULE_hearMessage = 5, RULE_unum = 6, RULE_playmode = 7, 
		RULE_side = 8, RULE_initMessage = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"seeMessage", "time", "direction", "sender", "sayMessage", "hearMessage", 
			"unum", "playmode", "side", "initMessage"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'see'", "'hear'", "'l'", "'r'", "'init'", null, null, null, "'('", 
			"')'", "'\"'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "INTEGER", "FLOAT", "DIGITS", "LPAREN", 
			"RPAREN", "QUOTE", "PERIOD", "MESSAGE_SENDER", "PLAYMODE", "PLAYER_MESSAGE", 
			"REF_MESSAGE", "WHITESPACE"
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
	public String getGrammarFileName() { return "ServerMessage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ServerMessageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SeeMessageContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(ServerMessageParser.EOF, 0); }
		public SeeMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seeMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSeeMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSeeMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSeeMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeeMessageContext seeMessage() throws RecognitionException {
		SeeMessageContext _localctx = new SeeMessageContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_seeMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(LPAREN);
			setState(21);
			match(T__0);
			setState(22);
			time();
			setState(23);
			match(RPAREN);
			setState(24);
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

	public static class TimeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(INTEGER);
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

	public static class DirectionContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public DirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_direction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectionContext direction() throws RecognitionException {
		DirectionContext _localctx = new DirectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_direction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(FLOAT);
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

	public static class SenderContext extends ParserRuleContext {
		public DirectionContext direction() {
			return getRuleContext(DirectionContext.class,0);
		}
		public TerminalNode MESSAGE_SENDER() { return getToken(ServerMessageParser.MESSAGE_SENDER, 0); }
		public SenderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sender; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSender(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSender(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSender(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SenderContext sender() throws RecognitionException {
		SenderContext _localctx = new SenderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_sender);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				direction();
				}
				break;
			case MESSAGE_SENDER:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				match(MESSAGE_SENDER);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SayMessageContext extends ParserRuleContext {
		public TerminalNode PLAYER_MESSAGE() { return getToken(ServerMessageParser.PLAYER_MESSAGE, 0); }
		public TerminalNode REF_MESSAGE() { return getToken(ServerMessageParser.REF_MESSAGE, 0); }
		public SayMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sayMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSayMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSayMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSayMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SayMessageContext sayMessage() throws RecognitionException {
		SayMessageContext _localctx = new SayMessageContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sayMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_la = _input.LA(1);
			if ( !(_la==PLAYER_MESSAGE || _la==REF_MESSAGE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class HearMessageContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public SenderContext sender() {
			return getRuleContext(SenderContext.class,0);
		}
		public SayMessageContext sayMessage() {
			return getRuleContext(SayMessageContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(ServerMessageParser.EOF, 0); }
		public HearMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hearMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterHearMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitHearMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitHearMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HearMessageContext hearMessage() throws RecognitionException {
		HearMessageContext _localctx = new HearMessageContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_hearMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(LPAREN);
			setState(37);
			match(T__1);
			setState(38);
			time();
			setState(39);
			sender();
			setState(40);
			sayMessage();
			setState(41);
			match(RPAREN);
			setState(42);
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

	public static class UnumContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public UnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterUnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitUnum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitUnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnumContext unum() throws RecognitionException {
		UnumContext _localctx = new UnumContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(INTEGER);
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

	public static class PlaymodeContext extends ParserRuleContext {
		public TerminalNode PLAYMODE() { return getToken(ServerMessageParser.PLAYMODE, 0); }
		public PlaymodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playmode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterPlaymode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitPlaymode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitPlaymode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlaymodeContext playmode() throws RecognitionException {
		PlaymodeContext _localctx = new PlaymodeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_playmode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(PLAYMODE);
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

	public static class SideContext extends ParserRuleContext {
		public SideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_side; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSide(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSide(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SideContext side() throws RecognitionException {
		SideContext _localctx = new SideContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class InitMessageContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public SideContext side() {
			return getRuleContext(SideContext.class,0);
		}
		public UnumContext unum() {
			return getRuleContext(UnumContext.class,0);
		}
		public PlaymodeContext playmode() {
			return getRuleContext(PlaymodeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(ServerMessageParser.EOF, 0); }
		public InitMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterInitMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitInitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitInitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitMessageContext initMessage() throws RecognitionException {
		InitMessageContext _localctx = new InitMessageContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_initMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(LPAREN);
			setState(51);
			match(T__4);
			setState(52);
			side();
			setState(53);
			unum();
			setState(54);
			playmode();
			setState(55);
			match(RPAREN);
			setState(56);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\23=\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\5\5#\n\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\4\3\2\21\22"+
		"\3\2\5\6\2\63\2\26\3\2\2\2\4\34\3\2\2\2\6\36\3\2\2\2\b\"\3\2\2\2\n$\3"+
		"\2\2\2\f&\3\2\2\2\16.\3\2\2\2\20\60\3\2\2\2\22\62\3\2\2\2\24\64\3\2\2"+
		"\2\26\27\7\13\2\2\27\30\7\3\2\2\30\31\5\4\3\2\31\32\7\f\2\2\32\33\7\2"+
		"\2\3\33\3\3\2\2\2\34\35\7\b\2\2\35\5\3\2\2\2\36\37\7\t\2\2\37\7\3\2\2"+
		"\2 #\5\6\4\2!#\7\17\2\2\" \3\2\2\2\"!\3\2\2\2#\t\3\2\2\2$%\t\2\2\2%\13"+
		"\3\2\2\2&\'\7\13\2\2\'(\7\4\2\2()\5\4\3\2)*\5\b\5\2*+\5\n\6\2+,\7\f\2"+
		"\2,-\7\2\2\3-\r\3\2\2\2./\7\b\2\2/\17\3\2\2\2\60\61\7\20\2\2\61\21\3\2"+
		"\2\2\62\63\t\3\2\2\63\23\3\2\2\2\64\65\7\13\2\2\65\66\7\7\2\2\66\67\5"+
		"\22\n\2\678\5\16\b\289\5\20\t\29:\7\f\2\2:;\7\2\2\3;\25\3\2\2\2\3\"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}