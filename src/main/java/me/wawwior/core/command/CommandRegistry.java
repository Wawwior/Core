package me.wawwior.core.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
	
	CommandDispatcher<CommandSourceStack> dispatcher;
	
	Map<String, AbstractCommand> commands = new HashMap<>();
	
	public CommandRegistry() {
		this.dispatcher = new CommandDispatcher<>();
	}
	
	public void register(AbstractCommand command) {
		commands.putIfAbsent(command.getId(), command);
		Bukkit.getServer().getCommandMap().register(command.getId(), command);
		command.register(dispatcher);
	}
}
