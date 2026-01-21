package com.gotbot.apoapsis.common;

import net.minecraft.resources.ResourceLocation;

public class Planet {
    private ResourceLocation id;
    private double radius, mu, a, e, i, raan, argPeri, meanAnomalyAtEpoch;
    private long epochTick;
    private ResourceLocation parentBody;

    public Planet(ResourceLocation id,
                  double radius, double mu,
                  double a, double e,
                  double i, double raan,
                  double argPeri,
                  double meanAnomalyAtEpoch,
                  ResourceLocation parentBody) {
        this.radius = radius;
        this.mu = mu;
        this.a = a;
        this.e = e;
        this.i = i;
        this.raan = raan;
        this.argPeri = argPeri;
        this.meanAnomalyAtEpoch = meanAnomalyAtEpoch;
        this.id = id;
        this.parentBody = parentBody;
    }

    public Planet() {}

    public ResourceLocation getID() {
        return id;
    }

    public void setID(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getParentBody() {
        return parentBody;
    }

    public void setParentBody(ResourceLocation parentBody) {
        this.parentBody = parentBody;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getRaan() {
        return raan;
    }

    public void setRaan(double raan) {
        this.raan = raan;
    }

    public double getArgPeri() {
        return argPeri;
    }

    public void setArgPeri(double argPeri) {
        this.argPeri = argPeri;
    }

    public double getMeanAnomalyAtEpoch() {
        return meanAnomalyAtEpoch;
    }

    public void setMeanAnomalyAtEpoch(double meanAnomalyAtEpoch) {
        this.meanAnomalyAtEpoch = meanAnomalyAtEpoch;
    }

    public long getEpochTick() {
        return epochTick;
    }

    public void setEpochTick(long epochTick) {
        this.epochTick = epochTick;
    }
}
