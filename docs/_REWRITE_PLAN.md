# Codebase rewrite plan
July 2020.

## Why rewrite?
- Old codebase was kind of bad, not happy with it
- Want to write custom server interaction library instead of using Atan, since Atan is very old and it's easier to write
a custom parser (maybe). Will recycle parts of Atan if required and librcsc
- Want to more thoroughly plan a better structure for everything

## What are the end goals of the project?
- Be vaguely competitive with agent2d despite writing everything from scratch
    - Is this unreasonable? Possibly. We'll have to find out.

## Things to do
### Important
- Use Apache Commons Math instead of Vectorz.
- Write or find S-expr parser to interact with rcssserver
    - Perhaps this: https://github.com/julianmendez/jsexp
- Use soccerwindow2 for visualising and for debugging, so we will give it debug strings
- Implement localisation either via nonlinear least squares or triangulation (or something else?)
- Write unit tests
- Rewrite tools to output JSON using kotlinx.serialisation or GSON
- Maybe rewrite blackboard
- Make constants loadable from json/yaml config file or maybe toml
- Refactor tools, move to separate package perhaps

### Less important
- Update README
- Changed license to MPL 2.0
- Clean up docs folder
- Reconsider comms protocol idea
- Find a better way of launching the agents. They shouldn't be just one thread in one big app should they?
    - How do the pro teams do it?
- Read through librcsc more and use concepts from there if necessary
    
## Why not use agent2d?
(I suppose this is self-justification)

Basically, I just want to do something myself. I could probably submit agent2d as an agent, but, you need to change
stuff in it, like what Fractals have done by adding much more complex multi agent planning. Unfortunately I don't
know anything about that, so instead it would be cool if code I had written could be vaguely competitive with
the baseline agent2d. Then maybe we can add stuff on afterwards if I learn it.

Also, changing things up sometimes is a good idea. My understanding is that a lot of the agents are agent2d with
extra stuff added on, maybe it's a good idea to do things from scratch. Who knows.