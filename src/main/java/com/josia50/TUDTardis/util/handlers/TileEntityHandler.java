package com.josia50.TUDTardis.util.handlers;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.client.renderers.exterior.RenderTardis;
import com.josia50.TUDTardis.common.tileentity.*;
import com.josia50.TUDTardis.common.tileentity.exteriors.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

    //Register Tileeintity's
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileExterior_01.class, new ResourceLocation(TudTardis.MODID + ":TileExterior1"));
        GameRegistry.registerTileEntity(TileExterior_02.class, new ResourceLocation(TudTardis.MODID + ":TileExterior2"));
        GameRegistry.registerTileEntity(TileExterior_03.class, new ResourceLocation(TudTardis.MODID + ":TileExterior3"));
        GameRegistry.registerTileEntity(TileExterior_04.class, new ResourceLocation(TudTardis.MODID + ":TileExterior4"));
        GameRegistry.registerTileEntity(TileExterior_05.class, new ResourceLocation(TudTardis.MODID + ":TileExterior5"));
        GameRegistry.registerTileEntity(TileExterior_06.class, new ResourceLocation(TudTardis.MODID + ":TileExterior6"));

        // GameRegistry.registerTileEntity(TileTardis.class, new ResourceLocation(TudTardis.MODID + ":TileTardis"));
        GameRegistry.registerTileEntity(TileTardisSpawner.class, new ResourceLocation(TudTardis.MODID + ":TileTardisSpawner"));
        GameRegistry.registerTileEntity(TileScreen.class, new ResourceLocation(TudTardis.MODID + ":TileScreen"));
        GameRegistry.registerTileEntity(TileConsole.class, new ResourceLocation(TudTardis.MODID + ":TileConsole"));
        GameRegistry.registerTileEntity(TileDematLever.class, new ResourceLocation(TudTardis.MODID + ":TileDematLever"));
        GameRegistry.registerTileEntity(TileDoor.class, new ResourceLocation(TudTardis.MODID + ":TileDoor"));
        GameRegistry.registerTileEntity(TileCoord.class, new ResourceLocation(TudTardis.MODID + ":TileCoord"));
    }

    //Bind TESR to tileentity
    public static void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileExterior_06.class, new RenderTardis());
    }

    // Item Render
    public static void Init() {
        // Item.getItemFromBlock(TBlocks.TARDIS_EXTERIOR).setTileEntityItemStackRenderer(new RenderTardisItem());
    }
}
