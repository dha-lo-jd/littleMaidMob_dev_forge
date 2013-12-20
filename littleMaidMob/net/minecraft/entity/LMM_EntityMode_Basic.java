package net.minecraft.entity;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
public class LMM_EntityMode_Basic extends LMM_EntityModeBlockBase {

	public static final int mmode_Wild		= 0x0000;
	public static final int mmode_Escorter	= 0x0001;
	
	private IInventory myInventory;
	private IInventory myChest;
=======
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.LMM_EntityAIAttackOnCollide;
import net.minecraft.src.LMM_EntityAICollectItem;
import net.minecraft.src.LMM_EntityAIHurtByTarget;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.MMM_Helper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_LMM_littleMaidMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class LMM_EntityMode_Basic extends LMM_EntityModeBase {

	public static final int mmode_Escorter = 0x0001;
	public static final int mmode_Wild = 0x0000;

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	private List<IInventory> fusedTiles;
	private boolean isWorking;
	private double lastdistance;
	private int maidSearchCount;
	private IInventory myChest;
	private IInventory myTile;

	/**
	 * Wild, Escorter
	 */
	public LMM_EntityMode_Basic(LMM_EntityLittleMaid pEntity) {
		super(pEntity);
		fusedTiles = new ArrayList<IInventory>();
//		myTile = null;
	}

	@Override
	public void addEntityMode(EntityAITasks pDefaultMove, EntityAITasks pDefaultTargeting) {
		// Wild
		EntityAITasks[] ltasks = new EntityAITasks[2];
		ltasks[0] = new EntityAITasks(null);
		ltasks[1] = new EntityAITasks(null);

		ltasks[0].addTask(1, owner.aiSwiming);
		ltasks[0].addTask(2, new LMM_EntityAIAttackOnCollide(owner, 0.23F, true));
		ltasks[0].addTask(3, owner.aiPanic);
		ltasks[0].addTask(4, owner.aiBegMove);
		ltasks[0].addTask(4, owner.aiBeg);
		ltasks[0].addTask(5, owner.aiRestrictRain);
		ltasks[0].addTask(6, owner.aiFreeRain);
		//        ltasks[0].addTask(4, new EntityAIMoveIndoors(this));
		//		ltasks[0].addTask(7, owner.aiCloseDoor);
		//		ltasks[0].addTask(8, owner.aiOpenDoor);
		ltasks[0].addTask(9, new LMM_EntityAICollectItem(owner, 0.23F));
		ltasks[0].addTask(10, new EntityAILeapAtTarget(owner, 0.3F));
		ltasks[0].addTask(11, owner.aiWander);
		ltasks[0].addTask(12, new EntityAIWatchClosest2(owner, EntityLiving.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAIWatchClosest2(owner, LMM_EntityLittleMaid.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAIWatchClosest2(owner, EntityPlayer.class, 10F, 0.02F));
		ltasks[0].addTask(13, new EntityAILookIdle(owner));

		ltasks[1].addTask(1, new LMM_EntityAIHurtByTarget(owner, false));

		owner.addMaidMode(ltasks, "Wild", mmode_Wild);

		// Escorter:0x0001
		ltasks = new EntityAITasks[2];
		ltasks[0] = pDefaultMove;
		ltasks[1] = pDefaultTargeting;
		owner.addMaidMode(ltasks, "Escorter", mmode_Escorter);

	}

	@Override
	public boolean changeMode(EntityPlayer pentityplayer) {
		// å¼·åˆ¶çš„ã«å‰²ã‚Šå½“ã¦ã‚‹
		owner.setMaidMode("Escorter");
		return true;
	}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	
	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Wild :
			owner.setFreedom(true);
//			owner.aiWander.setEnable(true);
			return true;
		case mmode_Escorter :
			owner.aiAvoidPlayer.setEnable(false);
			for (int li = 0; li < owner.mstatSwingStatus.length; li++) {
				owner.setEquipItem(li, -1);
			}
			return true;
		}
//		owner.getNavigator().clearPathEntity()
		return false;
	}
	
	@Override
	public int getNextEquipItem(int pMode) {
		return pMode == mmode_Wild ? 0 : -1;
	}
	
	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return true;
	}

