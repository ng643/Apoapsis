package com.gotbot.apoapsis.network;

import com.gotbot.apoapsis.network.payload.StarSystemSyncPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ApoapsisNetwork {
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(StarSystemSyncPayload.TYPE, StarSystemSyncPayload.STREAM_CODEC, ClientStarSystemSync::handle);
    }
}
