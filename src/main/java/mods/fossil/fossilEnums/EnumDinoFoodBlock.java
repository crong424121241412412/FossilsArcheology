package mods.fossil.fossilEnums;

import mods.fossil.Fossil;
import net.minecraft.block.Block;

public enum EnumDinoFoodBlock
{
    Cake(Block.cake, 50, 8),
    Carrot(Block.carrot, 20, 3),
    Crops(Block.crops, 10, 2),
    Leaves(Block.leaves, 20, 4),
    Melon(Block.melon, 65, 6),
    BrownMushroom(Block.mushroomBrown, 15, 3),
    RedMushroom(Block.mushroomRed, 15, 3),
    RedFlower(Block.plantRed, 5, 1),
    YellowFlower(Block.plantYellow, 5, 1),
    Potato(Block.potato, 25, 5),
    Pumpkin(Block.pumpkin, 30, 6),
    Reed(Block.reed, 15, 2),
    Sapling(Block.sapling, 15, 1),
    TallGrass(Block.tallGrass, 5, 1),
    Ferns(Fossil.ferns, 55, 4),
    Palae(Fossil.palmLeaves, 40, 4)
    ;
    public Block block;
    public int FoodValue;
    public int HealValue;

    private EnumDinoFoodBlock(Block Block, int Food, int Heal)
    {
        block = Block;
        FoodValue = Food;
        HealValue = Heal;
    }
    /**
     *
     * Takes the itemid and gives the food value
     */
    public static int getBlockFood(int i0)
    {
        for (int i = 0; i < EnumDinoFoodBlock.values().length; i++)
        {
            if (EnumDinoFoodBlock.values()[i].block.blockID == i0)
            {
                return EnumDinoFoodBlock.values()[i].FoodValue;
            }
        }

        return 0;
    }
}
