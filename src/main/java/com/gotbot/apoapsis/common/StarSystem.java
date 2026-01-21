package com.gotbot.apoapsis.common;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StarSystem {
    private final Map<ResourceLocation, Planet> bodies = new HashMap<>();

    public Collection<Planet> getBodies() {
        return bodies.values();
    }

    public @Nullable Planet get(ResourceLocation id) {
        return bodies.get(id);
    }

    public void put(Planet def) {
        bodies.put(def.getID(), def);
    }
}
