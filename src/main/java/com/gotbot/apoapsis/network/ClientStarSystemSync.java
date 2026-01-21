package com.gotbot.apoapsis.network;

import com.gotbot.apoapsis.client.state.ClientStarSystemState;
import com.gotbot.apoapsis.common.Planet;
import com.gotbot.apoapsis.common.StarSystemSavedData;
import com.gotbot.apoapsis.network.payload.StarSystemSyncPayload;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientStarSystemSync {
    public static void handle(StarSystemSyncPayload payload, IPayloadContext ctx) {
        ctx.enqueueWork(() -> setStarSystem(payload.tag()));
    }

    public static void setStarSystem(CompoundTag tag) {
        ListTag bodies = tag.getList(StarSystemSavedData.N_BODIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < bodies.size(); i++) {
            CompoundTag pTag = bodies.getCompound(i);
            Planet planet = StarSystemSavedData.readPlanet(pTag);
            ClientStarSystemState.getStarSystem().put(planet);
        }
    }
}
