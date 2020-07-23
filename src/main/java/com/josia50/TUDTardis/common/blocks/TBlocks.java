package com.josia50.TUDTardis.common.blocks;

import com.josia50.TUDTardis.common.tileentity.exteriors.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class TBlocks {

    public static List<Block> BLOCK_LIST = new ArrayList<Block>();

    public static final Block TARDIS_SPAWNER = new BlockTardisSpawner("tardis_spawner", Material.BARRIER);
    public static final Block TARDIS_COORD = new BlockCoord("tardis_coord", Material.CLAY);



    //Consoles
    public static final Block TARDIS_CONSOLE = new BlockTardisConsole("tardis_console", Material.BARRIER);

    //Exteriors
    public static final Block TARDIS_EXTERIOR_01 = new BlockTardisExterior(TileExterior_01::new, "tardis_01");
    public static final Block TARDIS_EXTERIOR_02 = new BlockTardisExterior(TileExterior_02::new, "tardis_02");
    public static final Block TARDIS_EXTERIOR_03 = new BlockTardisExterior(TileExterior_03::new, "tardis_03");
    public static final Block TARDIS_EXTERIOR_04 = new BlockTardisExterior(TileExterior_04::new, "tardis_04");
    public static final Block TARDIS_EXTERIOR_05 = new BlockTardisExterior(TileExterior_05::new, "tardis_05");
    public static final Block TARDIS_EXTERIOR_06 = new BlockTardisExterior(TileExterior_06::new, "tardis_06");
}
