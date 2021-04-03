package me.conclure.actions;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderAPIOperator implements PlaceholderOperator {

  @Override
  public String apply(Player player, String s) {
    return PlaceholderAPI.setBracketPlaceholders(player,s);
  }
}
