
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import ru.bclib.interfaces.SurfaceMaterialProvider;
import ru.betterend.registry.EndBlocks;
import ru.betterend.registry.EndEntities;
import ru.betterend.registry.EndFeatures;
import ru.betterend.registry.EndSounds;

		super("chorus_forest");
	}

	@Override
		builder
			.fogColor(87, 26, 87)
			.fogDensity(1.5F)
			.plantsColor(122, 45, 122)
			.waterAndFogColor(73, 30, 73)
			.particles(ParticleTypes.PORTAL, 0.01F)
			.loop(EndSounds.AMBIENT_CHORUS_FOREST)
			.music(EndSounds.MUSIC_DARK)
			.feature(EndFeatures.VIOLECITE_LAYER)
			.feature(EndFeatures.END_LAKE_RARE)
			.feature(EndFeatures.PYTHADENDRON_TREE)
			.feature(EndFeatures.PYTHADENDRON_BUSH)
			.feature(EndFeatures.PURPLE_POLYPORE)
			.feature(Decoration.VEGETAL_DECORATION, EndPlacements.CHORUS_PLANT)
			.feature(EndFeatures.CHORUS_GRASS)
			.feature(EndFeatures.CHORUS_MUSHROOM)
			.feature(EndFeatures.TAIL_MOSS)
			.feature(EndFeatures.TAIL_MOSS_WOOD)
			.feature(EndFeatures.CHARNIA_PURPLE)
			.feature(EndFeatures.CHARNIA_RED_RARE)
			.structure(VANILLA_FEATURES.getEndCity())
			.spawn(EndEntities.END_SLIME, 5, 1, 2)
			.spawn(EntityType.ENDERMAN, 50, 1, 4);
	}

	@Override
	protected SurfaceMaterialProvider surfaceMaterial() {
			@Override
			public BlockState getTopMaterial() {
				return EndBlocks.CHORUS_NYLIUM.defaultBlockState();
			}
		};
	}
}
