// Generated from /home/matt/workspace/omicron2d/src/main/antlr/ServerMessage.g4 by ANTLR 4.8
package io.github.omicron2d.communication.messages;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ServerMessageParser}.
 */
public interface ServerMessageListener extends ParseTreeListener {
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