
import net.minecraft.world.entity.EntityType;
import ru.betterend.registry.EndFeatures;
import ru.betterend.registry.EndParticles;
import ru.betterend.registry.EndStructures;

		super("ice_starfield");
	}

	@Override
	protected boolean hasCaves() {
		return false;
	}

	@Override
		builder.structure(EndStructures.GIANT_ICE_STAR.getFeatureConfigured())
			   .fogColor(224, 245, 254)
			   .temperature(0F)
			   .fogDensity(2.2F)
			   .foliageColor(193, 244, 244)
			   .genChance(0.25F)
			   .particles(EndParticles.SNOWFLAKE, 0.002F)
			   .feature(EndFeatures.ICE_STAR)
			   .feature(EndFeatures.ICE_STAR_SMALL)
			   .spawn(EntityType.ENDERMAN, 20, 1, 4);
	}
}
