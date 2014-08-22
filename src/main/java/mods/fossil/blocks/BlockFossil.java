package mods.fossil.blocks;

import mods.fossil.Fossil;
import mods.fossil.fossilEnums.EnumDinoType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockFossil extends BlockStone
{
    private int randomMeta;
    Random rand = new Random();

	public BlockFossil(int var1, int var2)
    {
        super(var1);
        randomMeta = 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        int i = (new Random()).nextInt(1000);

        if (i < 1)
        {
            return Fossil.gem.itemID;
        }

        if (i < 6)
        {
            return Fossil.brokenSword.itemID;
        }

        if (i < 11)
        {
            return Fossil.brokenhelmet.itemID;
        }

        if (i < 13)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.legBone.itemID;
        }

        if (i < 15)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.skull.itemID;
        }

        if (i < 17)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.claw.itemID;
        }

        if (i < 19)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.foot.itemID;
        }
        
        if (i < 21)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.vertebrae.itemID;
        }
        
        if (i < 23)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.armBone.itemID;
        }
        
        if (i < 25)
        {
        	int dropRandom = rand.nextInt(EnumDinoType.values().length)+1;
        	if(dropRandom != 4)
        		this.randomMeta = dropRandom;
            return Fossil.dinoRibCage.itemID;
        }

        if (i < 50)
        {
            return Fossil.blockSkull.blockID;
        }

        if (i < 350)
        {
            return Fossil.biofossil.itemID;
        }

        if (i < 550)
        {
            return Fossil.relic.itemID;
        }

        if (i < 900)
        {
            return Item.bone.itemID;
        }

        return Block.cobblestone.blockID;
    }
    
    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int j = par2Random.nextInt(par1 + 1) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(par2Random) * (j + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        int count = quantityDropped(metadata, fortune, world.rand);
        for(int i = 0; i < count; i++)
        {
            int id = idDropped(metadata, world.rand, fortune);
            if (id > 0)
            {
                ret.add(new ItemStack(id, 1, damageDropped(metadata)));
//                ret.add(new ItemStack(Fossil.biofossil.itemID, 1, 0));
            }
        }
        return ret;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("fossil:Fossil"); //adding in a texture, 1.5.1 style!
    }
}