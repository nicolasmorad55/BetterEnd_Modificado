package ru.betterend.blocks;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import ru.betterend.blocks.basis.VineBlock;
import ru.betterend.interfaces.IColorProvider;
import ru.betterend.registry.EndParticles;
import ru.betterend.util.MHelper;

public class TenaneaFlowersBlock extends VineBlock implements IColorProvider {
	public static final Vec3i[] COLORS;
	
	public TenaneaFlowersBlock() {
		super(15);
	}

	@Override
	public BlockColorProvider getProvider() {
		return (state, world, pos, tintIndex) -> {
			long i = (MHelper.getRandom(pos.getX(), pos.getZ()) & 63) + pos.getY();
			double delta = i * 0.1;
			int index = MHelper.floor(delta);
			int index2 = (index + 1) & 3;
			delta -= index;
			index &= 3;
			
			Vec3i color1 = COLORS[index];
			Vec3i color2 = COLORS[index2];
			
			int r = MHelper.floor(MathHelper.lerp(delta, color1.getX(), color2.getX()));
			int g = MHelper.floor(MathHelper.lerp(delta, color1.getY(), color2.getY()));
			int b = MHelper.floor(MathHelper.lerp(delta, color1.getZ(), color2.getZ()));
			float[] hsb = MHelper.fromRGBtoHSB(r, g, b);
			
			return MHelper.fromHSBtoRGB(hsb[0], MHelper.max(0.5F, hsb[1]), hsb[2]);
		};
	}

	@Override
	public ItemColorProvider getItemProvider() {
		return (stack, tintIndex) -> {
			return MHelper.color(255, 255, 255);
		};
	}
	
	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}
	
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(32) == 0) {
			double x = (double) pos.getX() + random.nextGaussian() + 0.5;
			double z = (double) pos.getZ() + random.nextGaussian() + 0.5;
			double y = (double) pos.getY() + random.nextDouble();
			world.addParticle(EndParticles.TENANEA_PETAL, x, y, z, 0, 0, 0);
		}
	}
	
	static {
		COLORS = new Vec3i[] {
			new Vec3i(250, 111, 222),
			new Vec3i(167, 89, 255),
			new Vec3i(120, 207, 239),
			new Vec3i(255, 87, 182)
		};
	}
}