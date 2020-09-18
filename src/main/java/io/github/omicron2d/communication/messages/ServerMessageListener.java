// Generated from /home/matt/workspace/omicron2d/src/main/antlr/ServerMessage.g4 by ANTLR 4.8
package io.github.omicron2d.communication.messages;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ServerMessageParser}.
 */
public interface ServerMessageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#teamName}.
	 * @param ctx the parse tree
	 */
	void enterTeamName(ServerMessageParser.TeamNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#teamName}.
	 * @param ctx the parse tree
	 */
	void exitTeamName(ServerMessageParser.TeamNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#playerName}.
	 * @param ctx the parse tree
	 */
	void enterPlayerName(ServerMessageParser.PlayerNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#playerName}.
	 * @param ctx the parse tree
	 */
	void exitPlayerName(ServerMessageParser.PlayerNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#playerBehind}.
	 * @param ctx the parse tree
	 */
	void enterPlayerBehind(ServerMessageParser.PlayerBehindContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#playerBehind}.
	 * @param ctx the parse tree
	 */
	void exitPlayerBehind(ServerMessageParser.PlayerBehindContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#distance}.
	 * @param ctx the parse tree
	 */
	void enterDistance(ServerMessageParser.DistanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#distance}.
	 * @param ctx the parse tree
	 */
	void exitDistance(ServerMessageParser.DistanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#seeDirection}.
	 * @param ctx the parse tree
	 */
	void enterSeeDirection(ServerMessageParser.SeeDirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#seeDirection}.
	 * @param ctx the parse tree
	 */
	void exitSeeDirection(ServerMessageParser.SeeDirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#distChange}.
	 * @param ctx the parse tree
	 */
	void enterDistChange(ServerMessageParser.DistChangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#distChange}.
	 * @param ctx the parse tree
	 */
	void exitDistChange(ServerMessageParser.DistChangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#dirChange}.
	 * @param ctx the parse tree
	 */
	void enterDirChange(ServerMessageParser.DirChangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#dirChange}.
	 * @param ctx the parse tree
	 */
	void exitDirChange(ServerMessageParser.DirChangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#headFaceDir}.
	 * @param ctx the parse tree
	 */
	void enterHeadFaceDir(ServerMessageParser.HeadFaceDirContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#headFaceDir}.
	 * @param ctx the parse tree
	 */
	void exitHeadFaceDir(ServerMessageParser.HeadFaceDirContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#bodyFaceDir}.
	 * @param ctx the parse tree
	 */
	void enterBodyFaceDir(ServerMessageParser.BodyFaceDirContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#bodyFaceDir}.
	 * @param ctx the parse tree
	 */
	void exitBodyFaceDir(ServerMessageParser.BodyFaceDirContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#flagName}.
	 * @param ctx the parse tree
	 */
	void enterFlagName(ServerMessageParser.FlagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#flagName}.
	 * @param ctx the parse tree
	 */
	void exitFlagName(ServerMessageParser.FlagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#flagBehind}.
	 * @param ctx the parse tree
	 */
	void enterFlagBehind(ServerMessageParser.FlagBehindContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#flagBehind}.
	 * @param ctx the parse tree
	 */
	void exitFlagBehind(ServerMessageParser.FlagBehindContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#goalBehind}.
	 * @param ctx the parse tree
	 */
	void enterGoalBehind(ServerMessageParser.GoalBehindContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#goalBehind}.
	 * @param ctx the parse tree
	 */
	void exitGoalBehind(ServerMessageParser.GoalBehindContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#goalName}.
	 * @param ctx the parse tree
	 */
	void enterGoalName(ServerMessageParser.GoalNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#goalName}.
	 * @param ctx the parse tree
	 */
	void exitGoalName(ServerMessageParser.GoalNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#ballName}.
	 * @param ctx the parse tree
	 */
	void enterBallName(ServerMessageParser.BallNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#ballName}.
	 * @param ctx the parse tree
	 */
	void exitBallName(ServerMessageParser.BallNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#ballBehind}.
	 * @param ctx the parse tree
	 */
	void enterBallBehind(ServerMessageParser.BallBehindContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#ballBehind}.
	 * @param ctx the parse tree
	 */
	void exitBallBehind(ServerMessageParser.BallBehindContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#lineName}.
	 * @param ctx the parse tree
	 */
	void enterLineName(ServerMessageParser.LineNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#lineName}.
	 * @param ctx the parse tree
	 */
	void exitLineName(ServerMessageParser.LineNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#objectName}.
	 * @param ctx the parse tree
	 */
	void enterObjectName(ServerMessageParser.ObjectNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#objectName}.
	 * @param ctx the parse tree
	 */
	void exitObjectName(ServerMessageParser.ObjectNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#objectContents}.
	 * @param ctx the parse tree
	 */
	void enterObjectContents(ServerMessageParser.ObjectContentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#objectContents}.
	 * @param ctx the parse tree
	 */
	void exitObjectContents(ServerMessageParser.ObjectContentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#seeObject}.
	 * @param ctx the parse tree
	 */
	void enterSeeObject(ServerMessageParser.SeeObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#seeObject}.
	 * @param ctx the parse tree
	 */
	void exitSeeObject(ServerMessageParser.SeeObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#seeMessage}.
	 * @param ctx the parse tree
	 */
	void enterSeeMessage(ServerMessageParser.SeeMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#seeMessage}.
	 * @param ctx the parse tree
	 */
	void exitSeeMessage(ServerMessageParser.SeeMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(ServerMessageParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(ServerMessageParser.TimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#direction}.
	 * @param ctx the parse tree
	 */
	void enterDirection(ServerMessageParser.DirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#direction}.
	 * @param ctx the parse tree
	 */
	void exitDirection(ServerMessageParser.DirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#sender}.
	 * @param ctx the parse tree
	 */
	void enterSender(ServerMessageParser.SenderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#sender}.
	 * @param ctx the parse tree
	 */
	void exitSender(ServerMessageParser.SenderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#sayMessage}.
	 * @param ctx the parse tree
	 */
	void enterSayMessage(ServerMessageParser.SayMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#sayMessage}.
	 * @param ctx the parse tree
	 */
	void exitSayMessage(ServerMessageParser.SayMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#hearMessage}.
	 * @param ctx the parse tree
	 */
	void enterHearMessage(ServerMessageParser.HearMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#hearMessage}.
	 * @param ctx the parse tree
	 */
	void exitHearMessage(ServerMessageParser.HearMessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#unum}.
	 * @param ctx the parse tree
	 */
	void enterUnum(ServerMessageParser.UnumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#unum}.
	 * @param ctx the parse tree
	 */
	void exitUnum(ServerMessageParser.UnumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#playmode}.
	 * @param ctx the parse tree
	 */
	void enterPlaymode(ServerMessageParser.PlaymodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#playmode}.
	 * @param ctx the parse tree
	 */
	void exitPlaymode(ServerMessageParser.PlaymodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#side}.
	 * @param ctx the parse tree
	 */
	void enterSide(ServerMessageParser.SideContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#side}.
	 * @param ctx the parse tree
	 */
	void exitSide(ServerMessageParser.SideContext ctx);
	/**
	 * Enter a parse tree produced by {@link ServerMessageParser#initMessage}.
	 * @param ctx the parse tree
	 */
	void enterInitMessage(ServerMessageParser.InitMessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link ServerMessageParser#initMessage}.
	 * @param ctx the parse tree
	 */
	void exitInitMessage(ServerMessageParser.InitMessageContext ctx);
}