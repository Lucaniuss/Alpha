package gg.clouke.alpha.util.chat;

import lombok.NoArgsConstructor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Clouke
 * @since 04.02.2022 19:11
 * All Rights Reserved
 */

@NoArgsConstructor
public class DefaultClickable implements Clickable {

    private final List<TextComponent> components = new ArrayList<>();

    public DefaultClickable(String msg) {
        this.components.add(new TextComponent(msg));
    }

    public DefaultClickable(String msg, String hoverMsg, String clickString) {
        this.append(msg, hoverMsg, clickString);
    }

    @Override
    public Clickable append(String msg, String hoverMsg, String clickString) {
        TextComponent message = new TextComponent(msg);

        if(hoverMsg != null) {
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverMsg).create()));
        }

        if(clickString != null && !clickString.equals("")) {
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickString));
        }

        this.components.add(message);
        return this;
    }

    @Override
    public void send(Player player) {
        player.spigot().sendMessage(this.components.toArray(new TextComponent[0]));
    }

    @Override
    public void send(Iterator<Player> players) {
        while (players.hasNext()) {
            this.send(players.next());
        }
    }

    @Override
    public Clickable append(String text) {
        this.components.add(new TextComponent(text));
        return this;
    }
}

