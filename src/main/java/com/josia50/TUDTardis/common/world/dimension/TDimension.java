package com.josia50.TUDTardis.common.world.dimension;

import com.josia50.TUDTardis.common.world.dimension.tardis.WorldProviderTardis;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class TDimension {

    public static int TARDISID;
    public static DimensionType Type;

    public static void Register() {
        TARDISID = DimensionManager.getNextFreeDimId();
        Type = DimensionType.register("tardis", "tardis", TARDISID, WorldProviderTardis.class, true);
        DimensionManager.registerDimension(TARDISID, Type);
    }
}
