/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.command;

import net.forthecrown.grenadier.command.AbstractCommand;

import java.util.ArrayList;

public class CommandRegistry {

    private static final ArrayList<AbstractCommand> commands = new ArrayList<>();

    public static void register(AbstractCommand command) {
        if (commands.stream().noneMatch(c -> c.getName().equalsIgnoreCase(command.getName()))) {
            commands.add(command);
        }
    }
}
