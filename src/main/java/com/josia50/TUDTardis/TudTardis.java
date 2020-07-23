package com.josia50.TUDTardis;

import com.josia50.TUDTardis.common.world.ConsoleRoom;
import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.network.NetworkHandler;
import com.josia50.TUDTardis.proxy.IProxy;
import com.josia50.TUDTardis.util.handlers.SoundHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TudTardis.MODID, name = TudTardis.NAME, version = TudTardis.VERSION)
public class TudTardis
{

    @Mod.Instance
    public static TudTardis instance;

    public static final String MODID = "bdwm";
    public static final String NAME = "BDWM";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.josia50.TUDTardis.proxy.ClientProxy", serverSide = "com.josia50.TUDTardis.proxy.ServerProxy")
    public static IProxy proxy;
    private static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
        NetworkHandler.init();
        ConsoleRoom.registerConsoleRoom( "tardis", new BlockPos(16, 1, 16));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        SoundHandler.registerSounds();
        TDimension.Register();
    }
}
