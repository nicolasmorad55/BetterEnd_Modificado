package ru.betterend.world.structures.features;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import ru.bclib.util.MHelper;
import ru.betterend.registry.EndBlocks;
import ru.betterend.world.structures.piece.PaintedMountainPiece;

import java.util.Random;

public class PaintedMountainStructure extends FeatureBaseStructure {
	private static final BlockState[] VARIANTS;

	public PaintedMountainStructure() {
		super(PieceGeneratorSupplier.simple(
				FeatureBaseStructure::checkLocation,
				PaintedMountainStructure::generatePieces
		));
	}

	protected static void generatePieces(StructurePiecesBuilder structurePiecesBuilder, PieceGenerator.Context<NoneFeatureConfiguration> context) {
		final Random random = context.random();
		final ChunkPos chunkPos = context.chunkPos();
		final ChunkGenerator chunkGenerator = context.chunkGenerator();
		final LevelHeightAccessor levelHeightAccessor = context.heightAccessor();

		int x = chunkPos.getBlockX(MHelper.randRange(4, 12, random));
			int z = chunkPos.getBlockZ(MHelper.randRange(4, 12, random));
			int y = chunkGenerator.getBaseHeight(x, z, Types.WORLD_SURFACE_WG, levelHeightAccessor);
			if (y > 50) {
				float radius = MHelper.randRange(50, 100, random);
				float height = radius * MHelper.randRange(0.4F, 0.6F, random);
				int count = MHelper.floor(height * MHelper.randRange(0.1F, 0.35F, random) + 1);
				BlockState[] slises = new BlockState[count];
				for (int i = 0; i < count; i++) {
					slises[i] = VARIANTS[random.nextInt(VARIANTS.length)];
				}
			}
			
			//this.calculateBoundingBox();
		}

	
	static {
		VARIANTS = new BlockState[] {
			Blocks.END_STONE.defaultBlockState(),
			EndBlocks.FLAVOLITE.stone.defaultBlockState(),
			EndBlocks.VIOLECITE.stone.defaultBlockState(),
		};
	}
}
