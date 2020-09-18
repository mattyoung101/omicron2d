// Generated from /home/matt/workspace/omicron2d/src/main/antlr/ServerMessage.g4 by ANTLR 4.8
package io.github.omicron2d.communication.messages;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ServerMessageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ServerMessageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#teamName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTeamName(ServerMessageParser.TeamNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#playerName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayerName(ServerMessageParser.PlayerNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#playerBehind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayerBehind(ServerMessageParser.PlayerBehindContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#distance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistance(ServerMessageParser.DistanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#seeDirection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeeDirection(ServerMessageParser.SeeDirectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#distChange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistChange(ServerMessageParser.DistChangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#dirChange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirChange(ServerMessageParser.DirChangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#headFaceDir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeadFaceDir(ServerMessageParser.HeadFaceDirContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#bodyFaceDir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyFaceDir(ServerMessageParser.BodyFaceDirContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#flagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlagName(ServerMessageParser.FlagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#flagBehind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlagBehind(ServerMessageParser.FlagBehindContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#goalBehind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoalBehind(ServerMessageParser.GoalBehindContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#goalName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoalName(ServerMessageParser.GoalNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#ballName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBallName(ServerMessageParser.BallNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#ballBehind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBallBehind(ServerMessageParser.BallBehindContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#lineName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineName(ServerMessageParser.LineNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#objectName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectName(ServerMessageParser.ObjectNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#objectContents}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectContents(ServerMessageParser.ObjectContentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#seeObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeeObject(ServerMessageParser.SeeObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#seeMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeeMessage(ServerMessageParser.SeeMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime(ServerMessageParser.TimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#direction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirection(ServerMessageParser.DirectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#sender}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSender(ServerMessageParser.SenderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#sayMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSayMessage(ServerMessageParser.SayMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#hearMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHearMessage(ServerMessageParser.HearMessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#unum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnum(ServerMessageParser.UnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#playmode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlaymode(ServerMessageParser.PlaymodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#side}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSide(ServerMessageParser.SideContext ctx);
	/**
	 * Visit a parse tree produced by {@link ServerMessageParser#initMessage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitMessage(ServerMessageParser.InitMessageContext ctx);
}