package com.josia50.TUDTardis.common.items;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.common.tileentity.TileConsole;
import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.util.helper.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemRemote extends Item implements IHasModel {

    public ItemRemote(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.COMBAT);

        TItems.ITEM_LIST.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = (TileEntity) worldIn.getTileEntity(pos);
        if(tileEntity != null && tileEntity instanceof TileConsole) {
            setConsolePos(player.getHeldItem(hand), pos);
            return EnumActionResult.SUCCESS;
        }else {
            if(!worldIn.isRemote) {
                TileConsole tileConsole = (TileConsole) worldIn.getMinecraftServer().getWorld(TDimension.TARDISID).getTileEntity(getConsolePos(player.getHeldItem(hand)));
                if (tileConsole != null) {
                    if (worldIn.provider.getDimension() != TDimension.TARDISID) {
                        tileConsole.setDestination(pos.up(1), player.dimension);
                        tileConsole.Land();
                    }else{
                        player.sendStatusMessage(new TextComponentString("Can't land in Tardis Dimension!"), true);
                    }
                }
            }
        }
        return EnumActionResult.SUCCESS;
    }

    public void setConsolePos(ItemStack stack, BlockPos pos) {
        if(stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setLong("consolepos", pos.toLong());
    }

    public BlockPos getConsolePos(ItemStack stack) {
        if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("consolepos")) {
            return BlockPos.fromLong(stack.getTagCompound().getLong("consolepos"));
        }
        return BlockPos.ORIGIN;
    }

    @Override
    public void registerModels() {
        TudTardis.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
