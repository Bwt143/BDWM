package com.josia50.TUDTardis.util.handlers;

import com.josia50.TUDTardis.common.blocks.TBlocks;
import com.josia50.TUDTardis.common.items.TItems;
import com.josia50.TUDTardis.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TItems.ITEM_LIST.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(TBlocks.BLOCK_LIST.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {


        for (Item item : TItems.ITEM_LIST) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

        for (Block block : TBlocks.BLOCK_LIST) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }
    }

}
