package net.minecraft.entity;

import static net.minecraft.src.LMM_Statics.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.model.MMM_ModelMultiBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.LMM_ContainerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet42RemoveEntityEffect;
import net.minecraft.network.packet.Packet5PlayerInventory;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.src.LMM_Client;
import net.minecraft.src.LMM_EntityAIAttackArrow;
import net.minecraft.src.LMM_EntityAIAttackOnCollide;
import net.minecraft.src.LMM_EntityAIAvoidPlayer;
import net.minecraft.src.LMM_EntityAIBeg;
import net.minecraft.src.LMM_EntityAIBegMove;
import net.minecraft.src.LMM_EntityAICollectItem;
import net.minecraft.src.LMM_EntityAIFindBlock;
import net.minecraft.src.LMM_EntityAIFleeRain;
import net.minecraft.src.LMM_EntityAIFollowOwner;
import net.minecraft.src.LMM_EntityAIJumpToMaster;
import net.minecraft.src.LMM_EntityAIRestrictRain;
import net.minecraft.src.LMM_EntityAISwimming;
import net.minecraft.src.LMM_EntityAITracerMove;
import net.minecraft.src.LMM_EntityAIWait;
import net.minecraft.src.LMM_EntityAIWander;
import net.minecraft.src.LMM_EnumSound;
import net.minecraft.src.LMM_IFF;
import net.minecraft.src.LMM_InventoryLittleMaid;
import net.minecraft.src.LMM_Net;
import net.minecraft.src.LMM_SoundManager;
import net.minecraft.src.LMM_SwingStatus;
import net.minecraft.src.MMM_Counter;
import net.minecraft.src.MMM_EquippedStabilizer;
import net.minecraft.src.MMM_Helper;
import net.minecraft.src.MMM_ITextureEntity;
import net.minecraft.src.MMM_TextureBox;
import net.minecraft.src.MMM_TextureBoxServer;
import net.minecraft.src.MMM_TextureManager;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_LMM_littleMaidMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;

public class LMM_EntityLittleMaid extends EntityTameable implements MMM_ITextureEntity {

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	// �萔��Statics�ֈړ�


	// �ϐ����炵�����Ȃ�
//    protected long maidContractLimit;		// �_�񎸌���
	protected int maidContractLimit;		// �_�����
	protected long maidAnniversary;			// �_���UID�Ƃ��Ďg�p
	protected int maidDominantArm;			// �����r�A1Byte
	public ResourceLocation textures[][] = new ResourceLocation[][] {
			{null, null},
			{null, null, null, null},
			{null, null, null, null}
	};
	public MMM_TextureBoxBase textureBox[] = new MMM_TextureBoxBase[2];
	public int textureIndex[] = new int[2];
	public Map<String, MMM_EquippedStabilizer> maidStabilizer = new HashMap<String, MMM_EquippedStabilizer>();
	
	
	public LMM_InventoryLittleMaid maidInventory;
	public LMM_EntityLittleMaidAvatar maidAvatar;
	public LMM_EntityCaps maidCaps;	// Client���̂�
	
	public List<LMM_EntityModeBase> maidEntityModeList;
	public Map<Integer, EntityAITasks[]> maidModeList;
	public Map<String, Integer> maidModeIndexList;
	public int maidMode;		// 2Byte
	public int maidColor;		// 1Byte
	public boolean maidTracer;
	public boolean maidFreedom;
	public boolean maidWait;
	public boolean maidContract;
	public int homeWorld;
	protected int maidTiles[][] = new int[9][3];
	public int maidTile[] = new int[3];
	public TileEntity maidTileEntity;
	
	// ���I�ȏ��
	protected EntityPlayer mstatMasterEntity;	// ��
	protected double mstatMasterDistanceSq;		// ��Ƃ̋����A�v�Z�y�ʉ��p
	protected Entity mstatgotcha;				// ���C���[�h�p
	protected boolean mstatBloodsuck;
	protected boolean mstatClockMaid;
	// �}�X�N����
	protected int mstatMaskSelect;
	// �ǉ��̓�������
	protected boolean mstatCamouflage;
	protected boolean mstatPlanter;
//	protected boolean isMaidChaseWait;
	protected int mstatWaitCount;
	protected int mstatTime;
	protected MMM_Counter maidOverDriveTime;
	protected boolean mstatFirstLook;
	protected boolean mstatLookSuger;
	protected MMM_Counter mstatWorkingCount;
	protected int mstatPlayingRole;
	protected int mstatWorkingInt;
	protected String mstatModeName;
	protected boolean mstatOpenInventory;
	// �r�U��
	public LMM_SwingStatus mstatSwingStatus[]; 
	public boolean mstatAimeBow;
	// �����
	private boolean looksWithInterest;
	private boolean looksWithInterestAXIS;
	private float rotateAngleHead;			// Angle
	private float prevRotateAngleHead;		// prevAngle
=======
	//	public static Minecraft mcGame;

	public static final int dataFlags_ForceUpdateInventory = 0x80000000;
	public static final int dataWatch_ColorMode = 19;
	//    public static final int dataWatch_TexArmar	= 21;
	public static final int dataWatch_Flags = 22;

	public static final int dataWatch_Flags_Aimebow = 0x00000004;
	public static final int dataWatch_Flags_Bloodsuck = 0x00000800;
	public static final int dataWatch_Flags_Freedom = 0x00000008;
	public static final int dataWatch_Flags_LooksSugar = 0x00000400;
	public static final int dataWatch_Flags_looksWithInterest = 0x00000001;
	public static final int dataWatch_Flags_looksWithInterestAXIS = 0x00000002;
	public static final int dataWatch_Flags_OverDrive = 0x00001000;
	public static final int dataWatch_Flags_PlayingMode = 0x00000040;
	public static final int dataWatch_Flags_remainsContract = 0x00000020;
	public static final int dataWatch_Flags_Tracer = 0x00000010;
	public static final int dataWatch_Flags_Wait = 0x00000100;
	public static final int dataWatch_Flags_WaitEx = 0x00000200;
	public static final int dataWatch_Flags_Working = 0x00000080;
	public static final int dataWatch_Free = 31;
	public static final int dataWatch_Gotcha = 23;
	public static final int dataWatch_Health = 18;
	public static final int dataWatch_Texture = 20;
	private static final float moveSpeed_Max = 0.3F;
	// TODO:定数は要修正
	private static final float moveSpeed_Nomal = 0.23F;

	private static final float moveSpeed_Overdrive = 1.0F;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java

	public LMM_EntityAIAttackOnCollide aiAttack;
	public LMM_EntityAIAvoidPlayer aiAvoidPlayer;
	public LMM_EntityAIBeg aiBeg;
	public LMM_EntityAIBegMove aiBegMove;
	public EntityAIRestrictOpenDoor aiCloseDoor;
	public LMM_EntityAICollectItem aiCollectItem;
	public LMM_EntityAIFindBlock aiFindBlock;
	public LMM_EntityAIFollowOwner aiFollow;
	public LMM_EntityAIFleeRain aiFreeRain;
	public LMM_EntityAIJumpToMaster aiJumpTo;
	public EntityAIOpenDoor aiOpenDoor;
	public EntityAIPanic aiPanic;
	public Profiler aiProfiler;

	public LMM_EntityAIRestrictRain aiRestrictRain;
	public LMM_EntityAIAttackArrow aiShooting;
	public EntityAISwimming aiSwiming;

	// AI
	public EntityAITempt aiTempt;
	public LMM_EntityAITracerMove aiTracer;
	public LMM_EntityAIWander aiWander;
	/**
	 * 個体ごとに値をバラつかせるのに使う。
	 */
	public float entityIdFactor;
	// 実験用
	private int firstload = 1;
	public int homeWorld;
	// 首周り
	private boolean looksWithInterest;
	private boolean looksWithInterestAXIS;
	// ActiveModeClass
	public LMM_EntityModeBase maidActiveModeClass;
	public long maidAnniversary; // 契約日UIDとして使用

	public LMM_EntityLittleMaidAvatar maidAvatar;
	public boolean maidCamouflage;
	public LMM_EntityCaps maidCaps; // Client側のみ
	public int maidColor; // 1Byte
	public boolean maidContract;
	// 変数減らしたいなぁ
	//    public long maidContractLimit;		// 契約失効日
	public int maidContractLimit; // 契約期間
	// 音声
	//	public LMM_EnumSound maidAttackSound;
	public LMM_EnumSound maidDamegeSound;
	public int maidDominantArm; // 利き腕、1Byte
	public List<LMM_EntityModeBase> maidEntityModeList;
	public boolean maidFreedom;
	public LMM_InventoryLittleMaid maidInventory;
	public int maidMode; // 2Byte
	public Map<String, Integer> maidModeIndexList;
	public Map<Integer, EntityAITasks[]> maidModeList;
	public MMM_Counter maidOverDriveTime;
	public int maidSoundInterval;
	public float maidSoundRate;
	public Map<String, MMM_EquippedStabilizer> maidStabilizer = new HashMap<String, MMM_EquippedStabilizer>();
	public boolean maidTracer;
	public boolean maidWait;
	public boolean mstatAimeBow;
	public boolean mstatBloodsuck;
	// 追加の頭部装備
	public boolean mstatCamouflage;
	public boolean mstatClockMaid;

	public boolean mstatFirstLook;

	public Entity mstatgotcha; // ワイヤード用
	public boolean mstatLookSuger;
	// マスク判定
	public int mstatMaskSelect;

	public double mstatMasterDistanceSq; // 主との距離、計算軽量化用
	// 動的な状態
	public EntityPlayer mstatMasterEntity; // 主
	public String mstatModeName;

	public boolean mstatOpenInventory;
	public boolean mstatPlanter;

	public int mstatPlayingRole;
	// 腕振り
	public LMM_SwingStatus mstatSwingStatus[];
	public int mstatTime;
	//	public boolean isMaidChaseWait;
	public int mstatWaitCount;
	public MMM_Counter mstatWorkingCount;
	public int mstatWorkingInt;
	private float prevRotateAngleHead; // prevAngle
	private float rotateAngleHead; // Angle
	public String statusMessage = "";
	public String textureArmor1[] = new String[4];
	public String textureArmor2[] = new String[4];
	public int textureArmorIndex;
	public String textureArmorName;
	public int textureIndex;
	//	public int prevTextureIndex;
	//	public int prevTextureArmorIndex;
	public MMM_ModelMultiBase textureModel0;
	public MMM_ModelMultiBase textureModel1;
	public MMM_ModelMultiBase textureModel2;
	public String textureName;
	public boolean weaponFullAuto; // 装備がフルオート武器かどうか
	public boolean weaponReload; // 装備がリロードを欲しているかどうか

