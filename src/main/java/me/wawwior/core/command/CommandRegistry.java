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
	
	boolean register(AbstractCommand command) {
		
		if (commands.containsKey(command.getId())) {
			throw new IllegalArgumentException("Command with id " + command.getId() + " already registered");
		}
		
		commands.putIfAbsent(command.getId(), command);
		
		Bukkit.getServer().getCommandMap().register(command.prefix(), command);
		
		return true;
	}
}
