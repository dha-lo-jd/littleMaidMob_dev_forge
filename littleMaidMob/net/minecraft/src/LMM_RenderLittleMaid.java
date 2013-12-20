package net.minecraft.src;

import net.minecraft.client.model.MMM_ModelDuo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;

import org.lwjgl.opengl.GL11;

public class LMM_RenderLittleMaid extends MMM_RenderModelMulti {

	public MMM_ModelDuo modelFATT;
	// Feilds
<<<<<<< HEAD


	// Method
	public LMM_RenderLittleMaid(float f) {
		super(f);
	}

	@Override
	public void setModelValues(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9, MMM_IModelCaps pEntityCaps) {
		LMM_EntityLittleMaid lmaid = (LMM_EntityLittleMaid)par1EntityLiving;
		
=======
	public MMM_ModelDuo modelMain;

	// Method
	public LMM_RenderLittleMaid(float f) {
		super(null, f);
		modelFATT = new MMM_ModelDuo(this);
		modelFATT.isModelAlphablend = mod_LMM_littleMaidMob.AlphaBlend;
		modelMain = new MMM_ModelDuo(this);
		modelMain.isModelAlphablend = mod_LMM_littleMaidMob.AlphaBlend;
		modelMain.capsLink = modelFATT;
		mainModel = modelMain;
		setRenderPassModel(modelFATT);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		doRenderLitlleMaid((LMM_EntityLittleMaid) entity, d, d1, d2, f, f1);
	}

	public void doRenderLitlleMaid(LMM_EntityLittleMaid plittleMaid, double px, double py, double pz, float f, float f1) {
		// ã„ãã¤ã‹é‡è¤‡ã—ã¦ã‚‹ã®ã§ã‚ã¨ã§ç¢ºèª
		// å§¿å‹¢ã«ã‚ˆã‚‹é«˜ã•èª¿æ•´
		double lay = py;
		if (plittleMaid.isSneaking()) {
			// ã—ã‚ƒãŒã¿
			lay -= 0.06D;
		} else if (plittleMaid.isRiding() && plittleMaid.ridingEntity == null) {
			// åº§ã‚Šè¾¼ã¿
			lay -= 0.25D;
		}

		//		plittleMaid.textureModel0.render = this;
		modelMain.modelArmorInner = plittleMaid.textureModel0;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		modelMain.setRender(this);
		modelMain.setEntityCaps(pEntityCaps);
		modelMain.showAllParts();
		modelMain.isAlphablend = true;
<<<<<<< HEAD
		modelFATT.isAlphablend = true;
		
		modelMain.setCapsValue(MMM_IModelCaps.caps_heldItemLeft, (Integer)0);
		modelMain.setCapsValue(MMM_IModelCaps.caps_heldItemRight, (Integer)0);
//		modelMain.setCapsValue(MMM_IModelCaps.caps_onGround, renderSwingProgress(lmaid, par9));
		modelMain.setCapsValue(MMM_IModelCaps.caps_onGround,
				lmaid.mstatSwingStatus[0].getSwingProgress(par9),
				lmaid.mstatSwingStatus[1].getSwingProgress(par9));
		modelMain.setCapsValue(MMM_IModelCaps.caps_isRiding, lmaid.isRiding());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isSneak, lmaid.isSneaking());
		modelMain.setCapsValue(MMM_IModelCaps.caps_aimedBow, lmaid.isAimebow());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isWait, lmaid.isMaidWait());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isChild, lmaid.isChild());
		modelMain.setCapsValue(MMM_IModelCaps.caps_entityIdFactor, lmaid.entityIdFactor);
		modelMain.setCapsValue(MMM_IModelCaps.caps_ticksExisted, lmaid.ticksExisted);
		modelMain.setCapsValue(MMM_IModelCaps.caps_dominantArm, lmaid.maidDominantArm);
		// ‚¾‚ª–³ˆÓ–¡‚¾
