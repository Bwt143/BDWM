package com.josia50.TUDTardis.common.world;

import com.josia50.TUDTardis.TudTardis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import java.util.ArrayList;
import java.util.List;

public class ConsoleRoom {

    public static List<ConsoleRoom> ROOM_LIST = new ArrayList<ConsoleRoom>();

    private ResourceLocation room_location;
    private BlockPos console = BlockPos.ORIGIN;

    public ConsoleRoom(ResourceLocation src, BlockPos pos) {
        this.room_location = src;
        this.console = pos;
    }

    public void generate(WorldServer world, BlockPos pos) {
        Template template = world.getStructureTemplateManager().get(world.getMinecraftServer(), room_location);
        PlacementSettings placementSettings = new PlacementSettings();
        template.addBlocksToWorld(world, pos.subtract(console), placementSettings);
    }

    public static void registerConsoleRoom(String interiorName, BlockPos pos) {
        ROOM_LIST.add(new ConsoleRoom(new ResourceLocation(TudTardis.MODID, interiorName), pos));
    }
}
