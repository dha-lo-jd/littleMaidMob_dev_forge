package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.ModLoader;

public class LMM_EntityMode_Healer extends LMM_EntityModeBase {

	public static final int mmode_Healer		= 0x0082;

	
	public LMM_EntityMode_Healer(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
	}

	@Override
	public int priority() {
		return 3300;
	}

	@Override
	public void init() {
		// 登録モードの名称追加
		ModLoader.addLocalization("littleMaidMob.mode.Healer", "Healer");
		ModLoader.addLocalization("littleMaidMob.mode.F-Healer", "F-Healer");
		ModLoader.addLocalization("littleMaidMob.mode.T-Healer", "T-Healer");
		ModLoader.addLocalization("littleMaidMob.mode.D-Healer", "D-Healer");
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Healer:0x0082
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = new EntityAITasks(owner.aiProfiler);
		
		// 索敵系
		ltasks[1].addTask(1, new EntityAIHurtByTarget(owner, true));
		owner.addMaidMode(ltasks, "Healer", mmode_Healer);
	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		ItemStack litemstack = owner.maidInventory.getStackInSlot(0);
		if (litemstack != null) {
			if (litemstack.getItem() instanceof ItemFood || (litemstack.getItem() instanceof ItemPotion && MMM_Helper.hasEffect(litemstack))) {
				owner.setMaidMode("Healer");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Healer :
			owner.setBloodsuck(false);
			owner.aiAttack.setEnable(false);
			owner.aiShooting.setEnable(false);
			return true;
		}
		
		return false;
	}

	@Override
	public int getNextEquipItem(int pMode) {
		switch (pMode) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Healer.java
		case mmode_Healer:
			// Healer
			for (int i = 0; i < owner.maidInventory.getSizeInventory(); i++) {
				ItemStack is = owner.maidInventory.getStackInSlot(i);
				if (is == null) continue;
				// �Ώۂ͐H�����|�[�V����
				if (is.getItem() instanceof ItemFood || (is.getItem() instanceof ItemPotion && MMM_Helper.hasEffect(is))) {
					return i;
				}
			}
			break;
=======
    	case mmode_Healer:
    		// Healer
    		for (int i = 0; i < owner.maidInventory.getSizeInventory(); i++) {
    			ItemStack is = owner.maidInventory.getStackInSlot(i);
    			if (is == null) continue;
				// 対象は食料かポーション
				if (is.getItem() instanceof ItemFood || (is.getItem() instanceof ItemPotion && is.hasEffect())) {
    	    		return i;
    			}
    		}
    		break;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Healer.java
		}
		return -1;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return pItemStack.getItem() instanceof ItemFood || pItemStack.getItem() instanceof ItemPotion;
	}

