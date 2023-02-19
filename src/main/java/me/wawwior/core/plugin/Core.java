package me.wawwior.core.plugin;

import me.wawwior.core.core.CorePlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class Core extends CorePlugin {
	
	public static Core CORE;

	@Override
	protected void load() {

		CORE = this;
		
		commandFactory.create("core", builder -> builder
                .executes(context -> {
                    context.getSource().getBukkitSender().sendMessage(
                            Component
                                    .text("Running core version " + version())
                                    .color(NamedTextColor.GRAY)
                                    .decorate(TextDecoration.ITALIC)
                    );
                    return 1;
                })).register();
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

    @Override
    public String version() {
        return "1.3.7-DEV";
    }
}
