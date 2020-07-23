package com.josia50.TUDTardis.common.blocks;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.common.items.TItems;
import com.josia50.TUDTardis.common.tileentity.TileConsole;
import com.josia50.TUDTardis.common.tileentity.TileCoord;
import com.josia50.TUDTardis.util.helper.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;

public class BlockCoord extends Block implements IHasModel, ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockCoord(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.COMBAT);

        TBlocks.BLOCK_LIST.add(this);
        TItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void registerModels() {
        TudTardis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCoord();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        TileCoord coord = (TileCoord) worldIn.getTileEntity(pos);

        if (coord.GetConsolePos() == BlockPos.ORIGIN) {
                for (BlockPos pos1 : BlockPos.getAllInBox(pos.getX() - 32, pos.getY() - 32, pos.getZ() - 32, pos.getX() + 32, pos.getY() + 32, pos.getZ() + 32)) {
                    if (worldIn.getBlockState(pos1).getBlock() == TBlocks.TARDIS_CONSOLE) {
                        if (worldIn.getTileEntity(pos1) instanceof TileConsole) {
                            coord.setConsolePos(pos1);
                        }
                    }
                }

        } else {
            TileConsole console = (TileConsole) worldIn.getTileEntity(coord.GetConsolePos());

            if (!worldIn.isRemote) {
                if (!playerIn.isSneaking()) {
                    Integer[] dim = DimensionManager.getStaticDimensionIDs();
                    ++console.dimensions;

                    if (console.dimensions >= dim.length)
                        console.dimensions = 0;

                    playerIn.sendStatusMessage(new TextComponentString("Dimension :" + DimensionManager.createProviderFor(dim[console.dimensions]).getDimensionType().getName()), true);
                    console.setDestinationDim(dim[console.dimensions]);
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
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
