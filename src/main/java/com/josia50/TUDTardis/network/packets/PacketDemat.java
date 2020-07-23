package com.josia50.TUDTardis.network.packets;

import com.josia50.TUDTardis.common.tileentity.TileExterior;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDemat implements IMessage {

    public boolean isDemat;
    public BlockPos pos;

    public PacketDemat() {
    }

    public PacketDemat(BlockPos pos1, boolean b) {
        this.pos = pos1;
        this.isDemat = b;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = BlockPos.fromLong(buf.readLong());
        this.isDemat = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeBoolean(isDemat);
    }

    public static class Handler implements IMessageHandler<PacketDemat, IMessage> {

        public Handler() {

        }

        @Override
        public IMessage onMessage(PacketDemat mes, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntity te = Minecraft.getMinecraft().world.getTileEntity(mes.pos);
                if (te != null && te instanceof TileExterior) {
                    if (mes.isDemat) {
                        ((TileExterior) te).setDemat();
                    } else {
                        ((TileExterior) te).setRemat();
                    }
                }
            });
            return null;
        }
    }
}
