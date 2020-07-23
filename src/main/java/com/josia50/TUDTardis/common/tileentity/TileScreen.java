package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileScreen extends TileEntity {

    private int ExteriorID = 0;
    public int maxexterior = 5;

    public void setExteriorID() {
        if(!world.isRemote) {
            if (ExteriorID < maxexterior) {
                ExteriorID++;
            } else {
                ExteriorID = 0;
            }
        }
            SyncToConsole(this.ExteriorID, 32); // TODO make config for this "32" value;;
    }

    public int getExteriorId() {
        return ExteriorID;
    }

    public void SyncToConsole(int id, int r) {
        for (BlockPos b : BlockPos.getAllInBox(pos.getX() - r, pos.getY() - r, pos.getZ() - r, pos.getX() + r, pos.getY() + r, pos.getZ() + r)) {
            if(world.getBlockState(b).getBlock() == TBlocks.TARDIS_CONSOLE) {
                if (world.getTileEntity(b) instanceof TileConsole) {
                    TileConsole tardis = (TileConsole) world.getTileEntity(b);
                    tardis.setExteriorid(id);
                    System.out.println(id +" screen");
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ExteriorID = compound.getInteger("exterior_id");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("exterior_id", ExteriorID);
        return compound;
    }
}
