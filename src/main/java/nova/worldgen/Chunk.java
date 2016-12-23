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

import nova.core.block.Block;
import nova.core.block.BlockFactory;
import nova.core.util.shape.Cuboid;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Optional;

/**
 * Chunk is a unit of generated terrain, it does contain fixed
 * volume of voxels and has fixed position in world
 */
public abstract class Chunk {
	private final Vector3D position;

	public Chunk(Vector3D position) {
		this.position = position;
	}

	/**
	 * Gets the block which occupies the given position.
	 *
	 * @param position The position to query.
	 * @return The block at the position. If the block is air, it will return the air block. If no block is present (the void), it will return an empty optional.
	 * @throws IllegalArgumentException when position specified is outside this chunk
	 */
	public abstract Optional<Block> getBlock(Vector3D position) throws IllegalArgumentException;

	/**
	 * Sets the block occupying a given position.
	 *
	 * @param position The position of the block to set.
	 * @param blockFactory The block factory.
	 * @param args The block constructor arguments.
	 * @return {@code true} if the replace was successful.
	 * @throws IllegalArgumentException when position specified is outside this chunk
	 */
	public abstract boolean setBlock(Vector3D position, BlockFactory blockFactory, Object... args) throws IllegalArgumentException;

	/**
	 * @return Size of this chunk
	 */
	public abstract Cuboid getDimmensions();
}
