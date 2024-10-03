package ru.betterend.integration.byg;

import ru.bclib.integration.ModIntegration;

	public BYGIntegration() {
		super("byg");
	}
	
	@Override
	public void init() {
		/*Block block = Integrations.BYG.getBlock("ivis_phylium");
		if (block != null) {
			TagAPI.addTags(block, CommonBlockTags.END_STONES, CommonBlockTags.GEN_END_STONES);
		}
		BYGBlocks.register();
		BYGFeatures.register();
	}
	
	@Override
	}
}
