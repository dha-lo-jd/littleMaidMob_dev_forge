package net.minecraft.src;

<<<<<<< HEAD
/**
 * ‘I‘ðŽž‚ÉƒT[ƒo[‚Öõ—¿‚ÌŽg—p‚ð’Ê’m‚·‚é‚½‚ß‚Ìˆ—B
 */
public class LMM_GuiTextureSelect extends MMM_GuiTextureSelect {

	public LMM_GuiTextureSelect(GuiScreen pOwner, MMM_ITextureEntity pTarget,
			int pColor, boolean pToServer) {
		super(pOwner, pTarget, pColor, pToServer);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);
=======
import java.util.Map;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.EXTRescaleNormal;
import org.lwjgl.opengl.GL11;

public class LMM_GuiTextureSelect extends GuiScreen {

	private String screenTitle = "Texture Select";
	private boolean lastDebug;
	public GuiScreen owner;
	public LMM_GuiTextureSlot selectPanel;
	public GuiButton modeButton[] = new GuiButton[2];
	public LMM_EntityLittleMaid theMaid;
	public int canSelectColor;
	public int selectColor;
	public boolean toServer;


	public LMM_GuiTextureSelect(GuiScreen pOwner, LMM_EntityLittleMaid pEntity, int pColor, boolean pToServer) {
		owner = pOwner;
		theMaid = pEntity;
		canSelectColor = pColor;
		selectColor = pEntity.maidColor;
		toServer = pToServer;
		lastDebug = mod_LMM_littleMaidMob.DebugMessage;
		mod_LMM_littleMaidMob.DebugMessage = false;
	}

	@Override
	public void initGui() {
		selectPanel = new LMM_GuiTextureSlot(this);
		selectPanel.registerScrollButtons(buttonList, 3, 4);
		buttonList.add(modeButton[0] = new GuiButton(100, width / 2 - 55, height - 55, 80, 20, "Texture"));
		buttonList.add(modeButton[1] = new GuiButton(101, width / 2 + 30, height - 55, 80, 20, "Armor"));
		buttonList.add(new GuiButton(200, width / 2 - 10, height - 30, 120, 20, "Select"));
		modeButton[0].enabled = false;
	}

	@Override
	public void keyTyped(char par1, int par2) {
		if (par2 == 1) {
			mc.displayGuiScreen(owner);
		}
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		mod_LMM_littleMaidMob.DebugMessage = lastDebug;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		selectPanel.drawScreen(par1, par2, par3);
		drawCenteredString(fontRenderer, StatCollector.translateToLocal(screenTitle), width / 2, 4, 0xffffff);
		
		super.drawScreen(par1, par2, par3);
		
		GL11.glPushMatrix();
		GL11.glEnable(EXTRescaleNormal.GL_RESCALE_NORMAL_EXT);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		RenderHelper.enableGUIStandardItemLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		MMM_TextureBox lbox = selectPanel.getSelectedBox();
		GL11.glTranslatef(width / 2 - 115F, height - 5F, 100F);
		GL11.glScalef(60F, -60F, 60F);
		selectPanel.maid.textureName = lbox.packegeName;
//		selectPanel.maid.maidContract = true;
		selectPanel.maid.renderYawOffset = -25F;
		selectPanel.maid.rotationYawHead = -10F;
		if (selectPanel.mode) {
			selectPanel.maid.textureModel0 = null;
			Map<Integer, String> lmap = lbox.armors.get("default");
			selectPanel.maid.textureArmor1[0] = selectPanel.maid.textureArmor1[1] = 
					selectPanel.maid.textureArmor1[2] = selectPanel.maid.textureArmor1[3] =
					(new StringBuilder()).append(lbox.textureDir[1])
					.append(lbox.packegeName.replace('.', '/'))
					.append(lmap.get(0x0040))
					.toString();
			selectPanel.maid.textureArmor2[0] = selectPanel.maid.textureArmor2[1] = 
					selectPanel.maid.textureArmor2[2] = selectPanel.maid.textureArmor2[3] =
					(new StringBuilder()).append(lbox.textureDir[1]).append(lbox.packegeName.replace('.', '/')).append(lmap.get(0x0050)).toString();
			selectPanel.maid.textureModel1 = lbox.models[1];
			selectPanel.maid.textureModel2 = lbox.models[2];
		} else {
			selectPanel.maid.maidColor = selectColor;
			LMM_Client.setTextureValue(selectPanel.maid);
		}
		RenderManager.instance.renderEntityWithPosYaw(selectPanel.maid, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		for (int li = 0; li < 16; li++) {
			if (lbox.hasColor(li)) {
				break;
			}
		}
		GL11.glDisable(EXTRescaleNormal.GL_RESCALE_NORMAL_EXT);
		GL11.glPopMatrix();
		
		
	}

	@Override
	public void actionPerformed(GuiButton par1GuiButton) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		switch (par1GuiButton.id) {
		case 200:
			if (toServer) {
				if (selectColor != selectPanel.color) {
<<<<<<< HEAD
					// Fî•ñ‚ÌÝ’è
//					theMaid.maidColor = selectPanel.color | 0x010000 | (selectColor << 8);
					// ƒT[ƒo[‚Öõ—¿‚ÌŽg—p‚ð’Ê’m
=======
					// è‰²æƒ…å ±ã®è¨­å®š
					theMaid.maidColor = selectPanel.color | 0x010000 | (selectColor << 8);
					// ã‚µãƒ¼ãƒãƒ¼ã¸æŸ“æ–™ã®ä½¿ç”¨ã‚’é€šçŸ¥
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
					byte ldata[] = new byte[2];
					ldata[0] = LMM_Statics.LMN_Server_DecDyePowder;
					ldata[1] = (byte)selectColor;
					LMM_Net.sendToServer(ldata);
				}
			}
			break;
		}
	}

<<<<<<< HEAD
=======
	@Override
	public void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		
	}

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
}
