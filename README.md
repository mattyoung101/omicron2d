# Omicron2D

Omicron2D aims to be a team competing in RoboCup Soccer 2D Simulation. It is currently in very early stages of development.
This repo contains the Kotlin code, running on the JVM, that powers our soccer agents.

Omicron2D is related to Team Omicron, an official team competing in RoboCup Jr Open Soccer.
Omicron2D, however, is currently just an independent side-project consisting of only one member: me, Matt Young (software
engineer on the RoboCup Jr team). If you feel like getting involved, don't hesitate to get in touch :)

## Project status
Updated: 1 December 2020

The agent can localise its position and orientation to high levels of accuracy. An entire team of Omicron2D agents can
be launched, who align themselves in a formation (which can be generated using a graphical editor). With that done,
I'm now planning out the full AI infrastructure for movement and planning, and finishing off world modelling code.

## About the agents
If you want to know more about our agents, please check out some Markdown files in the docs folder (such as
PLANNING.md) and/or read the code. In the future when we're closer to the tournament, a team description
paper (TDP) will be prepared.

Novel features of Omicron2D plan to be:

- Behaviour planning using Goal Oriented Action Planning (GOAP), a STRIPS-like planning algorithm
- Iterative Closest Point (ICP) for efficient and accurate localisation
- Complex data exchange with fellow teammates using the `say` command with only 10 characters per message
- Implemented from scratch using Kotlin, rather than librcsc/agent2d and C++ as is done normally

## Building/running
**To build:**

TBA: basically clone the project, open in IntelliJ and run Main.kt

TBA: also cover instructions on how to build dist version (for comp).

**To run:**

TBA: cover how to run locally, and how to run for competition.

TBA: also cover running unit tests.

## License
This repo is licenced under the Mozilla Public License v2.0, see LICENSE.txt.

See ATTRIBUTION.txt for libraries which require attribution in project documentation.
