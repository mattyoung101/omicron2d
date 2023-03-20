# Omicron2D

Omicron2D aims to be a team competing in the [RoboCup Soccer 2D Simulation league](https://rcsoccersim.github.io/). It
is currently in early stages of development. This project is written entirely from scratch in Kotlin, and runs on the
JVM. Comparatively, while most teams in RoboCup 2D are a fork of HELIOS' base C++ source code (or use librcsc/agent2d in some form),
Omicron2D aims to be completely original and written from the ground up.

Development on this project has been cancelled (see below).

The project is licenced under the Mozilla Public License v2.0, see LICENSE.txt for info.

## Project status

Last updated: July 2021

**Status:** In case it's not already clear, the Omicron2D project has been canned. This is mostly because I lost interest in RoboCup 2D Sim League, and also got busy with other projects. However, all is not lost - although the agents don't really do much, there's a lot of useful stuff in here. I think this repo could reasonably used as a base if you are looking to start your own RoboCup Sim League team that doesn't use rcsoccersim. Main highlights are:

- Agent networking with rcssserver
- ANTLR-based parser for most server messages (with tests)
- Fast Iterative Closest Point (ICP) localisation
- Nice behaviour tree and world model system (in my opinion, of course)

Good luck. If there's any questions you have, feel free to open a ticket and I'll have a look.

## About the agents

If you want to know more about the agents, please check out some Markdown files in the docs folder (such as PLANNING.md)
and/or read the code. In the future when I'm closer to the tournament, a team description paper (TDP) will be
prepared.

Novel features of Omicron2D plan to be (subject to a lot of redesigns and changes):

- Behaviour planning using a custom behaviour tree framework
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
This repo is licenced under the Mozilla Public License v2.0, see LICENSE.txt. For info on complying with the MPL 2.0
see [this FAQ](https://www.mozilla.org/en-US/MPL/2.0/FAQ/).

See ATTRIBUTION.txt for libraries used in the project which require attribution in project documentation.
