package com.gotbot.apoapsis.client.render.shader;

import com.gotbot.apoapsis.Apoapsis;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid=Apoapsis.MODID)
public class PlanetShader {
    public static ShaderInstance PLANET;

    @SubscribeEvent
    public static void onShaderRegister(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "planet"), DefaultVertexFormat.NEW_ENTITY), shaderInstance -> PLANET = shaderInstance);
    }
}
