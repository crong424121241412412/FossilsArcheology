package mods.fossil.client.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class FossilGuiButton extends GuiButton
{
    private int IconIndexHeight;
    private int IconIndexWidth;

    public FossilGuiButton(int par1, int par2, int par3, int par4)
    {
        super(par1, par2, par3, 32, 32, "");

        for (; par4 > 7;)
        {
            par4 -= 8;
            IconIndexHeight++;
        }

        IconIndexWidth = par4;
    }

    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {}
}