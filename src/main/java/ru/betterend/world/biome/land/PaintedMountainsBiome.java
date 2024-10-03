
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import ru.bclib.interfaces.SurfaceMaterialProvider;
import ru.betterend.registry.EndBlocks;
import ru.betterend.registry.EndSounds;
import ru.betterend.registry.EndStructures;

		super("painted_mountains");
	}

	@Override
		builder
			.structure(EndStructures.PAINTED_MOUNTAIN.getFeatureConfigured())
			.fogColor(226, 239, 168)
			.fogDensity(2)
			.waterAndFogColor(192, 180, 131)
			.music(EndSounds.MUSIC_OPENSPACE)
			.loop(EndSounds.AMBIENT_DUST_WASTELANDS)
			.particles(ParticleTypes.WHITE_ASH, 0.01F)
			.spawn(EntityType.ENDERMAN, 50, 1, 2);
	}

	@Override
	protected SurfaceMaterialProvider surfaceMaterial() {
			@Override
			public BlockState getTopMaterial() {
				return EndBlocks.ENDSTONE_DUST.defaultBlockState();
			}
		};
	}
}
