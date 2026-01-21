package com.gotbot.apoapsis.network;

import com.gotbot.apoapsis.Apoapsis;
import com.gotbot.apoapsis.network.payload.StarSystemSyncPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid= Apoapsis.MODID)
public class ApoapsisNetwork {
    @SubscribeEvent
    public void ClientStarSystemSync(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(StarSystemSyncPayload.TYPE, StarSystemSyncPayload.STREAM_CODEC, ClientStarSystemSync::handle);
    }
}
