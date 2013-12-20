package net.minecraft.src;

import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.entity.LMM_EntityModeBase;
import net.minecraft.entity.MMM_EntityDummy;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class LMM_EntityAIFindBlock extends EntityAIBase implements LMM_IEntityAI {

<<<<<<< HEAD
	protected boolean isEnable;
	protected LMM_EntityLittleMaid theMaid;
	protected LMM_EntityModeBase fmodeBase;
//	protected MovingObjectPosition theBlock;
//	protected int tileX;
//	protected int tileY;
//	protected int tileZ;
//	protected boolean isFind;
=======
	public boolean isEnable;
	public LMM_EntityLittleMaid theMaid;
	public LMM_EntityModeBase llmode;
	public MovingObjectPosition theBlock;
	public int tileX;
	public int tileY;
	public int tileZ;
//	public boolean isFind;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
	
	
	public LMM_EntityAIFindBlock(LMM_EntityLittleMaid pEntityLittleMaid) {
		theMaid = pEntityLittleMaid;
		isEnable = true;
//		theBlock = null;
		
		setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		fmodeBase = theMaid.getActiveModeClass();
//		LMM_EntityModeBase llmode = theMaid.getActiveModeClass();
//		if (!isEnable || theMaid.isWait() || theMaid.getActiveModeClass() == null || !theMaid.getActiveModeClass().isSearchBlock() || theMaid.getCurrentEquippedItem() == null) {
		if (!isEnable || theMaid.isMaidWait() || fmodeBase == null) {
			return false;
		}
		if (!fmodeBase.isSearchBlock()) {
			return fmodeBase.shouldBlock(theMaid.maidMode);
		}
		
<<<<<<< HEAD
		// É^Å[ÉQÉbÉgÇÉTÅ[É`
		int lx = MathHelper.floor_double(theMaid.posX);
		int ly = MathHelper.floor_double(theMaid.posY);
		int lz = MathHelper.floor_double(theMaid.posZ);
=======
		// „Çø„Éº„Ç≤„ÉÉ„Éà„Çí„Çµ„Éº„ÉÅ
		tileX = MathHelper.floor_double(theMaid.posX);
		tileY = MathHelper.floor_double(theMaid.posY);
		tileZ = MathHelper.floor_double(theMaid.posZ);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		int vt = MathHelper.floor_float(((theMaid.rotationYawHead * 4F) / 360F) + 2.5F) & 3;
		int xx = lx;
		int yy = ly;
		int zz = lz;
		
		// TODO:Dummy
		MMM_EntityDummy.clearDummyEntity(theMaid);
		boolean flagdammy = false;
		
		// CWÊñπÂêë„Å´Ê§úÁ¥¢È†òÂüü„ÇíÂ∫É„Åí„Çã 
		for (int d = 0; d < 4; d++) {
			for (int a = 0; a < 18; a += 2) {
				int del = a / 2;
				if (vt == 0) {
					xx = lx - del;
					zz = lz - del;
				} 
				else if (vt == 1) { 
					xx = lx + del;
					zz = lz - del;
				} 
				else if (vt == 2) { 
					xx = lx + del;
					zz = lz + del;
				} 
				else if (vt == 3) { 
					xx = lx - del;
					zz = lz + del;
				}
				// TODO:Dummay
				if (!flagdammy) {
					MMM_EntityDummy.setDummyEntity(theMaid, 0x00ff4f4f, xx, ly, zz);
					flagdammy = true;
				}
				int b = 0;
				do {
					for (int c = 0; c < 3; c++) {
						yy = ly + (c == 2 ? -1 : c);
						if (fmodeBase.checkBlock(theMaid.maidMode, xx, yy, zz)) {
							if (fmodeBase.outrangeBlock(theMaid.maidMode, xx, yy, zz)) {
								theMaid.setTilePos(xx, yy, zz);
								// TODO:Dummay
								MMM_EntityDummy.setDummyEntity(theMaid, 0x004fff4f, xx, yy, zz);
								flagdammy = true;
								return true;
							}
						}
					}
					// TODO:Dummay
					if (!flagdammy) {
						MMM_EntityDummy.setDummyEntity(theMaid, 0x00ffffcf, xx, ly, zz);
						flagdammy = true;
					}
					// TODO:dammy
					flagdammy = false;
					
					if (vt == 0) {
						xx++;
					} 
					else if (vt == 1) { 
						zz++;
					} 
					else if (vt == 2) { 
						xx--;
					} 
					else if (vt == 3) { 
						zz--;
					}
					
				} while(++b < a);
			}
			vt = (vt + 1) & 3;
		}
		if (fmodeBase.overlooksBlock(theMaid.maidMode)) {
			TileEntity ltile = theMaid.maidTileEntity;
			if (ltile != null) {
				lx = ltile.xCoord;
				ly = ltile.yCoord;
				lz = ltile.zCoord;
				// TODO:Dummay
				MMM_EntityDummy.setDummyEntity(theMaid, 0x004fff4f, lx, ly, lz);
				flagdammy = true;
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean continueExecuting() {
<<<<<<< HEAD
		fmodeBase.updateBlock();
		// à⁄ìÆíÜÇÕåpë±
=======
		// ÁßªÂãï‰∏≠„ÅØÁ∂ôÁ∂ö
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		if (!theMaid.getNavigator().noPath()) return true;
		
		double ld = theMaid.getDistanceTilePos();
		if (ld > 100.0D) {
			// Á¥¢ÊïµÁØÑÂõ≤Â§ñ
			theMaid.getActiveModeClass().farrangeBlock();
			return false;
		} else if (ld > 5.0D) {
<<<<<<< HEAD
			// éÀíˆãóó£äO
			return theMaid.getActiveModeClass().outrangeBlock(theMaid.maidMode);
		} else {
			// éÀíˆãóó£
			return theMaid.getActiveModeClass().executeBlock(theMaid.maidMode);
=======
			// Â∞ÑÁ®ãË∑ùÈõ¢Â§ñ
			return theMaid.getActiveModeClass().outrangeBlock(theMaid.getMaidModeInt(), tileX, tileY, tileZ);
		} else {
			// Â∞ÑÁ®ãË∑ùÈõ¢
			return theMaid.getActiveModeClass().executeBlock(theMaid.getMaidModeInt(), tileX, tileY, tileZ);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		}
	}

	@Override
	public void startExecuting() {
		fmodeBase.startBlock(theMaid.maidMode);
	}

	@Override
	public void resetTask() {
		fmodeBase.resetBlock(theMaid.maidMode);
	}

	@Override
	public void updateTask() {
<<<<<<< HEAD
		// É^Å[ÉQÉbÉgÇå©Ç¬ÇØÇƒÇ¢ÇÈ
		theMaid.looksTilePos();
=======
		// „Çø„Éº„Ç≤„ÉÉ„Éà„ÇíË¶ã„Å§„Åë„Å¶„ÅÑ„Çã
		theMaid.getLookHelper().setLookPosition(tileX + 0.5D, tileY + 0.5D, tileZ + 0.5D, 10F, theMaid.getVerticalFaceSpeed());
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
	}


	@Override
	public void setEnable(boolean pFlag) {
		isEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return isEnable;
	}

}
