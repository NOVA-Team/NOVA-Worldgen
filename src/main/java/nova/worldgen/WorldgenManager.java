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

package nova.worldgen;

import nova.worldgen.generator.Generator;
import nova.core.event.bus.GlobalEvents;
import nova.core.util.registry.Manager;
import nova.core.util.registry.Registry;
import nova.worldgen.event.WorldgenEvent;
import nova.worldgen.generator.CustomGenerator;
import nova.worldgen.ore.Ore;
import nova.worldgen.world.WorldInfo;

import java.util.Optional;

/**
 *
 * @author ExE Boss
 */
public class WorldgenManager extends Manager<WorldgenManager> {
	public final Registry<Generator> registry;

	private final WorldInfo worldInfo;
	private final GlobalEvents events;

	public WorldgenManager(Registry<Generator> registry, GlobalEvents events, WorldInfo worldInfo) {
		this.registry = registry;
		this.events = events;
		this.worldInfo = worldInfo;
	}

	public Ore register(Ore ore) {
		WorldgenEvent.Register<Ore> event = new WorldgenEvent.Register<>(ore);
		this.events.publish(event);
		registry.register(event.generable);
		return event.generable;
	}

	public CustomGenerator register(CustomGenerator generator) {
		WorldgenEvent.Register<CustomGenerator> event = new WorldgenEvent.Register<>(generator);
		this.events.publish(event);
		registry.register(event.generable);
		return event.generable;
	}

	public Optional<Generator> get(String ID) {
		return registry.get(ID);
	}

	public WorldInfo getWorldInfo() {
		return this.worldInfo;
	}

	@Override
	public void init() {
		this.events.publish(new Init(this));
	}

	public class Init extends ManagerEvent<WorldgenManager> {
		public Init(WorldgenManager manager) {
			super(manager);
		}
	}
}
