package ru.betterend.world.features.terrain.caves;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import ru.bclib.api.tag.CommonBlockTags;
import ru.bclib.util.BlocksHelper;
import ru.bclib.world.features.DefaultFeature;
import ru.betterend.util.BlockFixer;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class CaveChunkPopulatorFeature extends DefaultFeature {
	
	}
	
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featureConfig) {
		final Random random = featureConfig.random();
		final BlockPos pos = featureConfig.origin();
		final WorldGenLevel world = featureConfig.level();
		Set<BlockPos> floorPositions = Sets.newHashSet();
		Set<BlockPos> ceilPositions = Sets.newHashSet();
		int sx = (pos.getX() >> 4) << 4;
		int sz = (pos.getZ() >> 4) << 4;
		MutableBlockPos min = new MutableBlockPos().set(pos);
		MutableBlockPos max = new MutableBlockPos().set(pos);
		fillSets(sx, sz, world.getChunk(pos), floorPositions, ceilPositions, min, max);
		BlockFixer.fixBlocks(world, min, max);
		return true;
	}
	
	protected void fillSets(int sx, int sz, ChunkAccess chunk, Set<BlockPos> floorPositions, Set<BlockPos> ceilPositions, MutableBlockPos min, MutableBlockPos max) {
		MutableBlockPos mut = new MutableBlockPos();
		MutableBlockPos mut2 = new MutableBlockPos();
		MutableBlockPos mut3 = new MutableBlockPos();
		for (int x = 0; x < 16; x++) {
			mut.setX(x);
			mut2.setX(x);
			for (int z = 0; z < 16; z++) {
				mut.setZ(z);
				mut2.setZ(z);
				mut2.setY(0);
				for (int y = 1; y < chunk.getMaxBuildHeight(); y++) {
					mut.setY(y);
					BlockState top = chunk.getBlockState(mut);
					BlockState bottom = chunk.getBlockState(mut2);
					if (top.isAir() && (bottom.is(CommonBlockTags.GEN_END_STONES) || bottom.is(Blocks.STONE))) {
						mut3.set(mut2).move(sx, 0, sz);
						floorPositions.add(mut3.immutable());
						updateMin(mut3, min);
						updateMax(mut3, max);
					}
					else if (bottom.isAir() && (top.is(CommonBlockTags.GEN_END_STONES) || top.is(Blocks.STONE))) {
						mut3.set(mut).move(sx, 0, sz);
						ceilPositions.add(mut3.immutable());
						updateMin(mut3, min);
						updateMax(mut3, max);
					}
					mut2.setY(y);
				}
			}
		}
	}
	
	private void updateMin(BlockPos pos, MutableBlockPos min) {
		if (pos.getX() < min.getX()) {
			min.setX(pos.getX());
		}
		if (pos.getY() < min.getY()) {
			min.setY(pos.getY());
		}
		if (pos.getZ() < min.getZ()) {
			min.setZ(pos.getZ());
		}
	}
	
	private void updateMax(BlockPos pos, MutableBlockPos max) {
		if (pos.getX() > max.getX()) {
			max.setX(pos.getX());
		}
		if (pos.getY() > max.getY()) {
			max.setY(pos.getY());
		}
		if (pos.getZ() > max.getZ()) {
			max.setZ(pos.getZ());
		}
	}
	
		floorPositions.forEach((pos) -> {
			BlocksHelper.setWithoutUpdate(world, pos, surfaceBlock);
			if (density > 0 && random.nextFloat() <= density) {
				if (feature != null) {
					feature.place(new FeaturePlaceContext<>(Optional.empty(), world, null, random, pos.above(), null));
				}
			}
		});
	}
	
		ceilPositions.forEach((pos) -> {
			if (ceilBlock != null) {
				BlocksHelper.setWithoutUpdate(world, pos, ceilBlock);
			}
			if (density > 0 && random.nextFloat() <= density) {
				if (feature != null) {
					feature.place(new FeaturePlaceContext<>(Optional.empty(), world, null, random, pos.below(), null));
				}
			}
		});
	}
}
