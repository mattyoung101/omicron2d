# AI implementation

Hopefully this will be the final version of this document.

## Concept
- Things the robot can do, of vary levels of complexity. From move to point to score a goal.
- Organised into high level (score a goal), low level (move to point) and generic behaviours (sequence/selector).
- Generic behaviours make up a behaviour tree, like "Sequence" and "Selector".
- Agent loads appropriate behaviour tree for given play mode/communication setup. Communication is handled separately and
can override the current behaviour tree.
- Behaviour trees defined in YAML.

## Implementation details
**IMPORTANT**

- MOST IMPORTANT: WE ARE GOING to have to refactor ALL the behaviour stuff so that it returns its status and
applies its updates to an object, rather than returning its updates and having a separate method for report status.
This way, any behaviour is basically the same as any other behaviour. 

- So we can get rid of MovementBehaviour and  MovementManager, since they are all really just behaviours that operate on 
the agent ctx (which will then store movement data) in different ways. Also, FollowPath for example will literally
just be a node, that has a sequence node, that has MoveToPointLooking in it. Much cleaner overall.

**END IMPORTANT**

- Rewrite Behaviour interface to be an abstract class that supports parent/child nodes (DONE)
- We will also have to rewrite the behaviour subclasses so that they can work with generic nodes. The problem is that
how do we actually get a steering output eventually out of something like Sequence?
- Rewrite MovementManager/HeadManager
- How to handle the 3 different types of behaviours generically and cleanly?

## Extra notes
- What about FollowPath and other behaviours that use a nested MovementManager?
    - This is OK, they will simply be refactored to use a nested behaviour tree sequence instead
    - Note that this requires the ability to programatically define trees as well as load them from YAML
    

# V2 (dumb)
- We have keep our FSM stuff for MovementManager (but remove the queue)
- We have a SEPARATE node graph thing for all the BTree stuff. BTree nodes defines in YAML, they would be like
GoToBall, which would then make ITS OWN MovementManager to make a FollowPath and get to the goal

- OK, great, but then how the do we get the bloody OUTPUT OF the FollowPath BACK TO THE AGENT???
  - We still need the thing where it applies directly to the agent in the ctx, not through return values
    - May as well just do V1 then where we use the same system
  - Otherwise we would have to pump it back via the output of the BTree nodes