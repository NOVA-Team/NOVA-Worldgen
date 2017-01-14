/*
 * Copyright (c) 2015 NOVA, All rights reserved.
 * This library is free software, licensed under GNU Lesser General Public License version 3
 *
 * This file is part of NOVA.
 *
 * NOVA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NOVA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NOVA.  If not, see <http://www.gnu.org/licenses/>.
 */

package nova.worldgen.ore;

import nova.core.block.BlockFactory;
import nova.core.util.EnumSelector;
import nova.worldgen.Generable;

/**
 * This class describes a resource that will be placed in the world
 * as an ore by the world generator.
 */
public final class Ore extends Generable {
	public final BlockFactory block;
	public final double rarity;
	public final double clusterSize;
	public final EnumSelector<OreHeight> oreLayers;

	/**
	 * @param id Ore name in world generator, used to identify the ore
	 * @param block Block factory of the ore
	 * @param rarity How rare is the ore, 1 should be considered base rarity, lower value means less common ore
	 * @param clusterSize Ore cluster size multiplier, base value is 1, bigger value means bigger ore cluster
	 * @param oreLayers World layers at which the ore can be found
	 */
	public Ore(String id, BlockFactory block, double rarity, double clusterSize, EnumSelector<OreHeight> oreLayers) {
		super(id);
		this.block = block;
		this.rarity = rarity;
		this.clusterSize = clusterSize;
		this.oreLayers = oreLayers;
	}
}
