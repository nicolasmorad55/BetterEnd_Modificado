
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import ru.bclib.util.WeightedList;
import ru.bclib.world.features.BCLFeature;
import ru.betterend.BetterEnd;
import ru.betterend.registry.EndSounds;
import ru.betterend.world.features.terrain.caves.CaveChunkPopulatorFeature;

import java.util.Random;

		protected Config(String name) {
			super(name);
		}

		@Override
			BCLFeature feature = BCLFeature.makeChunkFeature(
					BetterEnd.makeID(ID.getPath() + "_cave_populator"),
					GenerationStep.Decoration.RAW_GENERATION,
			);

				   .feature(feature)
				   .music(EndSounds.MUSIC_CAVES)
				   .loop(EndSounds.AMBIENT_CAVES);
		}

		@Override
		protected boolean hasCaves() {
			return false;
		}

		@Override
		}
	}

	private WeightedList<Feature<?>> floorFeatures = new WeightedList<Feature<?>>();
	private WeightedList<Feature<?>> ceilFeatures = new WeightedList<Feature<?>>();

	}
	
	public void addFloorFeature(Feature<?> feature, float weight) {
		floorFeatures.add(feature, weight);
	}
	
	public void addCeilFeature(Feature<?> feature, float weight) {
		ceilFeatures.add(feature, weight);
	}
	
	public Feature<?> getFloorFeature(Random random) {
		return floorFeatures.isEmpty() ? null : floorFeatures.get(random);
	}
	
	public Feature<?> getCeilFeature(Random random) {
		return ceilFeatures.isEmpty() ? null : ceilFeatures.get(random);
	}
	
	public float getFloorDensity() {
		return 0;
	}
	
	public float getCeilDensity() {
		return 0;
	}
	
	public BlockState getCeil(BlockPos pos) {
		return null;
	}
	
	public BlockState getWall(BlockPos pos) {
		return null;
	}

	}
}
