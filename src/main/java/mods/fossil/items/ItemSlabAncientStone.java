package mods.fossil.items;

import mods.fossil.Fossil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class ItemSlabAncientStone extends ItemSlab{
	
	public ItemSlabAncientStone(int block) {
		super(block, Fossil.ancientStoneSingleSlab, Fossil.ancientStoneDoubleSlab, false);
	}
}
