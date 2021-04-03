package me.conclure.actions;

import java.util.function.BiFunction;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface PlaceholderOperator extends BiFunction<Player,String,String> {

}
