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

package nova.worldgen.wrapper.mc.forge.v1_8.wrapper.world.forward;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import nova.internal.core.Game;
import nova.worldgen.WorldgenManager;
import nova.worldgen.event.WorldgenEvent;
import nova.worldgen.generator.CustomGenerator;
import nova.worldgen.ore.Ore;
import nova.worldgen.ore.OreHeight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author ExE Boss
 */
public class FWWorldGenerator implements IWorldGenerator {

	private final Map<Ore, WorldGenMinable> oreGen;
	private final Set<CustomGenerator> customGenerators;
	private final WorldgenManager worldgenManager;

	public FWWorldGenerator(WorldgenManager worldgenManager) {
		this.worldgenManager = worldgenManager;
		this.oreGen = new HashMap<>();
		this.customGenerators = new HashSet<>();
		Game.events().on(WorldgenEvent.Register.class).bind(evt -> {
			if (evt.generable instanceof Ore) {
				this.oreGen.put((Ore) evt.generable, new WorldGenMinable(((Block)Game.natives().toNative(((Ore)evt.generable).block.build())).getDefaultState(), (int) Math.round(((Ore)evt.generable).clusterSize * 5)));
			} else if (evt.generable instanceof CustomGenerator) {
				this.customGenerators.add((CustomGenerator) evt.generable);
			}
		});
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		double worldScale = world.provider.getAverageGroundLevel() / 64D;
		this.oreGen.forEach((ore, generator) -> {
			double baseCount = 12 * ore.rarity * worldScale;
			int count = (int) Math.round(random.nextGaussian() * Math.sqrt(baseCount) + baseCount);
			for(int i = 0; i < count; i++) {
				// OreHeight Values: (when world.provider.getAverageGroundLevel() == 64)
				//  SURFACE = 60 (55-65)
				//  UNDERSURFACE = 50 (45-55)
				//  DEEP = 40 (35-45)
				//  DEEPER = 30 (25-35)
				//  DEEPERER = 20 (15-25)
				//  REALLYDEEP = 10 (5-15)

				List<OreHeight> oreHeightList = Arrays.stream(OreHeight.values()).filter(ore.oreLayers::allows).collect(Collectors.toList());
				if (oreHeightList.isEmpty()) return;

				OreHeight height = oreHeightList.get(random.nextInt(oreHeightList.size()));
				double yAdd = (OreHeight.values().length - height.ordinal() - 1) * (10 * worldScale) + (5 * worldScale);

				int x = chunkX + random.nextInt(16);
				int y = (int) Math.round(yAdd + (random.nextDouble() * 10 * worldScale));
				int z = chunkZ + random.nextInt(16);

				generator.generate(world, random, new BlockPos(x, y, z));
			}
		});
		this.customGenerators.forEach(generator -> generator.generate(random, chunkX, chunkZ, worldScale, Game.natives().toNova(world), this.worldgenManager.getWorldInfo()));
	}
}
