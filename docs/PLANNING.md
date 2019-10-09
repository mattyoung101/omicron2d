# Main AI

Our agent will consist of two main sub-systems: the movement planner and movement executor. The planner will use GOAP
to decide which actions to perform to score perform the agent's field role, and the executor implements the skills
to do it.

## Movement planner
The movement planner looks at the world and lays out a list of skills and their parameters it will execute to have
the agent perform a certain goal, such as the goalie blocking or the striker scoring.

The movement planner will be different depending on what position the agent took, for example goal keeper, defender,
mid, striker, etc. Agents could be able to switch up their positions on request or go out of bounds of them if they
feel they need to in order to score a point.

We will probably need entire sub-subsystems to plan out extremely complex behaviours like passing and maybe kickoffs.

When we receive an update from the ref like free_kick or something, the planner will load up the free kick plan. 
How do we decide which agent goes and fetches the ball?

In addition to this, we have to solve an optimisation problem to determine for example how much power we put into
our kicks and dashes. This is another complex problem which I'm not sure what algorithm could be used to solve.

Note that the movement planner will also handle not only directional moving but also kicking and grabbing and everything.
It's more like an action planner actually.

### Path planning
I've seen lots of teams using Voronoi diagrams or rapidly exploring random trees. I was
personally thinking of implementing path planning with Bezier curves but the question still remains as *where* exactly
we want to go given that there's many optimal solutions to a question such as "how do I get this ball into the goal".

How do we integrate paths into GOAP? We may need a "NavigateTo" skill which will navigate to the position and avoid
other objects. Because we're 2d sim we have some flexibility like clipping through objects or bumping into other agents.

### Thoughts on planning algorithms
Change this, we're totally going to use GOAP or a related planning algorithm such as SHOP, STRIPS or hierarchical task
networks. The reason being is that we may be able to feed the future plan into the movement algorithm for smarter
long term moves and it may help us in teammate collaboration.

If this fails, behaviour trees are the way to go.

### Skills or actions
I think what we should do is have various skills or actions that the movement planner can execute, which are slightly
higher level than what the rcssserver offers. Intelligent behaviour will be made by combining these skills and executing
them (this is what the executor does). So the behaviour planner decides what to do, movement planner decides how to do it
(i.e. positions to move to) and the movement executor will do actually do it.

Example skills include MoveAbsolute (quickly but carefully manoeuvres to an absolute position on the field, gdx-ai's
Arrive behaviour), DashRelative (dashes in a direction relative to the player quickly like our real life robots do).

## Note about "behaviour planner"
There used to be a behaviour planner subsytem whose job was to decide "what to do". Looking more at GOAP, it would seem
that the behaviour planner step is useless and can just be integrated into movement. After all, we're only really deciding
where to move with a bit of extra planning for communication.

## Local blackboards
Used for communication between systems. Just a class of variables

# Team communication
Commonly used in AI, basically sharing information between agents. Now in our case, it's really interesting because
the blackboard will have to be distributed and synchronised very infrequently using only 10 bytes of data per 
transmission. Perhaps we could use the coach who can send 512 bytes of data every 30s, if we can communicate with the 
coach each agent can upload its own variables and download all the others. Otherwise, we'll have to develop some
distributed (dare I say almost _blockchain_ like system) of sharing knowledge, where a robot can put in a specific
request for another element of the robot's blackboard. Our blackboards should also probably be namespaced either just
in the name (as you would in C) or with sub HashMaps (we will represent the blackboard as a HashMap). Instead of sending
strings as requests, we could send hash codes of strings.

Another problem is that the connection protocol of rcssserver's say command is non-existent in fact it's even worse than
UDP because we can get a response from anyone, even other teams' players. Each transaction will need to have a unique 
ID associated with it, and the agents will have to store each request in a buffer so that they can keep track of the 
response to the request they sent out earlier.

# Localisation
We should localise using a particle filter, it's well suited for this task and a team called Brainstormers have used
it in the past.

# Layout tree
Main planners (assemble skills, path plan, communicate, etc) 
--> Movement executor (turn it into server commands)
--> Atan (Java library for rcssserver)
--> rcssserver commands (text)
--> rcssserver  (sent via UDP)