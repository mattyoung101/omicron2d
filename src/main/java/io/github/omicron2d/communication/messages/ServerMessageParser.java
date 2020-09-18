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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, INTEGER=13, FLOAT=14, DIGITS=15, LPAREN=16, 
		RPAREN=17, QUOTE=18, PERIOD=19, MESSAGE_SENDER=20, PLAYMODE=21, QUOTED_TEXT=22, 
		FLAG_NAME=23, GOAL_NAME=24, LINE_NAME=25, REF_MESSAGE=26, WHITESPACE=27;
	public static final int
		RULE_teamName = 0, RULE_playerName = 1, RULE_playerBehind = 2, RULE_distance = 3, 
		RULE_seeDirection = 4, RULE_distChange = 5, RULE_dirChange = 6, RULE_headFaceDir = 7, 
		RULE_bodyFaceDir = 8, RULE_flagName = 9, RULE_flagBehind = 10, RULE_goalBehind = 11, 
		RULE_goalName = 12, RULE_ballName = 13, RULE_ballBehind = 14, RULE_lineName = 15, 
		RULE_objectName = 16, RULE_objectContents = 17, RULE_seeObject = 18, RULE_seeMessage = 19, 
		RULE_time = 20, RULE_direction = 21, RULE_sender = 22, RULE_sayMessage = 23, 
		RULE_hearMessage = 24, RULE_unum = 25, RULE_playmode = 26, RULE_side = 27, 
		RULE_initMessage = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"teamName", "playerName", "playerBehind", "distance", "seeDirection", 
			"distChange", "dirChange", "headFaceDir", "bodyFaceDir", "flagName", 
			"flagBehind", "goalBehind", "goalName", "ballName", "ballBehind", "lineName", 
			"objectName", "objectContents", "seeObject", "seeMessage", "time", "direction", 
			"sender", "sayMessage", "hearMessage", "unum", "playmode", "side", "initMessage"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'p'", "'goalie'", "'P'", "'F'", "'G'", "'b'", "'B'", "'see'", 
			"'hear'", "'l'", "'r'", "'init'", null, null, null, "'('", "')'", "'\"'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "INTEGER", "FLOAT", "DIGITS", "LPAREN", "RPAREN", "QUOTE", "PERIOD", 
			"MESSAGE_SENDER", "PLAYMODE", "QUOTED_TEXT", "FLAG_NAME", "GOAL_NAME", 
			"LINE_NAME", "REF_MESSAGE", "WHITESPACE"
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

	public static class TeamNameContext extends ParserRuleContext {
		public TerminalNode QUOTED_TEXT() { return getToken(ServerMessageParser.QUOTED_TEXT, 0); }
		public TeamNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_teamName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterTeamName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitTeamName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitTeamName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TeamNameContext teamName() throws RecognitionException {
		TeamNameContext _localctx = new TeamNameContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_teamName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(QUOTED_TEXT);
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

	public static class PlayerNameContext extends ParserRuleContext {
		public TeamNameContext teamName() {
			return getRuleContext(TeamNameContext.class,0);
		}
		public UnumContext unum() {
			return getRuleContext(UnumContext.class,0);
		}
		public PlayerNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playerName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterPlayerName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitPlayerName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitPlayerName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlayerNameContext playerName() throws RecognitionException {
		PlayerNameContext _localctx = new PlayerNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_playerName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__0);
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUOTED_TEXT:
				{
				setState(61);
				teamName();
				}
				break;
			case INTEGER:
				{
				setState(62);
				unum();
				}
				break;
			case T__1:
			case RPAREN:
				break;
			default:
				break;
			}
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(65);
				match(T__1);
				}
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

	public static class PlayerBehindContext extends ParserRuleContext {
		public PlayerBehindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_playerBehind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterPlayerBehind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitPlayerBehind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitPlayerBehind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlayerBehindContext playerBehind() throws RecognitionException {
		PlayerBehindContext _localctx = new PlayerBehindContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_playerBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__2);
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

	public static class DistanceContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public DistanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterDistance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitDistance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitDistance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistanceContext distance() throws RecognitionException {
		DistanceContext _localctx = new DistanceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_distance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class SeeDirectionContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public SeeDirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seeDirection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSeeDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSeeDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSeeDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeeDirectionContext seeDirection() throws RecognitionException {
		SeeDirectionContext _localctx = new SeeDirectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_seeDirection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class DistChangeContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public DistChangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distChange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterDistChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitDistChange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitDistChange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistChangeContext distChange() throws RecognitionException {
		DistChangeContext _localctx = new DistChangeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_distChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class DirChangeContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public DirChangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dirChange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterDirChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitDirChange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitDirChange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirChangeContext dirChange() throws RecognitionException {
		DirChangeContext _localctx = new DirChangeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dirChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class HeadFaceDirContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public HeadFaceDirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headFaceDir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterHeadFaceDir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitHeadFaceDir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitHeadFaceDir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadFaceDirContext headFaceDir() throws RecognitionException {
		HeadFaceDirContext _localctx = new HeadFaceDirContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_headFaceDir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class BodyFaceDirContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public BodyFaceDirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyFaceDir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterBodyFaceDir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitBodyFaceDir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitBodyFaceDir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyFaceDirContext bodyFaceDir() throws RecognitionException {
		BodyFaceDirContext _localctx = new BodyFaceDirContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bodyFaceDir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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

	public static class FlagNameContext extends ParserRuleContext {
		public TerminalNode FLAG_NAME() { return getToken(ServerMessageParser.FLAG_NAME, 0); }
		public FlagNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flagName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterFlagName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitFlagName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitFlagName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlagNameContext flagName() throws RecognitionException {
		FlagNameContext _localctx = new FlagNameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_flagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(FLAG_NAME);
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

	public static class FlagBehindContext extends ParserRuleContext {
		public FlagBehindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flagBehind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterFlagBehind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitFlagBehind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitFlagBehind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlagBehindContext flagBehind() throws RecognitionException {
		FlagBehindContext _localctx = new FlagBehindContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flagBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__3);
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

	public static class GoalBehindContext extends ParserRuleContext {
		public GoalBehindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalBehind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterGoalBehind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitGoalBehind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitGoalBehind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalBehindContext goalBehind() throws RecognitionException {
		GoalBehindContext _localctx = new GoalBehindContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_goalBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__4);
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

	public static class GoalNameContext extends ParserRuleContext {
		public TerminalNode GOAL_NAME() { return getToken(ServerMessageParser.GOAL_NAME, 0); }
		public GoalNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterGoalName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitGoalName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitGoalName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalNameContext goalName() throws RecognitionException {
		GoalNameContext _localctx = new GoalNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_goalName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(GOAL_NAME);
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

	public static class BallNameContext extends ParserRuleContext {
		public BallNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterBallName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitBallName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitBallName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BallNameContext ballName() throws RecognitionException {
		BallNameContext _localctx = new BallNameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ballName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__5);
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

	public static class BallBehindContext extends ParserRuleContext {
		public BallBehindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballBehind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterBallBehind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitBallBehind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitBallBehind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BallBehindContext ballBehind() throws RecognitionException {
		BallBehindContext _localctx = new BallBehindContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ballBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__6);
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

	public static class LineNameContext extends ParserRuleContext {
		public TerminalNode LINE_NAME() { return getToken(ServerMessageParser.LINE_NAME, 0); }
		public LineNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterLineName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitLineName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitLineName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineNameContext lineName() throws RecognitionException {
		LineNameContext _localctx = new LineNameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_lineName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(LINE_NAME);
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

	public static class ObjectNameContext extends ParserRuleContext {
		public FlagNameContext flagName() {
			return getRuleContext(FlagNameContext.class,0);
		}
		public GoalNameContext goalName() {
			return getRuleContext(GoalNameContext.class,0);
		}
		public BallNameContext ballName() {
			return getRuleContext(BallNameContext.class,0);
		}
		public LineNameContext lineName() {
			return getRuleContext(LineNameContext.class,0);
		}
		public PlayerNameContext playerName() {
			return getRuleContext(PlayerNameContext.class,0);
		}
		public FlagBehindContext flagBehind() {
			return getRuleContext(FlagBehindContext.class,0);
		}
		public GoalBehindContext goalBehind() {
			return getRuleContext(GoalBehindContext.class,0);
		}
		public BallBehindContext ballBehind() {
			return getRuleContext(BallBehindContext.class,0);
		}
		public PlayerBehindContext playerBehind() {
			return getRuleContext(PlayerBehindContext.class,0);
		}
		public ObjectNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterObjectName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitObjectName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitObjectName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectNameContext objectName() throws RecognitionException {
		ObjectNameContext _localctx = new ObjectNameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_objectName);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLAG_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				flagName();
				}
				break;
			case GOAL_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				goalName();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				ballName();
				}
				break;
			case LINE_NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(99);
				lineName();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				playerName();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 6);
				{
				setState(101);
				flagBehind();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 7);
				{
				setState(102);
				goalBehind();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 8);
				{
				setState(103);
				ballBehind();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 9);
				{
				setState(104);
				playerBehind();
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

	public static class ObjectContentsContext extends ParserRuleContext {
		public DistanceContext distance() {
			return getRuleContext(DistanceContext.class,0);
		}
		public DirectionContext direction() {
			return getRuleContext(DirectionContext.class,0);
		}
		public DistChangeContext distChange() {
			return getRuleContext(DistChangeContext.class,0);
		}
		public DirChangeContext dirChange() {
			return getRuleContext(DirChangeContext.class,0);
		}
		public HeadFaceDirContext headFaceDir() {
			return getRuleContext(HeadFaceDirContext.class,0);
		}
		public BodyFaceDirContext bodyFaceDir() {
			return getRuleContext(BodyFaceDirContext.class,0);
		}
		public ObjectContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectContents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterObjectContents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitObjectContents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitObjectContents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContentsContext objectContents() throws RecognitionException {
		ObjectContentsContext _localctx = new ObjectContentsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_objectContents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			distance();
			setState(108);
			direction();
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(109);
				distChange();
				}
				break;
			}
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(112);
				dirChange();
				}
				break;
			}
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(115);
				headFaceDir();
				}
				break;
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==FLOAT) {
				{
				setState(118);
				bodyFaceDir();
				}
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

	public static class SeeObjectContext extends ParserRuleContext {
		public List<TerminalNode> LPAREN() { return getTokens(ServerMessageParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(ServerMessageParser.LPAREN, i);
		}
		public ObjectNameContext objectName() {
			return getRuleContext(ObjectNameContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(ServerMessageParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(ServerMessageParser.RPAREN, i);
		}
		public ObjectContentsContext objectContents() {
			return getRuleContext(ObjectContentsContext.class,0);
		}
		public SeeObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seeObject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSeeObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSeeObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSeeObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeeObjectContext seeObject() throws RecognitionException {
		SeeObjectContext _localctx = new SeeObjectContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_seeObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(LPAREN);
			setState(122);
			match(LPAREN);
			setState(123);
			objectName();
			setState(124);
			match(RPAREN);
			setState(125);
			objectContents();
			setState(126);
			match(RPAREN);
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

	public static class SeeMessageContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(ServerMessageParser.EOF, 0); }
		public List<SeeObjectContext> seeObject() {
			return getRuleContexts(SeeObjectContext.class);
		}
		public SeeObjectContext seeObject(int i) {
			return getRuleContext(SeeObjectContext.class,i);
		}
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
		enterRule(_localctx, 38, RULE_seeMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(LPAREN);
			setState(129);
			match(T__7);
			setState(130);
			time();
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131);
				seeObject();
				}
				}
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LPAREN );
			setState(136);
			match(RPAREN);
			setState(137);
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
		enterRule(_localctx, 40, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
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
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
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
		enterRule(_localctx, 42, RULE_direction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==FLOAT) ) {
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
		enterRule(_localctx, 44, RULE_sender);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				direction();
				}
				break;
			case MESSAGE_SENDER:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
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
		public TerminalNode QUOTED_TEXT() { return getToken(ServerMessageParser.QUOTED_TEXT, 0); }
		public TerminalNode REF_MESSAGE() { return getToken(ServerMessageParser.REF_MESSAGE, 0); }
		public TerminalNode PLAYMODE() { return getToken(ServerMessageParser.PLAYMODE, 0); }
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
		enterRule(_localctx, 46, RULE_sayMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLAYMODE) | (1L << QUOTED_TEXT) | (1L << REF_MESSAGE))) != 0)) ) {
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
		enterRule(_localctx, 48, RULE_hearMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(LPAREN);
			setState(150);
			match(T__8);
			setState(151);
			time();
			setState(152);
			sender();
			setState(153);
			sayMessage();
			setState(154);
			match(RPAREN);
			setState(155);
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
		enterRule(_localctx, 50, RULE_unum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
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
		enterRule(_localctx, 52, RULE_playmode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
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
		enterRule(_localctx, 54, RULE_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			_la = _input.LA(1);
			if ( !(_la==T__9 || _la==T__10) ) {
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
		enterRule(_localctx, 56, RULE_initMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(LPAREN);
			setState(164);
			match(T__11);
			setState(165);
			side();
			setState(166);
			unum();
			setState(167);
			playmode();
			setState(168);
			match(RPAREN);
			setState(169);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00ae\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3\3\3\3"+
		"\5\3B\n\3\3\3\5\3E\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22l\n\22\3\23\3\23"+
		"\3\23\5\23q\n\23\3\23\5\23t\n\23\3\23\5\23w\n\23\3\23\5\23z\n\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\6\25\u0087\n\25\r\25"+
		"\16\25\u0088\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\5\30\u0094\n"+
		"\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\2\2\37\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\5\3\2\17"+
		"\20\4\2\27\30\34\34\3\2\f\r\2\u00a1\2<\3\2\2\2\4>\3\2\2\2\6F\3\2\2\2\b"+
		"H\3\2\2\2\nJ\3\2\2\2\fL\3\2\2\2\16N\3\2\2\2\20P\3\2\2\2\22R\3\2\2\2\24"+
		"T\3\2\2\2\26V\3\2\2\2\30X\3\2\2\2\32Z\3\2\2\2\34\\\3\2\2\2\36^\3\2\2\2"+
		" `\3\2\2\2\"k\3\2\2\2$m\3\2\2\2&{\3\2\2\2(\u0082\3\2\2\2*\u008d\3\2\2"+
		"\2,\u008f\3\2\2\2.\u0093\3\2\2\2\60\u0095\3\2\2\2\62\u0097\3\2\2\2\64"+
		"\u009f\3\2\2\2\66\u00a1\3\2\2\28\u00a3\3\2\2\2:\u00a5\3\2\2\2<=\7\30\2"+
		"\2=\3\3\2\2\2>A\7\3\2\2?B\5\2\2\2@B\5\64\33\2A?\3\2\2\2A@\3\2\2\2AB\3"+
		"\2\2\2BD\3\2\2\2CE\7\4\2\2DC\3\2\2\2DE\3\2\2\2E\5\3\2\2\2FG\7\5\2\2G\7"+
		"\3\2\2\2HI\t\2\2\2I\t\3\2\2\2JK\t\2\2\2K\13\3\2\2\2LM\t\2\2\2M\r\3\2\2"+
		"\2NO\t\2\2\2O\17\3\2\2\2PQ\t\2\2\2Q\21\3\2\2\2RS\t\2\2\2S\23\3\2\2\2T"+
		"U\7\31\2\2U\25\3\2\2\2VW\7\6\2\2W\27\3\2\2\2XY\7\7\2\2Y\31\3\2\2\2Z[\7"+
		"\32\2\2[\33\3\2\2\2\\]\7\b\2\2]\35\3\2\2\2^_\7\t\2\2_\37\3\2\2\2`a\7\33"+
		"\2\2a!\3\2\2\2bl\5\24\13\2cl\5\32\16\2dl\5\34\17\2el\5 \21\2fl\5\4\3\2"+
		"gl\5\26\f\2hl\5\30\r\2il\5\36\20\2jl\5\6\4\2kb\3\2\2\2kc\3\2\2\2kd\3\2"+
		"\2\2ke\3\2\2\2kf\3\2\2\2kg\3\2\2\2kh\3\2\2\2ki\3\2\2\2kj\3\2\2\2l#\3\2"+
		"\2\2mn\5\b\5\2np\5,\27\2oq\5\f\7\2po\3\2\2\2pq\3\2\2\2qs\3\2\2\2rt\5\16"+
		"\b\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2uw\5\20\t\2vu\3\2\2\2vw\3\2\2\2wy\3"+
		"\2\2\2xz\5\22\n\2yx\3\2\2\2yz\3\2\2\2z%\3\2\2\2{|\7\22\2\2|}\7\22\2\2"+
		"}~\5\"\22\2~\177\7\23\2\2\177\u0080\5$\23\2\u0080\u0081\7\23\2\2\u0081"+
		"\'\3\2\2\2\u0082\u0083\7\22\2\2\u0083\u0084\7\n\2\2\u0084\u0086\5*\26"+
		"\2\u0085\u0087\5&\24\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\7\23\2\2"+
		"\u008b\u008c\7\2\2\3\u008c)\3\2\2\2\u008d\u008e\7\17\2\2\u008e+\3\2\2"+
		"\2\u008f\u0090\t\2\2\2\u0090-\3\2\2\2\u0091\u0094\5,\27\2\u0092\u0094"+
		"\7\26\2\2\u0093\u0091\3\2\2\2\u0093\u0092\3\2\2\2\u0094/\3\2\2\2\u0095"+
		"\u0096\t\3\2\2\u0096\61\3\2\2\2\u0097\u0098\7\22\2\2\u0098\u0099\7\13"+
		"\2\2\u0099\u009a\5*\26\2\u009a\u009b\5.\30\2\u009b\u009c\5\60\31\2\u009c"+
		"\u009d\7\23\2\2\u009d\u009e\7\2\2\3\u009e\63\3\2\2\2\u009f\u00a0\7\17"+
		"\2\2\u00a0\65\3\2\2\2\u00a1\u00a2\7\27\2\2\u00a2\67\3\2\2\2\u00a3\u00a4"+
		"\t\4\2\2\u00a49\3\2\2\2\u00a5\u00a6\7\22\2\2\u00a6\u00a7\7\16\2\2\u00a7"+
		"\u00a8\58\35\2\u00a8\u00a9\5\64\33\2\u00a9\u00aa\5\66\34\2\u00aa\u00ab"+
		"\7\23\2\2\u00ab\u00ac\7\2\2\3\u00ac;\3\2\2\2\13ADkpsvy\u0088\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}