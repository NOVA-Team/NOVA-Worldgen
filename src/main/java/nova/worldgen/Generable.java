/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nova.worldgen;

import nova.core.util.id.Identifiable;
import nova.core.util.id.Identifier;
import nova.core.util.id.StringIdentifier;

/**
 * This class describes a structure that will be generated
 * in the world by the world generator.
 *
 * @author ExE Boss
 */
public abstract class Generable implements Identifiable {
	public final String id;

	public Generable(String id) {
		this.id = id;
	}

	@Override
	public final Identifier getID() {
		return new StringIdentifier(id);
	}
}
