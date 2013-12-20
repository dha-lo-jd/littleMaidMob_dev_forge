package net.minecraft.src;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;

<<<<<<< HEAD
=======
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.LMM_EntityLittleMaid;
import net.minecraft.entity.LMM_EntityModeManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.biome.BiomeGenBase;

>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
public class mod_LMM_littleMaidMob extends BaseMod {

	public static Achievement ac_Contract;
	@MLProp(info = "used Achievement index.(0 = Disable)")
	public static int AchievementID = 222000;
	@MLProp(info = "true: Will be hostile, false: Is a pacifist")
	public static boolean Aggressive = true;
	@MLProp(info = "true: AlphaBlend(request power), false: AlphaTest(more fast)")
	public static boolean AlphaBlend = true;
	@MLProp(info = "Not to survive the doppelganger. ")
	public static boolean antiDoppelganger = true;
	@MLProp(info = "It will despawn, if it lets things go. ")
	public static boolean canDespawn = false;
	@MLProp(info = "At local, make sure the name of the owner. ")
	public static boolean checkOwnerName = false;
	public static int containerID;
	@MLProp(info = "Print Death Massages.")
	public static boolean DeathMessage = true;

	@MLProp(info = "Print Debug Massages.")
	public static boolean DebugMessage = true;

	@MLProp(info = "Default selected Texture Packege. Null is Random")
	public static String defaultTexture = "";
	@MLProp(info = "Spawn Anywhere.")
	public static boolean Dominant = false;
<<<<<<< HEAD
//	@MLProp(info="true: AlphaBlend(request power), false: AlphaTest(more fast)")
//	public static boolean AlphaBlend = true;
	@MLProp(info="true: Will be hostile, false: Is a pacifist")
	public static boolean Aggressive = true;
=======
	@MLProp(info = "Enable LMM SpawnEgg Recipe. ")
	public static boolean enableSpawnEgg = false;
	@MLProp(info = "Maximum spawn group count.")
	public static int maxGroupSize = 3;
	@MLProp(info = "Minimum spawn group count.")
	public static int minGroupSize = 1;
	@MLProp(info = "Maximum spawn count in the World.")
	public static int spawnLimit = 20;
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0

	@MLProp(info = "Relative spawn weight. The lower the less common. 10=pigs. 0=off")
	public static int spawnWeight = 5;

	//    @MLProp(info="trampleCrops")
	public static boolean trampleCrops = false;
	@MLProp(info = "UniqueEntityId(0 is AutoAssigned.)", max = 255)
	public static int UniqueEntityId = 30;

	@MLProp(info = "FilePath mode.")
	public static boolean useMinecraftPath = false;
	//    @MLProp(info="Living Voice Rate. 1.0=100%, 0.5=50%, 0.0=0%", max=1.0F, min=0.0F)
	//    public static float LivingVoiceRate = 1.0F;
	@MLProp(info = "LittleMaid Voice distortion.")
	public static boolean VoiceDistortion = true;

	public static void Debug(String pText, Object... pVals) {
		// デバッグメッセージ
		if (DebugMessage) {
			System.out.println(String.format("littleMaidMob-" + pText, pVals));
		}
	}

	@Override
	public void addRenderer(Map map) {
		LMM_Client.addRenderer(map);
	}

	@Override
	public void clientCustomPayload(NetClientHandler var1, Packet250CustomPayload var2) {
		// クライアント側の特殊パケット受信動作
		LMM_Client.clientCustomPayload(var1, var2);
	}

	@Override
	public GuiContainer getContainerGUI(EntityClientPlayerMP var1, int var2,
			int var3, int var4, int var5) {
		return LMM_Client.getContainerGUI(var1, var2, var3, var4, var5);
	}

	@Override
	public String getName() {
		return "littleMaidMob";
	}

	@Override
	public String getPriorities() {
		// MMMLibを要求
		return "required-after:mod_MMM_MMMLib";
	}

	@Override
	public String getVersion() {
		return "1.6.2-1";
	}

