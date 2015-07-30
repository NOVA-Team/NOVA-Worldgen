package nova.worldgen.ore;

//TODO: Find better names for those

/**
 * This enum allows game wrapers to adjust where in its' world an ore should be spawned
 */
public enum OreHeight {
	/**
	 * Ore can be found on surface level
	 */
	SURFACE,

	/**
	 * Ore can be found just under surface
	 */
	UNDERSURFACE,

	/**
	 * Ore can be found deep under the surface
	 */
	DEEP,

	/**
	 * Ore can be found deep deep under the surface
	 */
	DEEPER,

	/**
	 * Ore can be found deep deep deep under the surface
	 */
	DEEPERER,

	/**
	 * Ore can be found deep deep deep deep under the surface
	 */
	REALLYDEEP,
}