	@Override
	public boolean isSearchBlock() {
		if (owner.getMaidModeInt() == mmode_Escorter && owner.isFreedom() &&
				owner.maidInventory.getFirstEmptyStack() == -1) {
			// ‘ÎÛ‚ğ‚Ü‚¾Œ©‚Â‚¯‚Ä‚¢‚È‚¢‚Æ‚«‚ÍŒŸõ‚ğs‚¤B
			fDistance = 100F;
			return myInventory == null;
		}
//		clearMy();
//		fusedTiles.clear();
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return myInventory instanceof TileEntity;
	}
=======
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java

	@Override
	public boolean checkBlock(int pMode, int px, int py, int pz) {
		TileEntity ltile = owner.worldObj.getBlockTileEntity(px, py, pz);
		if (!(ltile instanceof IInventory)) {
			return false;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
		if (((IInventory)ltile).getSizeInventory() < 18) {
			// ƒCƒ“ƒxƒ“ƒgƒŠ‚ÌƒTƒCƒY‚ª‚P‚WˆÈ‰º‚È‚ç‘ÎÛ‚Æ‚µ‚È‚¢B
			return false;
		}
		
		// ¢ŠE‚ÌƒƒCƒh‚©‚ç
		if (checkWorldMaid(ltile)) return false;
		// g—pÏ‚İƒ`ƒFƒbƒN
=======

		// ä¸–ç•Œã®ãƒ¡ã‚¤ãƒ‰ã‹ã‚‰
		for (Object lo : owner.worldObj.getLoadedEntityList()) {
			if (lo instanceof LMM_EntityLittleMaid) {
				LMM_EntityLittleMaid lem = (LMM_EntityLittleMaid) lo;
				//				if (lem.isUsingTile(ltile)) {
				//					// èª°ã‹ãŒä½¿ã£ã¦ã„ã‚‹
				//					return false;
				//				}
			}
		}

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
		if (fusedTiles.contains(ltile)) {
			// æ—¢ã«é€šã‚ŠéããŸå ´æ‰€ã‚ˆãƒƒï¼
			return false;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
		
		double ldis = owner.getDistanceTilePosSq(ltile);
		if (fDistance > ldis) {
			myInventory = (IInventory)ltile;
			fDistance = ldis;
		}
		return false;
	}

	@Override
	public boolean overlooksBlock(int pMode) {
		// ƒ`ƒFƒXƒgƒJ[ƒg‚ÌŒŸõ
		List<Entity> list = owner.worldObj.getEntitiesWithinAABB(IInventory.class, owner.boundingBox.expand(8D, 2D, 8D));
		double cartl = 256D;
		for (Entity lentity : list) {
			if (!fusedTiles.contains(lentity)) {
				if (((IInventory)lentity).getSizeInventory() < 18) {
					// ƒCƒ“ƒxƒ“ƒgƒŠ‚ªˆê’èƒTƒCƒYˆÈ‰º‚ÍƒXƒLƒbƒv
					continue;
				}
				double lr = lentity.getDistanceSqToEntity(owner);
				// Œ©‚¦‚éˆÊ’u‚É‚ ‚éÅ‚à‹ß‚¢’²‚×‚Ä‚¢‚È‚¢ƒJ[ƒgƒ`ƒFƒXƒg
				
				if (fDistance > lr && owner.getEntitySenses().canSee(lentity)) {
					myInventory = (IInventory)lentity;
					fDistance = lr;
				}
			}
		}
		lastdistance = -1;
		myChest = null;
		maidSearchCount = 0;
		if (myInventory instanceof TileEntity) {
			owner.setTilePos((TileEntity)myInventory);
			return myInventory != null;
		} else {
			owner.setTarget((Entity)myInventory);
			return false;
		}
//		return myInventory != null;
	}

	@Override
	public void startBlock(int pMode) {
	}

	@Override
	public void resetBlock(int pMode) {
		clearMy();
//		fusedTiles.clear();
	}

	protected void clearMy() {
		myInventory = null;
		if (myChest != null) {
			myChest.closeChest();
			myChest = null;
		}
		owner.clearTilePos();
		owner.setTarget(null);
=======
		myTile = (IInventory) ltile;
		return true;
	}

	@Override
	public boolean checkItemStack(ItemStack pItemStack) {
		return true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

	@Override
	public boolean executeBlock(int pMode, int px, int py, int pz) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
//		isMaidChaseWait = true;
		if (myInventory instanceof TileEntityChest) {
			// ƒuƒƒbƒNŒn‚Ìƒ`ƒFƒXƒg
			TileEntityChest lchest = (TileEntityChest)myInventory;
			if (!lchest.isInvalid()) {
				// g—p’¼‘O‚É‰Â‹”»’è
=======
		//		isMaidChaseWait = true;
		if (myTile instanceof TileEntityChest) {
			// ãƒ–ãƒ­ãƒƒã‚¯ç³»ã®ãƒã‚§ã‚¹ãƒˆ
			if (!((TileEntityChest) myTile).isInvalid()) {
				// ã‚µãƒ¼ãƒã—ãŸãƒã‚§ã‚¹ãƒˆ
				TileEntityChest lchest = (TileEntityChest) myTile;
				// ä½¿ç”¨ç›´å‰ã«å¯è¦–åˆ¤å®š
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
				if (MMM_Helper.canBlockBeSeen(owner, lchest.xCoord, lchest.yCoord, lchest.zCoord, false, true, false)) {
					if (myChest == null) {
						getChest();
						if (myChest != null) {
							myChest.openChest();
						} else {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
							// ŠJ‚©‚È‚¢ƒ`ƒFƒXƒg
							myInventory = null;
=======
							// é–‹ã‹ãªã„ãƒã‚§ã‚¹ãƒˆ
							myTile = null;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
						}
					}
					// ãƒã‚§ã‚¹ãƒˆã«åç´
					owner.setWorking(true);
					putChest();
					return true;
				} else {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
					// Œ©¸‚Á‚½
					clearMy();
				}
			} else {
				// Tile‚ÌÁ¸
				clearMy();
			}
		} else {
			// ‘z’èŠO‚ÌƒCƒ“ƒxƒ“ƒgƒŠ
			if (myInventory != null) {
				fusedTiles.add(myInventory);
=======
					// è¦‹å¤±ã£ãŸ
					myTile = null;
					if (myChest != null) {
						myChest.closeChest();
						myChest = null;
					}
				}
			} else {
				// Tileã®æ¶ˆå¤±
				myTile = null;
				if (myChest != null) {
					myChest.closeChest();
					myChest = null;
				}
			}
		} else if (myTile instanceof Entity) {
			// ãƒã‚§ã‚¹ãƒˆä»˜ã‚«ãƒ¼ãƒˆã¨ã‹
			/*
			Entity lentity = (Entity)myTile;
			if (!lentity.isDead) {
				if (owner.getDistanceSqToEntity(lentity) < 5D)	{
			//				if (!hasPath() && getDistanceSqToEntity(myEntity) < 5D)	{
					setPathToEntity(null);
					if (myChest == null) {
						myChest = (EntityMinecart)myEntity;
						serchedChest.add((EntityMinecart)myEntity);
							myChest.openChest();
					}
					if (myChest != null) {
						faceEntity(myEntity, 30F, 40F);
					}
					// ãƒã‚§ã‚¹ãƒˆã«åç´
					putChest();
				} else {
					// ãƒã‚§ã‚¹ãƒˆã¾ã§ã®ãƒ‘ã‚¹ã‚’ä½œã‚‹
					if (!isMaidWaitEx()) {
						double distance = getDistanceSqToEntity(myEntity);
						if (distance == lastdistance) {
							mod_littleMaidMob.Debug("Assert.");
							updateWanderPath();
						} else {
							setPathToEntity(worldObj.getPathEntityToEntity(this, myEntity, 16F, true, false, false, true));
						}
						lastdistance = distance;
			//						mod_littleMaidMob.Debug(String.format("Rerute:%b", hasPath()));
						if (myChest != null) {
							myChest.closeChest();
							myChest = null;
						}
					}
				}

			} else {
				// Entityã®æ­»äº¡
				myTile = null;
				if (myChest != null) {
					myChest.closeChest();
					myChest = null;
				}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
			}
			clearMy();
		}
		return false;
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	public boolean outrangeBlock(int pMode, int pX, int pY, int pZ) {
		// ƒ`ƒFƒXƒg‚Ü‚Å‚ÌƒpƒX‚ğì‚é
		boolean lf = false;
		if (!owner.isMaidWaitEx()) {
			double distance;
			if (myInventory instanceof TileEntity) {
				distance = owner.getDistanceTilePos();
				if (distance == lastdistance) {
					// TODO:Œ»ó–³ˆÓ–¡
					// ˆÚ“®‚ªŒÅ‚Ü‚ç‚È‚¢‚æ‚¤‚É—”‰Á‘¬
					mod_LMM_littleMaidMob.Debug("Assert.");
					owner.updateWanderPath();
					lf = true;
				} else {
					lf = MMM_Helper.setPathToTile(owner, (TileEntity)myInventory, false);
				}
			} else {
				distance = 0;
			}
			lastdistance = distance;
			// ƒŒƒ“ƒWŠO‚Ìƒ`ƒFƒXƒg‚Í•Â‚¶‚é
			if (myChest != null) {
				myChest.closeChest();
				myChest = null;
			}
		}
		return lf;
	}

	@Override
=======
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	public void farrangeBlock() {
		super.farrangeBlock();
		clearMy();
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java

	protected boolean getChest() {
		// ƒ`ƒFƒXƒg‚ğŠl“¾
		if (myInventory == null) {
			return false;
		}
		// ŒŸõÏ‚İ‚ÉƒXƒ^ƒbƒN
		fusedTiles.add(myInventory);
		if (myInventory instanceof TileEntityChest) {
			TileEntityChest lchest = (TileEntityChest)myInventory;
			if (!lchest.adjacentChestChecked) {
				lchest.checkForAdjacentChests();
			}
			fusedTiles.add(lchest.adjacentChestXNeg);
			fusedTiles.add(lchest.adjacentChestXPos);
			fusedTiles.add(lchest.adjacentChestZNeg);
			fusedTiles.add(lchest.adjacentChestZPosition);
		}
		
		TileEntity ltile = (TileEntity)myInventory;
		Block lblock = Block.blocksList[owner.worldObj.getBlockId(ltile.xCoord, ltile.yCoord, ltile.zCoord)];
		myChest = myInventory;
		if (lblock instanceof BlockChest) {
			myChest = ((BlockChest)lblock).getInventory(owner.worldObj, ltile.xCoord, ltile.yCoord, ltile.zCoord);
		}
		
		return myChest != null;
=======
	public boolean getChest() {
		// ãƒã‚§ã‚¹ãƒˆã‚’ç²å¾—
		if (myTile == null) {
			return false;
		}
		World world = owner.worldObj;
		int i = ((TileEntity) myTile).xCoord;
		int j = ((TileEntity) myTile).yCoord;
		int k = ((TileEntity) myTile).zCoord;
		IInventory obj = (TileEntityChest) world.getBlockTileEntity(i, j, k);
		IInventory obj2 = null;
		if (obj == null || world.isRemote) {
			return false;
		}
		// æ¤œç´¢æ¸ˆã¿
		fusedTiles.add(obj);

		// é–‹ãã‹ã©ã†ã‹ã®åˆ¤å®š
		if (world.isBlockNormalCube(i, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i - 1, j, k) == Block.chest.blockID && world.isBlockNormalCube(i - 1, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i + 1, j, k) == Block.chest.blockID && world.isBlockNormalCube(i + 1, j + 1, k)) {
			return false;
		}
		if (world.getBlockId(i, j, k - 1) == Block.chest.blockID && world.isBlockNormalCube(i, j + 1, k - 1)) {
			return false;
		}
		if (world.getBlockId(i, j, k + 1) == Block.chest.blockID && world.isBlockNormalCube(i, j + 1, k + 1)) {
			return false;
		}
		if (world.getBlockId(i - 1, j, k) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i - 1, j, k);
			obj = new InventoryLargeChest("Large chest", obj2, ((obj)));
		}
		if (world.getBlockId(i + 1, j, k) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i + 1, j, k);
			obj = new InventoryLargeChest("Large chest", ((obj)), obj2);
		}
		if (world.getBlockId(i, j, k - 1) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i, j, k - 1);
			obj = new InventoryLargeChest("Large chest", obj2, ((obj)));
		}
		if (world.getBlockId(i, j, k + 1) == Block.chest.blockID) {
			obj2 = (TileEntityChest) world.getBlockTileEntity(i, j, k + 1);
			obj = new InventoryLargeChest("Large chest", ((obj)), obj2);
		}
		if (obj2 != null) {
			fusedTiles.add(obj2);
		}

		myChest = obj;
		return true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

	@Override
	public int getNextEquipItem(int pMode) {
		return pMode == mmode_Wild ? 0 : -1;
	}

	@Override
	public void init() {
		ModLoader.addLocalization("littleMaidMob.mode.Strike", "Strike");
		ModLoader.addLocalization("littleMaidMob.mode.Wait", "Wait");
		ModLoader.addLocalization("littleMaidMob.mode.Wild", "Wild");
		ModLoader.addLocalization("littleMaidMob.mode.Wild", "ja_JP", "é‡ç”Ÿç¨®");
		ModLoader.addLocalization("littleMaidMob.mode.Escorter", "Escorter");
		ModLoader.addLocalization("littleMaidMob.mode.Escorter", "ja_JP", "å¾“è€…");
		ModLoader.addLocalization("littleMaidMob.mode.F-Escorter", "Freedom");
		ModLoader.addLocalization("littleMaidMob.mode.D-Escorter", "D-Escorter");
		ModLoader.addLocalization("littleMaidMob.mode.T-Escorter", "Tracer");
	}

	@Override
	public boolean isSearchBlock() {
		if (owner.getMaidModeInt() == mmode_Escorter && owner.isFreedom()
				&& owner.maidInventory.getFirstEmptyStack() == -1) {
			/*
						// ãƒã‚§ã‚¹ãƒˆã‚«ãƒ¼ãƒˆã®æ¤œç´¢
						List<Entity> list = owner.worldObj.getEntitiesWithinAABB(IInventory.class, owner.boundingBox.expand(8D, 2D, 8D));
						double cartl = 256D;
						for (Entity lentity : list) {
							if (!fusedTiles.contains(lentity)) {
								if (lentity instanceof EntityMinecart && ((EntityMinecart)lentity).minecartType != 1) {
									continue;
								}
								double l = lentity.getDistanceSqToEntity(owner);
								// è¦‹ãˆã‚‹ä½ç½®ã«ã‚ã‚‹æœ€ã‚‚è¿‘ã„èª¿ã¹ã¦ã„ãªã„ã‚«ãƒ¼ãƒˆãƒã‚§ã‚¹ãƒˆ

								if (cartl > l && owner.getEntitySenses().canSee(lentity)) {
									myTile = (IInventory)lentity;
									cartl = l;
								}
							}
						}
			*/
			// Entityç³»ã®InventoryãŒè¦‹ã¤ã‹ã‚‰ãªã‘ã‚Œã°é€šå¸¸ã®ã‚µãƒ¼ãƒã‚’å®Ÿè¡Œ
			return myTile == null;
		}
		return false;
	}

	@Override
	public boolean outrangeBlock(int pMode, int pX, int pY, int pZ) {
		// ãƒã‚§ã‚¹ãƒˆã¾ã§ã®ãƒ‘ã‚¹ã‚’ä½œã‚‹
		boolean lf = false;
		if (!owner.isMaidWaitEx()) {
			double distance;
			if (myTile instanceof TileEntity) {
				distance = ((TileEntity) myTile).getDistanceFrom(owner.posX, owner.posY, owner.posZ);
				if (distance == lastdistance) {
					// ç§»å‹•ãŒå›ºã¾ã‚‰ãªã„ã‚ˆã†ã«ä¹±æ•°åŠ é€Ÿ
					mod_LMM_littleMaidMob.Debug("Assert.");
					owner.updateWanderPath();
					lf = true;
				} else {
					lf = MMM_Helper.setPathToTile(owner, (TileEntity) myTile, false);
				}
			} else {
				distance = 0;
			}
			lastdistance = distance;
			// ãƒ¬ãƒ³ã‚¸å¤–ã®ãƒã‚§ã‚¹ãƒˆã¯é–‰ã˜ã‚‹
			if (myChest != null) {
				myChest.closeChest();
				myChest = null;
			}
		}
		return lf;
	}

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 9000;
	}

	public void putChest() {
		// ãƒã‚§ã‚¹ãƒˆã«è¿‘æ¥
		if (owner.getSwingStatusDominant().canAttack() && myChest != null) {
			// ç ‚ç³–ã€æ™‚è¨ˆã€è¢«ã£ã¦ã„ã‚‹ãƒ˜ãƒ«ãƒ ä»¥å¤–ã®ã‚¢ã‚¤ãƒ†ãƒ ã‚’çªã£è¾¼ã‚€
			ItemStack is;
			mod_LMM_littleMaidMob.Debug(String.format("getChest:%d", maidSearchCount));
			while ((is = owner.maidInventory.getStackInSlot(maidSearchCount)) == null
					&& maidSearchCount < owner.maidInventory.mainInventory.length) {
				maidSearchCount++;
			}
			if (is != null && !(
					is.getItem().itemID == Item.sugar.itemID
							|| is.getItem().itemID == Item.pocketSundial.itemID
							|| (is == owner.maidInventory.armorItemInSlot(3))
					//					|| (is.getItem() instanceof ItemArmor && ((ItemArmor)is.getItem()).armorType == 0)
					))
			{
				//				mod_littleMaidMob.Debug("getchest2.");
				boolean f = false;
				for (int j = 0; j < myChest.getSizeInventory() && is.stackSize > 0; j++)
				{
					ItemStack isc = myChest.getStackInSlot(j);
					if (isc == null)
					{
						//						mod_littleMaidMob.Debug(String.format("%s -> NULL", is.getItemName()));
						myChest.setInventorySlotContents(j, is.copy());
						is.stackSize = 0;
						f = true;
						break;
					}
					else if (isc.isStackable() && isc.isItemEqual(is))
					{
						//						mod_littleMaidMob.Debug(String.format("%s -> %s", is.getItemName(), isc.getItemName()));
						f = true;
						isc.stackSize += is.stackSize;
						if (isc.stackSize > isc.getMaxStackSize())
						{
							is.stackSize = isc.stackSize - isc.getMaxStackSize();
							isc.stackSize = isc.getMaxStackSize();
						} else {
							is.stackSize = 0;
							break;
						}
					}
				}
				if (is.stackSize <= 0) {
					owner.maidInventory.setInventorySlotContents(maidSearchCount, null);
				}
				if (f) {
					owner.playSound("random.pop");
					owner.setSwing(2, LMM_EnumSound.Null);
				}
			}
			//			mod_littleMaidMob.Debug(String.format("getchest3:%d", maidSearchCount));
			if (++maidSearchCount >= owner.maidInventory.mainInventory.length) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
				// ŒŸõÏ‚İ‚Ì‘ÎÛ‚ğƒXƒ^ƒbƒN
//				serchedChest.add(myChest);
				clearMy();
=======
				// æ¤œç´¢æ¸ˆã¿ã®å¯¾è±¡ã‚’ã‚¹ã‚¿ãƒƒã‚¯
				//				serchedChest.add(myChest);
				myTile = null;
				myChest.closeChest();
				myChest = null;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
				lastdistance = 0D;
				mod_LMM_littleMaidMob.Debug("endChest.");
				// ç©ºããŒã§ããŸã‚‰æœç´¢çµ‚äº†
				if (owner.maidInventory.getFirstEmptyStack() > -1) {
					mod_LMM_littleMaidMob.Debug("Search clear.");
					fusedTiles.clear();
				}
			}
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityMode_Basic.java
	public boolean attackEntityAsMob(int pMode, Entity pEntity) {
		if (pEntity == myInventory) {
			// ƒ`ƒFƒXƒg•tƒJ[ƒg‚Æ‚©
			Entity lentity = (Entity)myInventory;
			if (!lentity.isDead) {
				if (owner.getDistanceSqToEntity(lentity) < 5D)	{
					owner.getNavigator().clearPathEntity();
					if (myChest == null) {
						myChest = (IInventory)lentity;
						fusedTiles.add(myChest);
						myChest.openChest();
					}
					if (myChest != null) {
						owner.getLookHelper().setLookPositionWithEntity(lentity, 30F, 40F);
					}
					// ƒ`ƒFƒXƒg‚Éû”[
					putChest();
				} else {
					// ƒ`ƒFƒXƒg‚Ü‚Å‚ÌƒpƒX‚ğì‚é
					if (!owner.isMaidWaitEx()) {
						double distance = owner.getDistanceSqToEntity(lentity);
						if (distance == lastdistance) {
							// TODO: Œ»ó–³ˆÓ–¡
							mod_LMM_littleMaidMob.Debug("Assert.");
							owner.updateWanderPath();
						} else {
							owner.getNavigator().tryMoveToXYZ(lentity.posX, lentity.posY, lentity.posZ, owner.getAIMoveSpeed());
						}
						lastdistance = distance;
//						mod_littleMaidMob.Debug(String.format("Rerute:%b", hasPath()));
						if (myChest != null) {
							myChest.closeChest();
							myChest = null;
						}
					}
				}
			} else {
				// Entity‚Ì€–S
				clearMy();
			}
			return true;
		} else {
			// ƒ^[ƒQƒbƒg‚ª•Ï‚í‚Á‚Ä‚éH
			clearMy();
		}
		return true;
	}

	@Override
	public boolean isChangeTartget(Entity pTarget) {
		if (pTarget instanceof IInventory) {
			return false;
		}
		return super.isChangeTartget(pTarget);
=======
	public void resetBlock(int pMode) {
		myTile = null;
		if (myChest != null) {
			myChest.closeChest();
			myChest = null;
		}
	}

	@Override
	public boolean setMode(int pMode) {
		switch (pMode) {
		case mmode_Wild:
			owner.setFreedom(true);
			//			owner.aiWander.setEnable(true);
			return true;
		case mmode_Escorter:
			owner.aiAvoidPlayer.setEnable(false);
			for (int li = 0; li < owner.mstatSwingStatus.length; li++) {
				owner.setEquipItem(li, -1);
			}
			return true;
		}
		//		owner.getNavigator().clearPathEntity()
		return false;
	}

	@Override
	public boolean shouldBlock(int pMode) {
		return myTile != null;
	}

	@Override
	public void startBlock(int pMode) {
		lastdistance = -1;
		myChest = null;
		maidSearchCount = 0;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityMode_Basic.java
	}

}
