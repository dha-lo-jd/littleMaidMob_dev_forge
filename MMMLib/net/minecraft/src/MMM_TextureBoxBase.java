package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MMM_TextureBoxBase {

	public String textureName;
	public int contractColor;
	public int wildColor;
	public float modelHeight;
	public float modelWidth;
	public float modelYOffset;
	public float modelMountedYOffset;
	public boolean isUpdateSize;


	public void setModelSize(float pHeight, float pWidth, float pYOffset, float pMountedYOffset) {
		modelHeight = pHeight;
		modelWidth = pWidth;
		modelYOffset = pYOffset;
		modelMountedYOffset = pMountedYOffset;
	}

	public int getRandomColor(int pColor, Random pRand) {
		List<Integer> llist = new ArrayList<Integer>();
		for (int li = 0; li < 16; li++) {
			if ((pColor & 0x01) > 0) {
				llist.add(li);
			}
			pColor = pColor >>> 1;
		}
		
		if (llist.size() > 0) {
			return llist.get(pRand.nextInt(llist.size()));
		} else {
			return -1;
		}
	}

	/**
	 * 契約色の有無をビット配列にして返す
	 */
	public int getContractColorBits() {
		return contractColor;
	}

	/**
	 * 野生色の有無をビット配列にして返す
	 */
	public int getWildColorBits() {
		return wildColor;
	}

	/**
	 * 野生のメイドの色をランダムで返す
	 */
	public int getRandomWildColor(Random pRand) {
		return getRandomColor(getWildColorBits(), pRand);
	}

	/**
	 * 契約のメイドの色をランダムで返す
	 */
	public int getRandomContractColor(Random pRand) {
		return getRandomColor(getContractColorBits(), pRand);
	}

	public float getHeight(MMM_IModelCaps pEntityCaps) {
		return modelHeight;
	}
	public float getHeight() {
		return getHeight(null);
	}

	public float getWidth(MMM_IModelCaps pEntityCaps) {
		return modelWidth;
	}
	public float getWidth() {
		return getWidth(null);
	}

	public float getYOffset(MMM_IModelCaps pEntityCaps) {
		return modelYOffset;
	}
	public float getYOffset() {
		return getYOffset(null);
	}

	public float getMountedYOffset(MMM_IModelCaps pEntityCaps) {
		return modelMountedYOffset;
	}
	public float getMountedYOffset() {
		return getMountedYOffset(null);
	}

}
