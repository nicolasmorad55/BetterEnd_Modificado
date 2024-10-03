package ru.betterend.integration;

import net.minecraft.core.MappedRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import ru.bclib.api.tag.NamedCommonBlockTags;
import ru.bclib.api.tag.TagAPI;
import ru.bclib.integration.ModIntegration;
import ru.bclib.world.features.BCLFeature;

public class EnderscapeIntegration extends ModIntegration {
	public EnderscapeIntegration() {
		super("enderscape");
	}
	
	@Override
	public void init() {
		Class<?> enderscape = getClass("net.enderscape.Enderscape");
			ResourceKey key = entry.getKey();
			}
			else {
			}
		});
		
		BCLFeature scatteredShadowQuartzOre = getFeature("scattered_shadow_quartz_ore", Decoration.UNDERGROUND_DECORATION);
		BCLFeature voidNebuliteOre = getFeature("void_nebulite_ore", Decoration.UNDERGROUND_DECORATION);
		BCLFeature nebuliteOre = getFeature("nebulite_ore", Decoration.UNDERGROUND_DECORATION);
		
			}
		});
		
		TagAPI.addBlockTag(NamedCommonBlockTags.GEN_END_STONES, getBlock("nebulite_ore"));
		TagAPI.addBlockTag(NamedCommonBlockTags.GEN_END_STONES, getBlock("shadow_quartz_ore"));
	}
}
