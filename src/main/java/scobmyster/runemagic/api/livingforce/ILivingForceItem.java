package scobmyster.runemagic.api.livingforce;

import net.minecraft.item.ItemStack;

public interface ILivingForceItem
{

    public int getCurrentLivingForce(ItemStack stack);

    public int getMaxLivingForce(ItemStack stack);

    public void addLivingForce(ItemStack stack, int livingForce);

}
