package com.josia50.TUDTardis.network.packets;

import com.josia50.TUDTardis.common.tileentity.TileConsole;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDim implements IMessage {

    public BlockPos monitorpos;
    public int dim;

    public PacketDim() {
    }

    public PacketDim(BlockPos monitorpos, int dim) {
        this.monitorpos = monitorpos;
        this.dim = dim;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.dim = buf.readInt();
        this.monitorpos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dim);
        buf.writeLong(monitorpos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketDim, IMessage> {

        @Override
        public IMessage onMessage(PacketDim message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                WorldServer world = ctx.getServerHandler().player.getServerWorld();
                TileConsole coord = (TileConsole) world.getTileEntity(message.monitorpos);
                coord.setDestinationDim(message.dim);
            });
            return null;
        }

    }

}
