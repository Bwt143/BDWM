package com.josia50.TUDTardis.util.helper;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.network.NetworkHandler;
import com.josia50.TUDTardis.network.packets.PacketTardis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Helper {
    public static float getAngleFromFacing(EnumFacing facing) {
        float angle = 0;

        if (facing.equals(EnumFacing.EAST))
            angle = 0;
        if (facing.equals(EnumFacing.SOUTH))
            angle = 90;
        if (facing.equals(EnumFacing.WEST))
            angle = 180;
        if (facing.equals(EnumFacing.NORTH))
            angle = 270;

        return angle;
    }

    public static class Tardis {
        public static int TARDIS_SIZE = 16;

        public static Map<String, BlockPos> owners = new HashMap<String, BlockPos>();

        public static boolean hasTardis(UUID id) {
            return owners.containsKey(id.toString());
        }

        public static BlockPos getTardis(UUID id) {
            if (hasTardis(id)) {
                return owners.get(id.toString());
            }
            return addTardis(id);
        }

        public static BlockPos addTardis(UUID id) {
            BlockPos pos = getNextFree();
           owners.put(id.toString(), pos.toImmutable());
            return pos;
        }

        public static BlockPos getLastPos() {
            int size = Tardis.owners.size();
            if (size > 0) {
                BlockPos last = BlockPos.ORIGIN;
                for (BlockPos pos : owners.values().toArray(new BlockPos[]{})) {
                    if (pos.getX() > last.getX() && pos.getZ() > last.getZ()) {
                        last = pos;
                    }
                }
                return last;
            }
            return new BlockPos(8, 128, 8);
        }

        public static BlockPos getNextFree() {
            return getLastPos().add(TARDIS_SIZE * 16, 0, TARDIS_SIZE * 16);
        }

        @Mod.EventBusSubscriber(modid = TudTardis.MODID)
        public static class Events {

            @SubscribeEvent
            public static void sync(LivingEvent.LivingUpdateEvent event) {
                if (event.getEntityLiving().world.getWorldTime() % 200 == 0 && !event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) {
                    NetworkHandler.NETWORK.sendTo(new PacketTardis(), (EntityPlayerMP) event.getEntityLiving());
                }
            }
        }
    }



}
