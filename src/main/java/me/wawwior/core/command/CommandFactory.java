package me.wawwior.core.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.wawwior.core.core.CorePlugin;
import me.wawwior.core.util.NameUtil;
import net.minecraft.commands.CommandSourceStack;

import java.util.function.Consumer;

public class CommandFactory {

    private final CommandRegistry commandRegistry = new CommandRegistry();

    private final Commodore commodore;
    private final CorePlugin plugin;

    public CommandFactory(CorePlugin plugin) {
        this.commodore = CommodoreProvider.getCommodore(plugin);
        this.plugin = plugin;
    }
	
	public AbstractCommand create(String id, Consumer<LiteralArgumentBuilder<CommandSourceStack>> consumer) {
		return new AbstractCommand(id, commandRegistry, commodore) {
			@Override
			public void build(LiteralArgumentBuilder<CommandSourceStack> builder) {
				consumer.accept(builder);
			}

            @Override
            public String prefix() {
                return NameUtil.identifier(plugin.getName());
            }
        };
	}
	
	public static LiteralArgumentBuilder<CommandSourceStack> literal(String id) {
		return LiteralArgumentBuilder.literal(id);
	}
	
	public static <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String id, ArgumentType<T> type) {
		return RequiredArgumentBuilder.argument(id, type);
	}
}
