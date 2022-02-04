package gg.clouke.alpha.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public final class CC {
    public String translate(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
