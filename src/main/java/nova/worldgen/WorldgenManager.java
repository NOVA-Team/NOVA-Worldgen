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

import nova.core.event.bus.GlobalEvents;
import nova.core.util.registry.Manager;
import nova.core.util.registry.Registry;
import nova.worldgen.event.WorldgenEvent;
import nova.worldgen.ore.Ore;

import java.util.Optional;

/**
 *
 * @author ExE Boss
 */
public class WorldgenManager extends Manager<WorldgenManager> {
	public final Registry<Ore> registry;

	private final GlobalEvents events;

	public WorldgenManager(GlobalEvents events) {
		this.registry = new Registry<>();
		this.events = events;
	}

	public Ore register(Ore ore) {
		WorldgenEvent.RegisterOre event = new WorldgenEvent.RegisterOre(ore);
		this.events.publish(event);
		registry.register(event.ore);
		return event.ore;
	}

	public Optional<Ore> get(String ID) {
		return registry.get(ID);
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
