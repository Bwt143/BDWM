package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileDoor extends TileEntity {

    public void exit(EntityPlayerMP playerMP)
    {
        SyncToConsole(32, playerMP);
    }

    public void SyncToConsole(int r, EntityPlayerMP playerMP) {
        for (BlockPos b : BlockPos.getAllInBox(pos.getX() - r, pos.getY() - r, pos.getZ() - r, pos.getX() + r, pos.getY() + r, pos.getZ() + r)) {
            if(world.getBlockState(b).getBlock() == TBlocks.TARDIS_CONSOLE) {
                if (world.getTileEntity(b) instanceof TileConsole) {
                    TileConsole tardis = (TileConsole) world.getTileEntity(b);
                    tardis.ExitTardis(playerMP);
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return compound;
    }
}
