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
