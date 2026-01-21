package com.gotbot.apoapsis.client.dimension;

import com.gotbot.apoapsis.Apoapsis;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid=Apoapsis.MODID, value=Dist.CLIENT)
public final class SpaceClientEffects {
    public static final ResourceLocation SPACE_EFFECTS = ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "space");

    private SpaceClientEffects() {}

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(SPACE_EFFECTS, new SpaceDimensionSpecialEffects());
    }
}
