package me.wawwior.core;

import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.wawwior.core.command.CommandFactory;
import me.wawwior.core.command.CommandRegistry;
import me.wawwior.core.core.CorePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class Core extends CorePlugin {
	
	public static Core CORE;
	
	private final CommandRegistry commandRegistry = new CommandRegistry();
	
	private Commodore commodore;
	
	private final CommandFactory commandFactory = new CommandFactory(() -> commandRegistry, () -> commodore);
	
	
	@Override
	protected void load() {
		CORE = this;
		
		commodore = CommodoreProvider.getCommodore(this);
		
		commandFactory.create("core", builder -> {
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
		}).register();
	}
	
	@Override
	protected void enable() {
		
		this.getLogger().info(
				"""
						
						\u001b[36;1m\s
						 #####  #####  ####   #####
						 ##     ## ##  #####  #####
						 #####  #####  ##  #  #####
						
						 - Wawwior's Backend Core\u001b[0m\s
						"""
		);
		
	}
	
	public CommandRegistry getCommandRegistry() {
		return commandRegistry;
	}
	
	public Commodore getCommodore() {
		return commodore;
	}
}
