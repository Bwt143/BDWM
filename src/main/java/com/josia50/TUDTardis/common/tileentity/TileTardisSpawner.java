package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import com.josia50.TUDTardis.common.world.ConsoleRoom;
import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.util.helper.Helper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import java.util.UUID;

public class TileTardisSpawner extends TileEntity implements ITickable {

    public UUID ownerid;

    @Override
    public void update() {
        if (ownerid != null) {
            Spawn();
        }
    }

    public void setOwnerid(UUID id) {
        this.ownerid = id;
    }


    public void Spawn() {
        if (!world.isRemote) {
            BlockPos position = Helper.Tardis.getTardis(ownerid);
            WorldServer World = world.getMinecraftServer().getWorld(TDimension.TARDISID);
            if (World != null && position != null) {
                TileEntity te = World.getTileEntity(position);
                if (te == null || !(te instanceof TileTardis)) {
                    ConsoleRoom.ROOM_LIST.get(0).generate((world).getMinecraftServer().getWorld(TDimension.TARDISID), position);
                    World.setBlockState(position, TBlocks.TARDIS_CONSOLE.getDefaultState());
                    TileConsole tardis = (TileConsole) World.getTileEntity(position);
                    tardis.setDestination(pos, world.provider.getDimension());
                    tardis.Land();
                }
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ownerid = compound.getUniqueId("ownerid");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setUniqueId("ownerid", ownerid);
        return compound;
    }
}
