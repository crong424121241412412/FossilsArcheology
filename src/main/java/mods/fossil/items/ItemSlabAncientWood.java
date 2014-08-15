package mods.fossil.items;

import mods.fossil.Fossil;
import net.minecraft.item.ItemSlab;

public class ItemSlabAncientWood extends ItemSlab{
	
	public ItemSlabAncientWood(int block) {
		super(block, Fossil.ancientWoodSingleSlab, Fossil.ancientWoodDoubleSlab, false);
	}
}
