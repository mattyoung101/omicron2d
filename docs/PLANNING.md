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

## Movement planner
Once the behaviour planner has decided the course of action, it will then calculate how to move on the field with
the movement planner. This will be quite complex and I'm not sure how to do it yet, some sort of path planning
algorithm will be required, I've seen lots of teams using Voronoi diagrams or rapidly exploring random trees. I was
personally thinking of implementing path planning with Bezier curves but the question still remains as *where* exactly
we want to go given that there's many optimal solutions to a question such as "how do I get this ball into the goal".

In addition to this, we have to solve an optimisation problem to determine for example how much power we put into
our kicks and dashes. This is another complex problem which I'm not sure what algorithm could be used to solve.

Note that the movement planner will also handle not only directional moving but also kicking and grabbing and everything.

# Execution process
1. First we will load the initial team positioning (4-3-3 or the like) from the config file created with FormationEditor
2. 