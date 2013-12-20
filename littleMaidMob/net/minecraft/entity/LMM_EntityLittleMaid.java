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
	// ’è”‚ÍStatics‚ÖˆÚ“®


	// •Ï”Œ¸‚ç‚µ‚½‚¢‚È‚Ÿ
//    protected long maidContractLimit;		// Œ_–ñ¸Œø“ú
	protected int maidContractLimit;		// Œ_–ñŠúŠÔ
	protected long maidAnniversary;			// Œ_–ñ“úUID‚Æ‚µ‚Äg—p
	protected int maidDominantArm;			// —˜‚«˜rA1Byte
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
	public LMM_EntityCaps maidCaps;	// Client‘¤‚Ì‚İ
	
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
	
	// “®“I‚Èó‘Ô
	protected EntityPlayer mstatMasterEntity;	// å
	protected double mstatMasterDistanceSq;		// å‚Æ‚Ì‹——£AŒvZŒy—Ê‰»—p
	protected Entity mstatgotcha;				// ƒƒCƒ„[ƒh—p
	protected boolean mstatBloodsuck;
	protected boolean mstatClockMaid;
	// ƒ}ƒXƒN”»’è
	protected int mstatMaskSelect;
	// ’Ç‰Á‚Ì“ª•”‘•”õ
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
	// ˜rU‚è
	public LMM_SwingStatus mstatSwingStatus[]; 
	public boolean mstatAimeBow;
	// ñü‚è
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
	// TODO:å®šæ•°ã¯è¦ä¿®æ­£
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
	 * å€‹ä½“ã”ã¨ã«å€¤ã‚’ãƒãƒ©ã¤ã‹ã›ã‚‹ã®ã«ä½¿ã†ã€‚
	 */
	public float entityIdFactor;
	// å®Ÿé¨“ç”¨
	private int firstload = 1;
	public int homeWorld;
	// é¦–å‘¨ã‚Š
	private boolean looksWithInterest;
	private boolean looksWithInterestAXIS;
	// ActiveModeClass
	public LMM_EntityModeBase maidActiveModeClass;
	public long maidAnniversary; // å¥‘ç´„æ—¥UIDã¨ã—ã¦ä½¿ç”¨

	public LMM_EntityLittleMaidAvatar maidAvatar;
	public boolean maidCamouflage;
	public LMM_EntityCaps maidCaps; // Clientå´ã®ã¿
	public int maidColor; // 1Byte
	public boolean maidContract;
	// å¤‰æ•°æ¸›ã‚‰ã—ãŸã„ãªã
	//    public long maidContractLimit;		// å¥‘ç´„å¤±åŠ¹æ—¥
	public int maidContractLimit; // å¥‘ç´„æœŸé–“
	// éŸ³å£°
	//	public LMM_EnumSound maidAttackSound;
	public LMM_EnumSound maidDamegeSound;
	public int maidDominantArm; // åˆ©ãè…•ã€1Byte
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
	// è¿½åŠ ã®é ­éƒ¨è£…å‚™
	public boolean mstatCamouflage;
	public boolean mstatClockMaid;

	public boolean mstatFirstLook;

	public Entity mstatgotcha; // ãƒ¯ã‚¤ãƒ¤ãƒ¼ãƒ‰ç”¨
	public boolean mstatLookSuger;
	// ãƒã‚¹ã‚¯åˆ¤å®š
	public int mstatMaskSelect;

	public double mstatMasterDistanceSq; // ä¸»ã¨ã®è·é›¢ã€è¨ˆç®—è»½é‡åŒ–ç”¨
	// å‹•çš„ãªçŠ¶æ…‹
	public EntityPlayer mstatMasterEntity; // ä¸»
	public String mstatModeName;

	public boolean mstatOpenInventory;
	public boolean mstatPlanter;

	public int mstatPlayingRole;
	// è…•æŒ¯ã‚Š
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
	public boolean weaponFullAuto; // è£…å‚™ãŒãƒ•ãƒ«ã‚ªãƒ¼ãƒˆæ­¦å™¨ã‹ã©ã†ã‹
	public boolean weaponReload; // è£…å‚™ãŒãƒªãƒ­ãƒ¼ãƒ‰ã‚’æ¬²ã—ã¦ã„ã‚‹ã‹ã©ã†ã‹

	public LMM_EntityLittleMaid(World par1World) {
		super(par1World);
		// åˆæœŸè¨­å®š
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
		// ˜rU‚è
		mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus()};
=======

		entityIdFactor = entityId * 70;
		// è…•æŒ¯ã‚Š
		mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus() };
		//		maidDominantArm = rand.nextInt(mstatSwingStatus.length);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		setDominantArm(rand.nextInt(mstatSwingStatus.length));

		// å†ç”ŸéŸ³å£°
		//		maidAttackSound = LMM_EnumSound.attack;
		maidDamegeSound = LMM_EnumSound.hurt;
		maidSoundInterval = 0;
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
		
		// –ì¶í—p‰Šú’lİ’è
		setEntityHealth(15F);
		
		// ˆÚ“®—pƒtƒBƒWƒJƒ‹İ’è
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
//		setAIMoveSpeed(moveSpeed_Nomal);
//		setAIMoveSpeed(1.0F);
		
		
		// TODO:‚±‚ê‚ÍƒeƒXƒg
//		maidStabilizer.put("HeadTop", MMM_StabilizerManager.getStabilizer("WitchHat", "HeadTop"));
		
		
		
		// Œ`‘ÔŒ`¬ê
		MMM_TextureBox ltb[] = new MMM_TextureBox[2];
		maidColor = 12;
		ltb[0] = ltb[1] = MMM_TextureManager.instance.getDefaultTexture(this);
		setTexturePackName(ltb);
//		mod_LMM_littleMaidMob.Debug("ltb[0]%s", ltb[0] == null ? "NULL" : ltb[0].textureName);
//		maidSoundRate = LMM_SoundManager.getSoundRate(textureBox[0].textureName, maidColor);
		
		// ƒ‚ƒfƒ‹ƒŒƒ“ƒ_ƒŠƒ“ƒO—p‚Ìƒtƒ‰ƒOŠl“¾—pƒwƒ‹ƒp[ŠÖ”
		maidCaps = new LMM_EntityCaps(this);
		
		// EntityMode‚Ì’Ç‰Á
