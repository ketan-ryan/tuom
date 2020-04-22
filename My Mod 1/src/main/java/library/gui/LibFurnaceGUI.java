package library.gui;

import library.blocks.LibFurnaceTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class LibFurnaceGUI extends GuiContainer {

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory tileFurnace;

    private LibFurnaceContainer container;

    public LibFurnaceGUI(InventoryPlayer playerInv, LibFurnaceTileEntity furnaceInv, LibFurnaceContainer container)
    {
        super(container);
        this.playerInventory = playerInv;
        this.tileFurnace = furnaceInv;
        this.container = container;
    }

    public LibFurnaceContainer getContainer() {
        return container;
    }

    public void setContainer(LibFurnaceContainer container) {
        this.container = container;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = container.getGuiTitle();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, container.getGuiTitleColor());
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, container.getGuiTitleColor());
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(container.getTextureGUI());
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        boolean test = false;
        if (test) {
            container.setArrowDirection(LibFurnaceContainer.AnimateDirection.DOWN);
            container.setBurnDirection(LibFurnaceContainer.AnimateDirection.DOWN);
        }

        boolean testFull = false;
        boolean testEmpty = false;

        //testFull = true;
        //testEmpty = true;

        int k;

        if (LibFurnaceTileEntity.isBurning(this.tileFurnace) || test)
        {
            switch (container.getAnimateDirectionBurn()) {
                case LEFT:
                    k = this.getBurnLeftScaled(container.getBurnWidth());
                    if (testFull) k = container.getBurnWidth();
                    if (testEmpty) k = 0;
                    this.drawTexturedModalRect(i + container.getBurnPositionScreenX(), j + container.getBurnPositionScreenY(),
                            container.getBurnPositionTextureX(), container.getBurnPositionTextureY(), k, container.getBurnHeight());
                    break;
                case RIGHT:
                    k = this.getBurnLeftScaled(container.getBurnWidth());
                    if (testFull) k = container.getBurnWidth();
                    if (testEmpty) k = 0;
                    this.drawTexturedModalRect(i + container.getBurnPositionScreenX() + (container.getBurnWidth()) - k, j + container.getBurnPositionScreenY(),
                            container.getBurnPositionTextureX() + container.getBurnWidth() - k, container.getBurnPositionTextureY(),
                            k, container.getBurnHeight());
                    break;
                case DOWN:
                    k = this.getBurnLeftScaled(container.getBurnHeight());
                    if (testFull) k = container.getBurnHeight();
                    if (testEmpty) k = 0;
                    this.drawTexturedModalRect(i + container.getBurnPositionScreenX(), j + container.getBurnPositionScreenY() + (container.getBurnHeight()) - k,
                            container.getBurnPositionTextureX(), container.getBurnPositionTextureY() + container.getBurnHeight() - k,
                            container.getBurnWidth(), k);
                    break;
                case UP:
                    k = this.getBurnLeftScaled(container.getBurnHeight());
                    if (testFull) k = container.getBurnHeight();
                    if (testEmpty) k = 0;
                    this.drawTexturedModalRect(i + container.getBurnPositionScreenX(), j + container.getBurnPositionScreenY(),
                            container.getBurnPositionTextureX(), container.getBurnPositionTextureY(), container.getBurnWidth(), k);
                    break;
            }

        }

        int l;
        int widthAdj;
        switch (container.getAnimateDirectionProgress()) {
            case LEFT:
                widthAdj = container.getSmeltWidth() + 2;
                l = this.getCookProgressScaled(widthAdj);
                if (testFull) l = widthAdj;
                if (testEmpty) l = 0;
                this.drawTexturedModalRect(i + container.getSmeltPositionScreenX() + (container.getSmeltWidth()) - l, j + container.getSmeltPositionScreenY(),
                        container.getSmeltPositionTextureX() + widthAdj - l, container.getSmeltPositionTextureY(),
                        l, container.getSmeltHeight());
                break;
            case RIGHT:
                widthAdj = container.getSmeltWidth() + 2;
                l = this.getCookProgressScaled(widthAdj);
                if (testFull) l = widthAdj;
                if (testEmpty) l = 0;
                this.drawTexturedModalRect(i + container.getSmeltPositionScreenX() - 2, j + container.getSmeltPositionScreenY(),
                        container.getSmeltPositionTextureX(), container.getSmeltPositionTextureY(), l, container.getSmeltHeight());
                break;
            case DOWN:
                widthAdj = container.getSmeltWidth() + 2;
                l = this.getCookProgressScaled(container.getSmeltHeight());
                if (testFull) l = container.getSmeltHeight();
                if (testEmpty) l = 0;
                this.drawTexturedModalRect(i + container.getSmeltPositionScreenX() - 2, j + container.getSmeltPositionScreenY(),
                        container.getSmeltPositionTextureX(), container.getSmeltPositionTextureY(), widthAdj, l);
                break;
            case UP:
                widthAdj = container.getSmeltWidth() + 2;
                l = this.getCookProgressScaled(container.getSmeltHeight());
                if (testFull) l = container.getSmeltHeight();
                if (testEmpty) l = 0;
                this.drawTexturedModalRect(i + container.getSmeltPositionScreenX() - 2, j + container.getSmeltPositionScreenY() + (container.getSmeltHeight()) - l,
                        container.getSmeltPositionTextureX(), container.getSmeltPositionTextureY() + container.getSmeltHeight() - l,
                        widthAdj, l + 1);
                break;

        }
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        double f = 0.00390625F;
        f = 1D / 256D;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((x + 0), (y + height), this.zLevel)       .tex((textureX + 0) * f, (textureY + height) * f).endVertex();
        bufferbuilder.pos((x + width), (y + height), this.zLevel)   .tex((textureX + width) * f, (textureY + height) * f).endVertex();
        bufferbuilder.pos((x + width), (y + 0), this.zLevel)        .tex((textureX + width) * f, (textureY + 0) * f).endVertex();
        bufferbuilder.pos((x + 0), (y + 0), this.zLevel)            .tex((textureX + 0) * f, (textureY + 0) * f).endVertex();
        tessellator.draw();
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileFurnace.getField(2);
        int j = this.tileFurnace.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileFurnace.getField(1);

        if (i == 0)
        {
            i = 200;
            //return 0;
        }

        return this.tileFurnace.getField(0) * pixels / i;
    }
}
