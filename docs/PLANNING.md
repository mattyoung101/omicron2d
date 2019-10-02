# Systems

Our agent will consist of two main sub-systems: the movement planner and the behaviour planner. The behaviour planner
provides movement instructions to the movement planner.

## Behaviour planner
The way the behaviour planner works is either a behaviour tree or some online planning algorithm like GOAP.
The BP's goal is to determine the most optimal course of action for each agent to perform. To make this decision,
it will need to calculate the absolute position of itself, the ball and other agents. For complex behaviours like
passing, it will also need to communicate with other agents on the field and perhaps with the coach.

The behaviour planner will be different depending on what position the agent took, for example goal keeper, defender,
mid, striker, etc. Agents should be able to switch up their positions on request or go out of bounds of them if they
feel they need to in order to score a point.

We will probably need entire sub-subsystems to plan out extremely complex behaviours like passing and maybe kickoffs.

When we receive an update from the ref like free_kick or something, the behaviour tree evaluator will load up the
free kick behaviour tree. How do we decide which agent goes and fetches the ball?

### Thoughts on planning algorithms
Probably going to go with behaviour trees. While they are basic I don't currently have the skills to implement
neural online planners like WrightEagle are using. I don't feel like GOAP would be real-time enough but I may be wrong.
A hierarchical finite state machine is also another option but I feel it's the weakest and would lead to ugly code.

Change this, we're totally going to use GOAP or a related planning algorithm such as SHOP, STRIPS or hierarchical task
networks. The reason being is that we may be able to feed the future plan into the movement algorithm for smarter
long term moves and it may help us in teammate collaboration.

## Movement planner
Once the behaviour planner has decided the course of action, it will then calculate how to move on the field with
the movement planner. This will be quite complex and I'm not sure how to do it yet, some sort of path planning
algorithm will be required, I've seen lots of teams using Voronoi diagrams or rapidly exploring random trees. I was
personally thinking of implementing path planning with Bezier curves but the question still remains as *where* exactly
we want to go given that there's many optimal solutions to a question such as "how do I get this ball into the goal".

In addition to this, we have to solve an optimisation problem to determine for example how much power we put into
our kicks and dashes. This is another complex problem which I'm not sure what algorithm could be used to solve.

Note that the movement planner will also handle not only directional moving but also kicking and grabbing and everything.

## Blackboards and inter-agent communication
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
Behaviour planner (plan on what to do) 
--> Movement planner (plan on where to go/look) 
--> Movement executor (turn it into server commands)
--> Atan (Java library for rcssserver)
--> rcsserver commands (text)
--> rcsserver  (sent via UDP)

# Execution process
1. First we will load the initial team positioning (4-3-3 or the like) from the config file created with FormationEditor
2. 