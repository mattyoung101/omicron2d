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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, INTEGER=33, FLOAT=34, DIGITS=35, LPAREN=36, RPAREN=37, QUOTE=38, 
		PERIOD=39, MESSAGE_SENDER=40, PLAYMODE=41, QUOTED_TEXT=42, FLAG_NAME=43, 
		GOAL_NAME=44, LINE_NAME=45, REF_MESSAGE=46, WS=47;
	public static final int
		RULE_viewQuality = 0, RULE_viewAngle = 1, RULE_viewModeMsg = 2, RULE_stamina = 3, 
		RULE_effort = 4, RULE_capacity = 5, RULE_staminaMsg = 6, RULE_amountOfSpeed = 7, 
		RULE_directionOfSpeed = 8, RULE_speedMsg = 9, RULE_headAngle = 10, RULE_headAngleMsg = 11, 
		RULE_useless = 12, RULE_senseBodyMessage = 13, RULE_teamName = 14, RULE_goalie = 15, 
		RULE_playerName = 16, RULE_playerBehind = 17, RULE_distance = 18, RULE_distChange = 19, 
		RULE_dirChange = 20, RULE_headFaceDir = 21, RULE_bodyFaceDir = 22, RULE_pointDir = 23, 
		RULE_unknownThing = 24, RULE_flagName = 25, RULE_flagBehind = 26, RULE_goalBehind = 27, 
		RULE_goalName = 28, RULE_ballName = 29, RULE_ballBehind = 30, RULE_lineName = 31, 
		RULE_objectName = 32, RULE_objectContents = 33, RULE_seeObject = 34, RULE_seeMessage = 35, 
		RULE_time = 36, RULE_direction = 37, RULE_sender = 38, RULE_sayMessage = 39, 
		RULE_hearMessage = 40, RULE_unum = 41, RULE_playmode = 42, RULE_side = 43, 
		RULE_initMessage = 44;
	private static String[] makeRuleNames() {
		return new String[] {
			"viewQuality", "viewAngle", "viewModeMsg", "stamina", "effort", "capacity", 
			"staminaMsg", "amountOfSpeed", "directionOfSpeed", "speedMsg", "headAngle", 
			"headAngleMsg", "useless", "senseBodyMessage", "teamName", "goalie", 
			"playerName", "playerBehind", "distance", "distChange", "dirChange", 
			"headFaceDir", "bodyFaceDir", "pointDir", "unknownThing", "flagName", 
			"flagBehind", "goalBehind", "goalName", "ballName", "ballBehind", "lineName", 
			"objectName", "objectContents", "seeObject", "seeMessage", "time", "direction", 
			"sender", "sayMessage", "hearMessage", "unum", "playmode", "side", "initMessage"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'high'", "'low'", "'narrow'", "'normal'", "'wide'", "'view_mode'", 
			"'stamina'", "'speed'", "'head_angle'", "'kick'", "'dash'", "'turn'", 
			"'say'", "'turn_neck'", "'catch'", "'move'", "'change_view'", "'sense_body'", 
			"'goalie'", "'p'", "'P'", "'t'", "'k'", "'F'", "'G'", "'b'", "'B'", "'see'", 
			"'hear'", "'l'", "'r'", "'init'", null, null, null, "'('", "')'", "'\"'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "INTEGER", "FLOAT", 
			"DIGITS", "LPAREN", "RPAREN", "QUOTE", "PERIOD", "MESSAGE_SENDER", "PLAYMODE", 
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
			setState(90);
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
			setState(92);
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
			setState(94);
			match(LPAREN);
			setState(95);
			match(T__5);
			setState(96);
			viewQuality();
			setState(97);
			viewAngle();
			setState(98);
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
			setState(102);
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
			setState(104);
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
			setState(106);
			match(LPAREN);
			setState(107);
			match(T__6);
			setState(108);
			stamina();
			setState(109);
			effort();
			setState(110);
			capacity();
			setState(111);
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
			setState(113);
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
			setState(115);
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
			setState(117);
			match(LPAREN);
			setState(118);
			match(T__7);
			setState(119);
			amountOfSpeed();
			setState(120);
			directionOfSpeed();
			setState(121);
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
			setState(123);
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
			setState(125);
			match(LPAREN);
			setState(126);
			match(T__8);
			setState(127);
			headAngle();
			setState(128);
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
			setState(130);
			match(LPAREN);
			setState(131);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(132);
			match(INTEGER);
			setState(133);
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
			setState(135);
			match(LPAREN);
			setState(136);
			match(T__17);
			setState(137);
			time();
			setState(138);
			viewModeMsg();
			setState(139);
			staminaMsg();
			setState(140);
			speedMsg();
			setState(141);
			headAngleMsg();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN) {
				{
				{
				setState(142);
				useless();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			match(RPAREN);
			setState(149);
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
			setState(151);
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
			setState(153);
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
			setState(155);
			match(T__19);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QUOTED_TEXT) {
				{
				setState(156);
				teamName();
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(159);
				unum();
				}
			}

			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(162);
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
			setState(165);
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
			setState(173);
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
			setState(175);
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

	public static class PointDirContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(ServerMessageParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(ServerMessageParser.FLOAT, 0); }
		public PointDirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointDir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterPointDir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitPointDir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitPointDir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointDirContext pointDir() throws RecognitionException {
		PointDirContext _localctx = new PointDirContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pointDir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
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

	public static class UnknownThingContext extends ParserRuleContext {
		public UnknownThingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unknownThing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).enterUnknownThing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ServerMessageListener ) ((ServerMessageListener)listener).exitUnknownThing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ServerMessageVisitor ) return ((ServerMessageVisitor<? extends T>)visitor).visitUnknownThing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnknownThingContext unknownThing() throws RecognitionException {
		UnknownThingContext _localctx = new UnknownThingContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unknownThing);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_la = _input.LA(1);
			if ( !(_la==T__21 || _la==T__22) ) {
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
		enterRule(_localctx, 50, RULE_flagName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
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
		enterRule(_localctx, 52, RULE_flagBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
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
		enterRule(_localctx, 54, RULE_goalBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
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
		enterRule(_localctx, 56, RULE_goalName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
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
		enterRule(_localctx, 58, RULE_ballName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__25);
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
		enterRule(_localctx, 60, RULE_ballBehind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(T__26);
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
		enterRule(_localctx, 62, RULE_lineName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
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
		enterRule(_localctx, 64, RULE_objectName);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLAG_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				flagName();
				}
				break;
			case GOAL_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				goalName();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				ballName();
				}
				break;
			case LINE_NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(198);
				lineName();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 5);
				{
				setState(199);
				playerName();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				setState(200);
				flagBehind();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 7);
				{
				setState(201);
				goalBehind();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 8);
				{
				setState(202);
				ballBehind();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 9);
				{
				setState(203);
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
		public PointDirContext pointDir() {
			return getRuleContext(PointDirContext.class,0);
		}
		public List<UnknownThingContext> unknownThing() {
			return getRuleContexts(UnknownThingContext.class);
		}
		public UnknownThingContext unknownThing(int i) {
			return getRuleContext(UnknownThingContext.class,i);
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
		enterRule(_localctx, 66, RULE_objectContents);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(206);
				distance();
				}
				break;
			}
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(209);
				direction();
				}
				break;
			}
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(212);
				distChange();
				}
				break;
			}
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(215);
				dirChange();
				}
				break;
			}
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(218);
				headFaceDir();
				}
				break;
			}
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(221);
				bodyFaceDir();
				}
				break;
			}
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER || _la==FLOAT) {
				{
				setState(224);
				pointDir();
				}
			}

			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(227);
				unknownThing();
				}
				break;
			}
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21 || _la==T__22) {
				{
				setState(230);
				unknownThing();
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
		enterRule(_localctx, 68, RULE_seeObject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(LPAREN);
			setState(234);
			match(LPAREN);
			setState(235);
			objectName();
			setState(236);
			match(RPAREN);
			setState(237);
			objectContents();
			setState(238);
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
		enterRule(_localctx, 70, RULE_seeMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(LPAREN);
			setState(241);
			match(T__27);
			setState(242);
			time();
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN) {
				{
				{
				setState(243);
				seeObject();
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(249);
			match(RPAREN);
			setState(250);
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
		enterRule(_localctx, 72, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
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
		enterRule(_localctx, 74, RULE_direction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
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
		enterRule(_localctx, 76, RULE_sender);
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				direction();
				}
				break;
			case MESSAGE_SENDER:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
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
		enterRule(_localctx, 78, RULE_sayMessage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
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
		enterRule(_localctx, 80, RULE_hearMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(LPAREN);
			setState(263);
			match(T__28);
			setState(264);
			time();
			setState(265);
			sender();
			setState(266);
			sayMessage();
			setState(267);
			match(RPAREN);
			setState(268);
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
		enterRule(_localctx, 82, RULE_unum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
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
		enterRule(_localctx, 84, RULE_playmode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
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
		enterRule(_localctx, 86, RULE_side);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
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
		enterRule(_localctx, 88, RULE_initMessage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(LPAREN);
			setState(277);
			match(T__31);
			setState(278);
			side();
			setState(279);
			unum();
			setState(280);
			playmode();
			setState(281);
			match(RPAREN);
			setState(282);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u011f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0092\n\17\f\17\16\17\u0095"+
		"\13\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\5\22\u00a0\n\22\3"+
		"\22\5\22\u00a3\n\22\3\22\5\22\u00a6\n\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\5\"\u00cf\n\"\3#\5#\u00d2\n#\3#\5#\u00d5\n#\3#\5#\u00d8\n#\3"+
		"#\5#\u00db\n#\3#\5#\u00de\n#\3#\5#\u00e1\n#\3#\5#\u00e4\n#\3#\5#\u00e7"+
		"\n#\3#\5#\u00ea\n#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\7%\u00f7\n%\f%\16"+
		"%\u00fa\13%\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\5(\u0105\n(\3)\3)\3*\3*\3*\3"+
		"*\3*\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\2\2/\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\2\t\3\2\3\4\3\2\5\7\3\2#$\3\2\f\23\3\2\30\31\4\2+,\60\60\3\2 !\2\u0108"+
		"\2\\\3\2\2\2\4^\3\2\2\2\6`\3\2\2\2\bf\3\2\2\2\nh\3\2\2\2\fj\3\2\2\2\16"+
		"l\3\2\2\2\20s\3\2\2\2\22u\3\2\2\2\24w\3\2\2\2\26}\3\2\2\2\30\177\3\2\2"+
		"\2\32\u0084\3\2\2\2\34\u0089\3\2\2\2\36\u0099\3\2\2\2 \u009b\3\2\2\2\""+
		"\u009d\3\2\2\2$\u00a7\3\2\2\2&\u00a9\3\2\2\2(\u00ab\3\2\2\2*\u00ad\3\2"+
		"\2\2,\u00af\3\2\2\2.\u00b1\3\2\2\2\60\u00b3\3\2\2\2\62\u00b5\3\2\2\2\64"+
		"\u00b7\3\2\2\2\66\u00b9\3\2\2\28\u00bb\3\2\2\2:\u00bd\3\2\2\2<\u00bf\3"+
		"\2\2\2>\u00c1\3\2\2\2@\u00c3\3\2\2\2B\u00ce\3\2\2\2D\u00d1\3\2\2\2F\u00eb"+
		"\3\2\2\2H\u00f2\3\2\2\2J\u00fe\3\2\2\2L\u0100\3\2\2\2N\u0104\3\2\2\2P"+
		"\u0106\3\2\2\2R\u0108\3\2\2\2T\u0110\3\2\2\2V\u0112\3\2\2\2X\u0114\3\2"+
		"\2\2Z\u0116\3\2\2\2\\]\t\2\2\2]\3\3\2\2\2^_\t\3\2\2_\5\3\2\2\2`a\7&\2"+
		"\2ab\7\b\2\2bc\5\2\2\2cd\5\4\3\2de\7\'\2\2e\7\3\2\2\2fg\t\4\2\2g\t\3\2"+
		"\2\2hi\t\4\2\2i\13\3\2\2\2jk\t\4\2\2k\r\3\2\2\2lm\7&\2\2mn\7\t\2\2no\5"+
		"\b\5\2op\5\n\6\2pq\5\f\7\2qr\7\'\2\2r\17\3\2\2\2st\t\4\2\2t\21\3\2\2\2"+
		"uv\t\4\2\2v\23\3\2\2\2wx\7&\2\2xy\7\n\2\2yz\5\20\t\2z{\5\22\n\2{|\7\'"+
		"\2\2|\25\3\2\2\2}~\t\4\2\2~\27\3\2\2\2\177\u0080\7&\2\2\u0080\u0081\7"+
		"\13\2\2\u0081\u0082\5\26\f\2\u0082\u0083\7\'\2\2\u0083\31\3\2\2\2\u0084"+
		"\u0085\7&\2\2\u0085\u0086\t\5\2\2\u0086\u0087\7#\2\2\u0087\u0088\7\'\2"+
		"\2\u0088\33\3\2\2\2\u0089\u008a\7&\2\2\u008a\u008b\7\24\2\2\u008b\u008c"+
		"\5J&\2\u008c\u008d\5\6\4\2\u008d\u008e\5\16\b\2\u008e\u008f\5\24\13\2"+
		"\u008f\u0093\5\30\r\2\u0090\u0092\5\32\16\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\'\2\2\u0097\u0098\7\2\2\3\u0098"+
		"\35\3\2\2\2\u0099\u009a\7,\2\2\u009a\37\3\2\2\2\u009b\u009c\7\25\2\2\u009c"+
		"!\3\2\2\2\u009d\u009f\7\26\2\2\u009e\u00a0\5\36\20\2\u009f\u009e\3\2\2"+
		"\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3\5T+\2\u00a2\u00a1"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a6\5 \21\2\u00a5"+
		"\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6#\3\2\2\2\u00a7\u00a8\7\27\2\2"+
		"\u00a8%\3\2\2\2\u00a9\u00aa\t\4\2\2\u00aa\'\3\2\2\2\u00ab\u00ac\t\4\2"+
		"\2\u00ac)\3\2\2\2\u00ad\u00ae\t\4\2\2\u00ae+\3\2\2\2\u00af\u00b0\t\4\2"+
		"\2\u00b0-\3\2\2\2\u00b1\u00b2\t\4\2\2\u00b2/\3\2\2\2\u00b3\u00b4\t\4\2"+
		"\2\u00b4\61\3\2\2\2\u00b5\u00b6\t\6\2\2\u00b6\63\3\2\2\2\u00b7\u00b8\7"+
		"-\2\2\u00b8\65\3\2\2\2\u00b9\u00ba\7\32\2\2\u00ba\67\3\2\2\2\u00bb\u00bc"+
		"\7\33\2\2\u00bc9\3\2\2\2\u00bd\u00be\7.\2\2\u00be;\3\2\2\2\u00bf\u00c0"+
		"\7\34\2\2\u00c0=\3\2\2\2\u00c1\u00c2\7\35\2\2\u00c2?\3\2\2\2\u00c3\u00c4"+
		"\7/\2\2\u00c4A\3\2\2\2\u00c5\u00cf\5\64\33\2\u00c6\u00cf\5:\36\2\u00c7"+
		"\u00cf\5<\37\2\u00c8\u00cf\5@!\2\u00c9\u00cf\5\"\22\2\u00ca\u00cf\5\66"+
		"\34\2\u00cb\u00cf\58\35\2\u00cc\u00cf\5> \2\u00cd\u00cf\5$\23\2\u00ce"+
		"\u00c5\3\2\2\2\u00ce\u00c6\3\2\2\2\u00ce\u00c7\3\2\2\2\u00ce\u00c8\3\2"+
		"\2\2\u00ce\u00c9\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cb\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cfC\3\2\2\2\u00d0\u00d2\5&\24\2"+
		"\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d5"+
		"\5L\'\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6"+
		"\u00d8\5(\25\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\3\2"+
		"\2\2\u00d9\u00db\5*\26\2\u00da\u00d9\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u00de\5,\27\2\u00dd\u00dc\3\2\2\2\u00dd\u00de\3\2"+
		"\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1\5.\30\2\u00e0\u00df\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e4\5\60\31\2\u00e3\u00e2\3"+
		"\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e7\5\62\32\2\u00e6"+
		"\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00ea\5\62"+
		"\32\2\u00e9\u00e8\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaE\3\2\2\2\u00eb\u00ec"+
		"\7&\2\2\u00ec\u00ed\7&\2\2\u00ed\u00ee\5B\"\2\u00ee\u00ef\7\'\2\2\u00ef"+
		"\u00f0\5D#\2\u00f0\u00f1\7\'\2\2\u00f1G\3\2\2\2\u00f2\u00f3\7&\2\2\u00f3"+
		"\u00f4\7\36\2\2\u00f4\u00f8\5J&\2\u00f5\u00f7\5F$\2\u00f6\u00f5\3\2\2"+
		"\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb"+
		"\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\7\'\2\2\u00fc\u00fd\7\2\2\3\u00fd"+
		"I\3\2\2\2\u00fe\u00ff\7#\2\2\u00ffK\3\2\2\2\u0100\u0101\t\4\2\2\u0101"+
		"M\3\2\2\2\u0102\u0105\5L\'\2\u0103\u0105\7*\2\2\u0104\u0102\3\2\2\2\u0104"+
		"\u0103\3\2\2\2\u0105O\3\2\2\2\u0106\u0107\t\7\2\2\u0107Q\3\2\2\2\u0108"+
		"\u0109\7&\2\2\u0109\u010a\7\37\2\2\u010a\u010b\5J&\2\u010b\u010c\5N(\2"+
		"\u010c\u010d\5P)\2\u010d\u010e\7\'\2\2\u010e\u010f\7\2\2\3\u010fS\3\2"+
		"\2\2\u0110\u0111\7#\2\2\u0111U\3\2\2\2\u0112\u0113\7+\2\2\u0113W\3\2\2"+
		"\2\u0114\u0115\t\b\2\2\u0115Y\3\2\2\2\u0116\u0117\7&\2\2\u0117\u0118\7"+
		"\"\2\2\u0118\u0119\5X-\2\u0119\u011a\5T+\2\u011a\u011b\5V,\2\u011b\u011c"+
		"\7\'\2\2\u011c\u011d\7\2\2\3\u011d[\3\2\2\2\22\u0093\u009f\u00a2\u00a5"+
		"\u00ce\u00d1\u00d4\u00d7\u00da\u00dd\u00e0\u00e3\u00e6\u00e9\u00f8\u0104";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}