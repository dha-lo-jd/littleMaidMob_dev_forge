package net.minecraft.src;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * アイテム用の特殊レンダーに継承させるインターフェース。
 * 現状、継承させていなくてもメソッドがItemに記述されていれば動作する。
 */
public interface MMM_IItemRenderManager {

	public boolean renderItem(EntityLivingBase pEntityLiving, ItemStack pItemStack, int pIndex);
	public boolean renderItemInFirstPerson(float pDeltaTimepRenderPhatialTick, MMM_ItemRenderer pItemRenderer);
	public ResourceLocation getRenderTexture();
	public boolean isRenderItemWorld();

}
