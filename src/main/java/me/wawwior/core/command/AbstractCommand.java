package me.wawwior.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.wawwior.core.Core;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand extends BukkitCommand {
	
	private final String id;
	private CommandDispatcher<CommandSourceStack> dispatcher;
	
	public AbstractCommand(String id) {
		super(id);
		this.id = id;
	}
	
	public abstract void build(LiteralArgumentBuilder<CommandSourceStack> builder);
	
	public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		this.dispatcher = dispatcher;
		
		LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.literal(id);
		
		build(builder);
		
		Core.CORE.getCommodore().register(builder);
		
		dispatcher.register(builder);
		
		
	}
	
	public void handleException(CommandSyntaxException e) {
		e.printStackTrace();
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public final boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
		
		StringBuilder builder = new StringBuilder(label.split(":")[label.split(":").length - 1]);
		
		for (String arg : args) {
			builder.append(" ").append(arg);
		}
		
		ParseResults<CommandSourceStack> parseResults = dispatcher.parse(builder.toString(), VanillaCommandWrapper.getListener(sender));
		
		try {
			return dispatcher.execute(parseResults) > 0;
		} catch (CommandSyntaxException e) {
			handleException(e);
		}
		
		return false;
	}
	
	//Utility methods
	
	protected LiteralArgumentBuilder<CommandSourceStack> literal(String id) {
		return LiteralArgumentBuilder.literal(id);
	}
	
	protected <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String id, ArgumentType<T> type) {
		return RequiredArgumentBuilder.argument(id, type);
	}
}
