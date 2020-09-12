# Omicron2D

Omicron2D aims to be a team competing in RoboCup Soccer 2D Simulation. It is currently in very early stages of development.
This repo contains the Kotlin code, running on the JVM, that powers our soccer agents.

Omicron2D is related to Team Omicron, an official team competing in RoboCup Jr Open Soccer.
Omicron2D, however, is currently just an independent side-project consisting of only one member: me, Matt Young (software
engineer on the RoboCup Jr team). If you feel like getting involved, don't hesitate to get in touch :)

If you have any questions, please contact Matt Young (matt.young.1@outlook.com).

## Project status
**The project has been postponed due to the parser library, Parboiled, causing me too much grief.**

The simple truth of the matter is Parboiled doesn't work. It is too insufferably complicated to write rules for, literally,
every time I want to write a new parser I have to fight off random problems with ZERO useful debugging info
for hours and hours and hours. What am I supposed to do with `Unwrapped action expression`!!! There's no help in the docs,
nothing online anywhere, it's ridiculous. I made a grave mistake using this over other, more common frameworks.
 
The project will be resumed once I have the time and mental energy to rewrite all the parsers in ANTLR or JavaCC.

This is all with respect to the Parboiled developers. It probably does work, but it's not for me. Good riddance.

## About the agents
If you want to know more about our agents, please check out some Markdown files in the docs folder (such as
PLANNING.md) and/or read the code. In the future when we're closer to the tournament, a team description
paper (TDP) will be prepared.

## Building/running
**To build:**

TBA: basically clone the project, open in IntelliJ and run Main.kt

**To run:**

TBA: more complicated, talk about command line parameters, etc.

## License
This repo is licenced under the Mozilla Public License v2.0, see LICENSE.txt.

## Libraries and licenses
See ATTRIBUTION.txt