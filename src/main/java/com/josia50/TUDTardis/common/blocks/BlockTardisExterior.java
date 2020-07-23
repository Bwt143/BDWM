package com.josia50.TUDTardis.common.blocks;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.common.tileentity.TileExterior;
import com.josia50.TUDTardis.common.world.dimension.TDimension;
import com.josia50.TUDTardis.util.handlers.SoundHandler;
import com.josia50.TUDTardis.util.helper.IHasModel;
import com.josia50.TUDTardis.util.helper.Teleporter;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class BlockTardisExterior extends BlockTileBase implements IHasModel {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockTardisExterior(Supplier<TileEntity> tileEntity, String name) {
        super(Material.IRON, tileEntity);
        setUnlocalizedName(name);
        setRegistryName(name);
        setBlockUnbreakable();
        setTickRandomly(true);

        TBlocks.BLOCK_LIST.add(this);
        //TItems.ITEM_LIST.add(new ItemBlock(this).setRegistryName(name));
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileExterior tardis = (TileExterior) worldIn.getTileEntity(pos);

        if (playerIn instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) playerIn;
            if(player.isSneaking()) {
                worldIn.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, TDimension.TARDISID, new Teleporter(new BlockPos(pos.getX(), pos.getY(), pos.getZ())));
                player.connection.setPlayerLocation(tardis.getConsolepos().getX(), tardis.getConsolepos().getY() + 1, tardis.getConsolepos().getZ(), 1, 1);
            }else{
                System.out.println(tardis.getConsolepos());
            }
        }
        return true;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return false;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        return false;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        System.out.println("Exterior Landed in dimension: " + worldIn.provider.getDimension());
        worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.SHORT_REMAT, SoundCategory.MASTER, 1f, 1f);
    }

    @Override
    public void registerModels() {
        TudTardis.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
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


