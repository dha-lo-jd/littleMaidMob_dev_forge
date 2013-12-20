package net.minecraft.entity;

import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.LMM_InventoryLittleMaid;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class LMM_EntityMode_Cooking extends LMM_EntityModeBlockBase {

	public static final int mmode_Cooking = 0x0021;


	public LMM_EntityMode_Cooking(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
	}

	@Override
	public int priority() {
		return 6000;
	}

	@Override
	public void init() {
		ModLoader.addLocalization("littleMaidMob.mode.Cooking", "Cooking");
		ModLoader.addLocalization("littleMaidMob.mode.T-Cooking", "T-Cooking");
		ModLoader.addLocalization("littleMaidMob.mode.F-Cooking", "F-Cooking");
		ModLoader.addLocalization("littleMaidMob.mode.F-Cooking", "D-Cooking");
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Cooking:0x0021
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = new EntityAITasks(owner.aiProfiler);
		
		owner.addMaidMode(ltasks, "Cooking", mmode_Cooking);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (owner.maidInventory.isItemBurned(0)) {
				owner.setMaidMode("Cooking");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Cooking :
			owner.setBloodsuck(false);
			owner.aiJumpTo.setEnable(false);
			owner.aiFollow.setEnable(false);
			owner.aiAvoidPlayer.setEnable(false);
			owner.aiAttack.setEnable(false);
			owner.aiShooting.setEnable(false);
			return true;
		}
		
		return false;
	}

	@Override
	public int getNextEquipItem(int pMode) {
		int li;
		ItemStack litemstack;
		
		// „É¢„Éº„Éâ„Å´Âøú„Åò„ÅüË≠òÂà•Âà§ÂÆö„ÄÅÈÄüÂ∫¶ÂÑ™ÂÖà
		switch (pMode) {
		case mmode_Cooking :
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
				// Ë™øÁêÜ
				if (owner.maidInventory.isItemBurned(li)) {
					return li;
				}
			}
			break;
		}
		
		return -1;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return LMM_InventoryLittleMaid.isItemBurned(pItemStack) || LMM_InventoryLittleMaid.isItemSmelting(pItemStack);
	}

	@Override
	public boolean isSearchBlock() {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
		if (!super.isSearchBlock()) return false;
		
		// îRèƒÉAÉCÉeÉÄÇéùÇ¡ÇƒÇ¢ÇÈÅH
=======
		// ÁáÉÁÑº„Ç¢„Ç§„ÉÜ„É†„ÇíÊåÅ„Å£„Å¶„ÅÑ„ÇãÔºü
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
		if (owner.getCurrentEquippedItem() != null && owner.maidInventory.getSmeltingItem() > -1) {
			fDistance = Double.MAX_VALUE;
			owner.clearTilePos();
			owner.setSneaking(false);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return owner.maidTileEntity instanceof TileEntityFurnace &&
				(((TileEntityFurnace)owner.maidTileEntity).isBurning() ||
				owner.maidInventory.isItemBurned(owner.getCurrentEquippedItem()));
	}

	@Override
	public boolean checkBlock(int pMode, int px, int py, int pz) {
		TileEntity ltile = owner.worldObj.getBlockTileEntity(px, py, pz);
		if (!(ltile instanceof TileEntityFurnace)) {
			return false;
		}
		
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
		// ê¢äEÇÃÉÅÉCÉhÇ©ÇÁ
		if (checkWorldMaid(ltile)) return false;
		// égópÇµÇƒÇ¢ÇΩ‚}Ç»ÇÁÇªÇ±Ç≈èIóπ
		if (owner.isUsingTile(ltile)) return true;
=======
		// ‰∏ñÁïå„ÅÆ„É°„Ç§„Éâ„Åã„Çâ
		for (Object lo : owner.worldObj.getLoadedEntityList()) {
			if (lo == owner) continue;
			if (lo instanceof LMM_EntityLittleMaid) {
				LMM_EntityLittleMaid lem = (LMM_EntityLittleMaid)lo;
				if (lem.isUsingTile(ltile)) {
					// Ë™∞„Åã„Åå‰ΩøÁî®‰∏≠
					return false;
				}
				if (myTile != null && lem.isUsingTile(myTile)) {
					// ÊâãÊåÅ„Å°„ÇíË™∞„Åã„Åå‰Ωø„Å£„Å¶„Çã„Å™„Çâ„ÇØ„É™„Ç¢
					myTile = null;
				}
			}
		}
		if (myTile != null) {
			// ‰ΩøÁî®„Åó„Å¶„ÅÑ„ÅüÁ´à„Å™„Çâ„Åù„Åì„ÅßÁµÇ‰∫Ü
			return myTile == ltile;
		}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
		
		double ldis = owner.getDistanceTilePosSq(ltile);
		if (fDistance > ldis) {
			owner.setTilePos(ltile);
			fDistance = ldis;
		}
		
		return false;
	}

	@Override
	public boolean executeBlock(int pMode, int px, int py, int pz) {
		if (!owner.isEqualTile()) {
			return false;
		}
		
		TileEntityFurnace ltile = (TileEntityFurnace)owner.maidTileEntity;
		ItemStack litemstack;
		boolean lflag = false;
		int li;
		
		if (owner.getSwingStatusDominant().canAttack()) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
			// äÆê¨ïiâÒé˚
			litemstack = ltile.getStackInSlot(2);
=======
			// ÂÆåÊàêÂìÅÂõûÂèé
			litemstack = myTile.getStackInSlot(2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
			if (litemstack != null) {
				if (litemstack.stackSize > 0) {
					li = litemstack.stackSize;
					if (owner.maidInventory.addItemStackToInventory(litemstack)) {
						dropExpOrb(litemstack, li - litemstack.stackSize);
						owner.playSound("random.pop");
						owner.setSwing(5, LMM_EnumSound.cookingOver);
//                    	if (!pEntityLittleMaid.maidInventory.isItemBurned(pEntityLittleMaid.maidInventory.currentItem)) {
						owner.getNextEquipItem();
//                    	}
						lflag = true;
					}
				}
				ltile.setInventorySlotContents(2, null);
			}
				
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
			// í≤óùâ¬î\ïiÇ‚}Ç…Ç€Å[Ç¢
			if (!lflag && ltile.getStackInSlot(0) == null) {
				litemstack = ltile.getStackInSlot(2);
=======
			// Ë™øÁêÜÂèØËÉΩÂìÅ„ÇíÁ´à„Å´„ÅΩ„Éº„ÅÑ
			if (!lflag && myTile.getStackInSlot(0) == null) {
				litemstack = myTile.getStackInSlot(2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
				li = owner.maidInventory.getSmeltingItem();
				owner.setEquipItem(li);
				if (li > -1) {
					litemstack = owner.maidInventory.getStackInSlot(li);
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
					// ÉåÉVÉsëŒâûïi
					if (litemstack.stackSize >= ltile.getInventoryStackLimit()) {
						ltile.setInventorySlotContents(0, litemstack.splitStack(ltile.getInventoryStackLimit()));
=======
					// „É¨„Ç∑„ÉîÂØæÂøúÂìÅ
					if (litemstack.stackSize >= myTile.getInventoryStackLimit()) {
						myTile.setInventorySlotContents(0, litemstack.splitStack(myTile.getInventoryStackLimit()));
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
					} else {
						ltile.setInventorySlotContents(0, litemstack.splitStack(litemstack.stackSize));
					}
					if (litemstack.stackSize <= 0) {
						owner.maidInventory.setInventorySlotContents(li, null);
					}
					owner.playSound("random.pop");
					owner.setSwing(5, LMM_EnumSound.cookingStart);
					lflag = true;
				}
			}
			
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
			// éËéùÇøÇÃîRóøÇÇ€Å[Ç¢
			if (!lflag && ltile.getStackInSlot(1) == null && ltile.getStackInSlot(0) != null) {
=======
			// ÊâãÊåÅ„Å°„ÅÆÁáÉÊñô„Çí„ÅΩ„Éº„ÅÑ
			if (!lflag && myTile.getStackInSlot(1) == null && myTile.getStackInSlot(0) != null) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
				owner.getNextEquipItem();
				litemstack = owner.getCurrentEquippedItem();
				if (LMM_InventoryLittleMaid.isItemBurned(litemstack)) {
					if (litemstack.stackSize >= ltile.getInventoryStackLimit()) {
						ltile.setInventorySlotContents(1, litemstack.splitStack(ltile.getInventoryStackLimit()));
					} else {
						ltile.setInventorySlotContents(1, litemstack.splitStack(litemstack.stackSize));
					}
					if (litemstack.stackSize <= 0) {
						owner.maidInventory.setInventoryCurrentSlotContents(null);
					}
					owner.getNextEquipItem();
					owner.playSound("random.pop");
					owner.setSwing(5, LMM_EnumSound.addFuel);
					lflag = true;
				} else {
					if (ltile.isBurning()) {
						lflag = true;
					} else {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
						// îRÇ‚ÇπÇÈÉAÉCÉeÉÄÇéùÇ¡ÇƒÇ»Ç¢ÇÃÇ≈í≤óùâ¬î\ïiÇâÒé˚
						ItemStack litemstack2 = ltile.getStackInSlotOnClosing(0);
=======
						// ÁáÉ„ÇÑ„Åõ„Çã„Ç¢„Ç§„ÉÜ„É†„ÇíÊåÅ„Å£„Å¶„Å™„ÅÑ„ÅÆ„ÅßË™øÁêÜÂèØËÉΩÂìÅ„ÇíÂõûÂèé
						ItemStack litemstack2 = myTile.getStackInSlotOnClosing(0);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
						if (owner.maidInventory.addItemStackToInventory(litemstack2)) {
							owner.playSound("random.pop");
							owner.setSwing(5, LMM_EnumSound.Null);
							owner.getNextEquipItem();
							lflag = false;
						} else {
							ltile.setInventorySlotContents(0, litemstack2);
						}
					}
				}
			} 
			
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Cooking.java
			// îRÇ¶èIÇÌÇ¡ÇƒÇÈÇÃÇ…îRóøå˚Ç…âΩÇ©Ç†ÇÈÇ»ÇÁâÒé˚Ç∑ÇÈ
			if (!lflag && !ltile.isBurning() && ltile.getStackInSlot(1) != null) {
				ItemStack litemstack2 = ltile.getStackInSlotOnClosing(1);
=======
			// ÁáÉ„ÅàÁµÇ„Çè„Å£„Å¶„Çã„ÅÆ„Å´ÁáÉÊñôÂè£„Å´‰Ωï„Åã„ÅÇ„Çã„Å™„ÇâÂõûÂèé„Åô„Çã
			if (!lflag && !myTile.isBurning() && myTile.getStackInSlot(1) != null) {
				ItemStack litemstack2 = myTile.getStackInSlotOnClosing(1);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Cooking.java
				if (owner.maidInventory.addItemStackToInventory(litemstack2)) {
					owner.playSound("random.pop");
					owner.setSwing(5, LMM_EnumSound.Null);
					owner.getNextEquipItem();
					lflag = owner.maidInventory.isItemBurned(owner.getCurrentEquippedItem());
				} else {
					ltile.setInventorySlotContents(1, litemstack2);
				}
			}
		} else {
			lflag = true;
		}
		if (ltile.isBurning()) {
			owner.setWorking(true);
			owner.setSneaking(py - (int)owner.posY <= 0);
			lflag = true;
		}
//mod_LMM_littleMaidMob.Debug("work" + lflag);
		return lflag;
	}

	@Override
	public void startBlock(int pMode) {
//		owner.setWorking(true);
	}

	@Override
	public void resetBlock(int pMode) {
		owner.setSneaking(false);
//		owner.setWorking(false);
	}


	public void dropExpOrb(ItemStack pItemStack, int pCount) {
		if (!owner.worldObj.isRemote) {
			float var3 = FurnaceRecipes.smelting().getExperience(pItemStack.itemID);
			int var4;
			
			if (var3 == 0.0F) {
				pCount = 0;
			} else if (var3 < 1.0F) {
				var4 = MathHelper.floor_float((float)pCount * var3);
				
				if (var4 < MathHelper.ceiling_float_int((float)pCount * var3) && (float)Math.random() < (float)pCount * var3 - (float)var4) {
					++var4;
				}
				
				pCount = var4 == 0 ? 1 : var4;
			}
			
			while (pCount > 0) {
				var4 = EntityXPOrb.getXPSplit(pCount);
				pCount -= var4;
				owner.worldObj.spawnEntityInWorld(new EntityXPOrb(owner.worldObj, owner.posX, owner.posY + 0.5D, owner.posZ + 0.5D, var4));
			}
		}
	}

}
