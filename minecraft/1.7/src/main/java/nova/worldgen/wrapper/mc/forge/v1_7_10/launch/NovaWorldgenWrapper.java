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

package nova.worldgen.wrapper.mc.forge.v1_7_10.launch;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import nova.core.loader.Mod;
import nova.core.wrapper.mc.forge.v17.launcher.ForgeLoadable;
import nova.internal.worldgen.depmodules.WorldgenModule;
import nova.worldgen.WorldgenManager;
import nova.worldgen.wrapper.mc.forge.v1_7_10.depmodules.MCWorldgenModule;
import nova.worldgen.wrapper.mc.forge.v1_7_10.wrapper.world.MCWorldInfo;
import nova.worldgen.wrapper.mc.forge.v1_7_10.wrapper.world.forward.FWWorldGenerator;

/**
 *
 * @author ExE Boss
 */
@Mod(id = NovaWorldgenWrapper.id, name = NovaWorldgenWrapper.name, version = NovaWorldgenWrapper.version, modules = { MCWorldgenModule.class, WorldgenModule.class }, novaVersion = "0.0.1")
public class NovaWorldgenWrapper implements ForgeLoadable {

	public static final String version = "0.0.1";
	public static final String id = "nova-worldgen-wrapper";
	public static final String name = "NOVA Worldgen";

	public final WorldgenManager worldgenManager;

	public FWWorldGenerator worldGenerator;

	public NovaWorldgenWrapper(WorldgenManager worldgenManager) {
		this.worldgenManager = worldgenManager;
		((MCWorldInfo)this.worldgenManager.getWorldInfo()).init(worldgenManager);
		this.worldGenerator = new FWWorldGenerator(this.worldgenManager);
	}

	@Override
	public void preInit(FMLPreInitializationEvent evt) {
		GameRegistry.registerWorldGenerator(this.worldGenerator, 0);
		worldgenManager.init();
	}
}
