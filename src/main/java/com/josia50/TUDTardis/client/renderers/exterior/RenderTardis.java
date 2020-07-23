package com.josia50.TUDTardis.client.renderers.exterior;

import com.josia50.TUDTardis.TudTardis;
import com.josia50.TUDTardis.client.models.ModelTardis;
import com.josia50.TUDTardis.common.blocks.BlockTardisExterior;
import com.josia50.TUDTardis.common.tileentity.exteriors.TileExterior_06;
import com.josia50.TUDTardis.util.helper.Helper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTardis extends TileEntitySpecialRenderer<TileExterior_06> {

    public static ModelTardis billted = new ModelTardis();
    public static final ResourceLocation TEXTURE = new ResourceLocation(TudTardis.MODID, "textures/blocks/tardis.png");


    @Override
    public void render(TileExterior_06 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
        GlStateManager.rotate(180.0F, 90.0F, 0.0F, 90.0F);
        if (te.getWorld() == null || te.getPos() == null) return;
        IBlockState state = te.getWorld().getBlockState(te.getPos());
        if (state.getBlock() instanceof BlockTardisExterior) {
            GlStateManager.rotate(Helper.getAngleFromFacing(state.getValue(BlockTardisExterior.FACING)), 0, 1, 0);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.color(1, 1, 1, te.getAlphaLevel());
            Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
            billted.render(null, 0, 0, 0, 0, 0, 0.0625F);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

}
