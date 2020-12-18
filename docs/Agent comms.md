# New communications plan
This document contains my new plan for server communications.

We will use a Java `BitSet` to construct a message. This stream of bits will then be encoded into a sequence of bytes,
which in turn will be encoded into a sequence of characters to be sent using the `say` command.

## Protocol description
### Message header

| Indices (inclusive)     | Size | Description                     |
|-------------------------|------|---------------------------------|
| 0                       | 1    | Relay flag                      |
| 1-4                     | 4    | Sender 0-indexed ID             |
| 5-8                     | 4    | Recipient 0-indexed ID          |
| 9-10                    | 2    | Message priority                |
| 11-16                   | 6    | Message ID                      |
| 17..._n_                | _n_  | Message contents                |

The message contents will change depending on ID. Message ID is 6 bits (it was only going to be 5) to pad one
extra bit.

Message priorities are 0, 1, 2 or 3 with 3 being the highest and 0 the lowest. They control which messages the
agent should relay to other agents.

### Messages
Note that from here on in, we reset the index counter. This is all from bit 17 onwards in the message, it's the 
message contents field.

#### Object location message
**Message ID: 0**

| Indices (inclusive)     | Size     | Description                     |
|-------------------------|----------|---------------------------------|
| 0-6                     | 6        | Object ID                       |
| 6-7                     | 2        | Grid selector                   |
| Variable                | Variable | Grid index of object            |

Object IDs:

- 0-10: Our team agents, last agent is goalie
- 11-21: Opposition agents, last agent is goalie
- 22: Ball
- The rest are currently reserved

To save bandwidth, instead of sending the actual position of objects we send the index that they would hold on
a variable size grid on the field. The grid is flattened from the top left corner like an array, and the index the
object is closest to is sent.

Grids:

- Grid 0: 1:16 size
- Grid 1: 1:8 size
- Grid 2: 1:4 size
- Grid 3: 1:2 size

The grid index size will change based on the grid selected.

## Example message
Not relayed, sent by agent 7, needs to be received by agent 10, priority 2, object location message. The object is
a ball, grid size 2, index 384.

```
Header                Contents
[0][111][1010][10][0] [10110][10][110000000]
```

Final message bits: `011110101001011010110000000` (27 bits). Could fit in a 32-bit integer or 4 bytes.

Using the rcsc encoder, this generates the string `(3ptc` which is only 5 chars.

Using our encoder it comes out as `bb4Xrr/` which is 7 chars.