	@Override
	public void load() {
<<<<<<< HEAD
		// MMMLib��Revision�`�F�b�N
		MMM_Helper.checkRevision("1");
		
//		UniqueEntityId = UniqueEntityId == 0 ? MMM_Helper.getNextEntityID(true) : UniqueEntityId;
		defaultTexture = defaultTexture.trim();
		containerID = 222;
		ModLoader.registerContainerID(this, containerID);
		MMM_Helper.registerEntity(LMM_EntityLittleMaid.class, "LittleMaid", UniqueEntityId, this, 80, 3, true, 0xefffef, 0x9f5f5f);
//		ModLoader.registerEntityID(LMM_EntityLittleMaid.class, "LittleMaid", UniqueEntityId, 0xefffef, 0x9f5f5f);
//        ModLoader.addEntityTracker(this, LMM_EntityLittleMaid.class, var2, var3, var4, var5);
=======
		// MMMLibのRevisionチェック
		MMM_Helper.checkRevision("2");

		UniqueEntityId = UniqueEntityId == 0 ? MMM_Helper.getNextEntityID(true) : UniqueEntityId;
		defaultTexture = defaultTexture.trim();
		containerID = 222;
		ModLoader.registerContainerID(this, containerID);
		ModLoader.registerEntityID(LMM_EntityLittleMaid.class, "LittleMaid", UniqueEntityId, 0xefffef, 0x9f5f5f);
		//        ModLoader.addEntityTracker(this, LMM_EntityLittleMaid.class, var2, var3, var4, var5);
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		ModLoader.addLocalization("entity.LittleMaid.name", "LittleMaid");
		ModLoader.addLocalization("entity.LittleMaid.name", "ja_JP", "リトルメイド");
		if (enableSpawnEgg) {
			// 招喚用レシピを追加
			ModLoader.addRecipe(new ItemStack(Item.monsterPlacer, 1, UniqueEntityId), new Object[] {
					"scs",
					"sbs",
					" e ",
					Character.valueOf('s'), Item.sugar,
					Character.valueOf('c'), new ItemStack(Item.dyePowder, 1, 3),
					Character.valueOf('b'), Item.slimeBall,
					Character.valueOf('e'), Item.egg,
			});
		}

		if (MMM_Helper.isClient) {
			// アチ実験用
			if (AchievementID != 0) {
<<<<<<< HEAD
				while (true) {
					// �A�`�[�u���l��������ԂŖ��o�^���ƁAUNKNOWN�̃A�`�[�u���o�^����Ă���̂ō폜����B
					int laid = 5242880 + AchievementID;
					StatBase lsb = StatList.getOneShotStat(laid);
					boolean lflag = false;
					if (lsb != null) {
						if (lsb instanceof StatPlaceholder) {
							StatList.oneShotStats.remove(Integer.valueOf(laid));
							Debug("Replace Achievement: %d(%d)", AchievementID, laid);
							lflag = true;
						} else {
							Debug("Already Achievement: %d(%d) - %s(%s)", AchievementID, laid, lsb.statGuid, lsb.getClass().getSimpleName());
							break;
						}
					}
					ac_Contract = new Achievement(AchievementID, "littleMaid", 1, -4, Item.cake, AchievementList.bakeCake).registerAchievement();
//	                ModLoader.AddAchievementDesc(ac_Contract, "(21)", "Capture the LittleMaid!");
					ModLoader.addAchievementDesc(ac_Contract, "Enlightenment!", "Capture the LittleMaid!");
					ModLoader.addLocalization("achievement.littleMaid", "ja_JP", "���B");
					ModLoader.addLocalization("achievement.littleMaid.desc", "ja_JP", "���C�h�������肵�܂����B");
					if (lflag) {
						LMM_Client.setAchievement();
					}
					break;
				}
=======
				ac_Contract = new Achievement(AchievementID, "littleMaid", 1, -4, Item.cake, AchievementList.bakeCake)
						.registerAchievement();
				//                ModLoader.AddAchievementDesc(ac_Contract, "(21)", "Capture the LittleMaid!");
				ModLoader.addAchievementDesc(ac_Contract, "Enlightenment!", "Capture the LittleMaid!");
				ModLoader.addLocalization("achievement.littleMaid", "ja_JP", "悟り。");
				ModLoader.addLocalization("achievement.littleMaid.desc", "ja_JP", "メイドさんを入手しました。");
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			}

			// 名称変換テーブル
			ModLoader.addLocalization("littleMaidMob.text.Health", "Health");
			ModLoader.addLocalization("littleMaidMob.text.Health", "ja_JP", "メイド強度");
			ModLoader.addLocalization("littleMaidMob.text.AP", "AP");
			ModLoader.addLocalization("littleMaidMob.text.AP", "ja_JP", "メイド装甲");

			// デフォルトモデルの設定
			LMM_Client.init();
		}

		// AIリストの追加
		LMM_EntityModeManager.init();

		// アイテムスロット更新用のパケット
		ModLoader.registerPacketChannel(this, "LMM|Upd");

	}

