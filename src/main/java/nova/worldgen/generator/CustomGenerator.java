/*
 * Copyright (c) 2017 NOVA, All rights reserved.
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
package nova.worldgen.generator;

import nova.core.world.World;
import nova.worldgen.WorldgenManager;
import nova.worldgen.world.WorldInfo;

import java.util.Random;

/**
 * This is a black box generator.
 * Unlike {@link nova.worldgen.ore.Ore},
 * this one is implemented by the mod, not the game.
 *
 * @author ExE Boss
 */
public abstract class CustomGenerator extends Generator {

	protected final WorldgenManager worldgenManager;

	public CustomGenerator(String id, WorldgenManager worldgenManager) {
		super(id);
		this.worldgenManager = worldgenManager;
	}

	public abstract void generate(Random random, int chunkX, int chunkZ, double worldScale, World worlda, WorldInfo worldInfo);
}
