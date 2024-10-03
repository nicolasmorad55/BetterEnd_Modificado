package ru.betterend.world.features.terrain;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;
import ru.bclib.api.tag.CommonBlockTags;
import ru.bclib.sdf.SDF;
import ru.bclib.sdf.operator.SDFDisplacement;
import ru.bclib.sdf.operator.SDFSmoothUnion;
import ru.bclib.sdf.operator.SDFTranslate;
import ru.bclib.sdf.primitive.SDFSphere;
import ru.bclib.util.MHelper;
import ru.bclib.world.features.DefaultFeature;
import ru.betterend.noise.OpenSimplexNoise;
import ru.betterend.registry.EndFeatures;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class SpireFeature extends DefaultFeature {
	protected static final Function<BlockState, Boolean> REPLACE;
	
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featureConfig) {
		final Random random = featureConfig.random();
		BlockPos pos = featureConfig.origin();
		final WorldGenLevel world = featureConfig.level();
		final ChunkGenerator chunkGenerator = featureConfig.chunkGenerator();
		pos = getPosOnSurfaceWG(world, pos);
		if (pos.getY() < 10 || !world.getBlockState(pos.below(3))
									 .is(CommonBlockTags.GEN_END_STONES) || !world.getBlockState(pos.below(6))
																				  .is(CommonBlockTags.GEN_END_STONES)) {
			return false;
		}
		
		SDF sdf = new SDFSphere().setRadius(MHelper.randRange(2, 3, random)).setBlock(Blocks.END_STONE);
		int count = MHelper.randRange(3, 7, random);
		for (int i = 0; i < count; i++) {
			float rMin = (i * 1.3F) + 2.5F;
			sdf = addSegment(sdf, MHelper.randRange(rMin, rMin + 1.5F, random), random);
		}
		OpenSimplexNoise noise = new OpenSimplexNoise(random.nextLong());
		sdf = new SDFDisplacement().setFunction((vec) -> {
			return (float) (Math.abs(noise.eval(
				vec.x() * 0.1,
				vec.y() * 0.1,
				vec.z() * 0.1
			)) * 3F + Math.abs(noise.eval(vec.x() * 0.3, vec.y() * 0.3 + 100, vec.z() * 0.3)) * 1.3F);
		}).setSource(sdf);
		final BlockPos center = pos;
		List<BlockPos> support = Lists.newArrayList();
		sdf.setReplaceFunction(REPLACE).addPostProcess((info) -> {
			if (info.getStateUp().isAir()) {
				if (random.nextInt(16) == 0) {
					support.add(info.getPos().above());
				}
			}
			else if (info.getState(Direction.UP, 3).isAir()) {
//							.getGenerationSettings()
//							.getSurfaceBuilderConfig()
//							.getUnderMaterial();
			}
			return info.getState();
		}).fillRecursive(world, center);
		
		support.forEach((bpos) -> {
				EndFeatures.TENANEA_BUSH.getFeature()
										.place(new FeaturePlaceContext<>(Optional.empty(), world, chunkGenerator, random, bpos, null));
			}
		});
		
		return true;
	}
	
	protected SDF addSegment(SDF sdf, float radius, Random random) {
		SDF sphere = new SDFSphere().setRadius(radius).setBlock(Blocks.END_STONE);
		SDF offseted = new SDFTranslate().setTranslate(0, radius + random.nextFloat() * 0.25F * radius, 0)
										 .setSource(sdf);
		return new SDFSmoothUnion().setRadius(radius * 0.5F).setSourceA(sphere).setSourceB(offseted);
	}
	
	static {
		REPLACE = (state) -> {
			if (state.is(CommonBlockTags.END_STONES)) {
				return true;
			}
			if (state.getBlock() instanceof LeavesBlock) {
				return true;
			}
			if (state.getMaterial().equals(Material.PLANT)) {
				return true;
			}
			return state.getMaterial().isReplaceable();
		};
	}
}
