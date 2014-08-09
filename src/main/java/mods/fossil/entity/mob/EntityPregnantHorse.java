package mods.fossil.entity.mob;

import java.util.Random;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.fossil.Fossil;
import mods.fossil.client.LocalizationStrings;
import mods.fossil.client.gui.GuiPedia;
import mods.fossil.fossilEnums.EnumAnimalType;
import mods.fossil.fossilInterface.IViviparous;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityPregnantHorse extends EntityHorse implements IViviparous, IEntityAdditionalSpawnData
{
    public int EmbryoProgress = 0;
    //public final int EmbryoGrowTime = 3000;
    public EnumAnimalType Embryo = null;
    //public String InsideText = "Embyo inside:";
    //public String GrowingText = "Growing progress:";
	public Attribute horseJumpStrength;

    public EntityPregnantHorse(World var1)
    {
        super(var1);
    }
    private void setPedia()
    {
        Fossil.ToPedia = (Object)this;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("EmbryoProgress", this.EmbryoProgress);
        var1.setByte("Inside", (byte)this.Embryo.ordinal());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        this.EmbryoProgress = var1.getInteger("EmbryoProgress");

        if (var1.hasKey("Inside"))
        {
            this.Embryo = EnumAnimalType.values()[var1.getByte("Inside")];
        }
    }

    public void SetEmbryo(EnumAnimalType var1)
    {
        this.Embryo = var1;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (FMLCommonHandler.instance().getSide().isClient() && itemstack != null && itemstack.itemID == Fossil.dinoPedia.itemID)
        {
            this.setPedia();
            player.openGui(Fossil.instance, 4, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
            return true;
        }
        else if (this.EmbryoProgress < 101)
        {
        	return true;
        }
        else
        {
            return super.interact(player);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        EntityHorse parentEntity = new EntityHorse(this.worldObj);
        float rnd = new Random().nextInt(100);

        if (this.Embryo == null)
        {
            this.setDead();
            this.worldObj.spawnEntityInWorld(parentEntity);
            parentEntity.setHorseType(this.getHorseType());
            parentEntity.setHorseVariant(this.getHorseVariant());
            parentEntity.setHorseTamed(this.isTame());
            parentEntity.setHorseSaddled(this.isHorseSaddled());
    		parentEntity.setOwnerName(this.getOwnerName());
    		parentEntity.setHorseTamed(this.isTame());
    		parentEntity.setTemper(this.getTemper());
    		parentEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
    		parentEntity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
    		parentEntity.setGrowingAge(this.getGrowingAge());  
        }

        ++this.EmbryoProgress;
        //int var10000 = this.EmbryoProgress;
        this.getClass();

        if (this.EmbryoProgress == this.Embryo.GrowTime) //var10000 == 3000)
        {
            Object birthEntity;

            switch (this.Embryo)//EntityPregnantCow$1.$SwitchMap$mod_Fossil$EnumEmbyos[this.Embyos.ordinal()])
            {
                case Pig:
                    birthEntity = new EntityPig(this.worldObj);
                    break;

                case Sheep:
                    birthEntity = new EntitySheep(this.worldObj);
                    break;

                case Cow:
                    birthEntity = new EntityCow(this.worldObj);
                    break;

                case Chicken:
                    birthEntity = new EntityChicken(this.worldObj);
                    break;
                    
                case Horse:
                	if(rnd < 1)
                	{
                		birthEntity = new EntityHorse(this.worldObj);
                		((EntityHorse)birthEntity).setHorseType(3);
                        if (this.getOwnerName() != null)
                        {
                		((EntityHorse)birthEntity).setOwnerName(this.getOwnerName());
                		((EntityHorse)birthEntity).setHorseTamed(true);
                        }
                		break;
                	}
                	else if(rnd < 2)
                	{
                		birthEntity = new EntityHorse(this.worldObj);
                		((EntityHorse)birthEntity).setHorseType(4);
                        if (this.getOwnerName() != null)
                        {
                		((EntityHorse)birthEntity).setOwnerName(this.getOwnerName());
                		((EntityHorse)birthEntity).setHorseTamed(true);
                        }
                		break;
                	}
                	else
                	{
                	birthEntity = super.createChild(new EntityHorse(this.worldObj));//new EntityHorse(this.worldObj);
                	break;
                	}

                case Smilodon:
                    birthEntity = new EntitySmilodon(this.worldObj).Imprinting(this.posX, this.posY, this.posZ);
                    break;

                case Mammoth:
                    birthEntity = (new EntityMammoth(this.worldObj)).Imprinting(this.posX, this.posY, this.posZ);
                    break;
                    
                case Quagga:
                    birthEntity = new EntityQuagga(this.worldObj);
                    
                    int d0 = (int)(this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + ((EntityQuagga)birthEntity).getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + (int)((EntityQuagga)birthEntity).randomHealthStat());
                    ((EntityQuagga)birthEntity).getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(d0 / 3.0D);
                    double d2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + ((EntityQuagga)birthEntity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + ((EntityQuagga)birthEntity).randomSpeedStat();
                    ((EntityQuagga)birthEntity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(d2 / 3.0D);
                    break;

                default:
                    birthEntity = new EntityPig(this.worldObj);
            }

            ((EntityAnimal)birthEntity).setGrowingAge(-24000);
            ((EntityAnimal)birthEntity).setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            parentEntity.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

            for (int var3 = 0; var3 < 7; ++var3)
            {
                double var4 = this.rand.nextGaussian() * 0.02D;
                double var6 = this.rand.nextGaussian() * 0.02D;
                double var8 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("heart", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
            }

            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.worldObj.spawnEntityInWorld((Entity)birthEntity);
                this.worldObj.spawnEntityInWorld(parentEntity);
                parentEntity.setHorseType(this.getHorseType());
                parentEntity.setHorseVariant(this.getHorseVariant());
                parentEntity.setHorseTamed(this.isTame());
                parentEntity.setHorseSaddled(this.isHorseSaddled());
        		parentEntity.setOwnerName(this.getOwnerName());
        		parentEntity.setHorseTamed(this.isTame());
        		parentEntity.setTemper(this.getTemper());
        		parentEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
        		parentEntity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
        		parentEntity.setGrowingAge(this.getGrowingAge());            
        	}
        }
        else
        {
            super.onLivingUpdate();
        }
    }
    @SideOnly(Side.CLIENT)
    public void ShowPedia(GuiPedia p0)
    {
        int quot = (int)Math.floor(((float)this.EmbryoProgress / (float)this.Embryo.GrowTime * 100.0F));
        p0.reset();
        p0.AddStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EMBRYO_INSIDE), false);
        p0.AddStringLR(StatCollector.translateToLocal("pedia.embryo." + this.Embryo.toString()), false, 40, 90, 245);
        p0.AddStringLR(StatCollector.translateToLocal(LocalizationStrings.PEDIA_EMBRYO_GROWING), false);
        p0.AddStringLR(String.valueOf(quot) + "/100", false);
        /*
        String var2 = "";
        this.UpdatePediaText();
        int var3 = (int)Math.floor((double)((float)this.EmbyoProgress / 3000.0F * 100.0F));
        Fossil.ShowMessage(this.InsideText + Fossil.GetEmbyoName(this.Embyos), var1);
        Fossil.ShowMessage(this.GrowingText + var3 + "%", var1);
        */
    }

    public EntityAnimal spawnBabyAnimal(EntityAnimal var1)
    {
        return null;
    }

    /*public void UpdatePediaText()
    {
        String var1 = "PediaText.vivi.";
        this.InsideText = Fossil.GetLangTextByKey("PediaText.vivi.inside");
        this.GrowingText = Fossil.GetLangTextByKey("PediaText.vivi.growing");
    }*/

    public void writeSpawnData(ByteArrayDataOutput var1)
    {
        var1.writeInt(this.Embryo.ordinal());
    }

    public void readSpawnData(ByteArrayDataInput var1)
    {
        this.Embryo = EnumAnimalType.values()[var1.readInt()];
    }
}
