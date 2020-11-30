# General notes
Contains some notes and information about the RoboCup server and other Omicron2D related stuff.

## Synch mode
Unclear what this is. Nothing in the 2003 manual or the new website. Both librcsc and WrightEagle implement it,
but from looking at the Fractals vs HELIOS 2019 finals match, it appears it is not used in matches. The server reports
that `(synch_mode 0)`. So most likely it's probably a debugging tool in that case.

## synch_see
Information on this: https://github.com/rcsoccersim/rcssserver/blob/1b5076d0e7fd24f4c168d00446be94d66807b139/NEWS#L710

And its implementation:
https://github.com/rcsoccersim/rcssserver/blob/630667b06a5acbf1262abbc5d76fede959335dc3/src/player.cpp#L1658

## Sending multiple commands
Looks like the server actually accepts multiple commands from each client, so you can go like `(dash ...)(move_neck ..)`.
We should do this. It's unclear whether the server cares if you send it multiple UDP packets per tick cycle, or if it
wants them all in one.

## Log file format
Especially when browsing https://archive.robocup.info/, the *.rcg files are ones that describe the position of each
object in the world that you load up in soccerwindow2, it appears. The *.rcl files are the actual logs of every
command sent by every agent.

## Coaches
Doesn't look like anyone uses them, especially not the CLang stuff. Logs show that people basically just use them to
cycle agent types. Doesn't look like anyone is even using them to predict the world, might be some room for research
there, though probably too smart for me.