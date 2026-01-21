package com.gotbot.apoapsis.event;

import com.gotbot.apoapsis.Apoapsis;
import com.gotbot.apoapsis.common.Planet;
import com.gotbot.apoapsis.common.StarSystem;
import com.gotbot.apoapsis.common.StarSystemSavedData;
import com.gotbot.apoapsis.network.payload.StarSystemSyncPayload;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;

@EventBusSubscriber(modid= Apoapsis.MODID)
public class PlayerJoinHandler {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        StarSystemSavedData starSystemSavedData = StarSystemSavedData.get(Objects.requireNonNull(player.getServer()));
        StarSystem starSystem = starSystemSavedData.getStarSystem();

        if (starSystem.getBodies().isEmpty()) {
            starSystemSavedData.put(new Planet(ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "earth"), 1000.0, 1000, 1, 1, 1, 1, 1, 1, ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "sun")));
        }

        CompoundTag tag = starSystemSavedData.save(new CompoundTag(), player.level().registryAccess());
        PacketDistributor.sendToPlayer((ServerPlayer) player, new StarSystemSyncPayload(tag));
    }
}