=======

		// é‡ç”Ÿç¨®ç”¨åˆæœŸå€¤è¨­å®š
		health = 15;

		// ç§»å‹•ç”¨ãƒ•ã‚£ã‚¸ã‚«ãƒ«è¨­å®š
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
		moveSpeed = moveSpeed_Nomal;

		// TODO:ã“ã‚Œã¯ãƒ†ã‚¹ãƒˆ
		//		maidStabilizer.put("HeadTop", MMM_StabilizerManager.getStabilizer("WitchHat", "HeadTop"));

		// å½¢æ…‹å½¢æˆå ´
		textureName = textureArmorName = "default";
		maidColor = 12;
		textureIndex = textureArmorIndex = 0;
		if (MMM_Helper.isClient) {
			LMM_Client.setTextureValue(this);
		}
		// ãƒ¢ãƒ‡ãƒ«ãƒ¬ãƒ³ãƒ€ãƒªãƒ³ã‚°ç”¨ã®ãƒ•ãƒ©ã‚°ç²å¾—ç”¨ãƒ˜ãƒ«ãƒ‘ãƒ¼é–¢æ•°
		maidCaps = new LMM_EntityCaps(this);

		maidSoundRate = LMM_SoundManager.getSoundRate(textureName, maidColor);

		// EntityModeã®è¿½åŠ 
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		maidEntityModeList = LMM_EntityModeManager.getModeList(this);

		// ãƒ¢ãƒ¼ãƒ‰ãƒªã‚¹ãƒˆ
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
		// ƒeƒNƒXƒ`ƒƒ[‚ğƒ‰ƒ“ƒ_ƒ€‚Å‘I‘ğ
		String ls;
		if (mod_LMM_littleMaidMob.defaultTexture.isEmpty()) {
			ls = MMM_TextureManager.instance.getRandomTextureString(rand);
		} else {
			ls = mod_LMM_littleMaidMob.defaultTexture;
		}
		textureIndex[0] = textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, ls);
		textureBox[0] = textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureIndex[0]);
		// –ì¶‚ÌƒƒCƒhF‚ğƒ‰ƒ“ƒ_ƒ€‚Åw’è
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
		 * DataWatcher‚ÍƒNƒ‰ƒCƒAƒ“ƒg‚©‚çƒT[ƒo[‚Ö‚Í’l‚ğ“n‚³‚È‚¢
		 */
		
		
		// 0, 1, 2, 3, 4, 5,
		// 6: HP
		// 7, 8, 9,
		// 10: ŒÅ—L–¼Ì
		// 11: –¼•t”»’è
		// 12: GrowingAge
		// 16: Tame(4), Sit(1) 
		// 17: ownerName
		
		// maidAvater—pEntityPlayerŒİŠ·•Ï”
		// 17 -> 18
		dataWatcher.addObject(18, Float.valueOf(0.0F));

		
		// “Æ©•ª
		// 18:HP:‚¢‚ç‚È‚­‚È‚Á‚½
//		dataWatcher.addObject(dataWatch_Health, new Integer(getMaxHealth()));
		// 19:maidMode(16Bit:LSB)AmaidColor(8Bit:<<16)AmaidDominantArm(8Bit:<<24);
		dataWatcher.addObject(dataWatch_ColorMode, new Integer((maidMode & 0xffff) | ((maidColor & 0xff) << 16) | ((maidDominantArm & 0xff) << 24)));
		// 20:‘I‘ğƒeƒNƒXƒ`ƒƒƒCƒ“ƒfƒbƒNƒX
		dataWatcher.addObject(dataWatch_Texture, Integer.valueOf(0));
		// 21:ƒA[ƒ}[ƒeƒNƒXƒ`ƒƒƒCƒ“ƒfƒbƒNƒX
//        dataWatcher.addObject(dataWatch_TexArmar, Integer.valueOf(0));
		// 22:ó‘Ô‘JˆÚƒtƒ‰ƒOŒQ(32Bit)
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
		// é–‰æ‰€æ¥è§¦å›é¿ç”¨
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
				
		// TODO:‚±‚ê‚¢‚ç‚È‚­‚ËH
		aiProfiler = worldObj != null && worldObj.theProfiler != null ? worldObj.theProfiler : null;

		// “®ìƒ‚[ƒh—p‚ÌTasksList‚ğ‰Šú‰»
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
		// ˆÚ“®—pAI
		ltasks[0].addTask(15, aiTracer);
		ltasks[0].addTask(16, aiFollow);
		ltasks[0].addTask(17, aiWander);
		ltasks[0].addTask(18, new EntityAILeapAtTarget(this, 0.3F));
		// Mutex‚Ì‰e‹¿‚µ‚È‚¢“Áês“®
		ltasks[0].addTask(20, aiCloseDoor);
		ltasks[0].addTask(21, aiOpenDoor);
		ltasks[0].addTask(22, aiRestrictRain);
		// ñ‚Ì“®‚«’P“Æ
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

		// æ­£å¸¸æ™‚ã¯å›å¾©å„ªå…ˆå‡¦ç†
		if (health < 10 && !isBloodsuck() && maidInventory.hasItem(Item.sugar.itemID)) {
			return true;
		}

		// ç‰¹æ®Šãªæ”»æ’ƒå‡¦ç†
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

		// æ¨™æº–å‡¦ç†
		setSwing(20, isBloodsuck() ? LMM_EnumSound.attack_bloodsuck : LMM_EnumSound.attack);
		maidAvatar.attackTargetEntityWithCurrentItem(par1Entity);
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		Entity entity = par1DamageSource.getEntity();

		// ãƒ€ãƒ¡ãƒ¼ã‚¸ã‚½ãƒ¼ã‚¹ã‚’ç‰¹å®šã—ã¦éŸ³å£°ã®è¨­å®š
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
			// éŠã³ã¯çµ‚ã‚ã‚Šã ï¼
			setPlayingRole(0);
			getNextEquipItem();
		}
		// ã‚²ãƒ¼ãƒ é›£æ˜“åº¦ã«ã‚ˆã‚‹ãƒ€ãƒ¡ãƒ¼ã‚¸ã®è£œæ­£
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
			// ãƒãƒ¼ãƒ€ãƒ¡ãƒ¼ã‚¸
			if (maidDamegeSound == LMM_EnumSound.hurt) {
				maidDamegeSound = LMM_EnumSound.hurt_nodamege;
			}
			playSound(maidDamegeSound, true);
			return false;
		}

		if (super.attackEntityFrom(par1DamageSource, par2)) {
			//å¥‘ç´„è€…ã®åå‰ãƒã‚§ãƒƒã‚¯ã¯ãƒãƒ«ãƒç”¨
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
				// ãƒ’ãƒ¼ãƒ©ãƒ¼ã¯è–¬å‰¤ã§æ”»æ’ƒ
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
		// TODO: IFFã®è¨­å®šã€ã‚¯ãƒ©ã‚¹æ¯ã®åˆ¤å®šã—ã‹ã§ããªã„ã®ã§ä½¿ã‚ãªã„ã€‚
		return true;
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java

	public boolean setMaidMode(int pindex, boolean pplaying) {
		// ƒ‚[ƒh‚É‰‚¶‚ÄAI‚ğØ‚è‘Ö‚¦‚é
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
		
		// AI‚ğª’ê‚©‚ç‘‚«Š·‚¦‚é
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

	// ãŠã‚“ã¶ãŠã°ã‘ã¯ç„¡æ•µ
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
		// ƒ‚[ƒhØ‘Ö‚É‰‚¶‚½ˆ—Œn‚ğŠm•Û
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
		// ãƒ‡ã‚¹ãƒãƒ¼ãƒ³åˆ¤å®š
		return mod_LMM_littleMaidMob.canDespawn || super.canDespawn();
	}

	public void checkClockMaid() {
		// æ™‚è¨ˆã‚’æŒã£ã¦ã„ã‚‹ã‹ï¼Ÿ
		mstatClockMaid = maidInventory.getInventorySlotContainItem(Item.pocketSundial.itemID) > -1;
	}

	public void checkHeadMount() {
		// è¿½åŠ ã®é ­éƒ¨è£…å‚™ã®åˆ¤å®š
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
		// ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã«ãƒ˜ãƒ«ãƒ ãŒã‚ã‚‹ã‹ï¼Ÿ
		for (int i = maidInventory.mainInventory.length - 1; i >= 0; i--) {
			ItemStack is = maidInventory.getStackInSlot(i);
			if (is != null && is.getItem() instanceof ItemArmor && ((ItemArmor) is.getItem()).armorType == 0) {
				// ãƒ˜ãƒ«ãƒ ã‚’æŒã£ã¦ã‚‹
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
		// ç™ºå…‰å‡¦ç†ç”¨
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
		// ãŠå­ã•ã‚“ã®è¨­å®š
		return null;
	}

	@Override
	public void damageArmor(int i) {
		maidAvatar.damageArmor(i);
	}

	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	protected String getLivingSound() {
		// •’i‚Ìº
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
	 * ŠÈˆÕ‰¹ºÄ¶A•W€‚Ì‰¹º‚Ì‚İg—p‚·‚é‚±‚ÆB
	 */
	public void playSound(String pname) {
		playSound(pname, 0.5F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
=======
	public void damageEntity(DamageSource par1DamageSource, int par2) {
		// ãƒ€ãƒ¡ãƒ¼ã‚¸ã‚½ãƒ¼ã‚¹ã«å¿œã˜ã¦éŸ³å£°å¤‰æ›´
		if (par1DamageSource == DamageSource.fall) {
			maidDamegeSound = LMM_EnumSound.hurt_fall;
		}
		if (!par1DamageSource.isUnblockable() && isBlocking()) {
			// ãƒ–ãƒ­ãƒƒã‚­ãƒ³ã‚°
			mod_LMM_littleMaidMob.Debug(String.format("Blocking success ID:%d, %d", entityId, par2));
			maidDamegeSound = LMM_EnumSound.hurt_guard;
		}

		// è¢«ãƒ€ãƒ¡
		maidAvatar.health = health;
		if (par2 > 0 && getActiveModeClass() != null
				&& !getActiveModeClass().damageEntity(maidMode, par1DamageSource, par2)) {
			maidAvatar.damageEntity(par1DamageSource, par2);

			// ãƒ€ãƒ¡ãƒ¼ã‚¸ã‚’å—ã‘ã‚‹ã¨å¾…æ©Ÿã‚’è§£é™¤
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
	 * æ‰‹æŒã¡ã‚¢ã‚¤ãƒ†ãƒ ã®ç ´å£Š
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
	 * ãƒ¡ã‚¤ãƒ‰ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã‚’é–‹ã
	 * @param pEntityPlayer
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force) {
		// ‰¹º‚ÌÄ¶
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
		// ãƒ¡ã‚¤ãƒ‰ã•ã‚“ã¯ãŠç ‚ç³–ã¨ã‚³ã‚³ã‚¢ã¨ä¸å®šå½¢ã®ä½•ã‹ã§ã§ãã¦ã‚‹ã®ï¼
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

		// ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã‚’ãƒ–ãƒãƒã‚±ãƒ­ï¼
		maidInventory.dropAllItems();
	}

	/**
	 * ãƒšãƒ­ãƒƒãƒ»ãƒ»ãƒ»ã“ã‚Œã¯ãƒ»ãƒ»ãƒ»ç ‚ç³–ãƒƒï¼ï¼
	 * motion : è…•ã‚’æŒ¯ã‚‹ã‹ï¼Ÿ
	 * recontract : å¥‘ç´„å»¶é•·åŠ¹æœã‚¢ãƒªï¼Ÿ
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
		// TODO:ƒT[ƒo[‘¤‚Å”»’è‚Å‚«‚È‚¢‚Ì‚ÅˆÓ–¡‚È‚µ?
		MMM_TextureBox lbox = MMM_TextureManager.instance.getTextureBox(textureBox[0]);
		if (worldObj == null || textureModel == null  
				|| !textureBox[0].mo.getCanSpawnHere(worldObj, lx, ly, lz, this)) {
			mod_LMM_littleMaidMob.Debug(String.format("%s is can't spawn hear.", textureName));
			return false;
		}
		*/
		if (mod_LMM_littleMaidMob.Dominant) {
			// ƒhƒ~ƒiƒ“ƒg
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
			// å¥‘ç´„æœŸé–“ã®å»¶é•·
			maidContractLimit += 24000;
			if (maidContractLimit > 168000) {
				maidContractLimit = 168000; // 24000 * 7
			}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

		// æš«å®šå‡¦ç†
		if (maidAvatar != null) {
			maidAvatar.foodStats.addStats(20, 20F);
		}
	}

	@Override
	public void entityInit() {
		super.entityInit();
		/*
		 * DataWatcherã¯ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆã‹ã‚‰ã‚µãƒ¼ãƒãƒ¼ã¸ã¯å€¤ã‚’æ¸¡ã•ãªã„
		 */

		// 0, 1, 2, 3, 4, 5, 6, 8, 12,
		// 16: Tame(4), Sit(1)
		// 17:ownerName

		// ç‹¬è‡ªåˆ†
		// 18:HP
		dataWatcher.addObject(dataWatch_Health, new Integer(getMaxHealth()));
		// 19:maidMode(16Bit:LSB)ã€maidColor(8Bit:<<16)ã€maidDominantArm(8Bit:<<24);
		dataWatcher.addObject(dataWatch_ColorMode, new Integer((maidMode & 0xffff) | ((maidColor & 0xff) << 16)
				| ((maidDominantArm & 0xff) << 24)));
		// 20:é¸æŠãƒ†ã‚¯ã‚¹ãƒãƒ£ã‚¤ãƒ³ãƒ‡ãƒƒã‚¯ã‚¹
		dataWatcher.addObject(dataWatch_Texture, Integer.valueOf(0));
		// 21:ã‚¢ãƒ¼ãƒãƒ¼ãƒ†ã‚¯ã‚¹ãƒãƒ£ã‚¤ãƒ³ãƒ‡ãƒƒã‚¯ã‚¹
		//        dataWatcher.addObject(dataWatch_TexArmar, Integer.valueOf(0));
		// 22:çŠ¶æ…‹é·ç§»ãƒ•ãƒ©ã‚°ç¾¤(32Bit)
		// isLookSuger, looksWithInterest, isContract, isBloodsuck, isWorking, isWait
		dataWatcher.addObject(dataWatch_Flags, new Integer(0));
		// 23:GotchaID
		dataWatcher.addObject(dataWatch_Gotcha, new Integer(0));

		// TODO:test:30:
		dataWatcher.addObject(30, new Integer(0));

		// 31:è‡ªç”±å¤‰æ•°ã€EntityModeç­‰ã§ä½¿ç”¨å¯èƒ½ãªå¤‰æ•°ã€‚
		dataWatcher.addObject(dataWatch_Free, new Integer(0));

	}

	/**
	 * é©ç”¨ã•ã‚Œã¦ã„ã‚‹ãƒ¢ãƒ¼ãƒ‰ã‚¯ãƒ©ã‚¹
	 */
	public LMM_EntityModeBase getActiveModeClass() {
		return maidActiveModeClass;
	}

	@Override
	public boolean getCanSpawnHere() {
		// ã‚¹ãƒãƒ¼ãƒ³å¯èƒ½ã‹ï¼Ÿ
		if (mod_LMM_littleMaidMob.spawnLimit <= getMaidCount()) {
			mod_LMM_littleMaidMob.Debug("Spawn Limit.");
			return false;
		}
		int lx = MathHelper.floor_double(posX);
		int ly = MathHelper.floor_double(boundingBox.minY);
		int lz = MathHelper.floor_double(posZ);
		/*
			// TODO:ã‚µãƒ¼ãƒãƒ¼å´ã§åˆ¤å®šã§ããªã„ã®ã§æ„å‘³ãªã—
		    	if (worldObj == null || textureModel == null
		    			|| !textureModel[0].getCanSpawnHere(worldObj, lx, ly, lz, this)) {
		    		mod_LMM_littleMaidMob.Debug(String.format("%s is can't spawn hear.", textureName));
		    		return false;
		    	}
		*/
		if (mod_LMM_littleMaidMob.Dominant) {
			// ãƒ‰ãƒŸãƒŠãƒ³ãƒˆ
			return worldObj.checkNoEntityCollision(boundingBox)
					&& worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty()
					&& !worldObj.isAnyLiquid(boundingBox)
					&& getBlockPathWeight(lx, ly, lz) >= 0.0F;
		} else {
			return super.getCanSpawnHere();
		}
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	// ƒGƒtƒFƒNƒg•\¦
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
	 * ç¾åœ¨ã®è£…å‚™å“
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

	// åŠ¹æœéŸ³ã®è¨­å®š
	@Override
	public String getHurtSound() {
		playSound(maidDamegeSound, true);
		return null;
	}

	/**
	 * æ•µå‘³æ–¹è­˜åˆ¥
	 */
	public boolean getIFF(Entity pEntity) {
		// æ•µå‘³æ–¹è­˜åˆ¥(æ•µ=false)
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
				// è¡€ã«é¤“ãˆã¦ã„ã‚‹æ™‚ã¯æ•µ
				return false;
			}
			if (pEntity instanceof LMM_EntityLittleMaid) {
				// ãŠéŠã³ãƒ¢ãƒ¼ãƒ‰ã®ãƒ¡ã‚¤ãƒ‰ã«ã¯æ•µå¯¾ã—ãªã„
				if (((LMM_EntityLittleMaid) pEntity).mstatPlayingRole > LMM_EntityMode_Playing.mpr_NULL) {
					return true;
				}
			}
			if (pEntity instanceof EntityCreature) {
				// ç›¸æ‰‹ãŒä½•ã‚’ã‚¿ãƒ¼ã‚²ãƒƒãƒˆã«ã—ã¦ã„ã‚‹ã‹ã§æ±ºã¾ã‚‹
				Entity et = ((EntityCreature) pEntity).getEntityToAttack();
				if (et != null && et == mstatMasterEntity) {
					return false;
				}
				if (et == this) {
					return false;
				}
				if (et instanceof LMM_EntityLittleMaid) {
					// åŒã˜ãƒã‚¹ã‚¿ãƒ¼ã®ãƒ¡ã‚¤ãƒ‰ã‚’æ”»æ’ƒå¯¾è±¡ã¨ã—ã¦ã„ã‚‹
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
		// IFF‚Ìİ’èAƒNƒ‰ƒX–ˆ‚Ì”»’è‚µ‚©‚Å‚«‚È‚¢‚Ì‚Åg‚í‚È‚¢B
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		
		// ³í‚Í‰ñ•œ—Dæˆ—
		if (func_110143_aJ() < 10 && !isBloodsuck() && maidInventory.hasItem(Item.sugar.itemID)) {
			return true;
		}
		
		// “Áê‚ÈUŒ‚ˆ—
		if (isActiveModeClass() && getActiveModeClass().attackEntityAsMob(maidMode, par1Entity)) {
			return true;
=======
	public float getInterestedAngle(float f) {
		return (prevRotateAngleHead + (rotateAngleHead - prevRotateAngleHead) * f)
				* ((looksWithInterestAXIS ? 0.08F : -0.08F) * (float) Math.PI);
	}

	@Override
	public Icon getItemIcon(ItemStack par1ItemStack, int par2) {
		// ã‚¢ã‚¤ãƒ†ãƒ ã®è¡¨ç¤º
		if (maidAvatar != null) {
			return maidAvatar.getItemIcon(par1ItemStack, par2);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		// ‚¨D‚İ‚Í‰½H
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
		// ƒf[ƒ^ƒZ[ƒu
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
		// ’Ç‰Á•ª
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			maidEntityModeList.get(li).writeEntityToNBT(par1nbtTagCompound);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		// ƒf[ƒ^ƒ[ƒh
		super.readEntityFromNBT(par1nbtTagCompound);
		
		if (par1nbtTagCompound.hasKey("ModeColor")) {
			// ‹Œ”Å‚©‚ç‚ÌŒp³
	        String s = par1nbtTagCompound.getString("Master");
	        if(s.length() > 0) {
	        	setOwner(s);
	            setContract(true);
	        }
	        NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory");
	        maidInventory.readFromNBT(nbttaglist);
	        // ƒA[ƒ}[ƒXƒƒbƒg•ÏX‚É‘Î‰‚·‚é‚½‚ß‚ÌƒR[ƒh
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
				// ƒ_ƒ~[‚Ì”’l‚ğ“ü‚ê‚é
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}
			
		} else {
			// VŒ^
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
		// æ™®æ®µã®å£°
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
				// ’l‚ª‚¨‚©‚µ‚¢‚Í‚P“ú•ª
//	        	maidContractLimit = worldObj.getWorldTime() + 24000L;
				maidContractLimit = 24000;
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isContract()) {
				// ƒ_ƒ~[‚Ì”’l‚ğ“ü‚ê‚é
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
			
			// ƒeƒXƒg—p
			if (worldObj.isRemote) {
//	        	setOwner(ModLoader.getMinecraftInstance().thePlayer.username);
			}
			
			// ’Ç‰Á•ª
			for (int li = 0; li < maidEntityModeList.size(); li++) {
				maidEntityModeList.get(li).readEntityFromNBT(par1nbtTagCompound);
			}
		}
		onInventoryChanged();
		
		// ƒhƒbƒyƒ‹‘Îô
		if (mod_LMM_littleMaidMob.antiDoppelganger && maidAnniversary > 0L) {
			for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
				Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
				if (!entity1.isDead && entity1 instanceof LMM_EntityLittleMaid) {
					LMM_EntityLittleMaid elm = (LMM_EntityLittleMaid)entity1;
					if (elm != this && elm.isContract() && elm.maidAnniversary == maidAnniversary
							&& elm.getMaidMaster().equalsIgnoreCase(getMaidMaster())) {
						// V‚µ‚¢•û‚ğc‚·
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
	 * èª­ã¿è¾¼ã¿é ˜åŸŸå†…ã®ãƒ¡ã‚¤ãƒ‰ã•ã‚“ã®æ•°
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
	 * æŒ‡å®šã•ã‚ŒãŸãƒ•ãƒ©ã‚°ã‚’ç²å¾—
	 */
	public boolean getMaidFlags(int pFlagvalue) {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & pFlagvalue) > 0;
	}

	public String getMaidMaster() {
		return getOwnerName();
	}

	public EntityPlayer getMaidMasterEntity() {
		// ä¸»ã‚’ç²å¾—
		if (isMaidContract()) {
			EntityPlayer entityplayer = mstatMasterEntity;
			if (mstatMasterEntity == null || mstatMasterEntity.isDead) {
				String lname;
				// ã‚µãƒ¼ãƒãƒ¼å´ãªã‚‰ã¡ã‚ƒã‚“ã¨ã‚ªãƒ¼ãƒŠåˆ¤å®šã™ã‚‹
				if (!MMM_Helper.isClient
						|| mod_LMM_littleMaidMob.checkOwnerName
						|| MMM_Helper.mc.thePlayer == null) {
					lname = getMaidMaster();
				} else {
					lname = MMM_Helper.mc.thePlayer.username;
				}
				entityplayer = worldObj.getPlayerEntityByName(lname);
				// ã¨ã‚Šã‚ãˆãšä¸»ã®åå‰ã‚’å…¥ã‚Œã¦ã¿ã‚‹
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
		// ãƒ¢ãƒ¼ãƒ‰åç§°ã®ç²å¾—
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
		// æœ€å¤§HP
		return 20;
	}

	/**
	 * ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã«ã‚ã‚‹æ¬¡ã®è£…å‚™å“ã‚’é¸æŠ
	 */
	public boolean getNextEquipItem() {
		if (worldObj.isRemote) {
			// ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆå´ã¯å‡¦ç†ã—ãªã„
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
		// TODO:‚±‚±‚Í—v’²®
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
			// p¨§Œä
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
	 * ãƒãƒ¼ã‚·ãƒ§ãƒ³ç­‰ã«ã‚ˆã‚‹è…•æŒ¯ã‚Šãƒ¢ãƒ¼ã‚·ãƒ§ãƒ³ã®é€Ÿåº¦è£œæ­£
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

	// ƒ_ƒ[ƒWƒRƒ“ƒgƒ[ƒ‹
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
	 * åˆ©ãè…•ã®ãƒªãƒ­ãƒ¼ãƒ‰ã‚¿ã‚¤ãƒ 
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
		// ƒ_ƒ[ƒWƒ\[ƒX‚É‰‚¶‚Ä‰¹º•ÏX
		if (par1DamageSource == DamageSource.fall) {
			maidDamegeSound = LMM_EnumSound.hurt_fall;
		}
		if(!par1DamageSource.isUnblockable() && isBlocking()) {
			// ƒuƒƒbƒLƒ“ƒO
			mod_LMM_littleMaidMob.Debug(String.format("Blocking success ID:%d, %d" , this.entityId, par2));
			maidDamegeSound = LMM_EnumSound.hurt_guard;
		}
		
		// ”íƒ_ƒ
		float llasthealth = func_110143_aJ();
		if (par2 > 0 && getActiveModeClass() != null && !getActiveModeClass().damageEntity(maidMode, par1DamageSource, par2)) {
			maidAvatar.damageEntity(par1DamageSource, par2);
			
			// ƒ_ƒ[ƒW‚ğó‚¯‚é‚Æ‘Ò‹@‚ğ‰ğœ
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
		
		// ƒ_ƒ[ƒWƒ\[ƒX‚ğ“Á’è‚µ‚Ä‰¹º‚Ìİ’è
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
			// —V‚Ñ‚ÍI‚í‚è‚¾I
			setPlayingRole(0);
			getNextEquipItem();
		}
		// ƒQ[ƒ€“ïˆÕ“x‚É‚æ‚éƒ_ƒ[ƒW‚Ì•â³
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
			// ƒm[ƒ_ƒ[ƒW
			if (maidDamegeSound == LMM_EnumSound.hurt) {
				maidDamegeSound = LMM_EnumSound.hurt_nodamege;
			}
			playSound(maidDamegeSound, true);
			return false;
		}
		
		if(super.attackEntityFrom(par1DamageSource, par2)) {
			//Œ_–ñÒ‚Ì–¼‘Oƒ`ƒFƒbƒN‚Íƒ}ƒ‹ƒ`—p
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
    			// ƒq[ƒ‰[‚Í–òÜ‚ÅUŒ‚
    			maidInventory.currentItem = maidInventory.getInventorySlotContainItemPotion(true, 0, ((EntityLiving)entity).isEntityUndead() & isMaskedMaid);
    		}
    		*/
			return true; 
=======
	/**
	 * å¯¾å¿œå‹å°„æ’ƒæ­¦å™¨ã®ãƒªãƒ­ãƒ¼ãƒ‰åˆ¤å®š
	 */
	public void getWeaponStatus() {
		// é£›ã³é“å…·ç”¨ã®ç‰¹æ®Šå‡¦ç†
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
			// å§¿å‹¢åˆ¶å¾¡
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
		// worldObj.setEntityState(this, (byte))ã§æŒ‡å®šã•ã‚ŒãŸã‚¢ã‚¯ã‚·ãƒ§ãƒ³ã‚’å®Ÿè¡Œ
		switch (par1) {
		case 10:
			// ä¸æ©Ÿå«Œ
			showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
			break;
		case 11:
			// ã‚´ã‚­ã‚²ãƒ³
			double a = getContractLimitDays() / 7D;
			double d6 = a * 0.3D;
			double d7 = a;
			double d8 = a * 0.3D;
			worldObj.spawnParticle("note", posX, posY + height + 0.1D, posZ, d6, d7, d8);
			break;
		case 12:
			// è‡ªç”±è¡Œå‹•
			showParticleFX("reddust", 0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
			break;
		case 13:
			// ä¸è‡ªç”±è¡Œå‹•
			showParticleFX("smoke", 0.02D, 0.02D, 0.02D);
			break;
		case 14:
			// ãƒˆãƒ¬ãƒ¼ã‚µãƒ¼
			showParticleFX("explode", 0.3D, 0.3D, 0.3D, 0.0D, 0.0D, 0.0D);
			break;

		default:
			super.handleHealthUpdate(par1);
		}
	}

	@Override
	public void initCreature() {
		super.initCreature();
		// ãƒ†ã‚¯ã‚¹ãƒãƒ£ãƒ¼ã‚’ãƒ©ãƒ³ãƒ€ãƒ ã§é¸æŠ
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
	 * ‘ÎÛ‚Éƒ|[ƒVƒ‡ƒ“‚ğg‚¤B
	 */
	public void usePotionTotarget(EntityLivingBase entityliving) {
		ItemStack itemstack = maidInventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
			// ƒ|[ƒVƒ‡ƒ“Œø‰Ê‚Ì”­“®
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
			// ã“ã“ã¯å¿…è¦ãªããªã£ãŸï¼Ÿ
			textureName = textureArmorName = "default";
			textureIndex = textureArmorIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
		// é‡ç”Ÿã®ãƒ¡ã‚¤ãƒ‰è‰²ã‚’ãƒ©ãƒ³ãƒ€ãƒ ã§æŒ‡å®š
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

		// TODO:ã“ã‚Œã„ã‚‰ãªãã­ï¼Ÿ
		aiProfiler = worldObj != null && worldObj.theProfiler != null ? worldObj.theProfiler : null;

		// å‹•ä½œãƒ¢ãƒ¼ãƒ‰ç”¨ã®TasksListã‚’åˆæœŸåŒ–
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
		// ç§»å‹•ç”¨AI
		ltasks[0].addTask(15, aiTracer);
		ltasks[0].addTask(16, aiFollow);
		ltasks[0].addTask(17, aiWander);
		ltasks[0].addTask(18, new EntityAILeapAtTarget(this, 0.3F));
		// Mutexã®å½±éŸ¿ã—ãªã„ç‰¹æ®Šè¡Œå‹•
		ltasks[0].addTask(20, aiCloseDoor);
		ltasks[0].addTask(21, aiOpenDoor);
		ltasks[0].addTask(22, aiRestrictRain);
		// é¦–ã®å‹•ãå˜ç‹¬
		ltasks[0].addTask(31, new EntityAIWatchClosest(this, EntityLiving.class, 10F));
		ltasks[0].addTask(32, new EntityAILookIdle(this));

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	protected void updateAITick() {
//		// AI‘Î‰Œ^‚Í‚±‚Á‚¿‚ªŒÄ‚Î‚ê‚é
//		dataWatcher.updateObject(dataWatch_Health, Integer.valueOf(getHealth()));
		
		// ’Ç‰Á•ª
=======
		//		ltasks[1].addTask(2, new EntityAIHurtByTarget(this, false));

		//		addMaidMode(ltasks, "Escorter", 0x0001);

		// è¿½åŠ åˆ†
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		for (LMM_EntityModeBase ieml : maidEntityModeList) {
			ieml.addEntityMode(ltasks[0], ltasks[1]);
		}

	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		// ãƒŠãƒ‡ãƒªåˆ¤å®š
		if (health > 0 && par1EntityPlayer.riddenByEntity != null
				&& !(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)) {
			// è¼‰ã›æ›¿ãˆ
			par1EntityPlayer.riddenByEntity.mountEntity(this);
			return true;
		}

		ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();

		if (mstatgotcha == null && par1EntityPlayer.fishEntity == null) {
			if (itemstack1 != null && itemstack1.itemID == Item.silk.itemID) {
				// ç´ã§ç¹‹ã
				setGotcha(par1EntityPlayer.entityId);
				mstatgotcha = par1EntityPlayer;
				MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
				playSound("random.pop");
				return true;
			}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	@Override
	public void onLivingUpdate() {
		// ‰ñ•œ”»’è
		float lhealth = func_110143_aJ();
		if (lhealth > 0) {
			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
					if (!isBloodsuck()) {
						// ’Êí‚Í‰ñ•œ—Dæ
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
		// –„‘’‘Îô
		boolean grave = true;
		grave &= pushOutOfBlocks(posX - (double)width * 0.34999999999999998D, boundingBox.minY, posZ + (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX - (double)width * 0.34999999999999998D, boundingBox.minY, posZ - (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + (double)width * 0.34999999999999998D, boundingBox.minY, posZ - (double)width * 0.34999999999999998D);
		grave &= pushOutOfBlocks(posX + (double)width * 0.34999999999999998D, boundingBox.minY, posZ + (double)width * 0.34999999999999998D);
		if (grave && onGround) {
			jump();
		}
		if(lhealth > 0) {
			// ‹ßÚŠÄ‹‚Ì’Ç‰Á‚Í‚±‚±
			// ƒAƒCƒeƒ€‚Ì‰ñû
			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Entity entity = (Entity)list.get(i);
						if (!entity.isDead) {
							entity.onCollideWithPlayer(maidAvatar);
						}
					}
					// ƒAƒCƒeƒ€‚ªˆê”t‚É‚È‚Á‚Ä‚¢‚ÄƒAƒCƒeƒ€‚Éƒ^ƒQ‚ğ‚Æ‚Á‚Ä‚¢‚éê‡‚Íƒ^ƒQ‚ğƒNƒŠƒA
					if (entityToAttack instanceof EntityItem && maidInventory.getFirstEmptyStack() == -1) {
						setTarget(null);
					}
				}
			}
			// Œv‚ğ‚Á‚Ä‚¢‚é
			// TODO:‘½•ª‚±‚Ì•Ó‚è‚Ìˆ—‚Í‚¨‚©‚µ‚¢
			if (isContractEX() && mstatClockMaid) {
				// ƒQ[ƒ€“àŠÔ‚É‡‚í‚¹‚½‰¹º‚ÌÄ¶
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
				// å¥‘ç´„çŠ¶æ…‹
				if (health > 0 && isMaidContractOwner(par1EntityPlayer)) {
					if (itemstack1 != null) {
						// è¿½åŠ åˆ†ã®å‡¦ç†
						setPathToEntity(null);
						for (int li = 0; li < maidEntityModeList.size(); li++) {
							if (maidEntityModeList.get(li).interact(par1EntityPlayer, itemstack1)) {
								return true;
							}
						}
						if (isRemainsContract()) {
							// é€šå¸¸
							if (itemstack1.itemID == Item.sugar.itemID) {
								// ãƒ¢ãƒ¼ãƒ‰åˆ‡æ›¿
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								eatSugar(false, true);
								worldObj.setEntityState(this, (byte) 11);

								mod_LMM_littleMaidMob.Debug("give suger." + worldObj.isRemote);
								if (!worldObj.isRemote) {
									setFreedom(isFreedom());
									if (isMaidWait()) {
										// å‹•ä½œãƒ¢ãƒ¼ãƒ‰ã®åˆ‡æ›¿
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
										// å¾…æ©Ÿ
										setMaidWait(true);
									}
								}
								return true;
							}
							else if (itemstack1.itemID == Item.dyePowder.itemID) {
								// ã‚«ãƒ©ãƒ¼ãƒ¡ã‚¤ãƒ‰
								if (!worldObj.isRemote) {
									setMaidColor(15 - itemstack1.getItemDamage());
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (itemstack1.itemID == Item.feather.itemID) {
								// è‡ªç”±è¡Œå‹•
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setFreedom(!isFreedom());
								worldObj.setEntityState(this, isFreedom() ? (byte) 12 : (byte) 13);
								return true;
							}
							else if (itemstack1.itemID == Item.saddle.itemID) {
								// è‚©è»Š
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
								// IFFã®ã‚ªãƒ¼ãƒ—ãƒ³
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								//	    		            	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiIFF(worldObj, this));
								if (worldObj.isRemote) {
									LMM_Client.OpenIFF(this, par1EntityPlayer);
								}
								return true;
							}
							else if ((itemstack1.itemID == Item.glassBottle.itemID) && (experienceValue >= 5)) {
								// Expãƒœãƒˆãƒ«
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
								// ãƒãƒ¼ã‚·ãƒ§ãƒ³
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
							// ã‚¹ãƒˆãƒ©ã‚¤ã‚­
							if (itemstack1.itemID == Item.sugar.itemID) {
								// å—å–æ‹’å¦
								worldObj.setEntityState(this, (byte) 10);
								return true;
							} else if (itemstack1.itemID == Item.cake.itemID) {
								// å†å¥‘ç´„
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
					// ãƒ¡ã‚¤ãƒ‰ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒª
					getNavigator().clearPathEntity();
					isJumping = false;
					displayGUIMaidInventory(par1EntityPlayer);
					//    		        	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiInventory(this, par1EntityPlayer.inventory, maidInventory));
					//    				serchedChest.clear();
					return true;
				}
			} else {
				// æœªå¥‘ç´„
				if (itemstack1 != null) {
					if (itemstack1.itemID == Item.cake.itemID) {
						// å¥‘ç´„
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
							// å¥‘ç´„è¨˜å¿µæ—¥ã¨ã€åˆæœŸå¥‘ç´„æœŸé–“
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getWorldTime();
							// ãƒ†ã‚¯ã‚¹ãƒãƒ£ã®ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆ:ã„ã‚‰ã‚“ï¼Ÿ
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
					// ‰ñ•œ
					if (func_110143_aJ() < func_110138_aP()) {
						if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							eatSugar(true, false);
						}
					}
					// ‚Â‚Ü‚İH‚¢
					if (rand.nextInt(50000) == 0 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
						eatSugar(true, false);
					}
					// Œ_–ñXV
					if (isContractEX()) {
						float f = getContractLimitDays();
						if (f <= 6 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							// Œ_–ñXV
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

	// AIé–¢é€£
	@Override
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public void onUpdate() {
		// Entity‰‰ñ¶¬‚ÌƒCƒ“ƒxƒ“ƒgƒŠXV—p
		// ƒT[ƒo[‚Ì•û‚ªæ‚É‹N“®‚·‚é‚Ì‚ÅƒNƒ‰ƒCƒAƒ“ƒg‘¤‚ªXV‚ğó‚¯æ‚ê‚È‚¢
		if (firstload > 0) {
			// ‰‰ñXV—p
			// ƒT[ƒo[‚Ì•û‚ªæ‚É‹N“®‚µ‚Ä‚¢‚é‚Ì‚Å‹­§“Ç‚İ‚İ‚Ìè‡‚ª•K—v
			if (--firstload == 0) {
				if (worldObj.isRemote) {
					LMM_Net.sendToEServer(this, new byte[] {LMM_Statics.LMN_Server_UpdateSlots, 0, 0, 0, 0});
				} else {
				}
			}
		}
		
		// ”ò‚Ñ“¹‹ï—p
		weaponFullAuto = false;
		weaponReload = false;
		
		// å‚ÌŠm”F‚È‚Ç
		mstatMasterEntity = getMaidMasterEntity();
		if (mstatMasterEntity != null) {
			mstatMasterDistanceSq = getDistanceSqToEntity(mstatMasterEntity);
		}
		// ƒ‚ƒfƒ‹ƒTƒCƒY‚ÌƒŠƒAƒ‹ƒ^ƒCƒ€•ÏX—L‚èH
		if (textureBox[0].isUpdateSize) {
			setSize(textureBox[0].getWidth(maidCaps), textureBox[0].getHeight(maidCaps));
			func_98054_a(false);
		}
		// ƒŠƒAƒ‹ƒ^ƒCƒ€•Ï“®’l‚ğƒAƒbƒvƒf[ƒg
		if (worldObj.isRemote) {
			// ƒNƒ‰ƒCƒAƒ“ƒg‘¤
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
//				// ‚È‚º‚©€–SƒAƒjƒ[ƒVƒ‡ƒ“‚ª‚¨‚©‚µ‚­‚È‚é‚Ì‚Å”»’è•t‚¯‚éB
//				setEntityHealth(dataWatcher.getWatchableObjectInt(dataWatch_Health));
//			}
			updateMaidFlagsClient();
			updateGotcha();
		} else {
			boolean lf;
			// ƒT[ƒo[‘¤
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
			// X‚Ë‚é
			if (!isContractEX() && !isFreedom()) {
				setFreedom(true);
				setMaidWait(false);
			}
		}
		// ˆÚ“®‘¬“x‚Ìİ’è
		// TODO:AIü‚è‚ÌˆÚ“®‘¬“x‚ğ‰½‚Æ‚©‚µ‚È‚¢‚ÆˆÓ–¡‚È‚¢B
//		setAIMoveSpeed((maidContract & !maidFreedom) ? moveSpeed_Max : moveSpeed_Nomal);
		
		// “Æ©ˆ——p–ˆˆ—
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
		
		// Aveter‚Ì–ˆˆ—
		if (maidAvatar != null) {
			maidAvatar.getValue();
			maidAvatar.onUpdate();
//			maidAvatar.setValue();
		}
		
		// ƒJƒEƒ“ƒ^Œn
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
		
		// ‚­‚Ñ‚©‚µ‚°	
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
		
		// ‚¿•¨‚ÌŠm”F
		if (maidInventory.inventoryChanged) {
			onInventoryChanged();
			maidInventory.inventoryChanged = false;
		}
		if (!worldObj.isRemote) {
			// ƒT[ƒo[‘¤ˆ—
			// ƒCƒ“ƒxƒ“ƒgƒŠ‚ÌXV
//			if (!mstatOpenInventory) {
				for (int li = 0 ;li < maidInventory.getSizeInventory(); li++) {
					boolean lchange = false;
					int lselect = 0xff;
					// ‘I‘ğ‘•”õ‚ª•Ï‚í‚Á‚½
					for (int lj = 0; lj < mstatSwingStatus.length; lj++) {
						lchange = mstatSwingStatus[lj].checkChanged();
						if (mstatSwingStatus[lj].index == li) {
							lselect = lj;
						}
					}
					// ƒCƒ“ƒxƒ“ƒgƒŠ‚Ì’†g‚ª•Ï‚í‚Á‚½
					if (lchange || maidInventory.isChanged(li)) {
						((WorldServer)worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, new Packet5PlayerInventory(this.entityId, (li | lselect << 8) + 5, maidInventory.getStackInSlot(li)));
						maidInventory.resetChanged(li);
						mod_LMM_littleMaidMob.Debug(String.format("ID:%d-%s - Slot(%x:%d-%d,%d) Update.", entityId, worldObj.isRemote ? "Client" : "Server", lselect, li, mstatSwingStatus[0].index, mstatSwingStatus[1].index));
					}
//				}
			}
			// ‹|\‚¦
			mstatAimeBow &= !getSwingStatusDominant().canAttack();
			// \‚¦‚ÌXV
			updateAimebow();
			
			// TODO:test
			if (dataWatcher.getWatchableObjectInt(30) != experienceValue) {
				dataWatcher.updateObject(30, experienceValue);
			}
			
			// ©•ª‚æ‚è‘å‚«‚È‚à‚Ì‚Íæ‚Á‚¯‚È‚¢iƒCƒJœ‚­j
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
		
		// •R‚Åf’v
		if(mstatgotcha != null) {
			double d = mstatgotcha.getDistanceSqToEntity(this);
			if (entityToAttack == null) {
				// ƒCƒ“ƒRƒ€‚²‚Á‚±—p
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
		// æ–°AIå¯¾å¿œ
		return true;
	}

	public boolean isAimebow() {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_Aimebow) > 0;
	}

	// ãƒ€ãƒ¡ãƒ¼ã‚¸ã‚³ãƒ³ãƒˆãƒ­ãƒ¼ãƒ«
	@Override
	public boolean isBlocking() {
		return maidAvatar.isBlocking();
	}

	/**
	 * åŸ‹è‘¬å¯¾ç­–ã‚³ãƒ”ãƒ¼
	 */
	private boolean isBlockTranslucent(int par1, int par2, int par3) {
		return worldObj.isBlockNormalCube(par1, par2, par3);
	}

	public boolean isBloodsuck() {
		return mstatBloodsuck;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		// ãŠå¥½ã¿ã¯ä½•ï¼Ÿ
		if (isMaidContractEX()) {
			return par1ItemStack.itemID == Item.sugar.itemID;
		} else {
			return par1ItemStack.itemID == Item.cake.itemID;
		}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
	}

	/**
	 * ã‚«ãƒ¢ãƒ•ãƒ©ãƒ¼ã‚¸ãƒ¥ï¼
	 */
	public boolean isCamouflage() {
		return mstatCamouflage;
	}

	/**
	 * æ™‚è¨ˆã‚’æŒã£ã¦ã„ã‚‹ã‹?
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
		// TODO:•K—v‚©‚Ç‚¤‚©‚Ìƒ`ƒFƒbƒN
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

	// ä¿æŒã‚¢ã‚¤ãƒ†ãƒ é–¢é€£

	public boolean isMaidContractOwner(EntityPlayer pentity) {
		return pentity == getMaidMasterEntity();

		//		return pentity == mstatMasterEntity;
	}

	public boolean isMaidContractOwner(String pname) {
		return pname.equalsIgnoreCase(mstatMasterEntity.username);
	}

	// ãƒ¡ã‚¤ãƒ‰ã®å¾…æ©Ÿè¨­å®š
	public boolean isMaidWait() {
		return maidWait;
	}

	public boolean isMaidWaitEx() {
		return isMaidWait() | (mstatWaitCount > 0) | isOpenInventory();
	}

	/**
	 * ãƒ¡ãƒƒãƒˆã‚’è¢«ã£ã¦ã‚‹ã‹
	 */
	public boolean isMaskedMaid() {
		return mstatMaskSelect > -1;
	}

	public boolean isOpenInventory() {
		return mstatOpenInventory;
	}

	/**
	 * é‰¢æ¤ãˆçŠ¶æ…‹
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

	// ãƒ¡ã‚¤ãƒ‰ã®å¥‘ç´„è¨­å®š
	@Override
	public boolean isTamed() {
		return isMaidContract();
	}

	/**
	 * ãƒˆãƒ¬ãƒ¼ã‚µãƒ¼ãƒ¢ãƒ¼ãƒ‰ã§ã‚ã‚‹ã‹ï¼Ÿ
	 */
	public boolean isTracer() {
		return maidTracer;
	}

	/**
	 * ä½¿ã£ã¦ã„ã‚‹Tileã‹ã©ã†ã‹åˆ¤å®šã—ã¦è¿”ã™ã€‚
	 */
	public boolean isUsingTile(TileEntity pTile) {
		if (isActiveModeClass()) {
			return getActiveModeClass().isUsingTile(pTile);
		} else {
			return false;
		}
	}

	/**
	 * ä»•äº‹ä¸­ã‹ã©ã†ã‹ã‚’è¿”ã™
	 */
	public boolean isWorking() {
		return mstatWorkingCount.isEnable();
	}

	/**
	 * ä»•äº‹ãŒçµ‚äº†ã—ã¦ã‚‚ä½™éŸ»ã‚’å«ã‚ã¦è¿”ã™
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
			// ‚¿•¨‚ÌƒAƒbƒvƒf[ƒg
			// “Æ©Šg’£:•’Ê‚ÉƒXƒƒbƒg”Ô†‚Ì’Ê‚èAãˆÊ‚Wƒrƒbƒg‚Í‘•”õƒXƒƒbƒg
			// par1‚ÍShort‚Å“n‚³‚ê‚é‚Ì‚Å‚»‚Ì‚æ‚¤‚ÉB
			int lslotindex = par1 & 0x7f;
			int lequip = (par1 >>> 8) & 0xff;
			maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
			maidInventory.resetChanged(lslotindex);	// ‚±‚ê‚ÍˆÓ–¡‚È‚¢‚¯‚Ç‚ÈB
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

		// æ­»å› ã‚’è¡¨ç¤º
		if (!worldObj.isRemote) {
			// ãƒã‚¹ã‚¿ãƒ¼åˆ¤å®šå¤±æ•—ã™ã‚‹ã‹ã‚‚ï¼Ÿ
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
		// ƒCƒ“ƒxƒ“ƒgƒŠ‚Éƒwƒ‹ƒ€‚ª‚ ‚é‚©H
		for (int i = maidInventory.mainInventory.length - 1; i >= 0; i--) {
			ItemStack is = maidInventory.getStackInSlot(i);
			if (is != null && is.getItem() instanceof ItemArmor && ((ItemArmor)is.getItem()).armorType == 0) {
				// ƒwƒ‹ƒ€‚ğ‚Á‚Ä‚é
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
	 * GUIã‚’é–‰ã‚ãŸæ™‚ã«ã‚µãƒ¼ãƒãƒ¼å´ã§å‘¼ã°ã‚Œã‚‹ã€‚
	 */
	public void onGuiClosed() {
		setOpenInventory(false);
		int li = maidMode & 0x0080;
		setMaidWaitCount((li == 0) ? 50 : 0);
	}

	/**
	 * GUIã‚’é–‹ã„ãŸæ™‚ã«ã‚µãƒ¼ãƒãƒ¼å´ã§å‘¼ã°ã‚Œã‚‹ã€‚
	 */
	public void onGuiOpened() {
		setOpenInventory(true);
	}

	/**
	 *  ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªãŒå¤‰æ›´ã•ã‚Œã¾ã—ãŸã€‚
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
		// é£›ã³é“å…·ç”¨
		weaponFullAuto = false;
		weaponReload = false;

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	/**
	 * ƒƒCƒhƒCƒ“ƒxƒ“ƒgƒŠ‚ğŠJ‚­
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
					// å›å¾©åˆ¤å®š
					if (!isBloodsuck()) {
						// é€šå¸¸æ™‚ã¯å›å¾©å„ªå…ˆ
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
		// ƒiƒfƒŠ”»’è
		if (lhealth > 0F && par1EntityPlayer.riddenByEntity != null && !(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)) {
			// Ú‚¹‘Ö‚¦
			par1EntityPlayer.riddenByEntity.mountEntity(this);
			return true;
		}
		
		ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();
		
		
		if (mstatgotcha == null && par1EntityPlayer.fishEntity == null) {
			if(itemstack1 != null && itemstack1.itemID == Item.silk.itemID) {
				// •R‚ÅŒq‚®
				setGotcha(par1EntityPlayer.entityId);
				mstatgotcha = par1EntityPlayer;
				MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
				playSound("random.pop");
				return true;
			} 
			
			if (isContract()) {
				// Œ_–ñó‘Ô
				if (lhealth > 0F && isMaidContractOwner(par1EntityPlayer)) {
					if (itemstack1 != null) {
						// ’Ç‰Á•ª‚Ìˆ—
						setPathToEntity(null);
						for (int li = 0; li < maidEntityModeList.size(); li++) {
							if (maidEntityModeList.get(li).interact(par1EntityPlayer, itemstack1)) {
								return true;
							}
						}
						if (isRemainsContract()) {
							// ’Êí
							if (itemstack1.itemID == Item.sugar.itemID) {
								// ƒ‚[ƒhØ‘Ö
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								eatSugar(false, true);
								worldObj.setEntityState(this, (byte)11);
								
								mod_LMM_littleMaidMob.Debug("give suger." + worldObj.isRemote);
								if (!worldObj.isRemote) {
									setFreedom(isFreedom());
									if (isMaidWait()) {
										// “®ìƒ‚[ƒh‚ÌØ‘Ö
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
										// ‘Ò‹@
										setMaidWait(true);
									}
								}
								return true;
							}
							else if (itemstack1.itemID == Item.dyePowder.itemID) {
								// ƒJƒ‰[ƒƒCƒh
								if (!worldObj.isRemote) {
									setColor(15 - itemstack1.getItemDamage());
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (itemstack1.itemID == Item.feather.itemID) {
								// ©—Rs“®
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setFreedom(!isFreedom());
								worldObj.setEntityState(this, isFreedom() ? (byte)12 : (byte)13);
								return true;
							}
							else if (itemstack1.itemID == Item.saddle.itemID) {
								// Œ¨Ô
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
								// IFF‚ÌƒI[ƒvƒ“
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
//	    		            	ModLoader.openGUI(par1EntityPlayer, new LMM_GuiIFF(worldObj, this));
								if (worldObj.isRemote) {
									LMM_Client.OpenIFF(this, par1EntityPlayer);
								}
								return true;
							}
							else if ((itemstack1.itemID == Item.glassBottle.itemID) && (experienceValue >= 5)) {
								// Expƒ{ƒgƒ‹
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
								// ƒ|[ƒVƒ‡ƒ“
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
		// åŸ‹è‘¬å¯¾ç­–
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
			// è¿‘æ¥ç›£è¦–ã®è¿½åŠ ã¯ã“ã“
			// ã‚¢ã‚¤ãƒ†ãƒ ã®å›å
			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Entity entity = (Entity) list.get(i);
						if (!entity.isDead) {
							entity.onCollideWithPlayer(maidAvatar);
						}
					}
					// ã‚¢ã‚¤ãƒ†ãƒ ãŒä¸€æ¯ã«ãªã£ã¦ã„ã¦ã‚¢ã‚¤ãƒ†ãƒ ã«ã‚¿ã‚²ã‚’ã¨ã£ã¦ã„ã‚‹å ´åˆã¯ã‚¿ã‚²ã‚’ã‚¯ãƒªã‚¢
					if (entityToAttack instanceof EntityItem && maidInventory.getFirstEmptyStack() == -1) {
						setTarget(null);
					}
				}
			}
			// æ™‚è¨ˆã‚’æŒã£ã¦ã„ã‚‹
			// TODO:å¤šåˆ†ã“ã®è¾ºã‚Šã®å‡¦ç†ã¯ãŠã‹ã—ã„
			if (isMaidContractEX() && mstatClockMaid) {
				// ã‚²ãƒ¼ãƒ å†…æ™‚é–“ã«åˆã‚ã›ãŸéŸ³å£°ã®å†ç”Ÿ
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
					// å›å¾©
					if (health < getMaxHealth()) {
						if (maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							eatSugar(true, false);
						}
					}
					// ã¤ã¾ã¿é£Ÿã„
					if (rand.nextInt(50000) == 0 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
						eatSugar(true, false);
					}
					// å¥‘ç´„æ›´æ–°
					if (isMaidContractEX()) {
						float f = getContractLimitDays();
						if (f <= 6 && maidInventory.consumeInventoryItem(Item.sugar.itemID)) {
							// å¥‘ç´„æ›´æ–°
							eatSugar(true, true);
						}
					}
				}
			}
		}
	}

	// ãƒãƒ¼ã‚·ãƒ§ãƒ³ã‚¨ãƒ•ã‚§ã‚¯ãƒˆ
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
		// Entityåˆå›ç”Ÿæˆæ™‚ã®ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªæ›´æ–°ç”¨
		// ã‚µãƒ¼ãƒãƒ¼ã®æ–¹ãŒå…ˆã«èµ·å‹•ã™ã‚‹ã®ã§ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆå´ãŒæ›´æ–°ã‚’å—ã‘å–ã‚Œãªã„
		if (firstload > 0) {
			// åˆå›æ›´æ–°ç”¨
			// ã‚µãƒ¼ãƒãƒ¼ã®æ–¹ãŒå…ˆã«èµ·å‹•ã—ã¦ã„ã‚‹ã®ã§å¼·åˆ¶èª­ã¿è¾¼ã¿ã®æ‰‹é †ãŒå¿…è¦
			if (--firstload == 0) {
				if (worldObj.isRemote) {
					LMM_Net.sendToEServer(this, new byte[] { LMM_Net.LMN_Server_UpdateSlots, 0, 0, 0, 0 });
				} else {
				}
			}
		}

		// ä¸»ã®ç¢ºèªãªã©
		mstatMasterEntity = getMaidMasterEntity();
		if (mstatMasterEntity != null) {
			mstatMasterDistanceSq = getDistanceSqToEntity(mstatMasterEntity);
		}
		// ãƒªã‚¢ãƒ«ã‚¿ã‚¤ãƒ å¤‰å‹•å€¤ã‚’ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆ
		if (worldObj.isRemote) {
			// ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆå´
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
				// ãªãœã‹æ­»äº¡ã‚¢ãƒ‹ãƒ¡ãƒ¼ã‚·ãƒ§ãƒ³ãŒãŠã‹ã—ããªã‚‹ã®ã§åˆ¤å®šä»˜ã‘ã‚‹ã€‚
				setEntityHealth(dataWatcher.getWatchableObjectInt(dataWatch_Health));
			}
			updateMaidFlagsClient();
			updateGotcha();
		} else {
			boolean lf;
			// ã‚µãƒ¼ãƒãƒ¼å´
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
			// æ‹—ã­ã‚‹
			if (!isMaidContractEX() && !isFreedom()) {
				setFreedom(true);
				setMaidWait(false);
			}
		}
		// ç§»å‹•é€Ÿåº¦ã®è¨­å®š
		// TODO:AIå‘¨ã‚Šã®ç§»å‹•é€Ÿåº¦ã‚’ä½•ã¨ã‹ã—ãªã„ã¨æ„å‘³ãªã„ã€‚
		moveSpeed = (maidContract & !maidFreedom) ? moveSpeed_Max : moveSpeed_Nomal;

		super.onUpdate();
		// SwingUpdate
		LMM_SwingStatus lmss1 = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss1.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss1.swingProgress;
		swingProgressInt = maidAvatar.swingProgressInt = lmss1.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss1.isSwingInProgress;

		// Aveterã®æ¯æ™‚å‡¦ç†
		if (maidAvatar != null) {
			maidAvatar.getValue();
			maidAvatar.onUpdate();
			//			maidAvatar.setValue();
		}
		// ç‹¬è‡ªå‡¦ç†
		for (LMM_EntityModeBase leb : maidEntityModeList) {
			leb.onUpdate(maidMode);
		}

		// ã‚«ã‚¦ãƒ³ã‚¿ç³»
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

		// ãã³ã‹ã—ã’
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

		// æŒã¡ç‰©ã®ç¢ºèª
		if (maidInventory.inventoryChanged) {
			onInventoryChanged();
			maidInventory.inventoryChanged = false;
		}
		if (!worldObj.isRemote) {
			// ã‚µãƒ¼ãƒãƒ¼å´å‡¦ç†
			// ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã®æ›´æ–°
			//			if (!mstatOpenInventory) {
			for (int li = 0; li < maidInventory.getSizeInventory(); li++) {
				boolean lchange = false;
				int lselect = 0xff;
				// é¸æŠè£…å‚™ãŒå¤‰ã‚ã£ãŸ
				for (int lj = 0; lj < mstatSwingStatus.length; lj++) {
					lchange = mstatSwingStatus[lj].checkChanged();
					if (mstatSwingStatus[lj].index == li) {
						lselect = lj;
					}
				}
				// ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã®ä¸­èº«ãŒå¤‰ã‚ã£ãŸ
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
			// å¼“æ§‹ãˆ
			mstatAimeBow &= !getSwingStatusDominant().canAttack();
			// æ§‹ãˆã®æ›´æ–°
			updateAimebow();

			// TODO:test
			if (dataWatcher.getWatchableObjectInt(30) != experienceValue) {
				dataWatcher.updateObject(30, experienceValue);
			}

			// è‡ªåˆ†ã‚ˆã‚Šå¤§ããªã‚‚ã®ã¯ä¹—ã£ã‘ãªã„ï¼ˆã‚¤ã‚«é™¤ãï¼‰
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

		// ç´ã§æ‹‰è‡´
		if (mstatgotcha != null) {
			double d = mstatgotcha.getDistanceSqToEntity(this);
			if (entityToAttack == null) {
				// ã‚¤ãƒ³ã‚³ãƒ ã”ã£ã“ç”¨
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
	 * éŸ³å£°å†ç”Ÿç”¨ã€‚
	 * é€šå¸¸ã®å†ç”Ÿã§ã¯ãƒãƒƒãƒˆãƒ¯ãƒ¼ã‚¯è¶Šã—ã«ãªã‚‹ã®ã§ãã®å¯¾ç­–ã€‚
	 */
	public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force) {
		// éŸ³å£°ã®å†ç”Ÿ
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
	 * ãƒãƒƒãƒˆãƒ¯ãƒ¼ã‚¯å¯¾å¿œéŸ³å£°å†ç”Ÿ
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
		// ç°¡æ˜“éŸ³å£°å†ç”Ÿ
		playSound(pname, 0.5F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}

	/**
	 * åŸ‹è‘¬å¯¾ç­–ã‚³ãƒ”ãƒ¼
	 */
	@Override
	public boolean pushOutOfBlocks(double par1, double par3, double par5) {
		// EntityPlayerSPã®ã‚’å¼•ã£å¼µã£ã¦ããŸ
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
		// ãƒ‡ãƒ¼ã‚¿ãƒ­ãƒ¼ãƒ‰
		super.readEntityFromNBT(par1nbtTagCompound);

		if (par1nbtTagCompound.hasKey("ModeColor")) {
			// æ—§ç‰ˆã‹ã‚‰ã®ç¶™æ‰¿
			String s = par1nbtTagCompound.getString("Master");
			if (s.length() > 0) {
				setOwner(s);
				setMaidContract(true);
			}
			NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory");
			maidInventory.readFromNBT(nbttaglist);
			// ã‚¢ãƒ¼ãƒãƒ¼ã‚¹ãƒ­ãƒƒãƒˆå¤‰æ›´ã«å¯¾å¿œã™ã‚‹ãŸã‚ã®ã‚³ãƒ¼ãƒ‰
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
				// ãƒ€ãƒŸãƒ¼ã®æ•°å€¤ã‚’å…¥ã‚Œã‚‹
				maidAnniversary = worldObj.getWorldTime() - entityId;
			}

		} else {
			// æ–°å‹
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
				// å€¤ãŒãŠã‹ã—ã„æ™‚ã¯ï¼‘æ—¥åˆ†
				//	        	maidContractLimit = worldObj.getWorldTime() + 24000L;
				maidContractLimit = 24000;
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isMaidContract()) {
				// ãƒ€ãƒŸãƒ¼ã®æ•°å€¤ã‚’å…¥ã‚Œã‚‹
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

			// ãƒ†ã‚¹ãƒˆç”¨
			if (worldObj.isRemote) {
				//	        	setOwner(ModLoader.getMinecraftInstance().thePlayer.username);
			}

			// è¿½åŠ åˆ†
			for (int li = 0; li < maidEntityModeList.size(); li++) {
				maidEntityModeList.get(li).readEntityFromNBT(par1nbtTagCompound);
			}
		}
		// TODO: ColorBitsã‚’ã©ã†ã™ã‚‹ã¹ï¼Ÿ
		//		textureIndex = MMM_TextureManager.setStringToIndex(textureName, -1);
		//		textureArmorIndex = MMM_TextureManager.setStringToIndex(textureArmorName, -1);
		textureIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureName);
		textureArmorIndex = MMM_TextureManager.getIndexTextureBoxServer(this, textureArmorName);
		setTexturePackIndex(maidColor, new int[] { textureIndex, textureArmorIndex });
		onInventoryChanged();

		// ãƒ‰ãƒƒãƒšãƒ«å¯¾ç­–
		if (mod_LMM_littleMaidMob.antiDoppelganger && maidAnniversary > 0L) {
			for (int i = 0; i < worldObj.loadedEntityList.size(); i++) {
				Entity entity1 = (Entity) worldObj.loadedEntityList.get(i);
				if (!entity1.isDead && entity1 instanceof LMM_EntityLittleMaid) {
					LMM_EntityLittleMaid elm = (LMM_EntityLittleMaid) entity1;
					if (elm != this && elm.isMaidContract() && elm.maidAnniversary == maidAnniversary
							&& elm.getMaidMaster().equalsIgnoreCase(getMaidMaster())) {
						// æ–°ã—ã„æ–¹ã‚’æ®‹ã™
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
				// –¢Œ_–ñ
				if (itemstack1 != null) {
					if (itemstack1.itemID == Item.cake.itemID) {
						// Œ_–ñ
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
							// Œ_–ñ‹L”O“ú‚ÆA‰ŠúŒ_–ñŠúŠÔ
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getWorldTime();
							// ƒeƒNƒXƒ`ƒƒ‚ÌƒAƒbƒvƒf[ƒg:‚¢‚ç‚ñH
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
	 * ã‚µãƒ¼ãƒãƒ¼ã¸ãƒ†ã‚¯ã‚¹ãƒãƒ£ãƒ‘ãƒƒã‚¯ã®ã‚¤ãƒ³ãƒ‡ãƒƒã‚¯ã‚¹ã‚’é€ã‚‹ã€‚
	 * ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆå´ã®å‡¦ç†
	 */
	public boolean sendTextureToServer() {
		// 16bitã‚ã‚Œã°ãƒ†ã‚¯ã‚¹ãƒãƒ£ãƒ‘ãƒƒã‚¯ã®æ•°ã«ãŸã‚Šã‚“ã¹
		MMM_TextureManager.postSetTexturePack(this, maidColor, new String[] { textureName, textureArmorName });
		return true;
	}

	public void setActiveModeClass(LMM_EntityModeBase pEntityMode) {
		maidActiveModeClass = pEntityMode;
	}

	// ä»Šå®µã®ãƒ¡ã‚¤ãƒ‰ã¯è¡€ã«é£¢ãˆã¦ãŠã‚‹
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
			// æŒã¡ç‰©ã®ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆ
			// ç‹¬è‡ªæ‹¡å¼µ:æ™®é€šã«ã‚¹ãƒ­ãƒƒãƒˆç•ªå·ã®é€šã‚Šã€ä¸Šä½ï¼˜ãƒ“ãƒƒãƒˆã¯è£…å‚™ã‚¹ãƒ­ãƒƒãƒˆ
			// par1ã¯Shortã§æ¸¡ã•ã‚Œã‚‹ã®ã§ãã®ã‚ˆã†ã«ã€‚
			int lslotindex = par1 & 0x7f;
			int lequip = (par1 >>> 8) & 0xff;
			maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
			maidInventory.resetChanged(lslotindex); // ã“ã‚Œã¯æ„å‘³ãªã„ã‘ã©ãªã€‚
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
			// é¦–ç´ã‚’ãƒ‰ãƒ­ãƒƒãƒ—
			EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ,
					new ItemStack(Item.silk));
			worldObj.spawnEntityInWorld(entityitem);
			mstatgotcha = null;
		}
		super.setDead();
	}

	/**
	 *  åˆ©ãè…•ã®è¨­å®š
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

	// è‡ªç”±è¡Œå‹•
	public void setFreedom(boolean pFlag) {
		// AIé–¢é€£ã®ãƒªã‚»ãƒƒãƒˆã‚‚ã“ã“ã§ã€‚
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

	// ç ‚ç³–é–¢é€£
	public void setLookSuger(boolean pFlag) {
		mstatLookSuger = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_LooksSugar);
	}

	// é¦–å‘¨ã‚Š
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
	 * ãƒ•ãƒ©ã‚°ç¾¤ã«å€¤ã‚’ã‚»ãƒƒãƒˆã€‚
	 * @param pCheckï¼š å¯¾è±¡å€¤ã€‚
	 * @param pFlagsï¼š å¯¾è±¡ãƒ•ãƒ©ã‚°ã€‚
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
		// ãƒ¢ãƒ¼ãƒ‰ã«å¿œã˜ã¦AIã‚’åˆ‡ã‚Šæ›¿ãˆã‚‹
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

		// AIã‚’æ ¹åº•ã‹ã‚‰æ›¸ãæ›ãˆã‚‹
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

		// ãƒ¢ãƒ¼ãƒ‰åˆ‡æ›¿ã«å¿œã˜ãŸå‡¦ç†ç³»ã‚’ç¢ºä¿
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
		// “¯ˆê«‚Ìƒ`ƒFƒbƒN
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
		// å‚ğŠl“¾
		if (isContract()) {
			EntityPlayer entityplayer = mstatMasterEntity;
			if (mstatMasterEntity == null || mstatMasterEntity.isDead) {
				String lname; 
				// ƒT[ƒo[‘¤‚È‚ç‚¿‚á‚ñ‚ÆƒI[ƒi”»’è‚·‚é
				if (!MMM_Helper.isClient
						|| mod_LMM_littleMaidMob.checkOwnerName 
						|| MMM_Helper.mc.thePlayer == null) {
					lname = getMaidMaster();
				} else {
					lname = MMM_Helper.mc.thePlayer.username;
				}
				entityplayer = worldObj.getPlayerEntityByName(lname);
				// ‚Æ‚è‚ ‚¦‚¸å‚Ì–¼‘O‚ğ“ü‚ê‚Ä‚İ‚é
				// TODO:Äİ’è‚Í•s‰Â‚É‚È‚Á‚½‚Ì‚ÅŒo‰ßŠÏ@
//				maidAvatar.username = lname;
				
				if (entityplayer != null && maidAvatar != null) {
					maidAvatar.capabilities.isCreativeMode = entityplayer.capabilities.isCreativeMode;
=======
	public void setMaidModeAITasks(EntityAITasks pTasksSRC, EntityAITasks pTasksDEST) {
		// æ—¢å­˜ã®AIã‚’å‰Šé™¤ã—ã¦ç½®ãæ›ãˆã‚‹ã€‚
		// å‹•ä½œã‚’ã‚¯ãƒªã‚¢
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
		// å¾…æ©Ÿå¸¸æ…‹ã®è¨­å®šã€ isMaidWaitç³»ã§trueã‚’è¿”ã™ãªã‚‰AIãŒå‹æ‰‹ã«ç§»å‹•ã‚’åœæ­¢ã•ã›ã‚‹ã€‚
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

	// ã‚¤ãƒ³ãƒ™ãƒ³ãƒˆãƒªã®è¡¨ç¤ºé–¢ä¿‚
	// ã¾ã•ãã‚Œã‚‹ã®ã¯ä¸€äººã ã‘
	public void setOpenInventory(boolean flag) {
		mstatOpenInventory = flag;
	}

	// ãŠéŠã³ãƒ¢ãƒ¼ãƒ‰
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

	// è…•æŒ¯ã‚Š
	public void setSwing(int attacktime, LMM_EnumSound enumsound) {
		setSwing(attacktime, enumsound, maidDominantArm);
	}

	public void setSwing(int pattacktime, LMM_EnumSound enumsound, int pArm) {
		mstatSwingStatus[pArm].attackTime = pattacktime;
		//		maidAttackSound = enumsound;
		//        soundInterval = 0;// ã„ã‚‹ã‹ï¼Ÿ
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
	 * —˜‚«˜r‚ÌƒŠƒ[ƒhƒ^ƒCƒ€
	 */
	public LMM_SwingStatus getSwingStatusDominant() {
		return mstatSwingStatus[maidDominantArm];
	}

	public LMM_SwingStatus getSwingStatus(int pindex) {
		return mstatSwingStatus[pindex];
	}


	// ¡ª‚ÌƒƒCƒh‚ÍŒŒ‚É‹Q‚¦‚Ä‚¨‚é
	public void setBloodsuck(boolean pFlag) {
		mstatBloodsuck = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Bloodsuck);
	}

	public boolean isBloodsuck() {
		return mstatBloodsuck;
	}


	// »“œŠÖ˜A
	public void setLookSuger(boolean pFlag) {
		mstatLookSuger = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_LooksSugar);
	}

	public boolean isLookSuger() {
		return mstatLookSuger;
	}

	/**
	 * ƒyƒƒbEEE‚±‚ê‚ÍEEE»“œƒbII
	 * motion : ˜r‚ğU‚é‚©H
	 * recontract : Œ_–ñ‰„’·Œø‰ÊƒAƒŠH
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
			// Œ_–ñŠúŠÔ‚Ì‰„’·
			maidContractLimit += 24000;
			if (maidContractLimit > 168000) {
				maidContractLimit = 168000;	// 24000 * 7
			}
		}
		
		// b’èˆ—
		if (maidAvatar != null) {
			maidAvatar.foodStats.addStats(20, 20F);
		}
	}


	// ‚¨d–ƒ`ƒ…
	/**
	 * d–’†‚©‚Ç‚¤‚©‚Ìİ’è
	 */
	public void setWorking(boolean pFlag) {
		mstatWorkingCount.setEnable(pFlag);
	}
	
	/**
	 * d–’†‚©‚Ç‚¤‚©‚ğ•Ô‚·
	 */
	public boolean isWorking() {
		return mstatWorkingCount.isEnable();
	}

	/**
	 * d–‚ªI—¹‚µ‚Ä‚à—]‰C‚ğŠÜ‚ß‚Ä•Ô‚·
	 */
	public boolean isWorkingDelay() {
		return mstatWorkingCount.isDelay();
	}

	/**
	 * ƒgƒŒ[ƒT[ƒ‚[ƒh‚Ìİ’è
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
	 * ƒgƒŒ[ƒT[ƒ‚[ƒh‚Å‚ ‚é‚©H
	 */
	public boolean isTracer() {
		return maidTracer;
	}


	// ‚¨—V‚Ñƒ‚[ƒh
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


	// ©—Rs“®
	public void setFreedom(boolean pFlag) {
		// AIŠÖ˜A‚ÌƒŠƒZƒbƒg‚à‚±‚±‚ÅB
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
		// TODO:ã“ã®ä»¥ä¸‹ã¯ãƒ›ãƒ³ãƒˆã¯ã„ã‚‰ã‚“ã‘ã©ã‚‚ä¿®æ­£ã‚ã‚“ã©ã„ã®ã§ã€‚
		textureName = MMM_TextureManager.getTextureBoxServer(textureIndex).textureName;
		textureArmorName = MMM_TextureManager.getTextureBoxServer(textureArmorIndex).textureName;
		// ã‚µã‚¤ã‚ºã®å¤‰æ›´
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
	 * ãƒˆãƒ¬ãƒ¼ã‚µãƒ¼ãƒ¢ãƒ¼ãƒ‰ã®è¨­å®š
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	protected boolean sendTextureToServer() {
		// 16bit‚ ‚ê‚ÎƒeƒNƒXƒ`ƒƒƒpƒbƒN‚Ì”‚É‚½‚è‚ñ‚×
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

	// ãŠä»•äº‹ãƒãƒ¥
	/**
	 * ä»•äº‹ä¸­ã‹ã©ã†ã‹ã®è¨­å®š
	 */
	public void setWorking(boolean pFlag) {
		mstatWorkingCount.setEnable(pFlag);
	}

<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean updateTexturePack() {
		// ƒeƒNƒXƒ`ƒƒƒpƒbƒN‚ªXV‚³‚ê‚Ä‚¢‚È‚¢‚©‚ğƒ`ƒFƒbƒN
		// ƒNƒ‰ƒCƒAƒ“ƒg‘¤‚Ì
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
		// “¯ˆê«‚Ìƒ`ƒFƒbƒN
		int lc = getColor();
		if ((maidColor & 0x00ff) != lc) {
			maidColor = lc;
			return true;
=======
	// ã‚¨ãƒ•ã‚§ã‚¯ãƒˆè¡¨ç¤º
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
	 * å¼“æ§‹ãˆã‚’æ›´æ–°
	 */
	public void updateAimebow() {
		boolean lflag = (maidAvatar != null && maidAvatar.isUsingItemLittleMaid()) || mstatAimeBow;
		setMaidFlags(lflag, dataWatch_Flags_Aimebow);
	}

	@Override
	public void updateAITick() {
		// AIå¯¾å¿œå‹ã¯ã“ã£ã¡ãŒå‘¼ã°ã‚Œã‚‹
		dataWatcher.updateObject(dataWatch_Health, Integer.valueOf(getHealth()));

		// è¿½åŠ åˆ†
		for (LMM_EntityModeBase ieml : maidEntityModeList) {
			ieml.updateAITick(getMaidModeInt());
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

	/**
	 * ç´ã®æŒã¡ä¸»
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
		// åŒä¸€æ€§ã®ãƒã‚§ãƒƒã‚¯
		int lc = getMaidColor();
		if ((maidColor & 0x00ff) != lc) {
			maidColor = lc;
			return true;
		}
		return false;
	}

	public boolean updateMaidContract() {
		// åŒä¸€æ€§ã®ãƒã‚§ãƒƒã‚¯
		boolean lf = isMaidContract();
		if (maidContract != lf) {
			maidContract = lf;
			return true;
		}
		return false;
	}

	/**
	 * å„ç¨®ãƒ•ãƒ©ã‚°ã®ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆ
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
	 * å¥‘ç´„æœŸé–“ã®æ®‹ã‚ŠãŒã‚ã‚‹ã‹ã‚’ç¢ºèª
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
		// TODO:ã‚¢ãƒƒãƒ—ãƒ‡ãƒ¼ãƒˆæ™‚ã«ãƒã‚§ãƒƒã‚¯
		++ticksExisted;
		//

		if (ridingEntity instanceof EntityPlayer) {
			EntityPlayer lep = (EntityPlayer) ridingEntity;

			// ãƒ˜ãƒƒãƒ‰ãƒã‚¬ãƒ¼
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
		// ãƒ†ã‚¯ã‚¹ãƒãƒ£ãƒ‘ãƒƒã‚¯ãŒæ›´æ–°ã•ã‚Œã¦ã„ãªã„ã‹ã‚’ãƒã‚§ãƒƒã‚¯
		// ã‚¯ãƒ©ã‚¤ã‚¢ãƒ³ãƒˆå´ã®
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
		// ƒTƒCƒY‚Ì•ÏX
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
		// g’·•ÏX—p
		setSize(textureBox[0].getWidth(maidCaps), textureBox[0].getHeight(maidCaps));
		func_98054_a(false);
		setPosition(posX, posY, posZ);
		mod_LMM_littleMaidMob.Debug("ID:%d, TextureModel:%s", entityId, textureBox[0].textureName);
		// ƒ‚ƒfƒ‹‚Ì‰Šú‰»
		if (((MMM_TextureBox)textureBox[0]).models[0] instanceof MMM_ModelMultiMMMBase) {
			((MMM_ModelMultiMMMBase)((MMM_TextureBox)textureBox[0]).models[0]).changeModel(maidCaps);
		}
		// ƒXƒ^ƒr‚Ì•t‚¯‘Ö‚¦
//		for (Entry<String, MMM_EquippedStabilizer> le : pEntity.maidStabilizer.entrySet()) {
//			if (le.getValue() != null) {
//				le.getValue().updateEquippedPoint(pEntity.textureModel0);
//			}
//		}
		maidSoundRate = LMM_SoundManager.getSoundRate(textureBox[0].textureName, maidColor);

	}

	/**
	 * Client—p
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
				// w’èF‚ª–³‚¢ê‡‚Í•W€ƒ‚ƒfƒ‹‚É
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

	// TileŠÖŒW

	/**
	 * å¯¾è±¡ã«ãƒãƒ¼ã‚·ãƒ§ãƒ³ã‚’ä½¿ã†ã€‚
	 */
<<<<<<< HEAD:littleMaidMob/net/minecraft/src/LMM_EntityLittleMaid.java
	public boolean isUsingTile(TileEntity pTile) {
		if (isActiveModeClass()) {
			return getActiveModeClass().isUsingTile(pTile);
=======
	public void usePotionTotarget(EntityLiving entityliving) {
		ItemStack itemstack = maidInventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
			// ãƒãƒ¼ã‚·ãƒ§ãƒ³åŠ¹æœã®ç™ºå‹•
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
	 * ƒ[ƒJƒ‹•Ï”‚ÉTile‚ÌˆÊ’u‚ğ“ü‚ê‚éB
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
		// ãƒ‡ãƒ¼ã‚¿ã‚»ãƒ¼ãƒ–
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

		// è¿½åŠ åˆ†
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			maidEntityModeList.get(li).writeEntityToNBT(par1nbtTagCompound);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0:littleMaidMob/net/minecraft/entity/LMM_EntityLittleMaid.java
		}
	}

}
