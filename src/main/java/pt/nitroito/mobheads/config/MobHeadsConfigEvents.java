package pt.nitroito.mobheads.config;

import java.util.List;
import java.util.ArrayList;


public final class MobHeadsConfigEvents {
    private static final List<Runnable> LISTENERS = new ArrayList<>();

    public static void register(Runnable listener) {
        LISTENERS.add(listener);
    }

    public static void fire() {
        for (Runnable r:LISTENERS) {
            r.run();
        }
    }
}
