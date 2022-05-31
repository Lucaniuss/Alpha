package gg.clouke.alpha.provider.log;

import gg.clouke.alpha.check.BaseCheck;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Lucanius
 * @since May 31, 2022.
 * Alpha - All Rights Reserved.
 */
public interface LogProvider {

    void log(UUID uuid, String player, BaseCheck base, int vl, String data);

    Set<Log> fetch(UUID uuid);

    void save();

    String toPaste(String string);

    Map<UUID, Set<Log>> getCached();

}
