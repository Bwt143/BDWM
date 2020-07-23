package com.josia50.TUDTardis.network.packets;

import com.josia50.TUDTardis.common.tileentity.TileConsole;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCoord implements IMessage {

    public BlockPos pos;
    public BlockPos monitorpos;

    public PacketCoord() {
    }

    public PacketCoord(BlockPos pos, BlockPos monitorpos) {
        this.pos = pos;
        this.monitorpos = monitorpos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.monitorpos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeLong(monitorpos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketCoord, IMessage> {

        @Override
        public IMessage onMessage(PacketCoord message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                WorldServer world = ctx.getServerHandler().player.getServerWorld();
                TileConsole coord = (TileConsole) world.getTileEntity(message.monitorpos);
                coord.setDestinationLocation(message.pos);
            });
            return null;
        }

    }

}
