package nova.worldgen.ore;

import nova.core.block.BlockFactory;
import nova.core.util.Identifiable;
import nova.worldgen.util.EnumSelector;

/**
 * This class describes resource that will be placed by world generator
 * in world structure as ore.
 */
public final class Ore implements Identifiable {
	public final String oreGenName;
	public final BlockFactory block;
	public final double rarity;
	public final double clusterSize;
	public final EnumSelector<OreHeight> oreLayers;

	/**
	 * @param oreGenName Ore name in world generator, used to identify the ore
	 * @param block Block factory of the ore
	 * @param rarity How rare is the ore, 1 should be considered base rarity, lower value means less common ore
	 * @param clusterSize Ore cluster size multiplier, base value is 1, bigger value means bigger ore cluster
	 * @param oreLayers World layers at which the ore can be found
	 */
	public Ore(String oreGenName, BlockFactory block, double rarity, double clusterSize, EnumSelector<OreHeight> oreLayers) {
		this.oreGenName = oreGenName;
		this.block = block;
		this.rarity = rarity;
		this.clusterSize = clusterSize;
		this.oreLayers = oreLayers;
	}

	@Override
	public String getID() {
		return oreGenName;
	}
}