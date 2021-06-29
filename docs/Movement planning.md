# Movement planner

This is the docs for how the agent figures out how to get around. I think this may be complicated, because we want
to move such that we avoid moving obstacles (players) - we'll have to do prediction on that. We also want to move
in such a way that is beneficial to our teammates, but detrimental to our opposition.

Algorithms to be considered:

- Rapidly exploring random tree
- Some form of triangulation, followed by ???
- Quadtrees followed by A*

Currently I'm thinking we do quadtrees, then A*, but the weight for each A-star node is going to be more complicated
than just distance. We will have to make sure our heuristic is admissible. Since our world is small, we could probably 
get away with just doing Dijkstra's (saves implementing and verifying  a heuristic).