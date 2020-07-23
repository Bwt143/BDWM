package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.util.handlers.SoundHandler;
import com.josia50.TUDTardis.util.helper.Teleporter;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class TileConsole extends TileEntity implements ITickable {

    private BlockPos exteriorlocation = BlockPos.ORIGIN;
    private BlockPos destinationlocation = BlockPos.ORIGIN;
    private int exteriordimension = 0;
    private int destinationdimension = 0;
    private int exteriorid = 0;
    private boolean flight = false;
    private boolean drifting = false;
    private int traveltime = 0;
    private int tick = 0;
    public int dimensions = 0;

    private int demattime = 250;
    private int landtime = 82;
    private int afterdemat;
    private int looptime = 0;

    @Override
    public void update() {
        if (!world.isRemote) {
            World dim = world.getMinecraftServer().getWorld(getDimension());
            World destdim = world.getMinecraftServer().getWorld(getDestinationdimension());

            if (traveltime != 0 && flight && !getDrifting()) {
                this.traveltime--;
                System.out.println(traveltime);
            }

            if (traveltime == 0 && !getDrifting()) {
                // TODO Play BAM sound
                setFlight(false);
            }

            if (tick == 1) {
                for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(16))) {
                    player.connection.sendPacket(new SPacketSoundEffect(SoundHandler.DEMAT, SoundCategory.AMBIENT, getPos().getX(), getPos().getY(), getPos().getZ(), 0.5F, 1F));
                }
                tick = 0;
            }

            if (traveltime <= 350 && traveltime > 90) {
                looptime++;
                if (looptime >= 60) {
                    for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(16))) {
                        player.connection.sendPacket(new SPacketSoundEffect(SoundHandler.FLY, SoundCategory.AMBIENT, getPos().getX(), getPos().getY(), getPos().getZ(), 0.5F, 1F));
                    }
                    looptime = 0;
                }
            }


            if (traveltime == 80) {
                for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPos()).grow(16))) {
                    player.connection.sendPacket(new SPacketSoundEffect(SoundHandler.SHORT_REMAT, SoundCategory.AMBIENT, getPos().getX(), getPos().getY(), getPos().getZ(), 0.5F, 1F));
                }
                Land();
            }
        }
    }

    public void Land() {
        World dim = world.getMinecraftServer().getWorld(getDestinationdimension());
        World dima = world.getMinecraftServer().getWorld(getDimension());
        dima.setBlockToAir(getLocation());
        dim.setBlockState(getDestinationlocation(), getExteriorState().getDefaultState());
        for (EntityPlayerMP player : dim.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(getLocation()))) {
            EnterTardis(player);
        }
        setExteriorData(dim);
        setDimension(getDestinationdimension());
        setLocation(getDestinationlocation());
    }

    public void Travel(int time) {
        if (!flight) {
            if(!(getDestinationdimension() == TDimension.TARDISID)) {
                if (time > demattime + landtime + 10) {
                    if (!world.isRemote) {
                        World dim = world.getMinecraftServer().getWorld(getDimension());
                        if (dim.getTileEntity(getLocation()) instanceof TileExterior) {
                            TileExterior te = (TileExterior) dim.getTileEntity(getLocation());
                            te.setDemat();
                            for (EntityPlayerMP player : dim.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getLocation()).grow(16))) {
                                player.connection.sendPacket(new SPacketSoundEffect(SoundHandler.DEMAT, SoundCategory.AMBIENT, getLocation().getX(), getLocation().getY(), getLocation().getZ(), 0.5F, 1F));
                            }
                        }
                    }
                    this.traveltime = time;
                    setFlight(true);
                    this.afterdemat = time - demattime;
                    this.tick = 1;
                }
            }else{
                if(!world.isRemote) {
                    for (EntityPlayerMP player : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getLocation()).grow(16))) {
                        player.connection.sendPacket(new SPacketSoundEffect(SoundHandler.DENIED_TAKEOFF, SoundCategory.AMBIENT, getLocation().getX(), getLocation().getY(), getLocation().getZ(), 0.5F, 1F));
                        player.sendStatusMessage(new TextComponentString("Can't land in the tardis dimension!"), true);
                    }
                }
            }
        } else {
            if (traveltime < 350 && traveltime > landtime)
                QuickLand();
        }
    }

    public void ExitTardis(EntityPlayerMP player) {
        if (!flight) {
            world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, getDimension(), new Teleporter(new BlockPos(pos.getX(), pos.getY(), pos.getZ())));
            player.connection.setPlayerLocation(getLocation().getX(), getLocation().getY() + 1, getLocation().getZ(), 1, 1);
        }else{
            player.sendStatusMessage(new TextComponentString("You can't exit the tardis while being in flight!"), true);
        }
    }

    public void EnterTardis(EntityPlayerMP player) {
        world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, TDimension.TARDISID, new Teleporter(new BlockPos(pos.getX(), pos.getY(), pos.getZ())));
        player.connection.setPlayerLocation(getPos().getX(), getPos().getY() + 1, getPos().getZ(), 1, 1);
    }

    public void QuickLand() {
        this.traveltime = 81;
    }

    public int getTravelTime() {
        return this.traveltime;
    }



    public void setExteriorData(World dim) {
        TileExterior tileExterior = (TileExterior) dim.getTileEntity(getDestinationlocation());
        tileExterior.setConsolePos(getPos());
    }

    public void setExteriorid(int id) {
        this.exteriorid = id;
    }

    public void setDrifting(boolean drift) {
        this.drifting = drift;
    }

    public boolean getDrifting() {
        return this.drifting;
    }

    public void setFlight(boolean flights) {
        this.flight = flights;
    }

    public boolean getFlight() {
        return this.flight;
    }

    public void setLocation(BlockPos pos1) {
        this.exteriorlocation = pos1;
    }

    public void setDestination(BlockPos pos, int dim) {
        setDestinationLocation(pos);
        setDestinationDim(dim);
    }

    public void setDestinationLocation(BlockPos blockPos) {
        this.destinationlocation = blockPos;
    }

    public void setDimension(int dimension) {
        this.exteriordimension = dimension;
    }

    public void setDestinationDim(int dim) {
        this.destinationdimension = dim;
    }

    public int getDimension() {
        return this.exteriordimension;
    }

    public int getDestinationdimension() {
        return this.destinationdimension;
    }

    public BlockPos getLocation() {
        return this.exteriorlocation;
    }

    public BlockPos getDestinationlocation() {
        return this.destinationlocation;
    }

    public Block getExteriorState() {
        if (this.exteriorid == 0) {
            return TBlocks.TARDIS_EXTERIOR_01;
        }

        if (this.exteriorid == 1) {
            return TBlocks.TARDIS_EXTERIOR_02;
        }

        if (this.exteriorid == 2) {
            return TBlocks.TARDIS_EXTERIOR_03;
        }

        if (this.exteriorid == 3) {
            return TBlocks.TARDIS_EXTERIOR_04;
        }

        if (this.exteriorid == 4) {
            return TBlocks.TARDIS_EXTERIOR_05;
        }

        if(this.exteriorid == 5) {
            return TBlocks.TARDIS_EXTERIOR_06;
        }
        return TBlocks.TARDIS_EXTERIOR_01;
    }


    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.exteriorlocation = BlockPos.fromLong(compound.getLong("ext_pos"));
        this.destinationlocation = BlockPos.fromLong(compound.getLong("dest_pos"));
        this.exteriordimension = compound.getInteger("dimension");
        this.destinationdimension = compound.getInteger("dest_dimension");
        this.traveltime = compound.getInteger("traveltime");
        this.flight = compound.getBoolean("flight");
        this.drifting = compound.getBoolean("drifting");
        this.exteriorid = compound.getInteger("exteriorid");
        this.looptime = compound.getInteger("looptime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong("ext_pos", this.exteriorlocation.toLong());
        compound.setLong("dest_pos", this.destinationlocation.toLong());
        compound.setInteger("dimension", this.exteriordimension);
        compound.setInteger("dest_dimension", this.exteriordimension);
        compound.setInteger("traveltime", this.traveltime);
        compound.setInteger("exteriorid", this.exteriorid);
        compound.setInteger("looptime", this.looptime);
        compound.setBoolean("flight", this.flight);
        compound.setBoolean("drifting", this.drifting);
        return super.writeToNBT(compound);
    }
}
