package scobmyster.runemagic.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scobmyster.runemagic.Reference;
import scobmyster.runemagic.container.ContainerNaturalTable;

public class GUINaturalTable extends GuiContainer
{

    private InventoryPlayer playerinv;

    public GUINaturalTable(InventoryPlayer playerinv, World world, int x, int y, int z)
    {
        super(new ContainerNaturalTable(playerinv, world, new BlockPos(x, y, z)));

        this.xSize = 176;
        this.ySize = 166;
        this.playerinv = playerinv;
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/container/natural_table.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
       String s  = I18n.format("container.natural_table");
       this.mc.fontRendererObj.drawString(s, 28, 6, 4210752);
       this.mc.fontRendererObj.drawString(this.playerinv.getDisplayName().getFormattedText(), 8, this.ySize - 96 + 2,4210752 );
    }
}
