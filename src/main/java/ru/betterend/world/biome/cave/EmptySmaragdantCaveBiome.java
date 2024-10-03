
import net.minecraft.resources.ResourceLocation;
import ru.betterend.registry.EndFeatures;
import ru.betterend.registry.EndParticles;


			this.addFloorFeature(EndFeatures.SMARAGDANT_CRYSTAL, 1);
			this.addFloorFeature(EndFeatures.SMARAGDANT_CRYSTAL_SHARD, 20);

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

		super("empty_smaragdant_cave");
	}

	@Override
		super.addCustomBuildData(builder);
		builder.fogColor(0, 253, 182)
				.fogDensity(2.0F)
				.plantsColor(0, 131, 145)
				.waterAndFogColor(31, 167, 212)
				.particles(EndParticles.SMARAGDANT, 0.001F);
	}

	@Override
	}
}
