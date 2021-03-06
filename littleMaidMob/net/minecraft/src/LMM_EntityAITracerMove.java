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
		// ルート策定
		// ターゲットをサーチ
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
		
		// CW方向に検索領域を広げる 
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
							// 最も近いポイントの判定
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
	 * 指定座標のブロックは探しているものか？
	 */
	protected boolean checkBlock(int px, int py, int pz) {
		return world.getBlockPowerInput(px, py, pz) > 0 && (world.getBlockMaterial(px, py + 1, pz) == Material.air);
	}

	/**
	 * 見つけたブロックに対する動作。
	 * trueを返すとループ終了。
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
		// 繝ｫ繝ｼ繝育ｭ門ｮ�
		// 繧ｿ繝ｼ繧ｲ繝�繝医ｒ繧ｵ繝ｼ繝�
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
		
		// CW譁ｹ蜷代↓讀懃ｴ｢鬆伜沺繧貞ｺ�縺偵ｋ 
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
							// 譛�繧りｿ代＞繝昴う繝ｳ繝医�ｮ蛻､螳�
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
	 * 謖�螳壼ｺｧ讓吶�ｮ繝悶Ο繝�繧ｯ縺ｯ謗｢縺励※縺�繧九ｂ縺ｮ縺具ｼ�
	 */
	public boolean checkBlock(int px, int py, int pz) {
		return world.getBlockPowerInput(px, py, pz) > 0 && (world.getBlockMaterial(px, py + 1, pz) == Material.air);
	}

	/**
	 * 隕九▽縺代◆繝悶Ο繝�繧ｯ縺ｫ蟇ｾ縺吶ｋ蜍穂ｽ懊��
	 * true繧定ｿ斐☆縺ｨ繝ｫ繝ｼ繝礼ｵゆｺ�縲�
	 */
	public boolean doFindBlock(int px, int py, int pz) {
		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
//		return theMaid.getNavigator().tryMoveToXYZ(px, py, pz, theMaid.getAIMoveSpeed());
	}

}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