	@Override
	public void modsLoaded() {
<<<<<<< HEAD
		// �f�t�H���g���f���̐ݒ�
		MMM_TextureManager.instance.setDefaultTexture(LMM_EntityLittleMaid.class, MMM_TextureManager.instance.getTextureBox("default_Orign"));
		
		if (UniqueEntityId == -1) return;
=======
		if (UniqueEntityId == -1) {
			return;
		}
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
		// Dominant
		if (spawnWeight > 0) {
			if (Dominant) {
				// あらゆる場所にスポーンする
				try {
					Field afield[] = (BiomeGenBase.class).getDeclaredFields();
					LinkedList<BiomeGenBase> linkedlist = new LinkedList<BiomeGenBase>();
					for (Field element : afield) {
						Class class1 = element.getType();
						if ((element.getModifiers() & 8) != 0 && class1.isAssignableFrom(BiomeGenBase.class)) {
							BiomeGenBase biomegenbase = (BiomeGenBase) element.get(null);
							linkedlist.add(biomegenbase);
						}
					}
					BiomeGenBase[] dominateBiomes = linkedlist.toArray(new BiomeGenBase[0]);

					ModLoader.addSpawn(LMM_EntityLittleMaid.class, spawnWeight, minGroupSize, maxGroupSize,
							EnumCreatureType.creature, dominateBiomes);
				} catch (Exception exception) {
					Debug("Dominate Exception.");
				}
			} else {
				// 通常スポーン設定
				ModLoader.addSpawn(LMM_EntityLittleMaid.class, spawnWeight, minGroupSize, maxGroupSize,
						EnumCreatureType.creature);
			}
		}

		// モードリストを構築
		LMM_EntityModeManager.loadEntityMode();

		if (MMM_Helper.isClient) {
<<<<<<< HEAD
			// �����̉��
			LMM_SoundManager.init();
			// �T�E���h�p�b�N
=======
			// 音声の解析
			if (useMinecraftPath) {
				LMM_SoundManager.sounddir = new File(Minecraft.getMinecraftDir(), "/resources/mod/sound/littleMaidMob");
			} else {
				LMM_SoundManager.sounddir = Minecraft.getAppDir("minecraft/resources/mod/sound/littleMaidMob");
			}
			Debug("SoundDir:".concat(LMM_SoundManager.sounddir.toString()));
			// サウンドパック
>>>>>>> 3c9267ee863704790532f2c9b8ddc171642033f0
			LMM_SoundManager.loadDefaultSoundPack();
			LMM_SoundManager.loadSoundPack();
		}

		// IFFのロード
		LMM_IFF.loadIFFs();

	}

	@Override
	public void serverCustomPayload(NetServerHandler var1, Packet250CustomPayload var2) {
		// サーバ側の動作
		LMM_Net.serverCustomPayload(var1, var2);
	}

}
