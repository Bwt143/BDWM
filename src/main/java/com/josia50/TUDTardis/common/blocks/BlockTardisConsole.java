package com.josia50.TUDTardis.common.blocks;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.common.tileentity.TileConsole;
import com.josia50.TUDTardis.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTardisConsole extends Block implements IHasModel, ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockTardisConsole(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setBlockUnbreakable();

        TBlocks.BLOCK_LIST.add(this);
        // TItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) playerIn;
            TileConsole tardis = (TileConsole) worldIn.getTileEntity(pos);
            System.out.println(tardis.getDestinationlocation() + " destin");
            System.out.println(tardis.getLocation() + " locat");
            System.out.println(tardis.getDimension() + " locat dim");
            System.out.println(tardis.getDestinationdimension() + " destin dim");
            System.out.println(tardis.getPos() + "consol pos");
            System.out.println(tardis.getFlight() + " flight");
        }
        return true;
    }

    @Override
    public void registerModels() {
        TudTardis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileConsole();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
