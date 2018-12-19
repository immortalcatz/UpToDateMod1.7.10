package yuma140902.uptodatemod.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class BarrelGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/gui/barrel.png");
	private TileEntityBarrel tileEntity;

	public BarrelGuiContainer(EntityPlayer player, TileEntityBarrel tileEntity) {
		super(new BarrelContainer(player, tileEntity));
		this.tileEntity = tileEntity;
		this.xSize = 176;
		this.ySize = 166;
	}
	
	/*GUIの文字等の描画処理*/
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString("Barrel", 12, 8, 0, false);
	}

	/*GUIの背景の描画処理*/
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
		this.mc.renderEngine.bindTexture(TEXTURE);
		int xStart = (this.width - this.xSize) / 2;
    int yStart = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
	}
	
	/*GUIが開いている時にゲームの処理を止めるかどうか。*/
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
}