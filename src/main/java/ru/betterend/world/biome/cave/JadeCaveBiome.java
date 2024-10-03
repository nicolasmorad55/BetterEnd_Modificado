
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import ru.betterend.noise.OpenSimplexNoise;
import ru.betterend.registry.EndBlocks;

		private static final OpenSimplexNoise WALL_NOISE = new OpenSimplexNoise("jade_cave".hashCode());
		private static final OpenSimplexNoise DEPTH_NOISE = new OpenSimplexNoise("depth_noise".hashCode());
		private static final BlockState[] JADE = new BlockState[3];


			JADE[0] = EndBlocks.VIRID_JADESTONE.stone.defaultBlockState();
			JADE[1] = EndBlocks.AZURE_JADESTONE.stone.defaultBlockState();
			JADE[2] = EndBlocks.SANDY_JADESTONE.stone.defaultBlockState();
		}

		@Override
		public BlockState getWall(BlockPos pos) {
			double depth = DEPTH_NOISE.eval(pos.getX() * 0.02, pos.getZ() * 0.02) * 0.2 + 0.5;
			int index = Mth.floor((pos.getY() + WALL_NOISE.eval(pos.getX() * 0.2, pos.getZ() * 0.2) * 1.5) * depth + 0.5);
			index = Mth.abs(index) % 3;
			return JADE[index];
		}
	}

		super("jade_cave");
	}

	@Override
		super.addCustomBuildData(builder);
		builder.fogColor(118, 150, 112)
			   .fogDensity(2.0F)
			   .waterAndFogColor(95, 223, 255);
	}

	@Override
	}
}
