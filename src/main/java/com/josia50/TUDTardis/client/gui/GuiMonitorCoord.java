package com.josia50.TUDTardis.client.gui;


import com.josia50.TUDTardis.network.NetworkHandler;
import com.josia50.TUDTardis.network.packets.PacketCoord;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class GuiMonitorCoord extends GuiScreen {

    public GuiTextField xCoord;
    public GuiTextField yCoord;
    public GuiTextField zCoord;
    private GuiButton warp;
    private GuiButton coord;

    static final int GUI_WIDTH = 256;
    static final int GUI_HEIGHT = 256;

    private Minecraft mc;
    private FontRenderer fr;
    private BlockPos blockPos;

    public GuiMonitorCoord(BlockPos pos) {
        mc = Minecraft.getMinecraft();
        fr = mc.fontRenderer;
        blockPos = pos;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == this.warp) {
            BlockPos tpPos = new BlockPos(getInt(xCoord.getText(), XYZ.X), getInt(yCoord.getText(), XYZ.Y), getInt(zCoord.getText(), XYZ.Z));
            NetworkHandler.NETWORK.sendToServer(new PacketCoord(tpPos, blockPos));
            Minecraft.getMinecraft().displayGuiScreen(null);
        }

        super.actionPerformed(button);
    }

    private int getInt(String num, XYZ type) {
        if (num != null && !num.isEmpty()) {
            int i;
            try {
                i = Integer.parseInt(num);
            } catch (Exception e) {
                i = 0;
            }
            return i;
        } else {

            switch (type) {
                case X:
                    return (int) mc.player.posX;
                case Y:
                    return (int) mc.player.posY;
                case Z:
                    return (int) mc.player.posZ;
                default:
                    return 0;
            }
        }

    }

    public class SmallButton extends GuiButton {

        int BUTTON_WIDTH = 50;
        int BUTTON_HEIGHT = 60;

        public SmallButton(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, buttonText);
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            this.width = BUTTON_WIDTH;
            this.height = BUTTON_HEIGHT;
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);

            int j = 14737632;

            if (packedFGColour != 0) {
                j = packedFGColour;
            } else if (!this.enabled) {
                j = 10526880;
            } else if (this.hovered) {
                j = 16777120;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }

    }

    @Override
    public void initGui() {
        int x = width / 2 - 15;
        int y = height / 2 - 15;
        String warpButton = "Warp";

        int yOffset = fr.FONT_HEIGHT + 3;

        xCoord = new GuiTextField(0, fr, x - 40, y * 1 + 10, 100, fr.FONT_HEIGHT);
        yCoord = new GuiTextField(1, fr, x - 40, y + yOffset * 2, 100, fr.FONT_HEIGHT);
        zCoord = new GuiTextField(2, fr, x - 40, y + yOffset * 3 + 1, 100, fr.FONT_HEIGHT);
        warp = new GuiButton(3, x + 11, y + yOffset * 9, warpButton);
        warp.x -= warp.width / 2;
        xCoord.setFocused(true);

        this.buttonList.clear();
        this.addButton(warp);
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        xCoord.mouseClicked(mouseX, mouseY, mouseButton);
        yCoord.mouseClicked(mouseX, mouseY, mouseButton);
        zCoord.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;

        this.drawTexturedModalRect(x, y, 0, 0, GUI_WIDTH, GUI_HEIGHT);
        xCoord.drawTextBox();
        yCoord.drawTextBox();
        zCoord.drawTextBox();


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        xCoord.textboxKeyTyped(typedChar, keyCode);
        yCoord.textboxKeyTyped(typedChar, keyCode);
        zCoord.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void updateScreen() {
        xCoord.updateCursorCounter();
        yCoord.updateCursorCounter();
        zCoord.updateCursorCounter();
        super.updateScreen();
    }

    public enum XYZ {
        X, Y, Z
    }
}
