package com.gotbot.apoapsis.network.payload;

import com.gotbot.apoapsis.Apoapsis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record StarSystemSyncPayload(CompoundTag tag) implements CustomPacketPayload {
    public static final Type<StarSystemSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "star_system_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StarSystemSyncPayload> STREAM_CODEC = CustomPacketPayload.codec(
            (payload, buf) -> buf.writeNbt(payload.tag),
            buf -> new StarSystemSyncPayload(Objects.requireNonNull(buf.readNbt()))
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public @NotNull ClientboundCustomPayloadPacket toVanillaClientbound() {
        return CustomPacketPayload.super.toVanillaClientbound();
    }

    @Override
    public @NotNull ServerboundCustomPayloadPacket toVanillaServerbound() {
        return CustomPacketPayload.super.toVanillaServerbound();
    }
}
