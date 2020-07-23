package com.josia50.TUDTardis.network.packets;

import com.josia50.TUDTardis.util.helper.Helper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;

public class PacketTardis implements IMessage {

    public Map<String, BlockPos> map = new HashMap<String, BlockPos>();

    public PacketTardis() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        for (int i = 0; i < size; ++i) {
            map.put(ByteBufUtils.readUTF8String(buf), BlockPos.fromLong(buf.readLong()));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(Helper.Tardis.owners.size());
        for (String key : Helper.Tardis.owners.keySet()) {
            ByteBufUtils.writeUTF8String(buf, key);
            buf.writeLong(Helper.Tardis.owners.get(key).toLong());
        }
    }

    public static class Handler implements IMessageHandler<PacketTardis, IMessage> {

        @Override
        public IMessage onMessage(PacketTardis message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                @Override
                public void run() {
                    Helper.Tardis.owners.clear();
                    Helper.Tardis.owners.putAll(message.map);
                }
            });
            return null;
        }

    }

}
