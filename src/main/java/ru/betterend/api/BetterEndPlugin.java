package ru.betterend.api;

public interface BetterEndPlugin {
	/**
	 * Alloying recipes registration.
	 * See AlloyingRecipe.Builder for details.
	 */
	default void registerAlloyingRecipes() {
	}
	
	/**
	 * Smithing recipes registration.
	 * See AnvilSmithingRecipe.Builder for details.
	 */
	default void registerSmithingRecipes() {
	}
	
	/**
	 */
	}
	
	/**
	 * Register other mod stuff, for example, EndITEM_HAMMERS.
	 */
	default void registerOthers() {
	}
	
	
	public static void register(BetterEndPlugin plugin) {
		plugin.registerAlloyingRecipes();
		plugin.registerSmithingRecipes();
		plugin.registerOthers();
	}
}
