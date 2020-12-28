// Generated from /home/matt/workspace/omicron2d/src/main/antlr/PlannerConfig.g4 by ANTLR 4.9
package io.github.omicron2d.ai.planning;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PlannerConfigParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PlannerConfigVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(PlannerConfigParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#conditionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionList(PlannerConfigParser.ConditionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#preConditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreConditions(PlannerConfigParser.PreConditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#postConditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostConditions(PlannerConfigParser.PostConditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#actionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionName(PlannerConfigParser.ActionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(PlannerConfigParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlannerConfigParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(PlannerConfigParser.DocumentContext ctx);
}