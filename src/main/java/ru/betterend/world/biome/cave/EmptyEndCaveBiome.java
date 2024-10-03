
import net.minecraft.resources.ResourceLocation;
import ru.betterend.registry.EndFeatures;

import java.util.function.BiFunction;


			this.addFloorFeature(EndFeatures.END_STONE_STALAGMITE, 1);
			this.addCeilFeature(EndFeatures.END_STONE_STALACTITE, 1);
		}

		@Override
		public float getFloorDensity() {
			return 0.1F;
		}

		@Override
		public float getCeilDensity() {
			return 0.1F;
		}
	}

		super("empty_end_cave");
	}

	@Override
		super.addCustomBuildData(builder);
		builder.fogDensity(2.0F);
	}

	@Override
	}
}
