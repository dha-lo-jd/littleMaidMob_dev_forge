<<<<<<< HEAD
package net.minecraft.src;

public class LMM_EntityAITracerMove extends EntityAIBase implements LMM_IEntityAI {
	
	protected LMM_EntityLittleMaid theMaid; 
	protected World world;
	protected boolean isEnable;
	protected int tileX;
	protected int tileY;
	protected int tileZ;


	public LMM_EntityAITracerMove(LMM_EntityLittleMaid pEntityLittleMaid) {
		theMaid = pEntityLittleMaid;
		world = pEntityLittleMaid.worldObj;
		isEnable = false;
		
		setMutexBits(1);
	}

	@Override
	public void setEnable(boolean pFlag) {
		isEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return isEnable;
	}

	@Override
	public boolean shouldExecute() {
		return isEnable && !theMaid.isMaidWaitEx() &&  theMaid.getNavigator().noPath();
	}

	@Override
	public boolean continueExecuting() {
		return !theMaid.getNavigator().noPath();
	}

	@Override
	public void startExecuting() {
		// ƒ‹[ƒgô’è
		// ƒ^[ƒQƒbƒg‚ğƒT[ƒ`
		int ox = MathHelper.floor_double(theMaid.posX);
		int oy = MathHelper.floor_double(theMaid.posY);
		int oz = MathHelper.floor_double(theMaid.posZ);
		int vt = MathHelper.floor_float(((theMaid.rotationYawHead * 4F) / 360F) + 2.5F) & 3;
		int xx = ox;
		int yy = oy;
		int zz = oz;
		double lrange = Double.MAX_VALUE;
		
		// TODO:Dummy
		MMM_EntityDummy.clearDummyEntity(theMaid);
		boolean flagdammy = false;
		
		// CW•ûŒü‚ÉŒŸõ—Ìˆæ‚ğL‚°‚é 
		for (int d = 0; d < 4; d++) {
			for (int a = 2; a < 14; a += 2) {
				int del = a / 2;
				if (vt == 0) {
					xx = ox - del;
					zz = oz - del;
				}
				else if (vt == 1) { 
					xx = ox + del;
					zz = oz - del;
				}
				else if (vt == 2) { 
					xx = ox + del;
					zz = oz + del;
				}
				else if (vt == 3) { 
					xx = ox - del;
					zz = oz + del;
				}
				// TODO:Dummay
				if (!flagdammy) {
					MMM_EntityDummy.setDummyEntity(theMaid, 0x00ff4f4f, xx, oy, zz);
					flagdammy = true;
				}
				int b = 0;
				do {
					for (int c = 0; c < 3; c++) {
						yy = oy + (c == 2 ? -1 : c);
						if (checkBlock(xx, yy, zz)) {
							// Å‚à‹ß‚¢ƒ|ƒCƒ“ƒg‚Ì”»’è
							double lr = theMaid.getDistanceSq(xx, yy, zz);
							if (lr < lrange) {
								if (doFindBlock(xx, yy, zz)) {
									lrange = lr;
									tileX = xx;
									tileY = yy;
									tileZ = zz;
									theMaid.func_110171_b(xx, yy, zz, 16);
//									theMaid.setHomeArea(xx, yy, zz, 16);
									// TODO:Dummay
									MMM_EntityDummy.setDummyEntity(theMaid, 0x004f4fff, xx, yy, zz);
									flagdammy = true;
									return;
								}
							}
							// TODO:Dummay
							MMM_EntityDummy.setDummyEntity(theMaid, 0x004fff4f, xx, yy, zz);
							flagdammy = true;
						}
					}
					// TODO:Dummay
					if (!flagdammy) {
						MMM_EntityDummy.setDummyEntity(theMaid, 0x00ffffcf, xx, oy, zz);
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
	}

	/**
	 * w’èÀ•W‚ÌƒuƒƒbƒN‚Í’T‚µ‚Ä‚¢‚é‚à‚Ì‚©H
	 */
	protected boolean checkBlock(int px, int py, int pz) {
		return world.getBlockPowerInput(px, py, pz) > 0 && (world.getBlockMaterial(px, py + 1, pz) == Material.air);
	}

	/**
	 * Œ©‚Â‚¯‚½ƒuƒƒbƒN‚É‘Î‚·‚é“®ìB
	 * true‚ğ•Ô‚·‚Æƒ‹[ƒvI—¹B
	 */
	protected boolean doFindBlock(int px, int py, int pz) {
		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
//		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
	}

}
=======
package net.minecraft.src;

import net.minecraft.block.material.Material;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.entity.MMM_EntityDummy;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LMM_EntityAITracerMove extends EntityAIBase implements LMM_IEntityAI {
	
	public LMM_EntityLittleMaid theMaid; 
	public World world;
	public boolean isEnable;
	public int tileX;
	public int tileY;
	public int tileZ;


	public LMM_EntityAITracerMove(LMM_EntityLittleMaid pEntityLittleMaid) {
		theMaid = pEntityLittleMaid;
		world = pEntityLittleMaid.worldObj;
		isEnable = false;
		
		setMutexBits(1);
	}

	@Override
	public void setEnable(boolean pFlag) {
		isEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return isEnable;
	}

	@Override
	public boolean shouldExecute() {
		return isEnable && !theMaid.isMaidWaitEx() &&  theMaid.getNavigator().noPath();
	}

	@Override
	public boolean continueExecuting() {
		return !theMaid.getNavigator().noPath();
	}

	@Override
	public void startExecuting() {
		// ãƒ«ãƒ¼ãƒˆç­–å®š
		// ã‚¿ãƒ¼ã‚²ãƒƒãƒˆã‚’ã‚µãƒ¼ãƒ
		int ox = MathHelper.floor_double(theMaid.posX);
		int oy = MathHelper.floor_double(theMaid.posY);
		int oz = MathHelper.floor_double(theMaid.posZ);
		int vt = MathHelper.floor_float(((theMaid.rotationYawHead * 4F) / 360F) + 2.5F) & 3;
		int xx = ox;
		int yy = oy;
		int zz = oz;
		double lrange = Double.MAX_VALUE;
		
		// TODO:Dummy
		MMM_EntityDummy.clearDummyEntity(theMaid);
		boolean flagdammy = false;
		
		// CWæ–¹å‘ã«æ¤œç´¢é ˜åŸŸã‚’åºƒã’ã‚‹ 
		for (int d = 0; d < 4; d++) {
			for (int a = 2; a < 14; a += 2) {
				int del = a / 2;
				if (vt == 0) {
					xx = ox - del;
					zz = oz - del;
				}
				else if (vt == 1) { 
					xx = ox + del;
					zz = oz - del;
				}
				else if (vt == 2) { 
					xx = ox + del;
					zz = oz + del;
				}
				else if (vt == 3) { 
					xx = ox - del;
					zz = oz + del;
				}
				// TODO:Dummay
				if (!flagdammy) {
					MMM_EntityDummy.setDummyEntity(theMaid, 0x00ff4f4f, xx, oy, zz);
					flagdammy = true;
				}
				int b = 0;
				do {
					for (int c = 0; c < 3; c++) {
						yy = oy + (c == 2 ? -1 : c);
						if (checkBlock(xx, yy, zz)) {
							// æœ€ã‚‚è¿‘ã„ãƒã‚¤ãƒ³ãƒˆã®åˆ¤å®š
							double lr = theMaid.getDistanceSq(xx, yy, zz);
							if (lr < lrange) {
								if (doFindBlock(xx, yy, zz)) {
									lrange = lr;
									tileX = xx;
									tileY = yy;
									tileZ = zz;
									theMaid.setHomeArea(xx, yy, zz, 16);
									// TODO:Dummay
									MMM_EntityDummy.setDummyEntity(theMaid, 0x004f4fff, xx, yy, zz);
									flagdammy = true;
									return;
								}
							}
							// TODO:Dummay
							MMM_EntityDummy.setDummyEntity(theMaid, 0x004fff4f, xx, yy, zz);
							flagdammy = true;
						}
					}
					// TODO:Dummay
					if (!flagdammy) {
						MMM_EntityDummy.setDummyEntity(theMaid, 0x00ffffcf, xx, oy, zz);
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
	}

	/**
	 * æŒ‡å®šåº§æ¨™ã®ãƒ–ãƒ­ãƒƒã‚¯ã¯æ¢ã—ã¦ã„ã‚‹ã‚‚ã®ã‹ï¼Ÿ
	 */
	public boolean checkBlock(int px, int py, int pz) {
		return world.getBlockPowerInput(px, py, pz) > 0 && (world.getBlockMaterial(px, py + 1, pz) == Material.air);
	}

	/**
	 * è¦‹ã¤ã‘ãŸãƒ–ãƒ­ãƒƒã‚¯ã«å¯¾ã™ã‚‹å‹•ä½œã€‚
	 * trueã‚’è¿”ã™ã¨ãƒ«ãƒ¼ãƒ—çµ‚äº†ã€‚
	 */
	public boolean doFindBlock(int px, int py, int pz) {
		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
//		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
	}

}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
