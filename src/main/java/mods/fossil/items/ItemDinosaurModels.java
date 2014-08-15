package mods.fossil.items;

import java.util.List;
import java.util.Random;

import mods.fossil.Fossil;
import mods.fossil.blocks.BlockVaseAmphora;
import mods.fossil.entity.EntityDinoEgg;
import mods.fossil.entity.mob.EntityDinosaur;
import mods.fossil.entity.mob.EntityNautilus;
import mods.fossil.fossilEnums.EnumDinoType;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDinosaurModels extends Item {

	public ItemDinosaurModels(int ID) {
		super(ID);
		setHasSubtypes(true);
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
        return getUnlocalizedName() + "." + EnumDinoType.values()[itemstack.getItemDamage()].name();
	}
	
	@SideOnly(Side.CLIENT)
	public static Icon[] icons;

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
				icons[i] = icon.registerIcon(Fossil.modid + ":" + "dinosaur_bones/models/" + EnumDinoType.values()[i]+"_model");
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

    public boolean tryPlaceIntoWorld(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6/*, int var7, float var8, float var9, float var10*/)
    {
        if (var3.isRemote)
        {
            return true;
        }
        else
        {
            Class var11 = EnumDinoType.values()[var1.getItemDamage()].getDinoClass();
            EntityDinosaur var12;

            try
            {
                var12 = (EntityDinosaur)var11.getConstructor(new Class[] {World.class}).newInstance(new Object[] {var3});
            }
            catch (Throwable var14)
            {
                var14.printStackTrace();
                return false;
            }

            var12.setModelized(true);
            var12.setLocationAndAngles((double)var4, (double)(var5 + 1), (double)var6, var3.rand.nextFloat() * 360.0F, 0.0F);
            var12.faceEntity(var2, 360.0F, 360.0F);

            if (var3.checkNoEntityCollision(var12.boundingBox) && var3.getCollidingBoundingBoxes(var12, var12.boundingBox).size() == 0)
            {
                var3.spawnEntityInWorld(var12);
                --var1.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean spawnCreature(World var0, EnumDinoType var1, double var2, double var4, double var6)
    {
        Object var8;

        if (var1 == EnumDinoType.Nautilus)
        {
            var8 = new EntityNautilus(var0);
        }
        else
        {
            var8 = new EntityDinoEgg(var0, var1);
        }

        if (var8 != null)
        {
            ((Entity)var8).setLocationAndAngles(var2, var4, var6, var0.rand.nextFloat() * 360.0F, 0.0F);
            var0.spawnEntityInWorld((Entity)var8);
        }

        return var8 != null;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        float var4 = 1.0F;
        float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
        float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
        double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double)var4;
        double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double)var4 + 1.62D - (double)var3.yOffset;
        double var11 = var3.prevPosZ + (var3.posZ - var3.prevPosZ) * (double)var4;
        Vec3 var13 = var2.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * 0.017453292F - (float)Math.PI);
        float var15 = MathHelper.sin(-var6 * 0.017453292F - (float)Math.PI);
        float var16 = -MathHelper.cos(-var5 * 0.017453292F);
        float var17 = MathHelper.sin(-var5 * 0.017453292F);
        float var18 = var15 * var16;
        float var19 = var14 * var16;
        double var20 = 5.0D;
        Vec3 var22 = var13.addVector((double)var18 * var20, (double)var17 * var20, (double)var19 * var20);
        MovingObjectPosition var23 = this.getMovingObjectPositionFromPlayer(var2, var3, true);

        if (var23 == null)
        {
            return var1;
        }
        else if (var23.typeOfHit == EnumMovingObjectType.TILE && var2.getBlockMaterial(var23.blockX, var23.blockY, var23.blockZ).isSolid())
        {
            int var34 = var23.blockX;
            int var32 = var23.blockY;
            int var33 = var23.blockZ;
            this.tryPlaceIntoWorld(var1, var3, var2, var34, var32, var33);
        }

        return var1;
    }
}
