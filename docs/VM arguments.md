# VM arguments to use
Mainly in case IntelliJ decides to nuke my run config like it does occasionally.

## Final arguments
`-XX:+UseStringDeduplication --add-opens java.base/java.lang=ALL-UNNAMED -DignoreNullServerMessages -DstartSimTool`

## Tell JVM to shut up
`-add-opens java.base/java.lang=ALL-UNNAMED`

**Description:** This is VERY important since Java 11 keeps complaining about illegal reflective access stuff.
Source: https://stackoverflow.com/a/46230678/5007892

## Debugging options
`-DignoreNullServerMessages -DstartSimTool`

**Description:** Custom flags for some pieces of code.

ignoreNullServerMessages: Does not throw an exception if PlayerAgent receives a null server message. Normally would be
a timeout, but this causes problems during breakpoint suspension in debugging.

startSimTool: If set, the AgentLauncher will start rcsoccersim on launch to make repeated testing easier.