package net.minecraft.entity;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaidAvatar.java
=======
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet18Animation;
import net.minecraft.src.LMM_Client;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.mod_LMM_littleMaidMob;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaidAvatar.java

public class LMM_EntityLittleMaidAvatar extends EntityPlayer {

	public LMM_EntityLittleMaid avatar;
	/** Ç¢ÇÁÇÒÅH **/
	public boolean isItemTrigger;
	/** Ç¢ÇÁÇÒÅH **/
	public boolean isItemReload;
	/** Ç¢ÇÁÇÒÅH **/
	private boolean isItemPreReload;
	private double appendX;
	private double appendY;
	private double appendZ;

	
	public LMM_EntityLittleMaidAvatar(World par1World, LMM_EntityLittleMaid par2EntityLittleMaid) {
		super(par1World, "");
		
		// ÂàùÊúüË®≠ÂÆö
		avatar = par2EntityLittleMaid;
		dataWatcher = avatar.dataWatcher;
		
		inventory = avatar.maidInventory;
		inventory.player = this;
	}

	@Override
	public float getEyeHeight() {
		return avatar.getEyeHeight();
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaidAvatar.java
	protected String getHurtSound() {
=======
	public String getLivingSound() {
		return null;
	}

	@Override
	public String getHurtSound() {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaidAvatar.java
		return null;
	}

	@Override
	public String getDeathSound() {
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(int var1, String var2) {
		return false;
	}

	@Override
	public void addStat(StatBase par1StatBase, int par2) {}

	@Override
	public void addScore(int par1) {}

	@Override
	public void onUpdate() {
//		posX = avatar.posX;
		EntityPlayer lep = avatar.getMaidMasterEntity();
		entityId = avatar.entityId;
		
		if (lep != null) {
			capabilities.isCreativeMode = lep.capabilities.isCreativeMode;
		}
		
		if (xpCooldown > 0) {
			xpCooldown--;
		}
		avatar.experienceValue = experienceTotal;
		
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaidAvatar.java
=======
	public void triggerAchievement(StatBase par1StatBase) {
		// „Ç¢„ÉÅ„Éº„Éñ„É°„É≥„ÉàÊÆ∫„Åó
	}

	@Override
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaidAvatar.java
	public void onItemPickup(Entity entity, int i) {
		// „Ç¢„Ç§„ÉÜ„É†ÂõûÂèé„ÅÆ„Ç®„Éï„Çß„ÇØ„Éà
		if (worldObj.isRemote) {
			// Client
			LMM_Client.onItemPickup(this, entity, i);
		} else {
			super.onItemPickup(entity, i);
		}
	}

	@Override
	public void onCriticalHit(Entity par1Entity) {
		if (worldObj.isRemote) {
			// Client
			LMM_Client.onCriticalHit(this, par1Entity);
		} else {
			((WorldServer)worldObj).getEntityTracker().sendPacketToAllAssociatedPlayers(avatar, new Packet18Animation(par1Entity, 6));
		}
	}

	@Override
	public void onEnchantmentCritical(Entity par1Entity) {
		if (worldObj.isRemote) {
			LMM_Client.onEnchantmentCritical(this, par1Entity);
		} else {
			((WorldServer)worldObj).getEntityTracker().sendPacketToAllAssociatedPlayers(avatar, new Packet18Animation(par1Entity, 7));
		}
	}

	@Override
	public void attackTargetEntityWithCurrentItem(Entity par1Entity) {
		// TODO:
		float ll = 0;
		if (par1Entity instanceof EntityLivingBase) {
			ll = ((EntityLivingBase)par1Entity).func_110143_aJ();
		}
		super.attackTargetEntityWithCurrentItem(par1Entity);
		if (par1Entity instanceof EntityLivingBase) {
			((EntityLivingBase)par1Entity).setRevengeTarget(avatar);
		}
		if (par1Entity instanceof EntityCreature) {
			((EntityCreature)par1Entity).setTarget(avatar);
		}
		if (ll > 0) {
			mod_LMM_littleMaidMob.Debug(String.format("ID:%d Given Damege:%f", avatar.entityId, ll - ((EntityLivingBase)par1Entity).func_110143_aJ()));
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaidAvatar.java
	public ItemStack getCurrentEquippedItem() {
		return avatar.getCurrentEquippedItem();
=======
	public void alertWolves(EntityLiving par1EntityLiving, boolean par2) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaidAvatar.java
	}

	@Override
	public ItemStack getCurrentArmor(int par1) {
		return avatar.func_130225_q(par1);
	}

	@Override
	public ItemStack getCurrentItemOrArmor(int par1) {
		return avatar.getCurrentItemOrArmor(par1);
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		return avatar.getLastActiveItems();
	}
/*
	@Override
	protected void alertWolves(EntityLivingBase par1EntityLiving, boolean par2) {
		// Ç±Ç±Çê›íËÇµÇøÇ·Ç§Ç∆í èÌÇ≈ÇÕÇ ÇÈÇ€óéÇøÇ∑ÇÈ
	}
*/
	@Override
	public void destroyCurrentEquippedItem() {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaidAvatar.java
		// ÉAÉCÉeÉÄÇ™âÛÇÍÇΩÇÃÇ≈éüÇÃëïîıÇëIë
		// TODO:íAÇµÅAForgeìôÇ≈ÉvÉåÅ[ÉÑÅ[ÉCÉxÉìÉgÇê›íËÇµÇƒÇ¢ÇÈÇ‡ÇÃÇæÇ∆Ç ÇÈÇ€óéÇøÇ∑ÇÈÇÃÇ≈ÅAâΩÇÁÇ©ÇÃëŒçÙÇ™ïKóvÅB
//		super.destroyCurrentEquippedItem();
		inventory.setInventorySlotContents(inventory.currentItem, (ItemStack)null);
=======
		// „Ç¢„Ç§„ÉÜ„É†„ÅåÂ£ä„Çå„Åü„ÅÆ„ÅßÊ¨°„ÅÆË£ÖÂÇô„ÇíÈÅ∏Êäû
		super.destroyCurrentEquippedItem();
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaidAvatar.java
		avatar.getNextEquipItem();
	}

	@Override
	public void onKillEntity(EntityLivingBase entityliving) {
		avatar.onKillEntity(entityliving);
	}

	protected Entity getEntityServer() {
		return worldObj.isRemote ? null : this;
	}

	// Itemégópä÷òA

	public int getItemInUseDuration(int pIndex) {
		return avatar.getSwingStatus(pIndex).getItemInUseDuration();
	}
	@Deprecated
	@Override
	public int getItemInUseDuration() {
		return getItemInUseDuration(avatar.maidDominantArm);
	}

	public ItemStack getItemInUse(int pIndex) {
		return avatar.getSwingStatus(pIndex).getItemInUse();
	}
	@Deprecated
	@Override
	public ItemStack getItemInUse() {
		return getItemInUse(avatar.maidDominantArm);
	}

	public int getItemInUseCount(int pIndex) {
		return avatar.getSwingStatus(pIndex).getItemInUseCount();
	}
	@Deprecated
	@Override
	public int getItemInUseCount() {
		return getItemInUseCount(avatar.maidDominantArm);
	}

	public boolean isUsingItem(int pIndex) {
		return avatar.getSwingStatus(pIndex).isUsingItem();
	}
	@Deprecated
	@Override
	public boolean isUsingItem() {
		return isUsingItem(avatar.maidDominantArm);
	}
	public boolean isUsingItemLittleMaid() {
		return super.isUsingItem() | isItemTrigger;
	}

	public void clearItemInUse(int pIndex) {
		avatar.getSwingStatus(pIndex).clearItemInUse(getEntityServer());
	}
	@Deprecated
	@Override
	public void clearItemInUse() {
//		super.clearItemInUse();
		isItemTrigger = false;
		isItemReload = isItemPreReload = false;
		clearItemInUse(avatar.maidDominantArm);
	}

	public void stopUsingItem(int pIndex) {
		avatar.getSwingStatus(pIndex).stopUsingItem(getEntityServer());
	}
	@Deprecated
	@Override
	public void stopUsingItem() {
//		super.stopUsingItem();
		isItemTrigger = false;
		isItemReload = isItemPreReload = false;
		stopUsingItem(avatar.maidDominantArm);
	}

	public void setItemInUse(int pIndex, ItemStack itemstack, int i) {
		avatar.getSwingStatus(pIndex).setItemInUse(itemstack, i, getEntityServer());
	}
	@Deprecated
	@Override
	public void setItemInUse(ItemStack itemstack, int i) {
//		super.setItemInUse(itemstack, i);
		isItemTrigger = true;
		isItemReload = isItemPreReload;
		setItemInUse(avatar.maidDominantArm, itemstack, i);
	}

	@Override
	public void setEating(boolean par1) {
		avatar.setEating(par1);
	}

	@Override
	public boolean isEating() {
		return avatar.isEating();
	}

	@Override
	public void setAir(int par1) {
		avatar.setAir(par1);
	}

	@Override
	public int getAir() {
		return avatar.getAir();
	}

	@Override
	public void setFire(int par1) {
		avatar.setFire(par1);
	}

	@Override
	public boolean isBurning() {
		return avatar.isBurning();
	}

	@Override
	protected void setFlag(int par1, boolean par2) {
		avatar.setFlag(par1, par2);
	}

	@Override
	public boolean isBlocking() {
		return avatar.isBlocking();
	}

	@Override
	public void playSound(String par1Str, float par2, float par3) {
		avatar.playSound(par1Str, par2, par3);
	}

	public void getValue() {
		// EntityLittleMaid„Åã„ÇâÂÄ§„Çí„Ç≥„Éî„Éº
		setPosition(avatar.posX, avatar.posY, avatar.posZ);
		prevPosX = avatar.prevPosX;
		prevPosY = avatar.prevPosY;
		prevPosZ = avatar.prevPosZ;
		rotationPitch = avatar.rotationPitch;
		rotationYaw = avatar.rotationYaw;
		prevRotationPitch = avatar.prevRotationPitch;
		prevRotationYaw = avatar.prevRotationYaw;
		yOffset = avatar.yOffset;
		renderYawOffset = avatar.renderYawOffset;
		prevRenderYawOffset = avatar.prevRenderYawOffset;
		rotationYawHead = avatar.rotationYawHead;
		attackTime = avatar.attackTime;
	}

	public void getValueVector(double atx, double aty, double atz, double atl) {
		// EntityLittleMaid„Åã„ÇâÂÄ§„Çí„Ç≥„Éî„Éº
		double l = MathHelper.sqrt_double(atl);
		appendX = atx / l;
		appendY = aty / l;
		appendZ = atz / l;
		posX = avatar.posX + appendX;
		posY = avatar.posY + appendY;
		posZ = avatar.posZ + appendZ;
		prevPosX = avatar.prevPosX + appendX;
		prevPosY = avatar.prevPosY + appendY;
		prevPosZ = avatar.prevPosZ + appendZ;
		rotationPitch		= avatar.rotationPitch;
		prevRotationPitch	= avatar.prevRotationPitch;
		rotationYaw			= avatar.rotationYaw;
		prevRotationYaw		= avatar.prevRotationYaw;
		renderYawOffset		= avatar.renderYawOffset;
		prevRenderYawOffset	= avatar.prevRenderYawOffset;
		rotationYawHead		= avatar.rotationYawHead;
		prevRotationYawHead	= avatar.prevRotationYawHead;
		yOffset = avatar.yOffset;
		motionX = avatar.motionX;
		motionY = avatar.motionY;
		motionZ = avatar.motionZ;
		isSwingInProgress = avatar.getSwinging();
	}

	/**
	 * Â∞ÑÊíÉÁÆ°Âà∂Áî®„ÄÅrotation„ÇíÈ†≠„Å´Âêà„Çè„Åõ„Çã
	 */
	public void getValueVectorFire(double atx, double aty, double atz, double atl) {
		// EntityLittleMaid„Åã„ÇâÂÄ§„Çí„Ç≥„Éî„Éº
		double l = MathHelper.sqrt_double(atl);
		appendX = atx / l;
		appendY = aty / l;
		appendZ = atz / l;
		posX = avatar.posX + appendX;
		posY = avatar.posY + appendY;
		posZ = avatar.posZ + appendZ;
		prevPosX = avatar.prevPosX + appendX;
		prevPosY = avatar.prevPosY + appendY;
		prevPosZ = avatar.prevPosZ + appendZ;
		rotationPitch		= updateDirection(avatar.rotationPitch);
		prevRotationPitch	= updateDirection(avatar.prevRotationPitch);
		rotationYaw			= updateDirection(avatar.rotationYawHead);
		prevRotationYaw		= updateDirection(avatar.prevRotationYawHead);
		renderYawOffset		= updateDirection(avatar.renderYawOffset);
		prevRenderYawOffset	= updateDirection(avatar.prevRenderYawOffset);
		rotationYawHead		= updateDirection(avatar.rotationYawHead);
		prevRotationYawHead	= updateDirection(avatar.prevRotationYawHead);
		yOffset = avatar.yOffset;
		motionX = avatar.motionX;
		motionY = avatar.motionY;
		motionZ = avatar.motionZ;
		isSwingInProgress = avatar.getSwinging();
	}

	public float updateDirection(float pValue) {
		pValue %= 360F;
		if (pValue < 0) pValue += 360F;
		return pValue;
	}


	public void setValue() {
		// EntityLittleMiad„Å∏ÂÄ§„Çí„Ç≥„Éî„Éº
		avatar.setPosition(posX, posY, posZ);
		avatar.prevPosX = prevPosX;
		avatar.prevPosY = prevPosY;
		avatar.prevPosZ = prevPosZ;
		avatar.rotationPitch = rotationPitch;
		avatar.rotationYaw = rotationYaw;
		avatar.prevRotationPitch = prevRotationPitch;
		avatar.prevRotationYaw = prevRotationYaw;
		avatar.yOffset = yOffset;
		avatar.renderYawOffset = renderYawOffset;
		avatar.prevRenderYawOffset = prevRenderYawOffset;
		avatar.getSwingStatusDominant().attackTime = avatar.attackTime = attackTime;
	}

	public void setValueRotation() {
		// EntityLittleMiad„Å∏ÂÄ§„Çí„Ç≥„Éî„Éº
		avatar.rotationPitch = rotationPitch;
		avatar.rotationYaw = rotationYaw;
		avatar.prevRotationPitch = prevRotationPitch;
		avatar.prevRotationYaw = prevRotationYaw;
		avatar.renderYawOffset = renderYawOffset;
		avatar.prevRenderYawOffset = prevRenderYawOffset;
		avatar.motionX = motionX;
		avatar.motionY = motionY;
		avatar.motionZ = motionZ;
		if (isSwingInProgress) avatar.setSwinging(LMM_EnumSound.Null);
		
	}

	public void setValueVector() {
		// EntityLittleMiad„Å∏ÂÄ§„Çí„Ç≥„Éî„Éº
		avatar.posX = posX - appendX;
		avatar.posY = posY - appendY;
		avatar.posZ = posZ - appendZ;
		avatar.prevPosX = prevPosX - appendX;
		avatar.prevPosY = prevPosY - appendY;
		avatar.prevPosZ = prevPosZ - appendZ;
		avatar.rotationPitch	 = rotationPitch;
		avatar.prevRotationPitch = prevRotationPitch;
//		avatar.rotationYaw			= rotationYaw;
//		avatar.prevRotationYaw		= prevRotationYaw;
//		avatar.renderYawOffset		= renderYawOffset;
//		avatar.prevRenderYawOffset	= prevRenderYawOffset;
		avatar.motionX = motionX;
		avatar.motionY = motionY;
		avatar.motionZ = motionZ;
		if (isSwingInProgress) avatar.setSwinging(LMM_EnumSound.Null);
	}

	@Override
	public ChunkCoordinates getPlayerCoordinates() {
		return null;
	}

	@Override
	public void sendChatToPlayer(ChatMessageComponent var1) {
		// É`ÉÉÉbÉgÉÅÉbÉZÅ[ÉWÇÕégÇÌÇ»Ç¢ÅB
	}

	// ïsóvÅH

	@Override
	protected void setHideCape(int par1, boolean par2) {}

	@Override
	protected boolean getHideCape(int par1) {
		return false;
	}

	@Override
	public void setScore(int par1) {}

	@Override
	public int getScore() {
		return 0;
	}

	public void func_110149_m(float par1) {
		/*
		if (par1 < 0.0F) {
			par1 = 0.0F;
		}
		
		this.getDataWatcher().updateObject(17, Float.valueOf(par1));
		*/
		avatar.func_110149_m(par1);
	}

	public float func_110139_bj() {
//		return this.getDataWatcher().func_111145_d(17);
		return avatar.func_110139_bj();
	}

}
