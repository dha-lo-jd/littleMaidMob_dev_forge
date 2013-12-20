package net.minecraft.entity;

import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.src.LMM_EntityAIHurtByTarget;
import net.minecraft.src.LMM_EntityAINearestAttackableTarget;
import net.minecraft.src.LMM_TriggerSelect;
import net.minecraft.src.ModLoader;

/**
 * ç‹¬è‡ªåŸºæº–ã¨ã—ã¦ãƒ¢ãƒ¼ãƒ‰å®šæ•°ã¯0x0080ã¯å¹³å¸¸ã€0x00c0ã¯è¡€ã¾ã¿ã‚Œãƒ¢ãƒ¼ãƒ‰ã¨åŒºåˆ¥ã€‚
 */
public class LMM_EntityMode_Fencer extends LMM_EntityModeBase{

	public static final int mmode_Fencer		= 0x0080;
	public static final int mmode_Bloodsucker	= 0x00c0;


	public LMM_EntityMode_Fencer(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
	}

	@Override
	public int priority() {
		return 3000;
	}

	@Override
	public void init() {
		// ç™»éŒ²ãƒ¢ãƒ¼ãƒ‰ã®åç§°è¿½åŠ 
		ModLoader.addLocalization("littleMaidMob.mode.Fencer", "Fencer");
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Fencer.java
		ModLoader.addLocalization("littleMaidMob.mode.Fencer", "ja_JP", "Œì‰qŒ•m");
		ModLoader.addLocalization("littleMaidMob.mode.F-Fencer", "F-Fencer");
		ModLoader.addLocalization("littleMaidMob.mode.F-Fencer", "ja_JP", "©—RŒ•m");
		ModLoader.addLocalization("littleMaidMob.mode.T-Fencer", "T-Fencer");
		ModLoader.addLocalization("littleMaidMob.mode.D-Fencer", "D-Fencer");
		ModLoader.addLocalization("littleMaidMob.mode.Bloodsucker", "Bloodsucker");
		ModLoader.addLocalization("littleMaidMob.mode.Bloodsucker", "ja_JP", "ŒŒ‚É‹Q‚¦‚½–»“y");
		ModLoader.addLocalization("littleMaidMob.mode.F-Bloodsucker", "F-Bloodsucker");
		ModLoader.addLocalization("littleMaidMob.mode.F-Bloodsucker", "ja_JP", "’Ê–‚–»“y");
		ModLoader.addLocalization("littleMaidMob.mode.T-Bloodsucker", "T-Bloodsucker");
		ModLoader.addLocalization("littleMaidMob.mode.D-Bloodsucker", "D-Bloodsucker");
=======
		ModLoader.addLocalization("littleMaidMob.mode.Fencer", "ja_JP", "è­·è¡›å‰£å£«");
		ModLoader.addLocalization("littleMaidMob.mode.Bloodsucker", "Bloodsucker");
		ModLoader.addLocalization("littleMaidMob.mode.Bloodsucker", "ja_JP", "è¡€ã«é£¢ãˆãŸå†¥åœŸ");
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Fencer.java
		LMM_TriggerSelect.appendTriggerItem(null, "Sword", "");
		LMM_TriggerSelect.appendTriggerItem(null, "Axe", "");
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Fencer:0x0080
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = new EntityAITasks(owner.aiProfiler);
		
//		ltasks[1].addTask(1, new EntityAIOwnerHurtByTarget(owner));
//		ltasks[1].addTask(2, new EntityAIOwnerHurtTarget(owner));
		ltasks[1].addTask(3, new LMM_EntityAIHurtByTarget(owner, true));
		ltasks[1].addTask(4, new LMM_EntityAINearestAttackableTarget(owner, EntityLiving.class, 0, true));
		
		owner.addMaidMode(ltasks, "Fencer", mmode_Fencer);
		
		
		// Bloodsucker:0x00c0
		EntityAITasks[] ltasks2 = new EntityAITasks[2];
		ltasks2[0] = pDefaultMove;
		ltasks2[1] = new EntityAITasks(owner.aiProfiler);
		
		ltasks2[1].addTask(1, new LMM_EntityAIHurtByTarget(owner, true));
		ltasks2[1].addTask(2, new LMM_EntityAINearestAttackableTarget(owner, EntityLiving.class, 0, true));
		
		owner.addMaidMode(ltasks2, "Bloodsucker", mmode_Bloodsucker);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (litemstack.getItem() instanceof ItemSword || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Sword", litemstack)) {
				owner.setMaidMode("Fencer");
				return true;
			} else  if (litemstack.getItem() instanceof ItemAxe || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Axe", litemstack)) {
				owner.setMaidMode("Bloodsucker");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Fencer :
//			pentitylittlemaid.maidInventory.currentItem = getNextEquipItem(pentitylittlemaid, pMode);
			owner.setBloodsuck(false);
			return true;
		case mmode_Bloodsucker :
//			pentitylittlemaid.maidInventory.currentItem = getNextEquipItem(pentitylittlemaid, pMode);
			owner.setBloodsuck(true);
			return true;
		}
		
		return false;
	}

