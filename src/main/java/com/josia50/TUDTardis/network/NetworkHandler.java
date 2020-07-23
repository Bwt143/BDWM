package com.josia50.TUDTardis.network;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.network.packets.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(TudTardis.MODID);
    public static int id = -1;

    public static void init() {
        NETWORK.registerMessage(PacketDemat.Handler.class, PacketDemat.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketTardis.Handler.class, PacketTardis.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketVisible.Handler.class, PacketVisible.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketCoord.Handler.class, PacketCoord.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketDim.Handler.class, PacketDim.class, id++, Side.SERVER);
    }
}
