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