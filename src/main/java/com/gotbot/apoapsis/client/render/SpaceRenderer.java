package com.gotbot.apoapsis.client.render;

import com.gotbot.apoapsis.Apoapsis;
import com.gotbot.apoapsis.client.render.mesh.SimpleMeshes;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Decoder;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid=Apoapsis.MODID, value=Dist.CLIENT)
public class SpaceRenderer {
    private static final ResourceLocation CUBE_TEX = ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "textures/space/planet_cube.png");
    private static final ResourceLocation SKY_TEX = ResourceLocation.fromNamespaceAndPath(Apoapsis.MODID, "textures/space/universe_contrast_upscaled_4x.png");

    @SubscribeEvent
    public static void onRender(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_ENTITIES) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        PoseStack pose = event.getPoseStack();

        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();

        //RenderSystem.disableDepthTest();
        //RenderSystem.depthMask(false);

        pose.pushPose();
        VertexConsumer vcSky = buffers.getBuffer(ApoapsisRenderTypes.skybox(SKY_TEX));
        pose.translate(0.0, -0.5, 0.0);

        pose.scale(1.0f, 1.0f, 1.0f);

        SimpleMeshes.drawSphere(vcSky, pose, 0xFFFFFFFF, 1, 100, 100);
        pose.popPose();


        pose.pushPose();
        VertexConsumer vc = buffers.getBuffer(ApoapsisRenderTypes.planet(CUBE_TEX));
        Camera camera = event.getCamera();
        Vec3 cameraPos = camera.getPosition();
        Vec3 cameraLook = new Vec3(camera.getLookVector());

        Vec3 planetPos = new Vec3(0.0, 10000.0, 0.0);
        float planetScale = 10000.0f;
        double universalRadius = 10.0;

        Vec3 toPlanet = planetPos.subtract(cameraPos);
        Vec3 dir = toPlanet.normalize();
        double camDepth = toPlanet.dot(cameraLook);
        double actualDepth = dir.multiply(universalRadius, universalRadius, universalRadius).dot(cameraLook);

        double distance = toPlanet.length();
        if (distance >=  universalRadius) {
            pose.translate(dir.x * universalRadius, dir.y * universalRadius, dir.z * universalRadius);
            float s = (float) (planetScale * (actualDepth / camDepth));
            pose.scale(s, s, s);
        }
        else {
            pose.translate(planetPos.x, planetPos.y, planetPos.z);
            pose.scale(planetScale, planetScale, planetScale);
        }

        //SimpleMeshes.drawUnitCube(pose, vc, 0xFFFFFFFF);
        SimpleMeshes.drawSphere(vc, pose, 0xFFFFFFFF, 1, 100, 100);
        pose.popPose();

        //RenderSystem.enableDepthTest();
        //RenderSystem.depthMask(true);
        buffers.endBatch();
    }
}
