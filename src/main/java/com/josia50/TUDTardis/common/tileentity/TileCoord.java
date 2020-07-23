package com.josia50.TUDTardis.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileCoord extends TileEntity {

    private BlockPos blockPos = BlockPos.ORIGIN;

   public void setConsolePos(BlockPos pos) {
        this.blockPos = pos;
        markDirty();
    }

    public BlockPos GetConsolePos() {
       return this.blockPos;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.blockPos = BlockPos.fromLong(compound.getLong("pos"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setLong("pos", this.blockPos.toLong());
        return compound;
    }
}
