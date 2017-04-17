package scobmyster.runemagic.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import scobmyster.runemagic.Reference;

public class ItemLightCore extends Item
{

    public ItemLightCore(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
    }

}
