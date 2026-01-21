package com.gotbot.apoapsis.client.state;

import com.gotbot.apoapsis.common.StarSystem;

public class ClientStarSystemState {
    private static StarSystem starSystem = new StarSystem();

    public static StarSystem getStarSystem() {
        return starSystem;
    }

    public static void setStarSystem(StarSystem starSystem) {
        ClientStarSystemState.starSystem = starSystem;
    }
}
