package mod.simonsmod.core.containers;

import org.lwjgl.opengl.GL11;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperDuct;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmni;
import mod.simonsmod.core.objects.tileEntity.TileEntityTest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHopperOmni extends GuiContainer
{
    /** The ResourceLocation containing the chest GUI texture. */
    private static final ResourceLocation HOPPER_DUCT_GUI_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/container/hopper_omni.png");
    private IInventory playerInventory;
	private IInventory ductInventory;
    private final int inventoryRows;

    public GuiHopperOmni(InventoryPlayer hopperInv, IInventory playerInv) {
    	super(new ContainerHopperOmni(hopperInv, playerInv, Minecraft.getMinecraft().player));
        this.playerInventory = playerInv;
        this.ductInventory = hopperInv;
        this.allowUserInput = false;
        this.inventoryRows = hopperInv.getSizeInventory() / 9;
        int i = 222;
        int j = 114;
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
    	//TileEntity tileentity = null;
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
        this.fontRenderer.drawString(this.ductInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 128 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(this.HOPPER_DUCT_GUI_TEXTURE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

}