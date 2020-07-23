package com.josia50.TUDTardis.common.blocks;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.common.items.TItems;
import com.josia50.TUDTardis.common.tileentity.TileTardisSpawner;
import com.josia50.TUDTardis.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTardisSpawner extends Block implements IHasModel, ITileEntityProvider {

    public BlockTardisSpawner(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setTickRandomly(true);
        setCreativeTab(CreativeTabs.COMBAT);

        TBlocks.BLOCK_LIST.add(this);
        TItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileTardisSpawner tileTardisSpawner = (TileTardisSpawner) worldIn.getTileEntity(pos);
        tileTardisSpawner.setOwnerid(placer.getUniqueID());
    }

    @Override
    public void registerModels() {
        TudTardis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTardisSpawner();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
