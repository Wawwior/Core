/*
 * Copyright (c) 2021. Wawwior
 * All Rights Reserved.
 */

package me.wawwior.core.command.commands;

import com.mojang.brigadier.context.CommandContext;
import me.wawwior.core.Core;
import net.forthecrown.grenadier.CommandSource;
import net.forthecrown.grenadier.command.AbstractCommand;
import net.forthecrown.grenadier.command.BrigadierCommand;
import org.bukkit.ChatColor;

public class CoreCommand extends AbstractCommand {


    public CoreCommand() {
        super("core", Core.CORE);

        register();
    }

    @Override
    protected void createCommand(BrigadierCommand command) {
        command
                .executes(this::sendVersion)
                .then(
                        literal("version")
                                .executes(this::sendVersion)
                );
    }

    private int sendVersion(CommandContext<CommandSource> context) {
        CommandSource source = context.getSource();

        source.sendMessage(ChatColor.GRAY + "§oRunning Core " + Core.version);
        return 1;
    }
}
