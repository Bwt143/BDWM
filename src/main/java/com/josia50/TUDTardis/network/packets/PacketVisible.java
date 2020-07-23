package com.josia50.TUDTardis.network.packets;

import com.josia50.TUDTardis.common.tileentity.TileExterior;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketVisible implements IMessage {

    public boolean force;
    public BlockPos pos;

    public PacketVisible() {
    }

    public PacketVisible(BlockPos pos1, boolean b) {
        this.pos = pos1;
        this.force = b;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.force = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeBoolean(force);
    }

    public static class Handler implements IMessageHandler<PacketVisible, IMessage> {

        public Handler() {

        }

        @Override
        public IMessage onMessage(PacketVisible mes, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntity te = Minecraft.getMinecraft().world.getTileEntity(mes.pos);
                if (te != null && te instanceof TileExterior) {
                    if (mes.force) {
                        ((TileExterior) te).setAlphalevel(1.0f);
                    } else {
                        ((TileExterior) te).setAlphalevel(0.0f);
                    }
                }
            });
            return null;
        }
    }
}
