/*
 * This file is part of the Omicron2D RoboCup 2D Soccer Simulation team.
 * Copyright (c) 2021 Matt Young. All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.omicron2d.debug;

import org.tinylog.Level;
import org.tinylog.core.LogEntry;
import org.tinylog.core.LogEntryValue;
import org.tinylog.provider.InternalLogger;
import org.tinylog.writers.AbstractFormatPatternWriter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

// note: this is in Java because it is literally impossible in the wretched language known as Kotlin to elegantly
// implement a primary constructor that calls another a secondary constructor
// based on: https://github.com/tinylog-org/tinylog/blob/v2.0/tinylog-impl/src/main/java/org/tinylog/writers/ConsoleWriter.java
public class WebSocketWriter extends AbstractFormatPatternWriter {
    private Level errorLevel;

    public WebSocketWriter(){
        this(Collections.emptyMap());
    }

    public WebSocketWriter(Map<String, String> properties){
        super(properties);

        String stream = properties.get("stream");
        if (stream == null) {
            errorLevel = Level.WARN;
        } else if ("err".equalsIgnoreCase(stream)) {
            errorLevel = Level.TRACE;
        } else if ("out".equalsIgnoreCase(stream)) {
            errorLevel = Level.OFF;
        } else {
            InternalLogger.log(Level.ERROR, "Stream must be \"out\" or \"err\", \"" + stream + "\" is an invalid stream name");
            errorLevel = Level.WARN;
        }
    }

    @Override
    public Collection<LogEntryValue> getRequiredLogEntryValues() {
        Collection<LogEntryValue> logEntryValues = super.getRequiredLogEntryValues();
        logEntryValues.add(LogEntryValue.LEVEL);
        return logEntryValues;
    }

    @Override
    public void write(final LogEntry logEntry) {
        DebugServer.INSTANCE.transmit("log", -1, render(logEntry));
        // TODO infer ID based on string scan (e.g. [Think 12] or [Agent 0])
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }
}
