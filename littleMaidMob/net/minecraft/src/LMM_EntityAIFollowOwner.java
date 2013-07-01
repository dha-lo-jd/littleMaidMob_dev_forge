package net.minecraft.src;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;

public class LMM_EntityAIFollowOwner extends EntityAIBase implements LMM_IEntityAI {

	private LMM_EntityLittleMaid theMaid;
	private EntityLiving theOwner;
	private World theWorld;
	private float moveSpeed;
	private PathNavigate petPathfinder;
	private int field_48310_h;
	public double maxDist;
	public double minDist;
	private boolean field_48311_i;
	public boolean isEnable;

	public LMM_EntityAIFollowOwner(LMM_EntityLittleMaid par1EntityLittleMaid,
			float par2, double par3, double par4) {
		theMaid = par1EntityLittleMaid;
		theWorld = par1EntityLittleMaid.worldObj;
		moveSpeed = par2;
		petPathfinder = par1EntityLittleMaid.getNavigator();
		minDist = par3;
		maxDist = par4;
		isEnable = true;
		setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (!isEnable)
			return false;

		EntityLiving entityliving = theMaid.getOwner();
		if (entityliving == null) {
			return false;
		}

		if (theMaid.isSitting()) {
			return false;
		}

		if (theMaid.getDistanceSqToEntity(entityliving) < minDist) {
			return false;
		} else {
			theOwner = entityliving;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !petPathfinder.noPath()
				&& theMaid.getDistanceSqToEntity(theOwner) > maxDist
				&& !theMaid.isSitting();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		field_48310_h = 0;
		field_48311_i = theMaid.getNavigator().getAvoidsWater();
		theMaid.getNavigator().setAvoidsWater(false);
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		theOwner = null;
		petPathfinder.clearPathEntity();
		theMaid.getNavigator().setAvoidsWater(field_48311_i);
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		theMaid.getLookHelper().setLookPositionWithEntity(theOwner, 10F,
				theMaid.getVerticalFaceSpeed());

		if (theMaid.isSitting()) {
			return;
		}

		if (--field_48310_h > 0) {
			return;
		}

		field_48310_h = 10;

		petPathfinder.tryMoveToEntityLiving(theOwner, moveSpeed);
	}

	@Override
	public void setEnable(boolean pFlag) {
		isEnable = pFlag;
	}

	@Override
	public boolean getEnable() {
		return isEnable;
	}

}
