package com.gotbot.apoapsis.common;

import net.minecraft.world.phys.Vec3;

public class Orbital {
    public static Vec3 computePos(double mu, double a, double e, double t, double raan, double i, double argPeri) {
        double n = Math.sqrt(mu/Math.pow(a, 3));
        double M = wrap0ToTau(n * t);
        double E = M;
        for (int it = 0; it < 10; it++) {
            E = E - (E - (e * Math.sin(E)) - M)/(1 - (e * Math.cos(E)));
        }

        double xd = a * (Math.cos(E) - e);
        double yd = a * (Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E));
        double zd = 0.0;

        double x = ((Math.cos(argPeri) * Math.cos(raan) - Math.sin(argPeri) * Math.sin(raan) * Math.cos(i)) * xd)
                + ((-Math.sin(argPeri) * Math.cos(raan) - Math.cos(argPeri) * Math.sin(raan) * Math.cos(i)) * yd);
        double y = ((Math.cos(argPeri) * Math.sin(raan) + Math.sin(argPeri) * Math.cos(raan) * Math.cos(i)) * xd)
                + ((-Math.sin(argPeri) * Math.sin(raan) + Math.cos(argPeri) * Math.cos(raan) * Math.cos(i)) * yd);
        double z = (Math.sin(argPeri) * Math.sin(i) * xd) + (Math.cos(argPeri) * Math.sin(i) * yd);

        return new Vec3(x, z, y);
    }

    private static double wrap0ToTau(double a) {
        a = a % Math.TAU;
        if (a < 0) a += Math.TAU;
        return a;
    }
}
