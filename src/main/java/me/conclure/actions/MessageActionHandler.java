package me.conclure.actions;

import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

public class MessageActionHandler implements ActionHandler {

  @Override
  public void handle(Player player, String node) {
    CompletableFuture.runAsync(() -> player.sendMessage(ActionParser.COLOR.apply(node)),ActionParser.POOL);
  }
}
