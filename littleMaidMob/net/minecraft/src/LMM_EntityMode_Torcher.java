package net.minecraft.src;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LMM_EntityMode_Torcher extends LMM_EntityModeBase {
	
	public static final int mmode_Torcher = 0x0020;


	public LMM_EntityMode_Torcher(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
	}

	@Override
	public int priority() {
		return 6200;
	}

	@Override
	public void init() {
		ModLoader.addLocalization("littleMaidMob.mode.Torcher", "Torcher");
		LMM_TriggerSelect.appendTriggerItem(null, "Torch", "");
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Torcher:0x0020
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = pDefaultTargeting;
		
		owner.addMaidMode(ltasks, "Torcher", mmode_Torcher);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (litemstack.itemID == Block.torchWood.blockID || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Torch", litemstack)) {
				owner.setMaidMode("Torcher");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Torcher :
			owner.setBloodsuck(false);
			return true;
		}
		
		return false;
	}

	@Override
	public int getNextEquipItem(int pMode) {
		int li;
		ItemStack litemstack;
		
		// モードに応じた識別判定、速度優先
		switch (pMode) {
		case mmode_Torcher : 
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
				litemstack = owner.maidInventory.getStackInSlot(li);
				if (litemstack == null) continue;
				
				// 松明
				if (litemstack.itemID == Block.torchWood.blockID || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Torch", litemstack)) {
					return li;
				}
			}
			break;
		}
		
		return -1;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return pItemStack.itemID == Block.torchWood.blockID;
	}

	@Override
	public boolean isSearchBlock() {
		return true;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return !(owner.getCurrentEquippedItem() == null);
	}

	public int getBlockLighting(int i, int j, int k) {
		World worldObj = owner.worldObj;
		if (worldObj.getBlockMaterial(i, j - 1, k).isSolid() && worldObj.getBlockId(i, j, k) == 0) {
			return worldObj.getBlockLightValue(i, j, k);
		}
		return 32;
	}

	@Override
	public boolean checkBlock(int pMode, int px, int py, int pz) {
		int v = getBlockLighting(px, py, pz);
		if (v < 8 && canBlockBeSeen(px, py - 1, pz, true, true, false)) {
			if (owner.getNavigator().tryMoveToXYZ(px, py, pz, owner.getAIMoveSpeed()) ) {
				owner.playSound(LMM_EnumSound.findTarget_D, false);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean executeBlock(int pMode, int px, int py, int pz) {
		/*
		ItemStack lis = owner.getCurrentEquippedItem();
		if (lis == null) return false;
		
		int li = lis.stackSize;
		// TODO:当たり判定をどうするか
		if (lis.tryPlaceItemIntoWorld(owner.maidAvatar, owner.worldObj, px, py - 1, pz, 1, 0.5F, 1.0F, 0.5F)) {
			owner.setSwing(10, LMM_EnumSound.installation);
			
			if (owner.maidAvatar.capabilities.isCreativeMode) {
				lis.stackSize = li;
			}
			if (lis.stackSize <= 0) {
				owner.maidInventory.setInventoryCurrentSlotContents(null);
				owner.getNextEquipItem();
			}
		}
		*/
		return false;
	}

	@Override
	public void updateAITick(int pMode) {
		// トーチの設置
		if (pMode == mmode_Torcher && owner.getNextEquipItem()) {
			ItemStack lis = owner.getCurrentEquippedItem();
			int lic = lis.stackSize;
			Item lii = lis.getItem();
			World lworld = owner.worldObj;
			
			// 周囲を検索
			int lxx = MathHelper.floor_double(owner.posX);
			int lyy = MathHelper.floor_double(owner.posY);
			int lzz = MathHelper.floor_double(owner.posZ);
			int lym = MathHelper.floor_float(owner.height) + 1;
//			mod_LMM_littleMaidMob.Debug("torch-s: %d, %d, %d", lxx, lyy, lzz);
			int ll = 8;
			int ltx = lxx, lty = lyy, ltz = lzz;
			int lil[] = {lyy, lyy - 1, lyy + 1};
			owner.maidAvatar.getValue();
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					for (int lyi : lil) {
						int lv = lworld.getBlockLightValue(lxx + x, lyi, lzz + z);
						if (ll > lv && lii instanceof ItemBlock &&
								((ItemBlock)lii).canPlaceItemBlockOnSide(lworld, lxx + x, lyi - 1, lzz + z, 1, owner.maidAvatar, lis)
								&& canBlockBeSeen(lxx + x, lyi - 1, lzz + z, true, false, true)) {
//						if (ll > lv && lworld.getBlockMaterial(lxx + x, lyi - 1, lzz + z).isSolid()
//								&& (lworld.getBlockMaterial(lxx + x, lyi, lzz + z) == Material.air
//								|| lworld.getBlockId(lxx + x, lyi, lzz + z) == Block.snow.blockID)
//								&& canBlockBeSeen(lxx + x, lyi - 1, lzz + z, true, false, true)) {
							ll = lv;
							ltx = lxx + x;
							lty = lyi - 1;
							ltz = lzz + z;
//							mod_LMM_littleMaidMob.Debug("torch: %d, %d, %d: %d", ltx, lty, ltz, lv);
						}
					}
				}
			}
			
			if (ll < 8 && lis.tryPlaceItemIntoWorld(owner.maidAvatar, owner.worldObj, ltx, lty, ltz, 1, 0.5F, 1.0F, 0.5F)) {
//				mod_LMM_littleMaidMob.Debug("torch-inst: %d, %d, %d: %d", ltx, lty, ltz, ll);
				owner.setSwing(10, LMM_EnumSound.installation);
				owner.getNavigator().clearPathEntity();
				if (owner.maidAvatar.capabilities.isCreativeMode) {
					lis.stackSize = lic;
				}
				if (lis.stackSize <= 0) {
					owner.maidInventory.setInventoryCurrentSlotContents(null);
					owner.getNextEquipItem();
				}
			}

		}
	}

}
