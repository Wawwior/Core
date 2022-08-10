package me.wawwior.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lucko.commodore.Commodore;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand extends BukkitCommand {
	
	private final String id;
	private final CommandRegistry registry;
	private final Commodore commodore;
	
	AbstractCommand(String id, CommandRegistry registry, Commodore commodore) {
		super(id);
		this.id = id;
		this.registry = registry;
		this.commodore = commodore;
	}
	
	public abstract void build(LiteralArgumentBuilder<CommandSourceStack> builder);
	
	public void register() {
		
		if (registry.register(this)) {
			
			LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.literal(id);
			build(builder);
			
			CommandDispatcher<CommandSourceStack> dispatcher = registry.dispatcher;
			dispatcher.register(builder);
			
			commodore.register(this, builder);
		
		}
		
	}
	
	public void handleException(CommandSyntaxException e) {
		e.printStackTrace();
	}
	
	public String prefix() {
		return id;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public final boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
		
		CommandDispatcher<CommandSourceStack> dispatcher = registry.dispatcher;
		
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
}
