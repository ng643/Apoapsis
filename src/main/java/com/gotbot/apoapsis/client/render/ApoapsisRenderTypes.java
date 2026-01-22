package com.gotbot.apoapsis.client.render;

import com.gotbot.apoapsis.client.render.shader.PlanetShader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class ApoapsisRenderTypes {
    private static final Function<ResourceLocation, RenderType> PLANET = Util.memoize(tex -> {
        var state = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> PlanetShader.PLANET))
                .setTextureState(new RenderStateShard.TextureStateShard(tex, false, false))
                .setTransparencyState(RenderStateShard.NO_TRANSPARENCY)
                .setCullState(RenderStateShard.CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create("planet", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536,
                true, false, state);
    });

    private static final Function<ResourceLocation, RenderType> SKYBOX = Util.memoize(tex -> {
        var state = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(() -> PlanetShader.PLANET))
                .setTextureState(new RenderStateShard.TextureStateShard(tex, false, false))
                .setTransparencyState(RenderStateShard.NO_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create("skybox", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536,
                true, false, state);
    });
    private static final Logger log = LoggerFactory.getLogger(ApoapsisRenderTypes.class);

    public static RenderType planet(ResourceLocation tex) {
        return PLANET.apply(tex);
    }
    public static RenderType skybox(ResourceLocation tex) {
        return SKYBOX.apply(tex);
    }
}
