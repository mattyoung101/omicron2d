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

// SYMBOL TABLE
// Source: https://tomassetti.me/ebnf/
// and https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687036/ANTLR+Cheat+Sheet
//
// space = sequence
// | = alternative
// + = one or more
// * = zero or more
// ? = optional

// see message
seeMessage
    : LPAREN 'see' time RPAREN EOF ;


// hear message
time
    : INTEGER ;

direction
    : FLOAT ;

sender
    : direction
    | MESSAGE_SENDER ;

sayMessage
    : PLAYER_MESSAGE
    | REF_MESSAGE ;

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
// integer number
INTEGER
    : ('+' | '-')? DIGITS ;

// double/float (ignore all the exp stuff (E+10), hope it never happens)
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

// say message from another player
PLAYER_MESSAGE
    : QUOTE [a-zA-Z0-9).+*/?<>_ ]+ QUOTE ;

// say message from a referee
REF_MESSAGE
    : [0-9a-z_]+ ;

WHITESPACE
   : (' ' | '\n' | '\t' | '\r')+ -> skip
   ;