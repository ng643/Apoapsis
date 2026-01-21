package com.gotbot.apoapsis.event;

import com.gotbot.apoapsis.Apoapsis;
import com.gotbot.apoapsis.client.dimension.SpaceFogHandler;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid= Apoapsis.MODID)
public class VoidDamageHandler {
    @SubscribeEvent()
    public static void onHurt(LivingIncomingDamageEvent event) {
        if (!SpaceFogHandler.isSpace()) return;

        if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD)) {
            event.setCanceled(true);
        }
    }
}
