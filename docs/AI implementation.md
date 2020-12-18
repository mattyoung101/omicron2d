# AI implementation
How we're going to implement agent behaviour.

Our agent is made up of a bunch of facets, or strategies. The GOAP system decides **what** we want to do, and then
(something else?) decides **how** to do it.

We could also divide this into short and long term planning.

config_behaviour.yml will contain tasks and their definitions, and that will load a class of the same name which has
code on how to execute it on the inside. We could also just do it with inheritance which would be better and skip the 
YAML.

So for example, the GetBall task will choose a MovementStrategy and a HeadingStrategy. Where we want to go, and where
we want to look. And maybe also a NetworkingStrategy for communication.

## Notes 18 Dec 2020
I'm thinking something like this:

1. Planner decides what actions/behaviours to do, to achieve our goal (i.e. score a goal, defend our goal)
2. Planner assembles a list of tasks/skills that are hardcoded by us (e.g. MoveToPoint)
3. We execute those skills

So basically we need the planner to be able to decompose tasks well. Maybe, we could dump all our tasks on it,
and hope it's good enough to just "figure out" what it has to do. Thogh this might not be possible.

Look into using the JSHOP2 HTN planner.

### How do we model our robot?
Since omni-directional dash has been added, let's model our robot as an omni-directional drive robot like in
RoboCup.

We can drive in any direction at any time. We can turn our head +-180 degrees from the angle we're currently facing.
It might be sensible to model this as "we can turn to any angle", and have the code automatically calculate the most
optimal body angle and worry about the head the most. We can't see all of the field at once, we must scan.

### Final thoughts
- Skills will execute actions on the field
- Skill output is passed to a SkillExecutor which will actually generate the commandss
- Pluggable planning infrastructure: support HTN planners (JSHOP2), HFSM, behaviour tree and machine learning all in one.