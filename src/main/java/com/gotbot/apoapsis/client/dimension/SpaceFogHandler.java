package com.gotbot.apoapsis.client.dimension;

import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber
public final class SpaceFogHandler {
    private SpaceFogHandler() {}

    public static boolean isSpace() {
        var level = Minecraft.getInstance().level;
        if (level == null) return false;
        return level.dimensionType().effectsLocation().equals(SpaceClientEffects.SPACE_EFFECTS);
    }

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        if (!isSpace()) return;
        event.setNearPlaneDistance(0.0f);
        event.setFarPlaneDistance(1_000_000_000.0f);
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        if (!isSpace()) return;
        event.setRed(0.0f);
        event.setGreen(0.0f);
        event.setBlue(0.0f);
    }
}
