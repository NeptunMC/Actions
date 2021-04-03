package me.conclure.actions;

import com.google.common.primitives.Doubles;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

public class MoneyActionHandler implements ActionHandler {
  private final Economy economy;

  public MoneyActionHandler(Economy economy) {
    this.economy = economy;
  }

  @Override
  public void handle(Player player, String node) {
    Double amount = Doubles.tryParse(node);

    if (amount == null) {
      return;
    }

    this.economy.depositPlayer(player,amount);
  }
}
