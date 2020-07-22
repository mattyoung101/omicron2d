# VM arguments to use
Mainly in case IntelliJ decides to nuke my run config like it does occasionally.

## Final arguments
`-XX:+UseStringDeduplication --add-opens java.base/java.lang=ALL-UNNAMED`

## Tell JVM to shut up
`-add-opens java.base/java.lang=ALL-UNNAMED`

**Description:** This is VERY important since Java 11 keeps complaining about illegal reflective access stuff.
Source: https://stackoverflow.com/a/46230678/5007892

