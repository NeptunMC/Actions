package me.conclure.actions;

import org.bukkit.entity.Player;

public class PlayerPlaceholderOperator implements PlaceholderOperator {

  @Override
  public String apply(Player player, String s) {
    return s.replace("{player}",player.getName());
  }
}
