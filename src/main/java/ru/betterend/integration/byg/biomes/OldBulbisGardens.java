
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import ru.bclib.BCLib;
import ru.betterend.BetterEnd;
import ru.betterend.integration.Integrations;
import ru.betterend.integration.byg.features.BYGFeatures;
import ru.betterend.registry.EndFeatures;

import java.util.List;
import java.util.function.Supplier;


	public OldBulbisGardens() {
		super("old_bulbis_gardens");
	}

	@Override

		Block ivis = Integrations.BYG.getBlock("ivis_phylium");
//							.getSurfaceBuilderConfig()
//							.getTopMaterial()
//							.getBlock();
		builder.fogColor(215, 132, 207)
			   .fogDensity(1.8F)
			   .waterAndFogColor(40, 0, 56)
			   .foliageColor(122, 17, 155)
			   .particles(
					   ParticleTypes.REVERSE_PORTAL,
					   0.002F
			   )
			   //TODO: 1.18 surface rules
			   //.surface(ivis, origin)
			   .feature(EndFeatures.END_LAKE_RARE)
			   .feature(BYGFeatures.OLD_BULBIS_TREE);

		if (BCLib.isClient()) {
			SoundEvent loop = effects.getAmbientLoopSoundEvent()
									 .get();
			SoundEvent music = effects.getBackgroundMusic()
									  .get()
									  .getEvent();
			SoundEvent additions = effects.getAmbientAdditionsSettings()
										  .get()
										  .getSoundEvent();
			SoundEvent mood = effects.getAmbientMoodSettings()
									 .get()
									 .getSoundEvent();
			builder.loop(loop)
				   .music(music)
				   .additions(additions)
				   .mood(mood);
		}

		for (MobCategory group : MobCategory.values()) {
										  .getMobs(group)
										  .unwrap();
			list.forEach((entry) -> {
				builder.spawn((EntityType<? extends Mob>) entry.type, 1, entry.minCount, entry.maxCount);
			});
		}

															.features();
		List<Supplier<PlacedFeature>> vegetal = features.get(Decoration.VEGETAL_DECORATION.ordinal());
		if (vegetal.size() > 2) {
			Supplier<PlacedFeature> getter;
			// Trees (first two features)
			// I couldn't process them with conditions, so that's why they are hardcoded (paulevs)
			for (int i = 0; i < 2; i++) {
				getter = vegetal.get(i);
				PlacedFeature feature = getter.get();
				ResourceLocation id = BetterEnd.makeID("obg_feature_" + i);
				feature = Registry.register(
						BuiltinRegistries.PLACED_FEATURE,
						id,
						//TODO: 1.18 Check if this is correct
						feature//.decorated(FeaturesAccesor.shadowHEIGHTMAP_SQUARE).countRandom(1)
				);
				builder.feature(Decoration.VEGETAL_DECORATION, feature);
			}
			// Grasses and other features
			for (int i = 2; i < vegetal.size(); i++) {
				getter = vegetal.get(i);
				PlacedFeature feature = getter.get();
				builder.feature(Decoration.VEGETAL_DECORATION, feature);
			}
		}

		builder.feature(EndFeatures.PURPLE_POLYPORE)
			   .feature(BYGFeatures.IVIS_MOSS_WOOD)
			   .feature(BYGFeatures.IVIS_MOSS)
			   .feature(BYGFeatures.IVIS_VINE)
			   .feature(BYGFeatures.IVIS_SPROUT);
	}
}
