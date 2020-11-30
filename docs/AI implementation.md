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