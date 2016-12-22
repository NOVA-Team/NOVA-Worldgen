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

package nova.worldgen.wrapper.mc.forge.v1_11.wrapper.world;

import nova.core.util.registry.Registry;
import nova.core.util.shape.Cuboid;
import nova.worldgen.WorldgenManager;
import nova.worldgen.ore.Ore;
import nova.worldgen.world.WorldInfo;

/**
 * @author ExE Boss
 */
public class MCWorldInfo extends WorldInfo {

	private WorldgenManager worldgenManager;

	public void init(WorldgenManager worldgenManager) {
		this.worldgenManager = worldgenManager;
	}

	@Override
	public Cuboid getWorldDimmensions() {
		return new Cuboid(Double.NEGATIVE_INFINITY, 0, Double.NEGATIVE_INFINITY,
						  Double.POSITIVE_INFINITY, 256, Double.POSITIVE_INFINITY);
	}

	@Override
	public Cuboid getWorldGenerationUnitDimmensions() {
		return new Cuboid(0, 0, 0, 16, 16, 16);
	}

	@Override
	public Registry<Ore> getOreGenerationRegistry() {
		return worldgenManager.registry.stream().filter(gen -> gen instanceof Ore).map(ore -> (Ore) ore).collect(Registry::new, Registry::register, (r1, r2) -> r2.forEach(r1::register));
	}
}
