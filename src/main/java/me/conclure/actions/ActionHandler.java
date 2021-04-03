package me.conclure.actions;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface ActionHandler {
  void handle(Player player, String node);
}
