package com.gotbot.apoapsis.common;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class StarSystemSavedData extends SavedData {
    private static final String FILE_ID = "apoapsis_star_system";
    public static final String N_BODIES = "bodies";
    private final StarSystem starSystem = new StarSystem();

    public StarSystem getStarSystem() {
        return starSystem;
    }

    public void put(Planet planet) {
        starSystem.put(planet);
        setDirty();
    }

    public static SavedData.Factory<StarSystemSavedData> factory() {
        return new SavedData.Factory<StarSystemSavedData>(StarSystemSavedData::new, StarSystemSavedData::load);
    }

    public static StarSystemSavedData get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(factory(), FILE_ID);
    }

    private static StarSystemSavedData load(CompoundTag tag, HolderLookup.Provider registries) {
        StarSystemSavedData data = new StarSystemSavedData();

        ListTag bodies = tag.getList(N_BODIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < bodies.size(); i++) {
            CompoundTag pTag = bodies.getCompound(i);
            Planet planet = readPlanet(pTag);
            data.starSystem.put(planet);
        }

        return data;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        ListTag bodies = new ListTag();
        for (Planet p : starSystem.getBodies()) {
            bodies.add(writePlanet(p));
        }
        tag.put(N_BODIES, bodies);
        return tag;
    }

    private static CompoundTag writePlanet(Planet p) {
        CompoundTag t = new CompoundTag();

        t.putString("id", p.getID().toString());

        @Nullable ResourceLocation parent = p.getParentBody();
        if (parent != null) t.putString("parent", parent.toString());

        t.putDouble("radius", p.getRadius());
        t.putDouble("mu", p.getMu());
        t.putDouble("a", p.getA());
        t.putDouble("e", p.getE());
        t.putDouble("i", p.getI());
        t.putDouble("raan", p.getRaan());
        t.putDouble("argPeri", p.getArgPeri());
        t.putDouble("meanAnomalyAtEpoch", p.getMeanAnomalyAtEpoch());
        t.putLong("epochTick", p.getEpochTick());

        return t;
    }

    public static Planet readPlanet(CompoundTag t) {
        Planet p = new Planet();

        p.setID(ResourceLocation.parse(t.getString("id")));

        if (t.contains("parent", Tag.TAG_STRING)) {
            p.setParentBody(ResourceLocation.parse(t.getString("parent")));
        }

        p.setRadius(t.getDouble("radius"));
        p.setMu(t.getDouble("mu"));
        p.setA(t.getDouble("a"));
        p.setE(t.getDouble("e"));
        p.setI(t.getDouble("i"));
        p.setRaan(t.getDouble("raan"));
        p.setArgPeri(t.getDouble("argPeri"));
        p.setMeanAnomalyAtEpoch(t.getDouble("meanAnomalyAtEpoch"));
        p.setEpochTick(t.getLong("epochTick"));

        return p;
    }
}
