// Generated from /home/matt/workspace/omicron2d/src/main/antlr/PlannerConfig.g4 by ANTLR 4.9
package io.github.omicron2d.ai.planning;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PlannerConfigParser}.
 */
public interface PlannerConfigListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(PlannerConfigParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(PlannerConfigParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#conditionList}.
	 * @param ctx the parse tree
	 */
	void enterConditionList(PlannerConfigParser.ConditionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#conditionList}.
	 * @param ctx the parse tree
	 */
	void exitConditionList(PlannerConfigParser.ConditionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#preConditions}.
	 * @param ctx the parse tree
	 */
	void enterPreConditions(PlannerConfigParser.PreConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#preConditions}.
	 * @param ctx the parse tree
	 */
	void exitPreConditions(PlannerConfigParser.PreConditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#postConditions}.
	 * @param ctx the parse tree
	 */
	void enterPostConditions(PlannerConfigParser.PostConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#postConditions}.
	 * @param ctx the parse tree
	 */
	void exitPostConditions(PlannerConfigParser.PostConditionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#actionName}.
	 * @param ctx the parse tree
	 */
	void enterActionName(PlannerConfigParser.ActionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#actionName}.
	 * @param ctx the parse tree
	 */
	void exitActionName(PlannerConfigParser.ActionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(PlannerConfigParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(PlannerConfigParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlannerConfigParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(PlannerConfigParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlannerConfigParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(PlannerConfigParser.DocumentContext ctx);
}