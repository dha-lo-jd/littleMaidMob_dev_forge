package net.minecraft.src;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.entity.LMM_EntityModeBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTargetSorter;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public class LMM_EntityAINearestAttackableTarget extends EntityAINearestAttackableTarget {

<<<<<<< HEAD
	protected LMM_EntityLittleMaid theMaid;
	protected Entity targetEntity;
	protected Class targetClass;
	protected int targetChance;
	protected LMM_EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
=======
	public LMM_EntityLittleMaid theMaid;
	Entity targetEntity;
	Class targetClass;
	int targetChance;
	private final IEntitySelector field_82643_g;
	private EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0

	private boolean field_75303_a;
	private int field_75301_b;
	private int field_75302_c;


	public LMM_EntityAINearestAttackableTarget(LMM_EntityLittleMaid par1EntityLiving, Class par2Class, int par4, boolean par5) {
		this(par1EntityLiving, par2Class, par4, par5, false);
	}

	public LMM_EntityAINearestAttackableTarget(LMM_EntityLittleMaid par1, Class par2, int par4, boolean par5, boolean par6) {
		super(par1, par2, par4, par5, par6, null);
		targetClass = par2;
		targetChance = par4;
		theNearestAttackableTargetSorter = new LMM_EntityAINearestAttackableTargetSorter(par1);
		field_75301_b = 0;
		field_75302_c = 0;
		field_75303_a = par6;
		theMaid = par1;
		
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
			return false;
//		} else if (theMaid.getAttackTarget() != null) {
//			return true;
		} else {
			double lfollowRange = this.func_111175_f();
			List llist = this.taskOwner.worldObj.getEntitiesWithinAABB(targetClass, taskOwner.boundingBox.expand(lfollowRange, 4.0D, lfollowRange));
			if (theMaid.mstatMasterEntity != null && !theMaid.isBloodsuck()) {
<<<<<<< HEAD
				// É\Å[É^Å[ÇéÂíÜêSÇ÷
				theNearestAttackableTargetSorter.setEntity(theMaid.mstatMasterEntity);
			} else {
				// é©ï™íÜêSÇ…É\Å[Ég
				theNearestAttackableTargetSorter.setEntity(theMaid);
=======
				// „ÇΩ„Éº„Çø„Éº„Çí‰∏ª‰∏≠ÂøÉ„Å∏
				Collections.sort(var5, new EntityAINearestAttackableTargetSorter(this, theMaid.mstatMasterEntity));
			} else {
				// Ëá™ÂàÜ‰∏≠ÂøÉ„Å´„ÇΩ„Éº„Éà
				Collections.sort(var5, this.theNearestAttackableTargetSorter);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			}
			Collections.sort(llist, theNearestAttackableTargetSorter);
			Iterator var2 = llist.iterator();
			while (var2.hasNext()) {
				Entity var3 = (Entity)var2.next();
				if (var3.isEntityAlive() && this.isSuitableTargetLM(var3, false)) {
					this.targetEntity = var3;
					return true;
				}
			}
			
			return false;
		}
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		if (targetEntity instanceof EntityLiving) {
			theMaid.setAttackTarget((EntityLiving)targetEntity);
		} else {
			theMaid.setTarget(targetEntity);
		}
	}

//	@Override
<<<<<<< HEAD
	protected boolean isSuitableTargetLM(Entity par1EntityLiving, boolean par2) {
		// LMMópÇ…ÉJÉXÉ^ÉÄ
		// îÒê∂ï®Ç‡ëŒè€ÇÃÇΩÇﬂï ÉNÉâÉX
=======
	public boolean isSuitableTargetLM(Entity par1EntityLiving, boolean par2) {
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
