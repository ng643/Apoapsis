package com.gotbot.apoapsis.event;

import com.gotbot.apoapsis.Apoapsis;
import com.gotbot.apoapsis.common.Planet;
import com.gotbot.apoapsis.common.StarSystem;
import com.gotbot.apoapsis.common.StarSystemSavedData;
import com.gotbot.apoapsis.network.payload.StarSystemSyncPayload;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

@EventBusSubscriber(modid= Apoapsis.MODID)
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        StarSystemSavedData starSystemSavedData = StarSystemSavedData.get(Objects.requireNonNull(player.getServer()));
        StarSystem starSystem = starSystemSavedData.getStarSystem();

        if (starSystem.getBodies().isEmpty()) {
            starSystemSavedData.put(new Planet(
                    ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "earth"),
                    6_371.0 * 0.1, // km
                    1.32712440018E11, // Sun mu (km^3/s^2)
                    149_597_870.7, // 1 AU (km)
                    0.01671022,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "sun")
            ));
        }

        CompoundTag tag = starSystemSavedData.save(new CompoundTag(), player.level().registryAccess());
        PacketDistributor.sendToPlayer(player, new StarSystemSyncPayload(tag));
    }
}
