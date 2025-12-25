package com.example.sculkalertmod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent SCULK_ALERT = registerSound("sculk_alert");

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier("sculkalertmod", name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void register() {}
}
