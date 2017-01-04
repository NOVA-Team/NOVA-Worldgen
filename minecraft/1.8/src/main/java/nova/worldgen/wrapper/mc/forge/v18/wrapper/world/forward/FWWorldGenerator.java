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

package nova.worldgen.wrapper.mc.forge.v18.wrapper.world.forward;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import nova.internal.core.Game;
import nova.worldgen.WorldgenManager;
import nova.worldgen.event.WorldgenEvent;
import nova.worldgen.ore.Ore;
import nova.worldgen.ore.OreHeight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author ExE Boss
 */
public class FWWorldGenerator implements IWorldGenerator {

	private final Map<Ore, WorldGenMinable> oreGen;
	private final WorldgenManager worldgenManager;

	public FWWorldGenerator(WorldgenManager worldgenManager) {
		this.worldgenManager = worldgenManager;
		this.oreGen = new HashMap<>();
		Game.events().on(WorldgenEvent.RegisterOre.class).bind(evt -> {
			this.oreGen.put(evt.ore, new WorldGenMinable(((Block)Game.natives().toNative(evt.ore.block)).getDefaultState(),
					(int) Math.round(evt.ore.clusterSize * 5)));
		});
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		double worldScale = world.provider.getAverageGroundLevel();
		this.worldgenManager.registry.stream().forEachOrdered(ore -> {
			double baseCount = 12 * ore.rarity * worldScale / 64;
			int count = (int) Math.round(random.nextGaussian() * Math.sqrt(baseCount) + baseCount);
			for(int i = 0; i < count; i++) {
				// OreHeight Values: (when worldScale == 64)
				//  SURFACE = 60 (55-65)
				//  UNDERSURFACE = 50 (45-55)
				//  DEEP = 40 (35-45)
				//  DEEPER = 30 (25-35)
				//  DEEPERER = 20 (15-25)
				//  REALLYDEEP = 10 (5-15)

				List<OreHeight> oreHeightList = Arrays.stream(OreHeight.values()).filter(ore.oreLayers::allows).collect(Collectors.toList());
				if (oreHeightList.isEmpty()) return;

				OreHeight height = oreHeightList.get(random.nextInt(oreHeightList.size()));
				double yAdd = (OreHeight.values().length - height.ordinal() - 1) * (10 * worldScale / 64) + (5 * worldScale / 64);

				int x = chunkX + random.nextInt(16);
				int y = (int) Math.round(yAdd + (random.nextDouble() * 10 * worldScale / 64));
				int z = chunkZ + random.nextInt(16);

				oreGen.get(ore).generate(world, random, new BlockPos(x, y, z));
			}
		});
	}
}
