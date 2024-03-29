/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
// Manages the WebSocket

console.log("Opening socket");
const socket = new WebSocket("ws://localhost:8081/agents/");
let notRespondingTimeout;

function rescheduleTimeout(){
    $("#connStatus").text("ONLINE");
    clearTimeout(notRespondingTimeout);

    notRespondingTimeout = setTimeout(() => {
        console.log("Server is not responding!");
        $("#connStatus").text("OFFLINE");
        // TODO auto-retry
        // TODO make text red to indicate server is offline
    }, 1000);
}

socket.addEventListener("open", (event) => {
    console.log("Socket opened!");
    rescheduleTimeout();
});

socket.addEventListener("message", (event) => {
    //console.log("Received message from server!");
    let message = JSON.parse(event.data);

    let msgId = message.msgId;
    let agentId = message.agentId;
    let msgContents = message.payload;

    console.log(`[${agentId}] -> [${msgId}]:`);
    console.log(message.payload);

    // refresh field display if we have one (players will then be drawn on top)
    if (typeof refreshFieldDisplay === "function"){
        refreshFieldDisplay();
    }

    // dispatch message to relevant handler (if it exists)
    // note: bad code
    if (typeof handleMessage === "function"){
        handleMessage(msgId, agentId, msgContents);
    }

    rescheduleTimeout();
});

window.addEventListener("beforeunload", (event) => {
    console.log("Closing socket");
    socket.close();
});