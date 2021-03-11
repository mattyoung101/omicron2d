# Omicron2D

Omicron2D aims to be a team competing in the [RoboCup Soccer 2D Simulation league](https://rcsoccersim.github.io/). It
is currently in early stages of development. This project is written entirely from scratch in Kotlin, and runs on the
JVM (whereas most other teams in 2D use the librcsc library and C++ as their language of choice).

Omicron2D derives its name from [Team Omicron](http://teamomicron.github.io/), a team who would have been competing in
RoboCup Jr Open Soccer Internationals in Bordeaux, France in 2020. Unfortunately, this was cancelled due to the COVID-19
pandemic. Other than the name and developer, this project has no similarity or relation to Team Omicron.

Omicron2D is currently an independent project consisting of only one developer: me, Matt Young (previously
software engineer on the RoboCup Jr team). This means development will likely be very slow! However, any feedback
or assistance is welcomed (just open a GitHub issue).

## Project status

Updated: March 2020

**Note:** As it turns out, university life is actually quite busy - so I can probably only work on Omicron2D on the 
holidays and weekends if I'm free and motivated. This means that development will be a lot slower since I'm only a 
one man team. Hopefully we'll have acceptable gameplay by the time I graduate (~3 years from now!!)

Working on basic movement behaviours, then will do some higher level ones. Currently have MoveToPoint, MoveToPointLooking,
TurnBodyTo and Wait behaviours implemented.

Previously finished:

- STRIPS planner and custom plan definition DSL mostly implemented
- Agent can localise its position with high accuracy
- All team members can join and align themselves in formation
- Basic stuff like parser, networking, all works

## About the agents

If you want to know more about the agents, please check out some Markdown files in the docs folder (such as PLANNING.md)
and/or read the code. In the future when I'm closer to the tournament, a team description paper (TDP) will be
prepared.

Novel features of Omicron2D plan to be:

- Behaviour planning using a simple STRIPS-inspired symbolic planning algorithm
- Iterative Closest Point (ICP) for efficient and accurate localisation
- Complex data exchange with fellow teammates using the `say` command with only 10 characters per message
- Implemented from scratch using Kotlin, rather than librcsc/agent2d and C++ as is done normally

As well as standard features like behaviours/skills, networking, parsing, etc.

## Building/running

**Local run (for development):**

TBA: basically clone the project, open in IntelliJ and run TeamMain.kt (for the whole team) or Main.kt (for one agent).

TBA: also cover running tests.

**Preparing for competition:**

TODO

**Running in competition:**

TODO

## Licence

This repo is licenced under the Mozilla Public License v2.0, see LICENSE.txt.

See ATTRIBUTION.txt for libraries which require attribution in project documentation.
