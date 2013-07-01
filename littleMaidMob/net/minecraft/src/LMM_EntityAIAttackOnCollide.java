package net.minecraft.src;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LMM_EntityAIAttackOnCollide extends EntityAIBase implements LMM_IEntityAI {

	public boolean fEnable;

	public World worldObj;
	public LMM_EntityLittleMaid theMaid;
	public Entity entityTarget;
	public float moveSpeed;
	public boolean isReroute;
	public PathEntity pathToTarget;
	public int rerouteTimer;
	public double attackRange;


	public LMM_EntityAIAttackOnCollide(LMM_EntityLittleMaid par1EntityLittleMaid, float par2, boolean par3) {
		theMaid = par1EntityLittleMaid;
		worldObj = par1EntityLittleMaid.worldObj;
		moveSpeed = par2;
		isReroute = par3;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (!fEnable) {
			return false;
		}
		Entity lentity = theMaid.getAttackTarget();
		if (lentity == null) {
			lentity = theMaid.getEntityToAttack();
			if (lentity == null) {
				return false;
			}
		}
		
		entityTarget = lentity;
		pathToTarget = theMaid.getNavigator().getPathToXYZ(entityTarget.posX, entityTarget.posY, entityTarget.posZ);
//		pathToTarget = theMaid.getNavigator().getPathToEntityLiving(entityTarget);
		attackRange = (double)theMaid.width + (double)entityTarget.width + 0.4D;
		attackRange *= attackRange;
		
		if ((pathToTarget != null) || (theMaid.getDistanceSq(entityTarget.posX, entityTarget.boundingBox.minY, entityTarget.posZ) <= attackRange)) {
			return true;
		} else {
			theMaid.setAttackTarget(null);
			theMaid.setTarget(null);
			return false;
		}
		
	}

	@Override
	public void startExecuting() {
		theMaid.getNavigator().setPath(pathToTarget, moveSpeed);
		rerouteTimer = 0;
		theMaid.playSound(theMaid.isBloodsuck() ? LMM_EnumSound.findTarget_B : LMM_EnumSound.findTarget_N, false);
	}

	@Override
	public boolean continueExecuting() {
		Entity lentity = theMaid.getAttackTarget();
		if (lentity == null) {
			lentity = theMaid.getEntityToAttack();
		}
		if (lentity == null || entityTarget != lentity) {
			return false;
		}
		
		if (entityTarget.isDead) {
			theMaid.setAttackTarget(null);
			theMaid.setTarget(null);
			theMaid.getNavigator().clearPathEntity();
			return false;
		}
		
		if (!entityTarget.isEntityAlive()) {
			return false;
		}
		
		if (!isReroute) {
			return !theMaid.getNavigator().noPath();
		}
		
		return theMaid.isWithinHomeDistance(MathHelper.floor_double(entityTarget.posX), MathHelper.floor_double(entityTarget.posY), MathHelper.floor_double(entityTarget.posZ));
	}

	@Override
	public void resetTask() {
		entityTarget = null;
//		theMaid.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask() {
		theMaid.getLookHelper().setLookPositionWithEntity(entityTarget, 30F, 30F);
		
		if ((isReroute || theMaid.getEntitySenses().canSee(entityTarget)) && --rerouteTimer <= 0) {
			// リルート
			rerouteTimer = 4 + theMaid.getRNG().nextInt(7);
			theMaid.getNavigator().tryMoveToXYZ(entityTarget.posX, entityTarget.posY, entityTarget.posZ, moveSpeed);
		}
		
		if (theMaid.getDistanceSq(entityTarget.posX, entityTarget.boundingBox.minY, entityTarget.posZ) > attackRange) {
			return;
		}
		
		// 正面から110度方向が攻撃範囲
		double tdx = entityTarget.posX - theMaid.posX;
		double tdz = entityTarget.posZ - theMaid.posZ;
		double vdx = -Math.sin(theMaid.renderYawOffset * 3.1415926535897932384626433832795F / 180F);
		double vdz = Math.cos(theMaid.renderYawOffset * 3.1415926535897932384626433832795F / 180F);
		double ld = (tdx * vdx + tdz * vdz) / (Math.sqrt(tdx * tdx + tdz * tdz) * Math.sqrt(vdx * vdx + vdz * vdz));
//        System.out.println(theMaid.renderYawOffset + ", " + ld);
		if (ld < -0.35D) {
			return;
		}

		if (!theMaid.getSwingStatusDominant().canAttack()) {
			return;
		} else {
			// 攻撃
			theMaid.attackEntityAsMob(entityTarget);
			// 対象を再設定させる
			if (!theMaid.isBloodsuck()) {
				theMaid.setAttackTarget(null);
				theMaid.setTarget(null);
				theMaid.getNavigator().clearPathEntity();
			}
			return;
		}
	}

	@Override
	public void setEnable(boolean pFlag) {
		fEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return fEnable;
	}
	
}
