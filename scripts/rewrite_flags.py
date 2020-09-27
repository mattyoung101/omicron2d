#  This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
#  Copyright (c) 2020 Matt Young. All rights reserved.
#
#  This Source Code Form is subject to the terms of the Mozilla Public
#  License, v. 2.0. If a copy of the MPL was not distributed with this
#  file, You can obtain one at http://mozilla.org/MPL/2.0/.

# this file is a terrible hack
import string

code = """
M_landmark_map[Flag_C]    = Vector2D(           0.0,           0.0 );
M_landmark_map[Flag_CT]   = Vector2D(           0.0, -pitch_half_w );
M_landmark_map[Flag_CB]   = Vector2D(           0.0, +pitch_half_w );
M_landmark_map[Flag_LT]   = Vector2D( -pitch_half_l, -pitch_half_w );
M_landmark_map[Flag_LB]   = Vector2D( -pitch_half_l, +pitch_half_w );
M_landmark_map[Flag_RT]   = Vector2D( +pitch_half_l, -pitch_half_w );
M_landmark_map[Flag_RB]   = Vector2D( +pitch_half_l, +pitch_half_w );

M_landmark_map[Flag_PLT]  = Vector2D( -(pitch_half_l - penalty_l), -penalty_half_w );
M_landmark_map[Flag_PLC]  = Vector2D( -(pitch_half_l - penalty_l),             0.0 );
M_landmark_map[Flag_PLB]  = Vector2D( -(pitch_half_l - penalty_l), +penalty_half_w );
M_landmark_map[Flag_PRT]  = Vector2D( +(pitch_half_l - penalty_l), -penalty_half_w );
M_landmark_map[Flag_PRC]  = Vector2D( +(pitch_half_l - penalty_l),             0.0 );
M_landmark_map[Flag_PRB]  = Vector2D( +(pitch_half_l - penalty_l), +penalty_half_w );

M_landmark_map[Flag_GLT]  = Vector2D( -pitch_half_l, -goal_half_w );
M_landmark_map[Flag_GLB]  = Vector2D( -pitch_half_l, +goal_half_w );
M_landmark_map[Flag_GRT]  = Vector2D( +pitch_half_l, -goal_half_w );
M_landmark_map[Flag_GRB]  = Vector2D( +pitch_half_l, +goal_half_w );

M_landmark_map[Flag_TL50] = Vector2D( -50.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TL40] = Vector2D( -40.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TL30] = Vector2D( -30.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TL20] = Vector2D( -20.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TL10] = Vector2D( -10.0, -pitch_half_w - 5.0 );

M_landmark_map[Flag_T0]   = Vector2D(  0.0, -pitch_half_w - 5.0 );

M_landmark_map[Flag_TR10] = Vector2D( +10.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TR20] = Vector2D( +20.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TR30] = Vector2D( +30.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TR40] = Vector2D( +40.0, -pitch_half_w - 5.0 );
M_landmark_map[Flag_TR50] = Vector2D( +50.0, -pitch_half_w - 5.0 );

M_landmark_map[Flag_BL50] = Vector2D( -50.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BL40] = Vector2D( -40.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BL30] = Vector2D( -30.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BL20] = Vector2D( -20.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BL10] = Vector2D( -10.0,  pitch_half_w + 5.0 );

M_landmark_map[Flag_B0]   = Vector2D(   0.0,  pitch_half_w + 5.0);

M_landmark_map[Flag_BR10] = Vector2D( +10.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BR20] = Vector2D( +20.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BR30] = Vector2D( +30.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BR40] = Vector2D( +40.0,  pitch_half_w + 5.0 );
M_landmark_map[Flag_BR50] = Vector2D( +50.0,  pitch_half_w + 5.0 );

M_landmark_map[Flag_LT30] = Vector2D( -pitch_half_l - 5.0, -30.0 );
M_landmark_map[Flag_LT20] = Vector2D( -pitch_half_l - 5.0, -20.0 );
M_landmark_map[Flag_LT10] = Vector2D( -pitch_half_l - 5.0, -10.0 );

M_landmark_map[Flag_L0]   = Vector2D( -pitch_half_l - 5.0,   0.0 );

M_landmark_map[Flag_LB10] = Vector2D( -pitch_half_l - 5.0,  10.0 );
M_landmark_map[Flag_LB20] = Vector2D( -pitch_half_l - 5.0,  20.0 );
M_landmark_map[Flag_LB30] = Vector2D( -pitch_half_l - 5.0,  30.0 );

M_landmark_map[Flag_RT30] = Vector2D( +pitch_half_l + 5.0, -30.0 );
M_landmark_map[Flag_RT20] = Vector2D( +pitch_half_l + 5.0, -20.0 );
M_landmark_map[Flag_RT10] = Vector2D( +pitch_half_l + 5.0, -10.0 );

M_landmark_map[Flag_R0]   = Vector2D( +pitch_half_l + 5.0,   0.0 );

M_landmark_map[Flag_RB10] = Vector2D( +pitch_half_l + 5.0,  10.0 );
M_landmark_map[Flag_RB20] = Vector2D( +pitch_half_l + 5.0,  20.0 );
M_landmark_map[Flag_RB30] = Vector2D( +pitch_half_l + 5.0,  30.0 );"""


def process_line(line):
    remove = ["M_landmark_map", "[", "]", " = Vector2D(", ");", "\t"]
    for element in remove:
        line = line.replace(element, "")
    return line


def add_spaces(text):
    numbers = "".join([s for s in text if s.isdigit()])
    if len(numbers) == 0:
        # no numbers, return string directly
        return " ".join(text)
    else:
        text_no_numbers = "".join([s for s in text if not s.isdigit()])
        return " ".join(text_no_numbers) + " " + numbers


if __name__ == "__main__":
    lines = [x for x in code.split("\n") if x.strip() != ""]
    lines = [process_line(line) for line in lines]

    print("# Generated using rewrite_flags.py")
    for line in lines:
        data = [x for x in line.split(" ") if x.strip() != ""]
        flag = data[0].replace("Flag_", "f").lower()
        flag_name = add_spaces(flag)
        rest = " ".join(data[1:])
        print(f"{flag_name}: {rest}")
