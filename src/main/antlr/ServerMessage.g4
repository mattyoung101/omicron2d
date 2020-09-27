/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2020 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
grammar ServerMessage;

// ANTLR grammar for messages received from rcssserver
// Most of this is based on the RoboCup 2D Soccer Simulation manual dated 2003 (available in the docs folder) and
// also the incomplete but more up to date docs available at https://rcsoccersim.github.io/manual/soccerserver.html

// SYMBOL TABLE
// space = sequence
// | = alternative
// + = one or more
// * = zero or more
// ? = optional

// TODO: redefine all numbers to be just "NUMBER" which is a float or an integer, then cast in code (maybe?)

// sense body message
viewQuality
    : 'high' | 'low' ;

viewAngle
    : 'narrow' | 'normal' | 'wide' ;

viewModeMsg
    : LPAREN 'view_mode' viewQuality viewAngle RPAREN ;

stamina
    : FLOAT
    | INTEGER ;

effort
    : FLOAT
    | INTEGER ;

capacity
    : INTEGER
    | FLOAT ;

staminaMsg
    : LPAREN 'stamina' stamina effort capacity RPAREN ;

amountOfSpeed
    : FLOAT
    | INTEGER ;

directionOfSpeed
    : FLOAT
    | INTEGER ;

speedMsg
    : LPAREN 'speed' amountOfSpeed directionOfSpeed RPAREN ;

headAngle
    : FLOAT
    | INTEGER ;

headAngleMsg
    : LPAREN 'head_angle' headAngle RPAREN ;

// we don't currently care about the counts, so this rule matches all of them
useless
    : LPAREN
    ('kick' | 'dash' | 'turn' | 'say' | 'turn_neck' | 'catch' | 'move' | 'change_view')
    INTEGER
    RPAREN ;

// note: the sense_body documentation in the 2003 manual is out of date, you need to use the online docs instead!
// (one of the rare instances they're actually up to date)
senseBodyMessage
    : LPAREN 'sense_body' time viewModeMsg staminaMsg speedMsg headAngleMsg useless* RPAREN EOF ;

// see message
teamName
    : QUOTED_TEXT ;

goalie
    : 'goalie' ;

playerName
    : 'p' teamName? unum? goalie? ;

// can't really make this a lexer rule or it matches all upper case Ps (maybe we could? not sure?)
playerBehind
    : 'P' ;

distance
    : FLOAT
    | INTEGER ;

distChange
    : FLOAT
    | INTEGER ;

dirChange
    : FLOAT
    | INTEGER ;

headFaceDir
    : INTEGER
    | FLOAT ;

bodyFaceDir
    : INTEGER
    | FLOAT ;

flagName
    : FLAG_NAME ;

flagBehind
    : 'F' ;

goalBehind
    : 'G' ;

goalName
    : GOAL_NAME ;

ballName
    : 'b' ;

ballBehind
    : 'B' ;

lineName
    : LINE_NAME ;

objectName
    : flagName
    | goalName
    | ballName
    | lineName
    | playerName
    | flagBehind
    | goalBehind
    | ballBehind
    | playerBehind ;

objectContents
    : distance direction distChange? dirChange? headFaceDir? bodyFaceDir?  ;

seeObject
    : LPAREN LPAREN objectName RPAREN objectContents RPAREN ;

seeMessage
    : LPAREN 'see' time seeObject+ RPAREN EOF ;


// hear message
time
    : INTEGER ;

direction
    : FLOAT
    | INTEGER ;

sender
    : direction
    | MESSAGE_SENDER ;

// the say message can either be a quoted string, a playmode, or some other thing the ref sends (since the ref doesn't
// actually send quoted messages), like yellow_card_l_1
sayMessage
    : QUOTED_TEXT
    | REF_MESSAGE
    | PLAYMODE ;

hearMessage
    : LPAREN 'hear' time sender sayMessage RPAREN EOF ;


// init message
unum
    : INTEGER ;

playmode
    : PLAYMODE ;

side
    : 'l'
    | 'r' ;

initMessage
    : LPAREN 'init' side unum playmode RPAREN EOF ;

// lexer rules
INTEGER
    : ('+' | '-')? DIGITS ;

// double/float (ignore all the exp stuff like E+10, hope it never happens)
FLOAT
    : ('+' | '-')? DIGITS PERIOD DIGITS ;

DIGITS
    : [0-9]+;

LPAREN
    : '(' ;

RPAREN
    : ')' ;

QUOTE
    : '"' ;

PERIOD
    : '.' ;

// message senders that aren't from players (used in say message)
MESSAGE_SENDER
    : 'online_coach_left'
    | 'online_coach_right'
    | 'coach'
    | 'referee'
    | 'self' ;

PLAYMODE
    : 'before_kick_off'
    | 'time_over'
    | 'play_on'
    | 'kick_off_l'
    | 'kick_off_r'
    | 'kick_in_l'
    | 'kick_in_r'
    | 'free_kick_l'
    | 'free_kick_r'
    | 'corner_kick_l'
    | 'corner_kick_r'
    | 'goal_kick_l'
    | 'goal_kick_r'
    | 'goal_l'
    | 'goal_r'
    | 'drop_ball'
    | 'offside_l'
    | 'offside_r'
    | 'penalty_kick_l'
    | 'penalty_kick_r'
    | 'first_half_over'
    | 'pause'
    | 'human_judge'
    | 'foul_charge_l'
    | 'foul_charge_r'
    | 'foul_push_l'
    | 'foul_push_r'
    | 'foul_multiple_attack_l'
    | 'foul_multiple_attack_r'
    | 'foul_ballout_l'
    | 'foul_ballout_r'
    | 'back_pass_l'
    | 'back_pass_r'
    | 'free_kick_fault_l'
    | 'free_kick_fault_r'
    | 'catch_fault_l'
    | 'catch_fault_r'
    | 'indirect_free_kick_l'
    | 'indirect_free_kick_r'
    | 'penalty_setup_l'
    | 'penalty_setup_r'
    | 'penalty_ready_l'
    | 'penalty_ready_r'
    | 'penalty_taken_l'
    | 'penalty_taken_r'
    | 'penalty_miss_l'
    | 'penalty_miss_r'
    | 'penalty_score_l'
    | 'penalty_score_r'
    | 'illegal_defense_l'
    | 'illegal_defense_r' ;

// quoted text that appears in a message, for example say message or team name
// it is not technically correct to use this as a team name because it matches illegal team names, but, it's impossible
// to lex otherwise and that's for the server to deal with anyways!
QUOTED_TEXT
    : QUOTE [a-zA-Z0-9().+*/?<>_ ]* QUOTE ;

// pulled from page 33 of the manual
FLAG_NAME
    : 'f c'
    | 'f ' [lcr] ' ' [tb]
    | 'f ' [pg] ' ' [lr] ' ' [tcb]
    | 'f ' [lrtb] ' 0'
    | 'f ' [lrtb] ' ' [lrtb] ' ' ('10' | '20' | '30' | '40' | '50') ;

GOAL_NAME
    : 'g ' ('l' | 'r') ;

LINE_NAME
    : 'l ' ('l' | 'r' | 't' | 'b') ;

// say message from a referee
// probably keep this last so everything else is matched before it, to reduce errors
REF_MESSAGE
    : [0-9a-z_]+ ;

WS
   : (' ' | '\n' | '\t' | '\r')+ -> skip
   ;