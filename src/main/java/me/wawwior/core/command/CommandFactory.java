package me.wawwior.core.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.lucko.commodore.Commodore;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.command.Command;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CommandFactory {
	
	private final Supplier<CommandRegistry> registrySupplier;
	private final Supplier<Commodore> commodoreSupplier;
	
	public CommandFactory(Supplier<CommandRegistry> registrySupplier, Supplier<Commodore> commodoreSupplier) {
		this.registrySupplier = registrySupplier;
		this.commodoreSupplier = commodoreSupplier;
	}
	
	public AbstractCommand create(String id, Consumer<LiteralArgumentBuilder<CommandSourceStack>> consumer) {
		return new AbstractCommand(id, registrySupplier.get(), commodoreSupplier.get()) {
			@Override
			public void build(LiteralArgumentBuilder<CommandSourceStack> builder) {
				consumer.accept(builder);
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
