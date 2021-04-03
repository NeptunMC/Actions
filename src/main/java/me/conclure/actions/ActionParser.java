package me.conclure.actions;

import com.google.common.collect.ListMultimap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.spigotmc.CaseInsensitiveMap;

public class ActionParser {
  static final UnaryOperator<String> COLOR = s -> ChatColor.translateAlternateColorCodes('&',s);

  private final String splitChar;
  private final Map<String,ActionHandler> map;
  private final List<PlaceholderOperator> list;

  public ActionParser(String splitChar) {
    this.splitChar = splitChar;
    this.map = new CaseInsensitiveMap<>();
    this.list = new ArrayList<>();
  }

  public static ActionParser create() {
    return create(":");
  }

  public static ActionParser create(String splitChar) {
    final ActionParser actionParser = new ActionParser(splitChar);

    actionParser.addPlaceholderTransformer(new PlayerPlaceholderOperator());
    actionParser.addPlaceholderTransformer(new PlaceholderAPIOperator());

    actionParser.addHandler("message", new MessageActionHandler());
    actionParser.addHandler("actonbar", new ActionBarActionHandler());
    actionParser.addHandler("money", new MessageActionHandler());
    actionParser.addHandler("broadcast", new BroadcastActionHandler());

    return actionParser;
  }

  public void addHandler(String token, ActionHandler handler) {
    this.map.computeIfAbsent(token,key -> handler);
  }

  public void addPlaceholderTransformer(PlaceholderOperator operator) {
    this.list.add(operator);
  }

  public void parse(Player player, List<String> nodes) {
    for (String node : nodes) {
      final String[] split = StringUtils.split(node, this.splitChar);

      if (split.length != 2) {
        continue;
      }

      if (StringUtils.isBlank(split[0]) || StringUtils.isBlank(split[1])) {
        continue;
      }

      final ActionHandler actionHandler = this.map.get(split[0]);

      if (actionHandler == null) {
        continue;
      }

      node = split[1];

      for (PlaceholderOperator operator : this.list) {
        node = operator.apply(player,node);
      }

      actionHandler.handle(player,node);
    }
  }
}
