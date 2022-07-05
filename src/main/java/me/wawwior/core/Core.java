package me.wawwior.core;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.wawwior.core.command.AbstractCommand;
import me.wawwior.core.command.CommandRegistry;
import me.wawwior.core.core.CorePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.commands.CommandSourceStack;

public final class Core extends CorePlugin {
	
	public static Core CORE;
	
	private final CommandRegistry commandRegistry = new CommandRegistry();
	
	private Commodore commodore;
	
	@Override
	protected void load() {
		CORE = this;
		
		getLogger().info(
				"""
						
						\u001b[36;1m\s
						 #####  #####  ####   #####
						 ##     ## ##  #####  #####
						 #####  #####  ##  #  #####
						
						 - Wawwior's Backend Core\u001b[0m\s
						"""
		);
	}
	
	@Override
	protected void enable() {
		
		commodore = CommodoreProvider.getCommodore(this);
		commandRegistry.register(new AbstractCommand("core") {
			@Override
			public void build(LiteralArgumentBuilder<CommandSourceStack> builder) {
				builder
						.executes(context -> {
							context.getSource().getBukkitSender().sendMessage(
									Component
											.text("Running core version " + version())
											.color(NamedTextColor.GRAY)
											.decorate(TextDecoration.ITALIC)
							);
							return 1;
						});
			}
		});
		
	}
	
	public CommandRegistry getCommandRegistry() {
		return commandRegistry;
	}
	
	public Commodore getCommodore() {
		return commodore;
	}
}
