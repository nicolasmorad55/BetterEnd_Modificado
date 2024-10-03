
import net.minecraft.resources.ResourceLocation;
import ru.betterend.registry.EndFeatures;
import ru.betterend.registry.EndParticles;


			this.addFloorFeature(EndFeatures.BIG_AURORA_CRYSTAL, 1);

			this.addCeilFeature(EndFeatures.END_STONE_STALACTITE, 1);
		}

		@Override
		public float getFloorDensity() {
			return 0.01F;
		}

		@Override
		public float getCeilDensity() {
			return 0.1F;
		}
	}

		super("empty_aurora_cave");
	}

	@Override
		super.addCustomBuildData(builder);
		builder.fogColor(150, 30, 68)
			   .fogDensity(2.0F)
			   .plantsColor(108, 25, 46)
			   .waterAndFogColor(186, 77, 237)
			   .particles(EndParticles.GLOWING_SPHERE, 0.001F);
	}

	@Override
	}
}