	public LMM_EntityLittleMaid(World par1World) {
		super(par1World);
		// 初期設定
		maidInventory = new LMM_InventoryLittleMaid(this);
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		if (par1World != null ) {
			maidAvatar = new LMM_EntityLittleMaidAvatar(par1World, this);
=======
		if (par1World != null) {
			maidAvatar = new LMM_EntityLittleMaidAvatar(par1World, MMM_Helper.mc, this);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
		mstatOpenInventory = false;
		//		isMaidChaseWait = false;
		mstatTime = 6000;
		maidOverDriveTime = new MMM_Counter(5, 300, -100);
		mstatWorkingCount = new MMM_Counter(11, 10, -10);
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		
		entityIdFactor = (float)(entityId * 70);
		// �r�U��
		mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus()};
=======

		entityIdFactor = entityId * 70;
		// 腕振り
		mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus() };
		//		maidDominantArm = rand.nextInt(mstatSwingStatus.length);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		setDominantArm(rand.nextInt(mstatSwingStatus.length));

		// 再生音声
		//		maidAttackSound = LMM_EnumSound.attack;
		maidDamegeSound = LMM_EnumSound.hurt;
		maidSoundInterval = 0;
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		
		// �쐶��p�����l�ݒ�
		setEntityHealth(15F);
		
		// �ړ��p�t�B�W�J���ݒ�
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
//		setAIMoveSpeed(moveSpeed_Nomal);
//		setAIMoveSpeed(1.0F);
		
		
		// TODO:����̓e�X�g
//		maidStabilizer.put("HeadTop", MMM_StabilizerManager.getStabilizer("WitchHat", "HeadTop"));
		
		
		
		// �`�Ԍ`����
		MMM_TextureBox ltb[] = new MMM_TextureBox[2];
		maidColor = 12;
		ltb[0] = ltb[1] = MMM_TextureManager.instance.getDefaultTexture(this);
		setTexturePackName(ltb);
//		mod_LMM_littleMaidMob.Debug("ltb[0]%s", ltb[0] == null ? "NULL" : ltb[0].textureName);
//		maidSoundRate = LMM_SoundManager.getSoundRate(textureBox[0].textureName, maidColor);
		
		// ���f�������_�����O�p�̃t���O�l���p�w���p�[�֐�
		maidCaps = new LMM_EntityCaps(this);
		
		// EntityMode�̒ǉ�
=======

		// 野生種用初期値設定
		health = 15;

		// 移動用フィジカル設定
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
		moveSpeed = moveSpeed_Nomal;

		// TODO:これはテスト
		//		maidStabilizer.put("HeadTop", MMM_StabilizerManager.getStabilizer("WitchHat", "HeadTop"));

		// 形態形成場
		textureName = textureArmorName = "default";
		maidColor = 12;
		textureIndex = textureArmorIndex = 0;
		if (MMM_Helper.isClient) {
			LMM_Client.setTextureValue(this);
		}
		// モデルレンダリング用のフラグ獲得用ヘルパー関数
		maidCaps = new LMM_EntityCaps(this);

		maidSoundRate = LMM_SoundManager.getSoundRate(textureName, maidColor);

		// EntityModeの追加
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		maidEntityModeList = LMM_EntityModeManager.getModeList(this);

		// モードリスト
		maidActiveModeClass = null;
		maidModeList = new HashMap<Integer, EntityAITasks[]>();
		maidModeIndexList = new HashMap<String, Integer>();
		initModeList();
		mstatModeName = "";
		maidMode = 65535;
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public EntityLivingData func_110161_a(EntityLivingData par1EntityLivingData) {
		// �e�N�X�`���[�������_���őI��
		String ls;
		if (mod_LMM_littleMaidMob.defaultTexture.isEmpty()) {
			ls = MMM_TextureManager.instance.getRandomTextureString(rand);
		} else {
			ls = mod_LMM_littleMaidMob.defaultTexture;
		}
		textureIndex[0] = textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, ls);
		textureBox[0] = textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		// �쐶�̃��C�h�F�������_���Ŏw��
		maidColor = textureBox[0].getRandomWildColor(rand);
		mod_LMM_littleMaidMob.Debug("init-ID:%d, %s:%d", entityId, textureBox[0].textureName, maidColor);
		setTexturePackIndex(maidColor, textureIndex);
		setMaidMode("Wild");
		return super.func_110161_a(par1EntityLivingData);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		/*
		 * DataWatcher�̓N���C�A���g����T�[�o�[�ւ͒l��n���Ȃ�
		 */
		
		
		// 0, 1, 2, 3, 4, 5,
		// 6: HP
		// 7, 8, 9,
		// 10: �ŗL����
		// 11: ���t����
		// 12: GrowingAge
		// 16: Tame(4), Sit(1) 
		// 17: ownerName
		
		// maidAvater�pEntityPlayer�݊��ϐ�
		// 17 -> 18
		dataWatcher.addObject(18, Float.valueOf(0.0F));

		
		// �Ǝ���
		// 18:HP:����Ȃ��Ȃ���
//		dataWatcher.addObject(dataWatch_Health, new Integer(getMaxHealth()));
		// 19:maidMode(16Bit:LSB)�AmaidColor(8Bit:<<16)�AmaidDominantArm(8Bit:<<24);
		dataWatcher.addObject(dataWatch_ColorMode, new Integer((maidMode & 0xffff) | ((maidColor & 0xff) << 16) | ((maidDominantArm & 0xff) << 24)));
		// 20:�I���e�N�X�`���C���f�b�N�X
		dataWatcher.addObject(dataWatch_Texture, Integer.valueOf(0));
		// 21:�A�[�}�[�e�N�X�`���C���f�b�N�X
//        dataWatcher.addObject(dataWatch_TexArmar, Integer.valueOf(0));
		// 22:��ԑJ�ڃt���O�Q(32Bit)
		// isLookSuger, looksWithInterest, isContract, isBloodsuck, isWorking, isWait
		dataWatcher.addObject(dataWatch_Flags, new Integer(0));
		// 23:GotchaID
		dataWatcher.addObject(dataWatch_Gotcha, new Integer(0));
		
		// TODO:test:30:
		dataWatcher.addObject(30, new Integer(0));
=======
	public void addMaidMode(EntityAITasks[] peaiTasks, String pmodeName, int pmodeIndex) {
		maidModeList.put(pmodeIndex, peaiTasks);
		maidModeIndexList.put(pmodeName, pmodeIndex);
	}

	@Override
	public int applyArmorCalculations(DamageSource par1DamageSource, int par2) {
		return maidAvatar.applyArmorCalculations(par1DamageSource, par2);
	}

	@Override
	public void applyEntityCollision(Entity par1Entity) {
		// 閉所接触回避用
		super.applyEntityCollision(par1Entity);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java

		if (par1Entity instanceof LMM_EntityLittleMaid) {
			if (((LMM_EntityLittleMaid) par1Entity).aiAvoidPlayer.isActive) {
				aiAvoidPlayer.isActive = true;
			}
		} else if (par1Entity == mstatMasterEntity) {
			aiAvoidPlayer.setActive();
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void initModeList() {
		// AI
		aiBeg = new LMM_EntityAIBeg(this, 8F);
		aiBegMove = new LMM_EntityAIBegMove(this, 0.3F);
		aiOpenDoor = new EntityAIOpenDoor(this, true);
		aiCloseDoor = new EntityAIRestrictOpenDoor(this);
		aiAvoidPlayer = new LMM_EntityAIAvoidPlayer(this, 0.3F, 3);
		aiFollow = new LMM_EntityAIFollowOwner(this, 0.3F, 36D, 25D);
		aiAttack = new LMM_EntityAIAttackOnCollide(this, 0.3F, true);
		aiShooting = new LMM_EntityAIAttackArrow(this);
		aiCollectItem = new LMM_EntityAICollectItem(this, 0.3F);
		aiRestrictRain = new LMM_EntityAIRestrictRain(this);
		aiFreeRain = new LMM_EntityAIFleeRain(this, 0.30F);
		aiWander = new LMM_EntityAIWander(this, 0.23F);
		aiJumpTo = new LMM_EntityAIJumpToMaster(this);
		aiFindBlock = new LMM_EntityAIFindBlock(this);
		aiSwiming = new LMM_EntityAISwimming(this);
		aiPanic = new EntityAIPanic(this, 0.38F);
		aiTracer = new LMM_EntityAITracerMove(this);
		aiSit = new LMM_EntityAIWait(this);
				
		// TODO:���ꂢ��Ȃ��ˁH
		aiProfiler = worldObj != null && worldObj.theProfiler != null ? worldObj.theProfiler : null;

		// ���샂�[�h�p��TasksList��������
		EntityAITasks ltasks[] = new EntityAITasks[2];
		ltasks[0] = new EntityAITasks(aiProfiler);
		ltasks[1] = new EntityAITasks(aiProfiler);
		
		// default
		ltasks[0].addTask(1, aiSwiming);
		ltasks[0].addTask(2, aiSit);
		ltasks[0].addTask(3, aiJumpTo);
		ltasks[0].addTask(4, aiFindBlock);
		ltasks[0].addTask(5, aiAttack);
		ltasks[0].addTask(6, aiShooting);
//		ltasks[0].addTask(7, aiPanic);
		ltasks[0].addTask(8, aiBeg);
		ltasks[0].addTask(9, aiBegMove);
		ltasks[0].addTask(10, aiAvoidPlayer);
		ltasks[0].addTask(11, aiFreeRain);
		ltasks[0].addTask(12, aiCollectItem);
		// �ړ��pAI
		ltasks[0].addTask(15, aiTracer);
		ltasks[0].addTask(16, aiFollow);
		ltasks[0].addTask(17, aiWander);
		ltasks[0].addTask(18, new EntityAILeapAtTarget(this, 0.3F));
		// Mutex�̉e�����Ȃ�����s��
		ltasks[0].addTask(20, aiCloseDoor);
		ltasks[0].addTask(21, aiOpenDoor);
		ltasks[0].addTask(22, aiRestrictRain);
		// ��̓����P��
		ltasks[0].addTask(31, new EntityAIWatchClosest(this, net.minecraft.src.EntityLiving.class, 10F));
		ltasks[0].addTask(32, new EntityAILookIdle(this));
=======
	@Override
	public int applyPotionDamageCalculations(DamageSource par1DamageSource, int par2) {
		return maidAvatar.applyPotionDamageCalculations(par1DamageSource, par2);
	}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {

		// 正常時は回復優先処理
		if (health < 10 && !isBloodsuck() && maidInventory.hasItem(Item.sugar.itemID)) {
			return true;
		}

		// 特殊な攻撃処理
		if (isActiveModeClass() && getActiveModeClass().attackEntityAsMob(maidMode, par1Entity)) {
			return true;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		
	
	}


	public void addMaidMode(EntityAITasks[] peaiTasks, String pmodeName, int pmodeIndex) {
		maidModeList.put(pmodeIndex, peaiTasks);
		maidModeIndexList.put(pmodeName, pmodeIndex);
	}


	public int getMaidModeInt() {
		return maidMode;
	}

	public String getMaidModeString() {
		if (!isContract()) {
			return getMaidModeString(maidMode);
		} else if (!isRemainsContract()) {
			return "Strike";
		} else if (isMaidWait()) {
			return "Wait";
		} else if (isPlaying()) {
			return "Playing";
		} else {
			String ls = getMaidModeString(maidMode);
			if (maidOverDriveTime.isEnable()) {
				ls = "D-" + ls;
			} else
			if (maidTracer) {
				ls = "T-" + ls;
			} else
			if (maidFreedom) {
				ls = "F-" + ls;
=======

		// 標準処理
		setSwing(20, isBloodsuck() ? LMM_EnumSound.attack_bloodsuck : LMM_EnumSound.attack);
		maidAvatar.attackTargetEntityWithCurrentItem(par1Entity);
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		Entity entity = par1DamageSource.getEntity();

		// ダメージソースを特定して音声の設定
		maidDamegeSound = LMM_EnumSound.hurt;
		if (par1DamageSource == DamageSource.inFire || par1DamageSource == DamageSource.onFire
				|| par1DamageSource == DamageSource.lava) {
			maidDamegeSound = LMM_EnumSound.hurt_fire;
		}
		for (LMM_EntityModeBase lm : maidEntityModeList) {
			int li = lm.attackEntityFrom(par1DamageSource, par2);
			if (li > 0) {
				return li == 1 ? false : true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
			}
		}

		setMaidWait(false);
		setMaidWaitCount(0);
		if (par2 > 0) {
			// 遊びは終わりだ！
			setPlayingRole(0);
			getNextEquipItem();
		}
		// ゲーム難易度によるダメージの補正
		if (isMaidContract() && (entity instanceof EntityLiving) || (entity instanceof EntityArrow)) {
			if (worldObj.difficultySetting == 0) {
				par2 = 0;
			}
			if (worldObj.difficultySetting == 1 && par2 > 0) {
				par2 = par2 / 2 + 1;
			}
			if (worldObj.difficultySetting == 3) {
				par2 = (par2 * 3) / 2;
			}
		}

		//		if (par2 == 0 && maidMode != mmode_Detonator) {
		if (par2 == 0) {
			// ノーダメージ
			if (maidDamegeSound == LMM_EnumSound.hurt) {
				maidDamegeSound = LMM_EnumSound.hurt_nodamege;
			}
			playSound(maidDamegeSound, true);
			return false;
		}

		if (super.attackEntityFrom(par1DamageSource, par2)) {
			//契約者の名前チェックはマルチ用
			if (isMaidContract() && entity != null) {
				if (getIFF(entity) && !isPlaying()) {
					fleeingTick = 0;
					return true;
				}
			} else if (maidInventory.getCurrentItem() == null) {
				return true;
			}
			fleeingTick = 0;
			//            entityToAttack = entity;
			/*
			if (entity != null) {
			    setPathToEntity(worldObj.getPathEntityToEntity(this, entityToAttack, 16F, true, false, false, true));
			}
			if (maidMode == mmode_Healer && entity instanceof EntityLiving) {
				// ヒーラーは薬剤で攻撃
				maidInventory.currentItem = maidInventory.getInventorySlotContainItemPotion(true, 0, ((EntityLiving)entity).isEntityUndead() & isMaskedMaid);
			}
			*/
			return true;
		} else {
			return false;
		}

		//		return maidAvatar.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public boolean canAttackClass(Class par1Class) {
		// TODO: IFFの設定、クラス毎の判定しかできないので使わない。
		return true;
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java

	public boolean setMaidMode(int pindex, boolean pplaying) {
		// ���[�h�ɉ�����AI��؂�ւ���
		velocityChanged = true;
		if (!maidModeList.containsKey(pindex)) return false;
		if (maidMode == pindex) return true;
		
		if (pplaying) {
			
		} else {
			mstatWorkingInt = pindex;
		}
		mstatModeName = getMaidModeString(pindex);
		maidMode = pindex;
		setMaidColorMode();
		EntityAITasks[] ltasks = maidModeList.get(pindex);
		
		// AI�����ꂩ�珑��������
		if (ltasks.length > 0 && ltasks[0] != null) {
			setMaidModeAITasks(ltasks[0], tasks);
=======
	@Override
	public boolean canAttackWithItem() {
		if (ridingEntity != null && ridingEntity == mstatMasterEntity) {
			return false;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		} else {
			return super.canAttackWithItem();
		}
	}

	// おんぶおばけは無敵
	@Override
	public boolean canBeCollidedWith() {
		if (ridingEntity != null && ridingEntity == mstatMasterEntity) {
			ItemStack litemstack = mstatMasterEntity.getCurrentEquippedItem();
			return (litemstack == null) || (litemstack.itemID == Item.saddle.itemID);
		} else {
			return super.canBeCollidedWith();
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		// ���[�h�ؑւɉ����������n���m��
		setSitting(false);
		setSneaking(false);
		setActiveModeClass(null);
		aiJumpTo.setEnable(true);
//		aiFollow.setEnable(true);
		aiAttack.setEnable(true);
		aiShooting.setEnable(false);
		aiAvoidPlayer.setEnable(true);
//		aiWander.setEnable(maidFreedom);
		setBloodsuck(false);
		clearTilePosAll();
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			LMM_EntityModeBase iem = maidEntityModeList.get(li); 
			if (iem.setMode(maidMode)) {
				setActiveModeClass(iem);
				aiFollow.minDist = iem.getRangeToMaster(0);
				aiFollow.maxDist = iem.getRangeToMaster(1);
				break;
=======
	@Override
	public boolean canDespawn() {
		// デスポーン判定
		return mod_LMM_littleMaidMob.canDespawn || super.canDespawn();
	}

	public void checkClockMaid() {
		// 時計を持っているか？
		mstatClockMaid = maidInventory.getInventorySlotContainItem(Item.pocketSundial.itemID) > -1;
	}

	public void checkHeadMount() {
		// 追加の頭部装備の判定
		ItemStack lis = maidInventory.getHeadMount();
		mstatPlanter = false;
		mstatCamouflage = false;
		if (lis != null) {
			if (lis.getItem() instanceof ItemBlock) {
				Block lblock = Block.blocksList[lis.getItem().itemID];
				mstatPlanter = (lblock instanceof BlockFlower) && lblock.getRenderType() == 1;
				mstatCamouflage = (lblock instanceof BlockLeaves) || (lblock instanceof BlockPumpkin);
			} else if (lis.getItem() instanceof ItemSkull) {
				mstatCamouflage = true;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
			}
		}
	}

	public void checkMaskedMaid() {
		// インベントリにヘルムがあるか？
		for (int i = maidInventory.mainInventory.length - 1; i >= 0; i--) {
			ItemStack is = maidInventory.getStackInSlot(i);
			if (is != null && is.getItem() instanceof ItemArmor && ((ItemArmor) is.getItem()).armorType == 0) {
				// ヘルムを持ってる
				mstatMaskSelect = i;
				maidInventory.armorInventory[3] = is;
				LMM_Client.setArmorTextureValue(this);
				return;
			}
		}

		mstatMaskSelect = -1;
		maidInventory.armorInventory[3] = null;
		return;
	}

	public int colorMultiplier(float pLight, float pPartialTicks) {
		// 発光処理用
		int lbase = 0;
		if (maidOverDriveTime.isDelay()) {
			int i;
			if (maidOverDriveTime.isEnable()) {
				i = 100;
			} else {
				i = 100 + maidOverDriveTime.getValue();
			}
			lbase = i << 24 | 0x00df0f0f;
		}

		if (isActiveModeClass()) {
			lbase = lbase | getActiveModeClass().colorMultiplier(pLight, pPartialTicks);
		}

		return lbase;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		// お子さんの設定
		return null;
	}

	@Override
	public void damageArmor(int i) {
		maidAvatar.damageArmor(i);
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	protected String getLivingSound() {
		// ���i�̐�
		LMM_EnumSound so = LMM_EnumSound.Null;
		if (func_110143_aJ() < 10)
			so = LMM_EnumSound.living_whine;
		else if (rand.nextFloat() < maidSoundRate) {
			if (mstatTime > 23500 || mstatTime < 1500) {
				so = LMM_EnumSound.living_morning;
			} else if (mstatTime < 12500) {
				if (isContract()) {
					BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(MathHelper.floor_double(posX + 0.5D), MathHelper.floor_double(posZ + 0.5D));
					float ltemp = biomegenbase.getFloatTemperature();
					if (ltemp <= 0.15F) {
						so = LMM_EnumSound.living_cold;
					} else if (ltemp > 1.0F) {
						so = LMM_EnumSound.living_hot;
					} else {
						so = LMM_EnumSound.living_daytime;
					}
					if (worldObj.isRaining()) {
						if (biomegenbase.canSpawnLightningBolt()) {
							so = LMM_EnumSound.living_rain;
						} else if (biomegenbase.getEnableSnow()) {
							so = LMM_EnumSound.living_snow;
						}
					}
				} else {
					so = LMM_EnumSound.living_daytime;
				}
			} else {
				so = LMM_EnumSound.living_night;
			}
		}
		
		mod_LMM_littleMaidMob.Debug("id:%d LivingSound:%s", entityId, worldObj == null ? "null" : worldObj.isRemote ? "Client" : "Server");
		playLittleMaidSound(so, false);
		return null;
	}

	/**
	 * �ȈՉ����Đ��A�W���̉����̂ݎg�p���邱�ƁB
	 */
	public void playSound(String pname) {
		playSound(pname, 0.5F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
=======
	public void damageEntity(DamageSource par1DamageSource, int par2) {
		// ダメージソースに応じて音声変更
		if (par1DamageSource == DamageSource.fall) {
			maidDamegeSound = LMM_EnumSound.hurt_fall;
		}
		if (!par1DamageSource.isUnblockable() && isBlocking()) {
			// ブロッキング
			mod_LMM_littleMaidMob.Debug(String.format("Blocking success ID:%d, %d", entityId, par2));
			maidDamegeSound = LMM_EnumSound.hurt_guard;
		}

		// 被ダメ
		maidAvatar.health = health;
		if (par2 > 0 && getActiveModeClass() != null
				&& !getActiveModeClass().damageEntity(maidMode, par1DamageSource, par2)) {
			maidAvatar.damageEntity(par1DamageSource, par2);

			// ダメージを受けると待機を解除
			setMaidWait(false);
		}

		if (health == maidAvatar.health && maidDamegeSound == LMM_EnumSound.hurt) {
			maidDamegeSound = LMM_EnumSound.hurt_nodamege;
		}
		mod_LMM_littleMaidMob.Debug(String.format("GetDamage ID:%d, %s, %d/ %d", entityId, par1DamageSource.damageType,
				health - maidAvatar.health, par2));
		health = maidAvatar.health;
		//		super.damageEntity(par1DamageSource, par2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	/**
	 * 手持ちアイテムの破壊
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void playSound(LMM_EnumSound enumsound, boolean force) {
		if ((maidSoundInterval > 0 && !force) || enumsound == LMM_EnumSound.Null) return;
		maidSoundInterval = 20;
		if (worldObj.isRemote) {
			// Client
//			String lsound = LMM_SoundManager.getSoundValue(enumsound, textureName, maidColor & 0x00ff);
//			float lpitch = mod_LMM_littleMaidMob.VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
//			worldObj.playSound(posX, posY, posZ, lsound, getSoundVolume(), lpitch, false);
		} else {
			// Server
			mod_LMM_littleMaidMob.Debug("id:%d-%s, seps:%04x-%s", entityId, worldObj.isRemote ? "Client" : "Server",  enumsound.index, enumsound.name());
			byte[] lbuf = new byte[] {
					LMM_Statics.LMN_Client_PlaySound,
					0, 0, 0, 0,
					0, 0, 0, 0
			};
			MMM_Helper.setInt(lbuf, 5, enumsound.index);
			LMM_Net.sendToAllEClient(this, lbuf);
		}
=======
	public void destroyCurrentEquippedItem() {
		maidInventory.setInventoryCurrentSlotContents(null);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	/**
	 * メイドインベントリを開く
	 * @param pEntityPlayer
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force) {
		// �����̍Đ�
		if ((maidSoundInterval > 0 && !force) || enumsound == LMM_EnumSound.Null) return;
		maidSoundInterval = 20;
		if (worldObj.isRemote) {
			// Client
			String s = LMM_SoundManager.getSoundValue(enumsound, textureBox[0].textureName, maidColor & 0x00ff);
			mod_LMM_littleMaidMob.Debug(String.format("id:%d, se:%04x-%s (%s)", entityId, enumsound.index, enumsound.name(), s));
			float lpitch = mod_LMM_littleMaidMob.VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
			worldObj.playSound(posX, posY, posZ, s, getSoundVolume(), lpitch, false);
=======
	public void displayGUIMaidInventory(EntityPlayer pEntityPlayer) {
		if (!worldObj.isRemote) {
			// server
			Container lcontainer = new LMM_ContainerInventory(pEntityPlayer.inventory, maidInventory);
			ModLoader.serverOpenWindow((EntityPlayerMP) pEntityPlayer, lcontainer, mod_LMM_littleMaidMob.containerID,
					entityId, 0, 0);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void onKillEntity(EntityLivingBase par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
		if (isBloodsuck()) {
//			mod_LMM_littleMaidMob.Debug("nice Kill.");
			playSound(LMM_EnumSound.laughter, true);
		} else {
			setTarget(null);
			setAttackTarget(null);
=======
	public void dropFewItems(boolean par1, int par2) {
		// メイドさんはお砂糖とココアと不定形の何かでできてるの！
		int k = rand.nextInt(3 + par2);
		for (int j = 0; j <= k; j++) {
			if (rand.nextInt(30) == 0) {
				dropItem(Item.slimeBall.itemID, 1);
			}
			if (rand.nextInt(50) == 0) {
				entityDropItem(new ItemStack(Item.dyePowder.itemID, 1, 3), 0F);
			}
			dropItem(Item.sugar.itemID, 1);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

		// インベントリをブチマケロ！
		maidInventory.dropAllItems();
	}

	/**
	 * ペロッ・・・これは・・・砂糖ッ！！
	 * motion : 腕を振るか？
	 * recontract : 契約延長効果アリ？
	 */
	public void eatSugar(boolean motion, boolean recontract) {
		if (motion) {
			setSwing(2, (health == 19 && !isBurning()) ? LMM_EnumSound.eatSugar_MaxPower : LMM_EnumSound.eatSugar);
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		int lx = MathHelper.floor_double(this.posX);
		int ly = MathHelper.floor_double(this.boundingBox.minY);
		int lz = MathHelper.floor_double(this.posZ);
		/*
		// TODO:�T�[�o�[���Ŕ���ł��Ȃ��̂ňӖ��Ȃ�?
		MMM_TextureBox lbox = MMM_TextureManager.instance.getTextureBox(textureBox[0]);
		if (worldObj == null || textureModel == null  
				|| !textureBox[0].mo.getCanSpawnHere(worldObj, lx, ly, lz, this)) {
			mod_LMM_littleMaidMob.Debug(String.format("%s is can't spawn hear.", textureName));
			return false;
		}
		*/
		if (mod_LMM_littleMaidMob.Dominant) {
			// �h�~�i���g
			return this.worldObj.checkNoEntityCollision(this.boundingBox) 
					&& this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() 
					&& !this.worldObj.isAnyLiquid(this.boundingBox)
					&& this.getBlockPathWeight(lx, ly, lz) >= 0.0F;
		} else {
			return super.getCanSpawnHere();
=======
		int h = hurtResistantTime;
		heal(1);
		hurtResistantTime = h;
		playSound("random.pop");
		mod_LMM_littleMaidMob.Debug(("eat Suger." + worldObj.isRemote));

		if (recontract) {
			// 契約期間の延長
			maidContractLimit += 24000;
			if (maidContractLimit > 168000) {
				maidContractLimit = 168000; // 24000 * 7
			}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

		// 暫定処理
		if (maidAvatar != null) {
			maidAvatar.foodStats.addStats(20, 20F);
		}
	}

	@Override
	public void entityInit() {
		super.entityInit();
		/*
		 * DataWatcherはクライアントからサーバーへは値を渡さない
		 */

		// 0, 1, 2, 3, 4, 5, 6, 8, 12,
		// 16: Tame(4), Sit(1)
		// 17:ownerName

		// 独自分
		// 18:HP
		dataWatcher.addObject(dataWatch_Health, new Integer(getMaxHealth()));
		// 19:maidMode(16Bit:LSB)、maidColor(8Bit:<<16)、maidDominantArm(8Bit:<<24);
		dataWatcher.addObject(dataWatch_ColorMode, new Integer((maidMode & 0xffff) | ((maidColor & 0xff) << 16)
				| ((maidDominantArm & 0xff) << 24)));
		// 20:選択テクスチャインデックス
		dataWatcher.addObject(dataWatch_Texture, Integer.valueOf(0));
		// 21:アーマーテクスチャインデックス
		//        dataWatcher.addObject(dataWatch_TexArmar, Integer.valueOf(0));
		// 22:状態遷移フラグ群(32Bit)
		// isLookSuger, looksWithInterest, isContract, isBloodsuck, isWorking, isWait
		dataWatcher.addObject(dataWatch_Flags, new Integer(0));
		// 23:GotchaID
		dataWatcher.addObject(dataWatch_Gotcha, new Integer(0));

		// TODO:test:30:
		dataWatcher.addObject(30, new Integer(0));

		// 31:自由変数、EntityMode等で使用可能な変数。
		dataWatcher.addObject(dataWatch_Free, new Integer(0));

	}

	/**
	 * 適用されているモードクラス
	 */
	public LMM_EntityModeBase getActiveModeClass() {
		return maidActiveModeClass;
	}

	@Override
	public boolean getCanSpawnHere() {
		// スポーン可能か？
		if (mod_LMM_littleMaidMob.spawnLimit <= getMaidCount()) {
			mod_LMM_littleMaidMob.Debug("Spawn Limit.");
			return false;
		}
		int lx = MathHelper.floor_double(posX);
		int ly = MathHelper.floor_double(boundingBox.minY);
		int lz = MathHelper.floor_double(posZ);
		/*
			// TODO:サーバー側で判定できないので意味なし
		    	if (worldObj == null || textureModel == null
		    			|| !textureModel[0].getCanSpawnHere(worldObj, lx, ly, lz, this)) {
		    		mod_LMM_littleMaidMob.Debug(String.format("%s is can't spawn hear.", textureName));
		    		return false;
		    	}
		*/
		if (mod_LMM_littleMaidMob.Dominant) {
			// ドミナント
			return worldObj.checkNoEntityCollision(boundingBox)
					&& worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
					&& !worldObj.isAnyLiquid(boundingBox)
					&& getBlockPathWeight(lx, ly, lz) >= 0.0F;
		} else {
			return super.getCanSpawnHere();
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	// �G�t�F�N�g�\��
	protected void showParticleFX(String s) {
		showParticleFX(s, 1D, 1D, 1D);
=======
	public float getContractLimitDays() {
		return maidContractLimit > 0 ? (maidContractLimit / 24000F) : -1F;
	}

	@Override
	public ItemStack getCurrentArmor(int par1) {
		return maidInventory.armorItemInSlot(par1);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	/**
	 * 現在の装備品
	 */
	public ItemStack getCurrentEquippedItem() {
		return maidInventory.getCurrentItem();
	}

	@Override
	public ItemStack getCurrentItemOrArmor(int par1) {
		if (par1 == 0) {
			return getHeldItem();
		} else if (par1 < 5) {
			return maidInventory.armorItemInSlot(par1 - 1);
		} else {
			return maidInventory.getStackInSlot(par1 - 5);
		}
	}

	@Override
	public String getDeathSound() {
		playSound(LMM_EnumSound.death, true);
		return null;
	}

	@Override
	public int getDropItemId() {
		return Item.sugar.itemID;
	}

	@Override
	public int getExperiencePoints(EntityPlayer par1EntityPlayer) {
		return experienceValue;
	}

	@Override
	public ItemStack getHeldItem() {
		return maidInventory.getCurrentItem();
	}

	// 効果音の設定
	@Override
	public String getHurtSound() {
		playSound(maidDamegeSound, true);
		return null;
	}

	/**
	 * 敵味方識別
	 */
	public boolean getIFF(Entity pEntity) {
		// 敵味方識別(敵=false)
		if (pEntity == null || pEntity == mstatMasterEntity) {
			return true;
		}

		int tt = LMM_IFF.getIFF(getMaidMaster(), pEntity);
		switch (tt) {
		case LMM_IFF.iff_Enemy:
			return false;
		case LMM_IFF.iff_Friendry:
			return true;
		case LMM_IFF.iff_Unknown:
			if (isBloodsuck()) {
				// 血に餓えている時は敵
				return false;
			}
			if (pEntity instanceof LMM_EntityLittleMaid) {
				// お遊びモードのメイドには敵対しない
				if (((LMM_EntityLittleMaid) pEntity).mstatPlayingRole > LMM_EntityMode_Playing.mpr_NULL) {
					return true;
				}
			}
			if (pEntity instanceof EntityCreature) {
				// 相手が何をターゲットにしているかで決まる
				Entity et = ((EntityCreature) pEntity).getEntityToAttack();
				if (et != null && et == mstatMasterEntity) {
					return false;
				}
				if (et == this) {
					return false;
				}
				if (et instanceof LMM_EntityLittleMaid) {
					// 同じマスターのメイドを攻撃対象としている
					if (((LMM_EntityLittleMaid) et).getMaidMasterEntity() == mstatMasterEntity) {
						return false;
					}
				}
			}
			return true;

		default:
			return false;
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public boolean canAttackClass(Class par1Class) {
		// IFF�̐ݒ�A�N���X���̔��肵���ł��Ȃ��̂Ŏg��Ȃ��B
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		
		// ���펞�͉񕜗D�揈��
		if (func_110143_aJ() < 10 && !isBloodsuck() && maidInventory.hasItem(Item.sugar.itemID)) {
			return true;
		}
		
		// ����ȍU������
		if (isActiveModeClass() && getActiveModeClass().attackEntityAsMob(maidMode, par1Entity)) {
			return true;
=======
	public float getInterestedAngle(float f) {
		return (prevRotateAngleHead + (rotateAngleHead - prevRotateAngleHead) * f)
				* ((looksWithInterestAXIS ? 0.08F : -0.08F) * (float) Math.PI);
	}

	@Override
	public Icon getItemIcon(ItemStack par1ItemStack, int par2) {
		// アイテムの表示
		if (maidAvatar != null) {
			return maidAvatar.getItemIcon(par1ItemStack, par2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		// ���D�݂͉��H
		if (isContractEX()) {
			return par1ItemStack.itemID == Item.sugar.itemID;
=======
		if (par1ItemStack.getItem().requiresMultipleRenderPasses()) {
			return par1ItemStack.getItem().getIconFromDamageForRenderPass(par1ItemStack.getItemDamage(), par2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		} else {
			return super.getItemIcon(par1ItemStack, par2);
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		// �f�[�^�Z�[�u
		super.writeEntityToNBT(par1nbtTagCompound);
		
		par1nbtTagCompound.setTag("Inventory", maidInventory.writeToNBT(new NBTTagList()));
		par1nbtTagCompound.setString("Mode", getMaidModeString(mstatWorkingInt));
		par1nbtTagCompound.setBoolean("Wait", isMaidWait());
		par1nbtTagCompound.setBoolean("Freedom", isFreedom());
		par1nbtTagCompound.setBoolean("Tracer", isTracer());
		par1nbtTagCompound.setInteger("LimitCount", maidContractLimit);
		par1nbtTagCompound.setLong("Anniversary", maidAnniversary);
		par1nbtTagCompound.setInteger("EXP", experienceValue);
		par1nbtTagCompound.setInteger("DominantArm", maidDominantArm);
		par1nbtTagCompound.setInteger("Color", maidColor);
		par1nbtTagCompound.setString("texName", textureBox[0].textureName);
		par1nbtTagCompound.setString("texArmor", textureBox[1].textureName);
		// HomePosition
		par1nbtTagCompound.setInteger("homeX", func_110172_bL().posX);
		par1nbtTagCompound.setInteger("homeY", func_110172_bL().posY);
		par1nbtTagCompound.setInteger("homeZ", func_110172_bL().posZ);
//		par1nbtTagCompound.setInteger("homeX", getHomePosition().posX);
//		par1nbtTagCompound.setInteger("homeY", getHomePosition().posY);
//		par1nbtTagCompound.setInteger("homeZ", getHomePosition().posZ);
		par1nbtTagCompound.setInteger("homeWorld", homeWorld);
		// Tiles
		NBTTagCompound lnbt = new NBTTagCompound();
		par1nbtTagCompound.setTag("Tiles", lnbt);
		for (int li = 0; li < maidTiles.length; li++) {
			if (maidTiles[li] != null) {
				lnbt.setIntArray(String.valueOf(li), maidTiles[li]);
			}
		}
		// �ǉ���
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			maidEntityModeList.get(li).writeEntityToNBT(par1nbtTagCompound);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		// �f�[�^���[�h
		super.readEntityFromNBT(par1nbtTagCompound);
		
		if (par1nbtTagCompound.hasKey("ModeColor")) {
			// ���ł���̌p��
	        String s = par1nbtTagCompound.getString("Master");
	        if(s.length() > 0) {
	        	setOwner(s);
	            setContract(true);
	        }
	        NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory");
	        maidInventory.readFromNBT(nbttaglist);
	        // �A�[�}�[�X���b�g�ύX�ɑΉ����邽�߂̃R�[�h
	        ItemStack[] armi = new ItemStack[4];
	        for (int i = 0; i < 4; i++) {
	        	ItemStack is = maidInventory.armorItemInSlot(i);
	        	if (is != null) {
	            	armi[3 - ((ItemArmor)is.getItem()).armorType] = is; 
	        	}
	        }
	        maidInventory.armorInventory = armi; 
	        //
	        setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
	        setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
	        setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
			textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
			textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
			textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[1]);
			byte b = par1nbtTagCompound.getByte("ModeColor");
			setColor(b & 0x0f);
	        switch ((b & 0xf0) >> 4) {
	        case 0:
	        	setMaidMode(0x0000);	// Wild
	        	break;
	        case 2:
	        	setMaidMode(0x0001);	// Escorter
	        	break;
	        case 4:
	        	setMaidMode(0x0080);	// Fencer
	        	break;
	        case 5:
	        	setMaidMode(0x0000);	// Healer
	        	break;
	        case 6:
	        	setMaidMode(0x0021);	// Cooking
	        	break;
	        case 7:
	        	setMaidMode(0x00c0);	// Bloodsucker
	        	break;
	        case 8:
	        	setMaidMode(0x0083);	// Archer
	        	break;
	        case 9:
	        	setMaidMode(0x00c3);	// Blazingstar
	        	break;
	        case 10:
	        	setMaidMode(0x0081);	// Ripper
	        	break;
	        case 11:
	        	setMaidMode(0x00c2);	// Detonator
	        	break;
	        case 12:
	        	setMaidMode(0x00c1);	// TNT-D
	        	break;
	        case 13:
	        	setMaidMode(0x0020);	// Torcher
	        	break;
	        case 15:
	        	setMaidMode(0x0000);	// Pharmacist
	        	break;
	        default :
	        	setMaidMode(0x0000);	// Wild
	        }
//	        setMaidMode((b & 0xf0) >> 4);
	        int lhx = 0;
	        int lhy = 0;
	        int lhz = 0;
	        NBTTagList nbttaglist1 = par1nbtTagCompound.getTagList("HomePosI");
	        if (nbttaglist1.tagCount() > 0) {
	        	lhx = ((NBTTagInt)nbttaglist1.tagAt(0)).data;
	        	lhy = ((NBTTagInt)nbttaglist1.tagAt(1)).data;
	        	lhz = ((NBTTagInt)nbttaglist1.tagAt(2)).data;
	        } else {
	        	lhx = MathHelper.floor_double(posX);
	        	lhy = MathHelper.floor_double(posY);
	        	lhz = MathHelper.floor_double(posZ);
	        }
	        func_110172_bL().set(lhx, lhy, lhz);
//			getHomePosition().set(lhx, lhy, lhz);
			long lcl = par1nbtTagCompound.getLong("Limit");
			if (isContract() && lcl == 0) {
				maidContractLimit = 24000;
			} else {
				maidContractLimit = (int)((lcl - worldObj.getWorldTime()));
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isContract()) {
				// �_�~�[�̐��l������
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}
			
		} else {
			// �V�^
			mod_LMM_littleMaidMob.Debug("read." + worldObj.isRemote);
			
			maidInventory.readFromNBT(par1nbtTagCompound.getTagList("Inventory"));
			setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
			setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
			setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			setMaidMode(par1nbtTagCompound.getString("Mode"));
			if (par1nbtTagCompound.hasKey("LimitCount")) {
				maidContractLimit = par1nbtTagCompound.getInteger("LimitCount");
			} else {
				long lcl = par1nbtTagCompound.getLong("Limit");
				if (isContract() && lcl == 0) {
					maidContractLimit = 24000;
=======
	public ItemStack[] getLastActiveItems() {
		return maidInventory.armorInventory;
	}

	@Override
	public String getLivingSound() {
		// 普段の声
		LMM_EnumSound so = LMM_EnumSound.Null;
		if (health < 10) {
			so = LMM_EnumSound.living_whine;
		} else if (rand.nextFloat() < maidSoundRate) {
			if (mstatTime > 23500 || mstatTime < 1500) {
				so = LMM_EnumSound.living_morning;
			} else if (mstatTime < 12500) {
				if (isMaidContract()) {
					BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(MathHelper.floor_double(posX + 0.5D),
							MathHelper.floor_double(posZ + 0.5D));
					float ltemp = biomegenbase.getFloatTemperature();
					if (ltemp <= 0.15F) {
						so = LMM_EnumSound.living_cold;
					} else if (ltemp > 1.0F) {
						so = LMM_EnumSound.living_hot;
					} else {
						so = LMM_EnumSound.living_daytime;
					}
					if (worldObj.isRaining()) {
						if (biomegenbase.canSpawnLightningBolt()) {
							so = LMM_EnumSound.living_rain;
						} else if (biomegenbase.getEnableSnow()) {
							so = LMM_EnumSound.living_snow;
						}
					}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
				} else {
					so = LMM_EnumSound.living_daytime;
				}
			} else {
				so = LMM_EnumSound.living_night;
			}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
			if (isContract() && maidContractLimit == 0) {
				// �l�������������͂P����
//	        	maidContractLimit = worldObj.getWorldTime() + 24000L;
				maidContractLimit = 24000;
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isContract()) {
				// �_�~�[�̐��l������
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}
			if (maidAvatar != null) {
				maidAvatar.experienceTotal = par1nbtTagCompound.getInteger("EXP");
			}
			setDominantArm(par1nbtTagCompound.getInteger("DominantArm"));
			if (mstatSwingStatus.length <= maidDominantArm) {
				maidDominantArm = 0;
			}
			textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
			textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
			textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
			textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[1]);
			setTexturePackIndex(par1nbtTagCompound.getInteger("Color"), textureIndex);
			
			// HomePosition
			int lhx = par1nbtTagCompound.getInteger("homeX");
			int lhy = par1nbtTagCompound.getInteger("homeY");
			int lhz = par1nbtTagCompound.getInteger("homeZ");
			func_110172_bL().set(lhx, lhy, lhz);
//			getHomePosition().set(lhx, lhy, lhz);
			homeWorld = par1nbtTagCompound.getInteger("homeWorld");
			
			// Tiles
			NBTTagCompound lnbt = par1nbtTagCompound.getCompoundTag("Tiles");
			for (int li = 0; li < maidTiles.length; li++) {
				int ltile[] = lnbt.getIntArray(String.valueOf(li));
				maidTiles[li] = ltile.length > 0 ? ltile : null;
			}
			
			// �e�X�g�p
			if (worldObj.isRemote) {
//	        	setOwner(ModLoader.getMinecraftInstance().thePlayer.username);
			}
			
			// �ǉ���
			for (int li = 0; li < maidEntityModeList.size(); li++) {
				maidEntityModeList.get(li).readEntityFromNBT(par1nbtTagCompound);
			}
		}
		onInventoryChanged();
		
		// �h�b�y���΍�
		if (mod_LMM_littleMaidMob.antiDoppelganger && maidAnniversary > 0L) {
			for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
				Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
				if (!entity1.isDead && entity1 instanceof LMM_EntityLittleMaid) {
					LMM_EntityLittleMaid elm = (LMM_EntityLittleMaid)entity1;
					if (elm != this && elm.isContract() && elm.maidAnniversary == maidAnniversary
							&& elm.getMaidMaster().equalsIgnoreCase(getMaidMaster())) {
						// �V���������c��
						if (entityId > elm.entityId) {
							mod_LMM_littleMaidMob.Debug(String.format("Load Doppelganger ID:%d, %d" ,elm.entityId, maidAnniversary));
							elm.setDead();
						} else {
							mod_LMM_littleMaidMob.Debug(String.format("Load Doppelganger ID:%d, %d" ,entityId, maidAnniversary));
							setDead();
							break;
						}
					}
=======
		}

		playLittleMaidSound(so, false);
		return null;
	}

	public boolean getLooksWithInterest() {
		looksWithInterest = (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_looksWithInterest) > 0;
		looksWithInterestAXIS = (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_looksWithInterestAXIS) > 0;

		return looksWithInterest;
	}

	public int getMaidColor() {
		return (dataWatcher.getWatchableObjectInt(dataWatch_ColorMode) >>> 16) & 0xff;
	}

	/**
	 * 読み込み領域内のメイドさんの数
	 */
	public int getMaidCount() {
		int lj = 0;
		for (int li = 0; li < worldObj.loadedEntityList.size(); li++) {
			if (worldObj.loadedEntityList.get(li) instanceof LMM_EntityLittleMaid) {
				lj++;
			}
		}
		return lj;
	}

	/**
	 * 指定されたフラグを獲得
	 */
	public boolean getMaidFlags(int pFlagvalue) {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & pFlagvalue) > 0;
	}

	public String getMaidMaster() {
		return getOwnerName();
	}

	public EntityPlayer getMaidMasterEntity() {
		// 主を獲得
		if (isMaidContract()) {
			EntityPlayer entityplayer = mstatMasterEntity;
			if (mstatMasterEntity == null || mstatMasterEntity.isDead) {
				String lname;
				// サーバー側ならちゃんとオーナ判定する
				if (!MMM_Helper.isClient
						|| mod_LMM_littleMaidMob.checkOwnerName
						|| MMM_Helper.mc.thePlayer == null) {
					lname = getMaidMaster();
				} else {
					lname = MMM_Helper.mc.thePlayer.username;
				}
				entityplayer = worldObj.getPlayerEntityByName(lname);
				// とりあえず主の名前を入れてみる
				maidAvatar.username = lname;

				if (entityplayer != null && maidAvatar != null) {
					maidAvatar.capabilities.isCreativeMode = entityplayer.capabilities.isCreativeMode;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
				}

			}
			return entityplayer;
		} else {
			return null;
		}
	}

	public int getMaidModeInt() {
		return maidMode;
	}

	public String getMaidModeString() {
		if (!isMaidContract()) {
			return getMaidModeString(maidMode);
		} else if (!isRemainsContract()) {
			return "Strike";
		} else if (isMaidWait()) {
			return "Wait";
		} else if (isPlaying()) {
			return "Playing";
		} else {
			String ls = getMaidModeString(maidMode);
			if (maidOverDriveTime.isEnable()) {
				ls = "D-" + ls;
			} else if (maidTracer) {
				ls = "T-" + ls;
			} else if (maidFreedom) {
				ls = "F-" + ls;
			}
			return ls;
		}
	}

	public String getMaidModeString(int pindex) {
		// モード名称の獲得
		String ls = "";
		for (Entry<String, Integer> le : maidModeIndexList.entrySet()) {
			if (le.getValue() == pindex) {
				ls = le.getKey();
				break;
			}
		}
		return ls;
	}

	@Override
	public int getMaxHealth() {
		// 最大HP
		return 20;
	}

	/**
	 * インベントリにある次の装備品を選択
	 */
	public boolean getNextEquipItem() {
		if (worldObj.isRemote) {
			// クライアント側は処理しない
			return false;
		}

		int li;
		if (isActiveModeClass()) {
			li = getActiveModeClass().getNextEquipItem(maidMode);
		} else {
			li = -1;
		}
		setEquipItem(maidDominantArm, li);
		return li > -1;
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public double getMountedYOffset() {
		// TODO:�����͗v����
		if (riddenByEntity instanceof EntityChicken) {
			return height + 0.03D;
		}
		if (riddenByEntity instanceof EntitySquid) {
			return height - 0.2D;
		}
		return super.getMountedYOffset() + 0.35D;
	}

	@Override
	public double getYOffset() {
		if(ridingEntity instanceof EntityPlayer) {
			// �p������
//        	setSneaking(true);
//        	mstatAimeBow = true;
//        	updateAimebow();
//            return (double)(yOffset - 1.8F);
			return (double)(yOffset - 2.0F);
		}
		return (double)(yOffset - 0.25F);
=======
	public EntityLiving getOwner() {
		return getMaidMasterEntity();
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	public int getPlayingRole() {
		return mstatPlayingRole;
	}

	public boolean getSwinging() {
		return getSwinging(maidDominantArm);
	}

	public boolean getSwinging(int pArm) {
		return mstatSwingStatus[pArm].isSwingInProgress;
	}

	@Override
	public float getSwingProgress(float par1) {
		for (LMM_SwingStatus lswing : mstatSwingStatus) {
			lswing.getSwingProgress(par1);
		}
		return getSwingStatusDominant().onGround;
	}

	/**
	 * ポーション等による腕振りモーションの速度補正
	 */
	public int getSwingSpeedModifier() {
		if (isPotionActive(Potion.digSpeed)) {
			return 6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1;
		}

		if (isPotionActive(Potion.digSlowdown)) {
			return 6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
		} else {
			return 6;
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java

	// �_���[�W�R���g���[��
//	@Override
	public boolean isBlocking() {
		return getSwingStatusDominant().isBlocking();
//		return maidAvatar.isBlocking();
	}

	@Override
	protected void damageArmor(float pDamage) {
		maidInventory.damageArmor(pDamage);
		maidAvatar.damageArmor(pDamage);
=======
	public LMM_SwingStatus getSwingStatus(int pindex) {
		return mstatSwingStatus[pindex];
	}

	/**
	 * 利き腕のリロードタイム
	 */
	public LMM_SwingStatus getSwingStatusDominant() {
		return mstatSwingStatus[maidDominantArm];
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	@Override
	public int getTotalArmorValue() {
		return maidAvatar.getTotalArmorValue();
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	protected float applyArmorCalculations(DamageSource par1DamageSource, float par2) {
		return maidAvatar.applyArmorCalculations(par1DamageSource, par2);
	}

	@Override
	protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2) {
		return maidAvatar.applyPotionDamageCalculations(par1DamageSource, par2);
	}

	@Override
	protected void damageEntity(DamageSource par1DamageSource, float par2) {
		// �_���[�W�\�[�X�ɉ����ĉ����ύX
		if (par1DamageSource == DamageSource.fall) {
			maidDamegeSound = LMM_EnumSound.hurt_fall;
		}
		if(!par1DamageSource.isUnblockable() && isBlocking()) {
			// �u���b�L���O
			mod_LMM_littleMaidMob.Debug(String.format("Blocking success ID:%d, %d" , this.entityId, par2));
			maidDamegeSound = LMM_EnumSound.hurt_guard;
		}
		
		// ��_��
		float llasthealth = func_110143_aJ();
		if (par2 > 0 && getActiveModeClass() != null && !getActiveModeClass().damageEntity(maidMode, par1DamageSource, par2)) {
			maidAvatar.damageEntity(par1DamageSource, par2);
			
			// �_���[�W���󂯂�Ƒҋ@������
			setMaidWait(false);
		}
		
		if (llasthealth == func_110143_aJ() && maidDamegeSound == LMM_EnumSound.hurt) {
			maidDamegeSound = LMM_EnumSound.hurt_nodamege;
		}
		mod_LMM_littleMaidMob.Debug(String.format("GetDamage ID:%d, %s, %f/ %f" , this.entityId, par1DamageSource.damageType, llasthealth - func_110143_aJ(), par2));
//		super.damageEntity(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity entity = par1DamageSource.getEntity();
		
		// �_���[�W�\�[�X����肵�ĉ����̐ݒ�
		maidDamegeSound = LMM_EnumSound.hurt;
		if (par1DamageSource == DamageSource.inFire || par1DamageSource == DamageSource.onFire || par1DamageSource == DamageSource.lava) {
			maidDamegeSound = LMM_EnumSound.hurt_fire;
		}
		for (LMM_EntityModeBase lm : maidEntityModeList) {
			float li = lm.attackEntityFrom(par1DamageSource, par2);
			if (li > 0) return li == 1 ? false : true;
		}
		
		setMaidWait(false);
		setMaidWaitCount(0);
		if (par2 > 0) {
			// �V�т͏I��肾�I
			setPlayingRole(0);
			getNextEquipItem();
		}
		// �Q�[����Փx�ɂ��_���[�W�̕␳
		if(isContract() && (entity instanceof EntityLiving) || (entity instanceof EntityArrow)) {
			if(worldObj.difficultySetting == 0) {
				par2 = 0;
			}
			if(worldObj.difficultySetting == 1 && par2 > 0) {
				par2 = par2 / 2 + 1;
			}
			if(worldObj.difficultySetting == 3) {
				par2 = (par2 * 3) / 2;
			}
		}
		
//		if (par2 == 0 && maidMode != mmode_Detonator) {
		if (par2 == 0) {
			// �m�[�_���[�W
			if (maidDamegeSound == LMM_EnumSound.hurt) {
				maidDamegeSound = LMM_EnumSound.hurt_nodamege;
			}
			playSound(maidDamegeSound, true);
			return false;
		}
		
		if(super.attackEntityFrom(par1DamageSource, par2)) {
			//�_��҂̖��O�`�F�b�N�̓}���`�p
			if (isContract() && entity != null) {
				if (getIFF(entity) && !isPlaying()) {
					fleeingTick = 0;
					return true;
				}
			} else if (maidInventory.getCurrentItem() == null) {
				return true;
			}
			fleeingTick = 0;
//            entityToAttack = entity;
            /*
            if (entity != null) {
                setPathToEntity(worldObj.getPathEntityToEntity(this, entityToAttack, 16F, true, false, false, true));
            }
    		if (maidMode == mmode_Healer && entity instanceof EntityLiving) {
    			// �q�[���[�͖�܂ōU��
    			maidInventory.currentItem = maidInventory.getInventorySlotContainItemPotion(true, 0, ((EntityLiving)entity).isEntityUndead() & isMaskedMaid);
    		}
    		*/
			return true; 
=======
	/**
	 * 対応型射撃武器のリロード判定
	 */
	public void getWeaponStatus() {
		// 飛び道具用の特殊処理
		ItemStack is = maidInventory.getCurrentItem();
		if (is == null) {
			return;
		}

		try {
			Method me = is.getItem().getClass().getMethod("isWeaponReload", ItemStack.class, EntityPlayer.class);
			weaponReload = (Boolean) me.invoke(is.getItem(), is, maidAvatar);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
		}

		try {
			Method me = is.getItem().getClass().getMethod("isWeaponFullAuto", ItemStack.class);
			weaponFullAuto = (Boolean) me.invoke(is.getItem(), is);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
		}
	}

	@Override
	public double getYOffset() {
		if (ridingEntity instanceof EntityPlayer) {
			// 姿勢制御
			//        	setSneaking(true);
			//        	mstatAimeBow = true;
			//        	updateAimebow();
			//            return (double)(yOffset - 1.8F);
			return yOffset - 2.0F;
		}
		return yOffset - 0.25F;
	}

	@Override
	public void handleHealthUpdate(byte par1) {
		// worldObj.setEntityState(this, (byte))で指定されたアクションを実行
		switch (par1) {
		case 10:
			// 不機嫌
			showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
			break;
		case 11:
			// ゴキゲン
			double a = getContractLimitDays() / 7D;
			double d6 = a * 0.3D;
			double d7 = a;
			double d8 = a * 0.3D;
			worldObj.spawnParticle("note", posX, posY + height + 0.1D, posZ, d6, d7, d8);
			break;
		case 12:
			// 自由行動
			showParticleFX("reddust", 0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
			break;
		case 13:
			// 不自由行動
			showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
			break;
		case 14:
			// トレーサー
			showParticleFX("explode", 0.3D, 0.3D, 0.3D, 0.0D, 0.0D, 0.0D);
			break;

		default:
			super.handleHealthUpdate(par1);
		}
	}

	@Override
	public void initCreature() {
		super.initCreature();
		// テクスチャーをランダムで選択
		if (mod_LMM_littleMaidMob.defaultTexture.isEmpty()) {
			textureName = textureArmorName = MMM_TextureManager.getRandomTexture(rand);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		} else {
			textureName = textureArmorName = mod_LMM_littleMaidMob.defaultTexture;
		}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		
		
//		return maidAvatar.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * �ΏۂɃ|�[�V�������g���B
	 */
	public void usePotionTotarget(EntityLivingBase entityliving) {
		ItemStack itemstack = maidInventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
			// �|�[�V�������ʂ̔���
			itemstack.stackSize--;
			List list = ((ItemPotion)itemstack.getItem()).getEffects(itemstack);
			if (list != null) {
				PotionEffect potioneffect;
				for (Iterator iterator = list.iterator(); iterator.hasNext(); entityliving.addPotionEffect(new PotionEffect(potioneffect))) {
					potioneffect = (PotionEffect)iterator.next();
				}
			}
			if(itemstack.stackSize <= 0) {
				maidInventory.setInventoryCurrentSlotContents(null);
			}
			maidInventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
=======
		textureIndex = textureArmorIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		if (textureIndex == -1) {
			// ここは必要なくなった？
			textureName = textureArmorName = "default";
			textureIndex = textureArmorIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
		// 野生のメイド色をランダムで指定
		maidColor = MMM_TextureManager.getRandomWildColor(textureIndex, rand);
		mod_LMM_littleMaidMob.Debug(String.format("init-ID:%d, %s:%d", entityId, textureName, maidColor));
		//		MMM_Helper.mc.thePlayer.addChatMessage(String.format("init-ID:%d, %s(%d):%d", entityId, textureName, textureIndex, maidColor));
		setTexturePackIndex(maidColor, new int[] { textureIndex, textureArmorIndex });
		//		setMaidColor(maidColor);

	}

	public void initModeList() {
		// AI
		aiBeg = new LMM_EntityAIBeg(this, 8F);
		aiBegMove = new LMM_EntityAIBegMove(this, 0.3F);
		aiOpenDoor = new EntityAIOpenDoor(this, true);
		aiCloseDoor = new EntityAIRestrictOpenDoor(this);
		aiAvoidPlayer = new LMM_EntityAIAvoidPlayer(this, 0.3F, 3);
		aiFollow = new LMM_EntityAIFollowOwner(this, 0.3F, 6F, 5F);
		aiAttack = new LMM_EntityAIAttackOnCollide(this, 0.3F, true);
		aiShooting = new LMM_EntityAIAttackArrow(this);
		aiCollectItem = new LMM_EntityAICollectItem(this, 0.3F);
		aiRestrictRain = new LMM_EntityAIRestrictRain(this);
		aiFreeRain = new LMM_EntityAIFleeRain(this, 0.30F);
		aiWander = new LMM_EntityAIWander(this, 0.23F);
		aiJumpTo = new LMM_EntityAIJumpToMaster(this);
		aiFindBlock = new LMM_EntityAIFindBlock(this);
		aiSwiming = new LMM_EntityAISwimming(this);
		aiPanic = new EntityAIPanic(this, 0.38F);
		aiTracer = new LMM_EntityAITracerMove(this);
		aiSit = new LMM_EntityAIWait(this);

		// TODO:これいらなくね？
		aiProfiler = worldObj != null && worldObj.theProfiler != null ? worldObj.theProfiler : null;

		// 動作モード用のTasksListを初期化
		EntityAITasks ltasks[] = new EntityAITasks[2];
		ltasks[0] = new EntityAITasks(aiProfiler);
		ltasks[1] = new EntityAITasks(aiProfiler);

		// default
		ltasks[0].addTask(1, aiSwiming);
		ltasks[0].addTask(2, aiSit);
		ltasks[0].addTask(3, aiJumpTo);
		ltasks[0].addTask(4, aiFindBlock);
		ltasks[0].addTask(5, aiAttack);
		ltasks[0].addTask(6, aiShooting);
		//		ltasks[0].addTask(7, aiPanic);
		ltasks[0].addTask(8, aiBeg);
		ltasks[0].addTask(9, aiBegMove);
		ltasks[0].addTask(10, aiAvoidPlayer);
		ltasks[0].addTask(11, aiFreeRain);
		ltasks[0].addTask(12, aiCollectItem);
		// 移動用AI
		ltasks[0].addTask(15, aiTracer);
		ltasks[0].addTask(16, aiFollow);
		ltasks[0].addTask(17, aiWander);
		ltasks[0].addTask(18, new EntityAILeapAtTarget(this, 0.3F));
		// Mutexの影響しない特殊行動
		ltasks[0].addTask(20, aiCloseDoor);
		ltasks[0].addTask(21, aiOpenDoor);
		ltasks[0].addTask(22, aiRestrictRain);
		// 首の動き単独
		ltasks[0].addTask(31, new EntityAIWatchClosest(this, EntityLiving.class, 10F));
		ltasks[0].addTask(32, new EntityAILookIdle(this));

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	protected void updateAITick() {
//		// AI�Ή��^�͂��������Ă΂��
//		dataWatcher.updateObject(dataWatch_Health, Integer.valueOf(getHealth()));
		
		// �ǉ���
=======
		//		ltasks[1].addTask(2, new EntityAIHurtByTarget(this, false));

		//		addMaidMode(ltasks, "Escorter", 0x0001);

		// 追加分
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		for (LMM_EntityModeBase ieml : maidEntityModeList) {
			ieml.addEntityMode(ltasks[0], ltasks[1]);
		}

	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		// ナデリ判定
		if (health > 0 && par1EntityPlayer.riddenByEntity != null
				&& !(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)) {
			// 載せ替え
			par1EntityPlayer.riddenByEntity.mountEntity(this);
			return true;
		}

		ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();

		if (mstatgotcha == null && par1EntityPlayer.fishEntity == null) {
			if (itemstack1 != null && itemstack1.itemID == Item.silk.itemID) {
				// 紐で繋ぐ
				setGotcha(par1EntityPlayer.entityId);
				mstatgotcha = par1EntityPlayer;
				MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
				playSound("random.pop");
				return true;
			}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public void onLivingUpdate() {
		// �񕜔���
		float lhealth = func_110143_aJ();
		if (lhealth > 0) {
			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
					if (!isBloodsuck()) {
						// �ʏ펞�͉񕜗D��
						if (lhealth < func_110138_aP()) {
							if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
								eatSugar(true, false);
							}
						}
					}
				}
			}
		}
		
		super.onLivingUpdate();
		
		maidInventory.decrementAnimations();
		// �����΍�
		boolean grave = true;
		grave &= pushOutOfBlocks(posX - (double)width * 0.34999999999999998D, boundingBox.minY, posZ + (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX - (double)width * 0.34999999999999998D, boundingBox.minY, posZ - (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + (double)width * 0.34999999999999998D, boundingBox.minY, posZ - (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + (double)width * 0.34999999999999998D, boundingBox.minY, posZ + (double)width * 0.34999999999999998D);
		if (grave && onGround) {
			jump();
		}
		if(lhealth > 0) {
			// �ߐڊĎ��̒ǉ��͂���
			// �A�C�e���̉��
			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Entity entity = (Entity)list.get(i);
						if (!entity.isDead) {
							entity.onCollideWithPlayer(maidAvatar);
						}
					}
					// �A�C�e������t�ɂȂ��Ă��ăA�C�e���Ƀ^�Q���Ƃ��Ă���ꍇ�̓^�Q���N���A
					if (entityToAttack instanceof EntityItem && maidInventory.getFirstEmptyStack() == -1) {
						setTarget(null);
					}
				}
			}
			// ���v�������Ă���
			// TODO:�������̕ӂ�̏����͂�������
			if (isContractEX() && mstatClockMaid) {
				// �Q�[�������Ԃɍ��킹�������̍Đ�
				mstatTime = (int)(worldObj.getWorldTime() % 24000);
				if (mstatMasterEntity != null) {
					boolean b = mstatMasterEntity.isPlayerSleeping();
					
					if (mstatMasterDistanceSq < 25D && getEntitySenses().canSee(mstatMasterEntity))	{
						LMM_EnumSound lsound = LMM_EnumSound.Null;
						if (mstatFirstLook && (mstatTime > 23500 || mstatTime < 1500)) {
							lsound = LMM_EnumSound.goodmorning;
							mstatFirstLook = false;
						} 
						else if (!mstatFirstLook && b) {
							lsound = LMM_EnumSound.goodnight;
							mstatFirstLook = true;
						} 
						else if (mstatFirstLook && !b) {
							mstatFirstLook = false;
						}
						
						if (lsound != LMM_EnumSound.Null) {
							playSound(lsound, true);
							setLooksWithInterest(true);
						}
					} else {
						if (!mstatFirstLook && (b || (mstatTime > 18000 && mstatTime < 23500))) {
							mstatFirstLook = true;
=======
			if (isMaidContract()) {
				// 契約状態
				if (health > 0 && isMaidContractOwner(par1EntityPlayer)) {
					if (itemstack1 != null) {
						// 追加分の処理
						setPathToEntity(null);
						for (int li = 0; li < maidEntityModeList.size(); li++) {
							if (maidEntityModeList.get(li).interact(par1EntityPlayer, itemstack1)) {
								return true;
							}
						}
						if (isRemainsContract()) {
							// 通常
							if (itemstack1.itemID == Item.sugar.itemID) {
								// モード切替
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								eatSugar(false, true);
								worldObj.setEntityState(this, (byte) 11);

								mod_LMM_littleMaidMob.Debug("give suger." + worldObj.isRemote);
								if (!worldObj.isRemote) {
									setFreedom(isFreedom());
									if (isMaidWait()) {
										// 動作モードの切替
										boolean lflag = false;
										setActiveModeClass(null);
										for (int li = 0; li < maidEntityModeList.size() && !lflag; li++) {
											lflag = maidEntityModeList.get(li).changeMode(par1EntityPlayer);
											if (lflag) {
												setActiveModeClass(maidEntityModeList.get(li));
											}
										}
										if (!lflag) {
											setMaidMode("Escorter");
											setEquipItem(-1);
											//	    									maidInventory.currentItem = -1;
										}
										setMaidWait(false);
										getNextEquipItem();
									} else {
										// 待機
										setMaidWait(true);
									}
								}
								return true;
							}
							else if (itemstack1.itemID == Item.dyePowder.itemID) {
								// カラーメイド
								if (!worldObj.isRemote) {
									setMaidColor(15 - itemstack1.getItemDamage());
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (itemstack1.itemID == Item.feather.itemID) {
								// 自由行動
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setFreedom(!isFreedom());
								worldObj.setEntityState(this, isFreedom() ? (byte) 12 : (byte) 13);
								return true;
							}
							else if (itemstack1.itemID == Item.saddle.itemID) {
								// 肩車
								if (!worldObj.isRemote) {
									if (ridingEntity == par1EntityPlayer) {
										mountEntity(null);
									} else {
										mountEntity(par1EntityPlayer);
									}
									return true;
								}
							}
							else if (itemstack1.itemID == Item.gunpowder.itemID) {
								// test TNT-D
								//								playSound(LMM_EnumSound.eatGunpowder, false);
								maidOverDriveTime.setValue(itemstack1.stackSize * 10);
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, itemstack1.stackSize);
								return true;
							}
							else if (itemstack1.itemID == Item.book.itemID) {
								// IFFのオープン
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								//	    		            	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiIFF(worldObj, this));
								if (worldObj.isRemote) {
									LMM_Client.OpenIFF(this, par1EntityPlayer);
								}
								return true;
							}
							else if ((itemstack1.itemID == Item.glassBottle.itemID) && (experienceValue >= 5)) {
								// Expボトル
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								if (!worldObj.isRemote) {
									entityDropItem(new ItemStack(Item.expBottle), 0.5F);
									experienceValue -= 5;
									if (maidAvatar != null) {
										maidAvatar.experienceTotal -= 5;
									}
								}
								return true;
							}
							else if (itemstack1.getItem() instanceof ItemPotion) {
								// ポーション
								if (!worldObj.isRemote) {
									List list = ((ItemPotion) itemstack1.getItem()).getEffects(itemstack1);
									if (list != null) {
										PotionEffect potioneffect;
										for (Iterator iterator = list.iterator(); iterator.hasNext(); addPotionEffect(new PotionEffect(
												potioneffect))) {
											potioneffect = (PotionEffect) iterator.next();
										}
									}
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (isFreedom() && itemstack1.itemID == Item.redstone.itemID) {
								// Tracer
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setPathToEntity(null);
								setMaidWait(false);
								setTracer(!maidTracer);
								if (isTracer()) {
									worldObj.setEntityState(this, (byte) 14);
								} else {
									worldObj.setEntityState(this, (byte) 12);
								}

								return true;
							}
						} else {
							// ストライキ
							if (itemstack1.itemID == Item.sugar.itemID) {
								// 受取拒否
								worldObj.setEntityState(this, (byte) 10);
								return true;
							} else if (itemstack1.itemID == Item.cake.itemID) {
								// 再契約
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								maidContractLimit = (24000 * 7);
								setFreedom(false);
								setTracer(false);
								setMaidWait(false);
								setMaidMode("Escorter");
								worldObj.setEntityState(this, (byte) 11);
								playSound(LMM_EnumSound.Recontract, true);
								return true;
							}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
						}
					}
					// メイドインベントリ
					getNavigator().clearPathEntity();
					isJumping = false;
					displayGUIMaidInventory(par1EntityPlayer);
					//    		        	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiInventory(this, par1EntityPlayer.inventory, maidInventory));
					//    				serchedChest.clear();
					return true;
				}
			} else {
				// 未契約
				if (itemstack1 != null) {
					if (itemstack1.itemID == Item.cake.itemID) {
						// 契約
						MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);

						deathTime = 0;
						if (!worldObj.isRemote) {
							par1EntityPlayer.triggerAchievement(mod_LMM_littleMaidMob.ac_Contract);
							setMaidContract(true);
							setOwner(par1EntityPlayer.username);
							setEntityHealth(20);
							setMaidMode("Escorter");
							setMaidWait(false);
							setFreedom(false);
							playLittleMaidSound(LMM_EnumSound.getCake, true);
							//    	                    playTameEffect(true);
							worldObj.setEntityState(this, (byte) 7);
							// 契約記念日と、初期契約期間
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getWorldTime();
							// テクスチャのアップデート:いらん？
							//							LMM_Net.sendToAllEClient(this, new byte[] {LMM_Net.LMN_Client_UpdateTexture, 0, 0, 0, 0});

						}
						return true;
					} else {
						//    	                worldObj.setEntityState(this, (byte)6);
					}
				}
			}
		} else if (health > 0 && mstatgotcha != null) {
			if (!worldObj.isRemote) {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
				if (getSwingStatusDominant().canAttack()) {
//					mod_LMM_littleMaidMob.Debug("isRemort:" + worldObj.isRemote);
					// ��
					if (func_110143_aJ() < func_110138_aP()) {
						if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							eatSugar(true, false);
						}
					}
					// �܂ݐH��
					if (rand.nextInt(50000) == 0 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
						eatSugar(true, false);
					}
					// �_��X�V
					if (isContractEX()) {
						float f = getContractLimitDays();
						if (f <= 6 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							// �_��X�V
							eatSugar(true, true);
						}
					}
				}
=======
				EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ,
						new ItemStack(Item.silk));
				worldObj.spawnEntityInWorld(entityitem);
				setGotcha(0);
				mstatgotcha = null;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
			}
			return true;
		}

		return false;
	}

	public boolean isActiveModeClass() {
		return maidActiveModeClass != null;
	}

	// AI関連
	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void onUpdate() {
		// Entity���񐶐����̃C���x���g���X�V�p
		// �T�[�o�[�̕�����ɋN������̂ŃN���C�A���g�����X�V���󂯎��Ȃ�
		if (firstload > 0) {
			// ����X�V�p
			// �T�[�o�[�̕�����ɋN�����Ă���̂ŋ����ǂݍ��݂̎菇���K�v
			if (--firstload == 0) {
				if (worldObj.isRemote) {
					LMM_Net.sendToEServer(this, new byte[] {LMM_Statics.LMN_Server_UpdateSlots, 0, 0, 0, 0});
				} else {
				}
			}
		}
		
		// ��ѓ���p
		weaponFullAuto = false;
		weaponReload = false;
		
		// ��̊m�F�Ȃ�
		mstatMasterEntity = getMaidMasterEntity();
		if (mstatMasterEntity != null) {
			mstatMasterDistanceSq = getDistanceSqToEntity(mstatMasterEntity);
		}
		// ���f���T�C�Y�̃��A���^�C���ύX�L��H
		if (textureBox[0].isUpdateSize) {
			setSize(textureBox[0].getWidth(maidCaps), textureBox[0].getHeight(maidCaps));
			func_98054_a(false);
		}
		// ���A���^�C���ϓ��l���A�b�v�f�[�g
		if (worldObj.isRemote) {
			// �N���C�A���g��
			boolean lupd = false;
			lupd |= updateMaidContract();
			lupd |= updateMaidColor();
//			lupd |= updateTexturePack();
			updateTexturePack();
			if (lupd) {
				setTextureNames();
			}
			int lcolormode = dataWatcher.getWatchableObjectInt(dataWatch_ColorMode);
			setMaidMode(lcolormode & 0xffff);
			setDominantArm(lcolormode >>> 24);
//			if (health > 0) {
//				// �Ȃ������S�A�j���[�V���������������Ȃ�̂Ŕ���t����B
//				setEntityHealth(dataWatcher.getWatchableObjectInt(dataWatch_Health));
//			}
			updateMaidFlagsClient();
			updateGotcha();
		} else {
			boolean lf;
			// �T�[�o�[��
			updateRemainsContract();
			// Overdrive
			lf = maidOverDriveTime.isEnable();
			if (getMaidFlags(dataWatch_Flags_OverDrive) != lf) {
				if (lf) {
					playSound(LMM_EnumSound.TNT_D, true);
				}
				setMaidFlags(lf, dataWatch_Flags_OverDrive);
			}
			// Working!
			lf = mstatWorkingCount.isEnable();
			if (getMaidFlags(dataWatch_Flags_Working) != lf) {
				setMaidFlags(lf, dataWatch_Flags_Working);
			}
			// �X�˂�
			if (!isContractEX() && !isFreedom()) {
				setFreedom(true);
				setMaidWait(false);
			}
		}
		// �ړ����x�̐ݒ�
		// TODO:AI����̈ړ����x�����Ƃ����Ȃ��ƈӖ��Ȃ��B
//		setAIMoveSpeed((maidContract & !maidFreedom) ? moveSpeed_Max : moveSpeed_Nomal);
		
		// �Ǝ������p��������
		for (LMM_EntityModeBase leb : maidEntityModeList) {
			leb.onUpdate(maidMode);
		}
		
		
		super.onUpdate();
		// SwingUpdate
		LMM_SwingStatus lmss1 = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss1.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss1.swingProgress;
		field_110158_av = maidAvatar.field_110158_av = lmss1.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss1.isSwingInProgress;
		
		// Aveter�̖�������
		if (maidAvatar != null) {
			maidAvatar.getValue();
			maidAvatar.onUpdate();
//			maidAvatar.setValue();
		}
		
		// �J�E���^�n
		if (mstatWaitCount > 0) {
			if (hasPath()) {
				mstatWaitCount = 0;
			} else {
				mstatWaitCount--;
			}
		}
		if (maidSoundInterval > 0) {
			maidSoundInterval--;
		}
		
		// ���т�����	
		prevRotateAngleHead = rotateAngleHead;
		if (getLooksWithInterest()) {
			rotateAngleHead = rotateAngleHead + (1.0F - rotateAngleHead) * 0.4F;
			numTicksToChaseTarget = 10;
		} else {
			rotateAngleHead = rotateAngleHead + (0.0F - rotateAngleHead) * 0.4F;
			if (numTicksToChaseTarget > 0) numTicksToChaseTarget--;
		}
		
		if (getAttackTarget() != null || getEntityToAttack() != null) {
			setWorking(true);
		}
		mstatWorkingCount.onUpdate();
		for (LMM_SwingStatus lmss : mstatSwingStatus) {
			lmss.onUpdate(this);
		}
		LMM_SwingStatus lmss = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss.swingProgress;
		field_110158_av = maidAvatar.field_110158_av = lmss.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss.isSwingInProgress;
		
		// �������̊m�F
		if (maidInventory.inventoryChanged) {
			onInventoryChanged();
			maidInventory.inventoryChanged = false;
		}
		if (!worldObj.isRemote) {
			// �T�[�o�[������
			// �C���x���g���̍X�V
//			if (!mstatOpenInventory) {
				for (int li = 0 ;li < maidInventory.getSizeInventory(); li++) {
					boolean lchange = false;
					int lselect = 0xff;
					// �I�𑕔����ς����
					for (int lj = 0; lj < mstatSwingStatus.length; lj++) {
						lchange = mstatSwingStatus[lj].checkChanged();
						if (mstatSwingStatus[lj].index == li) {
							lselect = lj;
						}
					}
					// �C���x���g���̒��g���ς����
					if (lchange || maidInventory.isChanged(li)) {
						((WorldServer)worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, new Packet5PlayerInventory(this.entityId, (li | lselect << 8) + 5, maidInventory.getStackInSlot(li)));
						maidInventory.resetChanged(li);
						mod_LMM_littleMaidMob.Debug(String.format("ID:%d-%s - Slot(%x:%d-%d,%d) Update.", entityId, worldObj.isRemote ? "Client" : "Server", lselect, li, mstatSwingStatus[0].index, mstatSwingStatus[1].index));
					}
//				}
			}
			// �|�\��
			mstatAimeBow &= !getSwingStatusDominant().canAttack();
			// �\���̍X�V
			updateAimebow();
			
			// TODO:test
			if (dataWatcher.getWatchableObjectInt(30) != experienceValue) {
				dataWatcher.updateObject(30, experienceValue);
			}
			
			// �������傫�Ȃ��̂͏�����Ȃ��i�C�J�����j
			if (riddenByEntity != null && !(riddenByEntity instanceof EntitySquid)) {
				if (height * width < riddenByEntity.height * riddenByEntity.width) {
					if (riddenByEntity instanceof EntityLiving) {
						attackEntityFrom(DamageSource.causeMobDamage((EntityLiving)riddenByEntity), 0);
					}
					riddenByEntity.mountEntity(null);
					return;
				}
			}
		} else {
			// Client
			// TODO:test
			experienceValue = dataWatcher.getWatchableObjectInt(30);
		}
		
		// �R�ŝf�v
		if(mstatgotcha != null) {
			double d = mstatgotcha.getDistanceSqToEntity(this);
			if (entityToAttack == null) {
				// �C���R���������p
				if (d > 4D) {
//                    setPathToEntity(null);
					getNavigator().clearPathEntity();
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
				if (d > 12.25D) {
//                    setPathToEntity(worldObj.getPathEntityToEntity(mstatgotcha, this, 16F, true, false, false, true));
					getNavigator().tryMoveToXYZ(mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, getAIMoveSpeed());
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
			}
			if (d > 25D) {
				double d1 = mstatgotcha.posX - posX;
				double d3 = mstatgotcha.posZ - posZ;
				double d5 = 0.125D / (Math.sqrt(d1 * d1 + d3 * d3) + 0.0625D);
				d1 *= d5;
				d3 *= d5;
				motionX += d1;
				motionZ += d3;
			}
			if (d > 42.25D) {
				double d2 = mstatgotcha.posX - posX;
				double d4 = mstatgotcha.posZ - posZ;
				double d6 = 0.0625D / (Math.sqrt(d2 * d2 + d4 * d4) + 0.0625D);
				d2 *= d6;
				d4 *= d6;
				mstatgotcha.motionX -= d2;
				mstatgotcha.motionZ -= d4;
			}
			if (d > 64D) {
				setGotcha(0);
				mstatgotcha = null;
				playSound("random.drr");
			}
			if(rand.nextInt(16) == 0) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(8D, 8D, 8D));
				for (int k = 0; k < list.size(); k++) {
					Entity entity = (Entity)list.get(k);
					if (!(entity instanceof EntityMob)) {
						continue;
					}
					EntityMob entitymob = (EntityMob)entity;
					if (entitymob.entityToAttack == mstatgotcha) {
						entitymob.entityToAttack = this;
					}
				}
			}
		}
		
=======
	public boolean isAIEnabled() {
		// 新AI対応
		return true;
	}

	public boolean isAimebow() {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_Aimebow) > 0;
	}

	// ダメージコントロール
	@Override
	public boolean isBlocking() {
		return maidAvatar.isBlocking();
	}

	/**
	 * 埋葬対策コピー
	 */
	private boolean isBlockTranslucent(int par1, int par2, int par3) {
		return worldObj.isBlockNormalCube(par1, par2, par3);
	}

	public boolean isBloodsuck() {
		return mstatBloodsuck;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		// お好みは何？
		if (isMaidContractEX()) {
			return par1ItemStack.itemID == Item.sugar.itemID;
		} else {
			return par1ItemStack.itemID == Item.cake.itemID;
		}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	/**
	 * カモフラージュ！
	 */
	public boolean isCamouflage() {
		return mstatCamouflage;
	}

	/**
	 * 時計を持っているか?
	 */
	public boolean isClockMaid() {
		return mstatClockMaid;
	}

	public boolean isFreedom() {
		return maidFreedom;
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2) {
		super.onChangedPotionEffect(par1PotionEffect, par2);
		// TODO:�K�v���ǂ����̃`�F�b�N
//		if (mstatMasterEntity instanceof EntityPlayerMP) {
//			((EntityPlayerMP)mstatMasterEntity).playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.entityId, par1PotionEffect));
//		}
=======
	public boolean isLookSuger() {
		return mstatLookSuger;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	public boolean isMaidContract() {
		return super.isTamed();
	}

	public boolean isMaidContractEX() {
		return isMaidContract() && isRemainsContract();
	}

	// 保持アイテム関連

	public boolean isMaidContractOwner(EntityPlayer pentity) {
		return pentity == getMaidMasterEntity();

		//		return pentity == mstatMasterEntity;
	}

	public boolean isMaidContractOwner(String pname) {
		return pname.equalsIgnoreCase(mstatMasterEntity.username);
	}

	// メイドの待機設定
	public boolean isMaidWait() {
		return maidWait;
	}

	public boolean isMaidWaitEx() {
		return isMaidWait() | (mstatWaitCount > 0) | isOpenInventory();
	}

	/**
	 * メットを被ってるか
	 */
	public boolean isMaskedMaid() {
		return mstatMaskSelect > -1;
	}

	public boolean isOpenInventory() {
		return mstatOpenInventory;
	}

	/**
	 * 鉢植え状態
	 */
	public boolean isPlanter() {
		return mstatPlanter;
	}

	public boolean isPlaying() {
		return mstatPlayingRole != 0;
	}

	public boolean isRemainsContract() {
		return getMaidFlags(dataWatch_Flags_remainsContract);
	}

	// メイドの契約設定
	@Override
	public boolean isTamed() {
		return isMaidContract();
	}

	/**
	 * トレーサーモードであるか？
	 */
	public boolean isTracer() {
		return maidTracer;
	}

	/**
	 * 使っているTileかどうか判定して返す。
	 */
	public boolean isUsingTile(TileEntity pTile) {
		if (isActiveModeClass()) {
			return getActiveModeClass().isUsingTile(pTile);
		} else {
			return false;
		}
	}

	/**
	 * 仕事中かどうかを返す
	 */
	public boolean isWorking() {
		return mstatWorkingCount.isEnable();
	}

	/**
	 * 仕事が終了しても余韻を含めて返す
	 */
	public boolean isWorkingDelay() {
		return mstatWorkingCount.isDelay();
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public ItemStack func_130225_q(int par1) {
		return maidInventory.armorItemInSlot(par1);
	}

	@Override
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		par1 &= 0x0000ffff;
		if (par1 == 0) {
			maidInventory.setInventoryCurrentSlotContents(par2ItemStack);
		} else if (par1 > 0 && par1 < 4) {
			maidInventory.armorInventory[par1 - 1] = par2ItemStack;
			setTextureNames();
		} else if (par1 == 4) {
//			maidInventory.mainInventory[mstatMaskSelect] = mstatMaskSelect > -1 ? par2ItemStack : null;
			if (mstatMaskSelect > -1) {
				maidInventory.mainInventory[mstatMaskSelect] = par2ItemStack;
			}
			setTextureNames();
		} else {
			par1 -= 5;
			// �������̃A�b�v�f�[�g
			// �Ǝ��g��:���ʂɃX���b�g�ԍ��̒ʂ�A��ʂW�r�b�g�͑����X���b�g
			// par1��Short�œn�����̂ł��̂悤�ɁB
			int lslotindex = par1 & 0x7f;
			int lequip = (par1 >>> 8) & 0xff;
			maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
			maidInventory.resetChanged(lslotindex);	// ����͈Ӗ��Ȃ����ǂȁB
			maidInventory.inventoryChanged = true;
//			if (par1 >= maidInventory.mainInventory.length) {
//				LMM_Client.setArmorTextureValue(this);
//			}
=======
	public void onChangedPotionEffect(PotionEffect par1PotionEffect) {
		super.onChangedPotionEffect(par1PotionEffect);
		if (mstatMasterEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP) mstatMasterEntity).playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(
					entityId, par1PotionEffect));
		}
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java

		// 死因を表示
		if (!worldObj.isRemote) {
			// マスター判定失敗するかも？
			if (mod_LMM_littleMaidMob.DeathMessage && mstatMasterEntity != null) {
				String ls = par1DamageSource.getDamageType();
				Entity lentity = par1DamageSource.getEntity();
				if (lentity != null) {
					if (par1DamageSource.getEntity() instanceof EntityPlayer) {
						ls += ":" + ((EntityPlayer) lentity).username;
					} else {
						String lt = EntityList.getEntityString(lentity);
						if (lt != null) {
							ls += ":" + lt;
						}
					}
				}
				mstatMasterEntity.addChatMessage(String.format("your LittleMaid killed by %s", ls));
			}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
			if (lequip != 0xff) {
				setEquipItem(lequip, lslotindex);
//				mstatSwingStatus[lequip].index = lslotindex;
			}
			if (lslotindex >= maidInventory.maxInventorySize) {
				setTextureNames();
			}
			String s = par2ItemStack == null ? null : par2ItemStack.getItemName();
			mod_LMM_littleMaidMob.Debug(String.format("ID:%d Slot(%2d:%d):%s", entityId, lslotindex, lequip, s == null ? "NoItem" : s));
=======
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	protected void checkMaskedMaid() {
		// �C���x���g���Ƀw���������邩�H
		for (int i = maidInventory.mainInventory.length - 1; i >= 0; i--) {
			ItemStack is = maidInventory.getStackInSlot(i);
			if (is != null && is.getItem() instanceof ItemArmor && ((ItemArmor)is.getItem()).armorType == 0) {
				// �w�����������Ă�
				mstatMaskSelect = i;
				maidInventory.armorInventory[3] = is;
				setTextureNames();
				return;
			}
=======
	@Override
	public void onFinishedPotionEffect(PotionEffect par1PotionEffect) {
		super.onFinishedPotionEffect(par1PotionEffect);
		if (mstatMasterEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP) mstatMasterEntity).playerNetServerHandler
					.sendPacketToPlayer(new Packet42RemoveEntityEffect(entityId, par1PotionEffect));
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

	/**
	 * GUIを閉めた時にサーバー側で呼ばれる。
	 */
	public void onGuiClosed() {
		setOpenInventory(false);
		int li = maidMode & 0x0080;
		setMaidWaitCount((li == 0) ? 50 : 0);
	}

	/**
	 * GUIを開いた時にサーバー側で呼ばれる。
	 */
	public void onGuiOpened() {
		setOpenInventory(true);
	}

	/**
	 *  インベントリが変更されました。
	 */
	public void onInventoryChanged() {
		checkClockMaid();
		checkMaskedMaid();
		checkHeadMount();
		getNextEquipItem();
		//		setArmorTextureValue();
	}

	@Override
	public void onKillEntity(EntityLiving par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
		if (isBloodsuck()) {
			//			mod_LMM_littleMaidMob.Debug("nice Kill.");
			playSound(LMM_EnumSound.laughter, true);
		} else {
			setTarget(null);
			setAttackTarget(null);
		}
	}

	@Override
	public void onLivingUpdate() {
		// 飛び道具用
		weaponFullAuto = false;
		weaponReload = false;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	/**
	 * ���C�h�C���x���g�����J��
	 * @param pEntityPlayer
	 */
	public void displayGUIMaidInventory(EntityPlayer pEntityPlayer) {
		if (!worldObj.isRemote) {
			// server
			Container lcontainer = new LMM_ContainerInventory(pEntityPlayer.inventory, this);
			ModLoader.serverOpenWindow((EntityPlayerMP)pEntityPlayer, lcontainer, mod_LMM_littleMaidMob.containerID, entityId, 0, 0);
=======
		if (health > 0) {
			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
					// 回復判定
					if (!isBloodsuck()) {
						// 通常時は回復優先
						if (health < getMaxHealth()) {
							if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
								eatSugar(true, false);
							}
						}
					}
				}
			}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (par1EntityPlayer.isSneaking()) {
			return false;
		}
		float lhealth = func_110143_aJ();
		// �i�f������
		if (lhealth > 0F && par1EntityPlayer.riddenByEntity != null && !(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)) {
			// �ڂ��ւ�
			par1EntityPlayer.riddenByEntity.mountEntity(this);
			return true;
		}
		
		ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();
		
		
		if (mstatgotcha == null && par1EntityPlayer.fishEntity == null) {
			if(itemstack1 != null && itemstack1.itemID == Item.silk.itemID) {
				// �R�Ōq��
				setGotcha(par1EntityPlayer.entityId);
				mstatgotcha = par1EntityPlayer;
				MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
				playSound("random.pop");
				return true;
			} 
			
			if (isContract()) {
				// �_����
				if (lhealth > 0F && isMaidContractOwner(par1EntityPlayer)) {
					if (itemstack1 != null) {
						// �ǉ����̏���
						setPathToEntity(null);
						for (int li = 0; li < maidEntityModeList.size(); li++) {
							if (maidEntityModeList.get(li).interact(par1EntityPlayer, itemstack1)) {
								return true;
							}
						}
						if (isRemainsContract()) {
							// �ʏ�
							if (itemstack1.itemID == Item.sugar.itemID) {
								// ���[�h�ؑ�
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								eatSugar(false, true);
								worldObj.setEntityState(this, (byte)11);
								
								mod_LMM_littleMaidMob.Debug("give suger." + worldObj.isRemote);
								if (!worldObj.isRemote) {
									setFreedom(isFreedom());
									if (isMaidWait()) {
										// ���샂�[�h�̐ؑ�
										boolean lflag = false;
										setActiveModeClass(null);
										for (int li = 0; li < maidEntityModeList.size() && !lflag; li++) {
											lflag = maidEntityModeList.get(li).changeMode(par1EntityPlayer);
											if (lflag) {
												setActiveModeClass(maidEntityModeList.get(li));
											}
										}
										if (!lflag) {
											setMaidMode("Escorter");
											setEquipItem(-1);
//	    									maidInventory.currentItem = -1;
										}
										setMaidWait(false);
										getNextEquipItem();
									} else {
										// �ҋ@
										setMaidWait(true);
									}
								}
								return true;
							}
							else if (itemstack1.itemID == Item.dyePowder.itemID) {
								// �J���[���C�h
								if (!worldObj.isRemote) {
									setColor(15 - itemstack1.getItemDamage());
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (itemstack1.itemID == Item.feather.itemID) {
								// ���R�s��
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setFreedom(!isFreedom());
								worldObj.setEntityState(this, isFreedom() ? (byte)12 : (byte)13);
								return true;
							}
							else if (itemstack1.itemID == Item.saddle.itemID) {
								// ����
								if (!worldObj.isRemote) {
									if (ridingEntity == par1EntityPlayer) {
										this.mountEntity(null);
									} else {
										this.mountEntity(par1EntityPlayer);
									}
									return true;
								}
							}
							else if (itemstack1.itemID == Item.gunpowder.itemID) {
								// test TNT-D
//								playSound(LMM_EnumSound.eatGunpowder, false);
								maidOverDriveTime.setValue(itemstack1.stackSize * 10);
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, itemstack1.stackSize);
								return true;
							}
							else if (itemstack1.itemID == Item.book.itemID) {
								// IFF�̃I�[�v��
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
//	    		            	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiIFF(worldObj, this));
								if (worldObj.isRemote) {
									LMM_Client.OpenIFF(this, par1EntityPlayer);
								}
								return true;
							}
							else if ((itemstack1.itemID == Item.glassBottle.itemID) && (experienceValue >= 5)) {
								// Exp�{�g��
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								if (!worldObj.isRemote) {
									entityDropItem(new ItemStack(Item.expBottle), 0.5F);
									experienceValue -= 5;
									if (maidAvatar != null) {
										maidAvatar.experienceTotal -= 5;
									}
								}
								return true;
							}
							else if (itemstack1.getItem() instanceof ItemPotion) {
								// �|�[�V����
								if(!worldObj.isRemote) {
									List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
									if (list != null) {
										PotionEffect potioneffect;
										for (Iterator iterator = list.iterator(); iterator.hasNext(); addPotionEffect(new PotionEffect(potioneffect))) {
											potioneffect = (PotionEffect)iterator.next();
										}
									}
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (isFreedom() && itemstack1.itemID == Item.redstone.itemID) {
								// Tracer
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setPathToEntity(null);
								setMaidWait(false);
								setTracer(!maidTracer);
								if (isTracer()) {
									worldObj.setEntityState(this, (byte)14);
								} else {
									worldObj.setEntityState(this, (byte)12);
								}
								
								return true;
=======
		super.onLivingUpdate();

		maidInventory.decrementAnimations();
		// 埋葬対策
		boolean grave = true;
		grave &= pushOutOfBlocks(posX - width * 0.34999999999999998D, boundingBox.minY, posZ + width
				* 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX - width * 0.34999999999999998D, boundingBox.minY, posZ - width
				* 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + width * 0.34999999999999998D, boundingBox.minY, posZ - width
				* 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + width * 0.34999999999999998D, boundingBox.minY, posZ + width
				* 0.34999999999999998D);
		if (grave && onGround) {
			jump();
		}
		if (health > 0) {
			// 近接監視の追加はここ
			// アイテムの回収
			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Entity entity = (Entity) list.get(i);
						if (!entity.isDead) {
							entity.onCollideWithPlayer(maidAvatar);
						}
					}
					// アイテムが一杯になっていてアイテムにタゲをとっている場合はタゲをクリア
					if (entityToAttack instanceof EntityItem && maidInventory.getFirstEmptyStack() == -1) {
						setTarget(null);
					}
				}
			}
			// 時計を持っている
			// TODO:多分この辺りの処理はおかしい
			if (isMaidContractEX() && mstatClockMaid) {
				// ゲーム内時間に合わせた音声の再生
				mstatTime = (int) (worldObj.getWorldTime() % 24000);
				if (mstatMasterEntity != null) {
					boolean b = mstatMasterEntity.isPlayerSleeping();

					if (mstatMasterDistanceSq < 25D && getEntitySenses().canSee(mstatMasterEntity)) {
						LMM_EnumSound lsound = LMM_EnumSound.Null;
						if (mstatFirstLook && (mstatTime > 23500 || mstatTime < 1500)) {
							lsound = LMM_EnumSound.goodmorning;
							mstatFirstLook = false;
						}
						else if (!mstatFirstLook && b) {
							lsound = LMM_EnumSound.goodnight;
							mstatFirstLook = true;
						}
						else if (mstatFirstLook && !b) {
							mstatFirstLook = false;
						}

						if (lsound != LMM_EnumSound.Null) {
							playSound(lsound, true);
							setLooksWithInterest(true);
						}
					} else {
						if (!mstatFirstLook && (b || (mstatTime > 18000 && mstatTime < 23500))) {
							mstatFirstLook = true;
						}
					}
				}
			} else {
				mstatTime = 6000;
			}

			// TNT-D System
			maidOverDriveTime.onUpdate();
			if (maidOverDriveTime.isDelay()) {
				for (int li = 0; li < mstatSwingStatus.length; li++) {
					mstatSwingStatus[li].attackTime--;
				}
				if (maidOverDriveTime.isEnable()) {
					worldObj.spawnParticle("reddust", (posX + rand.nextFloat() * width * 2.0F) - width, posY + 0.5D
							+ rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, 1.2D, 0.4D,
							0.4D);
				}
				if (!worldObj.isRemote) {
					Entity lattackentity = getAttackTarget();
					if (lattackentity == null) {
						lattackentity = getEntityToAttack();
					}
					if (lattackentity != null) {
						PathEntity pe = worldObj.getPathEntityToEntity(this, lattackentity, 16F, true, false, false,
								true);

						if (pe != null) {
							pe.incrementPathIndex();
							if (!pe.isFinished()) {
								Vec3 v = pe.getPosition(this);
								setPosition(v.xCoord, v.yCoord, v.zCoord);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
							}
						}
					}
				}
			}

			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
					//					mod_LMM_littleMaidMob.Debug("isRemort:" + worldObj.isRemote);
					// 回復
					if (health < getMaxHealth()) {
						if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							eatSugar(true, false);
						}
					}
					// つまみ食い
					if (rand.nextInt(50000) == 0 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
						eatSugar(true, false);
					}
					// 契約更新
					if (isMaidContractEX()) {
						float f = getContractLimitDays();
						if (f <= 6 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							// 契約更新
							eatSugar(true, true);
						}
					}
				}
			}
		}
	}

	// ポーションエフェクト
	@Override
	public void onNewPotionEffect(PotionEffect par1PotionEffect) {
		super.onNewPotionEffect(par1PotionEffect);
		if (mstatMasterEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP) mstatMasterEntity).playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(
					entityId, par1PotionEffect));
		}
	}

	@Override
	public void onUpdate() {
		// Entity初回生成時のインベントリ更新用
		// サーバーの方が先に起動するのでクライアント側が更新を受け取れない
		if (firstload > 0) {
			// 初回更新用
			// サーバーの方が先に起動しているので強制読み込みの手順が必要
			if (--firstload == 0) {
				if (worldObj.isRemote) {
					LMM_Net.sendToEServer(this, new byte[] { LMM_Net.LMN_Server_UpdateSlots, 0, 0, 0, 0 });
				} else {
				}
			}
		}

		// 主の確認など
		mstatMasterEntity = getMaidMasterEntity();
		if (mstatMasterEntity != null) {
			mstatMasterDistanceSq = getDistanceSqToEntity(mstatMasterEntity);
		}
		// リアルタイム変動値をアップデート
		if (worldObj.isRemote) {
			// クライアント側
			boolean lupd = false;
			//			isWait();
			if (textureIndex > -1 && textureArmorIndex > -1) {
				lupd |= updateMaidContract();
				lupd |= updateTexturePack();
				lupd |= updateMaidColor();
			} else {
				sendTextureToServer();
			}
			if (lupd) {
				LMM_Client.setTextureValue(this);
			}
			int lcolormode = dataWatcher.getWatchableObjectInt(dataWatch_ColorMode);
			setMaidMode(lcolormode & 0xffff);
			setDominantArm(lcolormode >>> 24);
			if (health > 0) {
				// なぜか死亡アニメーションがおかしくなるので判定付ける。
				setEntityHealth(dataWatcher.getWatchableObjectInt(dataWatch_Health));
			}
			updateMaidFlagsClient();
			updateGotcha();
		} else {
			boolean lf;
			// サーバー側
			updateRemainsContract();
			// Overdrive
			lf = maidOverDriveTime.isEnable();
			if (getMaidFlags(dataWatch_Flags_OverDrive) != lf) {
				if (lf) {
					playSound(LMM_EnumSound.TNT_D, true);
				}
				setMaidFlags(lf, dataWatch_Flags_OverDrive);
			}
			// Working!
			lf = mstatWorkingCount.isEnable();
			if (getMaidFlags(dataWatch_Flags_Working) != lf) {
				setMaidFlags(lf, dataWatch_Flags_Working);
			}
			// 拗ねる
			if (!isMaidContractEX() && !isFreedom()) {
				setFreedom(true);
				setMaidWait(false);
			}
		}
		// 移動速度の設定
		// TODO:AI周りの移動速度を何とかしないと意味ない。
		moveSpeed = (maidContract & !maidFreedom) ? moveSpeed_Max : moveSpeed_Nomal;

		super.onUpdate();
		// SwingUpdate
		LMM_SwingStatus lmss1 = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss1.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss1.swingProgress;
		swingProgressInt = maidAvatar.swingProgressInt = lmss1.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss1.isSwingInProgress;

		// Aveterの毎時処理
		if (maidAvatar != null) {
			maidAvatar.getValue();
			maidAvatar.onUpdate();
			//			maidAvatar.setValue();
		}
		// 独自処理
		for (LMM_EntityModeBase leb : maidEntityModeList) {
			leb.onUpdate(maidMode);
		}

		// カウンタ系
		if (mstatWaitCount > 0) {
			if (hasPath()) {
				mstatWaitCount = 0;
			} else {
				mstatWaitCount--;
			}
		}
		if (maidSoundInterval > 0) {
			maidSoundInterval--;
		}

		// くびかしげ
		prevRotateAngleHead = rotateAngleHead;
		if (getLooksWithInterest()) {
			rotateAngleHead = rotateAngleHead + (1.0F - rotateAngleHead) * 0.4F;
			numTicksToChaseTarget = 10;
		} else {
			rotateAngleHead = rotateAngleHead + (0.0F - rotateAngleHead) * 0.4F;
			if (numTicksToChaseTarget > 0) {
				numTicksToChaseTarget--;
			}
		}

		if (getAttackTarget() != null || getEntityToAttack() != null) {
			setWorking(true);
		}
		mstatWorkingCount.onUpdate();
		for (LMM_SwingStatus lmss : mstatSwingStatus) {
			lmss.onUpdate(this);
		}
		LMM_SwingStatus lmss = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss.swingProgress;
		swingProgressInt = maidAvatar.swingProgressInt = lmss.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss.isSwingInProgress;

		// 持ち物の確認
		if (maidInventory.inventoryChanged) {
			onInventoryChanged();
			maidInventory.inventoryChanged = false;
		}
		if (!worldObj.isRemote) {
			// サーバー側処理
			// インベントリの更新
			//			if (!mstatOpenInventory) {
			for (int li = 0; li < maidInventory.getSizeInventory(); li++) {
				boolean lchange = false;
				int lselect = 0xff;
				// 選択装備が変わった
				for (int lj = 0; lj < mstatSwingStatus.length; lj++) {
					lchange = mstatSwingStatus[lj].checkChanged();
					if (mstatSwingStatus[lj].index == li) {
						lselect = lj;
					}
				}
				// インベントリの中身が変わった
				if (lchange || maidInventory.isChanged(li)) {
					((WorldServer) worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(
							this,
							new Packet5PlayerInventory(entityId, (li | lselect << 8) + 5, maidInventory
									.getStackInSlot(li)));
					maidInventory.resetChanged(li);
					mod_LMM_littleMaidMob.Debug(String.format("ID:%d-%s - Slot(%x:%d-%d,%d) Update.", entityId,
							worldObj.isRemote ? "Client" : "Server", lselect, li, mstatSwingStatus[0].index,
							mstatSwingStatus[1].index));
				}
				//				}
			}
			// 弓構え
			mstatAimeBow &= !getSwingStatusDominant().canAttack();
			// 構えの更新
			updateAimebow();

			// TODO:test
			if (dataWatcher.getWatchableObjectInt(30) != experienceValue) {
				dataWatcher.updateObject(30, experienceValue);
			}

			// 自分より大きなものは乗っけない（イカ除く）
			if (riddenByEntity != null && !(riddenByEntity instanceof EntitySquid)) {
				if (height * width < riddenByEntity.height * riddenByEntity.width) {
					if (riddenByEntity instanceof EntityLiving) {
						attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) riddenByEntity), 0);
					}
					riddenByEntity.mountEntity(null);
					return;
				}
			}
		} else {
			// Client
			// TODO:test
			experienceValue = dataWatcher.getWatchableObjectInt(30);
		}

		// 紐で拉致
		if (mstatgotcha != null) {
			double d = mstatgotcha.getDistanceSqToEntity(this);
			if (entityToAttack == null) {
				// インコムごっこ用
				if (d > 4D) {
					//                    setPathToEntity(null);
					getNavigator().clearPathEntity();
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
				if (d > 12.25D) {
					//                    setPathToEntity(worldObj.getPathEntityToEntity(mstatgotcha, this, 16F, true, false, false, true));
					getNavigator().tryMoveToXYZ(mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, getAIMoveSpeed());
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
			}
			if (d > 25D) {
				double d1 = mstatgotcha.posX - posX;
				double d3 = mstatgotcha.posZ - posZ;
				double d5 = 0.125D / (Math.sqrt(d1 * d1 + d3 * d3) + 0.0625D);
				d1 *= d5;
				d3 *= d5;
				motionX += d1;
				motionZ += d3;
			}
			if (d > 42.25D) {
				double d2 = mstatgotcha.posX - posX;
				double d4 = mstatgotcha.posZ - posZ;
				double d6 = 0.0625D / (Math.sqrt(d2 * d2 + d4 * d4) + 0.0625D);
				d2 *= d6;
				d4 *= d6;
				mstatgotcha.motionX -= d2;
				mstatgotcha.motionZ -= d4;
			}
			if (d > 64D) {
				setGotcha(0);
				mstatgotcha = null;
				playSound("random.drr");
			}
			if (rand.nextInt(16) == 0) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(8D, 8D, 8D));
				for (int k = 0; k < list.size(); k++) {
					Entity entity = (Entity) list.get(k);
					if (!(entity instanceof EntityMob)) {
						continue;
					}
					EntityMob entitymob = (EntityMob) entity;
					if (entitymob.entityToAttack == mstatgotcha) {
						entitymob.entityToAttack = this;
					}
				}
			}
		}

	}

	/**
	 * 音声再生用。
	 * 通常の再生ではネットワーク越しになるのでその対策。
	 */
	public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force) {
		// 音声の再生
		if ((maidSoundInterval > 0 && !force) || enumsound == LMM_EnumSound.Null) {
			return;
		}
		maidSoundInterval = 20;
		if (worldObj.isRemote) {
			// Client
			String s = LMM_SoundManager.getSoundValue(enumsound, textureName, maidColor & 0x00ff);
			mod_LMM_littleMaidMob
					.Debug(String.format("id:%d, se:%04x-%s", entityId, enumsound.index, enumsound.name()));
			float lpitch = mod_LMM_littleMaidMob.VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
			worldObj.playSound(posX, posY, posZ, s, getSoundVolume(), lpitch, false);
		}
	}

	/**
	 * ネットワーク対応音声再生
	 */
	public void playSound(LMM_EnumSound enumsound, boolean force) {
		if ((maidSoundInterval > 0 && !force) || enumsound == LMM_EnumSound.Null) {
			return;
		}
		maidSoundInterval = 20;
		mod_LMM_littleMaidMob.Debug(String.format("id:%d-%s, seps:%04x-%s", entityId, worldObj.isRemote ? "Client"
				: "Server", enumsound.index, enumsound.name()));
		if (worldObj.isRemote) {
			// Client
			//			String lsound = LMM_SoundManager.getSoundValue(enumsound, textureName, maidColor & 0x00ff);
			//			float lpitch = mod_LMM_littleMaidMob.VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
			//			worldObj.playSound(posX, posY, posZ, lsound, getSoundVolume(), lpitch, false);
		} else {
			// Server
			byte[] lbuf = new byte[] {
					LMM_Net.LMN_Client_PlaySound,
					0, 0, 0, 0,
					0, 0, 0, 0
			};
			MMM_Helper.setInt(lbuf, 5, enumsound.index);
			LMM_Net.sendToAllEClient(this, lbuf);
		}
	}

	public void playSound(String pname) {
		// 簡易音声再生
		playSound(pname, 0.5F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}

	/**
	 * 埋葬対策コピー
	 */
	@Override
	public boolean pushOutOfBlocks(double par1, double par3, double par5) {
		// EntityPlayerSPのを引っ張ってきた
		int var7 = MathHelper.floor_double(par1);
		int var8 = MathHelper.floor_double(par3);
		int var9 = MathHelper.floor_double(par5);
		double var10 = par1 - var7;
		double var12 = par5 - var9;

		boolean lflag = false;
		for (int li = 0; li < height; li++) {
			lflag |= isBlockTranslucent(var7, var8 + li, var9);
		}
		if (lflag) {
			boolean var14 = !isBlockTranslucent(var7 - 1, var8, var9) && !isBlockTranslucent(var7 - 1, var8 + 1, var9);
			boolean var15 = !isBlockTranslucent(var7 + 1, var8, var9) && !isBlockTranslucent(var7 + 1, var8 + 1, var9);
			boolean var16 = !isBlockTranslucent(var7, var8, var9 - 1) && !isBlockTranslucent(var7, var8 + 1, var9 - 1);
			boolean var17 = !isBlockTranslucent(var7, var8, var9 + 1) && !isBlockTranslucent(var7, var8 + 1, var9 + 1);
			byte var18 = -1;
			double var19 = 9999.0D;

			if (var14 && var10 < var19) {
				var19 = var10;
				var18 = 0;
			}

			if (var15 && 1.0D - var10 < var19) {
				var19 = 1.0D - var10;
				var18 = 1;
			}

			if (var16 && var12 < var19) {
				var19 = var12;
				var18 = 4;
			}

			if (var17 && 1.0D - var12 < var19) {
				var19 = 1.0D - var12;
				var18 = 5;
			}

			float var21 = 0.1F;

			if (var18 == 0) {
				motionX = (-var21);
			}

			if (var18 == 1) {
				motionX = var21;
			}

			if (var18 == 4) {
				motionZ = (-var21);
			}

			if (var18 == 5) {
				motionZ = var21;
			}

			return !(var14 | var15 | var16 | var17);
		}

		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		// データロード
		super.readEntityFromNBT(par1nbtTagCompound);

		if (par1nbtTagCompound.hasKey("ModeColor")) {
			// 旧版からの継承
			String s = par1nbtTagCompound.getString("Master");
			if (s.length() > 0) {
				setOwner(s);
				setMaidContract(true);
			}
			NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory");
			maidInventory.readFromNBT(nbttaglist);
			// アーマースロット変更に対応するためのコード
			ItemStack[] armi = new ItemStack[4];
			for (int i = 0; i < 4; i++) {
				ItemStack is = maidInventory.armorItemInSlot(i);
				if (is != null) {
					armi[3 - ((ItemArmor) is.getItem()).armorType] = is;
				}
			}
			maidInventory.armorInventory = armi;
			//
			setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
			setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
			setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			textureName = par1nbtTagCompound.getString("texName");
			textureArmorName = par1nbtTagCompound.getString("texArmor");
			byte b = par1nbtTagCompound.getByte("ModeColor");
			setMaidColor(b & 0x0f);
			switch ((b & 0xf0) >> 4) {
			case 0:
				setMaidMode(0x0000); // Wild
				break;
			case 2:
				setMaidMode(0x0001); // Escorter
				break;
			case 4:
				setMaidMode(0x0080); // Fencer
				break;
			case 5:
				setMaidMode(0x0000); // Healer
				break;
			case 6:
				setMaidMode(0x0021); // Cooking
				break;
			case 7:
				setMaidMode(0x00c0); // Bloodsucker
				break;
			case 8:
				setMaidMode(0x0083); // Archer
				break;
			case 9:
				setMaidMode(0x00c3); // Blazingstar
				break;
			case 10:
				setMaidMode(0x0081); // Ripper
				break;
			case 11:
				setMaidMode(0x00c2); // Detonator
				break;
			case 12:
				setMaidMode(0x00c1); // TNT-D
				break;
			case 13:
				setMaidMode(0x0020); // Torcher
				break;
			case 15:
				setMaidMode(0x0000); // Pharmacist
				break;
			default:
				setMaidMode(0x0000); // Wild
			}
			//	        setMaidMode((b & 0xf0) >> 4);
			int lhx = 0;
			int lhy = 0;
			int lhz = 0;
			NBTTagList nbttaglist1 = par1nbtTagCompound.getTagList("HomePosI");
			if (nbttaglist1.tagCount() > 0) {
				lhx = ((NBTTagInt) nbttaglist1.tagAt(0)).data;
				lhy = ((NBTTagInt) nbttaglist1.tagAt(1)).data;
				lhz = ((NBTTagInt) nbttaglist1.tagAt(2)).data;
			} else {
				lhx = MathHelper.floor_double(posX);
				lhy = MathHelper.floor_double(posY);
				lhz = MathHelper.floor_double(posZ);
			}
			getHomePosition().set(lhx, lhy, lhz);
			long lcl = par1nbtTagCompound.getLong("Limit");
			if (isMaidContract() && lcl == 0) {
				maidContractLimit = 24000;
			} else {
				maidContractLimit = (int) ((lcl - worldObj.getWorldTime()));
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isMaidContract()) {
				// ダミーの数値を入れる
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}

		} else {
			// 新型
			mod_LMM_littleMaidMob.Debug("read." + worldObj.isRemote);

			maidInventory.readFromNBT(par1nbtTagCompound.getTagList("Inventory"));
			setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
			setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
			setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			setMaidMode(par1nbtTagCompound.getString("Mode"));
			if (par1nbtTagCompound.hasKey("LimitCount")) {
				maidContractLimit = par1nbtTagCompound.getInteger("LimitCount");
			} else {
				long lcl = par1nbtTagCompound.getLong("Limit");
				if (isMaidContract() && lcl == 0) {
					maidContractLimit = 24000;
				} else {
					maidContractLimit = (int) ((lcl - worldObj.getWorldTime()));
				}
			}
			if (isMaidContract() && maidContractLimit == 0) {
				// 値がおかしい時は１日分
				//	        	maidContractLimit = worldObj.getWorldTime() + 24000L;
				maidContractLimit = 24000;
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isMaidContract()) {
				// ダミーの数値を入れる
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}
			if (maidAvatar != null) {
				maidAvatar.experienceTotal = par1nbtTagCompound.getInteger("EXP");
			}
			setDominantArm(par1nbtTagCompound.getInteger("DominantArm"));
			if (mstatSwingStatus.length <= maidDominantArm) {
				maidDominantArm = 0;
			}
			textureName = par1nbtTagCompound.getString("texName");
			textureArmorName = par1nbtTagCompound.getString("texArmor");
			setMaidColor(par1nbtTagCompound.getInteger("Color"));

			// HomePosition
			int lhx = par1nbtTagCompound.getInteger("homeX");
			int lhy = par1nbtTagCompound.getInteger("homeY");
			int lhz = par1nbtTagCompound.getInteger("homeZ");
			getHomePosition().set(lhx, lhy, lhz);
			homeWorld = par1nbtTagCompound.getInteger("homeWorld");

			// テスト用
			if (worldObj.isRemote) {
				//	        	setOwner(ModLoader.getMinecraftInstance().thePlayer.username);
			}

			// 追加分
			for (int li = 0; li < maidEntityModeList.size(); li++) {
				maidEntityModeList.get(li).readEntityFromNBT(par1nbtTagCompound);
			}
		}
		// TODO: ColorBitsをどうするべ？
		//		textureIndex = MMM_TextureManager.setStringToIndex(textureName, -1);
		//		textureArmorIndex = MMM_TextureManager.setStringToIndex(textureArmorName, -1);
		textureIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		textureArmorIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureArmorName);
		setTexturePackIndex(maidColor, new int[] { textureIndex, textureArmorIndex });
		onInventoryChanged();

		// ドッペル対策
		if (mod_LMM_littleMaidMob.antiDoppelganger && maidAnniversary > 0L) {
			for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
				Entity entity1 = (Entity) worldObj.loadedEntityList.get(i);
				if (!entity1.isDead && entity1 instanceof LMM_EntityLittleMaid) {
					LMM_EntityLittleMaid elm = (LMM_EntityLittleMaid) entity1;
					if (elm != this && elm.isMaidContract() && elm.maidAnniversary == maidAnniversary
							&& elm.getMaidMaster().equalsIgnoreCase(getMaidMaster())) {
						// 新しい方を残す
						if (entityId > elm.entityId) {
							mod_LMM_littleMaidMob.Debug(String.format("Load Doppelganger ID:%d, %d", elm.entityId,
									maidAnniversary));
							elm.setDead();
						} else {
							mod_LMM_littleMaidMob.Debug(String.format("Load Doppelganger ID:%d, %d", entityId,
									maidAnniversary));
							setDead();
							break;
						}
					}
				}
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
			} else {
				// ���_��
				if (itemstack1 != null) {
					if (itemstack1.itemID == Item.cake.itemID) {
						// �_��
						MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
						
						deathTime = 0;
						if (!worldObj.isRemote) {
							par1EntityPlayer.triggerAchievement(mod_LMM_littleMaidMob.ac_Contract);
							setContract(true);
							setOwner(par1EntityPlayer.username);
							setEntityHealth(20);
							setMaidMode("Escorter");
							setMaidWait(false);
							setFreedom(false);
							playSound(LMM_EnumSound.getCake, true);
//							playLittleMaidSound(LMM_EnumSound.getCake, true);
//    	                    playTameEffect(true);
							worldObj.setEntityState(this, (byte)7);
							// �_��L�O���ƁA�����_�����
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getWorldTime();
							// �e�N�X�`���̃A�b�v�f�[�g:�����H
//							LMM_Net.sendToAllEClient(this, new byte[] {LMM_Net.LMN_Client_UpdateTexture, 0, 0, 0, 0});
							
						}
						return true;
					} else {
//    	                worldObj.setEntityState(this, (byte)6);
					}
				}
			}
		} else if (lhealth > 0F && mstatgotcha != null) {
			if (!worldObj.isRemote) {
				EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, new ItemStack(Item.silk));
				worldObj.spawnEntityInWorld(entityitem);
				setGotcha(0);
				mstatgotcha = null;
=======
			}
		} else {
			mod_LMM_littleMaidMob.Debug(String.format("Load ID:%d, MaidMaster:%s, x:%.1f, y:%.1f, z:%.1f, %d",
					entityId, getMaidMaster(), posX, posY, posZ, maidAnniversary));
		}

	}

	/**
	 * サーバーへテクスチャパックのインデックスを送る。
	 * クライアント側の処理
	 */
	public boolean sendTextureToServer() {
		// 16bitあればテクスチャパックの数にたりんべ
		MMM_TextureManager.postSetTexturePack(this, maidColor, new String[] { textureName, textureArmorName });
		return true;
	}

	public void setActiveModeClass(LMM_EntityModeBase pEntityMode) {
		maidActiveModeClass = pEntityMode;
	}

	// 今宵のメイドは血に飢えておる
	public void setBloodsuck(boolean pFlag) {
		mstatBloodsuck = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Bloodsuck);
	}

	@Override
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		par1 &= 0x0000ffff;
		if (par1 == 0) {
			maidInventory.setInventoryCurrentSlotContents(par2ItemStack);
		} else if (par1 > 0 && par1 < 4) {
			maidInventory.armorInventory[par1 - 1] = par2ItemStack;
		} else if (par1 == 4) {
			//			maidInventory.mainInventory[mstatMaskSelect] = mstatMaskSelect > -1 ? par2ItemStack : null;
			if (mstatMaskSelect > -1) {
				maidInventory.mainInventory[mstatMaskSelect] = par2ItemStack;
			}
			LMM_Client.setArmorTextureValue(this);
		} else {
			par1 -= 5;
			// 持ち物のアップデート
			// 独自拡張:普通にスロット番号の通り、上位８ビットは装備スロット
			// par1はShortで渡されるのでそのように。
			int lslotindex = par1 & 0x7f;
			int lequip = (par1 >>> 8) & 0xff;
			maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
			maidInventory.resetChanged(lslotindex); // これは意味ないけどな。
			maidInventory.inventoryChanged = true;
			//			if (par1 >= maidInventory.mainInventory.length) {
			//				LMM_Client.setArmorTextureValue(this);
			//			}

			for (LMM_SwingStatus lss : mstatSwingStatus) {
				if (lslotindex == lss.index) {
					lss.index = -1;
				}
			}
			if (lequip != 0xff) {
				setEquipItem(lequip, lslotindex);
				//				mstatSwingStatus[lequip].index = lslotindex;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
			}
			if (lslotindex >= LMM_InventoryLittleMaid.maxInventorySize) {
				LMM_Client.setArmorTextureValue(this);
			}
			String s = par2ItemStack == null ? null : par2ItemStack.getItemName();
			mod_LMM_littleMaidMob.Debug(String.format("ID:%d Slot(%2d:%d):%s", entityId, lslotindex, lequip,
					s == null ? "NoItem" : s));
		}
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean isTamed() {
		return isContract();
	}
	public boolean isContract() {
		return super.isTamed();
	}
	public boolean isContractEX() {
		return isContract() && isRemainsContract();
	}

	@Override
	public void setTamed(boolean par1) {
		setContract(par1);
	}
	@Override
	public void setContract(boolean flag) {
=======
	public void setDead() {
		if (mstatgotcha != null) {
			// 首紐をドロップ
			EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ,
					new ItemStack(Item.silk));
			worldObj.spawnEntityInWorld(entityitem);
			mstatgotcha = null;
		}
		super.setDead();
	}

	/**
	 *  利き腕の設定
	 */
	public void setDominantArm(int pindex) {
		if (maidDominantArm == pindex) {
			return;
		}
		for (LMM_SwingStatus lss : mstatSwingStatus) {
			lss.index = lss.lastIndex = -1;
		}
		maidDominantArm = pindex;
		setMaidColorMode();
		mod_LMM_littleMaidMob.Debug("Change Dominant.");
	}

	public void setEquipItem(int pIndex) {
		setEquipItem(maidDominantArm, pIndex);
	}

	public void setEquipItem(int pArm, int pIndex) {
		if (pArm == maidDominantArm) {
			maidInventory.currentItem = pIndex;
		}
		int li = mstatSwingStatus[pArm].index;
		if (li != pIndex) {
			if (li > -1) {
				maidInventory.setChanged(li);
			}
			if (pIndex > -1) {
				maidInventory.setChanged(pIndex);
			}
			mstatSwingStatus[pArm].setSlotIndex(pIndex);
		}
	}

	// 自由行動
	public void setFreedom(boolean pFlag) {
		// AI関連のリセットもここで。
		maidFreedom = pFlag;
		aiRestrictRain.setEnable(pFlag);
		aiFreeRain.setEnable(pFlag);
		aiWander.setEnable(pFlag);
		//		aiJumpTo.setEnable(!pFlag);
		aiAvoidPlayer.setEnable(!pFlag);
		aiFollow.setEnable(!pFlag);
		aiTracer.setEnable(false);
		setAIMoveSpeed(pFlag ? moveSpeed_Nomal : moveSpeed_Max);
		setMoveForward(0.0F);
		if (maidFreedom && isMaidContract()) {
			setHomeArea(
					MathHelper.floor_double(posX),
					MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ), 16);
		} else {
			detachHome();
		}

		setMaidFlags(maidFreedom, dataWatch_Flags_Freedom);
	}

	public void setGotcha(int pEntityID) {
		dataWatcher.updateObject(dataWatch_Gotcha, Integer.valueOf(pEntityID));
	}

	@Override
	public void setHomeArea(int par1, int par2, int par3, int par4) {
		homeWorld = dimension;
		super.setHomeArea(par1, par2, par3, par4);
	}

	// 砂糖関連
	public void setLookSuger(boolean pFlag) {
		mstatLookSuger = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_LooksSugar);
	}

	// 首周り
	public void setLooksWithInterest(boolean f) {
		if (looksWithInterest != f) {
			looksWithInterest = f;
			if (numTicksToChaseTarget <= 0) {
				looksWithInterestAXIS = rand.nextBoolean();
			}
			int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
			li = looksWithInterest ? (li | dataWatch_Flags_looksWithInterest)
					: (li & ~dataWatch_Flags_looksWithInterest);
			li = looksWithInterestAXIS ? (li | dataWatch_Flags_looksWithInterestAXIS)
					: (li & ~dataWatch_Flags_looksWithInterestAXIS);
			dataWatcher.updateObject(dataWatch_Flags, Integer.valueOf(li));
		}
	}

	public void setMaidColor(int index) {
		maidColor = index & 0xff;
		setMaidColorMode();
	}

	public void setMaidColorMode() {
		if (worldObj == null || worldObj.isRemote) {
			return;
		}
		dataWatcher.updateObject(dataWatch_ColorMode,
				(maidMode & 0xffff) | ((maidColor & 0xff) << 16) | ((maidDominantArm & 0xff) << 24));
	}

	public void setMaidContract(boolean flag) {
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		super.setTamed(flag);
		if (flag) {
			//        	maidMode = mmode_Escorter;
		} else {
		}
	}

	/**
	 * フラグ群に値をセット。
	 * @param pCheck： 対象値。
	 * @param pFlags： 対象フラグ。
	 */
	public void setMaidFlags(boolean pFlag, int pFlagvalue) {
		int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
		li = pFlag ? (li | pFlagvalue) : (li & ~pFlagvalue);
		dataWatcher.updateObject(dataWatch_Flags, Integer.valueOf(li));
	}

	public boolean setMaidMode(int pindex) {
		return setMaidMode(pindex, false);
	}

	public boolean setMaidMode(int pindex, boolean pplaying) {
		// モードに応じてAIを切り替える
		velocityChanged = true;
		if (!maidModeList.containsKey(pindex)) {
			return false;
		}
		if (maidMode == pindex) {
			return true;
		}

		if (pplaying) {

		} else {
			mstatWorkingInt = pindex;
		}
		mstatModeName = getMaidModeString(pindex);
		maidMode = pindex;
		setMaidColorMode();
		EntityAITasks[] ltasks = maidModeList.get(pindex);

		// AIを根底から書き換える
		if (ltasks.length > 0 && ltasks[0] != null) {
			setMaidModeAITasks(ltasks[0], tasks);
		} else {
			setMaidModeAITasks(null, tasks);
		}
		if (ltasks.length > 1 && ltasks[1] != null) {
			setMaidModeAITasks(ltasks[1], targetTasks);
		} else {
			setMaidModeAITasks(null, targetTasks);
		}

		// モード切替に応じた処理系を確保
		setSitting(false);
		setSneaking(false);
		setActiveModeClass(null);
		aiJumpTo.setEnable(true);
		//		aiFollow.setEnable(true);
		aiAttack.setEnable(true);
		aiShooting.setEnable(false);
		aiAvoidPlayer.setEnable(true);
		//		aiWander.setEnable(maidFreedom);
		setBloodsuck(false);
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			LMM_EntityModeBase iem = maidEntityModeList.get(li);
			if (iem.setMode(maidMode)) {
				setActiveModeClass(iem);
				break;
			}
		}
		getNextEquipItem();

		return true;
	}

	public boolean setMaidMode(String pname) {
		return setMaidMode(pname, false);
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean updateMaidContract() {
		// ���ꐫ�̃`�F�b�N
		boolean lf = isContract();
		if (maidContract != lf) {
			maidContract = lf;
			return true;
=======
	public boolean setMaidMode(String pname, boolean pplaying) {
		if (!maidModeIndexList.containsKey(pname)) {
			return false;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
		return setMaidMode(maidModeIndexList.get(pname), pplaying);
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public Entity getOwner() {
		return getMaidMasterEntity();
	}
	public String getMaidMaster() {
		return getOwnerName();
	}

	public EntityPlayer getMaidMasterEntity() {
		// ����l��
		if (isContract()) {
			EntityPlayer entityplayer = mstatMasterEntity;
			if (mstatMasterEntity == null || mstatMasterEntity.isDead) {
				String lname; 
				// �T�[�o�[���Ȃ炿���ƃI�[�i���肷��
				if (!MMM_Helper.isClient
						|| mod_LMM_littleMaidMob.checkOwnerName 
						|| MMM_Helper.mc.thePlayer == null) {
					lname = getMaidMaster();
				} else {
					lname = MMM_Helper.mc.thePlayer.username;
				}
				entityplayer = worldObj.getPlayerEntityByName(lname);
				// �Ƃ肠������̖��O�����Ă݂�
				// TODO:�Đݒ�͕s�ɂȂ����̂Ōo�ߊώ@
//				maidAvatar.username = lname;
				
				if (entityplayer != null && maidAvatar != null) {
					maidAvatar.capabilities.isCreativeMode = entityplayer.capabilities.isCreativeMode;
=======
	public void setMaidModeAITasks(EntityAITasks pTasksSRC, EntityAITasks pTasksDEST) {
		// 既存のAIを削除して置き換える。
		// 動作をクリア
		try {
			ArrayList<EntityAITaskEntry> ltasksDoDEST = (ArrayList<EntityAITaskEntry>) ModLoader.getPrivateValue(
					EntityAITasks.class, pTasksDEST, 0);
			ArrayList<EntityAITaskEntry> ltasksExeDEST = (ArrayList<EntityAITaskEntry>) ModLoader.getPrivateValue(
					EntityAITasks.class, pTasksDEST, 1);

			if (pTasksSRC == null) {
				ltasksDoDEST.clear();
				ltasksExeDEST.clear();
			} else {
				ArrayList<EntityAITaskEntry> ltasksDoSRC = (ArrayList<EntityAITaskEntry>) ModLoader.getPrivateValue(
						EntityAITasks.class, pTasksSRC, 0);
				ArrayList<EntityAITaskEntry> ltasksExeSRC = (ArrayList<EntityAITaskEntry>) ModLoader.getPrivateValue(
						EntityAITasks.class, pTasksSRC, 1);

				Iterator iterator;
				iterator = ltasksExeDEST.iterator();
				while (iterator.hasNext()) {
					EntityAITaskEntry ltaskentory = (EntityAITaskEntry) iterator.next();
					ltaskentory.action.resetTask();
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
				}
				ltasksExeDEST.clear();

				ltasksDoDEST.clear();
				ltasksDoDEST.addAll(ltasksDoSRC);
			}
		} catch (Exception s) {
		}

	}

	public void setMaidWait(boolean pflag) {
		// 待機常態の設定、 isMaidWait系でtrueを返すならAIが勝手に移動を停止させる。
		maidWait = pflag;
		setMaidFlags(pflag, dataWatch_Flags_Wait);

		aiSit.setSitting(pflag);
		maidWait = pflag;
		isJumping = false;
		setAttackTarget(null);
		setRevengeTarget(null);
		setPathToEntity(null);
		getNavigator().clearPathEntity();
		velocityChanged = true;
	}

	public void setMaidWaitCount(int count) {
		mstatWaitCount = count;
	}

	// インベントリの表示関係
	// まさぐれるのは一人だけ
	public void setOpenInventory(boolean flag) {
		mstatOpenInventory = flag;
	}

	// お遊びモード
	public void setPlayingRole(int pValue) {
		if (mstatPlayingRole != pValue) {
			mstatPlayingRole = pValue;
			if (pValue == 0) {
				setAttackTarget(null);
				setMaidMode(mstatWorkingInt, true);
			} else {
				setMaidMode(0x00ff, true);
			}
		}
	}

	// 腕振り
	public void setSwing(int attacktime, LMM_EnumSound enumsound) {
		setSwing(attacktime, enumsound, maidDominantArm);
	}

	public void setSwing(int pattacktime, LMM_EnumSound enumsound, int pArm) {
		mstatSwingStatus[pArm].attackTime = pattacktime;
		//		maidAttackSound = enumsound;
		//        soundInterval = 0;// いるか？
		if (!weaponFullAuto) {
			setSwinging(pArm, enumsound);
		}
		if (!worldObj.isRemote) {
			byte[] lba = new byte[] {
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
				LMM_Statics.LMN_Client_SwingArm,
				0, 0, 0, 0,
				(byte)pArm,
				0, 0, 0, 0
=======
					LMM_Net.LMN_Client_SwingArm,
					0, 0, 0, 0,
					(byte) pArm,
					0, 0, 0, 0
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
			};
			MMM_Helper.setInt(lba, 6, enumsound.index);
			LMM_Net.sendToAllEClient(this, lba);
		}
	}

	public void setSwinging(int pArm, LMM_EnumSound pSound) {
		if (mstatSwingStatus[pArm].setSwinging()) {
			playLittleMaidSound(pSound, true);
			maidAvatar.field_110158_av = -1;
//			maidAvatar.swingProgressInt = -1;
			maidAvatar.isSwingInProgress = true;
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean getSwinging() {
		return getSwinging(maidDominantArm);
	}
	public boolean getSwinging(int pArm) {
		return mstatSwingStatus[pArm].isSwingInProgress;
	}

	/**
	 * �����r�̃����[�h�^�C��
	 */
	public LMM_SwingStatus getSwingStatusDominant() {
		return mstatSwingStatus[maidDominantArm];
	}

	public LMM_SwingStatus getSwingStatus(int pindex) {
		return mstatSwingStatus[pindex];
	}


	// �����̃��C�h�͌��ɋQ���Ă���
	public void setBloodsuck(boolean pFlag) {
		mstatBloodsuck = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Bloodsuck);
	}

	public boolean isBloodsuck() {
		return mstatBloodsuck;
	}


	// �����֘A
	public void setLookSuger(boolean pFlag) {
		mstatLookSuger = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_LooksSugar);
	}

	public boolean isLookSuger() {
		return mstatLookSuger;
	}

	/**
	 * �y���b�E�E�E����́E�E�E�����b�I�I
	 * motion : �r��U�邩�H
	 * recontract : �_�񉄒����ʃA���H
	 */
	public void eatSugar(boolean motion, boolean recontract) {
		if (motion) {
			setSwing(2, (func_110138_aP() - func_110143_aJ() <= 1F) ?  LMM_EnumSound.eatSugar_MaxPower : LMM_EnumSound.eatSugar);
		}
		int h = hurtResistantTime;
		heal(1);
		hurtResistantTime = h;
		playSound("random.pop");
		mod_LMM_littleMaidMob.Debug(("eat Suger." + worldObj.isRemote));
		
		if (recontract) {
			// �_����Ԃ̉���
			maidContractLimit += 24000;
			if (maidContractLimit > 168000) {
				maidContractLimit = 168000;	// 24000 * 7
			}
		}
		
		// �b�菈��
		if (maidAvatar != null) {
			maidAvatar.foodStats.addStats(20, 20F);
		}
	}


	// ���d���`��
	/**
	 * �d�������ǂ����̐ݒ�
	 */
	public void setWorking(boolean pFlag) {
		mstatWorkingCount.setEnable(pFlag);
	}
	
	/**
	 * �d�������ǂ�����Ԃ�
	 */
	public boolean isWorking() {
		return mstatWorkingCount.isEnable();
	}

	/**
	 * �d�����I�����Ă��]�C���܂߂ĕԂ�
	 */
	public boolean isWorkingDelay() {
		return mstatWorkingCount.isDelay();
	}

	/**
	 * �g���[�T�[���[�h�̐ݒ�
	 */
	public void setTracer(boolean pFlag) {
		maidTracer = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Tracer);
		if (maidTracer) {
			setFreedom(true);
		}
		aiTracer.setEnable(pFlag);
	}

	/**
	 * �g���[�T�[���[�h�ł��邩�H
	 */
	public boolean isTracer() {
		return maidTracer;
	}


	// ���V�у��[�h
	public void setPlayingRole(int pValue) {
		if (mstatPlayingRole != pValue) {
			mstatPlayingRole = pValue;
			if (pValue == 0) {
				setAttackTarget(null);
				setMaidMode(mstatWorkingInt , true);
			} else {
				setMaidMode(0x00ff, true);
			}
		}
	}

	public int getPlayingRole() {
		return mstatPlayingRole;
	}

	public boolean isPlaying() {
		return mstatPlayingRole != 0;
	}


	// ���R�s��
	public void setFreedom(boolean pFlag) {
		// AI�֘A�̃��Z�b�g�������ŁB
		maidFreedom = pFlag;
		aiRestrictRain.setEnable(pFlag);
		aiFreeRain.setEnable(pFlag);
		aiWander.setEnable(pFlag);
//		aiJumpTo.setEnable(!pFlag);
		aiAvoidPlayer.setEnable(!pFlag);
		aiFollow.setEnable(!pFlag);
		aiTracer.setEnable(false);
//		setAIMoveSpeed(pFlag ? moveSpeed_Nomal : moveSpeed_Max);
		setMoveForward(0.0F);
		if (maidFreedom && isContract()) {
			func_110171_b(
//			setHomeArea(
					MathHelper.floor_double(posX),
					MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ), 16);
		} else {
			func_110177_bN();
//			detachHome();
		}
		
		setMaidFlags(maidFreedom, dataWatch_Flags_Freedom);
=======
	public void setSwinging(LMM_EnumSound pSound) {
		setSwinging(maidDominantArm, pSound);
	}

	@Override
	public void setTamed(boolean par1) {
		setMaidContract(par1);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	@Override
	public void setTexturePackIndex(int pColor, int[] pIndex) {
		// Server
		textureIndex = pIndex[0];
		textureArmorIndex = pIndex[1];
		dataWatcher.updateObject(dataWatch_Texture,
				(Integer.valueOf(textureIndex) & 0xffff) | ((Integer.valueOf(textureArmorIndex) & 0xffff) << 16));
		// TODO:この以下はホントはいらんけども修正めんどいので。
		textureName = MMM_TextureManager.getTextureBoxServer(textureIndex).textureName;
		textureArmorName = MMM_TextureManager.getTextureBoxServer(textureArmorIndex).textureName;
		// サイズの変更
		MMM_TextureBoxServer lbs = MMM_TextureManager.getTextureBoxServer(textureIndex);
		if (lbs != null) {
			setSize(-1F, 0F);
			setSize(lbs.modelWidth, lbs.modelHeight);
			mod_LMM_littleMaidMob.Debug("changeSize-ID:%d: %f, %f, %b", entityId, lbs.modelWidth, lbs.modelHeight,
					worldObj.isRemote);
		}
		setMaidColor(pColor);
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// TODO Auto-generated method stub

	}

	/**
	 * トレーサーモードの設定
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	protected boolean sendTextureToServer() {
		// 16bit����΃e�N�X�`���p�b�N�̐��ɂ�����
		MMM_TextureManager.instance.postSetTexturePack(this, maidColor, textureBox);
		return true;
=======
	public void setTracer(boolean pFlag) {
		maidTracer = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Tracer);
		if (maidTracer) {
			setFreedom(true);
		}
		aiTracer.setEnable(pFlag);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	// お仕事チュ
	/**
	 * 仕事中かどうかの設定
	 */
	public void setWorking(boolean pFlag) {
		mstatWorkingCount.setEnable(pFlag);
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean updateTexturePack() {
		// �e�N�X�`���p�b�N���X�V����Ă��Ȃ������`�F�b�N
		// �N���C�A���g����
		boolean lflag = false;
		MMM_TextureBoxServer lbox;
		
		int ltexture = dataWatcher.getWatchableObjectInt(dataWatch_Texture);
		int larmor = (ltexture >>> 16) & 0xffff;
		ltexture &= 0xffff;
		if (textureIndex[0] != ltexture) {
			textureIndex[0] = ltexture;
			lflag = true;
		}
		if (textureIndex[1] != larmor) {
			textureIndex[1] = larmor;
			lflag = true;
		}
		if (lflag) {
			MMM_TextureManager.instance.postGetTexturePack(this, textureIndex);
		}
		return lflag;
	}

	@Override
	public int getColor() {
		return (dataWatcher.getWatchableObjectInt(dataWatch_ColorMode) >>> 16) & 0xff;
	}

	@Override
	public void setColor(int index) {
		maidColor = index & 0x00ff;
		setMaidColorMode();
	}

	protected void setMaidColorMode() {
		if (worldObj == null || worldObj.isRemote) return;
		dataWatcher.updateObject(dataWatch_ColorMode,
				(maidMode & 0xffff) | ((maidColor & 0x00ff) << 16) | ((maidDominantArm & 0x00ff) << 24));
	}

	public boolean updateMaidColor() {
		// ���ꐫ�̃`�F�b�N
		int lc = getColor();
		if ((maidColor & 0x00ff) != lc) {
			maidColor = lc;
			return true;
=======
	// エフェクト表示
	public void showParticleFX(String s) {
		showParticleFX(s, 1D, 1D, 1D);
	}

	public void showParticleFX(String s, double d, double d1, double d2) {
		showParticleFX(s, d, d1, d2, 0D, 0D, 0D);
	}

	public void showParticleFX(String s, double d, double d1, double d2, double d3, double d4, double d5) {
		for (int i = 0; i < 7; i++) {
			double d6 = rand.nextGaussian() * d + d3;
			double d7 = rand.nextGaussian() * d1 + d4;
			double d8 = rand.nextGaussian() * d2 + d5;
			worldObj.spawnParticle(s, (posX + rand.nextFloat() * width * 2.0F) - width, posY + 0.5D + rand.nextFloat()
					* height, (posZ + rand.nextFloat() * width * 2.0F) - width, d6, d7, d8);
		}
	}

	/**
	 * 弓構えを更新
	 */
	public void updateAimebow() {
		boolean lflag = (maidAvatar != null && maidAvatar.isUsingItemLittleMaid()) || mstatAimeBow;
		setMaidFlags(lflag, dataWatch_Flags_Aimebow);
	}

	@Override
	public void updateAITick() {
		// AI対応型はこっちが呼ばれる
		dataWatcher.updateObject(dataWatch_Health, Integer.valueOf(getHealth()));

		// 追加分
		for (LMM_EntityModeBase ieml : maidEntityModeList) {
			ieml.updateAITick(getMaidModeInt());
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

	/**
	 * 紐の持ち主
	 */
	public void updateGotcha() {
		int lid = dataWatcher.getWatchableObjectInt(dataWatch_Gotcha);
		if (lid == 0) {
			mstatgotcha = null;
			return;
		}
		if (mstatgotcha != null && mstatgotcha.entityId == lid) {
			return;
		}
		for (int li = 0; li < worldObj.loadedEntityList.size(); li++) {
			if (((Entity) worldObj.loadedEntityList.get(li)).entityId == lid) {
				mstatgotcha = (Entity) worldObj.loadedEntityList.get(li);
				break;
			}
		}
	}

	public boolean updateMaidColor() {
		// 同一性のチェック
		int lc = getMaidColor();
		if ((maidColor & 0x00ff) != lc) {
			maidColor = lc;
			return true;
		}
		return false;
	}

	public boolean updateMaidContract() {
		// 同一性のチェック
		boolean lf = isMaidContract();
		if (maidContract != lf) {
			maidContract = lf;
			return true;
		}
		return false;
	}

	/**
	 * 各種フラグのアップデート
	 */
	public void updateMaidFlagsClient() {
		int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
		maidFreedom = (li & dataWatch_Flags_Freedom) > 0;
		maidTracer = (li & dataWatch_Flags_Tracer) > 0;
		maidWait = (li & dataWatch_Flags_Wait) > 0;
		mstatAimeBow = (li & dataWatch_Flags_Aimebow) > 0;
		mstatLookSuger = (li & dataWatch_Flags_LooksSugar) > 0;
		mstatBloodsuck = (li & dataWatch_Flags_Bloodsuck) > 0;
		looksWithInterest = (li & dataWatch_Flags_looksWithInterest) > 0;
		looksWithInterestAXIS = (li & dataWatch_Flags_looksWithInterestAXIS) > 0;
		maidOverDriveTime.updateClient((li & dataWatch_Flags_OverDrive) > 0);
		mstatWorkingCount.updateClient((li & dataWatch_Flags_Working) > 0);
	}

	/**
	 * 契約期間の残りがあるかを確認
	 */
	public void updateRemainsContract() {
		boolean lflag = false;
		if (maidContractLimit > 0) {
			maidContractLimit--;
			lflag = true;
		}
		if (getMaidFlags(dataWatch_Flags_remainsContract) != lflag) {
			setMaidFlags(lflag, dataWatch_Flags_remainsContract);
		}
	}

	@Override
	public void updateRidden() {
		// TODO:アップデート時にチェック
		++ticksExisted;
		//

		if (ridingEntity instanceof EntityPlayer) {
			EntityPlayer lep = (EntityPlayer) ridingEntity;

			// ヘッドハガー
			renderYawOffset = lep.renderYawOffset;
			prevRenderYawOffset = lep.prevRenderYawOffset;
			double llpx = lastTickPosX;
			double llpy = lastTickPosY;
			double llpz = lastTickPosZ;

			super.updateRidden();

			renderYawOffset = lep.renderYawOffset;
			if (((rotationYaw - renderYawOffset) % 360F) > 90F) {
				rotationYaw = renderYawOffset + 90F;
			}
			if (((rotationYaw - renderYawOffset) % 360F) < -90F) {
				rotationYaw = renderYawOffset - 90F;
			}
			if (((rotationYawHead - renderYawOffset) % 360F) > 90F) {
				rotationYawHead = renderYawOffset + 90F;
			}
			if (((rotationYawHead - renderYawOffset) % 360F) < -90F) {
				rotationYawHead = renderYawOffset - 90F;
			}

			double dx = Math.sin((lep.renderYawOffset * Math.PI) / 180D) * 0.35D;
			double dz = Math.cos((lep.renderYawOffset * Math.PI) / 180D) * 0.35D;
			setPosition(lep.posX + dx, posY, lep.posZ - dz);
			lastTickPosX = llpx;
			lastTickPosY = llpy;
			lastTickPosZ = llpz;
		} else {
			super.updateRidden();
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
	}

	public boolean updateTexturePack() {
		// テクスチャパックが更新されていないかをチェック
		// クライアント側の
		boolean lflag = false;
		MMM_TextureBoxServer lbox;

		int ltexture = dataWatcher.getWatchableObjectInt(dataWatch_Texture);
		int larmor = (ltexture >>> 16) & 0xffff;
		ltexture &= 0xffff;
		if (textureIndex > -1 && ltexture != textureIndex) {
			lflag = true;
		}
		if (textureArmorIndex > -1 && larmor != textureArmorIndex) {
			lflag = true;
		}

		if (lflag) {
			MMM_TextureManager.postGetTexturePack(this, new int[] { textureIndex, textureArmorIndex });
		}
		return lflag;
	}

	@Override
	public void func_110171_b(int par1, int par2, int par3, int par4) {
		homeWorld = dimension;
		super.func_110171_b(par1, par2, par3, par4);
//		super.setHomeArea(par1, par2, par3, par4);
	}

	@Override
	public void setTexturePackIndex(int pColor, int[] pIndex) {
		// Server
		textureIndex[0] = pIndex[0];
		textureIndex[1] = pIndex[1];
		dataWatcher.updateObject(dataWatch_Texture, (Integer.valueOf(textureIndex[0]) & 0xffff) | ((Integer.valueOf(textureIndex[1]) & 0xffff) << 16));
		textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[1]);
		// �T�C�Y�̕ύX
		setSize(textureBox[0].getWidth(maidCaps), textureBox[0].getHeight(maidCaps));
		func_98054_a(false);
		mod_LMM_littleMaidMob.Debug("changeSize-ID:%d: %f, %f, %b", entityId, width, height, worldObj.isRemote);
		setColor(pColor);
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// Client
		textureBox[0] = pTextureBox[0];
		textureBox[1] = pTextureBox[1];
		setTextureNames();
		// �g���ύX�p
		setSize(textureBox[0].getWidth(maidCaps), textureBox[0].getHeight(maidCaps));
		func_98054_a(false);
		setPosition(posX, posY, posZ);
		mod_LMM_littleMaidMob.Debug("ID:%d, TextureModel:%s", entityId, textureBox[0].textureName);
		// ���f���̏�����
		if (((MMM_TextureBox)textureBox[0]).models[0] instanceof MMM_ModelMultiMMMBase) {
			((MMM_ModelMultiMMMBase)((MMM_TextureBox)textureBox[0]).models[0]).changeModel(maidCaps);
		}
		// �X�^�r�̕t���ւ�
//		for (Entry<String, MMM_EquippedStabilizer> le : pEntity.maidStabilizer.entrySet()) {
//			if (le.getValue() != null) {
//				le.getValue().updateEquippedPoint(pEntity.textureModel0);
//			}
//		}
		maidSoundRate = LMM_SoundManager.getSoundRate(textureBox[0].textureName, maidColor);

	}

	/**
	 * Client�p
	 */
	public void setTextureNames() {
		if (!(textureBox[0] instanceof MMM_TextureBox) && !(textureBox[1] instanceof MMM_TextureBox)) return;
		int lc = (maidColor & 0x00ff) + (isContract() ? 0 : MMM_TextureManager.tx_wild);
		if (((MMM_TextureBox)textureBox[0]).hasColor(lc)) {
			textures[0][0] = ((MMM_TextureBox)textureBox[0]).getTextureName(lc);
			textures[0][1] = ((MMM_TextureBox)textureBox[0]).getTextureName(lc + MMM_TextureManager.tx_eye);
			for (int i = 0; i < 4; i++) {
				ItemStack is = maidInventory.armorItemInSlot(i);
				textures[1][i] = ((MMM_TextureBox)textureBox[1]).getArmorTextureName(MMM_TextureManager.tx_armor1, is);
				textures[2][i] = ((MMM_TextureBox)textureBox[1]).getArmorTextureName(MMM_TextureManager.tx_armor1, is);
			}
		} else {
			// TODO:setDefaultTexture
			setNextTexturePackege(0);
//			MMM_TextureManager.postSetTexturePack(this, maidColor & 0x00ff, textureBox);
		}
	}

	public void setNextTexturePackege(int pTargetTexture) {
		if (pTargetTexture == 0) {
			int lc = (maidColor & 0x00ff) + (isContract() ? 0 : MMM_TextureManager.tx_wild);
			textureBox[0] = MMM_TextureManager.instance.getNextPackege((MMM_TextureBox)textureBox[0], lc);
			if (textureBox[0] == null) {
				// �w��F�������ꍇ�͕W�����f����
				textureBox[0] = textureBox[1] = MMM_TextureManager.instance.getDefaultTexture(this);
				maidColor = 12;
			} else {
				textureBox[1] = textureBox[0];
			}
			if (!((MMM_TextureBox)textureBox[1]).hasArmor()) {
				pTargetTexture = 1;
			}
		}
		if (pTargetTexture == 1) {
			textureBox[1] = MMM_TextureManager.instance.getNextArmorPackege((MMM_TextureBox)textureBox[1]);
		}
	}

	public void setPrevTexturePackege(int pTargetTexture) {
		if (pTargetTexture == 0) {
			int lc = (maidColor & 0x00ff) + (isContract() ? 0 : MMM_TextureManager.tx_wild);
			textureBox[0] = MMM_TextureManager.instance.getPrevPackege((MMM_TextureBox)textureBox[0], lc);
			textureBox[1] = textureBox[0];
			if (!((MMM_TextureBox)textureBox[1]).hasArmor()) {
				pTargetTexture = 1;
			}
		}
		if (pTargetTexture == 1) {
			textureBox[1] = MMM_TextureManager.instance.getPrevArmorPackege((MMM_TextureBox)textureBox[1]);
		}
	}


	// textureEntity

	@Override
	public void setTextureBox(MMM_TextureBoxBase[] pTextureBox) {
		textureBox = pTextureBox;
	}

	@Override
	public MMM_TextureBoxBase[] getTextureBox() {
		return textureBox;
	}

	@Override
	public void setTextureIndex(int[] pTextureIndex) {
		textureIndex = pTextureIndex;
	}

	@Override
	public int[] getTextureIndex() {
		return textureIndex;
	}

	@Override
	public void setTextures(int pIndex, ResourceLocation[] pNames) {
		textures[pIndex] = pNames;
	}

	@Override
	public ResourceLocation[] getTextures(int pIndex) {
		return textures[pIndex];
	}

	// Tile�֌W

	/**
	 * 対象にポーションを使う。
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean isUsingTile(TileEntity pTile) {
		if (isActiveModeClass()) {
			return getActiveModeClass().isUsingTile(pTile);
=======
	public void usePotionTotarget(EntityLiving entityliving) {
		ItemStack itemstack = maidInventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
			// ポーション効果の発動
			itemstack.stackSize--;
			List list = ((ItemPotion) itemstack.getItem()).getEffects(itemstack);
			if (list != null) {
				PotionEffect potioneffect;
				for (Iterator iterator = list.iterator(); iterator.hasNext(); entityliving
						.addPotionEffect(new PotionEffect(potioneffect))) {
					potioneffect = (PotionEffect) iterator.next();
				}
			}
			if (itemstack.stackSize <= 0) {
				maidInventory.setInventoryCurrentSlotContents(new ItemStack(Item.glassBottle));
			} else {
				maidInventory.addItemStackToInventory(new ItemStack(Item.glassBottle));
			}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
		for (int li = 0; li < maidTiles.length; li++) {
			if (maidTiles[li] != null &&
					pTile.xCoord == maidTiles[li][0] &&
					pTile.yCoord == maidTiles[li][1] &&
					pTile.zCoord == maidTiles[li][2]) {
				return true;
			}
		}
		return false;
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean isEqualTile() {
		return worldObj.getBlockTileEntity(maidTile[0], maidTile[1], maidTile[2]) == maidTileEntity;
	}

	public boolean isTilePos() {
		return maidTileEntity != null;
	}
	public boolean isTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			return maidTiles[pIndex] != null;
		}
		return false;
	}

	/**
	 * ���[�J���ϐ���Tile�̈ʒu������B
	 */
	public boolean getTilePos(int pIndex) {
		if (pIndex < maidTiles.length && maidTiles[pIndex] != null) {
			maidTile[0] = maidTiles[pIndex][0];
			maidTile[1] = maidTiles[pIndex][1];
			maidTile[2] = maidTiles[pIndex][2];
			return true;
		}
		return false;
	}

	public void setTilePos(int pX, int pY, int pZ) {
		maidTile[0] = pX;
		maidTile[1] = pY;
		maidTile[2] = pZ;
	}
	public void setTilePos(TileEntity pEntity) {
		maidTile[0] = pEntity.xCoord;
		maidTile[1] = pEntity.yCoord;
		maidTile[2] = pEntity.zCoord;
		maidTileEntity = pEntity;
	}
	public void setTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			if (maidTiles[pIndex] == null) {
				maidTiles[pIndex] = new int[3];
			}
			maidTiles[pIndex][0] = maidTile[0];
			maidTiles[pIndex][1] = maidTile[1];
			maidTiles[pIndex][2] = maidTile[2];
		}
	}
	public void setTilePos(int pIndex, int pX, int pY, int pZ) {
		if (pIndex < maidTiles.length) {
			if (maidTiles[pIndex] == null) {
				maidTiles[pIndex] = new int[3];
			}
			maidTiles[pIndex][0] = pX;
			maidTiles[pIndex][1] = pY;
			maidTiles[pIndex][2] = pZ;
		}
	}

	public TileEntity getTileEntity() {
		return maidTileEntity = worldObj.getBlockTileEntity(maidTile[0], maidTile[1], maidTile[2]);
	}
	public TileEntity getTileEntity(int pIndex) {
		if (pIndex < maidTiles.length && maidTiles[pIndex] != null) {
			TileEntity ltile = worldObj.getBlockTileEntity(
					maidTiles[pIndex][0], maidTiles[pIndex][1], maidTiles[pIndex][2]);
			if (ltile == null) {
				clearTilePos(pIndex);
			}
			return ltile;
		}
		return null;
	}

	public void clearTilePos() {
		maidTileEntity = null;
	}
	public void clearTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			maidTiles[pIndex] = null;
		}
	}
	public void clearTilePosAll() {
		for (int li = 0; li < maidTiles.length; li++) {
			maidTiles[li] = null;
		}
	}

	public double getDistanceTilePos() {
		return getDistance(
				(double)maidTile[0] + 0.5D,
				(double)maidTile[1] + 0.5D,
				(double)maidTile[2] + 0.5D);
	}
	public double getDistanceTilePosSq() {
		return getDistanceSq(
				(double)maidTile[0] + 0.5D,
				(double)maidTile[1] + 0.5D,
				(double)maidTile[2] + 0.5D);
	}

	public double getDistanceTilePos(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			return getDistance(
					(double)maidTiles[pIndex][0] + 0.5D,
					(double)maidTiles[pIndex][1] + 0.5D,
					(double)maidTiles[pIndex][2] + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePosSq(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			return getDistanceSq(
					(double)maidTiles[pIndex][0] + 0.5D,
					(double)maidTiles[pIndex][1] + 0.5D,
					(double)maidTiles[pIndex][2] + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePos(TileEntity pTile) {
		if (pTile != null) {
			return getDistance(
					(double)pTile.xCoord + 0.5D,
					(double)pTile.yCoord + 0.5D,
					(double)pTile.zCoord + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePosSq(TileEntity pTile) {
		if (pTile != null) {
			return getDistanceSq(
					(double)pTile.xCoord + 0.5D,
					(double)pTile.yCoord + 0.5D,
					(double)pTile.zCoord + 0.5D);
		}
		return -1D;
	}

	public void looksTilePos() {
		getLookHelper().setLookPosition(
				maidTile[0] + 0.5D, maidTile[1] + 0.5D, maidTile[2] + 0.5D,
				10F, getVerticalFaceSpeed());
	}
	public void looksTilePos(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			getLookHelper().setLookPosition(
					maidTiles[pIndex][0] + 0.5D,
					maidTiles[pIndex][1] + 0.5D,
					maidTiles[pIndex][2] + 0.5D,
					10F, getVerticalFaceSpeed());
=======
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		// データセーブ
		super.writeEntityToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setTag("Inventory", maidInventory.writeToNBT(new NBTTagList()));
		par1nbtTagCompound.setString("Mode", getMaidModeString(mstatWorkingInt));
		par1nbtTagCompound.setBoolean("Wait", isMaidWait());
		par1nbtTagCompound.setBoolean("Freedom", isFreedom());
		par1nbtTagCompound.setBoolean("Tracer", isTracer());
		par1nbtTagCompound.setInteger("LimitCount", maidContractLimit);
		par1nbtTagCompound.setLong("Anniversary", maidAnniversary);
		par1nbtTagCompound.setInteger("EXP", experienceValue);
		par1nbtTagCompound.setInteger("DominantArm", maidDominantArm);
		par1nbtTagCompound.setInteger("Color", maidColor);
		if (textureName == null) {
			par1nbtTagCompound.setString("texName", "");
		} else {
			par1nbtTagCompound.setString("texName", textureName);
		}
		if (textureArmorName == null) {
			par1nbtTagCompound.setString("texArmor", "");
		} else {
			par1nbtTagCompound.setString("texArmor", textureArmorName);
		}
		textureName = par1nbtTagCompound.getString("texName");
		textureArmorName = par1nbtTagCompound.getString("texArmor");
		// HomePosition
		par1nbtTagCompound.setInteger("homeX", getHomePosition().posX);
		par1nbtTagCompound.setInteger("homeY", getHomePosition().posY);
		par1nbtTagCompound.setInteger("homeZ", getHomePosition().posZ);
		par1nbtTagCompound.setInteger("homeWorld", homeWorld);

		// 追加分
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			maidEntityModeList.get(li).writeEntityToNBT(par1nbtTagCompound);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

}