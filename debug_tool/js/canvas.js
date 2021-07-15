/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

// Implementation is derived from the code for rcssmonitor:
// - https://github.com/rcsoccersim/rcssmonitor/blob/master/src/options.cpp
// - https://github.com/rcsoccersim/rcssmonitor/blob/master/src/field_painter.cpp
// both files are LGPL, might make this file and thus all JavaScript LGPL too

const SCALE = 8.56; // derived from rcssmonitor preferences
const CANVAS_WIDTH = 1028;
const CANVAS_HEIGHT = 720;

const FIELD_CENTRE = {"x": CANVAS_WIDTH / 2.0, "y":CANVAS_HEIGHT / 2.0};
const PITCH_LENGTH = 105.0;
const PITCH_WIDTH = 68.0;
const PITCH_HALF_LENGTH = PITCH_LENGTH * 0.5;
const PITCH_HALF_WIDTH = PITCH_WIDTH * 0.5;
const CENTER_CIRCLE_R = 9.15;
const PENALTY_AREA_LENGTH = 16.5;
const PENALTY_AREA_WIDTH = 40.32;
const PENALTY_CIRCLE_R = 9.15;
const PENALTY_SPOT_DIST = 11.0;
const GOAL_WIDTH = 14.02;
const GOAL_HALF_WIDTH = GOAL_WIDTH * 0.5;
const GOAL_AREA_LENGTH = 5.5;
const GOAL_AREA_WIDTH = 18.32;
const GOAL_DEPTH = 2.44;
const CORNER_ARC_R = 1.0;
const GOAL_POST_RADIUS = 0.06;

function scale(value){
    return Math.round(value * SCALE);
}

function screenX(value){
    return FIELD_CENTRE.x + scale(value);
}

function screenY(value){
    return FIELD_CENTRE.y + scale(value);
}

/** draws a line on the canvas, beginPath() must already be called */
function drawLine(ctx, x0, y0, x1, y1){
    ctx.moveTo(x0, y0);
    ctx.lineTo(x1, y1);
}

function drawFieldLines(ctx){
    // set screen coordinates of field
    const left_x   = screenX(-PITCH_HALF_LENGTH );
    const right_x  = screenX(+PITCH_HALF_LENGTH );
    const top_y    = screenY(-PITCH_HALF_WIDTH );
    const bottom_y = screenY(+PITCH_HALF_WIDTH );

    // draw field lines
    ctx.strokeStyle = "white";
    ctx.beginPath();
    drawLine(ctx, left_x, top_y, right_x, top_y);
    drawLine(ctx, right_x, top_y, right_x, bottom_y );
    drawLine(ctx, right_x, bottom_y, left_x, bottom_y );
    drawLine(ctx, left_x, bottom_y, left_x, top_y );
    ctx.stroke();

    // centre line
    ctx.beginPath();
    drawLine(ctx, FIELD_CENTRE.x, top_y, FIELD_CENTRE.x, bottom_y);
    ctx.stroke();

    // centre circle
    ctx.beginPath();
    const centre_radius = scale(CENTER_CIRCLE_R);
    ctx.ellipse(FIELD_CENTRE.x, FIELD_CENTRE.y, centre_radius, centre_radius, 0, 0, Math.PI * 2);
    ctx.stroke();
}

function drawPenaltyAreaLines(ctx){
    // set screen coordinates of field
    const left_x  = screenX(-PITCH_HALF_LENGTH);
    const right_x = screenX(+PITCH_HALF_LENGTH);
    const pen_top_y    = screenY(-PENALTY_AREA_WIDTH*0.5);
    const pen_bottom_y = screenY(+PENALTY_AREA_WIDTH*0.5);
    let pen_x = screenX( -(PITCH_HALF_LENGTH - PENALTY_AREA_LENGTH ));
    const pen_spot_x = screenX( -(PITCH_HALF_LENGTH - PENALTY_SPOT_DIST ));

    // left rectangle
    ctx.strokeStyle = "white";
    ctx.beginPath();
    drawLine(ctx, left_x, pen_top_y, pen_x, pen_top_y );
    drawLine(ctx, pen_x, pen_top_y, pen_x, pen_bottom_y );
    drawLine(ctx, pen_x, pen_bottom_y, left_x, pen_bottom_y );
    ctx.stroke();

    // right penalty area X
    pen_x = screenX( +( PITCH_HALF_LENGTH - PENALTY_AREA_LENGTH ) );
    ctx.beginPath();
    drawLine(ctx, right_x, pen_top_y, pen_x, pen_top_y );
    drawLine(ctx, pen_x, pen_top_y, pen_x, pen_bottom_y );
    drawLine(ctx, pen_x, pen_bottom_y, right_x, pen_bottom_y );
    ctx.stroke();
}

function drawGoalAreaLines(ctx){
    // set screen coordinates of field
    const left_x  = screenX( - PITCH_HALF_LENGTH );
    const right_x = screenX( + PITCH_HALF_LENGTH );

    // set coordinates opts
    const goal_area_y_abs = scale( GOAL_AREA_WIDTH*0.5 );
    const goal_area_top_y = FIELD_CENTRE.y - goal_area_y_abs;
    const goal_area_bottom_y = FIELD_CENTRE.y + goal_area_y_abs;

    // left goal area
    let goal_area_x = screenX( - PITCH_HALF_LENGTH + GOAL_AREA_LENGTH );

    ctx.strokeStyle = "white";
    ctx.beginPath();
    drawLine(ctx, left_x, goal_area_top_y, goal_area_x, goal_area_top_y );
    drawLine(ctx, goal_area_x, goal_area_top_y, goal_area_x, goal_area_bottom_y );
    drawLine(ctx, goal_area_x, goal_area_bottom_y, left_x, goal_area_bottom_y );
    ctx.stroke();

    // right goal area
    goal_area_x = screenX(PITCH_HALF_LENGTH - GOAL_AREA_LENGTH );
    ctx.beginPath();
    drawLine(ctx, right_x, goal_area_top_y, goal_area_x, goal_area_top_y );
    drawLine(ctx, goal_area_x, goal_area_top_y, goal_area_x, goal_area_bottom_y );
    drawLine(ctx, goal_area_x, goal_area_bottom_y, right_x, goal_area_bottom_y );
    ctx.stroke();
}

function drawGoals(ctx){
    const goal_top_y = screenY( - GOAL_WIDTH*0.5 );
    const goal_size_x = scale(GOAL_DEPTH );
    const goal_size_y = scale(GOAL_WIDTH );

    ctx.fillStyle = "black";
    ctx.fillRect(screenX( -PITCH_HALF_LENGTH - GOAL_DEPTH ) - 1, goal_top_y, goal_size_x, goal_size_y );
    ctx.fillRect(screenX(PITCH_HALF_LENGTH ) + 1, goal_top_y, goal_size_x, goal_size_y);
}

function drawFlags(ctx){
    // TODO
}

function refreshFieldDisplay(){
    let canvas = $("#field")[0];
    let ctx = canvas.getContext("2d");

    // clear frame
    ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

    // draw background
    ctx.fillStyle = "rgb(31, 160, 31)";
    ctx.fillRect(0, 0, 1028, 720);

    drawFieldLines(ctx);
    drawPenaltyAreaLines(ctx);
    drawGoalAreaLines(ctx);
    drawGoals(ctx);
}

$(document).ready(() => {
    refreshFieldDisplay();
});