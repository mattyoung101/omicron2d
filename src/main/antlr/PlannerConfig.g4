/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
grammar PlannerConfig;

// ANTLR grammar for the domain-specific planner configuration language used to list tasks for STRIPS/GOAP planning.
// This is also known as "OMGCfg", short for OMicron2D GOAP Configuration
// File extension will be *.omplan
// Also: the reason why this was created, compared to using something like YAML is to provide a syntax that I like
// and isn't too verbose. Plus, as you can see by the grammar, it's really not a complicated language at all :)

// TODO add support for describing cost
// TODO (important) add support for OR conditions like (!InPriority OR !Stationary)

/*
Code example:

// this is a comment
action MyAction
    : [This !That !NotThis]
    -> [Stuff CoolStuff !MoreStuff] ;
*/

// parser rules
condition
    : NOT? LITERAL ;

conditionList
    : LBRACKET condition* RBRACKET ;

preConditions
    : conditionList ;

postConditions
    : conditionList ;

actionName
    : LITERAL ;

action
    : 'action' actionName COLON preConditions ARROW postConditions SEMICOLON ;

document
    : action+ EOF ;

// lexer rules
COLON
    : ':' ;

LBRACKET
    : '[' ;

RBRACKET
    : ']' ;

LPAREN
    : '(' ;

RPAREN
    : ')' ;

OR
    : ('OR' | '||') ;

SEMICOLON
    : ';' ;

ARROW
    : '->' ;

NOT
    : '!' ;

LITERAL
    : [a-zA-Z0-9]+ ;

// ignore whitespace
WS
   : (' ' | '\n' | '\t' | '\r')+ -> skip
   ;

// source: https://stackoverflow.com/a/7074214/5007892
COMMENT
    :  '//' ~('\r' | '\n')* -> skip
    ;