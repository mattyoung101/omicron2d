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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, INTEGER=31, 
		FLOAT=32, DIGITS=33, LPAREN=34, RPAREN=35, QUOTE=36, PERIOD=37, MESSAGE_SENDER=38, 
		PLAYMODE=39, QUOTED_TEXT=40, FLAG_NAME=41, GOAL_NAME=42, LINE_NAME=43, 
		REF_MESSAGE=44, WS=45;
	public static final int
		RULE_viewQuality = 0, RULE_viewAngle = 1, RULE_viewModeMsg = 2, RULE_stamina = 3, 
		RULE_effort = 4, RULE_capacity = 5, RULE_staminaMsg = 6, RULE_amountOfSpeed = 7, 
		RULE_directionOfSpeed = 8, RULE_speedMsg = 9, RULE_headAngle = 10, RULE_headAngleMsg = 11, 
		RULE_useless = 12, RULE_senseBodyMessage = 13, RULE_teamName = 14, RULE_goalie = 15, 
		RULE_playerName = 16, RULE_playerBehind = 17, RULE_distance = 18, RULE_distChange = 19, 
		RULE_dirChange = 20, RULE_headFaceDir = 21, RULE_bodyFaceDir = 22, RULE_flagName = 23, 
		RULE_flagBehind = 24, RULE_goalBehind = 25, RULE_goalName = 26, RULE_ballName = 27, 
		RULE_ballBehind = 28, RULE_lineName = 29, RULE_objectName = 30, RULE_objectContents = 31, 
		RULE_seeObject = 32, RULE_seeMessage = 33, RULE_time = 34, RULE_direction = 35, 
		RULE_sender = 36, RULE_sayMessage = 37, RULE_hearMessage = 38, RULE_unum = 39, 
		RULE_playmode = 40, RULE_side = 41, RULE_initMessage = 42;
	private static String[] makeRuleNames() {
		return new String[] {
			"viewQuality", "viewAngle", "viewModeMsg", "stamina", "effort", "capacity", 
			"staminaMsg", "amountOfSpeed", "directionOfSpeed", "speedMsg", "headAngle", 
			"headAngleMsg", "useless", "senseBodyMessage", "teamName", "goalie", 
			"playerName", "playerBehind", "distance", "distChange", "dirChange", 
			"headFaceDir", "bodyFaceDir", "flagName", "flagBehind", "goalBehind", 
			"goalName", "ballName", "ballBehind", "lineName", "objectName", "objectContents", 
			"seeObject", "seeMessage", "time", "direction", "sender", "sayMessage", 
			"hearMessage", "unum", "playmode", "side", "initMessage"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'high'", "'low'", "'narrow'", "'normal'", "'wide'", "'view_mode'", 
			"'stamina'", "'speed'", "'head_angle'", "'kick'", "'dash'", "'turn'", 
			"'say'", "'turn_neck'", "'catch'", "'move'", "'change_view'", "'sense_body'", 
			"'goalie'", "'p'", "'P'", "'F'", "'G'", "'b'", "'B'", "'see'", "'hear'", 
			"'l'", "'r'", "'init'", null, null, null, "'('", "')'", "'\"'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "INTEGER", "FLOAT", "DIGITS", 
			"LPAREN", "RPAREN", "QUOTE", "PERIOD", "MESSAGE_SENDER", "PLAYMODE", 
			"QUOTED_TEXT", "FLAG_NAME", "GOAL_NAME", "LINE_NAME", "REF_MESSAGE", 
			"WS"
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

	public static class ViewQualityContext extends ParserRuleContext {
		public ViewQualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewQuality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterViewQuality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitViewQuality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitViewQuality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewQualityContext viewQuality() throws RecognitionException {
		ViewQualityContext _localctx = new ViewQualityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_viewQuality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
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

	public static class ViewAngleContext extends ParserRuleContext {
		public ViewAngleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAngle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterViewAngle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitViewAngle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitViewAngle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAngleContext viewAngle() throws RecognitionException {
		ViewAngleContext _localctx = new ViewAngleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_viewAngle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) ) {
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

	public static class ViewModeMsgContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public ViewQualityContext viewQuality() {
			return getRuleContext(ViewQualityContext.class,0);
		}
		public ViewAngleContext viewAngle() {
			return getRuleContext(ViewAngleContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public ViewModeMsgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewModeMsg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterViewModeMsg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitViewModeMsg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitViewModeMsg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewModeMsgContext viewModeMsg() throws RecognitionException {
		ViewModeMsgContext _localctx = new ViewModeMsgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_viewModeMsg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(LPAREN);
			setState(91);
			match(T__5);
			setState(92);
			viewQuality();
			setState(93);
			viewAngle();
			setState(94);
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

	public static class StaminaContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public StaminaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stamina; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterStamina(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitStamina(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitStamina(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaminaContext stamina() throws RecognitionException {
		StaminaContext _localctx = new StaminaContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stamina);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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

	public static class EffortContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public EffortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_effort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterEffort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitEffort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitEffort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EffortContext effort() throws RecognitionException {
		EffortContext _localctx = new EffortContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_effort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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

	public static class CapacityContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public CapacityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capacity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterCapacity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitCapacity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitCapacity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CapacityContext capacity() throws RecognitionException {
		CapacityContext _localctx = new CapacityContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_capacity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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

	public static class StaminaMsgContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public StaminaContext stamina() {
			return getRuleContext(StaminaContext.class,0);
		}
		public EffortContext effort() {
			return getRuleContext(EffortContext.class,0);
		}
		public CapacityContext capacity() {
			return getRuleContext(CapacityContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public StaminaMsgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staminaMsg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterStaminaMsg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitStaminaMsg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitStaminaMsg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaminaMsgContext staminaMsg() throws RecognitionException {
		StaminaMsgContext _localctx = new StaminaMsgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_staminaMsg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LPAREN);
			setState(103);
			match(T__6);
			setState(104);
			stamina();
			setState(105);
			effort();
			setState(106);
			capacity();
			setState(107);
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

	public static class AmountOfSpeedContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public AmountOfSpeedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_amountOfSpeed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterAmountOfSpeed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitAmountOfSpeed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitAmountOfSpeed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AmountOfSpeedContext amountOfSpeed() throws RecognitionException {
		AmountOfSpeedContext _localctx = new AmountOfSpeedContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_amountOfSpeed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
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

	public static class DirectionOfSpeedContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public DirectionOfSpeedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directionOfSpeed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterDirectionOfSpeed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitDirectionOfSpeed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitDirectionOfSpeed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectionOfSpeedContext directionOfSpeed() throws RecognitionException {
		DirectionOfSpeedContext _localctx = new DirectionOfSpeedContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_directionOfSpeed);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
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

	public static class SpeedMsgContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public AmountOfSpeedContext amountOfSpeed() {
			return getRuleContext(AmountOfSpeedContext.class,0);
		}
		public DirectionOfSpeedContext directionOfSpeed() {
			return getRuleContext(DirectionOfSpeedContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public SpeedMsgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_speedMsg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSpeedMsg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSpeedMsg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSpeedMsg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpeedMsgContext speedMsg() throws RecognitionException {
		SpeedMsgContext _localctx = new SpeedMsgContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_speedMsg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(LPAREN);
			setState(114);
			match(T__7);
			setState(115);
			amountOfSpeed();
			setState(116);
			directionOfSpeed();
			setState(117);
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

	public static class HeadAngleContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public HeadAngleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headAngle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterHeadAngle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitHeadAngle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitHeadAngle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadAngleContext headAngle() throws RecognitionException {
		HeadAngleContext _localctx = new HeadAngleContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_headAngle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
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

	public static class HeadAngleMsgContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public HeadAngleContext headAngle() {
			return getRuleContext(HeadAngleContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public HeadAngleMsgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headAngleMsg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterHeadAngleMsg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitHeadAngleMsg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitHeadAngleMsg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadAngleMsgContext headAngleMsg() throws RecognitionException {
		HeadAngleMsgContext _localctx = new HeadAngleMsgContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_headAngleMsg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(LPAREN);
			setState(122);
			match(T__8);
			setState(123);
			headAngle();
			setState(124);
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

	public static class UselessContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public UselessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useless; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterUseless(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitUseless(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitUseless(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UselessContext useless() throws RecognitionException {
		UselessContext _localctx = new UselessContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_useless);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(LPAREN);
			setState(127);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(128);
			match(INTEGER);
			setState(129);
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

	public static class SenseBodyMessageContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ServerMessageParser.LPAREN, 0); }
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public ViewModeMsgContext viewModeMsg() {
			return getRuleContext(ViewModeMsgContext.class,0);
		}
		public StaminaMsgContext staminaMsg() {
			return getRuleContext(StaminaMsgContext.class,0);
		}
		public SpeedMsgContext speedMsg() {
			return getRuleContext(SpeedMsgContext.class,0);
		}
		public HeadAngleMsgContext headAngleMsg() {
			return getRuleContext(HeadAngleMsgContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ServerMessageParser.RPAREN, 0); }
		public TerminalNode EOF() { return getToken(ServerMessageParser.EOF, 0); }
		public List<UselessContext> useless() {
			return getRuleContexts(UselessContext.class);
		}
		public UselessContext useless(int i) {
			return getRuleContext(UselessContext.class,i);
		}
		public SenseBodyMessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_senseBodyMessage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterSenseBodyMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitSenseBodyMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitSenseBodyMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SenseBodyMessageContext senseBodyMessage() throws RecognitionException {
		SenseBodyMessageContext _localctx = new SenseBodyMessageContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_senseBodyMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(LPAREN);
			setState(132);
			match(T__17);
			setState(133);
			time();
			setState(134);
			viewModeMsg();
			setState(135);
			staminaMsg();
			setState(136);
			speedMsg();
			setState(137);
			headAngleMsg();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN) {
				{
				{
				setState(138);
				useless();
				}
				}
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(144);
			match(RPAREN);
			setState(145);
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
		enterRule(_localctx, 28, RULE_teamName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
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

	public static class GoalieContext extends ParserRuleContext {
		public GoalieContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalie; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterGoalie(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitGoalie(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitGoalie(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalieContext goalie() throws RecognitionException {
		GoalieContext _localctx = new GoalieContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_goalie);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__18);
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
		public GoalieContext goalie() {
			return getRuleContext(GoalieContext.class,0);
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
		enterRule(_localctx, 32, RULE_playerName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__19);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUOTED_TEXT) {
				{
				setState(152);
				teamName();
				}
			}

			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(155);
				unum();
				}
			}

			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(158);
				goalie();
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
		enterRule(_localctx, 34, RULE_playerBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__20);
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
		enterRule(_localctx, 36, RULE_distance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
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
		enterRule(_localctx, 38, RULE_distChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
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
		enterRule(_localctx, 40, RULE_dirChange);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
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
		enterRule(_localctx, 42, RULE_headFaceDir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
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
		enterRule(_localctx, 44, RULE_bodyFaceDir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
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
		enterRule(_localctx, 46, RULE_flagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
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
		enterRule(_localctx, 48, RULE_flagBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__21);
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
		enterRule(_localctx, 50, RULE_goalBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__22);
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
		enterRule(_localctx, 52, RULE_goalName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
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
		enterRule(_localctx, 54, RULE_ballName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(T__23);
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
		enterRule(_localctx, 56, RULE_ballBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__24);
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
		enterRule(_localctx, 58, RULE_lineName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
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
		enterRule(_localctx, 60, RULE_objectName);
		try {
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLAG_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				flagName();
				}
				break;
			case GOAL_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				goalName();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				ballName();
				}
				break;
			case LINE_NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(190);
				lineName();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 5);
				{
				setState(191);
				playerName();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 6);
				{
				setState(192);
				flagBehind();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 7);
				{
				setState(193);
				goalBehind();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 8);
				{
				setState(194);
				ballBehind();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 9);
				{
				setState(195);
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
		enterRule(_localctx, 62, RULE_objectContents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			distance();
			setState(199);
			direction();
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(200);
				distChange();
				}
				break;
			}
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(203);
				dirChange();
				}
				break;
			}
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(206);
				headFaceDir();
				}
				break;
			}
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==FLOAT) {
				{
				setState(209);
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
		enterRule(_localctx, 64, RULE_seeObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(LPAREN);
			setState(213);
			match(LPAREN);
			setState(214);
			objectName();
			setState(215);
			match(RPAREN);
			setState(216);
			objectContents();
			setState(217);
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
		enterRule(_localctx, 66, RULE_seeMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(LPAREN);
			setState(220);
			match(T__25);
			setState(221);
			time();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN) {
				{
				{
				setState(222);
				seeObject();
				}
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(228);
			match(RPAREN);
			setState(229);
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
		enterRule(_localctx, 68, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
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
		enterRule(_localctx, 70, RULE_direction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
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
		enterRule(_localctx, 72, RULE_sender);
		try {
			setState(237);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				direction();
				}
				break;
			case MESSAGE_SENDER:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
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
		enterRule(_localctx, 74, RULE_sayMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
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
		enterRule(_localctx, 76, RULE_hearMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(LPAREN);
			setState(242);
			match(T__26);
			setState(243);
			time();
			setState(244);
			sender();
			setState(245);
			sayMessage();
			setState(246);
			match(RPAREN);
			setState(247);
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
		enterRule(_localctx, 78, RULE_unum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
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
		enterRule(_localctx, 80, RULE_playmode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
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
		enterRule(_localctx, 82, RULE_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
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
		enterRule(_localctx, 84, RULE_initMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(LPAREN);
			setState(256);
			match(T__29);
			setState(257);
			side();
			setState(258);
			unum();
			setState(259);
			playmode();
			setState(260);
			match(RPAREN);
			setState(261);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u010a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u008e\n\17\f\17\16\17\u0091\13\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\5\22\u009c\n\22\3\22\5\22\u009f"+
		"\n\22\3\22\5\22\u00a2\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u00c7\n \3!\3!\3!\5!\u00cc"+
		"\n!\3!\5!\u00cf\n!\3!\5!\u00d2\n!\3!\5!\u00d5\n!\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\7#\u00e2\n#\f#\16#\u00e5\13#\3#\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\5&\u00f0\n&\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,"+
		"\3,\3,\3,\3,\3,\3,\3,\3,\2\2-\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 "+
		"\"$&(*,.\60\62\64\668:<>@BDFHJLNPRTV\2\b\3\2\3\4\3\2\5\7\3\2!\"\3\2\f"+
		"\23\4\2)*..\3\2\36\37\2\u00f0\2X\3\2\2\2\4Z\3\2\2\2\6\\\3\2\2\2\bb\3\2"+
		"\2\2\nd\3\2\2\2\ff\3\2\2\2\16h\3\2\2\2\20o\3\2\2\2\22q\3\2\2\2\24s\3\2"+
		"\2\2\26y\3\2\2\2\30{\3\2\2\2\32\u0080\3\2\2\2\34\u0085\3\2\2\2\36\u0095"+
		"\3\2\2\2 \u0097\3\2\2\2\"\u0099\3\2\2\2$\u00a3\3\2\2\2&\u00a5\3\2\2\2"+
		"(\u00a7\3\2\2\2*\u00a9\3\2\2\2,\u00ab\3\2\2\2.\u00ad\3\2\2\2\60\u00af"+
		"\3\2\2\2\62\u00b1\3\2\2\2\64\u00b3\3\2\2\2\66\u00b5\3\2\2\28\u00b7\3\2"+
		"\2\2:\u00b9\3\2\2\2<\u00bb\3\2\2\2>\u00c6\3\2\2\2@\u00c8\3\2\2\2B\u00d6"+
		"\3\2\2\2D\u00dd\3\2\2\2F\u00e9\3\2\2\2H\u00eb\3\2\2\2J\u00ef\3\2\2\2L"+
		"\u00f1\3\2\2\2N\u00f3\3\2\2\2P\u00fb\3\2\2\2R\u00fd\3\2\2\2T\u00ff\3\2"+
		"\2\2V\u0101\3\2\2\2XY\t\2\2\2Y\3\3\2\2\2Z[\t\3\2\2[\5\3\2\2\2\\]\7$\2"+
		"\2]^\7\b\2\2^_\5\2\2\2_`\5\4\3\2`a\7%\2\2a\7\3\2\2\2bc\t\4\2\2c\t\3\2"+
		"\2\2de\t\4\2\2e\13\3\2\2\2fg\t\4\2\2g\r\3\2\2\2hi\7$\2\2ij\7\t\2\2jk\5"+
		"\b\5\2kl\5\n\6\2lm\5\f\7\2mn\7%\2\2n\17\3\2\2\2op\t\4\2\2p\21\3\2\2\2"+
		"qr\t\4\2\2r\23\3\2\2\2st\7$\2\2tu\7\n\2\2uv\5\20\t\2vw\5\22\n\2wx\7%\2"+
		"\2x\25\3\2\2\2yz\t\4\2\2z\27\3\2\2\2{|\7$\2\2|}\7\13\2\2}~\5\26\f\2~\177"+
		"\7%\2\2\177\31\3\2\2\2\u0080\u0081\7$\2\2\u0081\u0082\t\5\2\2\u0082\u0083"+
		"\7!\2\2\u0083\u0084\7%\2\2\u0084\33\3\2\2\2\u0085\u0086\7$\2\2\u0086\u0087"+
		"\7\24\2\2\u0087\u0088\5F$\2\u0088\u0089\5\6\4\2\u0089\u008a\5\16\b\2\u008a"+
		"\u008b\5\24\13\2\u008b\u008f\5\30\r\2\u008c\u008e\5\32\16\2\u008d\u008c"+
		"\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\7%\2\2\u0093\u0094\7\2"+
		"\2\3\u0094\35\3\2\2\2\u0095\u0096\7*\2\2\u0096\37\3\2\2\2\u0097\u0098"+
		"\7\25\2\2\u0098!\3\2\2\2\u0099\u009b\7\26\2\2\u009a\u009c\5\36\20\2\u009b"+
		"\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009f\5P"+
		")\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u00a2\5 \21\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2#\3\2\2\2"+
		"\u00a3\u00a4\7\27\2\2\u00a4%\3\2\2\2\u00a5\u00a6\t\4\2\2\u00a6\'\3\2\2"+
		"\2\u00a7\u00a8\t\4\2\2\u00a8)\3\2\2\2\u00a9\u00aa\t\4\2\2\u00aa+\3\2\2"+
		"\2\u00ab\u00ac\t\4\2\2\u00ac-\3\2\2\2\u00ad\u00ae\t\4\2\2\u00ae/\3\2\2"+
		"\2\u00af\u00b0\7+\2\2\u00b0\61\3\2\2\2\u00b1\u00b2\7\30\2\2\u00b2\63\3"+
		"\2\2\2\u00b3\u00b4\7\31\2\2\u00b4\65\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6"+
		"\67\3\2\2\2\u00b7\u00b8\7\32\2\2\u00b89\3\2\2\2\u00b9\u00ba\7\33\2\2\u00ba"+
		";\3\2\2\2\u00bb\u00bc\7-\2\2\u00bc=\3\2\2\2\u00bd\u00c7\5\60\31\2\u00be"+
		"\u00c7\5\66\34\2\u00bf\u00c7\58\35\2\u00c0\u00c7\5<\37\2\u00c1\u00c7\5"+
		"\"\22\2\u00c2\u00c7\5\62\32\2\u00c3\u00c7\5\64\33\2\u00c4\u00c7\5:\36"+
		"\2\u00c5\u00c7\5$\23\2\u00c6\u00bd\3\2\2\2\u00c6\u00be\3\2\2\2\u00c6\u00bf"+
		"\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c6\u00c1\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c6"+
		"\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7?\3\2\2\2"+
		"\u00c8\u00c9\5&\24\2\u00c9\u00cb\5H%\2\u00ca\u00cc\5(\25\2\u00cb\u00ca"+
		"\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cf\5*\26\2\u00ce"+
		"\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00d2\5,"+
		"\27\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3"+
		"\u00d5\5.\30\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5A\3\2\2\2"+
		"\u00d6\u00d7\7$\2\2\u00d7\u00d8\7$\2\2\u00d8\u00d9\5> \2\u00d9\u00da\7"+
		"%\2\2\u00da\u00db\5@!\2\u00db\u00dc\7%\2\2\u00dcC\3\2\2\2\u00dd\u00de"+
		"\7$\2\2\u00de\u00df\7\34\2\2\u00df\u00e3\5F$\2\u00e0\u00e2\5B\"\2\u00e1"+
		"\u00e0\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7%\2\2\u00e7"+
		"\u00e8\7\2\2\3\u00e8E\3\2\2\2\u00e9\u00ea\7!\2\2\u00eaG\3\2\2\2\u00eb"+
		"\u00ec\t\4\2\2\u00ecI\3\2\2\2\u00ed\u00f0\5H%\2\u00ee\u00f0\7(\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0K\3\2\2\2\u00f1\u00f2\t\6\2\2"+
		"\u00f2M\3\2\2\2\u00f3\u00f4\7$\2\2\u00f4\u00f5\7\35\2\2\u00f5\u00f6\5"+
		"F$\2\u00f6\u00f7\5J&\2\u00f7\u00f8\5L\'\2\u00f8\u00f9\7%\2\2\u00f9\u00fa"+
		"\7\2\2\3\u00faO\3\2\2\2\u00fb\u00fc\7!\2\2\u00fcQ\3\2\2\2\u00fd\u00fe"+
		"\7)\2\2\u00feS\3\2\2\2\u00ff\u0100\t\7\2\2\u0100U\3\2\2\2\u0101\u0102"+
		"\7$\2\2\u0102\u0103\7 \2\2\u0103\u0104\5T+\2\u0104\u0105\5P)\2\u0105\u0106"+
		"\5R*\2\u0106\u0107\7%\2\2\u0107\u0108\7\2\2\3\u0108W\3\2\2\2\r\u008f\u009b"+
		"\u009e\u00a1\u00c6\u00cb\u00ce\u00d1\u00d4\u00e3\u00ef";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}