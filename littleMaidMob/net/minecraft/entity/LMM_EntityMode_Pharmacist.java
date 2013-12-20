package net.minecraft.entity;

import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.LMM_SwingStatus;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class LMM_EntityMode_Pharmacist extends LMM_EntityModeBlockBase {

	public static final int mmode_Pharmacist = 0x0022;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
	protected int inventryPos;
=======
	public int maidSearchCount;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java


	public LMM_EntityMode_Pharmacist(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
	}

	@Override
	public int priority() {
		return 6100;
	}

	@Override
	public void init() {
		ModLoader.addLocalization("littleMaidMob.mode.Pharmacist", "Pharmacist");
		ModLoader.addLocalization("littleMaidMob.mode.T-Pharmacist", "T-Pharmacist");
		ModLoader.addLocalization("littleMaidMob.mode.F-Pharmacist", "F-Pharmacist");
		ModLoader.addLocalization("littleMaidMob.mode.F-Pharmacist", "D-Pharmacist");
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Pharmacist:0x0022
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = pDefaultTargeting;
		
		owner.addMaidMode(ltasks, "Pharmacist", mmode_Pharmacist);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (litemstack.getItem() instanceof ItemPotion && !MMM_Helper.hasEffect(litemstack)) {
				owner.setMaidMode("Pharmacist");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Pharmacist :
			owner.setBloodsuck(false);
			owner.aiJumpTo.setEnable(false);
			owner.aiFollow.setEnable(false);
			owner.aiAttack.setEnable(false);
			owner.aiShooting.setEnable(false);
			inventryPos = 0;
			return true;
		}
		
		return false;
	}

	@Override
	public int getNextEquipItem(int pMode) {
		int li;
		ItemStack litemstack;
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
		
		// ÉÇÅ[ÉhÇ…âûÇ∂ÇΩéØï îªíËÅAë¨ìxóDêÊ
		switch (pMode) {
		case mmode_Pharmacist :
			litemstack = owner.getCurrentEquippedItem();
			if (!(inventryPos > 0 && litemstack != null && !litemstack.getItem().isPotionIngredient())) {
				for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
					litemstack = owner.maidInventory.getStackInSlot(li);
					if (litemstack != null) {
						// ëŒè€ÇÕêÖÉ|Å[ÉVÉáÉì
						if (litemstack.getItem() instanceof ItemPotion && !MMM_Helper.hasEffect(litemstack)) {
							return li;
						}
=======

		// „É¢„Éº„Éâ„Å´Âøú„Åò„ÅüË≠òÂà•Âà§ÂÆö„ÄÅÈÄüÂ∫¶ÂÑ™ÂÖà
		switch (pMode) {
		case mmode_Pharmacist : 
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
				litemstack = owner.maidInventory.getStackInSlot(li);
				if (litemstack != null) {
					// ÂØæË±°„ÅØÊ∞¥„Éù„Éº„Ç∑„Éß„É≥
					if (litemstack.getItem() instanceof ItemPotion && !litemstack.hasEffect()) {
						return li;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
					}
				}
			}
			break;
		}
		
		return -1;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return false;
	}

	@Override
	public boolean isSearchBlock() {
		if (!super.isSearchBlock()) return false;
		
		if (owner.getCurrentEquippedItem() != null) {
			fDistance = Double.MAX_VALUE;
			owner.clearTilePos();
			owner.setSneaking(false);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
		// é¿çsíÜîªíË
		return owner.maidTileEntity instanceof TileEntityBrewingStand &&
				(((TileEntityBrewingStand)owner.maidTileEntity).getBrewTime() > 0 ||
						(owner.getCurrentEquippedItem() != null) || inventryPos > 0);
=======
		// ÂÆüË°å‰∏≠Âà§ÂÆö
/*
		if (myTile.getBrewTime() > 0 
				|| myTile.getStackInSlot(0) != null | myTile.getStackInSlot(1) != null || myTile.getStackInSlot(2) != null 
				|| myTile.getStackInSlot(3) != null) {
			return true;
		}
		if (owner.getCurrentEquippedItem() != null && owner.maidInventory.getSmeltingItem() > -1) {
			return true;
		}
*/
		return false;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
	}

	@Override
	public boolean checkBlock(int pMode, int px, int py, int pz) {
		if (owner.getCurrentEquippedItem() == null) {
			return false;
		}
		TileEntity ltile = owner.worldObj.getBlockTileEntity(px, py, pz);
		if (!(ltile instanceof TileEntityBrewingStand)) {
			return false;
		}
		
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
		// ê¢äEÇÃÉÅÉCÉhÇ©ÇÁ
		checkWorldMaid(ltile);
		// égópÇµÇƒÇ¢ÇΩèˆóØäÌÇ»ÇÁÇªÇ±Ç≈èIóπ
		if (owner.isUsingTile(ltile)) return true;
		
		double ldis = owner.getDistanceTilePosSq(ltile);
		if (fDistance > ldis) {
			owner.setTilePos(ltile);
			fDistance = ldis;
=======
		// ‰∏ñÁïå„ÅÆ„É°„Ç§„Éâ„Åã„Çâ
		for (Object lo : owner.worldObj.getLoadedEntityList()) {
			if (lo == owner) continue;
			if (lo instanceof LMM_EntityLittleMaid) {
				LMM_EntityLittleMaid lem = (LMM_EntityLittleMaid)lo;
				if (lem.isUsingTile(ltile)) {
					return false;
				}
				if (lem.isUsingTile(myTile)) {
					myTile = null;
				}
			}
		}
		if (myTile != null) {
			return myTile == ltile;
		}

		if (mySerch != null) {
			double lleng = ltile.getDistanceFrom(owner.posX, owner.posY, owner.posZ);
			if (lleng < myleng) {
				mySerch = (TileEntityBrewingStand)ltile;
				myleng = lleng;
			}
		} else {
			mySerch = (TileEntityBrewingStand)ltile;
			myleng = mySerch.getDistanceFrom(owner.posX, owner.posY, owner.posZ);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
		}
		
		return false;
	}

	@Override
	public boolean executeBlock(int pMode, int px, int py, int pz) {
		TileEntityBrewingStand ltile = (TileEntityBrewingStand)owner.maidTileEntity;
		if (owner.worldObj.getBlockTileEntity(px, py, pz) != ltile) {
			return false;
		}		
		
		ItemStack litemstack1;
		boolean lflag = false;
		LMM_SwingStatus lswing = owner.getSwingStatusDominant();
		
		// Ëí∏ÁïôÂæÖÊ©ü
//    	isMaidChaseWait = true;
		if (ltile.getStackInSlot(0) != null || ltile.getStackInSlot(1) != null || ltile.getStackInSlot(2) != null || ltile.getStackInSlot(3) != null || !lswing.canAttack()) {
			// „Åä‰ªï‰∫ã‰∏≠
			owner.setWorking(true);
		}
		
		if (lswing.canAttack()) {
			ItemStack litemstack2 = ltile.getStackInSlot(3);
			
			if (litemstack2 != null && ltile.getBrewTime() <= 0) {
				// Ëí∏Áïô‰∏çËÉΩ„Å™„ÅÆ„ÅßÂõûÂèé
				if (py <= owner.posY) {
					owner.setSneaking(true);
				}
				lflag = true;
				if (owner.maidInventory.addItemStackToInventory(litemstack2)) {
					ltile.setInventorySlotContents(3, null);
					owner.playSound("random.pop");
					owner.setSwing(5, LMM_EnumSound.Null);
				}
			}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
			// äÆê¨ïi
			if (!lflag && inventryPos > owner.maidInventory.mainInventory.length) {
				// É|Å[ÉVÉáÉìÇÃâÒé˚
=======
			// ÂÆåÊàêÂìÅ
			if (!lflag && maidSearchCount > owner.maidInventory.mainInventory.length) {
				// „Éù„Éº„Ç∑„Éß„É≥„ÅÆÂõûÂèé
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
				for (int li = 0; li < 3 && !lflag; li ++) {
					litemstack1 = ltile.getStackInSlot(li);
					if (litemstack1 != null && owner.maidInventory.addItemStackToInventory(litemstack1)) {
						ltile.setInventorySlotContents(li, null);
						owner.playSound("random.pop");
						owner.setSwing(5, LMM_EnumSound.Null);
						lflag = true;
					}
				}
				if (!lflag) {
					inventryPos = 0;
					owner.getNextEquipItem();
					lflag = true;
				}
			}
			
			litemstack1 = owner.maidInventory.getCurrentItem();
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
			if (!lflag && (litemstack1 != null && litemstack1.getItem() instanceof ItemPotion && !MMM_Helper.hasEffect(litemstack1))) {
				// êÖïrÇÇ∞Ç¡Ç∆ÇÍÇ≈Ç°
=======
			if (!lflag && (litemstack1 != null && litemstack1.getItem() instanceof ItemPotion && !litemstack1.hasEffect())) {
				// Ê∞¥Áì∂„Çí„Åí„Å£„Å®„Çå„Åß„ÅÉ
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
				int li = 0;
				for (li = 0; li < 3 && !lflag; li++) {
					if (ltile.getStackInSlot(li) == null) {
						ltile.setInventorySlotContents(li, litemstack1);
						owner.maidInventory.setInventoryCurrentSlotContents(null);
						owner.playSound("random.pop");
						owner.setSwing(5, LMM_EnumSound.Null);
						owner.getNextEquipItem();
						lflag = true;
					}
				}
			}
			if (!lflag && (ltile.getStackInSlot(0) != null || ltile.getStackInSlot(1) != null || ltile.getStackInSlot(2) != null)
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
					&& (owner.maidInventory.currentItem == -1 || (litemstack1 != null && litemstack1.getItem() instanceof ItemPotion && !MMM_Helper.hasEffect(litemstack1)))) {
				// É|Å[ÉVÉáÉìà»äOÇåüçı
//				for (inventryPos = 0; inventryPos < owner.maidInventory.mainInventory.length; inventryPos++) {
				for (; inventryPos < owner.maidInventory.mainInventory.length; inventryPos++) {
					litemstack1 = owner.maidInventory.getStackInSlot(inventryPos);
=======
					&& (owner.maidInventory.currentItem == -1 || (litemstack1 != null && litemstack1.getItem() instanceof ItemPotion && !litemstack1.hasEffect()))) {
				// „Éù„Éº„Ç∑„Éß„É≥‰ª•Â§ñ„ÇíÊ§úÁ¥¢
				for (maidSearchCount = 0; maidSearchCount < owner.maidInventory.mainInventory.length; maidSearchCount++) {
					litemstack1 = owner.maidInventory.getStackInSlot(maidSearchCount);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
					if (litemstack1 != null && !(litemstack1.getItem() instanceof ItemPotion)) {
						owner.setEquipItem(inventryPos);
						lflag = true;
						break;
					}
				}
			}
			
			if (!lflag && litemstack2 == null && (ltile.getStackInSlot(0) != null || ltile.getStackInSlot(1) != null || ltile.getStackInSlot(2) != null)) {
				// ÊâãÊåÅ„Å°„ÅÆ„Ç¢„Ç§„ÉÜ„É†„Çí„ÅΩ„Éº„ÅÑ
				if (litemstack1 != null && !(litemstack1.getItem() instanceof ItemPotion) && litemstack1.getItem().isPotionIngredient()) {
					ltile.setInventorySlotContents(3, litemstack1);
					owner.maidInventory.setInventorySlotContents(inventryPos, null);
					owner.playSound("random.pop");
					owner.setSwing(15, LMM_EnumSound.Null);
					lflag = true;
				} 
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Pharmacist.java
				else if (litemstack1 == null || (litemstack1.getItem() instanceof ItemPotion && MMM_Helper.hasEffect(litemstack1)) || !litemstack1.getItem().isPotionIngredient()) {
					// ëŒè€äOÉAÉCÉeÉÄÇî≠å©ÇµÇΩéûÇ…èIóπ
					inventryPos = owner.maidInventory.mainInventory.length;
					lflag = true;
=======
				else if (litemstack1 == null || (litemstack1.getItem() instanceof ItemPotion && litemstack1.hasEffect()) || !litemstack1.getItem().isPotionIngredient()) {
					// ÂØæË±°Â§ñ„Ç¢„Ç§„ÉÜ„É†„ÇíÁô∫Ë¶ã„Åó„ÅüÊôÇ„Å´ÁµÇ‰∫Ü
					maidSearchCount = owner.maidInventory.mainInventory.length;
					lflag = false;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Pharmacist.java
				}
				inventryPos++;
//				owner.maidInventory.currentItem = maidSearchCount;
				owner.setEquipItem(inventryPos);
			}
			
			
			// ÁµÇ‰∫ÜÁä∂ÊÖã„ÅÆ„Ç≠„É£„É≥„Çª„É´
			if (owner.getSwingStatusDominant().index == -1 && litemstack2 == null) {
				owner.getNextEquipItem();
			}
		} else {
			lflag = true;
		}
		if (ltile.getBrewTime() > 0 || inventryPos > 0) {
			owner.setWorking(true);
			lflag = true;
		}
		return lflag;
	}

	@Override
	public void startBlock(int pMode) {
		inventryPos = 0;
	}

	@Override
	public void resetBlock(int pMode) {
		owner.setSneaking(false);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		inventryPos = par1nbtTagCompound.getInteger("InventryPos");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setInteger("InventryPos", inventryPos);
	}

}
