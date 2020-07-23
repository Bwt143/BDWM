package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileDematLever extends TileEntity {

    public void LeverHandle() {
      SyncToConsole(600, 32);
    }

    public void SyncToConsole(int time, int r) {
        for (BlockPos b : BlockPos.getAllInBox(pos.getX() - r, pos.getY() - r, pos.getZ() - r, pos.getX() + r, pos.getY() + r, pos.getZ() + r)) {
            if(world.getBlockState(b).getBlock() == TBlocks.TARDIS_CONSOLE) {
                if (world.getTileEntity(b) instanceof TileConsole) {
                    TileConsole tardis = (TileConsole) world.getTileEntity(b);
                    tardis.Travel(time);
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
        return super.writeToNBT(compound);
    }
}