//		plittleMaid.textureModel0.isChild = plittleMaid.textureModel1.isChild = plittleMaid.textureModel2.isChild = plittleMaid.isChild();
	}

	public void doRenderLitlleMaid(LMM_EntityLittleMaid plittleMaid, double px, double py, double pz, float f, float f1) {
		// ‚¢‚­‚Â‚©d•¡‚µ‚Ä‚é‚Ì‚Å‚ ‚Æ‚ÅŠm”F
		// Žp¨‚É‚æ‚é‚‚³’²®
		
		if (plittleMaid.worldObj instanceof WorldServer) {
			// RSHUD-ACV—p
			MMM_TextureBox ltbox0, ltbox1;
			ltbox0 = MMM_TextureManager.instance.getTextureBox(plittleMaid.textureBox[0]);
			ltbox1 = MMM_TextureManager.instance.getTextureBox(plittleMaid.textureBox[1]);
			modelMain.model = ltbox0.models[0];
			modelFATT.modelInner = ltbox1.models[1];
			modelFATT.modelOuter = ltbox1.models[2];
			plittleMaid.textures[0][0] = ltbox0.getTextureName(plittleMaid.maidColor);
			plittleMaid.textures[0][1] = ltbox0.getTextureName(plittleMaid.maidColor + MMM_TextureManager.tx_eye);
			plittleMaid.textures[1][0] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor1, plittleMaid.getCurrentItemOrArmor(1));
			plittleMaid.textures[1][1] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor1, plittleMaid.getCurrentItemOrArmor(2));
			plittleMaid.textures[1][2] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor1, plittleMaid.getCurrentItemOrArmor(3));
			plittleMaid.textures[1][3] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor1, plittleMaid.getCurrentItemOrArmor(4));
			plittleMaid.textures[2][0] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor2, plittleMaid.getCurrentItemOrArmor(1));
			plittleMaid.textures[2][1] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor2, plittleMaid.getCurrentItemOrArmor(2));
			plittleMaid.textures[2][2] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor2, plittleMaid.getCurrentItemOrArmor(3));
			plittleMaid.textures[2][3] = ltbox1.getArmorTextureName(MMM_TextureManager.tx_armor2, plittleMaid.getCurrentItemOrArmor(4));
			modelMain.textures = plittleMaid.textures[0];
			modelFATT.textureInner = plittleMaid.textures[1];
			modelFATT.textureOuter = plittleMaid.textures[2];
=======
		modelFATT.modelArmorInner = plittleMaid.textureModel1;
		modelFATT.modelArmorOuter = plittleMaid.textureModel2;
		modelFATT.textureOuter = plittleMaid.textureArmor2;
		modelFATT.textureInner = plittleMaid.textureArmor1;
		//		modelFATT.setRender(this);
		modelFATT.isAlphablend = true;
		//		if (modelMain.modelArmorInner == null) {
		//			modelMain.modelArmorInner = MMM_TextureManager.defaultModel[0];
		//		}
		modelMain.setEntityCaps(plittleMaid.maidCaps);

		modelMain.setCapsValue(MMM_IModelCaps.caps_heldItemLeft, (Integer) 0);
		modelMain.setCapsValue(MMM_IModelCaps.caps_heldItemRight, (Integer) 0);
		modelMain.setCapsValue(MMM_IModelCaps.caps_onGround, renderSwingProgress(plittleMaid, f1));
		modelMain.setCapsValue(MMM_IModelCaps.caps_isRiding, plittleMaid.isRiding());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isSneak, plittleMaid.isSneaking());
		modelMain.setCapsValue(MMM_IModelCaps.caps_aimedBow, plittleMaid.isAimebow());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isWait, plittleMaid.isMaidWait());
		modelMain.setCapsValue(MMM_IModelCaps.caps_isChild, plittleMaid.isChild());
		modelMain.setCapsValue(MMM_IModelCaps.caps_entityIdFactor, plittleMaid.entityIdFactor);
		modelMain.setCapsValue(MMM_IModelCaps.caps_ticksExisted, plittleMaid.ticksExisted);
		// ã ãŒç„¡æ„å‘³ã 
		//		plittleMaid.textureModel0.isChild = plittleMaid.textureModel1.isChild = plittleMaid.textureModel2.isChild = plittleMaid.isChild();

		if (plittleMaid.worldObj instanceof WorldServer) {
			Entity le = MMM_Helper.mc.theWorld.getEntityByID(plittleMaid.entityId);
			if (le instanceof LMM_EntityLittleMaid) {
				LMM_EntityLittleMaid lel = (LMM_EntityLittleMaid) le;
				modelMain.modelArmorInner = lel.textureModel0;
				modelFATT.modelArmorInner = lel.textureModel1;
				modelFATT.modelArmorOuter = lel.textureModel2;
				modelFATT.textureOuter = lel.textureArmor2;
				modelFATT.textureInner = lel.textureArmor1;
				plittleMaid.texture = lel.texture;
			}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		} else {
			modelMain.model = ((MMM_TextureBox)plittleMaid.textureBox[0]).models[0];
			modelMain.textures = plittleMaid.textures[0];
			modelFATT.modelInner = ((MMM_TextureBox)plittleMaid.textureBox[1]).models[1];
			modelFATT.modelOuter = ((MMM_TextureBox)plittleMaid.textureBox[1]).models[2];
			modelFATT.textureInner = plittleMaid.textures[1];
			modelFATT.textureOuter = plittleMaid.textures[2];
		}
<<<<<<< HEAD
		
