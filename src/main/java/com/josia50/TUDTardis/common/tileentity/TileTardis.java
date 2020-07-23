package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import com.josia50.TUDTardis.util.handlers.SoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileTardis extends TileEntity implements ITickable, IInventory {

    protected BlockPos location = BlockPos.ORIGIN;
    protected BlockPos destination = BlockPos.ORIGIN;
    private BlockPos consolepos = this.pos;
    private int traveltime = 0;
    private int dimension = 0;
    private int destinationdim = 0;
    private boolean drifting = false;
    private boolean flight = false;
    private int exteriorid = 0;


    @Override
    public void update() {

        if (!world.isRemote) {
            World dim = world.getMinecraftServer().getWorld(dimension);
            World destdim = world.getMinecraftServer().getWorld(destinationdim);

            if (traveltime != 0 && flight && !(dim.getBlockState(location) == getExteriorState() && !drifting)) {
                this.traveltime--;
            }

            if (traveltime == 0 && !drifting) {
                // TODO Play BAM sound
            }


            if (traveltime == 80) {
                destdim.playSound(null, destination.getX(), destination.getY(), destination.getZ(), SoundHandler.SHORT_REMAT, SoundCategory.MASTER, 1.0f, 1.0f);
                Land();
            }
        }
    }

    public void setLocation(BlockPos opos) {
        this.location = opos;
        this.markDirty();
    }

    public void Travel() {
        if (!world.isRemote) {
            if (!flight) {
                World dim = world.getMinecraftServer().getWorld(dimension);
                if (dim.getTileEntity(location) instanceof TileExterior) {
                    TileExterior te = (TileExterior) dim.getTileEntity(location);
                    te.setDemat();
                }
                this.traveltime = 300;
                this.flight = true;
            } else {
                QuickLand();
            }
        }
        this.markDirty();
    }

    public void QuickLand() {
        this.traveltime = 80;
        this.markDirty();
    }

    public void setDrifting(boolean drift) {
        this.drifting = drift;
        this.markDirty();
    }

    public boolean getDrifting() {
        return this.drifting;
    }

    public void Land() {
        if (!world.isRemote) {
            World dim = world.getMinecraftServer().getWorld(destinationdim);
            dim.setBlockState(destination, getExteriorState().getDefaultState());
            ExteriorData(dim);
            flight = false;
            this.location = destination;
            this.dimension = destinationdim;
            this.markDirty();
        }
    }

    public void ExteriorData(World dim) {
        TileExterior tileExterior = (TileExterior) dim.getTileEntity(destination);
        tileExterior.setConsolePos(getPos());
    }

    public void setExteriorid(int id) {
        this.exteriorid = id;
    }

    public void setDestination(BlockPos p, int dima) {
        this.destination = p.toImmutable();
        this.destinationdim = dima;
        this.markDirty();
    }

    public int getDestinationdim() {
        return this.destinationdim;
    }

    public int getDimension() {
        return this.dimension;
    }

    public BlockPos getLocation() {
        return this.location;
    }

    public BlockPos getDestination() {
        return this.destination;
    }

    public Block getExteriorState() {
        if(this.exteriorid == 0) {
            return TBlocks.TARDIS_EXTERIOR_01;
        }

        if(this.exteriorid == 1) {
            return TBlocks.TARDIS_EXTERIOR_02;
        }

        if(this.exteriorid == 2) {
            return TBlocks.TARDIS_EXTERIOR_03;
        }

        if(this.exteriorid == 3) {
            return TBlocks.TARDIS_EXTERIOR_04;
        }
        return TBlocks.TARDIS_EXTERIOR_01;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        compound.setLong("location", this.location.toLong());
        compound.setLong("destination", this.destination.toLong());
        compound.setLong("consolepos", this.consolepos.toLong());
        compound.setInteger("traveltime", this.traveltime);
        compound.setInteger("dimension", this.dimension);
        compound.setInteger("dest_dimension", this.destinationdim);
        compound.setBoolean("drifting", this.drifting);
        compound.setInteger("exterior_id", this.exteriorid);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.location = BlockPos.fromLong(compound.getLong("location"));
        this.destination = BlockPos.fromLong(compound.getLong("destination"));
        this.consolepos = BlockPos.fromLong(compound.getLong("consolepos"));
        this.dimension = compound.getInteger("dimension");
        this.destinationdim = compound.getInteger("dest_dimension");
        this.drifting = compound.getBoolean("drifting");
        this.exteriorid = compound.getInteger("exterior_id");
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return super.shouldRefresh(world, pos, oldState, newSate);
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
