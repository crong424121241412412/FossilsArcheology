package mods.fossil.items;

import java.util.List;

import mods.fossil.Fossil;
import mods.fossil.blocks.BlockVaseAmphora;
import mods.fossil.fossilEnums.EnumDinoType;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDinosaurBones extends Item {

	String itemType;

	public ItemDinosaurBones(int ID, String _itemType) {
		super(ID);
		this.itemType = _itemType;

		this.setCreativeTab(Fossil.tabFBones);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + EnumDinoType.values()[itemstack.getItemDamage()].name();
	}
	
	@SideOnly(Side.CLIENT)
	public Icon[] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) {
	return icons[damage];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
	icons = new Icon[EnumDinoType.values().length];

		for(int i = 0; i < icons.length; i++) 
		{
			if(i != 4) //Silly Nautilus, bones are for dinosaurs.
			{
			icons[i] = icon.registerIcon(Fossil.modid + ":" + "dinosaur_bones/" + this.itemType + "/" + EnumDinoType.values()[i] + "_" + this.itemType);
			}
		}
	}

	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			if(i != 4) //Silly Nautilus, bones are for dinosaurs.
			{
				ItemStack itemstack = new ItemStack(id, 1, i);
				list.add(itemstack);
			}
		}
	}
		
}
