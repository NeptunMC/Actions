package me.conclure.actions;

import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

public class ActionBarActionHandler implements ActionHandler {

  @Override
  public void handle(Player player, String node) {
    CompletableFuture.runAsync(() -> {
      Titles.sendActionBarMessage(player,node);
    });
  }
}