	@Override
	public void updateAITick(int pMode) {
		if (pMode == mmode_Healer) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Healer.java
			// �ߐڂ�����ɐH����˂�����
			if (owner.getSwingStatusDominant().canAttack()) {
				// ��̉�
				if (owner.isContractEX() && owner.mstatMasterDistanceSq < 16D
						&& owner.mstatMasterEntity != null && owner.mstatMasterEntity.isEntityAlive()
						&& owner.mstatMasterEntity instanceof EntityPlayer
						&& owner.canEntityBeSeen(owner.mstatMasterEntity)) {
					EntityPlayer lmaster = owner.mstatMasterEntity; 
					int h = lmaster.foodStats.getFoodLevel();
					// �}�X�N�h���C�h�͌�����
					while (owner.isMaskedMaid()) {
						// ��̏�Ԃɍ��킹�ăA�C�e����I��
						if (lmaster.func_110143_aJ() < 9F) {
							// HP�������Ă���Ƃ��̓|�[�V�������g��
							int j = owner.maidInventory.getInventorySlotContainItemPotion(false, Potion.heal.id, lmaster.isEntityUndead());
							if (j > -1) {
								owner.setEquipItem(j);
								break;
							}
						} 
						if (h < 18) {
							// ���R�񕜂ł��Ȃ�����Ȃ�H��
							int j = owner.maidInventory.getInventorySlotContainItemFood();
							if (j > -1) {
								owner.setEquipItem(j);
								break;
							}
						}
						break;
					}
					
					ItemStack itemstack1 = owner.maidInventory.getCurrentItem();
					if (itemstack1 != null) {
						if (itemstack1.getItem() instanceof ItemFood) {
							// �H����˂�����
							if (h < 18) {
								owner.setSwing(10, LMM_EnumSound.healing);
								itemstack1 = itemstack1.onFoodEaten(owner.worldObj, lmaster);
//	                        	owner.worldObj.playSoundAtEntity(lmaster, lmaster.getHurtSound(), 0.5F, (owner.rand.nextFloat() - owner.rand.nextFloat()) * 0.2F + 1.0F);
								if (itemstack1.stackSize <= 0) {
									itemstack1 = null;
								}
								owner.maidInventory.setInventoryCurrentSlotContents(itemstack1);
								owner.getNextEquipItem();
							}
						}
						else if (itemstack1.getItem() instanceof ItemPotion) {
							boolean lswing = true;
							// �|�[�V�����̌��ʂ��d�����Ȃ��悤�Ɏg��
							List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
							if (list != null) {
								PotionEffect potioneffect;
								for(Iterator iterator = list.iterator(); iterator.hasNext();) {
									potioneffect = (PotionEffect)iterator.next();
									if (potioneffect.getPotionID() == Potion.heal.id) {
										if ((6 << potioneffect.getAmplifier()) <= (lmaster.func_110138_aP() - lmaster.func_110143_aJ())) {
=======
			// 近接した主に食物を突っ込む
	        if (owner.getSwingStatusDominant().canAttack()) {
	            // 主の回復
	            if (owner.isMaidContractEX() && owner.mstatMasterDistanceSq < 16D  
	            		&& owner.mstatMasterEntity != null && owner.mstatMasterEntity.isEntityAlive()
	            		&& owner.mstatMasterEntity instanceof EntityPlayer
	            		&& owner.canEntityBeSeen(owner.mstatMasterEntity)) {
		        	EntityPlayer lmaster = owner.mstatMasterEntity; 
	            	int h = lmaster.foodStats.getFoodLevel();
	            	// マスクドメイドは賢いな
	            	while (owner.isMaskedMaid()) {
	            		// 主の状態に合わせてアイテムを選択
	            		if (lmaster.health < 9) {
	            			// HPが減っているときはインスタントヒール
	            			int j = owner.maidInventory.getInventorySlotContainItemPotion(false, Potion.heal.id, lmaster.isEntityUndead());
	            			if (j > -1) {
	            				owner.maidInventory.currentItem = j;
	            				break;
	            			}
	            		} 
	            		if (h < 18) {
	            			// 自然回復できない腹具合なら食料
	            			int j = owner.maidInventory.getInventorySlotContainItemFood();
	            			if (j > -1) {
	            				owner.maidInventory.currentItem = j;
	            				break;
	            			}
	            		}
	            		break;
	            	}
	            	
	          		ItemStack itemstack1 = owner.maidInventory.getCurrentItem();
	              	if (itemstack1 != null) {
	                  	if (itemstack1.getItem() instanceof ItemFood) {
	                    	// 食料を突っ込む
	                  		if (h < 18) {
	                        	owner.setSwing(10, LMM_EnumSound.healing);
	                        	itemstack1 = itemstack1.onFoodEaten(owner.worldObj, lmaster);
//	                        	owner.worldObj.playSoundAtEntity(lmaster, lmaster.getHurtSound(), 0.5F, (owner.rand.nextFloat() - owner.rand.nextFloat()) * 0.2F + 1.0F);
	                       		if (itemstack1.stackSize <= 0) {
	                       			itemstack1 = null;
	                       		}
	                          	owner.maidInventory.setInventoryCurrentSlotContents(itemstack1);
	                       		owner.getNextEquipItem();
	                       	}
	                  	}
	                  	else if (itemstack1.getItem() instanceof ItemPotion) {
	                        boolean f = true;
	                        // ポーションの効果が重複しないように使う
	                        List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
	                        if (list != null) {
	                            PotionEffect potioneffect;
	                            for(Iterator iterator = list.iterator(); iterator.hasNext();) {
	                                potioneffect = (PotionEffect)iterator.next();
	                                if (potioneffect.getPotionID() == Potion.heal.id) {
	                                	if ((6 << potioneffect.getAmplifier()) <= (lmaster.getMaxHealth() - lmaster.health)) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Healer.java
//	                                    	mod_littleMaidMob.Debug(String.format("%d <= %d", (6 << potioneffect.getAmplifier()), (masterEntity.func_40117_c() - masterEntity.health)));
											lswing = true;
										} else {
											lswing = false;
										}
										break;
									} else {
										if (Potion.potionTypes[potioneffect.getPotionID()].isBadEffect()
												|| lmaster.isPotionActive(potioneffect.getPotionID())) {
											lswing = false;
											break;
										}
									}
								}
							}
							
							if (lswing) {
								owner.setSwing(10, LMM_EnumSound.healing_potion);
								owner.usePotionTotarget(lmaster);
//	                        	owner.worldObj.playSoundAtEntity(lmaster, lmaster.getHurtSound(), 0.5F, (owner.rand.nextFloat() - owner.rand.nextFloat()) * 0.2F + 1.0F);
								owner.getNextEquipItem();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public double getRangeToMaster(int pIndex) {
		return pIndex == 0 ? 16D : pIndex == 1 ? 9D : 0D;
	}

}