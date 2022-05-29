package gg.clouke.alpha.check;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.ContainerService;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.functions.Condition;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractCheck {

    protected final Alpha plugin;
    protected final Profile profile;
    protected final ContainerService containers;
    protected double buffer;
    private int vl;

    @Setter
    private boolean debug;

    public AbstractCheck(final Profile profile) {
        this.profile = profile;
        this.plugin = Alpha.getInstance();
        this.containers = profile.getContainerService();
    }

    public abstract void handle(final EPacket<?> packet);

    public BaseCheck getBaseCheck() {
        if (this.getClass().isAnnotationPresent(BaseCheck.class)) {
            return this.getClass().getAnnotation(BaseCheck.class);
        } else {
            System.err.println("@BaseCheck annotation hasn't been added to the class " + this.getClass().getSimpleName() + ".");
            return null;
        }
    }

    public final void debug(Object o) {
        debug("" + o);
    }

    public final void debug(final String data) {
        Condition.of(debug, () -> profile.getPlayer().sendMessage(CC.translate("&7(" + getClass().getSimpleName() + ") &f" + data)));
    }

    protected void alert(final String data) {
        this.vl++;
        this.plugin.getAlertProvider().flag(profile, this, data);
    }

}
