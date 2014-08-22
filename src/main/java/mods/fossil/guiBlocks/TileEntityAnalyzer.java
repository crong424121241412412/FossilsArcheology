package mods.fossil.guiBlocks;

import mods.fossil.Fossil;
import mods.fossil.client.LocalizationStrings;
import mods.fossil.fossilEnums.EnumDinoType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityAnalyzer extends TileEntity implements IInventory, ISidedInventory
{

    
    private static final int[] slots_top = new int[] {}; // input
    private static final int[] slots_bottom = new int[] {10,11,12};  //output
    private static final int[] slots_sides = new int[] {0,1,2,3,4,5,6,7,8};//fuel
    
    private String customName;
    
    private ItemStack[] analyzerItemStacks;
    public int analyzerBurnTime = 0;
    public int currentItemBurnTime = 100;
    public int analyzerCookTime = 0;
    private int RawIndex = -1;
    private int SpaceIndex = -1;

    public TileEntityAnalyzer()
    {
        analyzerItemStacks = new ItemStack[13];
    }
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.analyzerItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.analyzerItemStacks[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.analyzerItemStacks[var1] != null)
        {
            ItemStack var3;

            if (this.analyzerItemStacks[var1].stackSize <= var2)
            {
                var3 = this.analyzerItemStacks[var1];
                this.analyzerItemStacks[var1] = null;
                return var3;
            }
            else
            {
                var3 = this.analyzerItemStacks[var1].splitStack(var2);

                if (this.analyzerItemStacks[var1].stackSize == 0)
                {
                    this.analyzerItemStacks[var1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.analyzerItemStacks[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.customName : "tile." + LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME + ".name";
    }
    
    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    /**
     * Sets the custom display name to use when opening a GUI linked to this tile entity.
     */
    public void setGuiDisplayName(String par1Str)
    {
        this.customName = par1Str;
    }


    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        NBTTagList var2 = var1.getTagList("Items");
        this.analyzerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.analyzerItemStacks.length)
            {
                this.analyzerItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.analyzerBurnTime = var1.getShort("BurnTime");
        this.analyzerCookTime = var1.getShort("CookTime");
        this.currentItemBurnTime = 100;
        
        if (var1.hasKey("CustomName"))
        {
            this.customName = var1.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setShort("BurnTime", (short)this.analyzerBurnTime);
        var1.setShort("CookTime", (short)this.analyzerCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.analyzerItemStacks.length; ++var3)
        {
            if (this.analyzerItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.analyzerItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        
        if (this.isInvNameLocalized())
        {
        	var1.setString("CustomName", this.customName);
        }

        var1.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int var1)
    {
        return this.analyzerCookTime * var1 / 200;
    }

    public int getBurnTimeRemainingScaled(int var1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 100;
        }

        return this.analyzerBurnTime * var1 / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.analyzerBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.analyzerBurnTime > 0;
        boolean var2 = false;

        if (this.analyzerBurnTime > 0)
        {
            --this.analyzerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.analyzerBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.analyzerBurnTime = 100;

                if (this.analyzerBurnTime > 0)
                {
                    var2 = true;
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.analyzerCookTime;

                if (this.analyzerCookTime == 200)
                {
                    this.analyzerCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.analyzerCookTime = 0;
            }

            if (var1 != this.analyzerBurnTime > 0)
            {
                var2 = true;
                BlockAnalyzer.updateFurnaceBlockState(this.analyzerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        this.SpaceIndex = -1;
        this.RawIndex = -1;
        int var1;

        for (var1 = 0; var1 < 9; ++var1)
        {
            if (this.analyzerItemStacks[var1] != null)
            {
                int var2 = this.analyzerItemStacks[var1].getItem().itemID;

                if (EnumDinoType.isDinoDrop(this.analyzerItemStacks[var1].getItem()) 
                		|| (var2 == Fossil.biofossil.itemID) 
                		|| (var2 == Fossil.relic.itemID) 
                		|| /*(var2 == Fossil.rawDinoMeat.itemID) 
                		||*/ (var2 == Item.porkRaw.itemID) 
                		|| (var2 == Item.beefRaw.itemID) 
                		|| (var2 == Item.egg.itemID) 
                		|| (var2 == Item.chickenRaw.itemID) 
                		|| (var2 == Block.cloth.blockID) 
                		|| (var2 == Fossil.icedMeat.itemID)
                		|| (var2 == Item.leather.itemID)
                		|| (var2 == Fossil.dodoWing.itemID)
                		|| (var2 == Fossil.terrorBirdMeat.itemID)
                		|| (var2 == Fossil.quaggaMeat.itemID))
                {
                    this.RawIndex = var1;
                    break;
                }
            }
        }

        if (this.RawIndex == -1)
        {
            return false;
        }
        else
        {
            for (var1 = 12; var1 > 8; --var1)
            {
                if (this.analyzerItemStacks[var1] == null)
                {
                    this.SpaceIndex = var1;
                    break;
                }
            }

            return this.SpaceIndex != -1 && this.RawIndex != -1;
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = null;
            int rand = (new Random()).nextInt(100);
            int var3;

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.biofossil)
            {
                if (rand < 1)
                {
                    itemstack = new ItemStack(Fossil.brokenSapling, 1);
                }

                if (rand > 1 && rand <= 45)
                {
                    itemstack = new ItemStack(Item.dyePowder, 3, 15);
                }

                if (rand > 45 && rand <= 80)
                {
                    itemstack = new ItemStack(Block.sand, 3);
                }

                if (rand > 85 && rand <= 85)
                {
                    itemstack = new ItemStack(Fossil.fernSeed, 3);
                }

                if (rand > 90	)
                {
                    int i = (new Random()).nextInt(EnumDinoType.values().length + 2); //+1 for the sapling, +2 for coelacanth
                    Item i0 = null;

                    if (i == 0)
                    {
                        i0 = Fossil.brokenSapling;
                    }
                    else if (i == 1)
                    {
                    	i0 = Fossil.dnaCoelacanth;
                    }
                    else
                    {
                        i0 = EnumDinoType.values()[i - 2].DNAItem;
                    }

                    itemstack = new ItemStack(i0, 1);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem().itemID == Block.cloth.blockID)
            {
                if ((new Random()).nextInt(50) <= 30)
                {
                    itemstack = new ItemStack(Item.silk, 4);
                }
                else
                {
                    itemstack = new ItemStack(Fossil.dnaSheep, 1);
                }
            }

            /*if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.rawDinoMeat)
            {
                var1 = new ItemStack(Fossil.dna, 4, this.analyzerItemStacks[this.RawIndex].getItemDamage());
            }*/
            if (EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem()) != null)
            {
                itemstack = new ItemStack(EnumDinoType.getDNA(this.analyzerItemStacks[this.RawIndex].getItem()), 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item.porkRaw)
            {
                itemstack = new ItemStack(Fossil.dnaPig, 2);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item.beefRaw)
            {
                itemstack = new ItemStack(Fossil.dnaCow, 2);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item.leather)
            {
            	if(new Random().nextInt(10) > 3)
                itemstack = new ItemStack(Fossil.dnaCow, 1);
            	else
            	itemstack = new ItemStack(Fossil.dnaHorse, 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item.egg)
            {
                itemstack = new ItemStack(Fossil.dnaChicken, 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Item.chickenRaw)
            {
                itemstack = new ItemStack(Fossil.dnaChicken, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.dodoWing)
            {
                itemstack = new ItemStack(Fossil.dnaDodo, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.dodoEgg)
            {
                itemstack = new ItemStack(Fossil.dnaDodo, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.cultivatedDodoEgg)
            {
                itemstack = new ItemStack(Fossil.dnaDodo, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.terrorBirdMeat)
            {
            	itemstack = new ItemStack(Fossil.dnaTerrorBird, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.quaggaMeat)
            {
            	itemstack = new ItemStack(Fossil.dnaQuagga, 1);
            }
            
            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.cultivatedTerrorBirdEgg)
            {
            	itemstack = new ItemStack(Fossil.dnaTerrorBird, 1);
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.icedMeat)
            {
                if (rand < 10)
                {
                    itemstack = new ItemStack(Fossil.dnaSmilodon, 1);
                }

                if (rand >= 10 && rand < 20)
                {
                    itemstack = new ItemStack(Fossil.dnaMammoth, 1);
                }

                if (rand >= 20 && rand < 30)
                {
                    itemstack = new ItemStack(Fossil.dnaDodo, 1);
                }

                if (rand >= 30 && rand < 40)
                {
                    itemstack = new ItemStack(Item.chickenRaw, 1);
                }

                if (rand >= 40 && rand < 50)
                {
                    itemstack = new ItemStack(Item.chickenRaw, 1);
                }

                if (rand >= 50 && rand < 60)
                {
                    itemstack = new ItemStack(Item.porkRaw, 1);
                }
                
                if (rand >= 60 && rand < 70)
                {
                    itemstack = new ItemStack(Fossil.dnaQuagga, 1);
                }
                
                if (rand >= 70 && rand < 80)
                {
                	itemstack = new ItemStack(Fossil.dnaTerrorBird, 1);
                }

                if (itemstack == null)
                {
                    itemstack = new ItemStack(Item.beefRaw);
                }
            }

            if (this.analyzerItemStacks[this.RawIndex].getItem() == Fossil.relic)
            {
                if (rand <= 40)
                {
                    itemstack = new ItemStack(Block.gravel, 2);
                }

                if (rand > 40 && rand <= 70)
                {
                    itemstack = new ItemStack(Fossil.stoneboard, 1);
                }

                if (rand > 70 && rand <= 88)
                {
                    itemstack = new ItemStack(Item.flint, 2);
                }
                
                if (rand > 88 && rand <= 92)
                {
                    itemstack = new ItemStack(Fossil.potteryShards, 1);
                }
                
                if (rand > 92 && rand <= 96)
                {
                	if (new Random().nextFloat() < 0.7)
                	itemstack = new ItemStack(Fossil.figurineBlock, 1, new Random().nextInt(5)+10);
                	else
                	itemstack = new ItemStack(Fossil.figurineBlock, 1, new Random().nextInt(5)+5);
                }

                if (rand > 96)
                {
                    itemstack = new ItemStack(Fossil.brokenSword, 1);
                }
            }

            if (itemstack != null)
            {
                if (itemstack.itemID == Item.dyePowder.itemID || itemstack.itemID == Fossil.fernSeed.itemID || itemstack.itemID == Fossil.stoneboard.itemID || itemstack.itemID == Item.flint.itemID || itemstack.itemID == Block.gravel.blockID || itemstack.itemID == Fossil.relic.itemID || itemstack.itemID == Fossil.brokenSapling.itemID || itemstack.itemID == Block.sand.blockID  || (rand == Item.silk.itemID) || (rand == Item.beefRaw.itemID))
                {
                    for (var3 = 12; var3 > 8; --var3)
                    {
                        if (this.analyzerItemStacks[var3] != null && itemstack.itemID == this.analyzerItemStacks[var3].itemID)
                        {
                            if (this.analyzerItemStacks[var3].stackSize + itemstack.stackSize <= this.analyzerItemStacks[var3].getMaxStackSize())
                            {
                                this.analyzerItemStacks[var3].stackSize += itemstack.stackSize;
                                itemstack.stackSize = 0;
                                break;
                            }

                            itemstack.stackSize -= this.analyzerItemStacks[var3].getMaxStackSize() - this.analyzerItemStacks[var3].stackSize;
                            this.analyzerItemStacks[var3].stackSize = this.analyzerItemStacks[var3].getMaxStackSize();
                        }
                    }
                }

                if (itemstack.stackSize != 0 && this.analyzerItemStacks[this.SpaceIndex] == null)
                {
                    this.analyzerItemStacks[this.SpaceIndex] = itemstack.copy();
                }

                --this.analyzerItemStacks[this.RawIndex].stackSize;

                if (this.analyzerItemStacks[this.RawIndex].stackSize == 0)
                {
                    this.analyzerItemStacks[this.RawIndex] = null;
                }
            }
        }
    }

    private static int getItemBurnTime(ItemStack var1)
    {
        return 100;
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 > 8 ? false : (par1 < 8 ? isItemFuel(par2ItemStack) : true);
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par3 != 0 || par1 != 1;
    }
}
