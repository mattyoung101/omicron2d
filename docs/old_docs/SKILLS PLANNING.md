# List of skills we should have

Each skill can access the thread local blackboard to pull information for decisions.

We should also add the relative data for the ball to the blackboard for simple updates like getting distance to it.

## Basic skills
### ArriveAt(point, targetAngle=null)
Arrive steering behaviour. Aims to land exactly on the point (which is in field coords).

Option to set target orientation to arrive at (will lerp, or snap to at the end).

### SeekTo(point, targetAngle=null)
Seeks to an absolute field coordinate point. Aims to touch the point but doesn't try to arrive at it (slow down).

### DashInDirection(direction, speed)
Combines the dash and rotate commands to run in a direction. The agent will rotate to the direction, then move.

### KickInDirection(direction, power)
If the agent is holding the ball, kicks the ball in an absolute direction. The agent will rotate to face the direction,
then kick.

### CatchBall
Decides if the ball can be caught and if so will catch it. Only applies to goalie.

### ScanField
The agent will change its view quality then rotate around in a full circle to scan the field (via action queue).

## Advanced skills
### NavigateTo(point, orientation=null, avoidBall=false)
Generates a navigation plan to arrive at a point while avoiding other agents and obstacles. This behaviour uses 
ArriveAtAbsolute and SeekToAbsolute internally.

This will be quite complex and probably be the Bezier curve thingy we were planning (curves may not be entirely
optimal in 2D space?). If we do use curves, then TinySpline is the library for it.

### GoalieIntercept
The goalie will DashInDirection quickly towards the ball to block it, and then try to CatchBall to pick it up.

It will try to predict the velocity of the ball to intercept it just in time.

### GoalKick
The agent (goalie?) will take a goal kick towards an agent who's willing to accept it.

### GoalieAlign
The goalie will try to align itself to stop the ball. This could be sort of similar to the curve defender we currently
use in RoboCup Jr, except with the added benefit of being able to have the ball's absolute position and (hopefully)
velocity.

### PassTo(agentId, agentPos)
Sets up a pass between agents. This will be quite complicated, unsure how to do it actually. May require networking
(via "say") to coordinate the pass and who it's going to and stuff?