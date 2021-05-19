package ru.betterend.blocks.basis;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;
import ru.betterend.client.models.BlockModelProvider;
import ru.betterend.client.models.Patterns;

public class EndComposterBlock extends ComposterBlock implements BlockModelProvider {
	public EndComposterBlock(Block source) {
		super(FabricBlockSettings.copyOf(source));
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		return Collections.singletonList(new ItemStack(this.asItem()));
	}

	@Override
	public Optional<String> getModelString(String block) {
		ResourceLocation blockId = Registry.BLOCK.getKey(this);
		String blockName = blockId.getPath();
		return Patterns.createJson(Patterns.BLOCK_COMPOSTER, blockName);
	}

	@Override
	public BlockModel getModel(ResourceLocation resourceLocation) {
		return (BlockModel) getBlockModel(resourceLocation, defaultBlockState());
	}

	@Override
	public @Nullable UnbakedModel getBlockModel(ResourceLocation blockId, BlockState blockState) {
		return null;
	}

	@Override
	public UnbakedModel getModelVariant(ResourceLocation resourceLocation, BlockState blockState, Map<ResourceLocation, UnbakedModel> modelCache) {
		return null;
	}
}
