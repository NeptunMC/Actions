package me.conclure.actions;

import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastActionHandler implements ActionHandler {

  @Override
  public void handle(Player player, String node) {
    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
      CompletableFuture.runAsync(() -> {
        onlinePlayer.sendMessage(ActionParser.COLOR.apply(node));
      });
    }
  }
}
