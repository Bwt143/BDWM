package com.josia50.TUDTardis.common.tileentity;

import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.network.NetworkHandler;
import com.josia50.TUDTardis.network.packets.PacketDemat;
import com.josia50.TUDTardis.network.packets.PacketVisible;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class TileExterior extends TileEntity implements ITickable {

    private int exteriorid = 0;
    private float alphalevel = 1.0f;
    private BlockPos consolepos = BlockPos.ORIGIN;
    public boolean isRemat, isDemat = false;
    private boolean requiresUpdate = true;

    public TileExterior() {
        this.isRemat = true;
        this.alphalevel = 0.0f;
    }

    public void setRemat() {
        this.isRemat = true;
        this.alphalevel = 0;
        sendDematPacket(false);
    }

    public void setDemat() {
        this.isDemat = true;
        this.alphalevel = 1;
        sendDematPacket(true);
    }

    public void setConsolePos(BlockPos pos) {
        this.consolepos = pos;
    }

    public BlockPos getConsolepos() {
        return consolepos;
    }


    public void sendDematPacket(boolean demat) {
        if (!world.isRemote)
            NetworkHandler.NETWORK.sendToAllAround(new PacketDemat(this.getPos(), demat), new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 64));
    }

    public void setAlphalevel(float alpha) {
        this.alphalevel = alpha;
    }

    public float getAlphaLevel() {
        return alphalevel;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return true;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public void update() {
        if (world == null) return;
        if (!world.isRemote) {
            WorldServer ws = (WorldServer) world;
            TileConsole tardis = (TileConsole) ws.getMinecraftServer().getWorld(TDimension.TARDISID).getTileEntity(getConsolepos());
            if (tardis == null) return;
        }

        if (isRemat) {
            if (alphalevel < 1.0F) {
                alphalevel += 0.007F;
            } else {
                this.isRemat = false;
                this.alphalevel = 1.0F;
            }
        }
        if (isDemat) {
            alphalevel -= 0.005F;
            if (alphalevel <= 0) {
                this.isDemat = false;
                this.world.setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
            }
        }
        if (!this.isRemat && !this.isDemat) this.alphalevel = 1.0F;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        net.minecraft.util.math.AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }

    public void forcevisible() {

        if (!world.isRemote) {
            this.alphalevel = 1.0f;
            NetworkHandler.NETWORK.sendToAllAround(new PacketVisible(this.getPos(), true), new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), getPos().getX(), getPos().getY(), getPos().getZ(), 64));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        exteriorid = tag.getInteger("exterior_id");
        alphalevel = tag.getFloat("alpha_level");
        isDemat = tag.getBoolean("is_demat");
        isRemat = tag.getBoolean("is_remat");
        consolepos = BlockPos.fromLong(tag.getLong("console_pos"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("exterior_id", exteriorid);
        tag.setFloat("alpha_level", alphalevel);
        tag.setBoolean("is_demat", isDemat);
        tag.setBoolean("is_remat", isRemat);
        tag.setLong("console_pos", consolepos.toLong());
        return tag;
    }
}
