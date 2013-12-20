package net.minecraft.src;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.entity.LMM_EntityModeBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public class LMM_EntityAIHurtByTarget extends EntityAIHurtByTarget {

	public LMM_EntityLittleMaid theMaid;
	private boolean field_75303_a;
	private int field_75301_b;
	private int field_75302_c;


	public LMM_EntityAIHurtByTarget(LMM_EntityLittleMaid par1EntityLiving, boolean par2) {
		super(par1EntityLiving, par2);
		
		theMaid = par1EntityLiving;
		field_75303_a = false;
		field_75301_b = 0;
		field_75302_c = 0;
	}

	@Override
	public boolean shouldExecute() {
<<<<<<< HEAD
		if (theMaid.isContract() && !theMaid.isBlocking() && theMaid.mstatMasterEntity != null) {
			// ÉtÉFÉìÉTÅ[ånÇÕéÂÇ…ëŒÇ∑ÇÈçUåÇÇ…îΩâû
			EntityLivingBase lentity = theMaid.mstatMasterEntity.getAITarget();
=======
		if (theMaid.isMaidContract() && !theMaid.isBlocking() && theMaid.mstatMasterEntity != null) {
			// „Éï„Çß„É≥„Çµ„ÉºÁ≥ª„ÅØ‰∏ª„Å´ÂØæ„Åô„ÇãÊîªÊíÉ„Å´ÂèçÂøú
			EntityLiving lentity = theMaid.mstatMasterEntity.getAITarget();
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			if (isSuitableTarget(lentity, false)) {
				theMaid.setRevengeTarget(lentity);
				return true;
			}
		}
		return super.shouldExecute();
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	@Override
	public void updateTask() {
		super.updateTask();
		String s1 = taskOwner.getAITarget() == null ? "Null" : taskOwner.getAITarget().getClass().toString();
		String s2 = taskOwner.getAttackTarget() == null ? "Null" : taskOwner.getAttackTarget().getClass().toString();
//		System.out.println(String.format("ID:%d, target:%s, attack:%s", taskOwner.entityId, s1, s2));
		
<<<<<<< HEAD
		// â£ÇÁÇÍÇΩédï‘Çµ
		EntityLivingBase leliving = taskOwner.getAITarget();
=======
		// ÊÆ¥„Çâ„Çå„Åü‰ªïËøî„Åó
		EntityLiving leliving = taskOwner.getAITarget();
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		if (leliving != null && leliving != taskOwner.getAttackTarget()) {
			taskOwner.setAttackTarget(null);
			System.out.println(String.format("ID:%d, ChangeTarget.", taskOwner.entityId));
		}
		
	}
	
	@Override
<<<<<<< HEAD
	protected boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		// LMMópÇ…ÉJÉXÉ^ÉÄ
=======
	public boolean isSuitableTarget(EntityLiving par1EntityLiving, boolean par2) {
		// LMMÁî®„Å´„Ç´„Çπ„Çø„É†
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		if (par1EntityLiving == null) {
			return false;
		}
		if (par1EntityLiving == taskOwner) {
			return false;
		}
		if (par1EntityLiving == theMaid.mstatMasterEntity) {
			return false;
		}
		if (!par1EntityLiving.isEntityAlive()) {
			return false;
		}
		
		LMM_EntityModeBase lailm = theMaid.getActiveModeClass(); 
		if (lailm != null && lailm.isSearchEntity()) {
			if (!lailm.checkEntity(theMaid.getMaidModeInt(), par1EntityLiving)) {
				return false;
			}
		} else {
			if (theMaid.getIFF(par1EntityLiving)) {
				return false;
			}
		}
		
<<<<<<< HEAD
		// äÓì_Ç©ÇÁàÍíËãóó£ó£ÇÍÇƒÇ¢ÇÈèÍçáÇ‡çUåÇÇµÇ»Ç¢
		if (!taskOwner.func_110176_b(MathHelper.floor_double(par1EntityLiving.posX), MathHelper.floor_double(par1EntityLiving.posY), MathHelper.floor_double(par1EntityLiving.posZ))) {
//		if (!taskOwner.isWithinHomeDistance(MathHelper.floor_double(par1EntityLiving.posX), MathHelper.floor_double(par1EntityLiving.posY), MathHelper.floor_double(par1EntityLiving.posZ))) {
=======
		// Âü∫ÁÇπ„Åã„Çâ‰∏ÄÂÆöË∑ùÈõ¢Èõ¢„Çå„Å¶„ÅÑ„ÇãÂ†¥Âêà„ÇÇÊîªÊíÉ„Åó„Å™„ÅÑ
		if (!taskOwner.isWithinHomeDistance(MathHelper.floor_double(par1EntityLiving.posX), MathHelper.floor_double(par1EntityLiving.posY), MathHelper.floor_double(par1EntityLiving.posZ))) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			return false;
		}
		
		// „Çø„Éº„Ç≤„ÉÉ„Éà„ÅåË¶ã„Åà„Å™„ÅÑ
		if (shouldCheckSight && !taskOwner.getEntitySenses().canSee(par1EntityLiving)) {
			return false;
		}
		
		// ÊîªÊíÉ‰∏≠Ê≠¢Âà§ÂÆöÔºü
		if (this.field_75303_a) {
			if (--this.field_75302_c <= 0) {
				this.field_75301_b = 0;
			}
			
			if (this.field_75301_b == 0) {
				this.field_75301_b = this.func_75295_a(par1EntityLiving) ? 1 : 2;
			}
			
			if (this.field_75301_b == 2) {
				return false;
			}
		}
		
		return true;
	}

	private boolean func_75295_a(Entity par1EntityLiving) {
		this.field_75302_c = 10 + this.taskOwner.getRNG().nextInt(5);
		PathEntity var2 = taskOwner.getNavigator().getPathToXYZ(par1EntityLiving.posX, par1EntityLiving.posY, par1EntityLiving.posZ);
//		PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLiving);
		
		if (var2 == null) {
			return false;
		} else {
			PathPoint var3 = var2.getFinalPathPoint();
			
			if (var3 == null) {
				return false;
			} else {
				int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
				int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
				return (double)(var4 * var4 + var5 * var5) <= 2.25D;
			}
		}
	}

}
