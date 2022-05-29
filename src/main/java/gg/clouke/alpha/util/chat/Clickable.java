package gg.clouke.alpha.util.chat;

import gg.clouke.alpha.util.annotations.DoNotMock;
import org.bukkit.entity.Player;

import java.util.Iterator;

/**
 * @author Clouke
 * @since 28.05.2022 21:43
 * © Alpha - All Rights Reserved
 */

@DoNotMock("Use Clickable clickable = new DefaultClickable(\"text\", player);")
public interface Clickable {

    static Clickable builder() {
        return new DefaultClickable();
    }

    Clickable append(String text);

    Clickable append(String msg, String hoverMsg, String clickString);

    void send(Player player);

    void send(Iterator<Player> players);

}
