# Server Communications Plan

1. UDP socket receives data message string
2. S-expression parsed by jsexp
3. Determine message type and dispatch to correct deserialiser
4. Deserialised message is transmitted to the rest of the project

- Maybe layer it like how the IP stack is layered (general project design goals though)

## Communication design
Probably do like this how librcsc does it, with a PlayerAgent that extends a general SoccerAgent.