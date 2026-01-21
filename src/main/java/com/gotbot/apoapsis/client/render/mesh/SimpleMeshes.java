package com.gotbot.apoapsis.client.render.mesh;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.joml.Matrix4f;

public final class SimpleMeshes {

    public static void drawSphere(VertexConsumer vc, PoseStack pose, int argb, double radius, int nSegments, int nSlices) {
        Matrix4f mat = pose.last().pose();
        PoseStack.Pose p = pose.last();

        int a = (argb >>> 24) & 255;
        int r = (argb >>> 16) & 255;
        int g = (argb >>> 8) & 255;
        int b = (argb) & 255;

        for (int slice = 0; slice < nSlices; slice++) {
            double lat0 = Math.PI * (((double) slice / nSlices) - 0.5);
            float z0 = (float) Math.sin(lat0);
            float zr0 = (float) Math.cos(lat0);

            double lat1 = Math.PI * (((double) (slice + 1) / nSlices) - 0.5);
            float z1 = (float) Math.sin(lat1);
            float zr1 = (float) Math.cos(lat1);

            float v0 = 1.0f - ((float) slice / (float) nSlices);
            float v1 = 1.0f - ((float) (slice + 1) / (float) nSlices);

            for (int seg = 0; seg < nSegments; seg++) {
                double long0 = 2 * Math.PI * ((double) seg / nSegments);
                float x0 = (float) Math.cos(long0);
                float y0 = (float) Math.sin(long0);

                double long1 = 2 * Math.PI * ((double) (seg + 1) / nSegments);
                float x1 = (float) Math.cos(long1);
                float y1 = (float) Math.sin(long1);

                float u0 = (float) seg / (float) nSegments;
                float u1 = (float) (seg + 1) / (float) nSegments;

                quad(vc, mat, p, r,g,b,a,
                        x0 * zr0, y0 * zr0, z0, u0, v0, 0, 0, 0,
                        x1 * zr0, y1 * zr0, z0, u1, v0, 0, 0, 0,
                        x1 * zr1, y1 * zr1, z1, u1, v1, 0, 0, 0,
                        x0 * zr1, y0 * zr1, z1, u0, v1, 0, 0, 0);
            }
        }
    }

    public static void drawUnitCube(PoseStack pose, VertexConsumer vc, int argb) {
        Matrix4f mat = pose.last().pose();
        PoseStack.Pose p = pose.last();

        int a = (argb >>> 24) & 255;
        int r = (argb >>> 16) & 255;
        int g = (argb >>> 8) & 255;
        int b = (argb) & 255;

        float min = -1.0f;
        float max =  1.0f;

        quad(vc, mat, p, r,g,b,a,
                min, min, max,  0,0,  0,0,1,
                max, min, max,  1,0,  0,0,1,
                max, max, max,  1,1,  0,0,1,
                min, max, max,  0,1,  0,0,1
        );
        quad(vc, mat, p, r,g,b,a,
                max, min, min,  0,0,  0,0,-1,
                min, min, min,  1,0,  0,0,-1,
                min, max, min,  1,1,  0,0,-1,
                max, max, min,  0,1,  0,0,-1
        );

        quad(vc, mat, p, r,g,b,a,
                max, min, max,  0,0,  1,0,0,
                max, min, min,  1,0,  1,0,0,
                max, max, min,  1,1,  1,0,0,
                max, max, max,  0,1,  1,0,0
        );

        quad(vc, mat, p, r,g,b,a,
                min, min, min,  0,0,  -1,0,0,
                min, min, max,  1,0,  -1,0,0,
                min, max, max,  1,1,  -1,0,0,
                min, max, min,  0,1,  -1,0,0
        );

        quad(vc, mat, p, r,g,b,a,
                min, max, max,  0,0,  0,1,0,
                max, max, max,  1,0,  0,1,0,
                max, max, min,  1,1,  0,1,0,
                min, max, min,  0,1,  0,1,0
        );

        quad(vc, mat, p, r,g,b,a,
                min, min, min,  0,0,  0,-1,0,
                max, min, min,  1,0,  0,-1,0,
                max, min, max,  1,1,  0,-1,0,
                min, min, max,  0,1,  0,-1,0
        );
    }

    private static void quad(VertexConsumer vc, Matrix4f mat, PoseStack.Pose p,
                             int r, int g, int b, int a,
                             float x1, float y1, float z1, float u1, float v1, float nx, float ny, float nz,
                             float x2, float y2, float z2, float u2, float v2, float nx2, float ny2, float nz2,
                             float x3, float y3, float z3, float u3, float v3, float nx3, float ny3, float nz3,
                             float x4, float y4, float z4, float u4, float v4, float nx4, float ny4, float nz4) {
        vc.addVertex(mat, x1,y1,z1).setColor(r,g,b,a).setUv(u1,v1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(p, nx, ny, nz);
        vc.addVertex(mat, x2,y2,z2).setColor(r,g,b,a).setUv(u2,v2).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(p, nx2, ny2, nz2);
        vc.addVertex(mat, x3,y3,z3).setColor(r,g,b,a).setUv(u3,v3).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(p, nx3, ny3, nz3);
        vc.addVertex(mat, x4,y4,z4).setColor(r,g,b,a).setUv(u4,v4).setOverlay(OverlayTexture.NO_OVERLAY).setLight(LightTexture.FULL_BRIGHT).setNormal(p, nx4, ny4, nz4);
    }
}