	@Override
	public int getNextEquipItem(int pMode) {
		int li;
		int ll = -1;
		double ld = 0;
		double lld;
		ItemStack litemstack;
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Fencer.java
		
		// ƒ‚[ƒh‚É‰‚¶‚½¯•Ê”»’èA‘¬“x—Dæ
=======

		// ãƒ¢ãƒ¼ãƒ‰ã«å¿œã˜ãŸè­˜åˆ¥åˆ¤å®šã€é€Ÿåº¦å„ªå…ˆ
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Fencer.java
		switch (pMode) {
		case mmode_Fencer : 
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
				litemstack = owner.maidInventory.getStackInSlot(li);
				if (litemstack == null) continue;
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Fencer.java
				
				// Œ•
				if (litemstack.getItem() instanceof ItemSword || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Sword", litemstack)) {
					return li;
				}
				
				// UŒ‚—Í‚È‚‚¢‚à‚Ì‚ğ‹L‰¯‚·‚é
=======

				// å‰£
				if (litemstack.getItem() instanceof ItemSword || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Sword", litemstack)) {
					return li;
				}

				// æ”»æ’ƒåŠ›ãªé«˜ã„ã‚‚ã®ã‚’è¨˜æ†¶ã™ã‚‹
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Fencer.java
				lld = 1;
				try {
					lld = MMM_Helper.getAttackVSEntity(litemstack);
				}
				catch (Exception e) {
				}
				if (lld > ld) {
					ll = li;
					ld = lld;
				}
			}
			break;
		case mmode_Bloodsucker :
			for (li = 0; li < owner.maidInventory.maxInventorySize; li++) {
				litemstack = owner.maidInventory.getStackInSlot(li);
				if (litemstack == null) continue;
				
				// æ–§
				if (litemstack.getItem() instanceof ItemAxe || LMM_TriggerSelect.checkWeapon(owner.getMaidMaster(), "Axe", litemstack)) {
					return li;
				}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Fencer.java
				
				// UŒ‚—Í‚È‚‚¢‚à‚Ì‚ğ‹L‰¯‚·‚é
=======

				// æ”»æ’ƒåŠ›ãªé«˜ã„ã‚‚ã®ã‚’è¨˜æ†¶ã™ã‚‹
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Fencer.java
				lld = 1;
				try {
					lld = MMM_Helper.getAttackVSEntity(litemstack);
				}
				catch (Exception e) {
				}
				if (lld > ld) {
					ll = li;
					ld = lld;
				}
			}
			break;
		}
		
		return ll;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		// è£…å‚™ã‚¢ã‚¤ãƒ†ãƒ ã‚’å›å
		return pItemStack.getItem() instanceof ItemSword || pItemStack.getItem() instanceof ItemAxe;
	}

}