//		doRenderLiving(plittleMaid, px, py, pz, f, f1);
		renderModelMulti(plittleMaid, px, py, pz, f, f1, plittleMaid.maidCaps);
		
		
		// ‚Ð‚à
		if(plittleMaid.mstatgotcha != null && plittleMaid.mstatgotcha instanceof EntityLiving) {
			EntityLiving lel = (EntityLiving)plittleMaid.mstatgotcha;
=======
		doRenderLiving(plittleMaid, px, lay, pz, f, f1);

		// ã²ã‚‚
		if (plittleMaid.mstatgotcha != null && plittleMaid.mstatgotcha instanceof EntityLiving) {
			EntityLiving lel = (EntityLiving) plittleMaid.mstatgotcha;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			py -= 0.5D;
			Tessellator tessellator = Tessellator.instance;
			float f9 = ((lel.prevRotationYaw + (lel.rotationYaw - lel.prevRotationYaw) * f1 * 0.5F) * 3.141593F) / 180F;
			float f3 = ((lel.prevRotationPitch + (lel.rotationPitch - lel.prevRotationPitch) * f1 * 0.5F) * 3.141593F) / 180F;
			double d3 = MathHelper.sin(f9);
			double d5 = MathHelper.cos(f9);
			float f11 = lel.getSwingProgress(f1);
			float f12 = MathHelper.sin(MathHelper.sqrt_float(f11) * 3.141593F);
			Vec3 vec3d = Vec3.createVectorHelper(-0.5D, 0.029999999999999999D, 0.55D);
			vec3d.rotateAroundX((-(lel.prevRotationPitch + (lel.rotationPitch - lel.prevRotationPitch) * f1) * 3.141593F) / 180F);
			vec3d.rotateAroundY((-(lel.prevRotationYaw + (lel.rotationYaw - lel.prevRotationYaw) * f1) * 3.141593F) / 180F);
			vec3d.rotateAroundY(f12 * 0.5F);
			vec3d.rotateAroundX(-f12 * 0.7F);
			double d7 = lel.prevPosX + (lel.posX - lel.prevPosX) * f1 + vec3d.xCoord;
			double d8 = lel.prevPosY + (lel.posY - lel.prevPosY) * f1 + vec3d.yCoord;
			double d9 = lel.prevPosZ + (lel.posZ - lel.prevPosZ) * f1 + vec3d.zCoord;
			if (renderManager.options.thirdPersonView > 0) {
				float f4 = ((lel.prevRenderYawOffset + (lel.renderYawOffset - lel.prevRenderYawOffset) * f1) * 3.141593F) / 180F;
				double d11 = MathHelper.sin(f4);
				double d13 = MathHelper.cos(f4);
				d7 = (lel.prevPosX + (lel.posX - lel.prevPosX) * f1) - d13 * 0.34999999999999998D - d11
						* 0.54999999999999998D;
				d8 = (lel.prevPosY + (lel.posY - lel.prevPosY) * f1) - 0.45000000000000001D;
				d9 = ((lel.prevPosZ + (lel.posZ - lel.prevPosZ) * f1) - d11 * 0.34999999999999998D) + d13
						* 0.54999999999999998D;
			}
<<<<<<< HEAD
			double d10 = plittleMaid.prevPosX + (plittleMaid.posX - plittleMaid.prevPosX) * (double)f1;
			double d12 = plittleMaid.prevPosY + (plittleMaid.posY - plittleMaid.prevPosY) * (double)f1 + 0.25D - 0.5D;//+ 0.75D;
			double d14 = plittleMaid.prevPosZ + (plittleMaid.posZ - plittleMaid.prevPosZ) * (double)f1;
			double d15 = (float)(d7 - d10);
			double d16 = (float)(d8 - d12);
			double d17 = (float)(d9 - d14);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
=======
			double d10 = plittleMaid.prevPosX + (plittleMaid.posX - plittleMaid.prevPosX) * f1;
			double d12 = plittleMaid.prevPosY + (plittleMaid.posY - plittleMaid.prevPosY) * f1 + 0.25D - 0.5D;//+ 0.75D;
			double d14 = plittleMaid.prevPosZ + (plittleMaid.posZ - plittleMaid.prevPosZ) * f1;
			double d15 = (float) (d7 - d10);
			double d16 = (float) (d8 - d12);
			double d17 = (float) (d9 - d14);
			GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
			GL11.glDisable(2896 /*GL_LIGHTING*/);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(0);
			int i = 16;
			for (int j = 0; j <= i; j++)
			{
				float f5 = (float) j / (float) i;
				tessellator.addVertex(px + d15 * f5, py + d16 * (f5 * f5 + f5) * 0.5D
						+ (((float) i - (float) j) / (i * 0.75F) + 0.125F), pz + d17 * f5);
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}

	@Override
	public int getColorMultiplier(EntityLiving par1EntityLiving, float par2, float par3) {
		return ((LMM_EntityLittleMaid) par1EntityLiving).colorMultiplier(par2, par3);
	}

	@Override
	public void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6) {
		super.passSpecialRender(par1EntityLiving, par2, par4, par6);

		if (par1EntityLiving instanceof LMM_EntityLittleMaid) {
			LMM_EntityLittleMaid llmm = (LMM_EntityLittleMaid) par1EntityLiving;

			// è¿½åŠ åˆ†
			for (int li = 0; li < llmm.maidEntityModeList.size(); li++) {
				llmm.maidEntityModeList.get(li).showSpecial(this, par2, par4, par6);
			}
		}
	}

	@Override
	public void preRenderCallback(EntityLiving entityliving, float f) {
		Float lscale = (Float) modelMain.getCapsValue(MMM_IModelCaps.caps_ScaleFactor);
		if (lscale != null) {
			GL11.glScalef(lscale, lscale, lscale);
		}
	}

	@Override
	public void renderEquippedItems(EntityLiving entityliving, float f) {
		renderSpecials((LMM_EntityLittleMaid) entityliving, f);
	}

	@Override
<<<<<<< HEAD
	protected void renderModel(EntityLivingBase par1EntityLiving, float par2,
			float par3, float par4, float par5, float par6, float par7) {
		if (!par1EntityLiving.isInvisible()) {
			
//			loadDownloadableImageTexture(par1EntityLiving.skinUrl, par1EntityLiving.getTexture());
=======
	public void renderModel(EntityLiving par1EntityLiving, float par2,
			float par3, float par4, float par5, float par6, float par7) {
		if (!par1EntityLiving.getHasActivePotion()) {

			loadDownloadableImageTexture(par1EntityLiving.skinUrl, par1EntityLiving.getTexture());
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			modelMain.setArmorRendering(true);
		} else {
			modelMain.setArmorRendering(false);
		}
<<<<<<< HEAD
		// ƒAƒCƒeƒ€‚ÌƒŒƒ“ƒ_ƒŠƒ“ƒOˆÊ’u‚ðŠl“¾‚·‚é‚½‚ßrender‚ðŒÄ‚Ô•K—v‚ª‚ ‚é
		mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
	}

	@Override
	protected void passSpecialRender(EntityLivingBase par1EntityLiving, double par2, double par4, double par6) {
		super.passSpecialRender(par1EntityLiving, par2, par4, par6);
		
		LMM_EntityLittleMaid llmm = (LMM_EntityLittleMaid)par1EntityLiving;
		// ’Ç‰Á•ª
		for (int li = 0; li < llmm.maidEntityModeList.size(); li++) {
			llmm.maidEntityModeList.get(li).showSpecial(this, par2, par4, par6);
=======
		// ã‚¢ã‚¤ãƒ†ãƒ ã®ãƒ¬ãƒ³ãƒ€ãƒªãƒ³ã‚°ä½ç½®ã‚’ç²å¾—ã™ã‚‹ãŸã‚renderã‚’å‘¼ã¶å¿…è¦ãŒã‚ã‚‹
		mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
	}

	public void renderSpecials(LMM_EntityLittleMaid entitylittlemaid, float f) {
		// ãƒãƒ¼ãƒ‰ãƒã‚¤ãƒ³ãƒˆã®æç”»
		modelMain.renderItems(entitylittlemaid, this);
		renderArrowsStuckInEntity(entitylittlemaid, f);
	}

	public int setArmorModelEx(LMM_EntityLittleMaid entitylmaid, int i, float f) {
		// ã‚¢ãƒ¼ãƒžãƒ¼ã®è¡¨ç¤ºè¨­å®š
		modelFATT.renderParts = i;
		ItemStack is = entitylmaid.maidInventory.armorItemInSlot(i);
		if (is != null && is.stackSize > 0) {
			//			mod_littleMaidMob.Debug(String.format("en:%d-%d", i, ret));
			modelFATT.showArmorParts(i);
			return is.isItemEnchanted() ? 15 : 1;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		}

		return -1;
	}

	@Override
<<<<<<< HEAD
	protected int getColorMultiplier(EntityLivingBase par1EntityLiving, float par2, float par3) {
		return ((LMM_EntityLittleMaid)par1EntityLiving).colorMultiplier(par2, par3);
=======
	public int shouldRenderPass(EntityLiving entityliving, int i, float f) {
		//littlemaidEX
		return setArmorModelEx((LMM_EntityLittleMaid) entityliving, i, f);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
	}

}
