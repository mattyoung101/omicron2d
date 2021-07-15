/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
$(document).ready(() => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    
    const agentId = urlParams.get("id");
    $("#agentId").text(agentId);
});

function handleMessage(msgId, agentId, msgContents){
    if (msgId == "selfModel"){
        // draw ourselves to canvas
        console.log("HAVE SELF MODEL MESSAGE");
    }
}