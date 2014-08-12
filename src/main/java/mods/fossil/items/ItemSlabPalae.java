package mods.fossil.items;

import mods.fossil.Fossil;
import net.minecraft.item.ItemSlab;

public class ItemSlabPalae extends ItemSlab{
	
	public ItemSlabPalae(int block) {
		super(block, Fossil.palaeSingleSlab, Fossil.palaeDoubleSlab, false);
	}
}
