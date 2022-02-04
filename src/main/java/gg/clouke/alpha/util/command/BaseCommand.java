package gg.clouke.alpha.util.command;

import gg.clouke.alpha.Alpha;

/**
 * @author Clouke
 * @since 04.02.2022 18:36
 * All Rights Reserved
 */

public abstract class BaseCommand {
    protected final Alpha plugin = Alpha.INSTANCE;

    public BaseCommand() {
        this.plugin.getCommandFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs cmd);

}
